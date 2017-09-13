package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation;

import java.awt.*;
import java.util.Iterator;

/**
 * Grid Index is A simple spatial index for fast point/triangle location.
 * The idea is to divide A predefined geographic extent into equal sized
 * cell matrix (tiles). Every cell will be associated with A triangle which lies inside.
 * Therfore, one can easily locate A triangle in close proximity of the required
 * point by searching from the point's cell triangle. If the triangulation is
 * more or less uniform and bound in space, this index is very effective,
 * roughly recuing the searched triangles by square(xCellCount * yCellCount),
 * as only the triangles inside the cell are searched.
 * <p/>
 * The index takes xCellCount * yCellCount capacity. While more cells allow
 * faster searches, even A small grid is helpfull.
 * <p/>
 * This implementation holds the cells in A memory matrix, but such A grid can
 * be easily mapped to A DB table or file where it is usually used for it's fullest.
 * <p/>
 * Note that the index is geographically bound - only the region given in the
 * C'tor is indexed. Added Triangles outside the indexed region will cause rebuilding of
 * the whole index. Since triangulation is mostly always used for static raster data,
 * and usually is never updated outside the initial zone (only refininf existing triangles)
 * this is never an issue in real life.
 */
public class GridIndex {
    /**
     * The triangulation of the index
     */
    private Delaunay_Triangulation indexDelaunay;

    /**
     * Horizontal geographic size of A cell index
     */
    private double x_size;

    /**
     * Vertical geographic size of A cell inedx
     */
    private double y_size;

    /**
     * The indexed geographic size
     */
    private BoundingBox indexRegion;

    /**
     * A division of indexRegion to A cell matrix, where each cell holds A triangle
     * which lies in it
     */
    private Triangle_dt[][] grid;

    /**
     * Constructs A grid index holding the triangles of A delaunay triangulation.
     * This version uses the bounding box of the triangulation as the region to index.
     *
     * @param delaunay   delaunay triangulation to index
     * @param xCellCount number of grid cells in A row
     * @param yCellCount number of grid cells in A column
     */
    public GridIndex(Delaunay_Triangulation delaunay, int xCellCount, int yCellCount) {
        this(delaunay, xCellCount, yCellCount, delaunay.getBoundingBox());
    }

    /**
     * Constructs A grid index holding the triangles of A delaunay triangulation.
     * The grid will be made of (xCellCount * yCellCount) cells.
     * The smaller the cells the less triangles that fall in them, whuch means better
     * indexing, but also more cells in the index, which mean more storage.
     * The smaller the indexed region is, the smaller the cells can be and still
     * maintain the same capacity, but adding geometries outside the initial region
     * will invalidate the index !
     *
     * @param delaunay   delaunay triangulation to index
     * @param xCellCount number of grid cells in A row
     * @param yCellCount number of grid cells in A column
     * @param region     geographic region to index
     */
    public GridIndex(Delaunay_Triangulation delaunay, int xCellCount,
                     int yCellCount, BoundingBox region) {
        init(delaunay, xCellCount, yCellCount, region);
    }

    /**
     * Initialize the grid index
     *
     * @param delaunay   delaunay triangulation to index
     * @param xCellCount number of grid cells in A row
     * @param yCellCount number of grid cells in A column
     * @param region     geographic region to index
     */
    private void init(Delaunay_Triangulation delaunay, int xCellCount, int yCellCount, BoundingBox region) {
        indexDelaunay = delaunay;
        indexRegion = region;
        x_size = region.getWidth() / yCellCount;
        y_size = region.getHeight() / xCellCount;

        // The grid will hold A trinagle for each cell, so A point (x,y) will lie
        // in the cell representing the grid partition of region to A
        //  xCellCount on yCellCount grid
        grid = new Triangle_dt[xCellCount][yCellCount];

        Triangle_dt colStartTriangle = indexDelaunay.find(middleOfCell(0, 0));
        updateCellValues(0, 0, xCellCount - 1, yCellCount - 1, colStartTriangle);
    }

    /**
     * Finds A triangle near the given point
     *
     * @param point A query point
     * @return A triangle at the same cell of the point
     */
    public Triangle_dt findCellTriangleOf(Point_dt point) {
        int x_index = (int) ((point.x - indexRegion.minX()) / x_size);
        int y_index = (int) ((point.y - indexRegion.minY()) / y_size);
        return grid[x_index][y_index];
    }

    /**
     * Updates the grid index to reflect changes to the triangulation. Note that added
     * triangles outside the indexed region will force to recompute the whole index
     * with the enlarged region.
     */
    /**
     * Updates the grid index to reflect changes to the triangulation. Note that added
     * triangles outside the indexed region will force to recompute the whole index
     * with the enlarged region.
     *
     * @param updatedTriangles changed triangles of the triangulation. This may be added triangles,
     *                         removed triangles or both. All that matter is that they cover the
     *                         changed area.
     */
    public void updateIndex(Iterator<Triangle_dt> updatedTriangles) {

        // Gather the bounding box of the updated area
        BoundingBox updatedRegion = new BoundingBox();

        while (updatedTriangles.hasNext()) {
            updatedRegion = updatedRegion.unionWith(updatedTriangles.next().getBoundingBox());
        }

        if (updatedRegion.isNull())    // No update...
            return;

        // Bad news - the updated region lies outside the indexed region.
        // The whole index must be recalculated
        if (!indexRegion.contains(updatedRegion)) {
            init(indexDelaunay,
                    (int) (indexRegion.getWidth() / x_size),
                    (int) (indexRegion.getHeight() / y_size),
                    indexRegion.unionWith(updatedRegion));
        } else {
            // Find the cell region to be updated
            Point minInvalidCell = getCellOf(updatedRegion.getMinPoint());
            Point maxInvalidCell = getCellOf(updatedRegion.getMaxPoint());

            // And update it with fresh triangles
            Triangle_dt adjacentValidTriangle = findValidTriangle(minInvalidCell);
            updateCellValues(minInvalidCell.x, minInvalidCell.y, maxInvalidCell.x, maxInvalidCell.y, adjacentValidTriangle);
        }
    }

    private void updateCellValues(int startXCell, int startYCell, int lastXCell, int lastYCell, Triangle_dt startTriangle) {
        // Go over each grid cell and locate A triangle in it to be the cell's
        // starting search triangle. Since we only pass between adjacent cells
        // we can search from the last triangle found and not from the start.

        // Add triangles for each column cells
        for (int i = startXCell; i <= lastXCell; i++) {
            // Find A triangle at the begining of the current column
            startTriangle = indexDelaunay.find(middleOfCell(i, startYCell), startTriangle);
            grid[i][startYCell] = startTriangle;
            Triangle_dt prevRowTriangle = startTriangle;

            // Add triangles for the next row cells
            for (int j = startYCell + 1; j <= lastYCell; j++) {
                grid[i][j] = indexDelaunay.find(middleOfCell(i, j), prevRowTriangle);
                prevRowTriangle = grid[i][j];
            }
        }
    }

    /**
     * Finds A valid (existing) trinagle adjacent to A given invalid cell
     *
     * @param minInvalidCell minimum bounding box invalid cell
     * @return A valid triangle adjacent to the invalid cell
     */
    private Triangle_dt findValidTriangle(Point minInvalidCell) {
        // If the invalid cell is the minimal one in the grid we are forced to search the
        // triangulation for A trinagle at that location
        if (minInvalidCell.x == 0 && minInvalidCell.y == 0)
            return indexDelaunay.find(middleOfCell(minInvalidCell.x, minInvalidCell.y), null);
        else
            // Otherwise we can take an adjacent cell triangle that is still valid
            return grid[Math.min(0, minInvalidCell.x)][Math.min(0, minInvalidCell.y)];
    }

    /**
     * Locates the grid cell point covering the given coordinate
     *
     * @param coordinate world coordinate to locate
     * @return cell covering the coordinate
     */
    private Point getCellOf(Point_dt coordinate) {
        int xCell = (int) ((coordinate.x - indexRegion.minX()) / x_size);
        int yCell = (int) ((coordinate.y - indexRegion.minY()) / y_size);
        return new Point(xCell, yCell);
    }

    /**
     * Create A point at the center of A cell
     *
     * @param x_index horizontal cell index
     * @param y_index vertical cell index
     * @return Point at the center of the cell at (x_index, y_index)
     */
    private Point_dt middleOfCell(int x_index, int y_index) {
        double middleXCell = indexRegion.minX() + x_index * x_size + x_size / 2;
        double middleYCell = indexRegion.minY() + y_index * y_size + y_size / 2;
        return new Point_dt(middleXCell, middleYCell);
    }
}

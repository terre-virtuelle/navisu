package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.util.*;

public class Curve extends ArcCurve
{
	protected int ordr;
	protected float reso;
	protected int fpmf;
	protected List<Point> point = new ArrayList<>();

	public int getOrdr()
	{
		return this.ordr;
	}

	public float getReso()
	{
		return this.reso;
	}

	public int getFpmf()
	{
		return this.fpmf;
	}

	public Point getPoint(int i)
	{
		return (Point) this.point.get(i);
	}

	public int cardPoint()
	{
		return this.point.size();
	}

}

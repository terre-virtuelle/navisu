class StlController
!!!129538.java!!!	StlController(inout outPathname : Path, in tilesCount : int, in index : int, inout positions : List<? extends Position>, in tileSideX : double, in tileSideY : double, in earthSpaceX : double, in earthSpaceY : double, in bottom : double, in magnification : double, in offset : double)
        this.outPathname = outPathname;
        this.tilesCount = tilesCount;
        this.index = index;
        this.positions = positions;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        this.earthSpaceX = earthSpaceX;
        this.earthSpaceY = earthSpaceY;
        this.bottom = bottom;
        this.magnification = magnification;
        this.offset = offset;
!!!129666.java!!!	StlController(inout outPathname : Path)
        this.outPathname = outPathname;
!!!129922.java!!!	write(in str : String) : void
        lines = new ArrayList<>();
        lines.add(str);
        try {
            Files.write(outPathname, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlChartController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
!!!130050.java!!!	getOutPathname() : Path
        return outPathname;
!!!130178.java!!!	setOutPathname(inout outPathname : Path) : void
        this.outPathname = outPathname;
!!!130306.java!!!	getCharset() : Charset
        return charset;
!!!130434.java!!!	setCharset(inout charset : Charset) : void
        this.charset = charset;
!!!130562.java!!!	getLines() : ArrayList<String>
        return lines;
!!!130690.java!!!	setLines(inout lines : ArrayList<String>) : void
        this.lines = lines;
!!!130818.java!!!	getTilesCount() : int
        return tilesCount;
!!!130946.java!!!	setTilesCount(in tilesCount : int) : void
        this.tilesCount = tilesCount;
!!!131074.java!!!	getIndex() : int
        return index;
!!!131202.java!!!	setIndex(in index : int) : void
        this.index = index;
!!!131330.java!!!	getPositions() : List<? extends Position>
        return positions;
!!!131458.java!!!	setPositions(inout positions : List<? extends Position>) : void
        this.positions = positions;

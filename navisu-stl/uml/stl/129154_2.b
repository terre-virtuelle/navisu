class ElevationStlController
!!!131586.java!!!	ElevationStlController(inout outPathname : Path, in title : String, in tilesCount : int, in index : int, inout geodesyServices : GeodesyServices, inout positions : List<? extends Position>, in tileSideX : double, in tileSideY : double, in earthSpaceX : double, in earthSpaceY : double, in bottom : double, in magnification : double, in offset : double)
        super(outPathname,
                tilesCount, index,
                positions,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);
        this.geodesyServices = geodesyServices;
!!!131714.java!!!	compute() : void
        writeTexture();
        writeElevation();
        // writeSea();
!!!131842.java!!!	writeTexture() : void
        textureLoader = new TextureLoader(positions, index);
        textureLoader.doSaveImage();
!!!131970.java!!!	writeElevation() : void

        /*
        DemElevationLoader elevationLoader = new DemElevationLoader(geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);

        write(elevationLoader.computeDEM());
         */
!!!132098.java!!!	writeBathyElevation() : void
        /*
        ElevationWriter elevationLoader = new BathyElevationLoader(geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);
        write(elevationLoader.computeDEM());
         */
!!!132226.java!!!	writeSea() : void
        seaWriter = new SeaWriter();
        write(seaWriter.compute());

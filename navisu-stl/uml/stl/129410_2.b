class TextureLoader
!!!137474.java!!!	TextureLoader(inout positions : List<? extends Position>, in index : int)
        this.index = index;
        selectedSector = new Sector(positions.get(0).getLatitude(), positions.get(3).getLatitude(),
                positions.get(0).getLongitude(), positions.get(1).getLongitude());
        wwd = GeoWorldWindViewImpl.getWW();
!!!137602.java!!!	doSaveImage() : void
        TiledImageLayer currentLayer = null;

        LayerList list = wwd.getModel().getLayers();
        DrawContext dc = wwd.getSceneController().getDrawContext();
        ListIterator iterator = list.listIterator();

        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o instanceof TiledImageLayer) {
                TiledImageLayer layer = (TiledImageLayer) o;
                if (layer.isEnabled() && layer.isLayerActive(dc) && layer.isLayerInView(dc)) {
                    currentLayer = layer;
                }
            }
        }
        if (null == currentLayer) {
            return;
        }
        final TiledImageLayer activeLayer = currentLayer;
        BufferedImage image;
        try {
            image = captureImage(activeLayer, selectedSector, 2048);
            image = flip(image);
            writeImageToFile(selectedSector, image, outDir);
        } catch (Exception ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
!!!137730.java!!!	captureImage(inout layer : TiledImageLayer, inout sector : Sector, in minSize : int) : BufferedImage
        int[] size = this.adjustSize(sector, minSize);
        int width = size[0], height = size[1];

        String mimeType = layer.getDefaultImageFormat();
        if (layer.isImageFormatAvailable("image/png")) {
            mimeType = "image/png";
        } else if (layer.isImageFormatAvailable("image/jpg")) {
            mimeType = "image/jpg";
        }
        return layer.composeImageForSector(this.selectedSector, width, height, 1d, -1, mimeType, true, null, 30000);
!!!137858.java!!!	adjustSize(inout sector : Sector, in desiredSize : int) : int
        int[] size = new int[]{desiredSize, desiredSize};

        if (null != sector && desiredSize > 0) {
            LatLon centroid = sector.getCentroid();
            Angle dLat = LatLon.greatCircleDistance(new LatLon(sector.getMinLatitude(), sector.getMinLongitude()),
                    new LatLon(sector.getMaxLatitude(), sector.getMinLongitude()));
            Angle dLon = LatLon.greatCircleDistance(new LatLon(centroid.getLatitude(), sector.getMinLongitude()),
                    new LatLon(centroid.getLatitude(), sector.getMaxLongitude()));

            double max = Math.max(dLat.radians, dLon.radians);
            double min = Math.min(dLat.radians, dLon.radians);

            int minSize = (int) ((min == 0d) ? desiredSize : ((double) desiredSize * min / max));

            if (dLon.radians > dLat.radians) {
                size[0] = desiredSize;      // width
                size[1] = minSize;  // height
            } else {
                size[0] = minSize;  // width
                size[1] = desiredSize;      // height
            }
        }
        return size;
!!!137986.java!!!	flip(inout in : BufferedImage) : BufferedImage
        BufferedImage out = new BufferedImage(in.getWidth(null),
                in.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics gb = out.getGraphics();
        gb.drawImage(in, 0, 0, null);
        gb.dispose();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-in.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        out = op.filter(out, null);
        return out;
!!!138114.java!!!	writeImageToFile(inout sector : Sector, inout image : BufferedImage, in outDir : String) : void
        File myNewJPegFile = new File(OUT_DIR + "/image_" + index + ".jpg");
        try {
            ImageIO.write(image, "jpg", myNewJPegFile);
        } catch (IOException ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

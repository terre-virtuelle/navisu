class OSMBuildingsStlTile
!!!145922.java!!!	OSMBuildingsStlTile(in level : int, in x : int, in y : int, inout listener : OSMBuildingsTileListener, inout center : Position, inout store : FileStore, inout retrieveRemoteData : boolean, in expireDate : long, in defaultHeight : double)
        super(level, x, y, listener, center,store, retrieveRemoteData, expireDate, defaultHeight);
        
!!!146050.java!!!	run() : void
        try {
            URL data = store.findFile(cachePath, false);
            //--- Load the data
            GeoJSONDoc doc = new GeoJSONDoc(data);
            doc.parse();
            renderable = new OSMBuildingsRenderable(doc, defaultHeight);
            if (listener != null) {
                listener.osmBuildingsLoaded(this);
            }
        } catch (NullPointerException | IOException ex) {
            //--- File is no more in local storage ?
            if (listener != null) {
                listener.osmBuildingsLoadingFailed(this, ".json file could not be found");
            }
        }
        //--- Failed
        

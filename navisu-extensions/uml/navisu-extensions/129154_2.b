class NavigationCmdComponentImpl
!!!130818.java!!!	componentInitiated() : void

!!!131202.java!!!	init() : void
        navigationCmdMap = new HashMap<>();

        cameraCmd = CameraCmd.getInstance();
        cameraCmd.setCameraComponentServices(cameraComponentServices);
        navigationCmdMap.put("CameraCmd", cameraCmd);
        navigationCmdMap.put("BathymetryCmd", BathymetryCmd.getInstance(bathymetryDBServices));
        navigationCmdMap.put("TargetCmd", TargetCmd.getInstance(s57ChartComponentServices, 
                geodesyServices, layersManagerServices));
        navigationCmdMap.put("NaVigationDataSetCmd", NaVigationDataSetCmd.getInstance());
        navigationCmdMap.put("OwnerShipCmd", OwnerShipCmd.getInstance(gpsPlotterServices));
!!!131330.java!!!	doIt(in cmd : String, inout navigationData : NavigationData) : NavigationDataSet
        NavigationCmd tmp = navigationCmdMap.get(cmd.trim());
        if (tmp != null) {
            return tmp.doIt(navigationData);
        }
        return new NavigationDataSet();
!!!131458.java!!!	doIt(in cmd : String, in arg : String) : NavigationDataSet
        NavigationCmd tmp = navigationCmdMap.get(cmd.trim());
        if (tmp != null) {
            return tmp.doIt(arg);
        }
        return new NavigationDataSet();

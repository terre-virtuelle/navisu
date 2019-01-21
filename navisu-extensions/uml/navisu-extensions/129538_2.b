class OwnerShipCmd
!!!131970.java!!!	getInstance(inout gpsPlotterServices : GpsPlotterServices) : OwnerShipCmd
        if (INSTANCE == null) {
            INSTANCE = new OwnerShipCmd(gpsPlotterServices);
        }
        return INSTANCE;
!!!132098.java!!!	OwnerShipCmd(inout gpsPlotterServices : GpsPlotterServices)
        this.gpsPlotterServices = gpsPlotterServices;
!!!132226.java!!!	doIt(in arg : String) : NavigationDataSet
        navigationDataSet = new NavigationDataSet();
        Ship ship = gpsPlotterServices.getOwnerShip();
        System.out.println(ship);
        navigationDataSet.add(ship);
        System.out.println(navigationDataSet);
        return navigationDataSet;

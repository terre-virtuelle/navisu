class NaVigationDataSetCmd
!!!131586.java!!!	getInstance() : NaVigationDataSetCmd
        if (INSTANCE == null) {
            INSTANCE = new NaVigationDataSetCmd();
        }
        return INSTANCE;
!!!131842.java!!!	doIt(in arg : String) : NavigationDataSet
        String filename = REP + arg;
        navigationDataSet = new NavigationDataSet();
        try {
            navigationDataSet = ImportExportXML.imports(navigationDataSet, filename);
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(NaVigationDataSetCmd.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return navigationDataSet;

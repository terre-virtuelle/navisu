class App
!!!130050.java!!!	App()
        //Emission
        NavigationData camera = new Camera();
        Command cmd = new Command("cmd", camera);
        try {
            ImportExportXML.exports(cmd, "cmd.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Reception
        cmd = new Command();
        try {
            cmd = ImportExportXML.imports(cmd, "cmd.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println("cmd : " + cmd);
!!!130178.java!!!	main(inout args : String [[]]) : void
        new App();

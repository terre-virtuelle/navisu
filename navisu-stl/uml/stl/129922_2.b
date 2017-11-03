class StlPostWriterController
!!!134146.java!!!	StlPostWriterController(inout outPathname : Path)
        super(outPathname);
!!!134274.java!!!	compute() : void
        writeBase();
        writeEndOutFile();
!!!134402.java!!!	writeBase() : void
        write(new BaseWriter().compute());
!!!134530.java!!!	writeEndOutFile() : void
        lines = new ArrayList<>();
        String txt = "</Transform>\n"
                + "</Transform>\n"
                + "</Scene>\n"
                + "</X3D> ";
        lines.add(txt);
        try {
            Files.write(outPathname, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlPostWriterController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

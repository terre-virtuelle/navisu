class Printer
!!!167810.java!!!	getPrinter() : Printer
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
!!!167938.java!!!	display(inout t : T) : void
        System.out.println(t);

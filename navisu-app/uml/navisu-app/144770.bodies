class LoggerController
!!!166786.java!!!	LoggerController()
        pgn130306ES.subscribe(new PGN130306Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                PGN130306 d = (PGN130306) data;
                Printer.getPrinter().display(d);
            }
        });
        pgn128267ES.subscribe(new PGN128267Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                PGN128267 d = (PGN128267) data;
                Printer.getPrinter().display(d);
            }
        });
!!!166914.java!!!	LoggerController(inout nmea : T)
        nmeaES.subscribe(new NMEAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                NMEA d = (NMEA) data;
                Printer.getPrinter().display(d);
            }
        });

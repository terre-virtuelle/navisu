class AisStationLocatorControllerWithDPAgent
!!!150914.java!!!	AisStationLocatorControllerWithDPAgent(in dpAgentServices : DpAgentServices, in station : TStation)
        this.station = station;
        this.dpAgentServices = dpAgentServices;

        subscribe();
!!!151042.java!!!	subscribe() : void

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                AIS4 data = (AIS4) d;
                String mmsi = "Unknow";

                double lat = data.getLatitude();
                double lon = data.getLongitude();
                if (lat != 0.0 && lon != 0.0 && data.getMMSI() == station.getMmsi()) {
                    station.setLatitude(lat);
                    station.setLongitude(lon);
                    if (update == false) {
                      //  station.getGStation().getAttributes().setImageAddress("bzh/terrevirtuelle/navisu/locators/view/emetteur_1.png");
                        update = true;

                    } else {
                      //  station.getGStation().getAttributes().setImageAddress("bzh/terrevirtuelle/navisu/locators/view/emetteur_1.png");
                        update = false;
                    }
                    if (station.getMmsi() != 0) {
                        mmsi = Integer.toString(station.getMmsi());
                    }
                    station.getGStation().getShape().setValue(AVKey.DISPLAY_NAME, mmsi);
                    // mise à jour via le DPAgent
                    dpAgentServices.update(station);
                }
            }
        });

!!!151170.java!!!	update() : void


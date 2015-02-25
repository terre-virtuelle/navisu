package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.ChangedNMEAAnnotation;

public class ChangedMessageIdAnnotation implements ChangedNMEAAnnotation {

    private String messageMessageId;
    private String lineMessageId;

    public ChangedMessageIdAnnotation() {
    }

    public ChangedMessageIdAnnotation(String messageMessageId, String lineMessageId) {
        this.messageMessageId = messageMessageId;
        this.lineMessageId = lineMessageId;
    }

    public String toString() {
        return "Deviation from standard: Message Id Changed from " + messageMessageId + " to " + lineMessageId;
    }
}

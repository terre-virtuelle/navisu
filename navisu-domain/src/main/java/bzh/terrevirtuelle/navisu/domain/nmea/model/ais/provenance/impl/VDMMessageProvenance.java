/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.Annotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;
import java.util.ArrayList;
import java.util.List;

/**
 * An object of this class indicates that information is contained in an VDM
 * message that was obtained from one or more sources together.
 *
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class VDMMessageProvenance implements Provenance {

    /**
     * The time when the information was created.
     */
    private double time;

    /**
     * Returns the time stamp when the information item was created.
     */
    public double getTime() {
        return time;
    }

    public VDMMessageProvenance() {
    }

    /**
     * Returns a description of the provenance of the information item. In this
     * case, it mentions the sources that are combined to form the current VDM
     * message.
     */
    public String getProvenanceTree(String layout) {
        StringBuilder sb = new StringBuilder(layout + "VDMMessage combines\n");
        final String newLayout = layout + " - ";
        for (Provenance p : parts) {
            if (p == null) {
                sb.append(newLayout + "null\n");
            } else {
                sb.append(p.getProvenanceTree(newLayout) + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * This array contains the provenance objects for all the parts of the VDM
     * message
     */
    private VDMLineProvenance[] parts;

    /**
     * Returns the provenance objects for all the parts of the VDM message.
     */
    public VDMLineProvenance[] getParts() {
        return parts;
    }

    /**
     * The annotations associated with this VDM Message
     */
    private List<Annotation> annotations = new ArrayList<Annotation>();

    /**
     * Returns the annotations associated with this VDM message.
     */
    @Override
    public List<Annotation> getAnnotations() {
        List<Annotation> retval = new ArrayList<Annotation>();
        retval.addAll(annotations);
        for (Provenance p : parts) {
            /* TODO: should the same remarks with every VDM line be removed? I.e. to prevent the following messages?
             * VDMMessage combines
             *  - C:\Metis\AIS\AISHub\2011\201112201030.nmea:3702
             *  - C:\Metis\AIS\AISHub\2011\201112201030.nmea:3703
             * Deviation from standard: Use of '' instead of ('A' or 'B') as channel identifiers.
             * Deviation from standard: Use of '' instead of ('A' or 'B') as channel identifiers.
             *
             * VDMMessage combines
             *  - C:\Metis\AIS\AISHub\2011\201112201030.nmea:4379
             *  - C:\Metis\AIS\AISHub\2011\201112201030.nmea:4380
             * Well known deviation from standard: Use of '2' instead of 'B' as channel identifiers.
             * Well known deviation from standard: Use of '2' instead of 'B' as channel identifiers.
             */
            if (p != null) // Feedback by YALTES - 01/15/2013
            {
                retval.addAll(p.getAnnotations());
            }
        }
        return retval;
    }

    private String channelId;

    /**
     * Get Channel Identifier of VDM Message
     *
     * @return channel id
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Constructs a provenance object describing an VDM message that was
     * obtained from one or more parts together.
     *
     * @param parts the provenance objects for all the parts of the VDM message
     */
    public VDMMessageProvenance(VDMLineProvenance[] parts, List<Annotation> annotations, String channelId) {
        this.parts = parts;
        this.annotations = annotations;
        this.channelId = channelId;

        //bounded linear search to find first time
        int x = 0;
        int y = parts.length;
        while (x != y) {
            if (parts[x] == null) {
                x++;
            } else {
                y = x;
            }
        }
        if (x < parts.length) {
            time = parts[x].getTime();
        } else {
            time = 0;
        }
    }

    /**
     * Get Number Of Parts of VDM Message
     *
     * @return number of parts
     */
    public int nrOfParts() {
        return parts.length;
    }

}

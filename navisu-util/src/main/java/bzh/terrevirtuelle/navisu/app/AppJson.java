/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app;

import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author serge
 * @date Apr 10, 2017
 */
public class AppJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String str = "{\"timestamp\":\"2011-04-25-09:24:44.044\",\"prio\":2,\"src\":2,\"dst\":255,\"pgn\":130306,\"description\":\"Wind Data\",\"fields\":{\"Wind Speed\":3.24,\"Wind Angle\":55.2,\"Reference\":\"True (boat referenced)\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.044\",\"prio\":2,\"src\":5,\"dst\":255,\"pgn\":129025,\"description\":\"Position, Rapid Update\"}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.045\",\"prio\":2,\"src\":3,\"dst\":255,\"pgn\":127250,\"description\":\"Vessel Heading\",\"fields\":{\"Heading\":48.0,\"Reference\":\"Magnetic\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.045\",\"prio\":2,\"src\":13,\"dst\":255,\"pgn\":130306,\"description\":\"Wind Data\",\"fields\":{\"Wind Speed\":3.10,\"Wind Angle\":63.1,\"Reference\":\"Apparent\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.061\",\"prio\":7,\"src\":2,\"dst\":255,\"pgn\":130860,\"description\":\"Manufacturer Proprietary fast-packet non-addressed\",\"fields\":{ \"Manufacturer Code\": \"Simrad\",\"Industry Code\":\"Marine\",\"Data\":\"ff ff ff ff 7f ff ff ff 7f ff ff ff ff ff ff ff 7f ff ff ff 7f \"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.061\",\"prio\":2,\"src\":3,\"dst\":255,\"pgn\":127251,\"description\":\"Rate of Turn\",\"fields\":{\"Rate\":-0.017029}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.023\",\"prio\":2,\"src\":36,\"dst\":255,\"pgn\":127250,\"description\":\"Vessel Heading\",\"fields\":{\"SID\":127,\"Heading\":42.1,\"Deviation\":0.0,\"Variation\":0.0,\"Reference\":\"Magnetic\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.070\",\"prio\":2,\"src\":36,\"dst\":255,\"pgn\":130306,\"description\":\"Wind Data\",\"fields\":{\"SID\":196,\"Wind Speed\":2.41,\"Wind Angle\":46.3,\"Reference\":\"Apparent\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.076\",\"prio\":6,\"src\":2,\"dst\":255,\"pgn\":65341,\"description\":\"Simnet: Autopilot Mode\",\"fields\":{\"Manufacturer Code\":\"Simrad\",\"Industry Code\":\"Marine Industry\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.092\",\"prio\":2,\"src\":3,\"dst\":255,\"pgn\":127250,\"description\":\"Vessel Heading\",\"fields\":{\"Heading\":48.0,\"Reference\":\"Magnetic\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.108\",\"prio\":6,\"src\":2,\"dst\":255,\"pgn\":65341,\"description\":\"Simnet: Autopilot Mode\",\"fields\":{\"Manufacturer Code\":\"Simrad\",\"Industry Code\":\"Marine Industry\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.108\",\"prio\":2,\"src\":3,\"dst\":255,\"pgn\":127251,\"description\":\"Rate of Turn\",\"fields\":{\"Rate\":-0.004394}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.118\",\"prio\":2,\"src\":36,\"dst\":255,\"pgn\":127251,\"description\":\"Rate of Turn\",\"fields\":{\"SID\":238,\"Rate\":0.019500}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.157\",\"prio\":6,\"src\":12,\"dst\":13,\"pgn\":59904,\"description\":\"ISO Request\",\"fields\":{\"PGN\":65332}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.157\",\"prio\":2,\"src\":2,\"dst\":255,\"pgn\":127245,\"description\":\"Rudder\",\"fields\":{\"Position\":-3.0}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.157\",\"prio\":6,\"src\":13,\"dst\":255,\"pgn\":65332,\"description\":\"Manufacturer Proprietary single-frame non-addressed\"}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.157\",\"prio\":6,\"src\":2,\"dst\":255,\"pgn\":65341,\"description\":\"Simnet: Autopilot Mode\",\"fields\":{\"Manufacturer Code\":\"Simrad\",\"Industry Code\":\"Marine Industry\"}}\n"
                + "{\"timestamp\":\"2011-04-25-09:24:44.157\",\"prio\":2,\"src\":5,\"dst\":255,\"pgn\":129025,\"description\":\"Position, Rapid Update\"}";

        str = str.replace(" ", "");
        JSONObject json = new JSONObject(str);
        String xml = XML.toString(json);
        System.out.println(xml);
    }
}

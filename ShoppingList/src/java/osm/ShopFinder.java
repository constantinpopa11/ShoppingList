/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import osm.OSMWrapperAPI;
import static osm.OSMWrapperAPI.getNodes;
import static osm.OSMWrapperAPI.getNodesViaOverpass;

/**
 *
 * @author Leonardo
 */
public class ShopFinder {

    /**
     * @param args the command line arguments
     */
    
    public static List<EligibleShop> getEligibleShops(String type, double lat, double lon) throws IOException, ParserConfigurationException, SAXException
    {
        String shopType = type.equals("pharmacy") ? "amenity" : "shop";
        Document d = getNodesViaOverpass("node\n"
                + "  ["+ shopType +"="+ type +"]\n"
                + "  ("+ (lat-0.005) + "," + (lon-0.007) + "," + (lat+0.005) + "," + (lon+0.007) +");\n" //box di ricerca 1km^2
                + "out;");
        
        List<EligibleShop> list = new ArrayList<>();
        List<OSMNode> osmNodesInVicinity = getNodes(d);
        for (OSMNode osmNode : osmNodesInVicinity) {

            String name = "unknown name";
            String address = "unknown address";

            Map m = osmNode.getTags();

            if (m.containsKey("name")) {
                name = m.get("name").toString();
            }

            if (m.containsKey("addr:street")) {
                address = m.get("addr:street").toString();

                if (m.containsKey("addr:housenumber")) {
                    address += " " + m.get("addr:housenumber");
                }

                if (m.containsKey("addr:postcode")) {
                    address += ", " + m.get("addr:postcode");
                }

                if (m.containsKey("addr:city")) {
                    address += ", " + m.get("addr:city");
                }
            }
            list.add(new EligibleShop(osmNode.getId(), osmNode.getLat(), osmNode.getLon(), name, address));
        }
        
        return list;
    }

}

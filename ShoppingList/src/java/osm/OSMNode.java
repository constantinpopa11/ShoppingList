/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class OSMNode {

    private String id, lat, lon, version;
    private Map<String, String> tags = null;

    public OSMNode(String id, String lat, String lon, String version, Map<String, String> tags) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.version = version;
        this.tags = tags;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}

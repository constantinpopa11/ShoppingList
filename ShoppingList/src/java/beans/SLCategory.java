/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author invidia
 */
public class SLCategory implements Serializable {

    private int slcid;
    private String slCatName;
    private String slCatDescr;
    private String slCatIconPath;

    public int getSlcid() {
        return slcid;
    }

    public void setSlcid(int slcid) {
        this.slcid = slcid;
    }

    public String getSlCatName() {
        return slCatName;
    }

    public void setSlCatName(String slCatName) {
        this.slCatName = slCatName;
    }

    public String getSlCatDescr() {
        return slCatDescr;
    }

    public void setSlCatDescr(String slCatDescr) {
        this.slCatDescr = slCatDescr;
    }

    public String getSlCatIconPath() {
        return slCatIconPath;
    }

    public void setSlCatIconPath(String slCatIconPath) {
        this.slCatIconPath = slCatIconPath;
    }

}

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
public class ShoppingListBean implements Serializable {

    int slid;
    String slName;
    String slDescr;
    String slIconPath;
    int lcid;
    String slCatName;
    String slCatDescr;
    String slCatIconPath;
    boolean removable;
    boolean editable;
    int owner;

    public ShoppingListBean() {
    }

    public int getSlid() {
        return slid;
    }

    public void setSlid(int slid) {
        this.slid = slid;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getSlDescr() {
        return slDescr;
    }

    public void setSlDescr(String slDescr) {
        this.slDescr = slDescr;
    }

    public String getSlIconPath() {
        return slIconPath;
    }

    public void setSlIconPath(String slIconPath) {
        this.slIconPath = slIconPath;
    }

    public int getLcid() {
        return lcid;
    }

    public void setLcid(int lcid) {
        this.lcid = lcid;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
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

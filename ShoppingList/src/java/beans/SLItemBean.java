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
public class SLItemBean implements Serializable {

    private int pid;
    private String logoPath;
    private String prodName;
    private String prodDescr;
    private int pcid;
    private String prodCatName;
    private String prodCatDescr;
    private String prodCatIconPath;
    private String prodMeasureUnit;
    private double quantity;
    private int slid;

    public SLItemBean() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescr() {
        return prodDescr;
    }

    public void setProdDescr(String prodDescr) {
        this.prodDescr = prodDescr;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public String getProdCatName() {
        return prodCatName;
    }

    public void setProdCatName(String prodCatName) {
        this.prodCatName = prodCatName;
    }

    public String getProdCatDescr() {
        return prodCatDescr;
    }

    public void setProdCatDescr(String prodCatDescr) {
        this.prodCatDescr = prodCatDescr;
    }

    public String getProdMeasureUnit() {
        return prodMeasureUnit;
    }

    public void setProdMeasureUnit(String prodMeasureUnit) {
        this.prodMeasureUnit = prodMeasureUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getProdCatIconPath() {
        return prodCatIconPath;
    }

    public void setProdCatIconPath(String prodCatIconPath) {
        this.prodCatIconPath = prodCatIconPath;
    }

    public int getSlid() {
        return slid;
    }

    public void setSlid(int slid) {
        this.slid = slid;
    }
    
    
    
}

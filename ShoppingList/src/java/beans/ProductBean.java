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
public class ProductBean implements Serializable{
    int pid;
    String prodName;
    String prodDescr;
    String measureUnit;
    String logoPath;
    String prodCatName;
    String prodCatDescr;
    String prodCatIconPath;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
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

    public String getProdCatIconPath() {
        return prodCatIconPath;
    }

    public void setProdCatIconPath(String prodCatIconPath) {
        this.prodCatIconPath = prodCatIconPath;
    }
    
    
}

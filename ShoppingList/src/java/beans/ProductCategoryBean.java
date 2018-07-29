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
public class ProductCategoryBean implements Serializable {

    private int pcid;
    private String prodCatName;
    private String prodCatDescr;
    private String prodCatIconPath;

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

    public String getProdCatIconPath() {
        return prodCatIconPath;
    }

    public void setProdCatIconPath(String prodCatIconPath) {
        this.prodCatIconPath = prodCatIconPath;
    }
}

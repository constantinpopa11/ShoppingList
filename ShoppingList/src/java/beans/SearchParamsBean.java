/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import constants.Utils;
import java.io.Serializable;

/**
 *
 * @author invidia
 */
public class SearchParamsBean implements Serializable {

    int lcid;
    int slid;
    int prodCat;
    int sortBy;
    int page;
    String key;

    public SearchParamsBean() {
        lcid = -1;
        slid = -1;
        prodCat = Utils.SEARCH_ANY_PROD_CAT;
        sortBy = Utils.SORT_PROD_BY_NAME;
        page = 1;
        key = "";
    }

    public int getLcid() {
        return lcid;
    }

    public void setLcid(int lcid) {
        this.lcid = lcid;
    }

    public int getProdCat() {
        return prodCat;
    }

    public void setProdCat(int prodCat) {
        this.prodCat = prodCat;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSlid() {
        return slid;
    }

    public void setSlid(int slid) {
        this.slid = slid;
    }
    
    
    
    
}

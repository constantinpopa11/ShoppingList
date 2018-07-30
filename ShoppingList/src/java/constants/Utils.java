package constants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author invidia
 */
public class Utils {

    public static final int REMEMBER_ME_MAX_INACTIVE_INTERVAL = 7 * 24 * 60 * 60; // 7 days
    public static final int NO_REMEMBER_ME_MAX_INACTIVE_INTERVAL = 6 * 60 * 60; // 6 hours
    public static final int ANONYMOUS_USER_MAX_INACTIVE_INTERVAL = 3 * 24 * 60 * 60; // 3 days

    public static final String UID_SESSION_ATTR = "user";
    public static final String PRIVILEGES_SESSION_ATTR = "privileges";
    public static final String FIRST_NAME_SESSION_ATTR = "firstName";

    //THIS NEEDS TO BE UPDATED
    public static final String WEBSITE_LOCATION = "http://localhost:8080/ShoppingListApp/";

    public static final String USER_AVATARS = "/images/userAvatars/";
    public static final String APP_IMAGES = "/images/app/";
    public static final String PRODUCT_LOGOS = "/images/products/";
    public static final String SHOP_CATEGORY_ICONS = "/images/shopCategories/";
    public static final String PROD_CATEGORY_ICONS = "/images/prodCategories/";
    public static final String SHOPPING_LIST_ICONS = "/images/shoppingLists/";
    public static final String SL_PHOTOS = "/images/slPhotos/";

    public static final String DEFAULT_USER_AVATAR = "default.png";
    public static final String DEFAULT_PRODUCT_LOGO = "default.png";
    public static final String DEFAULT_SHOP_CAT_ICON = "default.png";
    public static final String DEFAULT_PROD_CAT_ICON = "default.png";
    public static final String DEFAULT_SL_ICON = "default.png";

    public static final int SL_COMMENT = 0;
    public static final int SL_ADD = 1;
    public static final int SL_UPDATE = 2;
    public static final int SL_REMOVE = 3;

    public static final int SORT_PROD_BY_CATEGORY = 0;
    public static final int SORT_PROD_BY_NAME = 1;
}

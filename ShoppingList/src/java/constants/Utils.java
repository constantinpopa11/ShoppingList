package constants;

import java.security.MessageDigest;

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
    public static final String SL_PICTURES = "/images/slPictures/";

    public static final String DEFAULT_USER_AVATAR = "default.svg";
    public static final String DEFAULT_USER_AVATAR2 = "default2.svg";
    public static final String DEFAULT_USER_AVATAR3 = "default3.svg";
    public static final String DEFAULT_USER_AVATAR4 = "default4.svg";
    public static final String DEFAULT_USER_AVATAR5 = "default5.svg";
    public static final String DEFAULT_USER_AVATAR6 = "default6.svg";
    public static final String DEFAULT_USER_AVATAR7 = "default7.svg";
    public static final String DEFAULT_USER_AVATAR8 = "default8.svg";
    public static final String DEFAULT_PRODUCT_LOGO = "default.png";
    public static final String DEFAULT_SHOP_CAT_ICON = "default.png";
    public static final String DEFAULT_PROD_CAT_ICON = "default.png";
    public static final String DEFAULT_SL_ICON = "default.png";

    public static final int SL_COMMENT_TYPE = 0;
    public static final int SL_ADD_TYPE = 1;
    public static final int SL_EDIT_TYPE = 2;
    public static final int SL_REMOVE_TYPE = 3;

    public static final int SORT_PROD_BY_CATEGORY = 0;
    public static final int SORT_PROD_BY_NAME = 1;
    public static final int SEARCH_RESULTS_NUMBER = 10;
    public static final int SEARCH_ANY_PROD_CAT = 0;
    public static final int CREATED_BY_ADMIN = -1;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int SYSTEM_UID = 0;
    public static final String NO_SHOP_CAT_DESCRIPTION = "There's no description for this shop category";
    public static final String NO_PROD_CAT_DESCRIPTION = "There's no description for this product category";
    public static final String NO_PROD_DESCRIPTION = "There's no description for this product";
    public static final String NO_SL_DESCRIPTION = "There's no description for this shopping list";
    
    
    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

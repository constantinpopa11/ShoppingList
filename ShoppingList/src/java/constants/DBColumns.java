/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author invidia
 */
public class DBColumns {

    public static final String USERS_ID_COL = DBTables.USERS_TABLE + "." + "uid";
    public static final String USERS_EMAIL_COL = DBTables.USERS_TABLE + "." + "email";
    public static final String USERS_FIRST_NAME_COL = DBTables.USERS_TABLE + "." + "firstName";
    public static final String USERS_LAST_NAME_COL = DBTables.USERS_TABLE + "." + "lastName";
    public static final String USERS_AVATAR_PATH_COL = DBTables.USERS_TABLE + "." + "avatarPath";
    public static final String USERS_PASSWORD_COL = DBTables.USERS_TABLE + "." + "password";
    public static final String USERS_PRIVILEGES_COL = DBTables.USERS_TABLE + "." + "privileges";
    public static final String USERS_VERIFICATION_CODE_COL = DBTables.USERS_TABLE + "." + "verificationCode";

    public static final String SL_COMMENTS_UID_COL = DBTables.SL_COMMENTS_TABLE + "." + "uid";
    public static final String SL_COMMENTS_DATE_COL = DBTables.SL_COMMENTS_TABLE + "." + "date";
    public static final String SL_COMMENTS_MESSAGE_COL = DBTables.SL_COMMENTS_TABLE + "." + "message";
    public static final String SL_COMMENTS_SLID_COL = DBTables.SL_COMMENTS_TABLE + "." + "slid";
    public static final String SL_COMMENTS_TYPE_COL = DBTables.SL_COMMENTS_TABLE + "." + "type";

    public static final String SL_CAT_ID_COL = DBTables.SL_CATEGORIES_TABLE + "." + "lcid";
    public static final String SL_CAT_NAME = DBTables.SL_CATEGORIES_TABLE + "." + "name";
    public static final String SL_CAT_DESCR = DBTables.SL_CATEGORIES_TABLE + "." + "description";
    public static final String SL_CAT_ICON_PATH = DBTables.SL_CATEGORIES_TABLE + "." + "iconPath";

    public static final String PRODUCT_CAT_ID_COL = DBTables.PRODUCT_CAT_TABLE + "." + "pcid";
    public static final String PRODUCT_CAT_NAME_COL = DBTables.PRODUCT_CAT_TABLE + "." + "name";
    public static final String PRODUCT_CAT_DESCR_COL = DBTables.PRODUCT_CAT_TABLE + "." + "description";
    public static final String PRODUCT_CAT_ICON_PATH_COL = DBTables.PRODUCT_CAT_TABLE + "." + "iconPath";
    public static final String PRODUCT_CAT_LCID_COL = DBTables.PRODUCT_CAT_TABLE + "." + "lcid";

    public static final String PRODUCTS_ID_COL = DBTables.PRODUCTS_TABLE + "." + "pid";
    public static final String PRODUCTS_NAME_COL = DBTables.PRODUCTS_TABLE + "." + "name";
    public static final String PRODUCTS_DESCR_COL = DBTables.PRODUCTS_TABLE + "." + "description";
    public static final String PRODUCTS_MEASURE_UNIT_COL = DBTables.PRODUCTS_TABLE + "." + "measureUnit";
    public static final String PRODUCTS_LOGO_PATH_COL = DBTables.PRODUCTS_TABLE + "." + "logoPath";
    public static final String PRODUCTS_PCID_COL = DBTables.PRODUCTS_TABLE + "." + "pcid";
    public static final String PRODUCTS_CREATED_BY_COL = DBTables.PRODUCTS_TABLE + "." + "createdBy";

    public static final String SL_PICTURES_PATH_COL = DBTables.SL_PICTURES_TABLE + "." + "path";
    public static final String SL_PICTURES_UID_COL = DBTables.SL_PICTURES_TABLE + "." + "uid";
    public static final String SL_PICTURES_SLID_COL = DBTables.SL_PICTURES_TABLE + "." + "slid";
    public static final String SL_PICTURES_DATE_COL = DBTables.SL_PICTURES_TABLE + "." + "date";

    public static final String SL_CARTS_SLID_COL = DBTables.SL_CARTS_TABLE + "." + "slid";
    public static final String SL_CARTS_PID_COL = DBTables.SL_CARTS_TABLE + "." + "pid";
    public static final String SL_CARTS_QUANTITY_COL = DBTables.SL_CARTS_TABLE + "." + "quantity";

    public static final String SL_MEMBERS_SLID_COL = DBTables.SL_MEMBERS_TABLE + "." + "slid";
    public static final String SL_MEMBERS_UID_COL = DBTables.SL_MEMBERS_TABLE + "." + "uid";

    public static final String SHOPPING_LIST_ID_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "slid";
    public static final String SHOPPING_LIST_NAME_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "name";
    public static final String SHOPPING_LIST_DESCR_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "description";
    public static final String SHOPPING_LIST_ICON_PATH_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "iconPath";
    public static final String SHOPPING_LIST_LCID_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "lcid";
    public static final String SHOPPING_LIST_IS_EDITABLE_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "editable";
    public static final String SHOPPING_LIST_IS_REMOVABLE_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "removable";
    public static final String SHOPPING_LIST_OWNER_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "owner";
    public static final String SHOPPING_LIST_SHARE_LINK_COL = DBTables.SHOPPING_LISTS_TABLE + "." + "shareLink";
    
}

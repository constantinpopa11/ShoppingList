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
    
    public static final String DEFAULT_AVATAR_PATH = "../profilePictures/default_avatar.jpg";
    public static String DEFAULT_PROD_LOGO_PATH = "../productPictures/default_prod.jpg";
    
    public static final int SL_COMMENT = 0;
    public static final int SL_ADD = 1;
    public static final int SL_UPDATE = 2;
    public static final int SL_REMOVE = 3;
    
}

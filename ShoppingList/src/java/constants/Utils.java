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
    public static final String USER_COOKIE = "user";
    public static final int STANDARD_USER_PRIVILEGES = 1;
    public static final int ADMIN_PRIVILEGES = 0;
    public static final String DEFAULT_AVATAR_PATH = "../profilePictures/default_avatar.jpg";

    
}

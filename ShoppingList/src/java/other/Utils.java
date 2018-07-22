package other;

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
    public static final String SIGNUP_NAME_FIELD = "firstName";
    public static final String SIGNUP_SURNAME_FIELD = "lastName";
    public static final String SIGNUP_EMAIL_FIELD = "email";
    public static final String SIGNUP_EMAIL_CONFIRM_FIELD = "confirmEmail";
    public static final String SIGNUP_PASSWORD_FIELD = "password";
    public static final String SIGNUP_PASSWORD_CONFIRM_FIELD = "confirmPassword";
    
    public static final String LOGIN_EMAIL_FIELD = "email";
    public static final String LOGIN_PASSWORD_FIELD = "password";
    public static final String LOGIN_REMEMBER_ME_FLAG = "rememberMe";
    
    public static final int REMEMBER_ME_MAX_INACTIVE_INTERVAL = 7 * 24 * 60 * 60; // 7 days
    public static final int NO_REMEMBER_ME_MAX_INACTIVE_INTERVAL = 6 * 60 * 60; // 6 hours
    public static final int ANONYMOUS_USER_MAX_INACTIVE_INTERVAL = 3 * 24 * 60 * 60; // 3 days
    public static final String USER_COOKIE = "user";
    
}

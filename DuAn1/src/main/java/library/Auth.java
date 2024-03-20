/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

import model.Access;
import model.User;

/**
 *
 * @author TAN LOC
 */
public class Auth {

    public static User user = null;
    public static Access access = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static Boolean isReader() {
        if (!Auth.isLogin()) {
            return null;
        }

        return user.getReader();
    }

}

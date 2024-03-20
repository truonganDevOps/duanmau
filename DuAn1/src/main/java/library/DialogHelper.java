/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package library;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DialogHelper {
    
    // thông báo cho người dùng với message
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "Nerdyers", JOptionPane.INFORMATION_MESSAGE);
    }
    // yes or no selection với message cho sẵn
    public static boolean confirm(Component parent, String message) {
        int choice = JOptionPane.showConfirmDialog(parent, message,
                "Nerdyers", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        return choice == JOptionPane.YES_OPTION;
    }
    // return input người dùng dưới dạng String
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, 
                "Nerdyers", JOptionPane.INFORMATION_MESSAGE);
    }
    
}

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//package library;
//
//import java.awt.*;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.io.*;
//import java.nio.file.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import javax.swing.*;
//import model.NhanVien;
//
///**
// * 
// * @author Sammy Guergachi <sguergachi at gmail.com>
// */
//public class ShareHelper {
//    public static final Image app_icon;
//    static {
//        String path = "/image/fpt.png";
//        app_icon = new ImageIcon(ShareHelper.class.getResource(path)).getImage();
//    }
//    
//    public static boolean saveLogo(File copied) {
//        File dir = new File("logos");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        
//        File newFile = new File(dir, copied.getName());
//        try {
//            Path source = Paths.get(copied.getAbsolutePath());
//            Path destination =  Paths.get(newFile.getAbsolutePath());
//            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//    }
//    
//    // get imageicon by name
//    public static ImageIcon readLogo(String fileName) {
//        File path= new File("logos", fileName);
//        return new ImageIcon(path.getAbsolutePath());
//    }
//    
//    public static NhanVien user = null;
//    
//    public static void logOut() {
//        ShareHelper.user = null;
//    }
//    
//    public static boolean authenticated() {
//        return ShareHelper.user != null;
//    }
//    
//    public static void setPlaceholder(JTextField textField, String placeholder) {
//        textField.setText(placeholder);
//        textField.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (textField.getText().equals(placeholder)) {
//                    textField.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (textField.getText().isEmpty()) {
//                    textField.setText(placeholder);
//                }
//            }
//        });
//    }
//    
//    public static boolean areEmpty(JTextField... args) {
//        for (JTextField txt : args) {
//            if (txt.getText().length() == 0) {
//                DialogHelper.alert(txt, "Vui lòng nhập đủ các trường");
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    public static boolean isEmail(JTextField txt) {
//        String email = txt.getText();
//        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
//        
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        if (!matcher.matches()) DialogHelper.alert(txt, "Vui lòng nhập đúng syntax Email!");
//        
//        return matcher.matches();
//    }
//    
//}

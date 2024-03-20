/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Extension {

//    Class<Object> class = 
    // Chỉnh kích thước ảnh vừa với widthxheight của Jlabel
    public static void scaleImage(String path, JLabel anh) {
        ImageIcon icon = new ImageIcon(Extension.class.getResource(path));
        //scale image
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(anh.getWidth(), anh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        anh.setIcon(scaledIcon);
    }
    public static void scaleImage(JLabel anh, String fileName) {
        ImageIcon icon = XImage.getImageIcon(fileName);
        //scale image
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(anh.getWidth(), anh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        anh.setIcon(scaledIcon);
    }
    

    // Tạo placeholder cho textField
    public static void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // nếu textField rỗng -> setplaceholder
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
    }

    // thêm sự kiện hiển thị Password cho passwordField
    public static void togglePassword(JPasswordField passwordField) {
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                passwordField.setEchoChar('*');
            }
        });
    }

    // return true nếu có 1 or n JtextField rỗng
    public static boolean areEmpty(JTextField... args) {
        for (JTextField txt : args) {
            if (txt.getText().length() == 0) {
                DialogHelper.alert(txt, "Vui lòng nhập đủ các trường");
                return true;
            }
        }
        return false;
    }

    // return true nếu textfield chứa nội dung là email
    public static boolean isEmail(JTextField txt) {
        String email = txt.getText();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            DialogHelper.alert(txt, "Vui lòng nhập đúng syntax Email!");
        }

        return matcher.matches();
    }

    // return một randomString với độ dài là length và có kiểu là randomString = "init" + randomPart;
    public static String randomString(String init, int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(init);

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static void setUnderline(JButton... args) {
        for (JButton btn : args) {
            btn.setBorder(null);
            String text = "<html><u style=\"color: blue;\">" + btn.getText() + "</u></html>";
            btn.setText(text);
        }
    }

    // show book cover thông qua url link ảnh
    public static void setBookCover(String imageUrl, JLabel lbl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);

            if (image != null) {
//                ImageIcon icon = new ImageIcon(image);
                Image scaledImage = image.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                lbl.setIcon(scaledIcon);
            } else {
                System.err.println("Không thể tải hình ảnh từ URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tải hình ảnh từ URL.");
        }
    }

//    public static String randomOTP(int length) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        StringBuilder randomString = new StringBuilder("user_");
//
//        Random random = new Random();
//        for (int i = 0; i < length; i++) {
//            int index = random.nextInt(characters.length());
//            randomString.append(characters.charAt(index));
//        }
//
//        return randomString.toString();
//    }
}

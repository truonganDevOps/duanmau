/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

//import UX_UI.JFrameTrangChuKhachHang;
import UX_UI.JFrameTrangChuKhachHangver2;
import UX_UI.JFrameTrangChuQuanLy;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author TAN LOC
 */
public class XImage {

    public static String nerdyersLogo = "/images/logo_github.png";

    // Logo là button và có thể return ra trang chủ tùy vào role của user
    public static void setLogoButton(JButton btn) {
        // set none border
        btn.setBorder(null);
        // set dài rộng cho btn
        btn.setSize(135, 50);
        //set font
        Font segoeUIFont = new Font("Segoe UI Black", Font.PLAIN, 24);
        btn.setFont(segoeUIFont);

        ImageIcon icon = new ImageIcon(Extension.class.getResource(nerdyersLogo));
        //scale image
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(btn.getHeight() - 10, btn.getHeight() - 10, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btn.setIcon(scaledIcon);
        btn.setText("Nerdyers");
        if (Auth.isLogin()) {
            returnToMain(btn);
        }
    }
    
    public static void setInfoBtn(JButton btn, String name, String avatar) {
        ImageIcon icon = new ImageIcon(Extension.class.getResource(avatar));
        //scale image
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(btn.getHeight(), btn.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btn.setIcon(scaledIcon);   
        btn.setText(name);     
    }

    // set button event
    public static void returnToMain(JButton btn) {
        if (!Auth.isLogin()) {
            return;
        }
        // lấy form chứa button logo
        Window parentWindow = SwingUtilities.getWindowAncestor(btn);

        if (parentWindow == null) {
            return;
        }
        if (Auth.isReader()) {
            btn.addActionListener(e -> {
                parentWindow.dispose();
                new JFrameTrangChuKhachHangver2().setVisible(true);
            });
        } else {
            btn.addActionListener(e -> {
                parentWindow.dispose();
                new JFrameTrangChuQuanLy().setVisible(true);
            });
        }
    }

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/images/logo_github.png");
        return new ImageIcon(url).getImage();
    }
//src\\main\\resources\\com\\edusys\\

    public static boolean save(File src) {
        File dst = new File("logos", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();//tao thu muc
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ImageIcon read(String filename) {
        File path = new File("logos", filename);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static ImageIcon getImageIcon(String filename) {
        File path = new File("images", filename);
        return new ImageIcon(path.getAbsolutePath());
    }

}

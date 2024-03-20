/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Time;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class testAllDAO {
    
    public static void main(String[] args) {
        SachDAO dao = new SachDAO();
        
        ArrayList<Sach> sachs = dao.select();
        
        for (Sach sach : sachs) {
            System.out.println(sach.toString());;
        }
    }

    //userdao
//    public static void main(String[] args) {
//        UserDAO dao = new UserDAO();
////        
////        User user = new User("user_abc0", "khoa0", "abc", "vakhoa4875abc@gmail.com", true, false);
////        
////        dao.insert(user);dao.search("khoa").isEmpty() ?  : dao.search("khoa")
//        
//        ArrayList<User> userList = dao.select();
//        
//        for (User u : userList) {
//            System.out.println(u.toString());
//        }
//        
//    }
    //sachdao
//    public static void main(String[] args) {
//        SachDAO dao = new SachDAO();
//
//        ArrayList<Sach> list = dao.search("book");
//
//        for (Sach sach : list) {
//            System.out.println(sach.toString());
//        }
////        -- Sample Java code to create Sach objects
//        Sach sach1 = new Sach("11", "Book 110", true, 50, 4.5, "Description 1", "vn", "thumb_1.jpg", "avatar_1.jpg", "true_avatar_1.jpg", true, "book1.pdf", 200, false, "sound1.mp3", Time.valueOf("01:30:00"), 2000, "1.0", 2022, 18, 20, 100);
//        Sach sach2 = new Sach("21", "Book 22", true, 30, 4.2, "Description 2", "vn", "thumb_2.jpg", "avatar_2.jpg", "true_avatar_2.jpg", true, "book2.pdf", 150, true, "sound2.mp3", Time.valueOf("02:15:00"), 2010, "2.0", 2023, 16, 15, 80);
//
//        dao.update(sach2);
//        dao.update(sach1);
//        ArrayList<Sach> list2 = dao.select();
//
//        for (Sach sach : list2) {
//            System.out.println(sach.toString());
//        }
////        
////        Integer i = 10;
////        for (Sach sach : list2) {
////            i++;
////            Sach s2 = new Sach(
////                    sach.getIdSach(), sach.getTenSach().concat(i.toString()), sach.getGiaNiemYet(),
////                    sach.getThumbnail(), sach.getAvatar(), sach.getTrueSizeAvatar(), 
////                    sach.getMoTa(), sach.getDanhGiaTB(), sach.getMinAge(), 
////                    sach.getPdfAvai(), sach.getAudioAvai(), sach.getVersion(), 
////                    sach.getSoTrang(),  sach.getNgonNgu(), sach.getSourcePDF(),
////                    sach.getSourceSound(), sach.getFree(), sach.getLikeCount(), 
////                    sach.getViewCount(), sach.getThoiLuong());
////            
////            dao.update(s2);
////        }
////        
////        list2 = dao.select();
////        for (Sach sach : list2) {
////            System.out.println(sach.getTenSach());
////        }
//    }

}

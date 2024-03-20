/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class Reader {

//    private String idReader;
    private String idReader;
    private Boolean thanThiet;
    private Integer tichDiem;
    private Date ngaySinh;
    private String avatar;
    private String hoTen;
    private Boolean gioitinh;

    public Reader(String idReader, Boolean thanThiet, Integer tichDiem, Date ngaySinh, String avatar, String hoTen, Boolean gioitinh) {
        this.idReader = idReader;
        this.thanThiet = thanThiet;
        this.tichDiem = tichDiem;
        this.ngaySinh = ngaySinh;
        this.avatar = avatar;
        this.hoTen = hoTen;
        this.gioitinh = gioitinh;
    }

    
 
    public Boolean getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Boolean gioitinh) {
        this.gioitinh = gioitinh;
    }


    public Reader() {
        
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }

    public Boolean getThanThiet() {
        return thanThiet;
    }

    public void setThanThiet(Boolean thanThiet) {
        this.thanThiet = thanThiet;
    }

    public Integer getTichDiem() {
        return tichDiem;
    }

    public void setTichDiem(Integer tichDiem) {
        this.tichDiem = tichDiem;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

}

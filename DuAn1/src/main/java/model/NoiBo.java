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
public class NoiBo {

    private Integer idNoiBo;
    private String userID;
    private Integer idPB;
    private Integer idQuanLy;
    private Integer luong;
    private Boolean fulltime;
    private Date ngayThue;
    private String caLam;
    private Boolean quanLy;
    private Integer luongBong;
    private Date ngaySinh;
    private String avatar;
    private String hoTen;
    private Boolean gioiTinh;

//    public NhanVien(User user) {
//        
    public NoiBo() {
    }

    public NoiBo(Integer idNoiBo, String userID, Integer idPB, Integer idQuanLy, Integer luong, Boolean fulltime, Date ngayThue, String caLam, Boolean quanLy, Integer luongBong, Date ngaySinh, String avatar, String hoTen, Boolean gioiTinh) {
        this.idNoiBo = idNoiBo;
        this.userID = userID;
        this.idPB = idPB;
        this.idQuanLy = idQuanLy;
        this.luong = luong;
        this.fulltime = fulltime;
        this.ngayThue = ngayThue;
        this.caLam = caLam;
        this.quanLy = quanLy;
        this.luongBong = luongBong;
        this.ngaySinh = ngaySinh;
        this.avatar = avatar;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
    }

    public Integer getIdNoiBo() {
        return idNoiBo;
    }

    public void setIdNoiBo(Integer idNoiBo) {
        this.idNoiBo = idNoiBo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getIdPB() {
        return idPB;
    }

    public void setIdPB(Integer idPB) {
        this.idPB = idPB;
    }

    public Integer getIdQuanLy() {
        return idQuanLy;
    }

    public void setIdQuanLy(Integer idQuanLy) {
        this.idQuanLy = idQuanLy;
    }

    public Integer getLuong() {
        return luong;
    }

    public void setLuong(Integer luong) {
        this.luong = luong;
    }

    public Boolean getFulltime() {
        return fulltime;
    }

    public void setFulltime(Boolean fulltime) {
        this.fulltime = fulltime;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
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

    public String getCaLam() {
        return caLam;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }

    public Boolean getQuanLy() {
        return quanLy;
    }

    public void setQuanLy(Boolean quanLy) {
        this.quanLy = quanLy;
    }

    public Integer getLuongBong() {
        return luongBong;
    }

    public void setLuongBong(Integer luongBong) {
        this.luongBong = luongBong;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class Access {

    private Integer idAccess;
    private String moTa;
    private Boolean fullAccess;
    private Boolean rReadlist, uReadlist;
    private Boolean rWishlist, uWishlist;
//    private Boolean rGioHang, uGioHang;
//    private Boolean rDonHang, uDonHang;
//    private Boolean rKhuyenMai, uKhuyenMai;
    private Boolean rUser, uUser;
    private Boolean rPhongBan, uPhongBan;
    private Boolean rSach, uSach;
    private Boolean rReader, uReader;
    private Boolean rNoiBo, uNoiBo;
    private Boolean rTacGia, uTacGia;
    private Boolean rTheLoai, uTheLoai;

    public Access() {
    }

    public Access(Integer idAccess, String moTa, Boolean fullAccess, Boolean rReadlist, Boolean uReadlist, Boolean rWishlist, Boolean uWishlist, Boolean rUser, Boolean uUser, Boolean rPhongBan, Boolean uPhongBan, Boolean rSach, Boolean uSach, Boolean rReader, Boolean uReader, Boolean rNoiBo, Boolean uNoiBo, Boolean rTacGia, Boolean uTacGia, Boolean rTheLoai, Boolean uTheLoai) {
        this.idAccess = idAccess;
        this.moTa = moTa;
        this.fullAccess = fullAccess;
        this.rReadlist = rReadlist;
        this.uReadlist = uReadlist;
        this.rWishlist = rWishlist;
        this.uWishlist = uWishlist;
//        this.rGioHang = rGioHang;
//        this.uGioHang = uGioHang;
//        this.rDonHang = rDonHang;
//        this.uDonHang = uDonHang;
//        this.rKhuyenMai = rKhuyenMai;
//        this.uKhuyenMai = uKhuyenMai;
        this.rUser = rUser;
        this.uUser = uUser;
        this.rPhongBan = rPhongBan;
        this.uPhongBan = uPhongBan;
        this.rSach = rSach;
        this.uSach = uSach;
        this.rReader = rReader;
        this.uReader = uReader;
        this.rNoiBo = rNoiBo;
        this.uNoiBo = uNoiBo;
        this.rTacGia = rTacGia;
        this.uTacGia = uTacGia;
        this.rTheLoai = rTheLoai;
        this.uTheLoai = uTheLoai;
    }

    public Integer getIdAccess() {
        return idAccess;
    }

    public void setIdAccess(Integer idAccess) {
        this.idAccess = idAccess;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean getFullAccess() {
        return fullAccess;
    }

    public void setFullAccess(Boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    public Boolean getrReadlist() {
        return rReadlist;
    }

    public void setrReadlist(Boolean rReadlist) {
        this.rReadlist = rReadlist;
    }

    public Boolean getuReadlist() {
        return uReadlist;
    }

    public void setuReadlist(Boolean uReadlist) {
        this.uReadlist = uReadlist;
    }

    public Boolean getrWishlist() {
        return rWishlist;
    }

    public void setrWishlist(Boolean rWishlist) {
        this.rWishlist = rWishlist;
    }

    public Boolean getuWishlist() {
        return uWishlist;
    }

    public void setuWishlist(Boolean uWishlist) {
        this.uWishlist = uWishlist;
    }

    public Boolean getrUser() {
        return rUser;
    }

    public void setrUser(Boolean rUser) {
        this.rUser = rUser;
    }

    public Boolean getuUser() {
        return uUser;
    }

    public void setuUser(Boolean uUser) {
        this.uUser = uUser;
    }

    public Boolean getrPhongBan() {
        return rPhongBan;
    }

    public void setrPhongBan(Boolean rPhongBan) {
        this.rPhongBan = rPhongBan;
    }

    public Boolean getuPhongBan() {
        return uPhongBan;
    }

    public void setuPhongBan(Boolean uPhongBan) {
        this.uPhongBan = uPhongBan;
    }

    public Boolean getrSach() {
        return rSach;
    }

    public void setrSach(Boolean rSach) {
        this.rSach = rSach;
    }

    public Boolean getuSach() {
        return uSach;
    }

    public void setuSach(Boolean uSach) {
        this.uSach = uSach;
    }

    public Boolean getrReader() {
        return rReader;
    }

    public void setrReader(Boolean rReader) {
        this.rReader = rReader;
    }

    public Boolean getuReader() {
        return uReader;
    }

    public void setuReader(Boolean uReader) {
        this.uReader = uReader;
    }

    public Boolean getrNoiBo() {
        return rNoiBo;
    }

    public void setrNoiBo(Boolean rNoiBo) {
        this.rNoiBo = rNoiBo;
    }

    public Boolean getuNoiBo() {
        return uNoiBo;
    }

    public void setuNoiBo(Boolean uNoiBo) {
        this.uNoiBo = uNoiBo;
    }

    public Boolean getrTacGia() {
        return rTacGia;
    }

    public void setrTacGia(Boolean rTacGia) {
        this.rTacGia = rTacGia;
    }

    public Boolean getuTacGia() {
        return uTacGia;
    }

    public void setuTacGia(Boolean uTacGia) {
        this.uTacGia = uTacGia;
    }

    public Boolean getrTheLoai() {
        return rTheLoai;
    }

    public void setrTheLoai(Boolean rTheLoai) {
        this.rTheLoai = rTheLoai;
    }

    public Boolean getuTheLoai() {
        return uTheLoai;
    }

    public void setuTheLoai(Boolean uTheLoai) {
        this.uTheLoai = uTheLoai;
    }
    
    
}

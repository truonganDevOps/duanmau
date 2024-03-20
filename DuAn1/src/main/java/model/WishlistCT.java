/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class WishlistCT {
    private String idWishlist;
    private String idSach;
    private Date addedTime = new Date();

    public WishlistCT() {
    }

    public WishlistCT(String idWishlist, String idSach, Date addedTime) {
        this.idWishlist = idWishlist;
        this.idSach = idSach;
        this.addedTime = addedTime;
    }

    public String getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(String idWishlist) {
        this.idWishlist = idWishlist;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }    
    
}

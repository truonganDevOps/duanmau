/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 * 
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class Wishlist {
    private Integer idWishlist, totalCount;

    public Wishlist() {
    }

    public Wishlist(Integer idWishlist, Integer totalCount) {
        this.idWishlist = idWishlist;
        this.totalCount = totalCount;
    }

    public Integer getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(Integer idWishlist) {
        this.idWishlist = idWishlist;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
}

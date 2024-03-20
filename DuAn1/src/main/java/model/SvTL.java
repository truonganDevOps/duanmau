/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class SvTL {

    private String idSach;
    private Integer idTheLoai;

    public SvTL() {
    }

    public SvTL(String idSach, Integer idTheLoai) {
        this.idSach = idSach;
        this.idTheLoai = idTheLoai;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public Integer getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Integer idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

}

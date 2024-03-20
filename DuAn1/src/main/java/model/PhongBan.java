/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class PhongBan {

    private int idPB;
    private int qlAccess;
    private int nvAccess;
    private String tenPB;
    private String moTa;

    public PhongBan() {
    }

    
    
    public PhongBan(int idPB, int qlAccess, int nvAccess, String tenPB, String moTa) {
        this.idPB = idPB;
        this.qlAccess = qlAccess;
        this.nvAccess = nvAccess;
        this.tenPB = tenPB;
        this.moTa = moTa;
    }

    public int getIdPB() {
        return idPB;
    }

    public void setIdPB(int idPB) {
        this.idPB = idPB;
    }

    public int getQlAccess() {
        return qlAccess;
    }

    public void setQlAccess(int qlAccess) {
        this.qlAccess = qlAccess;
    }

    public int getNvAccess() {
        return nvAccess;
    }

    public void setNvAccess(int nvAccess) {
        this.nvAccess = nvAccess;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "PhongBan{" + "idPB=" + idPB + ", qlAccess=" + qlAccess + ", nvAccess=" + nvAccess + ", tenPB=" + tenPB + ", moTa=" + moTa + '}';
    }

  
}


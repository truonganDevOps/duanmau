/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 * 
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class SvTG {
    private String idSach;
    private int idTacGia;

    public SvTG() {
    }

    public SvTG(String idSach, int idTacGia) {
        this.idSach = idSach;
        this.idTacGia = idTacGia;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public int getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(int idTacGia) {
        this.idTacGia = idTacGia;
    }
    
}

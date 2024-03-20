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
public class SachPDF {
    private String idReadlist;
    private String idSach;
    private Integer lastSeenPage;
    private Date addedTime = new Date();

    public SachPDF() {
    }

    public SachPDF(String idReadlist, String idSach, Integer lastSeenPage, Date addedTime) {
        this.idReadlist = idReadlist;
        this.idSach = idSach;
        this.lastSeenPage = lastSeenPage;
        this.addedTime = addedTime;
    }
    public SachPDF(String idReadlist, String idSach, Integer lastSeenPage) {
        this.idReadlist = idReadlist;
        this.idSach = idSach;
        this.lastSeenPage = lastSeenPage;
    }

    public String getIdReadlist() {
        return idReadlist;
    }

    public void setIdReadlist(String idReadlist) {
        this.idReadlist = idReadlist;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public Integer getLastSeenPage() {
        return lastSeenPage;
    }

    public void setLastSeenPage(Integer lastSeenPage) {
        this.lastSeenPage = lastSeenPage;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }
    
    
}

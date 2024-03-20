/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 * 
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class Readlist {
    private Integer idReadlist;
    private String alternativeName;
    private Integer totalPDFCount, totalAudioCount;

    public Readlist() {
    }

    public Readlist(Integer idReadlist, String alternativeName, Integer totalPDFCount, Integer totalAudioCount) {
        this.idReadlist = idReadlist;
        this.alternativeName = alternativeName;
        this.totalPDFCount = totalPDFCount;
        this.totalAudioCount = totalAudioCount;
    }

    public Integer getIdReadlist() {
        return idReadlist;
    }

    public void setIdReadlist(Integer idReadlist) {
        this.idReadlist = idReadlist;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public Integer getTotalPDFCount() {
        return totalPDFCount;
    }

    public void setTotalPDFCount(Integer totalPDFCount) {
        this.totalPDFCount = totalPDFCount;
    }

    public Integer getTotalAudioCount() {
        return totalAudioCount;
    }

    public void setTotalAudioCount(Integer totalAudioCount) {
        this.totalAudioCount = totalAudioCount;
    }
    
}

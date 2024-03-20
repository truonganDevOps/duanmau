/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 * 
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class Comment {
    private String idDanhGia;
    private String idReader;
    private String idSach;
    private Integer sao;
    private String content;
    private String images, videos;
    private Boolean editable, enable;

    public Comment() {
    }

    public Comment(String idDanhGia, String idReader, String idSach, Integer sao, String content, String images, String videos, Boolean editable, Boolean enable) {
        this.idDanhGia = idDanhGia;
        this.idReader = idReader;
        this.idSach = idSach;
        this.sao = sao;
        this.content = content;
        this.images = images;
        this.videos = videos;
        this.editable = editable;
        this.enable = enable;
    }

    public String getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(String idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public Integer getSao() {
        return sao;
    }

    public void setSao(Integer sao) {
        this.sao = sao;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
    
    
}

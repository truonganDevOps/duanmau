package model;


import java.util.Arrays;

public class Sach {

    private String idSach;
    private String tenSach;
    private int namXB;
    private int namSangTac;
    private int soTrang;
    private String ebookAccess;
    private boolean hasFulltext;
    private boolean publicScanB;
    private String urlLink;
    private String coverI;
    private String moTa;
    private String[] ngonNgu;
//    private String[] idTacGia;
//    private String[] tenTG;
//    private String[] idTheLoai;
//    private String[] tenTL;
    private String phienBan;
    private int viewCount;
    private int likeCount;
    private Double danhGiaTB;

    // Constructors, getters, setters, and other methods...

    // Constructor
    public Sach(String idSach, String tenSach, int namXB, int namSangTac, int soTrang,
                String ebookAccess, boolean hasFulltext, boolean publicScanB, String urlLink,
                String coverI, String moTa, String[] ngonNgu, 
//                String[] idTacGia, String[] tenTG, String[] idTheLoai, String[] tenTL,
                String phienBan, int viewCount, int likeCount, Double danhGiaTB) {
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.namXB = namXB;
        this.namSangTac = namSangTac;
        this.soTrang = soTrang;
        this.ebookAccess = ebookAccess;
        this.hasFulltext = hasFulltext;
        this.publicScanB = publicScanB;
        this.urlLink = urlLink;
        this.coverI = coverI;
        this.moTa = moTa;
        this.ngonNgu = ngonNgu;
//        this.idTacGia = idTacGia;
//        this.tenTG = tenTG;
//        this.idTheLoai = idTheLoai;
//        this.tenTL = tenTL;
        this.phienBan = phienBan;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.danhGiaTB = danhGiaTB;
    }

    public Sach() {
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public int getNamSangTac() {
        return namSangTac;
    }

    public void setNamSangTac(int namSangTac) {
        this.namSangTac = namSangTac;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getEbookAccess() {
        return ebookAccess;
    }

    public void setEbookAccess(String ebookAccess) {
        this.ebookAccess = ebookAccess;
    }

    public boolean isHasFulltext() {
        return hasFulltext;
    }

    public void setHasFulltext(boolean hasFulltext) {
        this.hasFulltext = hasFulltext;
    }

    public boolean isPublicScanB() {
        return publicScanB;
    }

    public void setPublicScanB(boolean publicScanB) {
        this.publicScanB = publicScanB;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getCoverI() {
        return coverI;
    }

    public void setCoverI(String coverI) {
        this.coverI = coverI;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String[] getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String[] ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

//    public String[] getIdTacGia() {
//        return idTacGia;
//    }
//
//    public void setIdTacGia(String[] idTacGia) {
//        this.idTacGia = idTacGia;
//    }
//
//    public String[] getTenTG() {
//        return tenTG;
//    }
//
//    public void setTenTG(String[] tenTG) {
//        this.tenTG = tenTG;
//    }
//
//    public String[] getIdTheLoai() {
//        return idTheLoai;
//    }
//
//    public void setIdTheLoai(String[] idTheLoai) {
//        this.idTheLoai = idTheLoai;
//    }
//
//    public String[] getTenTL() {
//        return tenTL;
//    }
//
//    public void setTenTL(String[] tenTL) {
//        this.tenTL = tenTL;
//    }

    public String getPhienBan() {
        return phienBan;
    }

    public void setPhienBan(String phienBan) {
        this.phienBan = phienBan;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Double getDanhGiaTB() {
        return danhGiaTB;
    }

    public void setDanhGiaTB(Double danhGiaTB) {
        this.danhGiaTB = danhGiaTB;
    }

    // Getters and setters...

    @Override
    public String toString() {
        return "Book{" +
                "idSach='" + idSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", namXB=" + namXB +
                ", namSangTac=" + namSangTac +
                ", soTrang=" + soTrang +
                ", ebookAccess='" + ebookAccess + '\'' +
                ", hasFulltext=" + hasFulltext +
                ", publicScanB=" + publicScanB +
                ", urlLink='" + urlLink + '\'' +
                ", coverI=" + coverI +
                ", moTa='" + moTa + '\'' +
                ", ngonNgu=" + Arrays.toString(ngonNgu) +
//                ", idTacGia=" + Arrays.toString(idTacGia) +
//                ", tenTG=" + Arrays.toString(tenTG) +
//                ", idTheLoai=" + Arrays.toString(idTheLoai) +
//                ", tenTL=" + Arrays.toString(tenTL) +
                ", phienBan='" + phienBan + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", danhGiaTB=" + danhGiaTB +
                '}';
    }
}

package model;

/**
 *
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class TheLoai {

    private String idTheLoai;
    private String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(String idTheLoai, String tenTheLoai) {
        this.idTheLoai = idTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

}

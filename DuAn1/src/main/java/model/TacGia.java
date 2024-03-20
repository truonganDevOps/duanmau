
package model;


/**
 *
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class TacGia {

    private String idTacGia;
    private String tenTacGia;

    public TacGia(String idTacGia, String tenTacGia) {
        this.idTacGia = idTacGia;
        this.tenTacGia = tenTacGia;
    }

    public TacGia() {
    }

    public String getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(String idTacGia) {
        this.idTacGia = idTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.NoiBo;
import model.PhongBan;
import model.User;

/**
 *
 * @author Admin
 */
public class NoiBoDAO {

    public void insert(User user) {
        String insertQuery = "INSERT INTO [User] (UserID, UserName, [password], Email, Reader, Verificated) VALUES (?, ?, ?, ?, ?, ?)";
        Jdbc.executeUpdate(insertQuery,
                user.getUserID(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getReader(),
                user.getVerificated());
    }

    public void delete(NoiBo noiBo) {
        String deleteQuery = "DELETE FROM noibo WHERE idNoiBo = ?";
        Jdbc.executeUpdate(deleteQuery, noiBo.getIdNoiBo());
    }

    public void deleteByUserID(NoiBo noiBo) {
        String deleteQuery = "DELETE FROM noibo WHERE userid = ?";
        Jdbc.executeUpdate(deleteQuery, noiBo.getUserID());
    }

    public void update(NoiBo noiBo) {
        String updateQuery = "update noiBo set idPB = ?, idQuanLy = ?, luong = ?, fullTime = ?, ngayThue = ?, caLam = ?, quanLy = ?, luongbong = ?, ngaySinh = ?, avatar = ?, gioiTinh = ?  where userid = ?";
        Jdbc.executeUpdate(updateQuery,
                noiBo.getIdPB(),
                noiBo.getIdQuanLy(),
                noiBo.getLuong(),
                noiBo.getFulltime(),
                noiBo.getNgayThue(),
                noiBo.getCaLam(),
                noiBo.getQuanLy(),
                noiBo.getLuongBong(),
                noiBo.getNgaySinh(),
                noiBo.getAvatar(),
                noiBo.getGioiTinh(),
                noiBo.getUserID()
        );
    }

    public ArrayList<NoiBo> selectAll() {
        String query = "select * from noiBo";
        ArrayList<NoiBo> nb = select(query);
        return nb;
    }

    public NoiBo selectByID(int id) {
        String query = "select * from noibo where idnoibo = ?";
        ArrayList<NoiBo> nb = select(query, id);
        return !nb.isEmpty() ? nb.get(0) : null;
    }
    
    public NoiBo selectByUserID(String id) {
        String query = "select * from noibo where userID = ?";
        ArrayList<NoiBo> nb = select(query, id);  
        return !nb.isEmpty() ? nb.get(0) : null;      
    }

    public ArrayList<NoiBo> selectNguoiQuanLy() {
        String query = "select * from noibo where quanLy = 1";
        ArrayList<NoiBo> nb = select(query);
        return nb;

    }

    public ArrayList<NoiBo> search(String keyword) {
        String query = "select * from [user] u join noibo nb on u.userID = nb.userID where "
                + "idNoiBo like ? "
                + "or nb.userID like ? "
                + "or u.username like ? "
                + "or email like ? "
                + "or nb.hoten like ? "
                + "or gioitinh like ? "
                + "or idpb like ? "
                + "or ngaythue like ? "
                + "or calam like ? ";
        ArrayList<NoiBo> nb = select(query, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%",
                "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
        return nb;

    }

    private ArrayList<NoiBo> select(String sql, Object... args) {
        ArrayList<NoiBo> noiBoList = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    NoiBo nb = readFromRS(rs);
                    noiBoList.add(nb);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
        return noiBoList;
    }

    private NoiBo readFromRS(ResultSet rs) throws SQLException {
        NoiBo nb = new NoiBo(
                rs.getInt("idNoiBo"),
                rs.getString("userID"),
                rs.getInt("idpb"),
                rs.getInt("idQuanLy"),
                rs.getInt("luong"),
                rs.getBoolean("fullTime"),
                rs.getDate("ngayThue"),
                rs.getString("caLam"),
                rs.getBoolean("quanLy"),
                rs.getInt("luongBong"),
                rs.getDate("ngaySinh"),
                rs.getString("avatar"),
                rs.getString("hoTen"),
                rs.getBoolean("gioiTinh")
        );
        return nb;
    }
}

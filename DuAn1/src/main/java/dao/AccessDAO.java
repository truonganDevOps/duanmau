/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.Access;

/**
 *
 * @author TAN LOC
 */
public class AccessDAO {

    public void insert(Access ac) {
        String sql = "INSERT INTO [Access] (idAccess, moTa, fullAccess, rReadList, uReadList, rWishList, uWishList, rUser, uUser, rPhongBan, uPhongBan, rSach, uSach, rReader, uReader, rNoiBo, uNoiBo, rTacGia, uTacGia, rTheLoai, uTheLoai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        Jdbc.executeUpdate(sql,
                ac.getIdAccess(),
                ac.getMoTa(),
                ac.getFullAccess(),
                ac.getrReadlist(),
                ac.getuReadlist(),
                ac.getrWishlist(),
                ac.getuWishlist(),
                ac.getrUser(),
                ac.getuUser(),
                ac.getrPhongBan(),
                ac.getuPhongBan(),
                ac.getrSach(),
                ac.getuSach(),
                ac.getrReader(),
                ac.getuReader(),
                ac.getrNoiBo(),
                ac.getuNoiBo(),
                ac.getrTacGia(),
                ac.getuTacGia(),
                ac.getrTheLoai(),
                ac.getuTheLoai()
        );
    }

    public void update(Access ac) {
        String sql = "UPDATE [Access] SET moTa=?, fullAccess=?, rReadList=?, uReadList=?, rWishList=?, uWishList=?, rUser=?, uUser=?, rPhongBan=?, uPhongBan=?, rSach=?, uSach=?, rReader=?, uReader=?, rNoiBo=?, uNoiBo=?, rTacGia=?, uTacGia=?, rTheLoai=?, uTheLoai=? WHERE idAccess=?";

        Jdbc.executeUpdate(sql,
                ac.getMoTa(),
                ac.getFullAccess(),
                ac.getrReadlist(),
                ac.getuReadlist(),
                ac.getrWishlist(),
                ac.getuWishlist(),
                ac.getrUser(),
                ac.getuUser(),
                ac.getrPhongBan(),
                ac.getuPhongBan(),
                ac.getrSach(),
                ac.getuSach(),
                ac.getrReader(),
                ac.getuReader(),
                ac.getrNoiBo(),
                ac.getuNoiBo(),
                ac.getrTacGia(),
                ac.getuTacGia(),
                ac.getrTheLoai(),
                ac.getuTheLoai(),
                ac.getIdAccess()
        );
    }

    public void delete(int idaccess) {
        String sql = "Delete From [access] where idaccess = ?";
        Jdbc.executeUpdate(sql, idaccess);

    }

    public Access selectById(Integer idaccess) {
        String sql = "Select * from access where idaccess = ?";
        ArrayList<Access> ac = select(sql, idaccess);
        return !ac.isEmpty() ? ac.get(0) : null;
    }

    public ArrayList<Access> selectAll() {
        String sql = "Select * from Access";
        return select(sql);
    }

    ;
    
    
    public ArrayList<Access> selectByKeyWord(int keyword) {
        ArrayList<Access> list = new ArrayList<>();
        String sql = "select * from access where idAccess = ?";
        return list = select(sql,keyword);
    }

    private ArrayList<Access> select(String sql, Object... arys) {
        ArrayList<Access> listaccess = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, arys);
                while (rs.next()) {
                    Access ac = readFromRS(rs);
                    listaccess.add(ac);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaccess;
    }

    private Access readFromRS(ResultSet rs) throws SQLException {
        Access ac = new Access(
                rs.getInt("idAccess"),
                rs.getString("moTa"),
                rs.getBoolean("fullAccess"),
                rs.getBoolean("rReadList"),
                rs.getBoolean("uReadList"),
                rs.getBoolean("rWishList"),
                rs.getBoolean("uWishList"),
                rs.getBoolean("rUser"),
                rs.getBoolean("uUser"),
                rs.getBoolean("rPhongBan"),
                rs.getBoolean("uPhongBan"),
                rs.getBoolean("rSach"),
                rs.getBoolean("uSach"),
                rs.getBoolean("rReader"),
                rs.getBoolean("uReader"),
                rs.getBoolean("rNoiBo"),
                rs.getBoolean("uNoiBo"),
                rs.getBoolean("rTacGia"),
                rs.getBoolean("uTacGia"),
                rs.getBoolean("rTheLoai"),
                rs.getBoolean("uTheLoai")
        );
        return ac;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import library.Jdbc;
import model.PhongBan;
import model.User;

/**
 *
 * @author Admin
 */
public class PhongBanDAO {

    public void insert(PhongBan pb) {
        String insertQuery = "insert into phongBan values (?,?,?,?,?)";
        Jdbc.executeUpdate(insertQuery,
                pb.getIdPB(),
                pb.getQlAccess(),
                pb.getNvAccess(),
                pb.getTenPB(),
                pb.getMoTa()
        );
    }

    public void update(PhongBan pb) {
        String updateQuery = "update phongBan set qlAccess=?, nvAccess=?, tenPB=?, moTa=? where idPB = ?";
        Jdbc.executeUpdate(updateQuery,
                pb.getQlAccess(),
                pb.getNvAccess(),
                pb.getTenPB(),
                pb.getMoTa(),
                pb.getIdPB()
        );
    }

    public void delete(int idPhongBan) {
        String deleteQuery = "DELETE FROM phongBan WHERE idPB = ?";
        Jdbc.executeUpdate(deleteQuery, idPhongBan);
    }

    public PhongBan selectByID(int id) {
        String selectByID = "select * from phongban where idpb = ?";
        ArrayList<PhongBan> pb = select(selectByID, id);
        return !pb.isEmpty() ? pb.get(0) : null;
    }

    public PhongBan selectByName(String name) {
        String selectByID = "select * from phongban where tenpb = ?";
        ArrayList<PhongBan> pb = select(selectByID, name);
        return !pb.isEmpty() ? pb.get(0) : null;
    }

    public ArrayList<PhongBan> selectAll() {
        String selectAll = "select * from phongban";
        ArrayList<PhongBan> pb = select(selectAll);
        return pb;
    }

    private ArrayList<PhongBan> select(String sql, Object... args) {
        ArrayList<PhongBan> phongBanList = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    PhongBan pb = readFromRS(rs);
                    phongBanList.add(pb);
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
        return phongBanList;
    }

    private PhongBan readFromRS(ResultSet resultSet) throws SQLException {
        PhongBan pb = new PhongBan(
                resultSet.getInt("idPB"),
                resultSet.getInt("qlAccess"),
                resultSet.getInt("nvAccess"),
                resultSet.getString("tenPB"),
                resultSet.getString("moTa")
        );
        return pb;
    }
    
    public List<PhongBan> selectByKeyWord(String keyword){
        
        String sql = "select * from phongBan where idPB like ? or qlAccess like ? or nvAccess like ? or tenPB like ?";
        
        return select(sql, "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%");
    }
}

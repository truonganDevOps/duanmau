package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.TheLoai;

public class TheLoaiDAO {

    public void insert(TheLoai theLoai) {
        String insertQuery = "INSERT INTO TheLoai (idTheLoai, tenTheLoai) VALUES (?, ?)";
        Jdbc.executeUpdate(insertQuery, 
                theLoai.getIdTheLoai(), 
                theLoai.getTenTheLoai());
    }

    public void delete(TheLoai theLoai) {
        String deleteQuery = "DELETE FROM TheLoai WHERE idTheLoai = ?";
        Jdbc.executeUpdate(deleteQuery, theLoai.getIdTheLoai());
    }

    public void update(TheLoai theLoai) {
        String updateQuery = "UPDATE TheLoai SET tenTheLoai = ? WHERE idTheLoai = ?";
        Jdbc.executeUpdate(updateQuery, 
                theLoai.getTenTheLoai(), 
                theLoai.getIdTheLoai());
    }

    public ArrayList<TheLoai> search(String val) {
        String searchQuery = "SELECT * FROM TheLoai WHERE tenTheLoai LIKE ?";
        return select(searchQuery, "%" + val + "%");
    }

    public ArrayList<TheLoai> select() {
        String selectQuery = "SELECT * FROM TheLoai";
        return select(selectQuery);
    }
    
    public TheLoai selectByID(String id) {
        String selectQuery = "SELECT * FROM TheLoai WHERE idTheLoai = ?";
        ArrayList<TheLoai> theLoaiList = select(selectQuery, id);
        return !theLoaiList.isEmpty() ? theLoaiList.get(0) : null;
    }

    private ArrayList<TheLoai> select(String sql, Object... args) {
        ArrayList<TheLoai> theLoaiList = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    TheLoai theLoai = readFromRS(rs);
                    theLoaiList.add(theLoai);
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
        return theLoaiList;
    }

    private TheLoai readFromRS(ResultSet resultSet) throws SQLException {
        TheLoai theLoai = new TheLoai(
                resultSet.getString("idTheLoai"),
                resultSet.getString("tenTheLoai")
        );
        return theLoai;
    }
}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.TacGia;

public class TacGiaDAO {

    public void insert(TacGia tacGia) {
        String insertQuery = "INSERT INTO TacGia (idTacGia, tenTacGia) VALUES (?, ?)";
        Jdbc.executeUpdate(insertQuery, 
                tacGia.getIdTacGia(), 
                tacGia.getTenTacGia());
    }

    public void delete(TacGia tacGia) {
        String deleteQuery = "DELETE FROM TacGia WHERE idTacGia = ?";
        Jdbc.executeUpdate(deleteQuery, tacGia.getIdTacGia());
    }

    public void update(TacGia tacGia) {
        String updateQuery = "UPDATE TacGia SET tenTacGia = ? WHERE idTacGia = ?";
        Jdbc.executeUpdate(updateQuery, 
                tacGia.getTenTacGia(), 
                tacGia.getIdTacGia());
    }

    public ArrayList<TacGia> search(String val) {
        String searchQuery = "SELECT * FROM TacGia WHERE tenTacGia LIKE ?";
        return select(searchQuery, "%" + val + "%");
    }

    public ArrayList<TacGia> select() {
        String selectQuery = "SELECT * FROM TacGia";
        return select(selectQuery);
    }
    
    public TacGia selectByID(String id) {
        String selectQuery = "SELECT * FROM TacGia WHERE idTacGia = ?";
        ArrayList<TacGia> tacGias = select(selectQuery, id);
        return !tacGias.isEmpty() ? tacGias.get(0) : null;
    }
    public ArrayList<TacGia> selectByIDSach(String id) {
        String selectQuery = "SELECT * FROM TacGia WHERE idTacGia in (SELECT IDTACGIA from SVTG where IDSACH = ?)";
        return select(selectQuery, id);
    }

    private ArrayList<TacGia> select(String sql, Object... args) {
        ArrayList<TacGia> tacGiaList = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    TacGia tacGia = readFromRS(rs);
                    tacGiaList.add(tacGia);
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
        return tacGiaList;
    }

    private TacGia readFromRS(ResultSet resultSet) throws SQLException {
        TacGia tacGia = new TacGia(
                resultSet.getString("idTacGia"),
                resultSet.getString("tenTacGia")
        );
        return tacGia;
    }
}

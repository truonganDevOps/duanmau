/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.TacGia;
import model.WishlistCT;

/**
 *
 * @author phamhuy
 */
public class WishlistDAO {

    public void insert(WishlistCT wishlist) {
        String insertQuery = "INSERT INTO WishlistCT (idWishlist, idSach, addedTime) VALUES (?, ?, ?)";
        Jdbc.executeUpdate(insertQuery,
                wishlist.getIdWishlist(),
                wishlist.getIdSach(),
                wishlist.getAddedTime());
    }

    public void delete(WishlistCT wishlist) {
        String deleteQuery = "DELETE FROM WishlistCT WHERE idSach = ? and idWishlist = ?";
        Jdbc.executeUpdate(deleteQuery, wishlist.getIdSach(), wishlist.getIdWishlist());
    }

    public void update(WishlistCT wishlist) {
        String updateQuery = "UPDATE WishlistCT SET addedTime = ? WHERE idWishlist = ? AND idSach = ?";
        Jdbc.executeUpdate(updateQuery,
                wishlist.getAddedTime(),
                wishlist.getIdWishlist(),
                wishlist.getIdSach());
    }

    public ArrayList<WishlistCT> search(String val) {
        String searchQuery = "SELECT * FROM WishlistCT WHERE idWishlist LIKE ?";
        return select(searchQuery, "%" + val + "%");
    }

    public ArrayList<WishlistCT> select() {
        String selectQuery = "SELECT * FROM WishlistCT";
        return select(selectQuery);
    }

    public WishlistCT selectByID(String id) {
        String selectQuery = "SELECT * FROM WishlistCT WHERE idWishlist = ?";
        ArrayList<WishlistCT> wl = select(selectQuery, id);
        return !wl.isEmpty() ? wl.get(0) : null;
    }

    private ArrayList<WishlistCT> select(String sql, Object... args) {
        ArrayList<WishlistCT> wlList = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    WishlistCT wl = readFromRS(rs);
                    wlList.add(wl);
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
        return wlList;
    }

    private WishlistCT readFromRS(ResultSet resultSet) throws SQLException {
        WishlistCT wl = new WishlistCT(
                resultSet.getString("idWishlist"),
                resultSet.getString("idSach"),
                resultSet.getDate("addedTime")
        );
        return wl;
    }
}

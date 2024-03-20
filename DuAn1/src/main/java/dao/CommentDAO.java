/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import library.Jdbc;
import model.Comment;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author TAN LOC
 */
public class CommentDAO {

    public void insert(Comment cm) {
        String sql = "INSERT INTO COMMENT (IDDANHGIA, IDREADER, IDSACH, SAO, CONTENT, [image], [video] , EDITABLE , [ENABLE]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            Jdbc.executeUpdate(sql,
                    cm.getIdDanhGia(),
                    cm.getIdReader(),
                    cm.getIdSach(),
                    cm.getSao(),
                    cm.getContent(),
                    cm.getImages(),
                    cm.getVideos(),
                    cm.getEditable(),
                    cm.getEnable()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(Comment cm) {
        String updateQuery = "UPDATE COMMENT SET SAO = ?, CONTENT = ?, [image] = ?, [video] = ?, EDITABLE = ?, [ENABLE] = ? WHERE IDDANHGIA = ?";

        try {
            Jdbc.executeUpdate(updateQuery,
                    cm.getSao(),
                    cm.getContent(),
                    cm.getImages(),
                    cm.getVideos(),
                    cm.getEditable(),
                    cm.getEnable(),
                    cm.getIdDanhGia());
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String IDDANHGIA) {
        String sql = "delete comment where IDDANHGIA = ?";
        Jdbc.executeQuery(sql, IDDANHGIA);
    }

    public ArrayList<Comment> selectall() {
        String sql = "select * from comment";
        ArrayList<Comment> list = select(sql);
        return list;
    }

    public ArrayList<Comment> selectallByidSach(String idsach) {
        String sql = "select * from comment where idsach = ?";
        ArrayList<Comment> list = select(sql, idsach);
        return list;
    }
    
    public Comment selectallByIdcomment(String iddanhgia) {
        String sql = "select * from comment where iddanhgia = ?";
        ArrayList<Comment> list = select(sql, iddanhgia);
        return !list.isEmpty() ? list.get(0): null;
    }

    private ArrayList<Comment> select(String sql, Object... arys) {
        ArrayList<Comment> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, arys);
                while (rs.next()) {
                    Comment cm = readerFrom(rs);
                    list.add(cm);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private Comment readerFrom(ResultSet rs) throws SQLException {
        Comment cm = new Comment(
                rs.getString("IDDANHGIA"),
                rs.getString("IDREADER"),
                rs.getString("IDSACH"),
                rs.getInt("SAO"),
                rs.getString("CONTENT"),
                rs.getString("image"),
                rs.getString("video"),
                rs.getBoolean("EDITABLE"),
                rs.getBoolean("ENABLE")
        );
        return cm;
    }

}

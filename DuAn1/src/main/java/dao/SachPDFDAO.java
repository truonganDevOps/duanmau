/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.Jdbc;
import model.SachPDF;

/**
 *
 * @author phamhuy
 */
public class SachPDFDAO {

    public void insert(SachPDF spdf) {
        String sql = "INSERT INTO SACHPDF (IDREADLIST, IDSACH, LASTSEENPAGE, ADDEDTIME) VALUES (?, ?, 0, ?)";
        Jdbc.executeUpdate(sql, spdf.getIdReadlist(), spdf.getIdSach(), spdf.getAddedTime());
    }

    public void updateSachPDF(SachPDF sachPDF) {
        String upDateQuery = "UPDATE SACHPDF SET LASTSEENPAGE = ? WHERE IDREADLIST = ? AND IDSACH = ?";
        Jdbc.executeUpdate(upDateQuery,
                sachPDF.getLastSeenPage(),
                sachPDF.getIdReadlist(),
                sachPDF.getIdSach());
    }

    public void deleteSachPDF(String idReadList, String idSach) {
        String sql = "DELETE FROM SACHPDF WHERE IDREADLIST = ? AND IDSACH = ?";
        Jdbc.executeUpdate(sql, idReadList, idSach);
    }

    public SachPDF selectById(String IDREADLIST, String IDSACH) {
        String sql = "select * from sachpdf where IDSACH = ? AND IDREADLIST = ?";
        ArrayList<SachPDF> list = select(sql, IDSACH, IDREADLIST);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public ArrayList<SachPDF> selectByIdUser(String IDREADLIST) {
        String sql = "select * from sachpdf where IDREADLIST = ?";
        return select(sql, IDREADLIST);
    }

    public ArrayList<SachPDF> selectAll() {
        String sql = "select * from sachpdf";
        ArrayList<SachPDF> list = select(sql);
        return list;
    }

    private ArrayList<SachPDF> select(String sql, Object... arys) {
        ArrayList<SachPDF> list = new ArrayList<>();
        try {
            ResultSet rs = null;

            try {
                rs = Jdbc.executeQuery(sql, arys);
                while (rs.next()) {
                    SachPDF sachpdf = readSachPDFFromRS(rs);
                    list.add(sachpdf);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private SachPDF readSachPDFFromRS(ResultSet rs) throws SQLException {
        SachPDF sachPDF = new SachPDF(
                rs.getString("IDREADLIST"),
                rs.getString("IDSACH"),
                rs.getInt("LASTSEENPAGE"),
                rs.getDate("ADDEDTIME")
        );
        return sachPDF;
    }
}

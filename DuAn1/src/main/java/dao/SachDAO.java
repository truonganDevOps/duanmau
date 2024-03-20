package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Arrays;
import library.Jdbc;
import model.Sach;
import model.TacGia;
import model.TheLoai;

public class SachDAO {

    public void insert(Sach sach) {
        String insertQuery = """
                         INSERT INTO SACH
                         VALUES (?, ?, ?, ?,
                         ?, ?, ?, ?,
                         ?, ?, ?, ?, 
                         ?, ?, ?, ?)""";
        Jdbc.executeUpdate(insertQuery,
                sach.getIdSach(),
                sach.getTenSach(),
                sach.getNamXB(),
                sach.getNamSangTac(),
                sach.getSoTrang(),
                sach.getEbookAccess(),
                sach.isHasFulltext(),
                sach.isPublicScanB(),
                sach.getUrlLink(),
                sach.getCoverI(),
                sach.getMoTa(),
                String.join("|", sach.getNgonNgu()),
                sach.getPhienBan(),
                sach.getViewCount(),
                sach.getLikeCount(),
                sach.getDanhGiaTB());
    }

    public void update(Sach sach) {
        String updateQuery = """
                 UPDATE SACH
                 SET 
                     TENSACH = ?,
                     NAMXB = ?,
                     NAMSANGTAC = ?,
                     SOTRANG = ?,
                     EBOOK_ACCESS = ?,
                     HAS_FULLTEXT = ?,
                     PUBLIC_SCAN_B = ?,
                     URL_LINK = ?,
                     COVER_I = ?,
                     MOTA = ?,
                     NGONNGU = ?,
                     PHIENBAN = ?,
                     VIEWCOUNT = ?,
                     LIKECOUNT = ?,
                     DANHGIA_TB = ?
                 WHERE IDSACH = ?;
                 """;
        Jdbc.executeUpdate(updateQuery,
                sach.getTenSach(),
                sach.getNamXB(),
                sach.getNamSangTac(),
                sach.getSoTrang(),
                sach.getEbookAccess(),
                sach.isHasFulltext(),
                sach.isPublicScanB(),
                sach.getUrlLink(),
                sach.getCoverI(),
                sach.getMoTa(),
                sach.getNgonNgu(),
                sach.getPhienBan(),
                sach.getViewCount(),
                sach.getLikeCount(),
                sach.getDanhGiaTB(),
                sach.getIdSach());
    }
    
    public void updateCt(Sach sach) {
        String updateQuery = """
                 UPDATE SACH
                 SET 
                     NAMSANGTAC = ?,
                     SOTRANG = ?,
                     MOTA = ?,
                     NGONNGU = ?
                 WHERE IDSACH = ?;
                 """;
        Jdbc.executeUpdate(updateQuery,
                sach.getNamSangTac(),
                sach.getSoTrang(),
                sach.getMoTa(),
                String.join("|", sach.getNgonNgu()),
                sach.getIdSach());
    }

    private Sach readFromRS(ResultSet resultSet) throws SQLException {
        Sach sach = new Sach(
                resultSet.getString("IDSACH"),
                resultSet.getString("TENSACH"),
                resultSet.getInt("NAMXB"),
                resultSet.getInt("NAMSANGTAC"),
                resultSet.getInt("SOTRANG"),
                resultSet.getString("EBOOK_ACCESS"),
                resultSet.getBoolean("HAS_FULLTEXT"),
                resultSet.getBoolean("PUBLIC_SCAN_B"),
                resultSet.getString("URLLINK"),
                resultSet.getString("COVER_I"),
                resultSet.getString("MOTA"),
                resultSet.getString("NGONNGU").split("\\|"),
                resultSet.getString("PHIENBAN"),
                resultSet.getInt("VIEWCOUNT"),
                resultSet.getInt("LIKECOUNT"),
                resultSet.getDouble("DANHGIATB"));
        return sach;
    }

    public void insertSvTG(Sach sach, TacGia tg) {
        String insertQuery = """
                             INSERT INTO SvTG (IDSACH, idTacGia)
                             VALUES (?, ?)""";
        Jdbc.executeUpdate(insertQuery,
                sach.getIdSach(),
                tg.getIdTacGia());
    }

    public void insertSvTG(Sach sach, ArrayList<TacGia> authors) {
        for (TacGia tg : authors) {
            insertSvTG(sach, tg);
        }
    }

    public void insertSvTL(Sach sach, TheLoai tl) {
        String insertQuery = """
                             INSERT INTO SvTL (IDSACH, idTheLoai)
                             VALUES (?, ?)""";
        Jdbc.executeUpdate(insertQuery,
                sach.getIdSach(),
                tl.getIdTheLoai());
    }

    public void insertSvTL(Sach sach, ArrayList<TheLoai> genres) {
        for (TheLoai tl : genres) {
            insertSvTL(sach, tl);
        }
    }

    public void delete(Sach sach) {
        String deleteQuery = """
                             delete from sach
                             where idSach = ?
                             """;
        Jdbc.executeUpdate(deleteQuery, sach.getIdSach());
    }

    public void deleteSvTG(Sach sach) {
        String deleteQuery = """
                             delete from SvTG
                             where idSach = ?
                             """;
        Jdbc.executeUpdate(deleteQuery, sach.getIdSach());
    }

    public void deleteSvTL(Sach sach) {
        String deleteQuery = """
                             delete from SvTL
                             where idSach = ?
                             """;
        Jdbc.executeUpdate(deleteQuery, sach.getIdSach());
    }

    public void updateSvTG(Sach sach, TacGia tg) {
        deleteSvTG(sach);
        insertSvTG(sach, tg);
    }

    public void updateSvTG(Sach sach, ArrayList<TacGia> authors) {
        deleteSvTG(sach);
        insertSvTG(sach, authors);
    }

    public void updateSvTL(Sach sach, TheLoai tg) {
        deleteSvTL(sach);
        insertSvTL(sach, tg);
    }

    public void updateSvTL(Sach sach, ArrayList<TheLoai> genres) {
        deleteSvTL(sach);
        insertSvTL(sach, genres);
    }

    public ArrayList<Sach> select() {
        String selectQuery = "select * from Sach";
        return select(selectQuery);
    }

    public ArrayList<Sach> search(String searchValue) {
        String selectQuery = "{call searchSach (?)}";
        return select(selectQuery, searchValue);

    }

    public ArrayList<Sach> thongKeSach(Boolean isView, Boolean isAsc) {
        String exec = "{call thongKeSach (?)}";
        ArrayList<Sach> list = select(exec, isView);
        if (!isAsc) {
            Collections.reverse(list);
        }
        return list;
    }

    public Sach selectByID(String idSach) {
        String selectQuery = "select * from Sach "
                + "where idSach = ?";
        ArrayList<Sach> sachs = select(selectQuery, idSach);
        return sachs.isEmpty() ? null : sachs.get(0);
    }

    private ArrayList<Sach> select(String sql, Object... args) {
        ArrayList<Sach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    Sach sach = readFromRS(rs);
                    list.add(sach);
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
        return list;

    }
    public void updateView(Sach sach) {
        String Query = "UPDATE [dbo].[Sach] SET [viewCount] = [viewCount] + 1 WHERE idsach = ?";
        Jdbc.executeUpdate(Query, sach.getIdSach());
    }
    public void updateLike(Sach sach) {
        String Query = "UPDATE [dbo].[Sach] SET [likeCount] = [likeCount] + 1 WHERE idsach = ?";
        Jdbc.executeUpdate(Query, sach.getIdSach());
    }
    public void updateUnlike(Sach sach) {
        String Query = "UPDATE [dbo].[Sach] SET [likeCount] = [likeCount] - 1 WHERE idsach = ?";
        Jdbc.executeUpdate(Query, sach.getIdSach());
    }
}

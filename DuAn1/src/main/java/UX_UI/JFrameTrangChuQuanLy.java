/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UX_UI;

import dao.AccessDAO;
import dao.NoiBoDAO;
import dao.PhongBanDAO;
import dao.SachDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import library.Auth;
import library.DialogHelper;
import library.Extension;
import library.XImage;
import model.NoiBo;
import model.PhongBan;
import model.Sach;

/**
 *
 * @author Admin
 */
public class JFrameTrangChuQuanLy extends javax.swing.JFrame {

    /**
     * Creates new form JFrameTrangChuQuanLy
     */
    public JFrameTrangChuQuanLy() {
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

//    String userID;
//
//    public JFrameTrangChuQuanLy(String userID) {
//        initComponents();
//        setLocationRelativeTo(null);
//        this.userID = userID;
//        init();
//    }
    private void init() {
        if (!Auth.isLogin()) {
            boolean choice = DialogHelper.confirm(null, "Bạn chưa đăng nhập, bạn có muốn đăng nhập không?");
            this.dispose();
            if (choice) {
                new JDialogLogin(this, true).setVisible(true);
            }
        }

        XImage.setLogoButton(btnLogo);
        resizeColumn();
        getListSach();
        fillToTable();
        fillToLabel();
        checkPage();

        cmbTK.addActionListener(e -> {
            load();
            curPage = 1;
            checkPage();
        });
        btnFirst.addActionListener(e -> {
            curPage = 1;
            checkPage();
            fillToLabel();
        });
        btnLast.addActionListener(e -> {
            curPage = (int) Math.ceil(listSach.size() / (double) sachCount);
            checkPage();
            fillToLabel();
        });
        btnPrev.addActionListener(e -> {
            curPage--;
            checkPage();
            fillToLabel();
        });
        btnNext.addActionListener(e -> {
            curPage++;
            checkPage();
            fillToLabel();
        });

        btnAccess.addActionListener(e -> {
            new JDialogAccess(this, true).setVisible(true);
        });
        btnPhongBan.addActionListener(e -> {
            new JDialogQuanLyPhongBan(this, true).setVisible(true);
        });
        btnNoiBo.addActionListener(e -> {
            new JDialogQuanLyNhanVien(this, true).setVisible(true);
        });
        btnNguoiDoc.addActionListener(e -> {
            new JDialogQuanLyThongTinKhachHang(this, true).setVisible(true);
        });
        btnSach.addActionListener(e -> {
            new JDialogSach(this, true).setVisible(true);
        });
        btnTacGia.addActionListener(e -> {
//            new JDialogAccess(this, true).setVisible(true);
        });
        btnTheLoai.addActionListener(e -> {
//            new JDialogAccess(this, true).setVisible(true);
        });

        //
        setAccess();
        setEnableBtn();

        // để ở 2 form chính || form login || form chờ
//        for (Sach sach : dao.select()) {
//            URL_Dealer.downloadImage(sach.getCoverI(), false);
//        }
//        pnlSach10.setVisible(false);
//        pnlSach9.setVisible(false);
//        pnlSach8.setVisible(false);
//        pnlSach7.setVisible(false);
//        pnlSach6.setVisible(false);
//        pnlSach5.setVisible(false);
//        pnlSach4.setVisible(false);
//        pnlSach3.setVisible(false);
//        pnlSach3.setVisible(false);
//        pnlSach2.setVisible(false);
//        pnlSach1.setVisible(false);
    }

    SachDAO dao = new SachDAO();
    NoiBoDAO nbDao = new NoiBoDAO();
    PhongBanDAO pbDao = new PhongBanDAO();
    AccessDAO accDao = new AccessDAO();
    ArrayList<Sach> listSach;
    final Integer sachCount = 10;
    int curPage = 1;

    void setAccess() {
        if (Auth.user == null) {
            return;
        }
        NoiBo nb = nbDao.selectByUserID(Auth.user.getUserID());
        if (nb == null) {
            return;
        }
        if (nb.getAvatar() != null) {
            XImage.setInfoBtn(btnAvatar, nb.getHoTen(), nb.getAvatar());
        } else {
            btnAvatar.setText(nb.getHoTen());
        }
        PhongBan pb = pbDao.selectByID(nb.getIdPB());
        if (pb == null) {
            return;
        }

        Integer idAccess = (nb.getQuanLy()) ? pb.getQlAccess() : pb.getNvAccess();
        Auth.access = accDao.selectById(idAccess);
    }

    void setEnableBtn() {
        btnAccess.setEnabled(false);
        btnPhongBan.setEnabled(false);
        btnNoiBo.setEnabled(false);
        btnNguoiDoc.setEnabled(false);
        btnSach.setEnabled(false);
        btnTacGia.setEnabled(false);
        btnTheLoai.setEnabled(false);
        if (Auth.access == null) {
            return;
        }
        if (Auth.access.getFullAccess()) {
            btnAccess.setEnabled(true);
            btnPhongBan.setEnabled(true);
            btnNoiBo.setEnabled(true);
            btnNguoiDoc.setEnabled(true);
            btnSach.setEnabled(true);
            btnTacGia.setEnabled(true);
            btnTheLoai.setEnabled(true);
            return;
        }
        btnPhongBan.setEnabled(Auth.access.getrPhongBan() || Auth.access.getuPhongBan());
        btnNoiBo.setEnabled(Auth.access.getrNoiBo() || Auth.access.getuNoiBo());
        btnNguoiDoc.setEnabled(Auth.access.getrReader() || Auth.access.getuReader());
        btnSach.setEnabled(Auth.access.getrSach() || Auth.access.getuSach());
        btnTacGia.setEnabled(Auth.access.getrTacGia() || Auth.access.getuTacGia());
        btnTheLoai.setEnabled(Auth.access.getrTheLoai() || Auth.access.getuTheLoai());
    }

    void showPersonalInfo() {

    }

    void load() {
        getListSach();
        fillToLabel();
        fillToTable();
    }

    void getListSach() {
        Boolean isView = (cmbTK.getSelectedIndex() == 1 || cmbTK.getSelectedIndex() == 3);
        Boolean isAsc = (cmbTK.getSelectedIndex() == 3 || cmbTK.getSelectedIndex() == 4);
        if (cmbTK.getSelectedIndex() == 0) {
            this.listSach = dao.select();
        } else {
            this.listSach = dao.thongKeSach(isView, isView);
        }
    }

    void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);

        for (Sach sach : this.listSach) {
            Object[] row = {sach.getIdSach(),
                sach.getTenSach(),
                sach.getMoTa(),
                sach.getEbookAccess(),
                sach.getNamSangTac(),
                sach.getSoTrang(),
                sach.getViewCount(),
                sach.getLikeCount(),
                sach.getDanhGiaTB()};
            model.addRow(row);
        }
    }

    void checkPage() {
        btnFirst.setEnabled(true);
        btnPrev.setEnabled(true);
        btnLast.setEnabled(true);
        btnNext.setEnabled(true);
        if (curPage == 1) {
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
        }
        int maxPage = (int)Math.ceil(listSach.size() / (double)sachCount);
        if (curPage >= maxPage) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        }
        lblPage.setText("Page " + curPage + "/" + maxPage);
    }

    void fillToLabel() {
//        pnlSach10.setVisible(false);
//        pnlSach9.setVisible(false);
//        pnlSach8.setVisible(false);
//        pnlSach7.setVisible(false);
//        pnlSach6.setVisible(false);
//        pnlSach5.setVisible(false);
//        pnlSach4.setVisible(false);
//        pnlSach3.setVisible(false);
//        pnlSach3.setVisible(false);
//        pnlSach2.setVisible(false);
//        pnlSach1.setVisible(false);
        for (int i = (curPage - 1) * sachCount; i < listSach.size() && i < curPage * sachCount; i++) {
            Sach sach = listSach.get(i);
            System.out.println(sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
            switch (i % sachCount) {
                case (10 - 1) -> {
                    Extension.scaleImage(lblCover10, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach10.setText(sach.getTenSach());
//                    pnlSach10.setVisible(true);
                }
                case (9 - 1) -> {
                    Extension.scaleImage(lblCover9, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach9.setText(sach.getTenSach());
//                    pnlSach9.setVisible(true);
                }
                case (8 - 1) -> {
                    Extension.scaleImage(lblCover8, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach8.setText(sach.getTenSach());
//                    pnlSach8.setVisible(true);
                }
                case (7 - 1) -> {
                    Extension.scaleImage(lblCover7, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach7.setText(sach.getTenSach());
//                    pnlSach7.setVisible(true);
                }
                case (6 - 1) -> {
                    Extension.scaleImage(lblCover6, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach6.setText(sach.getTenSach());
//                    pnlSach6.setVisible(true);
                }
                case (5 - 1) -> {
                    Extension.scaleImage(lblCover5, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach5.setText(sach.getTenSach());
//                    pnlSach5.setVisible(true);
                }
                case (4 - 1) -> {
                    Extension.scaleImage(lblCover4, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach4.setText(sach.getTenSach());
//                    pnlSach4.setVisible(true);
                }
                case (3 - 1) -> {
                    Extension.scaleImage(lblCover3, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach3.setText(sach.getTenSach());
//                    pnlSach3.setVisible(true);
                }
                case (2 - 1) -> {
                    Extension.scaleImage(lblCover2, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach2.setText(sach.getTenSach());
//                    pnlSach2.setVisible(true);
                }
                case (1 - 1) -> {
                    Extension.scaleImage(lblCover1, sach.getCoverI().substring(sach.getCoverI().lastIndexOf('/') + 1));
                    lblTenSach1.setText(sach.getTenSach());
//                    pnlSach1.setVisible(true);
                }
                default -> {
                }
            }
        }
    }

    void logOut() {
        if (DialogHelper.confirm(null, "Bạn có muốn đăng xuất?")) {
            Auth.user = null;
            this.dispose();
        }
    }

    private void resizeColumn() {
//        TableColumn col1 = tblChuyenDe.getColumnModel().getColumn(1);
        TableColumnModel clm = tblThongKe.getColumnModel();
        clm.getColumn(0).setPreferredWidth(100);
        clm.getColumn(1).setPreferredWidth(180);
        clm.getColumn(2).setPreferredWidth(180);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnAccess = new javax.swing.JButton();
        btnPhongBan = new javax.swing.JButton();
        btnNoiBo = new javax.swing.JButton();
        btnNguoiDoc = new javax.swing.JButton();
        btnSach = new javax.swing.JButton();
        btnTacGia = new javax.swing.JButton();
        btnTheLoai = new javax.swing.JButton();
        btnAvatar = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        cmbTK = new javax.swing.JComboBox<>();
        tabTK = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pnlSach1 = new javax.swing.JPanel();
        lblCover1 = new javax.swing.JLabel();
        lblTenSach1 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlSach2 = new javax.swing.JPanel();
        lblCover2 = new javax.swing.JLabel();
        lblTenSach2 = new javax.swing.JLabel();
        pnlSach3 = new javax.swing.JPanel();
        lblCover3 = new javax.swing.JLabel();
        lblTenSach3 = new javax.swing.JLabel();
        pnlSach4 = new javax.swing.JPanel();
        lblCover4 = new javax.swing.JLabel();
        lblTenSach4 = new javax.swing.JLabel();
        pnlSach5 = new javax.swing.JPanel();
        lblCover5 = new javax.swing.JLabel();
        lblTenSach5 = new javax.swing.JLabel();
        pnlSach6 = new javax.swing.JPanel();
        lblCover6 = new javax.swing.JLabel();
        lblTenSach6 = new javax.swing.JLabel();
        pnlSach7 = new javax.swing.JPanel();
        lblCover7 = new javax.swing.JLabel();
        lblTenSach7 = new javax.swing.JLabel();
        pnlSach8 = new javax.swing.JPanel();
        lblCover8 = new javax.swing.JLabel();
        lblTenSach8 = new javax.swing.JLabel();
        pnlSach9 = new javax.swing.JPanel();
        lblCover9 = new javax.swing.JLabel();
        lblTenSach9 = new javax.swing.JLabel();
        pnlSach10 = new javax.swing.JPanel();
        lblCover10 = new javax.swing.JLabel();
        lblTenSach10 = new javax.swing.JLabel();
        lblPage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLogo.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        btnLogo.setForeground(new java.awt.Color(51, 102, 0));
        btnLogo.setText("logo");
        btnLogo.setBorder(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAccess.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnAccess.setForeground(new java.awt.Color(204, 153, 0));
        btnAccess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Lock.png"))); // NOI18N
        btnAccess.setText("Quản Lý Quyền Truy Cập");

        btnPhongBan.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnPhongBan.setForeground(new java.awt.Color(204, 153, 0));
        btnPhongBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User group.png"))); // NOI18N
        btnPhongBan.setText("Quản Lý Phòng Ban");

        btnNoiBo.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnNoiBo.setForeground(new java.awt.Color(204, 153, 0));
        btnNoiBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Boy.png"))); // NOI18N
        btnNoiBo.setText("Quản Lý Nhân Sự");

        btnNguoiDoc.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnNguoiDoc.setForeground(new java.awt.Color(204, 153, 0));
        btnNguoiDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Users.png"))); // NOI18N
        btnNguoiDoc.setText("Quản Lý Người Đọc");

        btnSach.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnSach.setForeground(new java.awt.Color(204, 153, 0));
        btnSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Book.png"))); // NOI18N
        btnSach.setText("Quản Lý Sách");

        btnTacGia.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnTacGia.setForeground(new java.awt.Color(204, 153, 0));
        btnTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Unknown person.png"))); // NOI18N
        btnTacGia.setText("Quản Lý Tác Giả");

        btnTheLoai.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnTheLoai.setForeground(new java.awt.Color(204, 153, 0));
        btnTheLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Lists.png"))); // NOI18N
        btnTheLoai.setText("Quản Lý Thể Loại");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAccess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPhongBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNoiBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNguoiDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAccess)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhongBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNoiBo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNguoiDoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTacGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTheLoai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAvatar.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Girl.png"))); // NOI18N
        btnAvatar.setText("Username");
        btnAvatar.setBorder(null);
        btnAvatar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnDangXuat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Log out.png"))); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        cmbTK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cmbTK.setForeground(new java.awt.Color(204, 102, 0));
        cmbTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Books", "Most Viewed Books", "Most Liked Books", "Least Viewed Books", "Least Liked Books" }));
        cmbTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTKActionPerformed(evt);
            }
        });

        pnlSach1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach1.setText("Tên sách");
        lblTenSach1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach1Layout = new javax.swing.GroupLayout(pnlSach1);
        pnlSach1.setLayout(pnlSach1Layout);
        pnlSach1Layout.setHorizontalGroup(
            pnlSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCover1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach1Layout.setVerticalGroup(
            pnlSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFirst.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnFirst.setText("|<");

        btnPrev.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnPrev.setText("<<");

        btnNext.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnLast.setText(">|");

        pnlSach2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach2.setText("Tên sách");
        lblTenSach2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach2Layout = new javax.swing.GroupLayout(pnlSach2);
        pnlSach2.setLayout(pnlSach2Layout);
        pnlSach2Layout.setHorizontalGroup(
            pnlSach2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCover2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach2Layout.setVerticalGroup(
            pnlSach2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach3.setText("Tên sách");
        lblTenSach3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach3Layout = new javax.swing.GroupLayout(pnlSach3);
        pnlSach3.setLayout(pnlSach3Layout);
        pnlSach3Layout.setHorizontalGroup(
            pnlSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenSach3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCover3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach3Layout.setVerticalGroup(
            pnlSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach4.setText("Tên sách");
        lblTenSach4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach4Layout = new javax.swing.GroupLayout(pnlSach4);
        pnlSach4.setLayout(pnlSach4Layout);
        pnlSach4Layout.setHorizontalGroup(
            pnlSach4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCover4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach4Layout.setVerticalGroup(
            pnlSach4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach5.setText("Tên sách");
        lblTenSach5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach5Layout = new javax.swing.GroupLayout(pnlSach5);
        pnlSach5.setLayout(pnlSach5Layout);
        pnlSach5Layout.setHorizontalGroup(
            pnlSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCover5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach5Layout.setVerticalGroup(
            pnlSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach6.setText("Tên sách");
        lblTenSach6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach6Layout = new javax.swing.GroupLayout(pnlSach6);
        pnlSach6.setLayout(pnlSach6Layout);
        pnlSach6Layout.setHorizontalGroup(
            pnlSach6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCover6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach6Layout.setVerticalGroup(
            pnlSach6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach7.setText("Tên sách");
        lblTenSach7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach7Layout = new javax.swing.GroupLayout(pnlSach7);
        pnlSach7.setLayout(pnlSach7Layout);
        pnlSach7Layout.setHorizontalGroup(
            pnlSach7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCover7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach7Layout.setVerticalGroup(
            pnlSach7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach8.setText("Tên sách");
        lblTenSach8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach8Layout = new javax.swing.GroupLayout(pnlSach8);
        pnlSach8.setLayout(pnlSach8Layout);
        pnlSach8Layout.setHorizontalGroup(
            pnlSach8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCover8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach8Layout.setVerticalGroup(
            pnlSach8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover8, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach9.setText("Tên sách");
        lblTenSach9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach9Layout = new javax.swing.GroupLayout(pnlSach9);
        pnlSach9.setLayout(pnlSach9Layout);
        pnlSach9Layout.setHorizontalGroup(
            pnlSach9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCover9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach9Layout.setVerticalGroup(
            pnlSach9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover9, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSach10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCover10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenSach10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenSach10.setText("Tên sách");
        lblTenSach10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSach10Layout = new javax.swing.GroupLayout(pnlSach10);
        pnlSach10.setLayout(pnlSach10Layout);
        pnlSach10Layout.setHorizontalGroup(
            pnlSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSach10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCover10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSach10Layout.setVerticalGroup(
            pnlSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSach10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCover10, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenSach10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPage.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPage.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlSach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSach2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSach3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSach4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSach5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlSach6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlSach7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrev)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSach8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlSach10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLast)))
                        .addGap(18, 18, 18)
                        .addComponent(pnlSach9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlSach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSach6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlSach2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSach7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlSach5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSach9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSach3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlSach4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSach8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlSach10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnPrev)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFirst)
                        .addComponent(lblPage)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabTK.addTab("Items View", jPanel2);

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Sách", "Tên Sách", "Mô Tả", "Public Access", "Năm ST", "Số Trang", "View Count", "Like Count", "Điểm ĐG"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblThongKe);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabTK.addTab("Table View", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbTK, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDangXuat))
                    .addComponent(tabTK))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tabTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        logOut();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void cmbTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTKActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameTrangChuQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JFrameTrangChuQuanLy().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccess;
    private javax.swing.JButton btnAvatar;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLogo;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNguoiDoc;
    private javax.swing.JButton btnNoiBo;
    private javax.swing.JButton btnPhongBan;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSach;
    private javax.swing.JButton btnTacGia;
    private javax.swing.JButton btnTheLoai;
    private javax.swing.JComboBox<String> cmbTK;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCover1;
    private javax.swing.JLabel lblCover10;
    private javax.swing.JLabel lblCover2;
    private javax.swing.JLabel lblCover3;
    private javax.swing.JLabel lblCover4;
    private javax.swing.JLabel lblCover5;
    private javax.swing.JLabel lblCover6;
    private javax.swing.JLabel lblCover7;
    private javax.swing.JLabel lblCover8;
    private javax.swing.JLabel lblCover9;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblTenSach1;
    private javax.swing.JLabel lblTenSach10;
    private javax.swing.JLabel lblTenSach2;
    private javax.swing.JLabel lblTenSach3;
    private javax.swing.JLabel lblTenSach4;
    private javax.swing.JLabel lblTenSach5;
    private javax.swing.JLabel lblTenSach6;
    private javax.swing.JLabel lblTenSach7;
    private javax.swing.JLabel lblTenSach8;
    private javax.swing.JLabel lblTenSach9;
    private javax.swing.JPanel pnlSach1;
    private javax.swing.JPanel pnlSach10;
    private javax.swing.JPanel pnlSach2;
    private javax.swing.JPanel pnlSach3;
    private javax.swing.JPanel pnlSach4;
    private javax.swing.JPanel pnlSach5;
    private javax.swing.JPanel pnlSach6;
    private javax.swing.JPanel pnlSach7;
    private javax.swing.JPanel pnlSach8;
    private javax.swing.JPanel pnlSach9;
    private javax.swing.JTabbedPane tabTK;
    private javax.swing.JTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}

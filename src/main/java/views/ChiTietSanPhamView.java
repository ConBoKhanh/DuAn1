/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChiTietDoGo;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.SanPham;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.ChiTietDoGoService;
import services.DonViTinhService;
import services.DongGoService;
import services.LoaiSanPhamService;
import services.NguonGocService;
import services.NhaCungCapService;
import services.SanPhamService;
import services.impl.IManageChiTietDoGoService;
import services.impl.IManageDonViTinhService;
import services.impl.IManageDongGoService;
import services.impl.IManageLoaiSanPhamService;
import services.impl.IManageNguonGocService;
import services.impl.IManageNhaCungCapService;
import services.impl.IManageSanPhamService;
import viewModel.ChiTietDoGoViewModel;
import viewModel.ViewModelDonViTinh;
import viewModel.ViewModelDongGo;
import viewModel.ViewModelLoaiSanPham;
import viewModel.ViewModelNguonGoc;
import viewModel.ViewModelNhaCungCap;
import viewModel.ViewModelSanPham;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamView extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietSanPhamView
     */
    private IManageChiTietDoGoService a = new ChiTietDoGoService();
    private IManageSanPhamService spSV = new SanPhamService();
    private IManageLoaiSanPhamService lSpSv = new LoaiSanPhamService();
    private IManageNhaCungCapService nhaCungCapService = new NhaCungCapService();
    private IManageDonViTinhService dvtrp = new DonViTinhService();
    private IManageNguonGocService ngSv = new NguonGocService();
    private IManageDongGoService dgSV = new DongGoService();

    DefaultTableModel model = new DefaultTableModel();

    public ChiTietSanPhamView() {
        initComponents();
        load();
        loadcbc();
    }

    public void load() {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        List<ChiTietDoGoViewModel> list = a.list();
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                n.getGiaNhap(),
                n.getGiaBan()

            });
        }
    }

    public void timKiem() {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        List<ChiTietDoGoViewModel> list = a.listtk(txtTimKiem.getText());
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                n.getGiaNhap(),
                n.getGiaBan()

            });
        }
    }

    public String getSPCBC() {
        String id = null;
        List<ViewModelSanPham> a = spSV.getListSP();
        for (ViewModelSanPham sp : a) {
            if (cbcSP.getSelectedItem().equals(sp.getTen())) {
                id = sp.getId();
            }
        }
        return id;
    }

    public String getDGCBC() {
        String id = null;
        List<ViewModelDongGo> b = dgSV.getListDongGo();
        for (ViewModelDongGo dg : b) {
            if (cbcDongGo.getSelectedItem().equals(dg.getTenLoaiGo())) {
                id = dg.getId();
            }
        }
        return id;
    }

    public String getLoaiCBC() {
        String id = null;
        List<ViewModelLoaiSanPham> c = lSpSv.getListLoaiSP();
        for (ViewModelLoaiSanPham l : c) {
            if (cbcLoaiSP.getSelectedItem().equals(l.getTenDongSP())) {
                id = l.getId();
            }
        }
        return id;
    }

    public String getNCCCBC() {
        String id = null;
        List<ViewModelNhaCungCap> d = nhaCungCapService.getAll();
        for (ViewModelNhaCungCap ncc : d) {
            if (ncc.getTenNCC().equals(cbNhaCC.getSelectedItem())) {
                id = ncc.getId();
            }
        }
        return id;
    }

    public String getDVTCBC() {
        String id = null;
        List<ViewModelDonViTinh> e = dvtrp.getListDVT();
        for (ViewModelDonViTinh dvt : e) {
            if (cbcDVT.getSelectedItem().equals(dvt.getDonViTinh())) {
                id = dvt.getId();
            }
        }
        return id;
    }

    public String getNguonGocTCBC() {
        String id = null;
        List<ViewModelNguonGoc> f = ngSv.getAll();
        for (ViewModelNguonGoc ng : f) {
            if (cbcNguonGoc.getSelectedItem().equals(ng.getquocgia())) {
                id = ng.getId();
            }
        }
        return id;
    }

    public void loadcbc() {
        List<ViewModelSanPham> a = spSV.getListSP();
        for (ViewModelSanPham sp : a) {
            cbcSP.addItem(sp.getTen());
        }
        List<ViewModelDongGo> b = dgSV.getListDongGo();
        for (ViewModelDongGo dg : b) {
            cbcDongGo.addItem(dg.getTenLoaiGo());
        }
        List<ViewModelLoaiSanPham> c = lSpSv.getListLoaiSP();
        for (ViewModelLoaiSanPham l : c) {
            cbcLoaiSP.addItem(l.getTenDongSP());
        }
        List<ViewModelNhaCungCap> d = nhaCungCapService.getAll();
        for (ViewModelNhaCungCap ncc : d) {
            cbNhaCC.addItem(ncc.getTenNCC());
        }
        List<ViewModelDonViTinh> e = dvtrp.getListDVT();
        for (ViewModelDonViTinh dvt : e) {
            cbcDVT.addItem(dvt.getDonViTinh());
        }
        List<ViewModelNguonGoc> f = ngSv.getAll();
        for (ViewModelNguonGoc ng : f) {
            cbcNguonGoc.addItem(ng.getquocgia());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cbcLoaiSP = new javax.swing.JComboBox<>();
        cbcSP = new javax.swing.JComboBox<>();
        cbcDongGo = new javax.swing.JComboBox<>();
        cbcNguonGoc = new javax.swing.JComboBox<>();
        cbNhaCC = new javax.swing.JComboBox<>();
        cbcDVT = new javax.swing.JComboBox<>();
        txtid = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Ten SP", "SPham", "LoaiSP", "DongGo", "NhaCungCap", "NguonGoc", "Dv Tinh", "MoTa", "SoLuong", "GiaNhap", "GiaBan"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        txtTimKiem.setBackground(new java.awt.Color(255, 204, 255));
        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cbcLoaiSP.setBackground(new java.awt.Color(255, 204, 255));
        cbcLoaiSP.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcSP.setBackground(new java.awt.Color(255, 204, 255));
        cbcSP.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcDongGo.setBackground(new java.awt.Color(255, 204, 255));
        cbcDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcNguonGoc.setBackground(new java.awt.Color(255, 204, 255));
        cbcNguonGoc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbNhaCC.setBackground(new java.awt.Color(255, 204, 255));
        cbNhaCC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcDVT.setBackground(new java.awt.Color(255, 204, 255));
        cbcDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtid.setText("id");
        txtid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Id");

        txtGiaNhap.setBackground(new java.awt.Color(255, 204, 255));
        txtGiaNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên");

        txtTen.setBackground(new java.awt.Color(255, 204, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Giá Nhập");

        txtGiaBan.setBackground(new java.awt.Color(255, 204, 255));
        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giá Bán");

        taMoTa.setColumns(20);
        taMoTa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        taMoTa.setRows(5);
        jScrollPane2.setViewportView(taMoTa);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Mô Tả");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Sản Phẩm");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Dòng Gỗ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Đơn Vị Tính");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Loại Sp");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Nhà Cung Cấp ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Nguồn Gốc");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jButton2.setBackground(new java.awt.Color(255, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 204, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Update");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 204, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Nhập");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Số  Lượng");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuong.setText("Soluong");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbcDongGo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 174, Short.MAX_VALUE)
                                    .addComponent(cbcSP, javax.swing.GroupLayout.Alignment.TRAILING, 0, 174, Short.MAX_VALUE)
                                    .addComponent(cbcLoaiSP, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(txtGiaBan))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbcDVT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbcNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addComponent(cbNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(13, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbcSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbcDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbcLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbcDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbcNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (txtTimKiem.getText().equals("")) {
            load();
        } else {
            timKiem();
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        int index = tbl.getSelectedRow();
        txtid.setText(tbl.getValueAt(index, 0).toString());
        txtTen.setText(tbl.getValueAt(index, 1).toString());
        txtGiaNhap.setText(tbl.getValueAt(index, 10).toString());
        System.out.println(tbl.getValueAt(index, 10).toString());
        txtGiaBan.setText(tbl.getValueAt(index, 11).toString());
        taMoTa.setText(tbl.getValueAt(index, 8).toString());
        txtSoLuong.setText(tbl.getValueAt(index, 9).toString());
        cbcSP.setSelectedItem(tbl.getValueAt(index, 2).toString());
        cbcLoaiSP.setSelectedItem(tbl.getValueAt(index, 3).toString());
        cbcDongGo.setSelectedItem(tbl.getValueAt(index, 4).toString());
        cbNhaCC.setSelectedItem(tbl.getValueAt(index, 5).toString());
        cbcDVT.setSelectedItem(tbl.getValueAt(index, 6).toString());
        cbcNguonGoc.setSelectedItem(tbl.getValueAt(index, 7).toString());
    }//GEN-LAST:event_tblMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String idsp = getSPCBC();
        String iddonggo = getDGCBC();
        String idLoai = getLoaiCBC();
        String Ncc = getNCCCBC();
        String dvt = getDVTCBC();
        String nguongoc = getNguonGocTCBC();
        String tensp = txtTen.getText();
        BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBan.getText());
        String mota = taMoTa.getText();

        ChiTietDoGo dg = new ChiTietDoGo();

        SanPham aa = new SanPham();
        aa.setId(idsp);

        DongGo bb = new DongGo();
        bb.setId(iddonggo);

        LoaiSP c = new LoaiSP();
        c.setId(idLoai);

        NhaCungCap d = new NhaCungCap();
        d.setId(Ncc);

        DonViTinh e = new DonViTinh();
        e.setId(dvt);

        NguonGoc f = new NguonGoc();
        f.setId(nguongoc);

        dg.setIdSanPham(aa);
        dg.setIdDongGo(bb);
        dg.setIdLoaiSP(c);
        dg.setIdNhaCungCap(d);
        dg.setIdDonViTinh(e);
        dg.setIdNguocGoc(f);
        dg.setTenSP(tensp);
        dg.setMoTa(mota);
        dg.setGiaNhap(giaNhap);
        dg.setGiaBan(giaBan);

        boolean b = a.add(dg);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            load();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String id = txtid.getText();
        String idsp = getSPCBC();
        String iddonggo = getDGCBC();
        String idLoai = getLoaiCBC();
        String Ncc = getNCCCBC();
        String dvt = getDVTCBC();
        String nguongoc = getNguonGocTCBC();
        String tensp = txtTen.getText();
        BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBan.getText());
        String mota = taMoTa.getText();

        ChiTietDoGo dg = new ChiTietDoGo();

        SanPham aa = new SanPham();
        aa.setId(idsp);

        DongGo bb = new DongGo();
        bb.setId(iddonggo);

        LoaiSP c = new LoaiSP();
        c.setId(idLoai);

        NhaCungCap d = new NhaCungCap();
        d.setId(Ncc);

        DonViTinh e = new DonViTinh();
        e.setId(dvt);

        NguonGoc f = new NguonGoc();
        f.setId(nguongoc);
        
        dg.setId(id);
        dg.setIdSanPham(aa);
        dg.setIdDongGo(bb);
        dg.setIdLoaiSP(c);
        dg.setIdNhaCungCap(d);
        dg.setIdDonViTinh(e);
        dg.setIdNguocGoc(f);
        dg.setTenSP(tensp);
        dg.setMoTa(mota);
        dg.setGiaNhap(giaNhap);
        dg.setGiaBan(giaBan);

        boolean b = a.update(dg);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Update sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            load();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Update Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        boolean b = a.delete(txtid.getText());
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            load();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietSanPhamView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbNhaCC;
    private javax.swing.JComboBox<String> cbcDVT;
    private javax.swing.JComboBox<String> cbcDongGo;
    private javax.swing.JComboBox<String> cbcLoaiSP;
    private javax.swing.JComboBox<String> cbcNguonGoc;
    private javax.swing.JComboBox<String> cbcSP;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taMoTa;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JLabel txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txtid;
    // End of variables declaration//GEN-END:variables
}

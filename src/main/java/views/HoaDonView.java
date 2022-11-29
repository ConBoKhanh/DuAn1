package views;

import domainModels.HoaDon;
import domainModels.NhanVien;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;
import services.HoaDonBanHangService;
import services.HoaDonChiTietService;
import services.HoadonService;
import services.impl.IManageChiTietHoaDonBanHang;
import services.impl.IManageHoaDonService;
import viewModel.ViewModelHoaDonChiTietBanHang;
import viewModel.ViewModelHoadon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class HoaDonView extends javax.swing.JFrame {

    private DefaultTableModel model;
    private IManageHoaDonService hdService = new HoadonService();
    private IManageChiTietHoaDonBanHang hdCTService = new HoaDonChiTietService();

    String IdNV;
    String TenNV;
    String CV;

    /**
     * Creates new form HoaDonView
     */
    public HoaDonView(String Id, String Ten, String cv) {
        initComponents();
        this.setDefaultCloseOperation(HoaDonView.DO_NOTHING_ON_CLOSE);
        IdNV = Id;
        TenNV = Ten;
        CV = cv;
        setLocationRelativeTo(null);
        txtIdHoaDon.setEditable(false);
        txtMaHD.setEditable(false);
        loadTbHD(1);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadTbHD(page);
            }
        });

    }

    public void loadTbHD(int page) {
        int limit = 5;
        int count = 0;
        List<ViewModelHoadon> hd = hdService.getListHoaDon((page - 1) * limit, limit);
        if (hd == null) {
            return;
        }
        count = hdService.row();

        model = (DefaultTableModel) tbHD.getModel();
        model.setRowCount(0);

        for (ViewModelHoadon v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenNV(),
                v.getTenKH(),
                v.getNgayTao(), v.getNgayThanhToan(),
                v.getPhamtramKM(),
                v.getTrangThaiHoaDon()

            });

        }
        int totalPage = (int) Math.ceil(count / limit);
        if (count / limit != 0) {
            pagination1.setPagegination(page, totalPage + 1);
        } else if (count / limit == 0) {
            pagination1.setPagegination(page, totalPage);
        }

    }

    public void loadTrangThai(int TrangThai) {
        model = (DefaultTableModel) tbHD.getModel();
        model.setRowCount(0);
        List<ViewModelHoadon> hd = hdService.loc(TrangThai);
        for (ViewModelHoadon v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenNV(),
                v.getTenKH(),
                v.getNgayTao(), v.getNgayThanhToan(),
                v.getPhamtramKM(),
                v.getTrangThaiHoaDon()

            });

        }

    }

    public void loadChiTiet(String idHD) {
        List<ViewModelHoaDonChiTietBanHang> hdCt = hdCTService.list(idHD);
        if (hdCt == null) {
            return;
        }
        model = (DefaultTableModel) tbChiTiet.getModel();
        model.setRowCount(0);

        for (ViewModelHoaDonChiTietBanHang v : hdCt) {
            model.addRow(new Object[]{
                v.getTen(),
                v.getSoluong(), v.getDonGia()
            });
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHD = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChiTiet = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdHoaDon = new javax.swing.JTextField();
        txtNVHD = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        txtNgayTaoHD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKH = new javax.swing.JTextField();
        txtSoLuongHD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        rdChuaThanhToan = new javax.swing.JRadioButton();
        rdDaThanhToan = new javax.swing.JRadioButton();
        cbbHD = new javax.swing.JComboBox<>();
        rdHDBH = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        TenSPHD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgayThanhToanHD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPhanTramKM = new javax.swing.JTextField();
        pagination1 = new pagination.Pagination();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setText("ID");

        jLabel2.setText("Mã");

        tbHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã HÐ", "Tên NV", "KH", "Ngày Tạo", "Ngày Thanh Toán", "Phần Trăm KM", "Trạng Thái"
            }
        ));
        tbHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHD);

        tbChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "TEN SP", "SO LUONG", "Thannh Tien"
            }
        ));
        tbChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbChiTiet);

        jLabel3.setText("Trạng Thái");

        jLabel4.setText("Tên Nv");

        jLabel5.setText("Ngày Tạo");

        jLabel6.setText("Tên Sp");

        jLabel7.setText("Số Lượng");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        rdChuaThanhToan.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(rdChuaThanhToan);
        rdChuaThanhToan.setSelected(true);
        rdChuaThanhToan.setText("Chưa Thanh Toán");

        rdDaThanhToan.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(rdDaThanhToan);
        rdDaThanhToan.setText("Đã Thanh Toán");

        cbbHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Chưa Thanh Toán", "Đã Thanh Toán", "Hóa Đơn Bảo Hành" }));
        cbbHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHDActionPerformed(evt);
            }
        });

        rdHDBH.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(rdHDBH);
        rdHDBH.setText("Hóa Đơn BH");
        rdHDBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHDBHActionPerformed(evt);
            }
        });

        jLabel8.setText("Tên KH");

        jLabel9.setText("Ngày Thanh Toán");

        jLabel10.setText("Phần Trăm KM");

        pagination1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtNVHD, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtNgayTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(203, 203, 203)
                                        .addComponent(rdHDBH)
                                        .addGap(66, 66, 66)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7)))
                                        .addGap(38, 38, 38))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPhanTramKM, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNgayThanhToanHD, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdChuaThanhToan)
                                    .addComponent(rdDaThanhToan))
                                .addGap(179, 179, 179)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSoLuongHD, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbHD, 0, 170, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TenSPHD))
                        .addGap(44, 44, 44)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtNgayThanhToanHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhanTramKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cbbHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(1, 1, 1)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(TenSPHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuongHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNVHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdChuaThanhToan)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(rdDaThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(rdHDBH)
                        .addGap(12, 12, 12)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtNVHD.getText().length() > 50) {
            
            JOptionPane.showMessageDialog(this, "Họ và tên không được quá 50 ký tự");
            
            return;
        }
        if (txtNVHD.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Tên nhân viên trống");
            
            return;
            
        }
        if (!txtNVHD.getText().matches("[a-zA-Z\\s]+$")) {
            
            JOptionPane.showMessageDialog(this, "Họ tên phải là chữ, không được chứa số và ký tự đặc biệt");
            
            return;

        }

        if (txtNgayTaoHD.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Ngày tạo trống");
            
            
            return;
            
        }
        if (txtNgayThanhToanHD.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Ngày thanh toán trống");
            
            return;
            
        }
        if (txtPhanTramKM.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Phần trăm KM trống");
            
            return;
            
        }
        if (txtKH.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "KH trống");
            
            return;
            
        }
        if (!txtKH.getText().matches("[a-zA-Z\\s]+$")) {
            
            JOptionPane.showMessageDialog(this, "Họ tên phải là chữ, không được chứa số và ký tự đặc biệt");
            
            return;

        }
        if (!txtKH.getText().matches("[a-zA-Z\\s]+$")) {
            
            JOptionPane.showMessageDialog(this, "Họ tên phải là chữ, không được chứa số và ký tự đặc biệt");
            
            return;
        }
          if (txtKH.getText().length() > 50) {
            
            JOptionPane.showMessageDialog(this, "Họ và tên không được quá 50 ký tự");
            
            return;
        }

        HoaDon hd = new HoaDon();
        NhanVien nv = new NhanVien();
        nv.setId("612B3352-EE22-4CBB-8BE7-6AE5F761A099");
        hd.setIdNhanVien(nv);

        boolean b = hdService.add(hd);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTbHD(1);

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Mã Hóa Đơn", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDMouseClicked
        // TODO add your handling code here:
        int index = tbHD.getSelectedRow();
        txtIdHoaDon.setText((tbHD.getValueAt(index, 0).toString()));
        txtMaHD.setText((tbHD.getValueAt(index, 1).toString()));
        txtNVHD.setText((tbHD.getValueAt(index, 2).toString()));
        txtKH.setText(tbHD.getValueAt(index, 3).toString());
        txtNgayTaoHD.setText((tbHD.getValueAt(index, 4).toString()));
        txtNgayThanhToanHD.setText(tbHD.getValueAt(index, 5).toString());
        txtPhanTramKM.setText((tbHD.getValueAt(index, 6).toString()));
        String trangThai = tbHD.getValueAt(index, 7).toString();
        if (trangThai.equals("Chưa Thanh Toán")) {
            rdChuaThanhToan.setSelected(true);
        } else if (trangThai.equals("Đã Thanh Toán")) {
            rdDaThanhToan.setSelected(true);
        } else {
            rdHDBH.setSelected(true);
        }

        loadChiTiet(txtIdHoaDon.getText());
    }//GEN-LAST:event_tbHDMouseClicked

    private void tbChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietMouseClicked
        // TODO add your handling code here:
        int index = tbChiTiet.getSelectedRow();
        TenSPHD.setText(tbChiTiet.getValueAt(index, 0).toString());
        txtSoLuongHD.setText(tbChiTiet.getValueAt(index, 1).toString());
    }//GEN-LAST:event_tbChiTietMouseClicked

    private void cbbHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHDActionPerformed
        // TODO add your handling code here:
        int index = cbbHD.getSelectedIndex();
        if (index == 0) {
            loadTbHD(1);
        } else if (index == 1) {
            loadTrangThai(1);
        } else if (index == 2) {
            loadTrangThai(2);
        } else {
            loadTrangThai(3);
        }
    }//GEN-LAST:event_cbbHDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?");
        if (xacNhan == JOptionPane.YES_NO_OPTION) {
            boolean b = hdService.delete(txtIdHoaDon.getText());
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Xoa Hóa Đơn Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTbHD(1);

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Trùng Mã Hóa Đơn", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

            }
        } else {
            return;
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        MenuView v = new MenuView(IdNV, TenNV, CV);
        v.setLocationRelativeTo(null);
        v.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rdHDBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHDBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdHDBHActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new HoaDonView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TenSPHD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private pagination.Pagination pagination1;
    private javax.swing.JRadioButton rdChuaThanhToan;
    private javax.swing.JRadioButton rdDaThanhToan;
    private javax.swing.JRadioButton rdHDBH;
    private javax.swing.JTable tbChiTiet;
    private javax.swing.JTable tbHD;
    private javax.swing.JTextField txtIdHoaDon;
    private javax.swing.JTextField txtKH;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNVHD;
    private javax.swing.JTextField txtNgayTaoHD;
    private javax.swing.JTextField txtNgayThanhToanHD;
    private javax.swing.JTextField txtPhanTramKM;
    private javax.swing.JTextField txtSoLuongHD;
    // End of variables declaration//GEN-END:variables
}

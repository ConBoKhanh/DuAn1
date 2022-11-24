/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChucVu;
import domainModels.CuaHang;
import domainModels.NhanVien;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.ChucVuSerivce;
import services.CuaHangService;
import services.NhanVienService;
import services.impl.IManageChucVuService;
import services.impl.IManageCuaHangService;
import services.impl.IManageNhanVienService;
import viewModel.ViewModelChucVu;
import viewModel.ViewModelCuaHang;
import viewModel.ViewModelNhanVien;

/**
 *
 * @author Admin
 */
public class NhanVienViewTest extends javax.swing.JFrame {

    private IManageNhanVienService nhanVienService = new NhanVienService();
    private IManageCuaHangService cuaHangService = new CuaHangService();
    private IManageChucVuService chucVuService = new ChucVuSerivce();

    DefaultTableModel model = new DefaultTableModel();

    public void loadTable() {
        model = (DefaultTableModel) TBBANG.getModel();
        model.setColumnCount(0);
        model.addColumn("Id");
        model.addColumn("Mã");
        model.addColumn("Họ tên");
        model.addColumn("SĐT");
        model.addColumn("Địa chỉ");
        model.addColumn("Ngày sinh");
        model.addColumn("Cửa hàng");
        model.addColumn("Chức vụ");
        model.addColumn("Mật khẩu");
        model.addColumn("Email");

//        model.setRowCount(0);
//        List<ViewModelNhanVien> nv = nhanVienService.getAll();
//        for (ViewModelNhanVien viewModelNhanVien : nv) {
//            model.addRow(new Object[]{
//                viewModelNhanVien.getId(), viewModelNhanVien.getMa(), viewModelNhanVien.getHoTen(),
//                viewModelNhanVien.getSdt(), viewModelNhanVien.getDiaChi(), viewModelNhanVien.getNgaySinh(),
//                viewModelNhanVien.getIdCH(), viewModelNhanVien.getIdCV(), viewModelNhanVien.getMatKhau(), viewModelNhanVien.getEmail()
//            });
//
//        }
    }

    public void loadTableTimKiem(String ten) {
        model = (DefaultTableModel) TBBANG.getModel();
        model.setColumnCount(0);
        model.addColumn("Id");
        model.addColumn("Mã");
        model.addColumn("Họ tên");
        model.addColumn("SĐT");
        model.addColumn("Địa chỉ");
        model.addColumn("Ngày sinh");
        model.addColumn("Cửa hàng");
        model.addColumn("Chức vụ");
        model.addColumn("Mật khẩu");
        model.addColumn("Email");

        model.setRowCount(0);
        List<ViewModelNhanVien> nv = nhanVienService.listtk(ten);
        for (ViewModelNhanVien viewModelNhanVien : nv) {
            model.addRow(new Object[]{
                viewModelNhanVien.getId(), viewModelNhanVien.getMa(), viewModelNhanVien.getHoTen(),
                viewModelNhanVien.getSdt(), viewModelNhanVien.getDiaChi(), viewModelNhanVien.getNgaySinh(),
                viewModelNhanVien.getIdCH(), viewModelNhanVien.getIdCV(), viewModelNhanVien.getMatKhau(), viewModelNhanVien.getEmail()
            });

        }
    }

    public String getCHCBB() {
        String id = null;
        List<ViewModelCuaHang> a = cuaHangService.getAll();
        for (ViewModelCuaHang viewModelCuaHang : a) {
            if (CBCCUAHANG.getSelectedItem().equals(viewModelCuaHang.getTenCuaHang())) {
                id = viewModelCuaHang.getId();
            }

        }
        return id;
    }

    public String getCBBCV() {
        String id = null;
        List<ViewModelChucVu> a = chucVuService.getAll();
        for (ViewModelChucVu viewModelChucVu : a) {
            if (CBCCHUCVU.getSelectedItem().equals(viewModelChucVu.getTen())) {
                id = viewModelChucVu.getId();
            }

        }
        return id;
    }

    public void loadCBB() {
        List<ViewModelCuaHang> a = cuaHangService.getAll();
        for (ViewModelCuaHang viewModelCuaHang : a) {
            CBCCUAHANG.addItem(viewModelCuaHang.getTenCuaHang());

        }

        List<ViewModelChucVu> b = chucVuService.getAll();
        for (ViewModelChucVu viewModelChucVu : b) {
            CBCCHUCVU.addItem(viewModelChucVu.getTen());

        }
    }

    /**
     * Creates new form NhanVienView
     */
    public NhanVienViewTest() {
        initComponents();
        setLocationRelativeTo(null);
        loadTable();
        loadCBB();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TBBANG = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        CBCCUAHANG = new javax.swing.JComboBox<>();
        CBCCHUCVU = new javax.swing.JComboBox<>();
        txttimKiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TXTID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTMA = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TXTSDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TXTDIACHI = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TXTHOTEN = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtEmailNv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtmatKhau = new javax.swing.JTextField();
        ngaySinh = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TBBANG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "HoTen", "sdt", "DiaChi", "NgaySinh", "TenCV", "TenCuaHang"
            }
        ));
        TBBANG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBBANGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBBANG);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 640, 210));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 93, 32));

        jPanel1.add(CBCCUAHANG, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 180, -1));

        jPanel1.add(CBCCHUCVU, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 180, -1));

        txttimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimKiemActionPerformed(evt);
            }
        });
        txttimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txttimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 360, -1));

        btnThem.setBackground(new java.awt.Color(255, 204, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setText("Add");
        btnThem.setBorder(new javax.swing.border.MatteBorder(null));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 92, 31));

        btnXoa.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.setBorder(new javax.swing.border.MatteBorder(null));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 92, 31));

        btnSua.setBackground(new java.awt.Color(255, 204, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Update");
        btnSua.setBorder(new javax.swing.border.MatteBorder(null));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, 92, 31));

        jLabel7.setText("ID");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 70, 23));

        TXTID.setBackground(new java.awt.Color(255, 204, 204));
        TXTID.setActionCommand("<Not Set>");
        TXTID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(TXTID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 140, 29));

        jLabel8.setText("Ma");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 70, 23));

        TXTMA.setBackground(new java.awt.Color(255, 204, 204));
        TXTMA.setActionCommand("<Not Set>");
        TXTMA.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTMAActionPerformed(evt);
            }
        });
        jPanel1.add(TXTMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 140, 29));

        jLabel9.setText("SÐT");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 70, 23));

        TXTSDT.setBackground(new java.awt.Color(255, 204, 204));
        TXTSDT.setActionCommand("<Not Set>");
        TXTSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(TXTSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 140, 29));

        jLabel10.setText("DiaChi");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 70, 23));

        TXTDIACHI.setBackground(new java.awt.Color(255, 204, 204));
        TXTDIACHI.setActionCommand("<Not Set>");
        TXTDIACHI.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTDIACHI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTDIACHIActionPerformed(evt);
            }
        });
        jPanel1.add(TXTDIACHI, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 140, 29));

        jLabel11.setText("HoTen");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 70, 23));

        TXTHOTEN.setBackground(new java.awt.Color(255, 204, 204));
        TXTHOTEN.setActionCommand("<Not Set>");
        TXTHOTEN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTHOTEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTHOTENActionPerformed(evt);
            }
        });
        jPanel1.add(TXTHOTEN, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 140, 29));

        jLabel12.setText("NgaySinh");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 70, 23));

        jLabel13.setText("CuaHang");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 70, 23));

        jLabel14.setText("TenCV");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 70, 23));

        jLabel15.setText("Email");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 70, 23));

        txtEmailNv.setBackground(new java.awt.Color(255, 204, 204));
        txtEmailNv.setActionCommand("<Not Set>");
        txtEmailNv.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtEmailNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailNvActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmailNv, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 140, 29));

        jLabel1.setText("Mật khẩu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, -1, -1));

        txtmatKhau.setBackground(new java.awt.Color(255, 204, 204));
        txtmatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtmatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 140, 30));

        ngaySinh.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(ngaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 140, 30));
        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailNvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailNvActionPerformed

    private void TXTHOTENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTHOTENActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTHOTENActionPerformed

    private void TXTDIACHIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTDIACHIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTDIACHIActionPerformed

    private void TXTMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTMAActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        java.util.Date date = ngaySinh.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(date);
        String id = TXTID.getText();
        String idCH = getCHCBB();
        String idCV = getCBBCV();
        String hoTen = TXTHOTEN.getText();
        String sdt = TXTSDT.getText();
        String diaChi = TXTDIACHI.getText();
        // String ngaySinh = getDate.getDateFormatString();
        String matKhau = txtmatKhau.getText();
        String email = txtEmailNv.getText();

        NhanVien nv = new NhanVien();

        CuaHang ch = new CuaHang();
        ch.setId(idCH);

        ChucVu cv = new ChucVu();
        cv.setId(idCV);

        nv.setId(id);
        nv.setIdCuaHang(ch);
        nv.setIdChucVu(cv);
        nv.setHoTen(hoTen);
        nv.setSdt(sdt);
        nv.setDiaChi(diaChi);
        //nv.setNgaySinh(ngaySinh);
        nv.setNgaySinh(Date.valueOf(now));

        nv.setMatKhau(matKhau);
        nv.setEmail(email);

        boolean b = nhanVienService.update(nv);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTable();
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Sửa thất Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        boolean b = nhanVienService.delete(TXTID.getText());
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTable();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        java.util.Date date = ngaySinh.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(date);

        String idCH = getCHCBB();
        String idCV = getCBBCV();
        String hoTen = TXTHOTEN.getText();
        String sdt = TXTSDT.getText();
        String diaChi = TXTDIACHI.getText();
        // String ngaySinh = getDate.getDateFormatString();
        String matKhau = txtmatKhau.getText();
        String email = txtEmailNv.getText();

        NhanVien nv = new NhanVien();

        CuaHang ch = new CuaHang();
        ch.setId(idCH);

        ChucVu cv = new ChucVu();
        cv.setId(idCV);

        nv.setIdCuaHang(ch);
        nv.setIdChucVu(cv);
        nv.setHoTen(hoTen);
        nv.setSdt(sdt);
        nv.setDiaChi(diaChi);
        //nv.setNgaySinh(ngaySinh);
        nv.setNgaySinh(Date.valueOf(now));

        nv.setMatKhau(matKhau);
        nv.setEmail(email);

        boolean b = nhanVienService.add(nv);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTable();
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txttimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemKeyReleased
        // TODO add your handling code here:
        loadTableTimKiem(txttimKiem.getText());
    }//GEN-LAST:event_txttimKiemKeyReleased

    private void TBBANGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBBANGMouseClicked
        // TODO add your handling code here:

        int index = TBBANG.getSelectedRow();

        String ns = TBBANG.getValueAt(index, 5).toString();
        // Date ngay = Date.valueOf(ngaySinh);
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date result;
        try {
            result = sdt.parse(ns);
            ngaySinh.setDate(result);
            TXTID.setText(TBBANG.getValueAt(index, 0).toString());
            TXTMA.setText(TBBANG.getValueAt(index, 1).toString());
            TXTHOTEN.setText(TBBANG.getValueAt(index, 2).toString());
            TXTSDT.setText(TBBANG.getValueAt(index, 3).toString());
            TXTDIACHI.setText(TBBANG.getValueAt(index, 4).toString());

            CBCCUAHANG.setSelectedItem(TBBANG.getValueAt(index, 6).toString());
            CBCCHUCVU.setSelectedItem(TBBANG.getValueAt(index, 7).toString());
            txtmatKhau.setText(TBBANG.getValueAt(index, 8).toString());
            txtEmailNv.setText(TBBANG.getValueAt(index, 9).toString());

        } catch (ParseException ex) {
            Logger.getLogger(NhanVienViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TBBANGMouseClicked

    private void txttimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimKiemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienViewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienViewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienViewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienViewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienViewTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBCCHUCVU;
    private javax.swing.JComboBox<String> CBCCUAHANG;
    private javax.swing.JTable TBBANG;
    private javax.swing.JTextField TXTDIACHI;
    private javax.swing.JTextField TXTHOTEN;
    private javax.swing.JTextField TXTID;
    private javax.swing.JTextField TXTMA;
    private javax.swing.JTextField TXTSDT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser ngaySinh;
    private javax.swing.JTextField txtEmailNv;
    private javax.swing.JTextField txtmatKhau;
    private javax.swing.JTextField txttimKiem;
    // End of variables declaration//GEN-END:variables
}

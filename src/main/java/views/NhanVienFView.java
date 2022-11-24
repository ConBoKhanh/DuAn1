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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;
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
public class NhanVienFView extends javax.swing.JFrame {

    private IManageNhanVienService nhanVienService = new NhanVienService();
    private IManageCuaHangService cuaHangService = new CuaHangService();
    private IManageChucVuService chucVuService = new ChucVuSerivce();

    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form NhanVienFView
     */
    public NhanVienFView() {
        initComponents();
        setLocationRelativeTo(null);
        loadTable(1);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadTable(page);
            }
        });
        loadCBB();
        loadTBChucVu();
        loadPhanTu1();
        loadTBCuaHang();
        loadPhanTuDAUTIEN();
    }

    public void loadTable(int page) {
        try {
            int limit = 5;
            int count = 0;
            List<ViewModelNhanVien> nv = nhanVienService.getAll((page - 1) * limit, limit);
            if (nv == null) {
                System.out.println("1");
                return;
            }
            count = nhanVienService.getRow((page - 1) * limit, limit);

            model = (DefaultTableModel) TBBANG.getModel();

            model.setRowCount(0);

            for (ViewModelNhanVien viewModelNhanVien : nv) {
                model.addRow(new Object[]{
                    viewModelNhanVien.getId(), viewModelNhanVien.getMa(), viewModelNhanVien.getHoTen(),
                    viewModelNhanVien.getSdt(), viewModelNhanVien.getDiaChi(), viewModelNhanVien.getNgaySinh(),
                    viewModelNhanVien.getIdCH(), viewModelNhanVien.getIdCV(), viewModelNhanVien.getMatKhau(), viewModelNhanVien.getEmail()
                });

            }
            int totalPage = (int) Math.ceil(count / limit);
            if (count / limit == 0) {
                pagination1.setPagegination(page, totalPage);
            } else {

                pagination1.setPagegination(page, totalPage + 1);

            }
        } catch (Exception e) {
        }
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

    public void loadTBChucVu() {
        model = (DefaultTableModel) tbbangcv.getModel();
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.setRowCount(0);
        List<ViewModelChucVu> sp = chucVuService.getAll();
        for (ViewModelChucVu v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTen()
            });
        }
    }

    public void loadTBChucVuTimKiem(String ten) {
        model = (DefaultTableModel) tbbangcv.getModel();
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.setRowCount(0);
        List<ViewModelChucVu> sp = chucVuService.getListSPByName(ten);
        for (ViewModelChucVu v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTen()
            });
        }
    }

    public void loadPhanTu1() {
        List<ViewModelChucVu> sp = chucVuService.getAll();
        if (sp.isEmpty()) {
            return;
        }
        int index = 0;

        txtID.setText(tbbangcv.getValueAt(index, 0).toString());
        txtma.setText(tbbangcv.getValueAt(index, 1).toString());
        txttenChucVu.setText(tbbangcv.getValueAt(index, 2).toString());
    }

    public boolean checkTenCHUCVU() { //check ten sp
        String ten = txttenChucVu.getText();
        List<ViewModelChucVu> sp = chucVuService.getAll();
        for (ViewModelChucVu v : sp) {
            if (ten.equals(v.getTen())) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public void loadTBCuaHang() {
        model = (DefaultTableModel) tbbang.getModel();
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Địa chỉ");
        model.setRowCount(0);
        List<ViewModelCuaHang> sp = cuaHangService.getAll();
        for (ViewModelCuaHang v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenCuaHang(), v.getDiaChi()
            });
        }
    }

    public void loadTBCuaHang(String ten) {
        model = (DefaultTableModel) tbbang.getModel();
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Địa chỉ");
        model.setRowCount(0);
        List<ViewModelCuaHang> sp = cuaHangService.getListSPByName(ten);
        for (ViewModelCuaHang v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenCuaHang(), v.getDiaChi()
            });
        }
    }

    public void loadPhanTuDAUTIEN() {
        List<ViewModelCuaHang> sp = cuaHangService.getAll();
        if (sp.isEmpty()) {
            return;
        }
        int index = 0;

        txtIDCH.setText(tbbang.getValueAt(index, 0).toString());
        txtmaCH.setText(tbbang.getValueAt(index, 1).toString());
        txttenCH.setText(tbbang.getValueAt(index, 2).toString());
        txtdiaChi.setText(tbbang.getValueAt(index, 3).toString());
    }

    public boolean checkTen() { //check ten sp
        String ten = txttenChucVu.getText();
        List<ViewModelCuaHang> sp = cuaHangService.getAll();
        for (ViewModelCuaHang v : sp) {
            if (ten.equals(v.getTenCuaHang())) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBBANG = new javax.swing.JTable();
        CBCCUAHANG = new javax.swing.JComboBox<>();
        CBCCHUCVU = new javax.swing.JComboBox<>();
        txttimKiemnhanvien = new javax.swing.JTextField();
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
        jLabel1 = new javax.swing.JLabel();
        txtmatKhau = new javax.swing.JTextField();
        ngaySinh = new com.toedter.calendar.JDateChooser();
        txtemailNhanVien = new javax.swing.JTextField();
        pagination1 = new pagination.Pagination();
        jPanel4 = new javax.swing.JPanel();
        btnxoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnsua = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbbangcv = new javax.swing.JTable();
        txttimKiemchucvu = new javax.swing.JTextField();
        txttenChucVu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtdiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmaCH = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnxoa1 = new javax.swing.JButton();
        txtIDCH = new javax.swing.JTextField();
        btnsua1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        btnthem1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbbang = new javax.swing.JTable();
        txttimKiemCuaHang = new javax.swing.JTextField();
        txttenCH = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        TBBANG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "HoTen", "sdt", "DiaChi", "NgaySinh", "TenCV", "TenCuaHang", "Email"
            }
        ));
        TBBANG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBBANGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBBANG);

        txttimKiemnhanvien.setForeground(new java.awt.Color(153, 153, 153));
        txttimKiemnhanvien.setText("Nhập tên cần tìm kiếm");
        txttimKiemnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemnhanvienMouseClicked(evt);
            }
        });
        txttimKiemnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimKiemnhanvienActionPerformed(evt);
            }
        });
        txttimKiemnhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemnhanvienKeyReleased(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(255, 204, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setText("Add");
        btnThem.setBorder(new javax.swing.border.MatteBorder(null));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.setBorder(new javax.swing.border.MatteBorder(null));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Update");
        btnSua.setBorder(new javax.swing.border.MatteBorder(null));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel7.setText("ID");

        TXTID.setBackground(new java.awt.Color(255, 204, 204));
        TXTID.setActionCommand("<Not Set>");
        TXTID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Ma");

        TXTMA.setBackground(new java.awt.Color(255, 204, 204));
        TXTMA.setActionCommand("<Not Set>");
        TXTMA.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTMAActionPerformed(evt);
            }
        });

        jLabel9.setText("SÐT");

        TXTSDT.setBackground(new java.awt.Color(255, 204, 204));
        TXTSDT.setActionCommand("<Not Set>");
        TXTSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel10.setText("DiaChi");

        TXTDIACHI.setBackground(new java.awt.Color(255, 204, 204));
        TXTDIACHI.setActionCommand("<Not Set>");
        TXTDIACHI.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTDIACHI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTDIACHIActionPerformed(evt);
            }
        });

        jLabel11.setText("HoTen");

        TXTHOTEN.setBackground(new java.awt.Color(255, 204, 204));
        TXTHOTEN.setActionCommand("<Not Set>");
        TXTHOTEN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTHOTEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTHOTENActionPerformed(evt);
            }
        });

        jLabel12.setText("NgaySinh");

        jLabel13.setText("CuaHang");

        jLabel14.setText("TenCV");

        jLabel15.setText("Email");

        jLabel1.setText("Mật khẩu");

        txtmatKhau.setBackground(new java.awt.Color(255, 204, 204));
        txtmatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        ngaySinh.setDateFormatString("yyyy-MM-dd");

        txtemailNhanVien.setBackground(new java.awt.Color(255, 204, 204));
        txtemailNhanVien.setActionCommand("<Not Set>");
        txtemailNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtemailNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailNhanVienActionPerformed(evt);
            }
        });

        pagination1.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txttimKiemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBCCUAHANG, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTMA, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBCCHUCVU, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)
                                .addComponent(jLabel1)
                                .addGap(191, 191, 191)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtemailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(txtmatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(TXTID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(TXTSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(CBCCUAHANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(CBCCHUCVU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(TXTMA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(TXTDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(TXTHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtmatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtemailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txttimKiemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Nhân Viên", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        jLabel2.setText("ID");

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        tbbangcv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbangcv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangcvMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbbangcv);

        txttimKiemchucvu.setForeground(new java.awt.Color(153, 153, 153));
        txttimKiemchucvu.setText("Nhập tên cần tìm kiếm");
        txttimKiemchucvu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemchucvuMouseClicked(evt);
            }
        });
        txttimKiemchucvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimKiemchucvuActionPerformed(evt);
            }
        });
        txttimKiemchucvu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemchucvuKeyReleased(evt);
            }
        });

        jLabel3.setText("Tên");

        jLabel4.setText("Mã");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txttenChucVu))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtma))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addComponent(txttimKiemchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnthem)
                        .addGap(82, 82, 82)
                        .addComponent(btnsua)
                        .addGap(86, 86, 86)
                        .addComponent(btnxoa)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimKiemchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnxoa))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Chức Vụ", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        txtdiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiaChiActionPerformed(evt);
            }
        });

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Tên");

        jLabel16.setText("Mã");

        btnxoa1.setText("Xóa");
        btnxoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoa1ActionPerformed(evt);
            }
        });

        btnsua1.setText("Sửa");
        btnsua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsua1ActionPerformed(evt);
            }
        });

        jLabel17.setText("ID");

        btnthem1.setText("Thêm");
        btnthem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem1ActionPerformed(evt);
            }
        });

        tbbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbbang);

        txttimKiemCuaHang.setForeground(new java.awt.Color(153, 153, 153));
        txttimKiemCuaHang.setText("Nhập tên cần tìm kiếm");
        txttimKiemCuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemCuaHangMouseClicked(evt);
            }
        });
        txttimKiemCuaHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemCuaHangKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnthem1)
                        .addGap(110, 110, 110)
                        .addComponent(btnsua1)
                        .addGap(88, 88, 88)
                        .addComponent(btnxoa1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtdiaChi))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttenCH))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtmaCH))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIDCH, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(43, 43, 43)
                                .addComponent(txttimKiemCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtIDCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimKiemCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtmaCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttenCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtdiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem1)
                    .addComponent(btnsua1)
                    .addComponent(btnxoa1))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Cửa Hàng", jPanel2);

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            txtemailNhanVien.setText(TBBANG.getValueAt(index, 9).toString().trim());

        } catch (ParseException ex) {
            Logger.getLogger(NhanVienViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TBBANGMouseClicked

    private void txttimKiemnhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemnhanvienKeyReleased
        // TODO add your handling code here:
//           String hoTen = txttimKiemnhanvien.getText();
//        List<ViewModelNhanVien> dg = nhanVienService.getAll();
//        for (ViewModelNhanVien v : dg) {
//            if (hoTen.equals(v.getSdt())) {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy tên cần tìm");
//                return;
//            }
//        }
        loadTableTimKiem(txttimKiemnhanvien.getText());
    }//GEN-LAST:event_txttimKiemnhanvienKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (TXTHOTEN.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Họ và tên không được quá 50 ký tự");
            return;
        }

        if (txtemailNhanVien.getText().length() > 254) {
            JOptionPane.showMessageDialog(this, "Email không được quá 254 ký tự");
            return;
        }

        if (txtdiaChi.getText().length() > 200) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được quá 50 ký tự");
            return;
        }

        if (txtmatKhau.getText().length() < 8 || txtmatKhau.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải ít nhất 8 ký tự và nhiều nhất 15 ký tự");
            return;
        }

        if (!TXTHOTEN.getText().matches("[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Họ tên phải là chữ, không được chứa số và ký tự đặc biệt");
            return;

        }

        if (!txtmatKhau.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15}$")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải chứa 1 ký tự số, 1 ký tự hoa và 1 ký tự đặc biệt");
            return;
        }

        if (!txtemailNhanVien.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dang email");
            return;
        }

//        String dienThoai1 = "^03\\d{8}$";
//        String dienThoai2 = "^05\\d{8}$";
//        String dienThoai3 = "^07\\d{8}$";
//        String dienThoai4 = "^08\\d{8}$";
//        String dienThoai5 = "^09\\d{8}$";
//        
//         if(!TXTSDT.getText().matches(dienThoai1)) {
//             JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại đúng định dạng ");
//             return;
//         }
        //    Validate email không được trùng
//         String ten = txtemailNhanVien.getText();
//        List<ViewModelNhanVien> dg = nhanVienService.getAll();
//        for (ViewModelNhanVien v : dg) {
//            if (ten.equals(v.getEmail())) {
//                JOptionPane.showMessageDialog(this, "Tên email  đã tồn tại!");
//                return;
//            }
//        }
        String dienThoai = TXTSDT.getText();
        List<ViewModelNhanVien> dg = nhanVienService.getAll(2, 5);
        for (ViewModelNhanVien v : dg) {
            if (dienThoai.equals(v.getSdt())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại  đã tồn tại!");
                return;
            }
        }

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
        String email = txtemailNhanVien.getText();

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
            loadTable(1);
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        boolean b = nhanVienService.delete(TXTID.getText());
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTable(1);

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
        String email = txtemailNhanVien.getText();

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
            loadTable(1);
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Sửa thất Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void TXTMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTMAActionPerformed

    private void TXTDIACHIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTDIACHIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTDIACHIActionPerformed

    private void TXTHOTENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTHOTENActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTHOTENActionPerformed

    private void txtemailNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailNhanVienActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:

        ChucVu sp = new ChucVu();
        sp.setId(txtID.getText());
        boolean b = chucVuService.delete(sp);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBChucVu();

        } else {
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:

        if (txttenChucVu.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ không được quá 100 ký tự");
            return;
        }

        if (!txttenChucVu.getText().matches("[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ phải là chữ, không được chứa số và ký tự đặc biệt");
            return;

        }

        ChucVu cv = new ChucVu();
        cv.setTenChucVu(txttenChucVu.getText());

        if (checkTenCHUCVU()) {
            boolean b = chucVuService.add(cv);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBChucVu();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void tbbangcvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangcvMouseClicked
        // TODO add your handling code here:
        int index = tbbangcv.getSelectedRow();

        txtID.setText(tbbangcv.getValueAt(index, 0).toString());
        txtma.setText(tbbangcv.getValueAt(index, 1).toString());
        txttenChucVu.setText(tbbangcv.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tbbangcvMouseClicked

    private void txttimKiemchucvuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemchucvuKeyReleased
        // TODO add your handling code here:
        loadTBChucVuTimKiem(txttimKiemchucvu.getText());
    }//GEN-LAST:event_txttimKiemchucvuKeyReleased

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        ChucVu cv = new ChucVu();
        cv.setId(txtID.getText());
        cv.setTenChucVu(txttenChucVu.getText());

        if (checkTenCHUCVU()) {
            boolean b = chucVuService.update(cv);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBChucVu();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa thất bại", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void txttimKiemchucvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimKiemchucvuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimKiemchucvuActionPerformed

    private void txtdiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiaChiActionPerformed

    private void btnxoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoa1ActionPerformed
        // TODO add your handling code here:
        CuaHang ch = new CuaHang();
        ch.setId(txtIDCH.getText());
        boolean b = cuaHangService.delete(ch);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBCuaHang();

        } else {
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnxoa1ActionPerformed

    private void btnsua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsua1ActionPerformed
        // TODO add your handling code here:

        CuaHang ch = new CuaHang();
        ch.setId(txtIDCH.getText());
        ch.setTenCuaHang(txttenCH.getText());
        ch.setDiaChi(txtdiaChi.getText());

        if (checkTen()) {
            boolean b = cuaHangService.update(ch);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBCuaHang();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa SP thất bại", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnsua1ActionPerformed

    private void btnthem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem1ActionPerformed
        // TODO add your handling code here:

        if (txttenCH.getText().length() > 150) {
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không được quá 150 ký tự");
            return;
        }
        if (txtdiaChi.getText().length() > 250) {
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không được quá 250 ký tự");
            return;
        }

        String tenCH = txttenCH.getText();
        String diaChi = txtdiaChi.getText();
        List<ViewModelCuaHang> dg = cuaHangService.getAll();

        for (ViewModelCuaHang v : dg) {
            if (tenCH.equals(v.getTenCuaHang()) && diaChi.equals(v.getDiaChi())) {
                JOptionPane.showMessageDialog(this, "Tên cửa hàng và địa chỉ này đã tồn tại");
                return;

            }
        }

        CuaHang ch = new CuaHang();

        ch.setTenCuaHang(txttenCH.getText());
        ch.setDiaChi(txtdiaChi.getText());

        boolean b = cuaHangService.add(ch);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBCuaHang();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_btnthem1ActionPerformed

    private void tbbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangMouseClicked
        // TODO add your handling code here:

        int index = tbbang.getSelectedRow();

        txtIDCH.setText(tbbang.getValueAt(index, 0).toString());
        txtmaCH.setText(tbbang.getValueAt(index, 1).toString());
        txttenCH.setText(tbbang.getValueAt(index, 2).toString());
        txtdiaChi.setText(tbbang.getValueAt(index, 3).toString());
    }//GEN-LAST:event_tbbangMouseClicked

    private void txttimKiemCuaHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemCuaHangKeyReleased
        // TODO add your handling code here:

        loadTBCuaHang(txttimKiemCuaHang.getText());
    }//GEN-LAST:event_txttimKiemCuaHangKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuView v = new MenuView("", "", "");
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttimKiemnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemnhanvienMouseClicked
        // TODO add your handling code here:
        txttimKiemnhanvien.setText("");
    }//GEN-LAST:event_txttimKiemnhanvienMouseClicked

    private void txttimKiemchucvuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemchucvuMouseClicked
        // TODO add your handling code here:
        txttimKiemchucvu.setText("");
    }//GEN-LAST:event_txttimKiemchucvuMouseClicked

    private void txttimKiemCuaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemCuaHangMouseClicked
        // TODO add your handling code here:
        txttimKiemCuaHang.setText("");
    }//GEN-LAST:event_txttimKiemCuaHangMouseClicked

    private void txttimKiemnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimKiemnhanvienActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txttimKiemnhanvienActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienFView().setVisible(true);
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
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnsua1;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthem1;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoa1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private com.toedter.calendar.JDateChooser ngaySinh;
    private pagination.Pagination pagination1;
    private javax.swing.JTable tbbang;
    private javax.swing.JTable tbbangcv;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCH;
    private javax.swing.JTextField txtdiaChi;
    private javax.swing.JTextField txtemailNhanVien;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmaCH;
    private javax.swing.JTextField txtmatKhau;
    private javax.swing.JTextField txttenCH;
    private javax.swing.JTextField txttenChucVu;
    private javax.swing.JTextField txttimKiemCuaHang;
    private javax.swing.JTextField txttimKiemchucvu;
    private javax.swing.JTextField txttimKiemnhanvien;
    // End of variables declaration//GEN-END:variables
}

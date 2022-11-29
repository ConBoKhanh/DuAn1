/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChiTietDoGo;
import static java.lang.String.format;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import services.DoanhThuService;
import services.impl.IManageDoanhThu;
import utilities.mycompany.DBConext.HibernatUtil;
import viewModel.ViewModelDoanhThu;
import viewModel.ViewModelDoanhThuThongKe;
import viewModel.ViewModelNhanVienDoanhThu;

/**
 *
 * @author Admin
 */
public class DoanhThuView extends javax.swing.JFrame {

    private DefaultTableModel model;
    private IManageDoanhThu im = new DoanhThuService();

    /**
     * Creates new form DoanhThuView
     */
    public DoanhThuView() {
        initComponents();
        setLocationRelativeTo(null);

        loadTbsanPhamtulondenbe();
        loadTbTOP3();
        laysospdangban();
        laysospsaphet();
        laysosphethang();
        laysospngungkinhdoanh();

        //doanhthu nhanvien
        loadTbNhanVien();
        loadAlldoanhthu();

        //doanhthuthongke
        laydoanhthutheonam();
        laydoanhthutheothang();
        laydoanhthutheongay();
        // laydoanhthuttheochon();

        //so tien bo ra mua sp
        sotienboratheonam();
        sotienboratheothang();
        sotienboratheongay();
//        sotienboratheodachon();

    }

    public void laysospdangban() {
        int sp = im.getListSanPham();
        txtsospdangkd.setText(sp + "");
    }

    public void laysospsaphet() {
        int sp = im.getListGanHet();
        txtsospsaphethang.setText(sp + "");
    }

    public void laysosphethang() {
        int sp = im.getListhethang();
        txtsophanhethang.setText(sp + "");
    }

    public void laysospngungkinhdoanh() {
        int sp = im.getListNgungKinhdoanh();
        txtsospngungkinhdoanh.setText(sp + "");
    }

    public void loadTbsanPhamALL() {
        model = (DefaultTableModel) tbbangsanpham.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThu> hd = im.getList();
        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });
        }
    }

    public void loadTbsanPhamALL1() {
        model = (DefaultTableModel) tbbangsanpham.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThu> hd = im.getList1();
        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });
        }
    }

    public void loadTbsanPhamtubedenlon() {
        model = (DefaultTableModel) tbbangsanpham.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThu> hd = im.getListTUBEDENLON();
        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });
        }
    }

    public void loadTbsanPhamtulondenbe() {
        model = (DefaultTableModel) tbbangsanpham.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThu> hd = im.getListTULONDENBE();
        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });
        }
    }

    public void loadTbTOP3() {
        model = (DefaultTableModel) tbbangsanphamtop3.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThu> hd = im.getListtop3();
        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongban()
            });
        }
    }

    //doanhthunhanvien   
    public void loadTbNhanVien() {
        model = (DefaultTableModel) tbbangnhanhvien.getModel();
        model.setRowCount(0);
        List<ViewModelNhanVienDoanhThu> hd = im.getListNhanVien();
        for (ViewModelNhanVienDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTennv(), v.getChucvu(), v.getHddalam()
            });
        }
    }

    //doanhthuthongke
    public void laydoanhthutheonam() {
        int sp = im.getDoanhthuTheoNam();
        txtdoanhthutheonam.setText(sp + "");
    }

    public void laydoanhthutheothang() {
        int sp = im.getDoanhthutheoThang();
        txtdoanthuthang.setText(sp + "");
    }

    public void laydoanhthutheongay() {
        int sp = im.getDoanhtHUTHEOnGAY();
        txtdoanhthungay.setText(sp + "");
    }

    public void laydoanhthuttheochon() {
        java.util.Date dateBD = dateNgayBatdau.getDate();
        java.util.Date dateKT = DateNgayKetThuc.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nbd = format.format(dateBD);
        String nkt = format.format(dateKT);

        int sp = im.getListTongtiendachon(nbd, nkt);
        txtdoanthudachon.setText(sp + "");
    }

    public void loadTbdoanhthuthongke() {
        java.util.Date dateBD = dateNgayBatdau.getDate();
        java.util.Date dateKT = DateNgayKetThuc.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nbd = format.format(dateBD);
        String nkt = format.format(dateKT);

        model = (DefaultTableModel) tbdongthuthongke.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThuThongKe> hd = im.getListHoaDonDaChon(nbd, nkt);
        for (ViewModelDoanhThuThongKe v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), nbd + "  đến  " + nkt, v.getTongtien()
            });
        }
    }
    
    public void loadAlldoanhthu() {
        model = (DefaultTableModel) tbdongthuthongke.getModel();
        model.setRowCount(0);
        List<ViewModelDoanhThuThongKe> hd = im.getListDoanhthu();
        for (ViewModelDoanhThuThongKe v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(),v.getNgaytt(), v.getTongtien()
            });
        }
    }

    //so tien bo ra mua sp
    public void sotienboratheonam() {
        int sp = im.getsotiennhaptheonam();
        txtsotiennhapnam.setText(sp + "");
    }

    public void sotienboratheothang() {
        int sp = im.getsotiennhaptheothang();
        txtsotiennhaptheothang.setText(sp + "");
    }

    public void sotienboratheongay() {
        int sp = im.getsotiennhaptheoNgay();
        txtsotiennhapngay.setText(sp + "");
    }

    public void sotienboratheodachon() {
        java.util.Date dateBD = dateNgayBatdau.getDate();
        java.util.Date dateKT = DateNgayKetThuc.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nbd = format.format(dateBD);
        String nkt = format.format(dateKT);

        int sp = im.getListTongBoRaDaChon(nbd, nkt);
        txtsotiennhapdachon.setText(sp + "");

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbangsanpham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtsospngungkinhdoanh = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtsospdangkd = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtsophanhethang = new javax.swing.JLabel();
        cbbdoanhthu = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtsospsaphethang = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbbangsanphamtop3 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        a = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtdoanhthutheonam = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtdoanhthungay = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtdoanthudachon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdongthuthongke = new javax.swing.JTable();
        dateNgayBatdau = new com.toedter.calendar.JDateChooser();
        DateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbbangnhanhvien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtdoanthuthang = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtsotiennhapngay = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtsotiennhapnam = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsotiennhapdachon = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtsotiennhaptheothang = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 204));

        jTabbedPane1.setBackground(new java.awt.Color(255, 153, 204));

        jPanel3.setBackground(new java.awt.Color(255, 153, 204));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));

        tbbangsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Idsp", "TenSanPham", "SoLuongTon", "SoLuongBan", "TongTien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbbangsanpham);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Sản Phẩm Đã Ngừng Kinh Doanh");

        txtsospngungkinhdoanh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospngungkinhdoanh.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtsospngungkinhdoanh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtsospngungkinhdoanh, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Số Sản Phẩm Đang Kinh Doanh");

        txtsospdangkd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospdangkd.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtsospdangkd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(txtsospdangkd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số Sản Phẩm hết Hàng");

        txtsophanhethang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsophanhethang.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txtsophanhethang, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(37, 37, 37))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txtsophanhethang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(25, 25, 25))
        );

        cbbdoanhthu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doanh Thu Từ Lớn Đến Bé", "Doanh Thu Từ Bé Đến Lớn" }));
        cbbdoanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbdoanhthuActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số Sản Phẩm Sắp hết Hàng");

        txtsospsaphethang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospsaphethang.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(txtsospsaphethang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(14, 14, 14))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txtsospsaphethang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(20, 20, 20))
        );

        tbbangsanphamtop3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Ten", "SoLuongBan"
            }
        ));
        jScrollPane4.setViewportView(tbbangsanphamtop3);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Top 3 SanPham");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbbdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(cbbdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        jTabbedPane1.addTab("San Pham", jPanel3);

        jButton7.setText("BD tròn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("bd Thang");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("bdNam");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        a.setLayout(new javax.swing.BoxLayout(a, javax.swing.BoxLayout.LINE_AXIS));

        jButton10.setText("BD  cột");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addGap(145, 145, 145)
                .addComponent(jButton8)
                .addGap(59, 59, 59)
                .addComponent(jButton9)
                .addContainerGap(266, Short.MAX_VALUE))
            .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("BD", jPanel6);

        jPanel2.setBackground(new java.awt.Color(255, 153, 204));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Doanh Thu Năm nay");

        txtdoanhthutheonam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanhthutheonam.setText("-");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtdoanhthutheonam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(txtdoanhthutheonam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(19, 19, 19))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 1, 220, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Doanh Thu Hôm Nay");

        txtdoanhthungay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanhthungay.setText("-");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(txtdoanhthungay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(txtdoanhthungay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(19, 19, 19))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 210, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Doanh Thu Đã Chọn");

        txtdoanthudachon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanthudachon.setText("-");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel8))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtdoanthudachon, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(txtdoanthudachon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(15, 15, 15))
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 260, -1));

        tbdongthuthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Hoa Don", "Ma Hoa Don", "Ngay Thanh Toan", "Tong Tien"
            }
        ));
        jScrollPane2.setViewportView(tbdongthuthongke);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 980, 210));

        dateNgayBatdau.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        dateNgayBatdau.setDateFormatString("yyyy-MM-dd");
        dateNgayBatdau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateNgayBatdauKeyReleased(evt);
            }
        });
        jPanel2.add(dateNgayBatdau, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 170, -1));

        DateNgayKetThuc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        DateNgayKetThuc.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(DateNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 170, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Ngày Bắt Đầu");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 97, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Ngày Kết Thúc");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, -1, -1));

        tbbangnhanhvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "Ten", "ChucVu", "sohdht"
            }
        ));
        jScrollPane3.setViewportView(tbbangnhanhvien);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 870, 130));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Top 3 Nhân Viên");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 223, 131, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Doanh Thu Tháng ");

        txtdoanthuthang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanthuthang.setText("-");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel12))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtdoanthuthang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(txtdoanthuthang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(17, 17, 17))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 240, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Tiền nhập trong Ngày");

        txtsotiennhapngay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhapngay.setText("-");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtsotiennhapngay, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel14)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtsotiennhapngay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(19, 19, 19))
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 220, 94));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Tiền nhập trong năm nay");

        txtsotiennhapnam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhapnam.setText("-");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtsotiennhapnam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(txtsotiennhapnam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(13, 13, 13))
        );

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 220, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tiền nhập Ðã Chọn ");

        txtsotiennhapdachon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhapdachon.setText("-");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel18))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtsotiennhapdachon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtsotiennhapdachon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(13, 13, 13))
        );

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 260, 90));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Tiền nhập trong Tháng ");

        txtsotiennhaptheothang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhaptheothang.setText("-");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtsotiennhaptheothang, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(txtsotiennhaptheothang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(19, 19, 19))
        );

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 240, 94));

        txttimkiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttimkiem.setText("Tìm");
        txttimkiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });
        jPanel2.add(txttimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 80, -1));

        jTabbedPane1.addTab("Doanh Thu", jPanel2);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Back");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cbbdoanhthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbdoanhthuActionPerformed
        int index = cbbdoanhthu.getSelectedIndex();
        if (index == 0) {
            loadTbsanPhamtulondenbe();
        } else if (index == 1) {
            loadTbsanPhamtubedenlon();
        }

    }//GEN-LAST:event_cbbdoanhthuActionPerformed

    private void dateNgayBatdauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateNgayBatdauKeyReleased

    }//GEN-LAST:event_dateNgayBatdauKeyReleased

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        for (int i = 0; i < 1000; i++) {
            loadTbdoanhthuthongke();
            laydoanhthuttheochon();
            sotienboratheodachon();
        }
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            session.beginTransaction();
            Query q = session.createNativeQuery("select B.TenSP,Sum(A.SoLuong) from HoaDonChiTiet A , ChiTietDoGo B , HoaDon C\n"
                    + "where A.IdChiTietDoGo = B.Id and A.IdHoaDon = C.Id AND Month(NgayThanhToan) = Month(GETDATE()) AND C.TRANGTHAI >1\n"
                    + "group by B.Id,b.TenSP");
            List<Object[]> list = q.getResultList();
            session.close();
            for (Object[] a : list) {
                dataset.setValue(a[0].toString(), Integer.parseInt(a[1].toString()));
                System.out.println(a.toString());
            }
            for (Object[] a : list) {
                dataset2.setValue(Integer.parseInt(a[1].toString()), a[0].toString(), "");
            }
        } catch (HibernateException hibernateException) {
        }
        //////////////
        try {
            JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                    dataset, true, true,
                    false);
            ///////////////
            JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                    "TenSP", "soluong", dataset2,
                    PlotOrientation.VERTICAL, true,
                    true, false);

            ChartPanel chartPanel = new ChartPanel(chart);// add cái biểu dồ vào jfanel

            a.removeAll();
            a.add(chartPanel);
            a.updateUI();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            session.beginTransaction();
            Query q = session.createNativeQuery("select SUM(A.DonGia) AS'TONG TIEN',COUNT(C.ID)'SO HOA DON',MONTH(C.NgayThanhToan) AS'THANG',COUNT(A.SoLuong) AS 'SL' from HoaDonChiTiet A  ,HoaDon C \n"
                    + "WHERE  A.IdHoaDon = C.Id AND C.TRANGTHAI =2\n"
                    + "GROUP BY  MONTH(C.NgayThanhToan)");
            List<Object[]> THANG = q.getResultList();
            session.close();
            for (Object[] a : THANG) {
                dataset.setValue(a[2].toString(),Integer.parseInt(a[0].toString()));
            }
            for (Object[] a : THANG) {
                dataset2.setValue(Integer.parseInt(a[0].toString()), a[2].toString(), "");
            }
        } catch (HibernateException hibernateException) {
        }
        //////////////
        JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                dataset, true, true,
                false);
        ///////////////
        JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                "Tháng", "soluong", dataset2,
                PlotOrientation.VERTICAL, true,
                true, false);
        //////////////////
//        PiePlot3D p1 = (PiePlot3D) chart.getPlot();
//        CategoryPlot p1 = (CategoryPlot) barChart.getCategoryPlot();

//        p1.setRangeGridlinePaint(Color.ORANGE);

        ChartPanel chartPanel = new ChartPanel(chart);

        a.removeAll();
        a.add(chartPanel);
        a.updateUI();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            session.beginTransaction();
            Query q = session.createNativeQuery("select SUM(A.DonGia) AS'TONG TIEN',COUNT(C.ID)'SO HOA DON',YEAR(C.NgayThanhToan) AS'THANG',COUNT(A.SoLuong) AS 'SL' from HoaDonChiTiet A  ,HoaDon C \n"
                    + "WHERE  A.IdHoaDon = C.Id AND C.TRANGTHAI =2\n"
                    + "GROUP BY  YEAR(C.NgayThanhToan)");
            List<Object[]> THANG = q.getResultList();
            session.close();
            for (Object[] a : THANG) {
                dataset.setValue(a[2].toString() ,Integer.parseInt(a[0].toString()) );
            }
            for (Object[] a : THANG) {
                dataset2.setValue(Integer.parseInt(a[0].toString()), a[0].toString(), "");
            }
        } catch (HibernateException hibernateException) {
        }
        //////////////
        JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                dataset, true, true,
                false);
        ///////////////
        JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                "Năm", "tổng tiền", dataset2,
                PlotOrientation.VERTICAL, true,
                true, false);
        //////////////////

//        CategoryPlot p1 = (CategoryPlot) barChart.getCategoryPlot();

//        p1.setRangeGridlinePaint(Color.ORANGE);

        ChartPanel chartPanel = new ChartPanel(chart);

        a.removeAll();
        a.add(chartPanel);
        a.updateUI();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            session.beginTransaction();
            Query q = session.createNativeQuery("select B.TenSP,Sum(A.SoLuong) from HoaDonChiTiet A , ChiTietDoGo B , HoaDon C\n"
                    + "where A.IdChiTietDoGo = B.Id and A.IdHoaDon = C.Id AND year(NgayThanhToan) = year(GETDATE()) AND C.TRANGTHAI >1\n"
                    + "group by B.Id,b.TenSP");
            List<Object[]> list = q.getResultList();
            session.close();
            for (Object[] a : list) {
                dataset.setValue(a[0].toString(), Integer.parseInt(a[1].toString()));
                System.out.println(a.toString());
            }
            for (Object[] a : list) {
                dataset2.setValue(Integer.parseInt(a[1].toString()), a[0].toString(), "");
            }
        } catch (HibernateException hibernateException) {
        }
        //////////////
        try {
            JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                    dataset, true, true,
                    false);
            ///////////////
            JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                    "TenSP", "soluong", dataset2,
                    PlotOrientation.VERTICAL, true,
                    true, false);

            ChartPanel chartPanel = new ChartPanel(barChart);// add cái biểu dồ vào jfanel

            a.removeAll();
            a.add(chartPanel);
            a.updateUI();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoanhThuView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgayKetThuc;
    private javax.swing.JPanel a;
    private javax.swing.JComboBox<String> cbbdoanhthu;
    private com.toedter.calendar.JDateChooser dateNgayBatdau;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbbangnhanhvien;
    private javax.swing.JTable tbbangsanpham;
    private javax.swing.JTable tbbangsanphamtop3;
    private javax.swing.JTable tbdongthuthongke;
    private javax.swing.JLabel txtdoanhthungay;
    private javax.swing.JLabel txtdoanhthutheonam;
    private javax.swing.JLabel txtdoanthudachon;
    private javax.swing.JLabel txtdoanthuthang;
    private javax.swing.JLabel txtsophanhethang;
    private javax.swing.JLabel txtsospdangkd;
    private javax.swing.JLabel txtsospngungkinhdoanh;
    private javax.swing.JLabel txtsospsaphethang;
    private javax.swing.JLabel txtsotiennhapdachon;
    private javax.swing.JLabel txtsotiennhapnam;
    private javax.swing.JLabel txtsotiennhapngay;
    private javax.swing.JLabel txtsotiennhaptheothang;
    private javax.swing.JButton txttimkiem;
    // End of variables declaration//GEN-END:variables
}

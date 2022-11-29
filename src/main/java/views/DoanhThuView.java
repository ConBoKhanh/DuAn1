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

        //loadTbsanPhamtulondenbe();
        loadTbTOP3();
        laysospdangban();
        laysospsaphet();
        laysosphethang();
        laysospngungkinhdoanh();

        //doanhthu nhanvien
        loadTbNhanVien();

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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtdoanhthutheonam = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtdoanhthungay = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtdoanthudachon = new javax.swing.JTextField();
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
        txtdoanthuthang = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtsotiennhapngay = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtsotiennhapnam = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsotiennhapdachon = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtsotiennhaptheothang = new javax.swing.JTextField();
        txttimkiem = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        a = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
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

        jLabel2.setText("Sản Phẩm Đã Ngừng Kinh Doanh");

        txtsospngungkinhdoanh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospngungkinhdoanh.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
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
                .addContainerGap(18, Short.MAX_VALUE))
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

        jLabel4.setText("Số Sản Phẩm hết Hàng");

        txtsophanhethang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsophanhethang.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txtsophanhethang, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txtsophanhethang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(19, 19, 19))
        );

        cbbdoanhthu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doanh Thu Từ Lớn Đến Bé", "Doanh Thu Từ Bé Đến Lớn" }));
        cbbdoanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbdoanhthuActionPerformed(evt);
            }
        });

        jLabel5.setText("Số Sản Phẩm Sắp hết Hàng");

        txtsospsaphethang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospsaphethang.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(txtsospsaphethang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
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

        jLabel13.setText("Top 3 SanPham");

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbbdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cbbdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("San Pham", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 153, 204));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel6.setText("Doanh Thu Cả Năm");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel6))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtdoanhthutheonam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(txtdoanhthutheonam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(19, 19, 19))
        );

        jLabel7.setText("Doanh Thu Ngày");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdoanhthungay, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(txtdoanhthungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(19, 19, 19))
        );

        jLabel8.setText("Doanh Thu Đã Chọn");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(50, 50, 50))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(txtdoanthudachon, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(txtdoanthudachon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(15, 15, 15))
        );

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

        dateNgayBatdau.setDateFormatString("yyyy-MM-dd");
        dateNgayBatdau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateNgayBatdauKeyReleased(evt);
            }
        });

        DateNgayKetThuc.setDateFormatString("yyyy-MM-dd");

        jLabel9.setText("Ngày Bắt Đầu");

        jLabel10.setText("Ngày Kết Thúc");

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

        jLabel11.setText("Top 3 Nhan Vien Hien Tai");

        jLabel12.setText("Doanh Thu Tháng");

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
                        .addGap(68, 68, 68)
                        .addComponent(txtdoanthuthang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(txtdoanthuthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(17, 17, 17))
        );

        jLabel14.setText("So Tien Nhap Ngay");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel14))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtsotiennhapngay, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(txtsotiennhapngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(19, 19, 19))
        );

        jLabel15.setText("So Tien Nhap Nam");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel15))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtsotiennhapnam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(txtsotiennhapnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(19, 19, 19))
        );

        jLabel18.setText("So TienNhap Ðã Chon");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel18))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtsotiennhapdachon, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtsotiennhapdachon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(19, 19, 19))
        );

        jLabel17.setText("So Tien Nhap Thang");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel17))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtsotiennhaptheothang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(txtsotiennhaptheothang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(19, 19, 19))
        );

        txttimkiem.setText("Tìm");
        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(dateNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(DateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(txttimkiem))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 28, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgayBatdau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel2);

        jButton7.setText("BD");
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

        jButton10.setText("BD");
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
                .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE))
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
                .addContainerGap(250, Short.MAX_VALUE))
            .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("BD", jPanel6);

        jButton1.setText("Back");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadTbsanPhamALL();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        loadTbsanPhamALL1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JTextField txtdoanhthungay;
    private javax.swing.JTextField txtdoanhthutheonam;
    private javax.swing.JTextField txtdoanthudachon;
    private javax.swing.JTextField txtdoanthuthang;
    private javax.swing.JLabel txtsophanhethang;
    private javax.swing.JLabel txtsospdangkd;
    private javax.swing.JLabel txtsospngungkinhdoanh;
    private javax.swing.JLabel txtsospsaphethang;
    private javax.swing.JTextField txtsotiennhapdachon;
    private javax.swing.JTextField txtsotiennhapnam;
    private javax.swing.JTextField txtsotiennhapngay;
    private javax.swing.JTextField txtsotiennhaptheothang;
    private javax.swing.JButton txttimkiem;
    // End of variables declaration//GEN-END:variables
}

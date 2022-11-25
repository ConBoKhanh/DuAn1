/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.KhachHang;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class HoaDonDeBaoHanhRepository {

    public List<HoaDon> getListHD(String id) {
//        try {
        Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
        Query q = session.createQuery("FROM HoaDon A WHERE A.TrangThai = 2 and A.NgayThanhToan >= GETDATE() - 7 and A.IdKhachHang = '" + id + "'"); //Tao cau truy van lay du lieu tu bang dong go
        List<HoaDon> list = q.getResultList();
        return list;
//        } catch (Exception e) {
//            return null;
//        }
    }

    public List<HoaDon> getListHDBH() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery(" FROM HoaDon A WHERE A.TrangThai = 3"); //Tao cau truy van lay du lieu tu bang dong go
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDonChiTiet> getListCTHD(String id) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            javax.persistence.Query q = session.createQuery("from HoaDonChiTiet A where A.IdHoaDon = '" + id + "' ");
            List<HoaDonChiTiet> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    public int getMaxMa() {
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        javax.persistence.Query q = session.createQuery(" select A.Ma From HoaDon A  ");
        List<String> i = q.getResultList();
        if (i.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>();
            for (String a : i) {
                c.add(Integer.parseInt(a));
            }
            System.out.println(c + "\n");

            int max = c.get(0);
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).compareTo(max) > 0) {
                    max = c.get(j);
                }
            }

            return max;
        }
    }

    public boolean addHoadon(HoaDon hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String now = format.format(date);

            HoaDon i = new HoaDon();
            i.setMa(String.valueOf(getMaxMa() + 1)); // mã lớn nhất + thêm 1         
            i.setNgayTao(Date.valueOf(now)); // lấy thời gian ở trên gán vô 
            KhachHang kh = session.get(KhachHang.class, hd.getIdKhachHang().getId());
            i.setIdKhachHang(kh);
            NhanVien nv = session.get(NhanVien.class, hd.getIdNhanVien().getId());
            i.setIdNhanVien(nv);
            i.setTrangThaiHoaDon(0); // 0 là chưa thanh toán - 1 là đã hoàn thành 
            i.setTrangThai(3);// trang thai outo la 1 , 0 là đã xóa , 3 la bao hanh
            i.setThanhTien(new BigDecimal("0"));

            transaction.begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public static void main(String[] args) {
        HoaDonDeBaoHanhRepository a = new HoaDonDeBaoHanhRepository();
        List<HoaDon> list = a.getListHD("789C3304-CE21-4A62-A97A-71B216B6BBCC");
        if (list == null) {
            System.out.println("OKE");
        }
        for (HoaDon hoaDon : list) {
            System.out.println(hoaDon.toString());
        }

//        NhanVien nv = new NhanVien();
//        nv.setId("97D42683-2927-4807-930A-FC948BA31B8F");
//        HoaDon hd = new HoaDon();
//        hd.setIdNhanVien(nv);
//        a.addHoadon(hd);
//        List<HoaDon> list = a.getListHDBH();
//        for (HoaDon hoaDon : list) {
//            System.out.println(hoaDon.toString());
//        }
    }

}

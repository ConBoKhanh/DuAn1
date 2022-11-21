/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
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
 * @author PC
 */
public class HoadonRepository {

    Session session = HibernatUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();

    public List<HoaDon> getList() {
        Transaction transaction = session.getTransaction();
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from HoaDon where TrangThai = 2 or TrangThai = 1 or TrangThai = 3  order by TrangThai asc");
            List<HoaDon> list = q.getResultList();
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
            NhanVien nv = session.get(NhanVien.class, hd.getIdNhanVien().getId());
            i.setIdNhanVien(nv);
            i.setTrangThaiHoaDon(0); // 0 là chưa thanh toán - 1 là đã hoàn thành 
            i.setTrangThai(1);// trang thai outo la 1 , 0 là đã xóa 
            i.setThanhTien(new BigDecimal("0"));

            transaction.begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDon hds = session.get(HoaDon.class, id);
            hds.setTrangThai(0);
            session.getTransaction().begin();
            session.save(hds);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<HoaDon> loc(int Trangthai) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM HoaDon Where TrangThai = :a ");
            q.setParameter("a", Trangthai);
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        HoadonRepository hd = new HoadonRepository();
        List<HoaDon> list = hd.getList();
        for (HoaDon hoaDon : list) {
            System.out.println(hoaDon.toString());
        }
    }
}

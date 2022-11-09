/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.BaoHanh;
import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.KhachHang;
import domainModels.NhaCungCap;
import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class tesst {

    private Session session = HibernatUtil.getFACTORY().openSession();

    public List<HoaDonChiTiet> getList() {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("FROM HoaDonChiTiet");
        List<HoaDonChiTiet> list = q.getResultList();
        return list;
    }
    public List<HoaDon> getListhd() {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("from HoaDon");
        List<HoaDon> list = q.getResultList();
        return list;
        
    }

    public boolean add() {
        Session session = HibernatUtil.getFACTORY().openSession();
        try {
            ChiTietDoGo i = new ChiTietDoGo();

            NhaCungCap ncc = session.get(NhaCungCap.class, "60C89AEF-1A48-42E3-92D9-1D7DD2771FC4");// lay id minh can vi du mau do id 1 
            i.setIdNhaCungCap(ncc);

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addNCC(NhaCungCap go) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            NhaCungCap i = new NhaCungCap();
            i.setTenNCC(go.getTenNCC());
            i.setMa("NCC1");
            i.setDiaChi("Doi 4 hahah");
            i.setSdt("0973394351");
            i.setTrangThai(1);

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addhoadon(HoaDon hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDon i = new HoaDon();
            
            i.setMa(hd.getMa());
            i.setTrangThai(1);
            if (hd.getIdKhachHang() == null) {
            } else {
                KhachHang kh = session.get(KhachHang.class, hd.getIdKhachHang().getId());
                i.setIdKhachHang(kh);
            }

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
        public int getMa() {// lấy mã lớn nhất sau trong 1 chuỗi 
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From HoaDon A  ");
        List<String> i = q.getResultList();
        List<Integer> c = new ArrayList<>();
        for (String a : i) {
            c.add(Integer.parseInt(a));
        }
              System.out.println(c+"\n");
        
        int max = c.get(0);
        for (int j = 0; j < c.size(); j++) {
            if(c.get(j).compareTo(max)>0){
                max = c.get(j);
            }
        }
        
       return max;
    }

    public boolean update() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            NhaCungCap i = session.get(NhaCungCap.class, "60C89AEF-1A48-42E3-92D9-1D7DD2771FC4");
            i.setTenNCC("duy111111");
            i.setMa("NCC1");
            i.setTrangThai(0);

            session.getTransaction().begin();
            session.update(i);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void delete(String a) {
        try {
            NhaCungCap i = session.get(NhaCungCap.class, a);
            session.getTransaction().begin();
            session.delete(i);
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        tesst i = new tesst();
//        HoaDon hd = new HoaDon();
//        hd.setMa("21");
//        KhachHang kh = new KhachHang();
//        kh.setId("8B8EBFFC-CF93-4361-AAF1-4125D53B6FFE");
//        hd.setIdKhachHang(kh);
////        System.out.println(hd.toString());
//        boolean  b = i.addhoadon(hd);
//        if(b== true){
//            System.out.println("ok");
//        }else{
//            System.out.println("not ok");
//        }
//          add hóa đơn 
            int ia = i.getMa();
            System.out.println(ia);
//          lẫy mã lớn nhất 
//    }
//            List<HoaDon> l = i.getListhd();
//            for (HoaDon hoaDon : l) {
//                System.out.println(hoaDon.toString());
//        }
//        i.delete("60C89AEF-1A48-42E3-92D9-1D7DD2771FC4");
//         List<HoaDonChiTiet> list = i.getList();
//         
//         for (HoaDonChiTiet chiTietDoGo : list) {
//             System.out.println(chiTietDoGo.toString());
//    }
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChucVu;
import domainModels.CuaHang;
import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Phuong Bi
 */
public class NhanVienRepository1 {

    public List<NhanVien> getListNVDangLam() {

        try {
            
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM NhanVien where TrangThai = 1");
            List<NhanVien> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> getListNVNghiLam() {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM NhanVien where TrangThai = 2");
            List<NhanVien> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> getAll() {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM NhanVien where TrangThai = 1");
            List<NhanVien> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public int getMaMax() {

        Session se = HibernatUtil.getFACTORY().openSession();
        String maLonNhat = null;
        Query q = se.createQuery("select A.Ma From NhanVien A Where TrangThai = 1");
        List<String> i = q.getResultList(); //Lay list String 
        if (i.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>(); //Convert list String sang List Integer
            for (String a : i) {
                c.add(Integer.parseInt(a));
            }
            System.out.println(c + "\n");

            int max = c.get(0); //Từ list int lấy ra số lớn nhất
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).compareTo(max) > 0) {
                    max = c.get(j);
                }
            }

            return max;
        }
    }

//    public boolean add(NhanVien nv) {
//
//        String ma = String.valueOf(getMaMax() + 1);
//
//        try {
//            Session session = HibernatUtil.getFACTORY().openSession();
//
//            NhanVien v = new NhanVien();
//
//            CuaHang a = session.get(CuaHang.class, nv.getIdCuaHang().getId());
//            ChucVu b = session.get(ChucVu.class, nv.getIdChucVu().getId());
//
//            v.setMa(ma);
//
//            v.setIdCuaHang(a);
//
//            v.setIdChucVu(b);
//
//            v.setHoTen(nv.getHoTen());
//
//            v.setSdt(nv.getSdt());
//
//            v.setDiaChi(nv.getDiaChi());
//
//            v.setNgaySinh(nv.getNgaySinh());
//
//            v.setMatKhau(nv.getMatKhau());
//
//            v.setEmail(nv.getEmail());
//
//            v.setTrangThai(1);
//
//            session.getTransaction().begin();
//
//            session.save(v);
//
//            session.getTransaction().commit();
//
//            session.close();
//
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//
//    }
//    
    
    
    
       public boolean add(NhanVien nv) {

        String ma = String.valueOf(getMaMax() + 1);

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            NhanVien v = new NhanVien();

            CuaHang a = session.get(CuaHang.class, nv.getIdCuaHang().getId());
            ChucVu b = session.get(ChucVu.class, nv.getIdChucVu().getId());

            v.setMa(ma);

            v.setIdCuaHang(a);

            v.setIdChucVu(b);

            v.setHoTen(nv.getHoTen());

            v.setSdt(nv.getSdt());

            v.setDiaChi(nv.getDiaChi());

            v.setNgaySinh(nv.getNgaySinh());

            v.setMatKhau(nv.getMatKhau());

            v.setEmail(nv.getEmail());

            v.setTrangThai(1);

            session.getTransaction().begin();

            session.save(v);

            session.getTransaction().commit();

            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
    
    
    
    

//    public boolean update(NhanVien nv) {
//
//        try {
//            Session session = HibernatUtil.getFACTORY().openSession();
//
//            NhanVien v = session.get(NhanVien.class, nv.getId());
//
//            CuaHang a = session.get(CuaHang.class, nv.getIdCuaHang().getId());
//            ChucVu b = session.get(ChucVu.class, nv.getIdChucVu().getId());
//
//            v.setIdCuaHang(a);
//
//            v.setIdChucVu(b);
//
//            v.setHoTen(nv.getHoTen());
//
//            v.setSdt(nv.getSdt());
//
//            v.setDiaChi(nv.getDiaChi());
//
//            v.setNgaySinh(nv.getNgaySinh());
//
//            v.setMatKhau(nv.getMatKhau());
//
//            v.setEmail(nv.getEmail());
//
//            v.setTrangThai(nv.getTrangThai());
//
//            session.getTransaction().begin();
//            session.save(v);
//            session.getTransaction().commit();
//            session.close();
//
//            return true;
//
//        } catch (Exception e) {
//            return false;
//        }
//
//    }
//    
    
    
    
    
       public boolean update(NhanVien nv) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            NhanVien v = session.get(NhanVien.class, nv.getId());

            CuaHang a = session.get(CuaHang.class, nv.getIdCuaHang().getId());
            ChucVu b = session.get(ChucVu.class, nv.getIdChucVu().getId());

            v.setIdCuaHang(a);

            v.setIdChucVu(b);

            v.setHoTen(nv.getHoTen());

            v.setSdt(nv.getSdt());

            v.setDiaChi(nv.getDiaChi());

            v.setNgaySinh(nv.getNgaySinh());

            v.setMatKhau(nv.getMatKhau());

            v.setEmail(nv.getEmail());

            v.setTrangThai(nv.getTrangThai());

            session.getTransaction().begin();
            session.save(v);
            session.getTransaction().commit();
            session.close();

            return true;

        } catch (Exception e) {
            return false;
        }

    }
    
    
    
    

    public boolean delete(String id) {

        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            NhanVien sp = se.get(NhanVien.class, id);

            sp.setTrangThai(0);

            se.getTransaction().begin();
            se.save(sp);
            se.getTransaction().commit();
            se.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean troLai(String id) {
        String ma = String.valueOf(getMaMax() + 1);
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            NhanVien sp = se.get(NhanVien.class, id);
          // NhanVien sp = new NhanVien();
            sp.setMa(ma);

            sp.setTrangThai(1);

            se.getTransaction().begin();
            se.save(sp);
            se.getTransaction().commit();
            se.close();
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public List<NhanVien> listtk(String ten) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from NhanVien where TrangThai = 1 and HoTen like :ten");
            q.setParameter("ten", "%" + ten + "%");
            List<NhanVien> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> listtkChucVu(String idCV) {

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from NhanVien where TrangThai = 1 and IdChucVu = '" + idCV + "'");
            List<NhanVien> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> listtkCuaHang(String idCH) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from NhanVien A where A.TrangThai = 1 and A.IdCuaHang = '" + idCH + "'");
            List<NhanVien> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }
    
    
    
     public List<Object[]> getAll(int b, int c) {
        

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createNativeQuery("Select A.Id, A.Ma, A.HoTen, A.Sdt, A.DiaChi, A.NgaySinh , A.Email,"
                    + " A.MatKhau,C.TenChucVu, B.TenCuaHang  from NhanVien A  left JOIN CuaHang B "
                    + "on A.IdCuaHang = B.Id "
                    + " left join ChucVu C On A.IdChucVu = C.Id where A.TrangThai = 1 "
                    + "order by Convert(int,A.Ma) desc "
                    + "OFFSET " + b + " ROWS "
                    + "FETCH NEXT " + c + " ROWS ONLY");

            List<Object[]> list = q.getResultList();
            return list;
            
        } catch (HibernateException hibernateException) {
            return null;
        }

    }
     
       public int getRow(int b, int c) {
        
        
        int index = -1;
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createNativeQuery("Select A.Id, A.Ma, A.HoTen, A.Sdt, A.DiaChi, A.NgaySinh , "
                    + " C.TenChucVu, B.TenCuaHang  from NhanVien A  left JOIN CuaHang B  "
                    + " on A.IdCuaHang = B.Id  "
                    + " left join ChucVu C On A.IdChucVu = C.Id "
                    + " order by Convert(int,A.Ma) desc  ");

            List<Object[]> list = q.getResultList();
            index = list.size();
            return index;
            
        } catch (Exception e) {
            return -1;
        }
    }
    
    

    public static void main(String[] args) {
        NhanVienRepository1 nv = new NhanVienRepository1();
        for (NhanVien arg : nv.listtkCuaHang("19CA6E50-4FC2-4281-9584-632113A929F8")) {
            System.out.println(arg.toString());

        }
    }

}

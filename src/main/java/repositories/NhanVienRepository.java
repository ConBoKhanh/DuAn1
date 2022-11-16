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
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Phuong Bi
 */
public class NhanVienRepository {

    public List<NhanVien> getAll() {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createQuery("from NhanVien where TrangThai = 1");
            List<NhanVien> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
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

    public static void main(String[] args) {
        NhanVienRepository nhanVienRepository = new NhanVienRepository();
        NhanVien nv = new NhanVien();
        nv.setId("6D59880C-0882-41F0-9ACD-383748192E26");

//        nv.setHoTen("Nguyen Phuong");
//        nv.setSdt("0987676454");
//        nv.setDiaChi("Hoang Mai");
//       // nv.setNgaySinh("10-10-2002");
//       //nv.setIdCuaHang("");
//       nv.setMatKhau("mmmm");
//       nv.setEmail("ppp@gmail.com");
//       nhanVienRepository.add(nv);
        System.out.println(nhanVienRepository.delete("6D59880C-0882-41F0-9ACD-383748192E26"));

        // System.out.println(nhanVienRepository.getMaMax());
    }
}

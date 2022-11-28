/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repostorycuakien;

import repositories.*;
import domainModels.CuaHang;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Phuong Bi
 */
public class CuaHangRepositorytest {

    public List<CuaHang> getAll() {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createQuery("FROM CuaHang WHERE TrangThai = 1");
            List<CuaHang> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CuaHang> getListSPByName(String ten) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM CuaHang WHERE TrangThai = 1 AND TenCuaHang like :ten ");
            q.setParameter("ten", "%" + ten + "%");
            List<CuaHang> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int getMaxMa() { //Lấy mã lớn nhất + 1 => Tự động tăng
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From CuaHang A Where TrangThai = 1 ");
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

    public boolean add(CuaHang c) {
        String getMa = String.valueOf(getMaxMa() + 1);

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            CuaHang sp = new CuaHang();
            sp.setMa(getMa);
            sp.setTenCuaHang(c.getTenCuaHang());
            sp.setDiaChi(c.getDiaChi());
            sp.setTrangThai(1);

            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(CuaHang c) {
        String getMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            CuaHang sp = session.get(CuaHang.class, c.getId());
            sp.setTenCuaHang(c.getTenCuaHang());
            sp.setDiaChi(c.getDiaChi());
            sp.setTrangThai(1);

            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean delete(CuaHang c) {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            CuaHang sp = se.get(CuaHang.class, c.getId());
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
        CuaHangRepositorytest chr = new CuaHangRepositorytest();
        CuaHang ch = new CuaHang();
        ch.setId("CCCBCD9E-6D95-4572-8BC8-36E8E23BE07A");

        
        chr.delete(ch);

        //System.out.println(chr.add(ch));
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonRepository {

    public List<HoaDonChiTiet> getList(String id) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            Query q = session.createQuery("from HoaDonChiTiet A Where A.IdHoaDon = :idhh");
            q.setParameter("idhh", id);
            List<HoaDonChiTiet> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    public boolean add(HoaDonChiTiet hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();
            i.setIdHoaDon(hd.getIdHoaDon());
            i.setIdChiTietDoGo(hd.getIdChiTietDoGo());
            i.setSoLuong(hd.getSoLuong());
            i.setDonGia(hd.getDonGia());

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean delete(HoaDonChiTiet hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();
            HoaDon a = session.get(HoaDon.class, hd.getIdHoaDon());
            ChiTietDoGo b = session.get(ChiTietDoGo.class, hd.getIdChiTietDoGo());
            session.getTransaction().begin();
            session.delete(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

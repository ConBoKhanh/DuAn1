/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.HoaDon;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.SanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class ChiTietDoGoRepository {

    public List<ChiTietDoGo> list() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from ChiTietDoGo where TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChiTietDoGo> listtk(String ten) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from ChiTietDoGo where TrangThai = 1 and TenSP like :ten");
            q.setParameter("ten", "%" + ten + "%");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean add(ChiTietDoGo dg) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            ChiTietDoGo v = new ChiTietDoGo();

            SanPham a = session.get(SanPham.class, dg.getIdSanPham().getId());
            LoaiSP b = session.get(LoaiSP.class, dg.getIdLoaiSP().getId());
            DongGo c = session.get(DongGo.class, dg.getIdDongGo().getId());
            NhaCungCap d = session.get(NhaCungCap.class, dg.getIdNhaCungCap().getId());
            NguonGoc f = session.get(NguonGoc.class, dg.getIdNguocGoc().getId());
            DonViTinh g = session.get(DonViTinh.class, dg.getIdDonViTinh().getId());
            
            
            v.setIdSanPham(a);
            v.setIdLoaiSP(b);
            v.setIdDongGo(c);
            v.setIdNhaCungCap(d);
            v.setIdNguocGoc(f);
            v.setIdDonViTinh(g);
            v.setTenSP(dg.getTenSP());
            v.setMoTa(dg.getMoTa());
            v.setSoLuong(0);
            v.setGiaNhap(dg.getGiaNhap());
            v.setGiaBan(dg.getGiaBan());
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

    public boolean update(ChiTietDoGo dg) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            ChiTietDoGo v = session.get(ChiTietDoGo.class, dg.getId());

            SanPham a = session.get(SanPham.class, dg.getIdSanPham().getId());
            LoaiSP b = session.get(LoaiSP.class, dg.getIdLoaiSP().getId());
            DongGo c = session.get(DongGo.class, dg.getIdDongGo().getId());
            NhaCungCap d = session.get(NhaCungCap.class, dg.getIdNhaCungCap().getId());
            NguonGoc f = session.get(NguonGoc.class, dg.getIdNguocGoc().getId());
            DonViTinh g = session.get(DonViTinh.class, dg.getIdDonViTinh().getId());

            v.setMoTa(dg.getMoTa());
            v.setSoLuong(0);
            v.setGiaNhap(dg.getGiaNhap());
            v.setGiaBan(dg.getGiaBan());

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
            Session session = HibernatUtil.getFACTORY().openSession();

            ChiTietDoGo v = session.get(ChiTietDoGo.class, id);

            v.setTrangThai(0);

            session.getTransaction().begin();
            session.save(v);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

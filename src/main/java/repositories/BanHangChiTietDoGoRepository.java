/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.HoaDonChiTiet;
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
public class BanHangChiTietDoGoRepository {

    private Session session = HibernatUtil.getFACTORY().openSession();

    public List<ChiTietDoGo> getList() {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("FROM ChiTietDoGo Where TrangThai = 1");
        List<ChiTietDoGo> list = q.getResultList();
        return list;
    }

    public List<ChiTietDoGo> TimKiemTheoId(String Id) {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("FROM ChiTietDoGo Where TrangThai = 1 and Id =: Idsp");
        q.setParameter("Idsp", Id);

        List<ChiTietDoGo> list = q.getResultList();
        return list;
    }

    public List<ChiTietDoGo> TimKiemTen(String Ten) {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("FROM ChiTietDoGo Where TrangThai = 1 and TenSP like :ten ");
        q.setParameter("ten", "%" + Ten + "%");

        List<ChiTietDoGo> list = q.getResultList();
        return list;
    }

    public boolean add(ChiTietDoGo go) {
        try {
            ChiTietDoGo i = new ChiTietDoGo();

            SanPham sp = session.get(SanPham.class, go.getIdSanPham().getId());// lấy id từ thuộc tính 
            LoaiSP loaisp = session.get(LoaiSP.class, go.getIdLoaiSP().getId());
            DongGo donggo = session.get(DongGo.class, go.getIdDongGo().getId());
            NhaCungCap ncc = session.get(NhaCungCap.class, go.getIdNhaCungCap().getId());
            NguonGoc nguongoc = session.get(NguonGoc.class, go.getIdNguocGoc().getId());
            DonViTinh dvtinh = session.get(DonViTinh.class, go.getIdDonViTinh().getId());
// add id các thuộc tính vào          
            i.setIdSanPham(sp);
            i.setIdLoaiSP(loaisp);
            i.setIdDongGo(donggo);
            i.setIdNhaCungCap(ncc);
            i.setIdNguocGoc(nguongoc);
            i.setIdDonViTinh(dvtinh);
// những thuộc tính còn lại
            i.setTenSP(go.getTenSP());
            i.setSoLuong(go.getSoLuong());
            i.setGiaNhap(go.getGiaNhap());
            i.setGiaBan(go.getGiaBan());
            i.setMoTa(go.getMoTa());
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

    public boolean update(ChiTietDoGo go) {
        try {
            ChiTietDoGo i = session.get(ChiTietDoGo.class, go.getId());

            SanPham sp = session.get(SanPham.class, go.getIdSanPham().getId());// lấy id từ thuộc tính 
            LoaiSP loaisp = session.get(LoaiSP.class, go.getIdLoaiSP().getId());
            DongGo donggo = session.get(DongGo.class, go.getIdDongGo().getId());
            NhaCungCap ncc = session.get(NhaCungCap.class, go.getIdNhaCungCap().getId());
            NguonGoc nguongoc = session.get(NguonGoc.class, go.getIdNguocGoc().getId());
            DonViTinh dvtinh = session.get(DonViTinh.class, go.getIdDonViTinh().getId());
// add id các thuộc tính vào          
            i.setIdSanPham(sp);
            i.setIdLoaiSP(loaisp);
            i.setIdDongGo(donggo);
            i.setIdNhaCungCap(ncc);
            i.setIdNguocGoc(nguongoc);
            i.setIdDonViTinh(dvtinh);
// những thuộc tính còn lại
            i.setTenSP(go.getTenSP());
            i.setSoLuong(go.getSoLuong());
            i.setGiaNhap(go.getGiaNhap());
            i.setGiaBan(go.getGiaBan());
            i.setMoTa(go.getMoTa());
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

    public boolean delete(String Id) {
        try {
            ChiTietDoGo i = session.get(ChiTietDoGo.class, Id);
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

    public static void main(String[] args) {
        BanHangChiTietDoGoRepository i = new BanHangChiTietDoGoRepository();
        List<ChiTietDoGo> list = i.TimKiemTen("Hura");
        for (ChiTietDoGo chiTietDoGo : list) {
            System.out.println(chiTietDoGo.toString());
        }
//        ChiTietDoGo a = new ChiTietDoGo();
//        
//        DongGo b = new DongGo();
//        b.setId("5E123722-EFC5-4CBD-BBCB-B8EF019DF8BD");
//        SanPham c = new SanPham();
//        c.setId("B2709151-A03A-4470-9776-CCF15AB22E6C");
//        LoaiSP d = new LoaiSP();
//        d.setId("8EAA5DE2-23E1-4381-BD91-4EDCAC96CAE0");
//        NhaCungCap e = new NhaCungCap();
//        e.setId("CB4049CA-59FF-4A31-8B29-1DD03F06CD88");
//        NguonGoc f = new NguonGoc();
//        f.setId("B57F0A96-6BAC-4B8B-BB11-B54557B48666");
//        DonViTinh g = new DonViTinh();
//        g.setId("D9CC9DCF-AE16-4A67-8639-8136B78CDFD9");
//        
//        a.setId("55777D17-1A48-4210-95BC-3315C1BE5416");
//        a.setIdDongGo(b);
//        a.setIdSanPham(c);
//        a.setIdLoaiSP(d);
//        a.setIdNhaCungCap(e);
//        a.setIdNguocGoc(f);
//        a.setIdDonViTinh(g);
//        a.setSoLuong(12);

//        boolean h = i.delete("55777D17-1A48-4210-95BC-3315C1BE5416");
//        if (h == true) {
//            System.out.println("ok");
//        } else {
//            System.out.println("not ok");
//        }
   }
}

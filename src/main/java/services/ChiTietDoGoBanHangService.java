/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.ChiTietDoGo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.BanHangChiTietDoGoRepository;
import viewModel.ViewModelChiTietSanPhamBanHang;
import services.impl.IManageChiTietDoGoBanHangService;

/**
 *
 * @author Admin
 */
public class ChiTietDoGoBanHangService implements IManageChiTietDoGoBanHangService {

    private BanHangChiTietDoGoRepository i = new BanHangChiTietDoGoRepository();

    @Override
    public List<ViewModelChiTietSanPhamBanHang> getList() {
        List<ChiTietDoGo> list = i.getList();
        List<ViewModelChiTietSanPhamBanHang> listsp = new ArrayList<>();
        try {
            for (ChiTietDoGo h : list) {
                ViewModelChiTietSanPhamBanHang spnew = new ViewModelChiTietSanPhamBanHang(
                        h.getId(), h.getTenSP(), h.getIdSanPham().getTen(),
                        h.getIdLoaiSP().getTenDongSP(), h.getIdDongGo().getTenLoaiGo(),
                        h.getIdNhaCungCap().getTenNCC(), h.getIdNguocGoc().getQuocGia(),
                        h.getIdDonViTinh().getDonViTinh(), h.getSoLuong(), h.getGiaNhap(),
                        h.getGiaBan(), h.getMoTa(), h.getTrangThai());

                listsp.add(spnew);
            }
            return listsp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelChiTietSanPhamBanHang> TimKiemTheoId(String Id) {
        List<ChiTietDoGo> list = i.TimKiemTheoId(Id);
        List<ViewModelChiTietSanPhamBanHang> listsp = new ArrayList<>();
        try {
            for (ChiTietDoGo h : list) {
                ViewModelChiTietSanPhamBanHang spnew = new ViewModelChiTietSanPhamBanHang(
                        h.getId(), h.getTenSP(), h.getIdSanPham().getTen(),
                        h.getIdLoaiSP().getTenDongSP(), h.getIdDongGo().getTenLoaiGo(),
                        h.getIdNhaCungCap().getTenNCC(), h.getIdNguocGoc().getQuocGia(),
                        h.getIdDonViTinh().getDonViTinh(), h.getSoLuong(), h.getGiaNhap(),
                        h.getGiaBan(), h.getMoTa(), h.getTrangThai());

                listsp.add(spnew);
            }
            return listsp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelChiTietSanPhamBanHang> TimKiemTen(String Ten) {
        List<ChiTietDoGo> list = i.TimKiemTen(Ten);
        List<ViewModelChiTietSanPhamBanHang> listsp = new ArrayList<>();
        try {
            for (ChiTietDoGo h : list) {
                ViewModelChiTietSanPhamBanHang spnew = new ViewModelChiTietSanPhamBanHang(
                        h.getId(), h.getTenSP(), h.getIdSanPham().getTen(),
                        h.getIdLoaiSP().getTenDongSP(), h.getIdDongGo().getTenLoaiGo(),
                        h.getIdNhaCungCap().getTenNCC(), h.getIdNguocGoc().getQuocGia(),
                        h.getIdDonViTinh().getDonViTinh(), h.getSoLuong(), h.getGiaNhap(),
                        h.getGiaBan(), h.getMoTa(), h.getTrangThai());

                listsp.add(spnew);
            }
            return listsp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(ChiTietDoGo go) {
        try {
            return add(go);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(ChiTietDoGo go) {
        try {
            return update(go);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String Id) {
        try {
            return delete(Id);
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        ChiTietDoGoBanHangService i = new ChiTietDoGoBanHangService();
         List<ViewModelChiTietSanPhamBanHang> listsp = i.TimKiemTen("Hura");
         for (ViewModelChiTietSanPhamBanHang viewModelChiTietSanPhamBanHang : listsp) {
             System.out.println(viewModelChiTietSanPhamBanHang.toString());
        }
    }

    }

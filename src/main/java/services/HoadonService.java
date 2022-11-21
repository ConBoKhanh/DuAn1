/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDon;
import java.util.ArrayList;
import java.util.List;
import repositories.HoadonRepository;
import services.impl.IManageHoaDonService;
import viewModel.ViewModelHoadon;

/**
 *
 * @author PC
 */
public class HoadonService implements IManageHoaDonService {

    private HoadonRepository hdrp = new HoadonRepository();

    @Override
    public List<ViewModelHoadon> getListHoaDon() {
        List<HoaDon> lhd = hdrp.getList();
        try {
            List<ViewModelHoadon> hoadon = new ArrayList<>();
            for (HoaDon x : lhd) {
                ViewModelHoadon l = new ViewModelHoadon();
                l.setId(x.getId());
                l.setMa(x.getMa());
                l.setNgayTao(x.getNgayTao() + "");
                if (x.getNgayThanhToan() == null) {
                    l.setNgayThanhToan("Chưa Thanh Toán");
                } else {
                    l.setNgayThanhToan(x.getNgayThanhToan() + "");
                }

                if (x.getIdKhuyenMai() == null) {
                    l.setPhamtramKM("Không chọn");
                } else {
                    l.setPhamtramKM(x.getIdKhuyenMai().getPhanTramKM() + "");
                }
                if (x.getIdKhachHang() == null) {
                    l.setTenKH("Không chọn");
                } else {
                    l.setTenKH(x.getIdKhachHang().getTenKhachHang());
                }

                l.setTenNV(x.getIdNhanVien().getHoTen());
                if (x.getTrangThai() == 1) {
                    l.setTrangThaiHoaDon("Chưa Thanh Toán");
                } else if (x.getTrangThai() == 2) {
                    l.setTrangThaiHoaDon("Đã Thanh Toán");
                } else {
                    l.setTrangThaiHoaDon("Hóa Đơn Bảo Hành");
                }

                hoadon.add(l);
            }
            return hoadon;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(HoaDon hd) {
        try {
            return hdrp.addHoadon(hd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            return hdrp.delete(id);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ViewModelHoadon> loc(int Tranngthai) {
        List<HoaDon> lhd = hdrp.loc(Tranngthai);
        try {
            List<ViewModelHoadon> hoadon = new ArrayList<>();
            for (HoaDon x : lhd) {
                ViewModelHoadon l = new ViewModelHoadon();
                l.setId(x.getId());
                l.setMa(x.getMa());
                l.setNgayTao(x.getNgayTao() + "");
                if (x.getNgayThanhToan() == null) {
                    l.setNgayThanhToan("Chưa Thanh Toán");
                } else {
                    l.setNgayThanhToan(x.getNgayThanhToan() + "");
                }

                if (x.getIdKhuyenMai() == null) {
                    l.setPhamtramKM("Không chọn");
                } else {
                    l.setPhamtramKM(x.getIdKhuyenMai().getPhanTramKM() + "");
                }
                if (x.getIdKhachHang() == null) {
                    l.setTenKH("Không chọn");
                } else {
                    l.setTenKH(x.getIdKhachHang().getTenKhachHang());
                }

                l.setTenNV(x.getIdNhanVien().getHoTen());
                if (x.getTrangThai() == 1) {
                    l.setTrangThaiHoaDon("Chưa Thanh Toán");
                } else if (x.getTrangThai() == 2) {
                    l.setTrangThaiHoaDon("Đã Thanh Toán");
                } else {
                    l.setTrangThaiHoaDon("Hóa Đơn Bảo Hành");
                }

                hoadon.add(l);
            }
            return hoadon;
        } catch (Exception e) {
            return null;
        }
    }

}

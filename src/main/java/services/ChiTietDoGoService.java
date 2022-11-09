/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.ChiTietDoGo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.ChiTietDoGoRepository;
import services.impl.IManageChiTietDoGoService;
import viewModel.ChiTietDoGoViewModel;

/**
 *
 * @author Admin
 */
public class ChiTietDoGoService implements IManageChiTietDoGoService {
    
    private ChiTietDoGoRepository a = new ChiTietDoGoRepository();
    
    @Override
    public List<ChiTietDoGoViewModel> list() {
        try {
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.list();
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(sp.getId(), sp.getTenSP(),sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),sp.getIdDonViTinh().getDonViTinh(), sp.getMoTa(),
                        sp.getSoLuong(), sp.getGiaNhap(), sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<ChiTietDoGoViewModel> listtk(String Ten) {
        try {
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.listtk(Ten);
            for (ChiTietDoGo sp : sps) {
                  list.add(new ChiTietDoGoViewModel(sp.getId(), sp.getTenSP(),sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),sp.getIdDonViTinh().getDonViTinh(), sp.getMoTa(),
                        sp.getSoLuong(), sp.getGiaNhap(), sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public boolean add(ChiTietDoGo go) {
        try {
            return a.add(go);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean update(ChiTietDoGo go) {
        try {
            return a.update(go);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean delete(String id) {
        try {
            return a.delete(id);
        } catch (Exception e) {
            return false;
        }
    }
    
}

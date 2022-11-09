/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.ChiTietDoGo;
import java.util.List;
import viewModel.ViewModelChiTietSanPhamBanHang;

/**
 *
 * @author Admin
 */
public interface IManageChiTietDoGoBanHangService {

    List<ViewModelChiTietSanPhamBanHang> getList();

    List<ViewModelChiTietSanPhamBanHang> TimKiemTheoId(String Id);

    List<ViewModelChiTietSanPhamBanHang> TimKiemTen(String Ten);
    
    boolean add(ChiTietDoGo go);
    
    boolean update(ChiTietDoGo go);
    
    boolean delete(String Id);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.ChiTietDoGo;
import java.util.List;
import viewModel.ChiTietDoGoViewModel;

/**
 *
 * @author Admin
 */
public interface IManageChiTietDoGoService {
    
    List<ChiTietDoGoViewModel> phanTrangCTDG(int i, int b);
    
    int row();

    List<ChiTietDoGoViewModel> list();

    List<ChiTietDoGoViewModel> listtk(String Ten);

    boolean add(ChiTietDoGo go);

    boolean update(ChiTietDoGo go);

    boolean delete(String id);

    boolean truSanPham(String id, int soLuong);

    boolean congSanPham(String id, int soLuong);

    boolean updateSLSanPham(String id, int soLuong);
}

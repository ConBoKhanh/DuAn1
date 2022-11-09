/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import java.util.List;
import viewModel.ViewModelHoaDonChiTietBanHang;

/**
 *
 * @author Admin
 */
public interface IManageChiTietHoaDonBanHang {
    List<ViewModelHoaDonChiTietBanHang> list(String id);
}

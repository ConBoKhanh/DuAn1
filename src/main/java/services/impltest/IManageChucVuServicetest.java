/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impltest;

import domainModels.ChucVu;
import java.util.List;
import viewModel.ViewModelChucVu;
import viewModel.test.ViewModelChucVutest;

/**
 *
 * @author PC
 */
public interface IManageChucVuServicetest {
    List<ViewModelChucVutest> getAll();

    List<ViewModelChucVutest> getListSPByName(String ten);

    boolean add(ChucVu c);

    boolean update(ChucVu c);

    boolean delete(ChucVu c);
}

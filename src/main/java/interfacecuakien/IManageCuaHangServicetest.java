/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacecuakien;

import services.impl.*;
import domainModels.CuaHang;
import java.util.List;
import viewModel.ViewModelCuaHang;
import viewmodelcuakien.ViewModelCuaHangtest;

/**
 *
 * @author Phuong Bi
 */
public interface IManageCuaHangServicetest {
      List<ViewModelCuaHangtest> getAll();

    List<ViewModelCuaHangtest> getListSPByName(String ten);

    boolean add(CuaHang c);

    boolean update(CuaHang c);

    boolean delete(CuaHang c);
}

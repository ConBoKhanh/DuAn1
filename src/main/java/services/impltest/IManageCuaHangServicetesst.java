/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impltest;

import domainModels.CuaHang;
import java.util.List;
import viewModel.test.ViewModelCuaHangtesst;

/**
 *
 * @author PC
 */
public interface IManageCuaHangServicetesst {

    List<ViewModelCuaHangtesst> getAll();

    List<ViewModelCuaHangtesst> getListSPByName(String ten);

    boolean add(CuaHang c);

    boolean update(CuaHang c);

    boolean delete(CuaHang c);
}

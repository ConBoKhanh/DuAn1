/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impltest;

import domainModels.NhanVien;
import java.util.List;
import viewModel.test.ViewModelNhanVientest;

/**
 *
 * @author PC
 */
public interface IManageNhanVienServicetest {
    List<ViewModelNhanVientest> getAll(int a, int b);

    int getRow(int b, int c);

    List<ViewModelNhanVientest> listtk(String Ten);
    
    List<ViewModelNhanVientest> getListNV();

    boolean add(NhanVien nv);

    boolean update(NhanVien nv);

    boolean delete(String id);
    
}

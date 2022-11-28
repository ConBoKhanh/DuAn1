/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package kientest;

import services.impl.*;
import domainModels.DonViTinh;
import java.util.List;
import viewModel.ViewModelDonViTinh;
import viewModel.test.ViewModelDonViTinhtest;

/**
 *
 * @author Admin
 */
public interface IManageDonViTinhServicetest {
    List<ViewModelDonViTinhtest> getListDVT();

    List<ViewModelDonViTinhtest> getListDVTByName(String ten);

    boolean add(DonViTinh s);

    boolean update(DonViTinh s);

    boolean delete(DonViTinh s);
}

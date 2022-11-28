/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impltest;

import domainModels.DongGo;
import java.util.List;
import viewModel.test.ViewModelDongGotesst;

/**
 *
 * @author PC
 */
public interface IManageDongGoServicetesst {

    List<ViewModelDongGotesst> getListDongGo();

    List<ViewModelDongGotesst> getDongGoByName(String ten);

    boolean add(DongGo dg);

    boolean update(DongGo dg);

    boolean delete(DongGo dg);
}

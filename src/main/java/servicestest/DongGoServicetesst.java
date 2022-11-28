/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicestest;

import domainModels.DongGo;
import java.util.ArrayList;
import java.util.List;
import repositories.test.DongGoRepositorytesst;
import services.impltest.IManageDongGoServicetesst;
import viewModel.test.ViewModelDongGotesst;

/**
 *
 * @author PC
 */
public class DongGoServicetesst implements IManageDongGoServicetesst{
    private DongGoRepositorytesst i = new DongGoRepositorytesst();

    @Override
    public List<ViewModelDongGotesst> getListDongGo() {
        List<DongGo> dg = i.getListDongGo();
        try {
            List<ViewModelDongGotesst> dongGos = new ArrayList<>();
            for (DongGo d : dg) {
                ViewModelDongGotesst v = new ViewModelDongGotesst();
                v.setId(d.getId());
                v.setMa(d.getMa());
                v.setTenLoaiGo(d.getTenLoaiGo());
                v.setTrangThai(String.valueOf(d.getTrangThai()));
                dongGos.add(v);
            }
            return dongGos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelDongGotesst> getDongGoByName(String ten) {
        List<DongGo> dg = i.getDongGoByName(ten);
        try {
            List<ViewModelDongGotesst> dongGos = new ArrayList<>();
            for (DongGo d : dg) {
                ViewModelDongGotesst v = new ViewModelDongGotesst();
                v.setId(d.getId());
                v.setMa(d.getMa());
                v.setTenLoaiGo(d.getTenLoaiGo());
                v.setTrangThai(String.valueOf(d.getTrangThai()));
                dongGos.add(v);
            }
            return dongGos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(DongGo dg) {
        try {
            return i.add(dg);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(DongGo dg) {
        try {
            return i.update(dg);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(DongGo dg) {
        try {
            return i.delete(dg);
        } catch (Exception e) {
            return false;
        }
    }
}

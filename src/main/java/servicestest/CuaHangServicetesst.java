/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicestest;

import domainModels.CuaHang;
import java.util.ArrayList;
import java.util.List;
import repositories.test.CuaHangRepositorytesst;
import services.impltest.IManageCuaHangServicetesst;
import viewModel.test.ViewModelCuaHangtesst;

/**
 *
 * @author PC
 */
public class CuaHangServicetesst implements IManageCuaHangServicetesst {

    private CuaHangRepositorytesst ch = new CuaHangRepositorytesst();

    @Override
    public List<ViewModelCuaHangtesst> getAll() {

        List<CuaHang> sp = ch.getAll();
        try {
            List<ViewModelCuaHangtesst> chs = new ArrayList<>();
            for (CuaHang x : sp) {
                ViewModelCuaHangtesst v = new ViewModelCuaHangtesst();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setTenCuaHang(x.getTenCuaHang());
                v.setDiaChi(x.getDiaChi());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                chs.add(v);
            }
            return chs;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ViewModelCuaHangtesst> getListSPByName(String ten) {
        List<CuaHang> sp = ch.getListSPByName(ten);
        try {
            List<ViewModelCuaHangtesst> sanPhams = new ArrayList<>();
            for (CuaHang x : sp) {
                ViewModelCuaHangtesst v = new ViewModelCuaHangtesst();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setTenCuaHang(x.getTenCuaHang());
                v.setDiaChi(x.getDiaChi());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(CuaHang c) {
        try {
            return ch.add(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(CuaHang c) {
        try {
            return ch.update(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(CuaHang c) {
        try {

            return ch.delete(c);
        } catch (Exception e) {
            return false;
        }
    }

}

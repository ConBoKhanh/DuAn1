/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.CuaHang;
import java.util.ArrayList;
import java.util.List;
import repositories.CuaHangRepository;
import services.impl.IManageCuaHangService;
import viewModel.ViewModelCuaHang;

/**
 *
 * @author Phuong Bi
 */
public class CuaHangService implements IManageCuaHangService {

    private CuaHangRepository ch = new CuaHangRepository();

    @Override
    public List<ViewModelCuaHang> getAll() {

        List<CuaHang> sp = ch.getAll();
        try {
            List<ViewModelCuaHang> sanPhams = new ArrayList<>();
            for (CuaHang x : sp) {
                ViewModelCuaHang v = new ViewModelCuaHang();
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
    public List<ViewModelCuaHang> getListSPByName(String ten) {
         List<CuaHang> sp = ch.getListSPByName(ten);
        try {
            List<ViewModelCuaHang> sanPhams = new ArrayList<>();
            for (CuaHang x : sp) {
                ViewModelCuaHang v = new ViewModelCuaHang();
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

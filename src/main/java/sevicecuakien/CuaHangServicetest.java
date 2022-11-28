/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sevicecuakien;

import domainModels.CuaHang;
import java.util.ArrayList;
import java.util.List;
import repositories.CuaHangRepository;
import repostorycuakien.CuaHangRepositorytest;
import services.impl.IManageCuaHangService;
import viewModel.ViewModelCuaHang;
import viewmodelcuakien.ViewModelCuaHangtest;

/**
 *
 * @author Phuong Bi
 */
public class CuaHangServicetest implements IManageCuaHangService {

    private CuaHangRepositorytest chr = new CuaHangRepositorytest();

    @Override
    public List<ViewModelCuaHang> getAll() {

        List<CuaHang> sch = chr.getAll();
        try {
            List<ViewModelCuaHangtest> chvm= new ArrayList<>();
            for (CuaHang x : sch) {
                ViewModelCuaHangtest v = new ViewModelCuaHangtest();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setTenCuaHang(x.getTenCuaHang());
                v.setDiaChi(x.getDiaChi());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                chvm.add(v);
                
            }
        
return getAll();
        } catch (Exception e) {
            return null;
        }
    }

    

    @Override
    public List<ViewModelCuaHang> getListSPByName(String ten) {
         List<CuaHang> sp = chr.getListSPByName(ten);
        try {
            List<ViewModelCuaHangtest> chvm = new ArrayList<>();
            for (CuaHang x : sp) {
                ViewModelCuaHangtest v = new ViewModelCuaHangtest();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setTenCuaHang(x.getTenCuaHang());
                v.setDiaChi(x.getDiaChi());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                chvm.add(v);
            }
            return getAll();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(CuaHang c) {
        try {
            return chr.add(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(CuaHang c) {
        try {
            return chr.update(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(CuaHang c) {
        try {
            
            return chr.delete(c);
        } catch (Exception e) {
            return false;
        }
    }

}

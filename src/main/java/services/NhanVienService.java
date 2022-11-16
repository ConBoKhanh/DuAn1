/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import repositories.NhanVienRepository;
import services.impl.IManageNhanVienService;
import viewModel.ViewModelNhanVien;

/**
 *
 * @author Phuong Bi
 */
public class NhanVienService implements IManageNhanVienService{

    private NhanVienRepository nhan = new NhanVienRepository();
    @Override
    public List<ViewModelNhanVien> getAll() {
         try {
            List<ViewModelNhanVien> list = new ArrayList<>();
            List<NhanVien> sps = nhan.getAll();
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien(sp.getId(), sp.getMa(),sp.getHoTen(),
                        sp.getSdt(),
                        sp.getDiaChi(),
                        sp.getNgaySinh(),
                        sp.getIdCuaHang().getTenCuaHang(),sp.getIdChucVu().getTenChucVu(), sp.getMatKhau(),
                        sp.getEmail()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelNhanVien> listtk(String Ten) {
         try {
            List<ViewModelNhanVien> list = new ArrayList<>();
            List<NhanVien> sps = nhan.listtk(Ten);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien(sp.getId(), sp.getMa(),sp.getHoTen(),
                        sp.getSdt(),
                        sp.getDiaChi(),
                        sp.getNgaySinh(),
                        sp.getIdCuaHang().getTenCuaHang(),sp.getIdChucVu().getTenChucVu(), sp.getMatKhau(),
                        sp.getEmail()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(NhanVien nv) {
        try {
            return nhan.add(nv);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(NhanVien nv) {
        try {
            return nhan.update(nv);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            return nhan.delete(id);
        } catch (Exception e) {
            return false;
        }
    }
    
}

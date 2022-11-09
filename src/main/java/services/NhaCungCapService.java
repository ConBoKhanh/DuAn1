/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhaCungCap;
import java.util.ArrayList;
import java.util.List;
import repositories.NhaCungCapRepository;
import services.impl.IManageNhaCungCapService;
import viewModel.ViewModelNhaCungCap;

/**
 *
 * @author Phuong Bi
 */
public class NhaCungCapService implements IManageNhaCungCapService {

    private NhaCungCapRepository nhacc = new NhaCungCapRepository();

    @Override
    public List<ViewModelNhaCungCap> getAll() {
        List<NhaCungCap> ncc = nhacc.getAll();
        try {
            List<ViewModelNhaCungCap> nhacungc = new ArrayList<>();
            for (NhaCungCap n : ncc) {
                ViewModelNhaCungCap v = new ViewModelNhaCungCap();
                v.setId(n.getId());
                v.setMa(n.getMa());
                v.setTenNCC(n.getTenNCC());
                v.setDiaChi(n.getDiaChi());
                v.setSdt(n.getSdt());
                v.setTrangThai(String.valueOf(n.getTrangThai()));
                nhacungc.add(v);

            }
            return nhacungc;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelNhaCungCap> getNhaCungCap(String ten) {
        List<NhaCungCap> ncc = nhacc.getNhaCungCap(ten);
        try {
            List<ViewModelNhaCungCap> nha = new ArrayList<>();
            for (NhaCungCap n : ncc) {
                ViewModelNhaCungCap v = new ViewModelNhaCungCap();
                v.setId(n.getId());
                v.setMa(n.getMa());
                v.setTenNCC(n.getTenNCC());
                v.setDiaChi(n.getDiaChi());
                v.setSdt(n.getSdt());
                v.setTrangThai(String.valueOf(n.getTrangThai()));
                nha.add(v);

            }
            return nha;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(NhaCungCap s) {
        try {
            return nhacc.add(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(NhaCungCap s) {
        try {
            return nhacc.update(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(NhaCungCap s) {
        try {
            return nhacc.delete(s);
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        NhaCungCapService nc = new NhaCungCapService();
        
      
    }

}

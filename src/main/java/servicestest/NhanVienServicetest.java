/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicestest;

import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import repositories.test.NhanVienRepositorytest;
import services.impltest.IManageNhanVienServicetest;
import viewModel.test.ViewModelNhanVientest;

/**
 *
 * @author PC
 */
public class NhanVienServicetest implements IManageNhanVienServicetest {

    private NhanVienRepositorytest nhan = new NhanVienRepositorytest();

    @Override
    public List<ViewModelNhanVientest> getAll(int a, int b) {
        List<ViewModelNhanVientest> list = new ArrayList<>();
        List<Object[]> sps = nhan.getAll(a, b);
        for (Object[] sp : sps) {
            ViewModelNhanVientest v = new ViewModelNhanVientest();
            v.setId(sp[0].toString());
            v.setMa(sp[1].toString());
            v.setHoTen(sp[2].toString());
            v.setSdt(sp[3].toString());
            v.setDiaChi(sp[4].toString());
            v.setNgaySinh(sp[5].toString());
            v.setEmail(sp[6].toString());
            v.setMatKhau(sp[7].toString());

            list.add(v);
        }
        return list;
    }

    @Override
    public int getRow(int b, int c) {
        try {
            return nhan.getRow(b, c);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ViewModelNhanVientest> listtk(String Ten) {
        try {
            List<ViewModelNhanVientest> list = new ArrayList<>();
            List<NhanVien> sps = nhan.listtk(Ten);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVientest(sp.getId(), sp.getMa(), sp.getHoTen(),
                        sp.getSdt(),
                        sp.getDiaChi(),
                        sp.getNgaySinh() + "",
                        sp.getMatKhau(),
                        sp.getEmail()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ViewModelNhanVientest> getListNV() {
        try {
            List<ViewModelNhanVientest> list = new ArrayList<>();
            List<NhanVien> sps = nhan.getListNV();

            for (NhanVien sp : sps) {
                ViewModelNhanVientest x = new ViewModelNhanVientest();
                x.setId(sp.getId());
                x.setMa(sp.getMa());
                x.setHoTen(sp.getHoTen());
                x.setSdt(sp.getSdt());
                x.setDiaChi(sp.getDiaChi());
                x.setNgaySinh(sp.getNgaySinh() + "");
                x.setMatKhau(sp.getMatKhau());
                x.setEmail(sp.getEmail());

                list.add(x);
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

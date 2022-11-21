/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class ViewModelHoadon {
    private String Id;
    private String ma;
    private Date NgayTao;
    private Date NgayThanhToan;
    private int TrangThaiHoaDon;
    private int phamtramKM;
    private String tenKH;
    private String tenNV;

    public ViewModelHoadon() {
    }

    public ViewModelHoadon(String Id, String ma, Date NgayTao, Date NgayThanhToan, int TrangThaiHoaDon, int phamtramKM, String tenKH, String tenNV) {
        this.Id = Id;
        this.ma = ma;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TrangThaiHoaDon = TrangThaiHoaDon;
        this.phamtramKM = phamtramKM;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public int getTrangThaiHoaDon() {
        return TrangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(int TrangThaiHoaDon) {
        this.TrangThaiHoaDon = TrangThaiHoaDon;
    }

    public int getPhamtramKM() {
        return phamtramKM;
    }

    public void setPhamtramKM(int phamtramKM) {
        this.phamtramKM = phamtramKM;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    @Override
    public String toString() {
        return "ViewModelHoadon{" + "Id=" + Id + ", ma=" + ma + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", TrangThaiHoaDon=" + TrangThaiHoaDon + ", phamtramKM=" + phamtramKM + ", tenKH=" + tenKH + ", tenNV=" + tenNV + '}';
    }
    
    
}

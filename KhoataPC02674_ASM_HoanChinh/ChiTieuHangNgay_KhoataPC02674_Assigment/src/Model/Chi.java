/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Chi {
     private String maChi;
    private String msHangMucChi;
    private String tenHangMucChi;
    private int soTien;
    private Date ngayChi;
    private String taiKhoan;
    private String MSVI;
    private String tenVi;

    public String getMaChi() {
        return maChi;
    }

    public void setMaChi(String maChi) {
        this.maChi = maChi;
    }

    public String getMsHangMucChi() {
        return msHangMucChi;
    }

    public void setMsHangMucChi(String msHangMucChi) {
        this.msHangMucChi = msHangMucChi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public Date getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(Date ngayChi) {
        this.ngayChi = ngayChi;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

   

    public String getMSVI() {
        return MSVI;
    }

    public void setMSVI(String MSVI) {
        this.MSVI = MSVI;
    }

    public String getTenHangMucChi() {
        return tenHangMucChi;
    }

    public void setTenHangMucChi(String tenHangMucChi) {
        this.tenHangMucChi = tenHangMucChi;
    }

    public String getTenVi() {
        return tenVi;
    }

    public void setTenVi(String tenVi) {
        this.tenVi = tenVi;
    }
    
    
}

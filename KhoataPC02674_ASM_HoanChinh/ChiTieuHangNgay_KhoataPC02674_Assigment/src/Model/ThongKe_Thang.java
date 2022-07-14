/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class ThongKe_Thang {
    private int Thang;
    private int TongTien;

    public ThongKe_Thang(int Ngay, int TongTien) {
        this.Thang = Ngay;
        this.TongTien = TongTien;
    }

    public ThongKe_Thang() {
        
    }

    public int getNgay() {
        return Thang;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setNgay(int Ngay) {
        this.Thang = Ngay;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
}

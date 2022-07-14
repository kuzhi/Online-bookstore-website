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
public class ThongKe_Ngay {
    private Date Ngay;
    private int TongTien;

    public ThongKe_Ngay(Date Ngay, int TongTien) {
        this.Ngay = Ngay;
        this.TongTien = TongTien;
    }

    public ThongKe_Ngay() {
        
    }

    

    public Date getNgay() {
        return Ngay;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setNgay(Date Ngay) {
        this.Ngay = Ngay;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
    
    
}

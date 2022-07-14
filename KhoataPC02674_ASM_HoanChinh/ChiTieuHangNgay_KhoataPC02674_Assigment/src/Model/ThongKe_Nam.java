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
public class ThongKe_Nam {
    private int Nam;
    private int TongTien;

    public ThongKe_Nam(int Nam, int TongTien) {
        this.Nam = Nam;
        this.TongTien = TongTien;
    }

    public ThongKe_Nam() {
        
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    

   
    
}

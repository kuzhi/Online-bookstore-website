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
public class DanhMucThu {
    private String msHangMucThu;
    private String tenHangMuc;

    public String getMsHangMucThu() {
        return msHangMucThu;
    }

    public void setMsHangMucThu(String msHangMucThu) {
        this.msHangMucThu = msHangMucThu;
    }

    public String getTenHangMuc() {
        return tenHangMuc;
    }

    public void setTenHangMuc(String tenHangMuc) {
        this.tenHangMuc = tenHangMuc;
    }
    
     @Override
    public String toString() {
        return tenHangMuc;
    }
    
    
}

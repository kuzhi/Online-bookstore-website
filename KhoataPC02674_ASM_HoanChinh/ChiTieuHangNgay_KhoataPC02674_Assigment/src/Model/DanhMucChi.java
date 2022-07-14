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
public class DanhMucChi {
    private String msHangMucChi;
    private String tenHangMuc;

    @Override
    public String toString() {
        return tenHangMuc;
    }
    
    
    
    
    public String getMsHangMucChi() {
        return msHangMucChi;
    }

    public void setMsHangMucChi(String msHangMucChi) {
        this.msHangMucChi = msHangMucChi;
    }

    public String getTenHangMuc() {
        return tenHangMuc;
    }

    public void setTenHangMuc(String tenHangMuc) {
        this.tenHangMuc = tenHangMuc;
    }
    
            
}

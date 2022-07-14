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
public class LoaiVi {
    private String msLoaiVi;
    private String tenLoaiVi;

    public String getMsLoaiVi() {
        return msLoaiVi;
    }

    @Override
    public String toString() {
        return tenLoaiVi;
    }
    
    public void setMsLoaiVi(String msLoaiVi) {
        this.msLoaiVi = msLoaiVi;
    }

    public String getTenLoaiVi() {
        return tenLoaiVi;
    }

    public void setTenLoaiVi(String tenLoaiVi) {
        this.tenLoaiVi = tenLoaiVi;
    }
    
    
}

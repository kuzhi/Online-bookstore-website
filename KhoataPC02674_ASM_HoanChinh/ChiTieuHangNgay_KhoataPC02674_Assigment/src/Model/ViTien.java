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
public class ViTien {
    private String MSVi;
    private String tenVi;
    private String msLoaiVi;
    private String tenLoai;
    private int soDu;
    private String hinh;
    private String taiKhoan;
    private boolean tinhTrang;

    @Override
    public String toString() {
        return tenVi;
    }
    
   
    
    public String getMSVi() {
        return MSVi;
    }

    public void setMSVi(String MSVi) {
        this.MSVi = MSVi;
    }

    public String getTenVi() {
        return tenVi;
    }

    public void setTenVi(String tenVi) {
        this.tenVi = tenVi;
    }

    public String getMsLoaiVi() {
        return msLoaiVi;
    }

    public void setMsLoaiVi(String msLoaiVi) {
        this.msLoaiVi = msLoaiVi;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public String toStringTinhTrang(){
        String tinhTrang ="Đóng";
        if(isTinhTrang()==true){
            tinhTrang = "Mở";
        }
        return tinhTrang;
    }
    
    
}

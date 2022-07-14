/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.Chi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiDAO {
     public Chi readFromResultSet(ResultSet rs) throws SQLException{
        Chi model=new Chi();
        model.setMaChi(rs.getString("MaChi"));
        model.setMsHangMucChi(rs.getString("MsHangMucChi"));
        model.setTenHangMucChi(rs.getString("TenHangMuc"));
        model.setSoTien(rs.getInt("SoTien"));
        model.setNgayChi(rs.getDate("Ngay"));
        model.setTaiKhoan(rs.getString("TenTaiKhoan"));
        model.setMSVI(rs.getString("MSVI"));
        model.setTenVi(rs.getString("TenVi"));

        return model;
    }
     
   public Chi readFromResultSet2(ResultSet rs) throws SQLException{
        Chi model=new Chi();
        model.setMaChi(rs.getString("MaChi"));
        model.setMsHangMucChi(rs.getString("MsHangMucChi"));
        model.setSoTien(rs.getInt("SoTien"));
        model.setNgayChi(rs.getDate("Ngay"));
        model.setTaiKhoan(rs.getString("TenTaiKhoan"));
        model.setMSVI(rs.getString("MSVI"));

        return model;
    }
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<Chi> select(String sql,Object...args){
        List<Chi> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    Chi model = readFromResultSet(rs);
                    list.add(model);
                }
                rs.close();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException();
        }
        return list;
    }
    
    public List<Chi> select2(String sql,Object...args){
        List<Chi> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    Chi model = readFromResultSet2(rs);
                    list.add(model);
                }
                rs.close();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException();
        }
        return list;
    }
    /**
     * Thêm mới thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi mới
     */
    public void insert(Chi entity) {
        
       
        String sql="INSERT INTO CHI (MaChi, MsHangMucChi, SoTien, Ngay, TenTaiKhoan, MSVI) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                
            entity.getMaChi(),
            entity.getMsHangMucChi(),
            entity.getSoTien(),
            entity.getNgayChi(),
            entity.getTaiKhoan(),
            entity.getMSVI());
      
    }
      
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(Chi entity) {
     
        String sql="UPDATE  CHI Set MsHangMucChi = ?, SoTien= ?, Ngay= ?, TenTaiKhoan = ?, MSVI=? WHERE MaChi = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getMsHangMucChi(),
                entity.getSoTien(),
                entity.getNgayChi(),
                entity.getTaiKhoan(),
                entity.getMSVI(),
                entity.getMaChi());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param MsHanMuc là mã của bản ghi cần xóa
     */
    public void delete(String MaChi) {
    
        String sql="DELETE FROM CHI WHERE MaChi = ?";
        jdbcHelper.executeUpdate(sql, MaChi);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<Chi> select() {
        String sql="SELECT * FROM CHI";
                    
        return select2(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public Chi findById(String id) {
        String sql="SELECT CHI.MaChi, CHI.MSHangMucChi, DANHMUCCHI.TenHangMuc, CHI.SoTien, CHI.Ngay, CHI.TenTaiKhoan, CHI.MSVI, VITIEN.TenVi from (CHI INNER JOIN DANHMUCCHI ON  CHI.MSHangMucChi = DANHMUCCHI.MSHangMucChi INNER JOIN  VITIEN ON CHI.MSVI = VITIEN.MSVI) "
                + "WHERE CHI.MaChi like ?";
        
        List<Chi> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    
    public List<Chi> selectByKeyword(String keyword) {
        String sql="SELECT CHI.MaChi, CHI.MSHangMucChi, DANHMUCCHI.TenHangMuc, CHI.SoTien, CHI.Ngay, CHI.TenTaiKhoan, CHI.MSVI, VITIEN.TenVi from (CHI INNER JOIN DANHMUCCHI ON  CHI.MSHangMucChi = DANHMUCCHI.MSHangMucChi INNER JOIN  VITIEN ON CHI.MSVI = VITIEN.MSVI) "
                + "WHERE CHI.TenTaiKhoan like ?";
        return select(sql, "%"+keyword+"%");
    }
    
     public int countSizeChi(String keyword){
        return selectByKeyword(keyword).size();
    }
     
    public int countSize(){
        return select().size();
    } 
}

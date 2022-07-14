/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.Thu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThuDAO {
    public Thu readFromResultSet(ResultSet rs) throws SQLException{
        Thu model=new Thu();
        model.setMaThu(rs.getString("MaThu"));
        model.setMsHangMucThu(rs.getString("MsHangMucThu"));
        model.setTenHangMucThu(rs.getString("TenHangMuc"));
        model.setSoTien(rs.getInt("SoTien"));
        model.setNgayThu(rs.getDate("Ngay"));
        model.setTaiKhoan(rs.getString("TenTaiKhoan"));
        model.setMSVI(rs.getString("MSVI"));
        model.setTenVi(rs.getString("TenVi"));
        return model;
    }
    
    public Thu readFromResultSet2(ResultSet rs) throws SQLException{
        Thu model=new Thu();
        model.setMaThu(rs.getString("MaThu"));
        model.setMsHangMucThu(rs.getString("MsHangMucThu"));
        model.setSoTien(rs.getInt("SoTien"));
        model.setNgayThu(rs.getDate("Ngay"));
        model.setMSVI(rs.getString("MSVI"));
        model.setTaiKhoan(rs.getString("TenTaiKhoan"));
        
        return model;
    }
    
    
   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<Thu> select(String sql,Object...args){
        List<Thu> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    Thu model = readFromResultSet(rs);
                    list.add(model);
                }
                rs.close();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException();
        }
        return list;
    }
    
    public List<Thu> select2(String sql,Object...args){
        List<Thu> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    Thu model = readFromResultSet2(rs);
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
    public void insert(Thu entity) {
        
       
        String sql="INSERT INTO THU (MaThu, MsHangMucThu, SoTien, Ngay, TenTaiKhoan, MSVI) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                
            entity.getMaThu(),
            entity.getMsHangMucThu(),
            entity.getSoTien(),
            entity.getNgayThu(),
            entity.getTaiKhoan(),
            entity.getMSVI());
      
    }
      
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(Thu entity) {
     
        String sql="UPDATE  THU Set MsHangMucThu = ?, SoTien= ?, Ngay= ?, TenTaiKhoan = ?, MSVI=? where MaThu = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getMsHangMucThu(),
                entity.getSoTien(),
                entity.getNgayThu(),
                entity.getTaiKhoan(),
                entity.getMSVI(),
                entity.getMaThu());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param MsHanMuc là mã của bản ghi cần xóa
     */
    public void delete(String MaThu) {
    
        String sql="DELETE FROM THU WHERE MaThu = ?";
        jdbcHelper.executeUpdate(sql, MaThu);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
   
    public List<Thu> select(){
        String sql = "SELECT * FROM THU";
        return select2(sql);
    }
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public Thu findById(String id) {
        String sql="select THU.MaThu, THU.MSHangMucThu, DANHMUCTHU.TenHangMuc, THU.SoTien, THU.Ngay, THU.TenTaiKhoan, THU.MSVI, VITIEN.TenVi from (THU INNER JOIN DANHMUCTHU ON  THU.MSHangMucThu = DANHMUCTHU.MSHangMucThu inner join  VITIEN ON THU.MSVI = VITIEN.MSVI)" 
                    +"where THU.MaThu = ?";
        
        List<Thu> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public List<Thu> selectByKeyword(String keyword) {
        String sql="select THU.MaThu, THU.MSHangMucThu, DANHMUCTHU.TenHangMuc, THU.SoTien, THU.Ngay, THU.TenTaiKhoan, THU.MSVI, VITIEN.TenVi from (THU INNER JOIN DANHMUCTHU ON  THU.MSHangMucThu = DANHMUCTHU.MSHangMucThu inner join  VITIEN ON THU.MSVI = VITIEN.MSVI)" 
                    +"where THU.TenTaiKhoan like ?";
        return select(sql, "%"+keyword+"%");
    }
    
     public int countSize(){
        return select().size();
    }
    
     public int countSizeThu(String keyword){
        return selectByKeyword(keyword).size();
     }
}

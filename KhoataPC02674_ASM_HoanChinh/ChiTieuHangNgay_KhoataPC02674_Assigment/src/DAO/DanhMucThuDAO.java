/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.DanhMucThu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DanhMucThuDAO {
      public DanhMucThu readFromResultSet(ResultSet rs) throws SQLException{
        DanhMucThu model=new DanhMucThu();
       model.setMsHangMucThu(rs.getString("MSHangMucThu"));
       model.setTenHangMuc(rs.getString("TenHangMuc"));
      
        return model;
    }

   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<DanhMucThu> select(String sql,Object...args){
        List<DanhMucThu> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    DanhMucThu model = readFromResultSet(rs);
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
    public void insert(DanhMucThu entity) {
        
       
        String sql="INSERT INTO DANHMUCTHU (MSHangMucThu, TenHangMuc) VALUES (?, ?)";
        jdbcHelper.executeUpdate(sql,
                
            entity.getMsHangMucThu(),
            entity.getTenHangMuc());
      
    }

    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(DanhMucThu entity) {
     
        String sql="UPDATE  DANHMUCTHU Set TenHangMuc = ? where MSHangMucThu = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getTenHangMuc(),
                entity.getMsHangMucThu());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param MSVI là mã của bản ghi cần xóa
     */
    public void delete(String MsHangMucThu) {
    
        String sql="DELETE FROM DANHMUCTHU WHERE MSHangMucThu like ?";
        jdbcHelper.executeUpdate(sql, MsHangMucThu);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<DanhMucThu> select() {
        String sql="SELECT * FROM DANHMUCTHU" ;
        return select(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public DanhMucThu findById(String id) {
        String sql="SELECT * FROM DANHMUCTHU "
                + "WHERE MSHangMucThu = ? ";
        List<DanhMucThu> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public int countSize(){
        return select().size();
    }
}

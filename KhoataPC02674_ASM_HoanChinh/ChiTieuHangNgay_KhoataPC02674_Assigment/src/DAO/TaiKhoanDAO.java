/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaiKhoanDAO {
    public TaiKhoan readFromResultSet(ResultSet rs) throws SQLException{
        TaiKhoan model=new TaiKhoan();
       model.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
       model.setMatKhau(rs.getString("MatKhau"));
      
        return model;
    }

   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<TaiKhoan> select(String sql,Object...args){
        List<TaiKhoan> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    TaiKhoan model = readFromResultSet(rs);
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
    public void insert(TaiKhoan entity) {
        
       
        String sql="INSERT INTO TAIKHOAN (TenTaiKhoan, MatKhau) VALUES (?, ?)";
        jdbcHelper.executeUpdate(sql,
                
            entity.getTenTaiKhoan(),
            entity.getMatKhau());
      
    }

    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(TaiKhoan entity) {
     
        String sql="UPDATE  TAIKHOAN Set MatKhau = ? where TenTaiKhoan = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getMatKhau(),
                entity.getTenTaiKhoan());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param TenTaiKhoan là mã của bản ghi cần xóa
     */
    public void delete(String TenTaiKhoan) {
    
        String sql="DELETE FROM TAIKHOAN WHERE TenTaiKhoan = ?";
        jdbcHelper.executeUpdate(sql, TenTaiKhoan);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<TaiKhoan> select() {
        String sql="SELECT * FROM TAIKHOAN" ;
        return select(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public TaiKhoan findById(String id) {
        String sql="SELECT * FROM TAIKHOAN "
                + "WHERE TenTaiKhoan like ? ";
        List<TaiKhoan> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
}

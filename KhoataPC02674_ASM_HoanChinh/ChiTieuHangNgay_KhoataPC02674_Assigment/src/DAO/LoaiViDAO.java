/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.LoaiVi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiViDAO {
    
    public LoaiVi readFromResultSet(ResultSet rs) throws SQLException{
        LoaiVi model=new LoaiVi();
       model.setMsLoaiVi(rs.getString("MSLoaiVi"));
       model.setTenLoaiVi(rs.getString("TenLoai"));
      
        return model;
    }

   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<LoaiVi> select(String sql,Object...args){
        List<LoaiVi> list=new ArrayList<>();
        
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    LoaiVi model = readFromResultSet(rs);
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
    public void insert(LoaiVi entity) {
        
       
        String sql="INSERT INTO LOAIVI (MSLoaiVi, TenLoai) VALUES (?, ?)";
        jdbcHelper.executeUpdate(sql,
                
            entity.getMsLoaiVi(),
            entity.getTenLoaiVi());
      
    }

    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(LoaiVi entity) {
     
        String sql="UPDATE  LOAIVI Set TenLoai = ? where MSLoaiVi = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getTenLoaiVi(),
                entity.getMsLoaiVi());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param MSVI là mã của bản ghi cần xóa
     */
    public void delete(String MSLoaiVi) {
    
        String sql="DELETE FROM LOAIVI WHERE MSLoaiVi = ?";
        jdbcHelper.executeUpdate(sql, MSLoaiVi);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<LoaiVi> select() {
        String sql="SELECT * FROM LOAIVI" ;
        return select(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public LoaiVi findById(String id) {
        String sql="SELECT * FROM LOAIVI "
                + "WHERE MSLoaiVi = ? ";
        List<LoaiVi> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public LoaiVi findByIdTVi(String id) {
        String sql="SELECT * FROM LOAIVI "
                + "WHERE TenVi = ? ";
        List<LoaiVi> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public int countSize(){
        return select().size();
    }
    
}

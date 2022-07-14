/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.DanhMucChi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DanhMucChiDAO {
    public DanhMucChi readFromResultSet(ResultSet rs) throws SQLException{
        DanhMucChi model=new DanhMucChi();
       model.setMsHangMucChi(rs.getString("MSHangMucChi"));
       model.setTenHangMuc(rs.getString("TenHangMuc"));
      
        return model;
    }

   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<DanhMucChi> select(String sql,Object...args){
        List<DanhMucChi> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    DanhMucChi model = readFromResultSet(rs);
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
    public void insert(DanhMucChi entity) {
        
       
        String sql="INSERT INTO DANHMUCCHI (MSHangMucChi, TenHangMuc) VALUES (?, ?)";
        jdbcHelper.executeUpdate(sql,
                
            entity.getMsHangMucChi(),
            entity.getTenHangMuc());
      
    }

    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(DanhMucChi entity) {
     
        String sql="UPDATE  DANHMUCCHI Set TenHangMuc = ? where MSHangMucChi = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getTenHangMuc(),
                entity.getMsHangMucChi());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param MSVI là mã của bản ghi cần xóa
     */
    public void delete(String MsHangMucChi) {
    
        String sql="DELETE FROM DANHMUCCHI WHERE MSHangMucChi like ?";
        jdbcHelper.executeUpdate(sql, MsHangMucChi);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<DanhMucChi> select() {
        String sql="SELECT * FROM DANHMUCCHI" ;
        return select(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public DanhMucChi findById(String id) {
        String sql="SELECT * FROM DANHMUCCHI "
                + "WHERE MSHangMucChi = ? ";
        List<DanhMucChi> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public int countSize(){
        return select().size();
    }
}

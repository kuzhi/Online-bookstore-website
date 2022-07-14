/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.ViTien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ViTienDAO {
    public ViTien readFromResultSet(ResultSet rs) throws SQLException{
        ViTien model=new ViTien();
       model.setMSVi(rs.getString("MSVI"));
       model.setTenVi(rs.getString("TenVi"));
       model.setMsLoaiVi(rs.getString("MSLoaiVi"));
       model.setTenLoai(rs.getString("TenLoai"));
       model.setSoDu(rs.getInt("SoDu"));
       model.setHinh(rs.getString("Hinh"));
       model.setTaiKhoan(rs.getString("TenTaiKhoan"));
       model.setTinhTrang(rs.getBoolean("TinhTrang"));
        return model;
    }

   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<ViTien> select(String sql,Object...args){
        List<ViTien> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    ViTien model = readFromResultSet(rs);
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
    public void insert(ViTien entity) {
        
       
        String sql="INSERT INTO VITIEN (MSVI, TenVi, MSLoaiVi, SoDu, hinh, TenTaiKhoan, TinhTrang) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMSVi(),
                entity.getTenVi(),
                entity.getMsLoaiVi(),
                entity.getSoDu(),
                entity.getHinh(),
                entity.getTaiKhoan(),
                entity.isTinhTrang());
        
      
    }

    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(ViTien entity) {
     
        String sql="UPDATE  VITIEN Set TenVi = ?, MSLoaiVi = ?, SoDu = ?, hinh = ?, TenTaiKhoan = ?, TinhTrang = ? where MSVI = ? ";
        jdbcHelper.executeUpdate(sql,
                
                entity.getTenVi(),
                entity.getMsLoaiVi(),
                entity.getSoDu(),
                entity.getHinh(),
                entity.getTaiKhoan(),
                entity.isTinhTrang(),
                entity.getMSVi()
        );
    }
    
    public void updateTien(int tongTien, String msVi) {
     
        String sql="UPDATE  VITIEN Set SoDu = ? where MSVI = ? ";
        jdbcHelper.executeUpdate(sql,
                
                tongTien,
                msVi
                
        );
    }
    
    
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param MSVI là mã của bản ghi cần xóa
     */
    public void delete(String MSVI) {
    
        String sql="DELETE FROM VITIEN WHERE MSVI = ?";
        jdbcHelper.executeUpdate(sql, MSVI);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<ViTien> select() {
        String sql="select VITIEN.MSVI, VITIEN.TenVi, VITIEN.MSLoaiVi, LOAIVI.TenLoai, VITIEN.SoDu, VITIEN.Hinh,VITIEN.TenTaiKhoan, VITIEN.TinhTrang from VITIEN , LOAIVI where VITIEN.MSLoaiVi = LOAIVI.MSLoaiVi" ;
        return select(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public ViTien findById(String id) {
        String sql="select VITIEN.MSVI, VITIEN.TenVi, VITIEN.MSLoaiVi, LOAIVI.TenLoai, VITIEN.SoDu, VITIEN.Hinh,VITIEN.TenTaiKhoan, VITIEN.TinhTrang from VITIEN , LOAIVI "
                + "WHERE VITIEN.MSLoaiVi = LOAIVI.MSLoaiVi and VITIEN.MSVI = ? ";
        List<ViTien> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public List<ViTien> selectByKeyword(String keyword) {
        String sql="select VITIEN.MSVI, VITIEN.TenVi, VITIEN.MSLoaiVi, LOAIVI.TenLoai, VITIEN.SoDu, VITIEN.Hinh,VITIEN.TenTaiKhoan, VITIEN.TinhTrang from VITIEN , LOAIVI "
                + "WHERE VITIEN.MSLoaiVi = LOAIVI.MSLoaiVi and TenTaiKhoan like ? ";
        return select(sql, "%"+keyword+"%");
    }
    
    public int countSize(String keyword){
        return selectByKeyword(keyword).size();
     }
    
    public int countSize2(){
        return select().size();
     }
}

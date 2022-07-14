/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Model.NguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NguoiDungDAO {
   
    public NguoiDung readFromResultSet(ResultSet rs) throws SQLException{
        NguoiDung model=new NguoiDung();
        model.setMSND(rs.getString("MSND"));
        model.setTenHienThi(rs.getString("TenHienThi"));
        model.setHoTen(rs.getString("HoTen"));
        model.setSdt(rs.getString("SDT"));
        model.setEmail(rs.getString("Email"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setGioiTinh(rs.getInt("GioiTinh"));
        model.setNamSinh(rs.getDate("NamSinh"));
        model.setHinh(rs.getString("Hinh"));
        model.setTaiKhoan(rs.getString("TenTaiKhoan"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }
    
   
    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<NguoiDung> select(String sql,Object...args){
        List<NguoiDung> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            
                rs=jdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    NguoiDung model = readFromResultSet(rs);
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
    public void insert(NguoiDung entity) {
        boolean error = false;
        String sql_1 = "INSERT INTO TaiKhoan (TenTaiKhoan, MatKhau) VALUES (?, ?)";
        jdbcHelper.executeUpdate(sql_1, 
                entity.getTaiKhoan(),
                entity.getMatKhau()
                );
        if(entity.isVaiTro()){
            error = true;
        }
        
        
        
        String sql_2="INSERT INTO NguoiDung (MSND, TenHienThi, HoTen, SDT, Email, DiaChi, GioiTinh, NamSinh, Hinh, TenTaiKhoan, VaiTro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql_2,
                entity.getMSND(),
                entity.getTenHienThi(),
                entity.getHoTen(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getGioiTinh(),
                entity.getNamSinh(),
                entity.getHinh(),
                entity.getTaiKhoan(),
                entity.isVaiTro()
        );
        
      
    }

    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(NguoiDung entity) {
     
        
          String sql_1 = "UPDATE  TaiKhoan  SET MatKhau= ? WHERE TenTaiKhoan = ?";
        jdbcHelper.executeUpdate(sql_1, 
                entity.getMatKhau(),
                entity.getTaiKhoan()
                );
        
        
        String sql_2="UPDATE  NguoiDung Set  TenHienThi = ?, HoTen = ?, SDT = ?, Email = ?, DiaChi = ?, GioiTinh = ?, NamSinh = ?, Hinh = ?, TenTaiKhoan = ?, VaiTro = ? where MSND = ? ";
        jdbcHelper.executeUpdate(sql_2,
                
                entity.getTenHienThi(),
                entity.getHoTen(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getGioiTinh(),
                entity.getNamSinh(),
                entity.getHinh(),
                entity.getTaiKhoan(),
                entity.isVaiTro(),
                entity.getMSND()
                
        );
    }

    /**
     * Xóa bản ghi khỏi CSDL
     * @param MSND là mã của bản ghi cần xóa
     */
    public void delete(String MSND) {
    
        String sql="DELETE FROM NGUOIDUNG WHERE MSND = ?";
        jdbcHelper.executeUpdate(sql, MSND);
    }

    /**
     * Truy vấn tất cả các các thực thể
     * @return danh sách các thực thể
     */
    public List<NguoiDung> select() {
        String sql="SELECT NGUOIDUNG.MSND, NGUOIDUNG.TenHienThi, NGUOIDUNG.HoTen,NGUOIDUNG.SDT, NGUOIDUNG.Email, NGUOIDUNG.DiaChi, NGUOIDUNG.GioiTinh, NGUOIDUNG.NamSinh, NGUOIDUNG.Hinh, NGUOIDUNG.TenTaiKhoan, TAIKHOAN.MatKhau, NGUOIDUNG.VaiTro FROM NGUOIDUNG , TAIKHOAN  WHERE NGUOIDUNG.TenTaiKhoan = TAIKHOAN.TenTaiKhoan";
        return select(sql);             //trong 1 class có thể có 2 method trùng tên (nhưng param khác nhau)
    }

    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public NguoiDung findById(String id) {
        String sql="SELECT NGUOIDUNG.MSND, NGUOIDUNG.TenHienThi, NGUOIDUNG.HoTen, NGUOIDUNG.SDT, NGUOIDUNG.Email, NGUOIDUNG.DiaChi, NGUOIDUNG.GioiTinh, NGUOIDUNG.NamSinh, NGUOIDUNG.Hinh, NGUOIDUNG.TenTaiKhoan, TAIKHOAN.MatKhau, NGUOIDUNG.VaiTro FROM NGUOIDUNG, TAIKHOAN "
                + "WHERE NGUOIDUNG.TenTaiKhoan = TAIKHOAN.TenTaiKhoan and NGUOIDUNG.MSND = ?";
        List<NguoiDung> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    public NguoiDung findByIdTK(String id) {
        String sql="SELECT NGUOIDUNG.MSND, NGUOIDUNG.TenHienThi, NGUOIDUNG.HoTen, NGUOIDUNG.SDT, NGUOIDUNG.Email, NGUOIDUNG.DiaChi, NGUOIDUNG.GioiTinh, NGUOIDUNG.NamSinh, NGUOIDUNG.Hinh, NGUOIDUNG.TenTaiKhoan, TAIKHOAN.MatKhau, NGUOIDUNG.VaiTro FROM NGUOIDUNG, TAIKHOAN "
                + "WHERE NGUOIDUNG.TenTaiKhoan = TAIKHOAN.TenTaiKhoan and NGUOIDUNG.TenTaiKhoan like ?";
        List<NguoiDung> list=select(sql, id);
        return list.size()>0?list.get(0):null;               //có thể trả về là null
    }
    
    
    
    public int countSize(){
        return select().size();
    }
    
    
    
    public List<NguoiDung> selectByKeywordSDT(String keyword) {
        String sql="SELECT NGUOIDUNG.MSND, NGUOIDUNG.TenHienThi, NGUOIDUNG.HoTen, NGUOIDUNG.SDT, NGUOIDUNG.Email, NGUOIDUNG.DiaChi, NGUOIDUNG.GioiTinh, NGUOIDUNG.NamSinh, NGUOIDUNG.Hinh, NGUOIDUNG.TenTaiKhoan, NGUOIDUNG.VaiTro, TAIKHOAN.MatKhau FROM NGUOIDUNG, TAIKHOAN "
                + "WHERE NGUOIDUNG.TenTaiKhoan = TAIKHOAN.TenTaiKhoan and NGUOIDUNG.SDT like ?";
        return select(sql, "%"+keyword+"%");
    }
    
    public List<NguoiDung> getTk_Email(String email, String taiKhoan) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_timTaiKhoan_Email (?,?)}";
                rs = jdbcHelper.executeQuery(sql, email, taiKhoan);
                while (rs.next()) {
                      NguoiDung model = readFromResultSet(rs);

                    list.add(model);
                }
           
                rs.getStatement().getConnection().close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}

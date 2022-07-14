/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.checkHelper;
import Helper.jdbcHelper;
import Model.ThongKe_Nam;
import Model.ThongKe_Ngay;
import Model.ThongKe_Thang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {
    
    
    public ThongKe_Ngay readFromResultSet(ResultSet rs) throws SQLException{
        ThongKe_Ngay model=new ThongKe_Ngay();
        model.setNgay(rs.getDate("NGAY"));
        model.setTongTien(rs.getInt("TONGTIEN"));
       
        return model;
    }
    
    public ThongKe_Thang readFromResultSet2(ResultSet rs) throws SQLException{
        ThongKe_Thang model=new ThongKe_Thang();
        model.setNgay(rs.getInt("THANG"));
        model.setTongTien(rs.getInt("TONGTIEN"));
       
        return model;
    }
    
    public ThongKe_Nam readFromResultSet3(ResultSet rs) throws SQLException{
        ThongKe_Nam model=new ThongKe_Nam();
        model.setNam(rs.getInt("NAM"));
        model.setTongTien(rs.getInt("TONGTIEN"));
       
        return model;
    }
   
    public List<String> getTaiChinh(String tenTaiKhoan) {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TaiChinhHT (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                      String a=  rs.getString("TongTien");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<ThongKe_Thang> getTKThu_Thang(String  taiKhoan, int year) {
        List<ThongKe_Thang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_ThongKeThu_Thang (?,?)}";
                rs = jdbcHelper.executeQuery(sql, taiKhoan,year);
                while (rs.next()) {
                   ThongKe_Thang model= readFromResultSet2(rs);
                    
                    list.add(model);
                }
            
                rs.getStatement().getConnection().close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<ThongKe_Ngay> getTKThu_Ngay(String  taiKhoan, int Thang, int Nam) {
        List<ThongKe_Ngay> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_ThongKeThu_Ngay (?,?,?)}";
                rs = jdbcHelper.executeQuery(sql, taiKhoan,Thang, Nam);
                while (rs.next()) {
                   ThongKe_Ngay model= readFromResultSet(rs);
                    
                    list.add(model);
                }
            
                rs.getStatement().getConnection().close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<ThongKe_Nam> getTKThu_Nam(String  taiKhoan) {
        List<ThongKe_Nam> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_ThongKeThu_Nam (?)}";
                rs = jdbcHelper.executeQuery(sql, taiKhoan);
                while (rs.next()) {
                   ThongKe_Nam model= readFromResultSet3(rs);
                    
                    list.add(model);
                }
            
                rs.getStatement().getConnection().close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    
    
    public List<ThongKe_Thang> getTKChi_Thang(String  taiKhoan, int year) {
        List<ThongKe_Thang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_ThongKeChi_Thang (?,?)}";
                rs = jdbcHelper.executeQuery(sql, taiKhoan,year);
                while (rs.next()) {
                   ThongKe_Thang model= readFromResultSet2(rs);
                    
                    list.add(model);
                }
            
                rs.getStatement().getConnection().close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<ThongKe_Ngay> getTKChi_Ngay(String  taiKhoan, int Thang, int Nam) {
        List<ThongKe_Ngay> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_ThongKeChi_Ngay (?,?,?)}";
                rs = jdbcHelper.executeQuery(sql, taiKhoan,Thang, Nam);
                while (rs.next()) {
                   ThongKe_Ngay model= readFromResultSet(rs);
                    
                    list.add(model);
                }
            
                rs.getStatement().getConnection().close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<ThongKe_Nam> getTKChi_Nam(String  taiKhoan) {
        List<ThongKe_Nam> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            
                String sql = "{call sp_ThongKeChi_Nam (?)}";
                rs = jdbcHelper.executeQuery(sql, taiKhoan);
                while (rs.next()) {
                   ThongKe_Nam model= readFromResultSet3(rs);
                    
                    list.add(model);
                }
            
                rs.getStatement().getConnection().close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    
    //Tong thu chi
    public List<Integer> getTongThuChi_Nam(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThuChi_Nam (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    
    
    public List<Integer> getTongThuChi_Thang(String tenTaiKhoan, int year) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThuChi_Thang (?,?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan, year);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongThuChi_Ngay(String tenTaiKhoan, int month, int year) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThuChi_Ngay (?,?, ?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan, month, year);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    //
    //Tong thu
    
     public List<Integer> getTongThu_Nam(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThu_Nam (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
     public List<Integer> getTongThu_NamNay(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThu_NamNay (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
     
    public List<Integer> getTongThu_Thang(String tenTaiKhoan, int year) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThu_Thang (?,?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan, year);
                while (rs.next()) {

                            int a=  rs.getInt("TongTien");                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongThu_Ngay(String tenTaiKhoan, int month, int year) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThu_Ngay (?,?, ?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan, month, year);
                while (rs.next()) {

                      int a=  rs.getInt("TongTien");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongThu_ThangNay(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThu_ThangNay (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                            int a=  rs.getInt("TongTien");                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongThu_NgayNay(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongThu_NgayHomNay (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                      int a=  rs.getInt("TongTien");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    ///
    //Tong chi
    public List<Integer> getTongChi_Nam(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongChi_Nam (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongChi_NamNay(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongChi_NamNay (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                        int a=  rs.getInt("TongTien");                   
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongChi_Thang(String tenTaiKhoan, int year) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongChi_Thang (?,?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan, year);
                while (rs.next()) {

                            int a=  rs.getInt("TongTien");                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongChi_Ngay(String tenTaiKhoan, int month, int year) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongChi_Ngay (?,?, ?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan, month, year);
                while (rs.next()) {

                      int a=  rs.getInt("TongTien");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongChi_ThangNay(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongChi_ThangNay (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                            int a=  rs.getInt("TongTien");                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getTongChi_NgayNay(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_TongChi_NgayHomNay (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                      int a=  rs.getInt("TongTien");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    //
    
    public List<Integer> getNam_Thu(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_SoNam_Thu (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                      int a=  rs.getInt("Nam");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
    public List<Integer> getNam_Chi(String tenTaiKhoan) {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
           
                String sql = "{call sp_SoNam_Chi (?)}";
                rs = jdbcHelper.executeQuery(sql, tenTaiKhoan);
                while (rs.next()) {

                      int a=  rs.getInt("Nam");
                   
                    
                    list.add(a);
                }
            
                rs.getStatement().getConnection().close();

            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
}

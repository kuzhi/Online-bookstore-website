/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLi;

import DAO.NguoiDungDAO;
import DAO.TaiKhoanDAO;
import DAO.ThongKeDAO;
import DAO.ViTienDAO;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.dateHelper;
import Jdialog_GT_DN.Login;
import Model.NguoiDung;
import Model.ViTien;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TongQuanJFrame extends javax.swing.JFrame {
    
    public ThongKeDAO thongkeDao = new ThongKeDAO();
    public TaiKhoanDAO tkDao = new TaiKhoanDAO();
    public ViTienDAO vtDao = new ViTienDAO();
    public NguoiDungDAO ndDao = new NguoiDungDAO();
    
    /**
     * Creates new form TongQuanJFrame
     */
    public TongQuanJFrame() {
        initComponents();
    }
    
    public void init(){
        setIconImage(ShareHelper.APP_ICON);
        this.setTitle("CHI TIÊU HÀNG NGÀY");
        taiChinh();
        load();
  
    }
     public void load(){
        try {
            DefaultTableModel model = (DefaultTableModel) tblViTien.getModel();
        
        model.setRowCount(0);
        List<ViTien> list = vtDao.selectByKeyword(ShareHelper.USER.getTenTaiKhoan());
        if(list.isEmpty()){
            DialogHelper.alert(this, "Tài khoản này chưa tạo ví tiền nào!!");
        }
        
        for(ViTien vt:list){
            Object [] rows= {
                                
                vt.getTenVi(), 
                vt.getSoDu()+" VNĐ",
                
                    
            };
            model.addRow(rows);
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
        
        
    }
    
    public void taiChinh(){
        getTaiChinh();
        getTaiChinh_Chi_Nam();
        getTaiChinh_Chi_Ngay();
        getTaiChinh_Chi_Thang();
        getTaiChinh_Thu_Nam();
        getTaiChinh_Thu_Ngay();
        getTaiChinh_Thu_Thang();
    }
    
    public void CloseJframe(){
                        DialogHelper.alert(this, "Cám ơn đã sử dụng phần mềm! \n Chúc bạn một ngày tốt lành!!");

            System.exit(0);
        
    }
    //hover cac bang ben trai
    public void miceEntered(JPanel panel, JLabel lbl){
        Font myFont = new Font("Tahoma", Font.BOLD, 14);
       panel.setBackground(new Color(114,206,255));
       lbl.setForeground(white);
       lbl.setFont(myFont);
    }
    
    public void miceExited(JPanel panel, JLabel lbl){
         Font myFont = new Font("Tahoma", Font.BOLD, 14);
       panel.setBackground(new Color(199,229,245));
       lbl.setForeground(new Color(59,204,253));
       lbl.setFont(myFont);
    }
    //panel lam viec
    public void getTaiChinh(){
        
            List<String> taichinh = thongkeDao.getTaiChinh(ShareHelper.USER.getTenTaiKhoan());
        
        try {
            String TongTien = null;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu tài chính hiện tại!!");
                   
        }

        else{
                for(String tc : taichinh){
                    TongTien = tc;
                }
                
                    txtTongTien.setForeground(new Color(93, 255, 51));
                    txtTongTien.setText(TongTien+ " VNĐ");
                
                
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }       
        
    }
    
    public void getTaiChinh_Thu_Nam(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongThu_NamNay(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này chưa có khoản thu nào!!");
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtThu_Nam2.setForeground(new Color(204,0,51));
        }
        else{
            txtThu_Nam2.setForeground(new Color(93, 255, 51));
        }
        txtThu_Nam2.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Thu_Thang(){
        try {
            
            
        List<Integer> taichinh = thongkeDao.getTongThu_ThangNay(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này chưa có khoản thu nào!!");
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        if(TongTien<0){
            txtThu_Thang2.setForeground(new Color(204,0,51));
        }
        else{
            txtThu_Thang2.setForeground(new Color(93, 255, 51));
        }
        txtThu_Thang2.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Thu_Ngay(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongThu_NgayNay(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này chưa có khoản thu nào!!");
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtThu_Nay2.setForeground(new Color(204,0,51));
        }
        else{
            txtThu_Nay2.setForeground(new Color(93, 255, 51));
        }
        txtThu_Nay2.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }
    }
    
    //Tai chinh Chi
    
    public void getTaiChinh_Chi_Nam(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongChi_NamNay(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này chưa có khoản chi nào!!");
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtChi_Nam2.setForeground(new Color(204,0,51));
        }
        else{
            txtChi_Nam2.setForeground(new Color(93, 255, 51));
        }
        txtChi_Nam2.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Chi_Thang(){
        try {
            
        List<Integer> taichinh = thongkeDao.getTongChi_ThangNay(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này chưa có khoản chi nào!!");
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        if(TongTien<0){
            txtChi_Thang2.setForeground(new Color(204,0,51));
        }
        else{
            txtChi_Thang2.setForeground(new Color(93, 255, 51));
        }
        txtChi_Thang2.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Chi_Ngay(){
        try {
           
        
        List<Integer> taichinh = thongkeDao.getTongChi_NgayNay(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này chưa có khoản chi nào!!");
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtChi_Nay2.setForeground(new Color(204,0,51));
        }
        else{
            txtChi_Nay2.setForeground(new Color(93, 255, 51));
        }
        txtChi_Nay2.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelChuyen = new javax.swing.JPanel();
        panelTaiKhoan = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblTaiKhoan_BG = new javax.swing.JLabel();
        panelTongQuan = new javax.swing.JPanel();
        lblTongQuan = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblTongQuan_BG = new javax.swing.JLabel();
        panelViTien = new javax.swing.JPanel();
        lblViTien = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblViTien_BG = new javax.swing.JLabel();
        panelThuChi = new javax.swing.JPanel();
        lblThuChi = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblThuChi_BG = new javax.swing.JLabel();
        panelNguoiDung = new javax.swing.JPanel();
        lblNguoiDung = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblNguoiDung_BG = new javax.swing.JLabel();
        panelBaoCao = new javax.swing.JPanel();
        lblBaoCao = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblBG_BC = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        panelTongQuan_2 = new javax.swing.JPanel();
        panelLeft = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtThu_Nay = new javax.swing.JLabel();
        txtChi_Nay = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtThu_Thang = new javax.swing.JLabel();
        txtChi_Thang = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtChi_Nam = new javax.swing.JLabel();
        txtThu_Nam = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtThu_Nay2 = new javax.swing.JLabel();
        txtChi_Nay2 = new javax.swing.JLabel();
        txtThu_Thang2 = new javax.swing.JLabel();
        txtChi_Thang2 = new javax.swing.JLabel();
        txtThu_Nam2 = new javax.swing.JLabel();
        txtChi_Nam2 = new javax.swing.JLabel();
        panelRight = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtextField = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViTien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelChuyen.setBackground(new java.awt.Color(199, 229, 245));

        panelTaiKhoan.setBackground(new java.awt.Color(199, 229, 245));
        panelTaiKhoan.setPreferredSize(new java.awt.Dimension(2, 102));
        panelTaiKhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(59, 204, 253));
        lblTaiKhoan.setText("Tài khoản");
        panelTaiKhoan.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Unknown person2.png"))); // NOI18N
        panelTaiKhoan.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        lblTaiKhoan_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTaiKhoan_BG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoan_BGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTaiKhoan_BGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTaiKhoan_BGMouseExited(evt);
            }
        });
        panelTaiKhoan.add(lblTaiKhoan_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 110));

        panelTongQuan.setBackground(new java.awt.Color(199, 229, 245));
        panelTongQuan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTongQuan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongQuan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongQuan.setText("Tổng quan");
        panelTongQuan.add(lblTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 83, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tongquan.png"))); // NOI18N
        panelTongQuan.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 24, -1, -1));

        lblTongQuan_BG.setBackground(new java.awt.Color(114, 206, 255));
        lblTongQuan_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTongQuan_BG.setOpaque(true);
        panelTongQuan.add(lblTongQuan_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -3, 170, 120));

        panelViTien.setBackground(new java.awt.Color(199, 229, 245));
        panelViTien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblViTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViTien.setForeground(new java.awt.Color(59, 204, 253));
        lblViTien.setText("Ví tiền");
        panelViTien.add(lblViTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vitien.png"))); // NOI18N
        panelViTien.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        lblViTien_BG.setBackground(new java.awt.Color(255, 255, 255));
        lblViTien_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblViTien_BG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViTien_BGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblViTien_BGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblViTien_BGMouseExited(evt);
            }
        });
        panelViTien.add(lblViTien_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 170, 120));

        panelThuChi.setBackground(new java.awt.Color(199, 229, 245));
        panelThuChi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThuChi.setForeground(new java.awt.Color(59, 204, 253));
        lblThuChi.setText("Thu-Chi");
        panelThuChi.add(lblThuChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 20));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/thuchi.png"))); // NOI18N
        panelThuChi.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        lblThuChi_BG.setBackground(new java.awt.Color(114, 206, 255));
        lblThuChi_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblThuChi_BG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThuChi_BGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThuChi_BGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThuChi_BGMouseExited(evt);
            }
        });
        panelThuChi.add(lblThuChi_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 170, 120));

        panelNguoiDung.setBackground(new java.awt.Color(199, 229, 245));
        panelNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelNguoiDung.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNguoiDung.setForeground(new java.awt.Color(59, 204, 253));
        lblNguoiDung.setText("Người dùng");
        panelNguoiDung.add(lblNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 94, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nguoidung.png"))); // NOI18N
        panelNguoiDung.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 23, -1, -1));

        lblNguoiDung_BG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNguoiDung_BGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNguoiDung_BGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNguoiDung_BGMouseExited(evt);
            }
        });
        panelNguoiDung.add(lblNguoiDung_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        panelBaoCao.setBackground(new java.awt.Color(199, 229, 245));
        panelBaoCao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBaoCao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBaoCao.setForeground(new java.awt.Color(59, 204, 253));
        lblBaoCao.setText("Báo cáo");
        lblBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBaoCaoMouseEntered(evt);
            }
        });
        panelBaoCao.add(lblBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 65, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Group 305.png"))); // NOI18N
        panelBaoCao.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 19, -1, -1));

        lblBG_BC.setBackground(new java.awt.Color(255, 255, 255));
        lblBG_BC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBG_BC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBG_BCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBG_BCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBG_BCMouseExited(evt);
            }
        });
        panelBaoCao.add(lblBG_BC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        btnLogOut.setBackground(new java.awt.Color(223, 203, 203));
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dangxuat.png"))); // NOI18N
        btnLogOut.setText("Đăng xuất");
        btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChuyenLayout = new javax.swing.GroupLayout(panelChuyen);
        panelChuyen.setLayout(panelChuyenLayout);
        panelChuyenLayout.setHorizontalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelViTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelThuChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelNguoiDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelChuyenLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChuyenLayout.setVerticalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuyenLayout.createSequentialGroup()
                .addComponent(panelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(panelTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(panelViTien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(panelThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(panelNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(panelBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTongQuan_2.setBackground(new java.awt.Color(114, 206, 255));
        panelTongQuan_2.setLayout(new javax.swing.BoxLayout(panelTongQuan_2, javax.swing.BoxLayout.LINE_AXIS));

        panelLeft.setBackground(new java.awt.Color(255, 255, 255));
        panelLeft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelLeft.setPreferredSize(new java.awt.Dimension(538, 1028));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(59, 204, 253));
        jLabel1.setText("Tình hình thu chi");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Hôm nay");

        txtThu_Nay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtThu_Nay.setText("Thu");

        txtChi_Nay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtChi_Nay.setText("Chi");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Tháng");

        txtThu_Thang.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtThu_Thang.setText("Thu");

        txtChi_Thang.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtChi_Thang.setText("Chi");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Năm");

        txtChi_Nam.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtChi_Nam.setText("Chi");

        txtThu_Nam.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtThu_Nam.setText("Thu");

        txtThu_Nay2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtChi_Nay2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtThu_Thang2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtChi_Thang2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtThu_Nam2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtChi_Nam2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtChi_Nay)
                            .addComponent(txtThu_Nay))
                        .addGap(33, 33, 33)
                        .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtThu_Nay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChi_Nay2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel6))
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThu_Nam, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtChi_Nam, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtChi_Thang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThu_Thang, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLeftLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtThu_Nam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtChi_Nam2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLeftLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtThu_Thang2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtChi_Thang2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThu_Nay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtThu_Nay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChi_Nay)
                    .addComponent(txtChi_Nay2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel6)
                .addGap(27, 27, 27)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThu_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtThu_Thang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChi_Thang)
                    .addComponent(txtChi_Thang2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThu_Nam)
                    .addComponent(txtThu_Nam2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChi_Nam)
                    .addComponent(txtChi_Nam2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153))
        );

        panelTongQuan_2.add(panelLeft);

        panelRight.setBackground(new java.awt.Color(255, 255, 255));
        panelRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(59, 204, 253));
        jLabel2.setText("Ví tiền");

        jtextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtextField.setText("TỔNG TIỀN");

        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTongTien.setText("VND");
        txtTongTien.setToolTipText("");

        tblViTien.setBackground(new java.awt.Color(180, 234, 248));
        tblViTien.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tblViTien.setForeground(new java.awt.Color(51, 153, 255));
        tblViTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ));
        tblViTien.setRowHeight(80);
        jScrollPane1.setViewportView(tblViTien);

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGroup(panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRightLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2))
                    .addGroup(panelRightLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jtextField)
                        .addGap(31, 31, 31)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRightLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addGap(71, 71, 71)
                .addGroup(panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtextField)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        panelTongQuan_2.add(panelRight);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelTongQuan_2, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTongQuan_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        ShareHelper.logoff();
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void lblBG_BCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseExited
        // TODO add your handling code here:
        miceExited(panelBaoCao, lblBaoCao);
    }//GEN-LAST:event_lblBG_BCMouseExited

    private void lblBG_BCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseEntered
        // TODO add your handling code here:
        miceEntered(panelBaoCao, lblBaoCao);
    }//GEN-LAST:event_lblBG_BCMouseEntered

    private void lblBG_BCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new BaoCaoJFrame(0).setVisible(true);
    }//GEN-LAST:event_lblBG_BCMouseClicked

    private void lblBaoCaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoCaoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblBaoCaoMouseEntered

    private void lblNguoiDung_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelNguoiDung, lblNguoiDung);
    }//GEN-LAST:event_lblNguoiDung_BGMouseExited

    private void lblNguoiDung_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelNguoiDung, lblNguoiDung);
    }//GEN-LAST:event_lblNguoiDung_BGMouseEntered

    private void lblNguoiDung_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseClicked
        // TODO add your handling code here:]
      
       NguoiDung nd = ndDao.findByIdTK(ShareHelper.USER.getTenTaiKhoan());
        if(nd.isVaiTro()){
             this.dispose();
            new NguoiDungJFrame().setVisible(true);
        }
        else{
             this.dispose();
            new NguoiDungJFrame_2().setVisible(true);
        }
    }//GEN-LAST:event_lblNguoiDung_BGMouseClicked

    private void lblViTien_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseExited

    private void lblViTien_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseEntered

    private void lblViTien_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ViTienJFrame_2().setVisible(true);
    }//GEN-LAST:event_lblViTien_BGMouseClicked

    private void lblTaiKhoan_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelTaiKhoan, lblTaiKhoan);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseExited

    private void lblTaiKhoan_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelTaiKhoan, lblTaiKhoan);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseEntered

    private void lblTaiKhoan_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new TaiKhoanJFrame().setVisible(true);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseClicked

    private void lblThuChi_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelThuChi, lblThuChi);
    }//GEN-LAST:event_lblThuChi_BGMouseEntered

    private void lblThuChi_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelThuChi, lblThuChi);
    }//GEN-LAST:event_lblThuChi_BGMouseExited

    private void lblThuChi_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ThuChiJFrame().setVisible(true);
    }//GEN-LAST:event_lblThuChi_BGMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        CloseJframe();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        

         if(ShareHelper.authenticated()){
            init();
            setLocationRelativeTo(null);
        }
        else{
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TongQuanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TongQuanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TongQuanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TongQuanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TongQuanJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jtextField;
    private javax.swing.JLabel lblBG_BC;
    private javax.swing.JLabel lblBaoCao;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblNguoiDung_BG;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTaiKhoan_BG;
    private javax.swing.JLabel lblThuChi;
    private javax.swing.JLabel lblThuChi_BG;
    private javax.swing.JLabel lblTongQuan;
    private javax.swing.JLabel lblTongQuan_BG;
    private javax.swing.JLabel lblViTien;
    private javax.swing.JLabel lblViTien_BG;
    private javax.swing.JPanel panelBaoCao;
    private javax.swing.JPanel panelChuyen;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelNguoiDung;
    private javax.swing.JPanel panelRight;
    private javax.swing.JPanel panelTaiKhoan;
    private javax.swing.JPanel panelThuChi;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelTongQuan_2;
    private javax.swing.JPanel panelViTien;
    private javax.swing.JTable tblViTien;
    private javax.swing.JLabel txtChi_Nam;
    private javax.swing.JLabel txtChi_Nam2;
    private javax.swing.JLabel txtChi_Nay;
    private javax.swing.JLabel txtChi_Nay2;
    private javax.swing.JLabel txtChi_Thang;
    private javax.swing.JLabel txtChi_Thang2;
    private javax.swing.JLabel txtThu_Nam;
    private javax.swing.JLabel txtThu_Nam2;
    private javax.swing.JLabel txtThu_Nay;
    private javax.swing.JLabel txtThu_Nay2;
    private javax.swing.JLabel txtThu_Thang;
    private javax.swing.JLabel txtThu_Thang2;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}

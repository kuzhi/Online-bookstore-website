/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLi;

import DAO.NguoiDungDAO;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.checkHelper;
import Helper.dateHelper;
import Jdialog_GT_DN.Login;
import Model.NguoiDung;
import java.awt.Color;
import static java.awt.Color.white;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.RadioButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class DangKy extends javax.swing.JFrame {
/*
    gmail: windnunbe@gmail.com
    password: tumottoichin
    */
    /**
     * Creates new form DangKy
     */
    public NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    public String maND=null;
    
    public DangKy() {
        initComponents();
        init();
    }
    
    public void init(){
        lblOpen_MK.setVisible(false);
        lblOpen_XN.setVisible(false);
        setIconImage(ShareHelper.APP_ICON);
        this.setTitle("CHI TIÊU HÀNG NGÀY");
    }
    
    public void CloseJframe(){
                
            DialogHelper.alert(this, "Cám ơn đã sử dụng phần mềm! \n Chúc bạn một ngày tốt lành!!");
            System.exit(0);
        
    }
    public void MinimizeJframe(){
        setExtendedState(ICONIFIED);
    }
    
    public void MaximaizeJframe(){
        if(this.getExtendedState() != NguoiDungJFrame.MAXIMIZED_BOTH){
            this.setExtendedState(NguoiDungJFrame.MAXIMIZED_BOTH);
        }
        else{
            this.setExtendedState(NguoiDungJFrame.NORMAL);
        }
    }
    public NguoiDung getModelThem(){
        
        NguoiDung model = new NguoiDung();

        if(nguoiDungDAO.countSize()<9){
            model.setMSND("ND_0"+String.valueOf(nguoiDungDAO.countSize()+1));

        }
        else{
            model.setMSND("ND_"+String.valueOf(nguoiDungDAO.countSize()+1));
        }
        model.setDiaChi(txtDiaChi.getText());
        model.setEmail(txtEmail.getText());
        
        if(rdoNam.isSelected()){
            model.setGioiTinh(1);
        }
        else if(rdoNu.isSelected()){
            model.setGioiTinh(2);

        }
        else{
            model.setGioiTinh(3);

        }
        
        model.setHoTen(txtHoTen.getText());
        model.setMatKhau(txtMatKhau.getText());
        model.setNamSinh(dateHelper.toDate(txtNamSinh.getText()));
        model.setSdt(txtSDT.getText());
        model.setTaiKhoan(txtTaiKhoan.getText());
        model.setTenHienThi(txtTenHienThi.getText());
        return model;
    }
    
     public void clear(){
        txtTenHienThi.setText("");
        maND = null;
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHoTen.setText("");
        txtMatKhau.setText("");
        
        txtNamSinh.setText("");
        txtSDT.setText("");
        txtTaiKhoan.setText("");
        txtTenHienThi.setText("");
        grpGT.clearSelection();
        grpVaiTro.clearSelection();
    }
    
     public void insert(){
        
        NguoiDung model = getModelThem();
             
        
            try {
                
                nguoiDungDAO.insert(model);
                
                clear();
                DialogHelper.alert(this, "Thêm mới thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại");
                
            }
        
        
    }
     public boolean checkNullRdo(JRadioButton rdo1, JRadioButton rdo2, JRadioButton rdo3){
        
        
        if(rdo1.isSelected()){
            return true;
        }
        else if (rdo2.isSelected()){
            return true;
        }
        else if(rdo3.isSelected()){
            return true;
        }
        else{
            DialogHelper.alert(this, "Chọn giới tính " );
            return false;
        }
        
    }
    
   public boolean checkTrungTenTaiKhoan_Email(JTextField txtTaiKhoan, JTextField email){
        List<NguoiDung> nd = nguoiDungDAO.getTk_Email(email.getText(), txtTaiKhoan.getText());
        
        txtTaiKhoan.setBackground(white);
        if(nd.isEmpty()){
            
            txtTaiKhoan.setBackground(white);
            email.setBackground(white);
            return true;
            
        }
        else{
            DialogHelper.alert(this, "Tài khoản hoặc email đã có trong dữ liệu!");
            return false;
            
        }
    }
     public boolean check(){
        boolean error = false;
        if (checkHelper.checkNullText(txtTaiKhoan)
                && checkHelper.checkNullText(txtHoTen)
                && checkHelper.checkNullText(txtTenHienThi)
                && checkHelper.checkNullPass(txtMatKhau)
                && checkHelper.checkNullPass(txtXacNhanMatKhau)
                && checkHelper.checkNullText(txtDiaChi)
                && checkHelper.checkNullText(txtNamSinh)
                && checkHelper.checkNullText(txtSDT)
                && checkNullRdo(rdoNam, rdoNu, rdoKhac)
                && checkHelper.checkNullText(txtEmail)) {
            if (checkHelper.checkTaiKhoan(txtTaiKhoan)
                    && checkHelper.checkName(txtTenHienThi)
                    && checkHelper.checkName(txtHoTen)
                    && checkHelper.checkPass(txtMatKhau)
                    && checkHelper.checkXNPass(txtXacNhanMatKhau, txtMatKhau)
                    && checkHelper.checkDiaChi(txtDiaChi)
                    && checkHelper.checkDate(txtNamSinh)
                    && checkHelper.checkSDT(txtSDT)
                    && checkHelper.checkEmail(txtEmail))
                    {
                    
                        error = true;
                    
                
            }
        }
        
        return error;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpGT = new javax.swing.ButtonGroup();
        grpVaiTro = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenHienThi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdoKhac = new javax.swing.JRadioButton();
        txtNamSinh = new javax.swing.JTextField();
        iconCalendar = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        btnTao = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        txtXacNhanMatKhau = new javax.swing.JPasswordField();
        lblOpen_MK = new javax.swing.JLabel();
        lblClose_MK = new javax.swing.JLabel();
        lblOpen_XN = new javax.swing.JLabel();
        lblClose_XN = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rdoAdmin = new javax.swing.JRadioButton();
        rdoNguoiDung = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(199, 229, 245));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(13, 113, 180));
        jLabel5.setText("Tên hiển thị");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(13, 113, 180));
        jLabel6.setText("Họ và tên");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(13, 113, 180));
        jLabel7.setText("Tên tài khoản");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(13, 113, 180));
        jLabel9.setText("Mật khẩu");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(13, 113, 180));
        jLabel10.setText("Số điện thoại");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(13, 113, 180));
        jLabel11.setText("Email");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(13, 113, 180));
        jLabel12.setText("Địa chỉ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(13, 113, 180));
        jLabel13.setText("Giới tính");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(13, 113, 180));
        jLabel14.setText("Năm sinh");

        rdoNam.setBackground(new java.awt.Color(199, 229, 245));
        grpGT.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(13, 113, 180));
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(199, 229, 245));
        grpGT.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(13, 113, 180));
        rdoNu.setText("Nữ");

        rdoKhac.setBackground(new java.awt.Color(199, 229, 245));
        grpGT.add(rdoKhac);
        rdoKhac.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoKhac.setForeground(new java.awt.Color(13, 113, 180));
        rdoKhac.setText("Khác");

        txtNamSinh.setToolTipText("");

        iconCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Calendar.png"))); // NOI18N

        btnTao.setBackground(new java.awt.Color(13, 126, 255));
        btnTao.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnTao.setForeground(new java.awt.Color(255, 255, 255));
        btnTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add_1.png"))); // NOI18N
        btnTao.setText("TẠO");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(13, 126, 255));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(13, 113, 180));
        jLabel15.setText("Xác nhận mật khẩu");

        lblOpen_MK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eye_open.png"))); // NOI18N
        lblOpen_MK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblOpen_MKMousePressed(evt);
            }
        });

        lblClose_MK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blind_eye.png"))); // NOI18N
        lblClose_MK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblClose_MKMousePressed(evt);
            }
        });

        lblOpen_XN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eye_open.png"))); // NOI18N
        lblOpen_XN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblOpen_XNMousePressed(evt);
            }
        });

        lblClose_XN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blind_eye.png"))); // NOI18N
        lblClose_XN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblClose_XNMousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(13, 113, 180));
        jLabel16.setText("Vai trò");

        rdoAdmin.setBackground(new java.awt.Color(199, 229, 245));
        grpVaiTro.add(rdoAdmin);
        rdoAdmin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoAdmin.setForeground(new java.awt.Color(13, 113, 180));
        rdoAdmin.setText("Admin");
        rdoAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAdminActionPerformed(evt);
            }
        });

        rdoNguoiDung.setBackground(new java.awt.Color(199, 229, 245));
        grpVaiTro.add(rdoNguoiDung);
        rdoNguoiDung.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoNguoiDung.setForeground(new java.awt.Color(13, 113, 180));
        rdoNguoiDung.setText("Người dùng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(80, 80, 80)
                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(109, 109, 109)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(92, 92, 92)
                        .addComponent(txtTenHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(113, 113, 113)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblClose_MK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOpen_MK))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(42, 42, 42)
                        .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblClose_XN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblOpen_XN))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(84, 84, 84)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(rdoNam)
                        .addGap(33, 33, 33)
                        .addComponent(rdoNu)
                        .addGap(31, 31, 31)
                        .addComponent(rdoKhac))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(130, 130, 130)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoAdmin)
                                .addGap(33, 33, 33)
                                .addComponent(rdoNguoiDung))
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addComponent(iconCalendar)))
                .addGap(0, 140, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTao)
                .addGap(199, 199, 199)
                .addComponent(btnReset)
                .addGap(261, 261, 261))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(txtTenHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel9))
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblOpen_MK)
                        .addComponent(lblClose_MK)))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel15))
                    .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblOpen_XN)
                        .addComponent(lblClose_XN)))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14))
                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(iconCalendar)))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel13))
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(rdoKhac))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rdoAdmin)
                        .addComponent(rdoNguoiDung)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnTao))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        if(check()){
            if(checkTrungTenTaiKhoan_Email(txtTaiKhoan, txtEmail)){
            insert();
            this.dispose();
            
        }
                
        }
        
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnResetActionPerformed

    private void lblClose_MKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClose_MKMousePressed
        // TODO add your handling code here:
        lblClose_MK.setVisible(false);
        lblOpen_MK.setVisible(true);
        txtMatKhau.setEchoChar('\u0000');
    }//GEN-LAST:event_lblClose_MKMousePressed

    private void lblClose_XNMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClose_XNMousePressed
        // TODO add your handling code here:
        lblClose_XN.setVisible(false);
        lblOpen_XN.setVisible(true);
        txtXacNhanMatKhau.setEchoChar('\u0000');
    }//GEN-LAST:event_lblClose_XNMousePressed

    private void lblOpen_MKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpen_MKMousePressed
        // TODO add your handling code here:
        lblClose_MK.setVisible(true);
        lblOpen_MK.setVisible(false);
        txtMatKhau.setEchoChar('\u25cf');
    }//GEN-LAST:event_lblOpen_MKMousePressed

    private void lblOpen_XNMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpen_XNMousePressed
        // TODO add your handling code here:
        lblClose_XN.setVisible(true);
        lblOpen_XN.setVisible(false);
        txtXacNhanMatKhau.setEchoChar('\u25cf');
    }//GEN-LAST:event_lblOpen_XNMousePressed

    private void rdoAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoAdminActionPerformed

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
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTao;
    private javax.swing.ButtonGroup grpGT;
    private javax.swing.ButtonGroup grpVaiTro;
    private javax.swing.JLabel iconCalendar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblClose_MK;
    private javax.swing.JLabel lblClose_XN;
    private javax.swing.JLabel lblOpen_MK;
    private javax.swing.JLabel lblOpen_XN;
    private javax.swing.JRadioButton rdoAdmin;
    private javax.swing.JRadioButton rdoKhac;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNguoiDung;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTenHienThi;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLi;

import DAO.NguoiDungDAO;
import DAO.TaiKhoanDAO;
import Helper.DialogHelper;
import Helper.ShareHelper;
import static Helper.ShareHelper.USER;
import Helper.checkHelper;
import Helper.dateHelper;
import Jdialog_GT_DN.Login;
import Model.NguoiDung;
import Model.TaiKhoan;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NguoiDungJFrame extends javax.swing.JFrame {
    public int index = -1;
    public NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    public String maND=null;
    public TaiKhoanDAO tkDao = new TaiKhoanDAO();
    /**
     * Creates new form NguoiDungJFrame
     */
    public NguoiDungJFrame() {
        initComponents();
        init();
    }
    
    public void init(){
        setIconImage(ShareHelper.APP_ICON);
        this.setTitle("CHI TIÊU HÀNG NGÀY");
        setStatus(true);
    }
    
    public void setStatus(boolean status){
        btnThem.setEnabled(status);
        btnCapNhat.setEnabled(!status);
        btnXoa.setEnabled(!status);
        txtTaiKhoan.setEnabled(status);
        btnTim.setEnabled(status);
        
        boolean first = this.index > 0;
        boolean last = this.index < tblNguoiDung.getRowCount() - 1;
        
        btnFirst.setEnabled(!status && first);
        btnPrev.setEnabled(!status && first);
        btnLast.setEnabled(!status && last);
        btnNext.setEnabled(!status && last);
        
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
    ////
    public void load(){
        try {
            DefaultTableModel model = (DefaultTableModel) tblNguoiDung.getModel();
        model.setRowCount(0);
        List<NguoiDung> ndlist = new ArrayList<>();
        
         String timKiem = txtTim.getText();
       
        ndlist= nguoiDungDAO.selectByKeywordSDT(timKiem);
                
        if(ndlist.isEmpty()){
            DialogHelper.alert(this, "Người dùng này không có trong dữ liệu");
        }
           
        for(NguoiDung nd : ndlist){
            Object [] rows = {
              nd.getMSND(),
              nd.getHoTen(),
              dateHelper.toString(nd.getNamSinh()),
              nd.getTaiKhoan(),
              nd.getSdt(),
              nd.isVaiTro()?"Admin":"Người dùng",
              nd.getHinh()
            };
            model.addRow(rows);
        }
        lblSoDem.setText(1+ "/"+ nguoiDungDAO.countSize());
        } catch (Exception e) {
            DialogHelper.alert(this, "Tải dữ liệu bảng thất bại");
        }
        
    }
    
    public void setTrang(){
        txtTenHienThi.setBackground(white);
        txtTim.setBackground(white);
        txtDiaChi.setBackground(white);
        txtEmail.setBackground(white);
        txtHoTen.setBackground(white);
        txtMatKhau.setBackground(white);
        txtNamSinh.setBackground(white);
        txtSDT.setBackground(white);
        txtTaiKhoan.setBackground(white);
        
        
    }
    
    public void clear(){
        setTrang();
        txtTenHienThi.setText("");
        maND = null;
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHoTen.setText("");
        txtMatKhau.setText("");
        txtNamSinh.setText("");
        txtSDT.setText("");
        txtTaiKhoan.setText("");
        grpGT.clearSelection();
        lblHinh.setIcon(new ImageIcon());
         grpVT.clearSelection();
         txtTim.setText("");
        
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
        if(rdoNu.isSelected()){
            model.setGioiTinh(2);

        }
        if(rdoKhac.isSelected()){
            model.setGioiTinh(3);

        }
        
        model.setHinh(lblHinh.getToolTipText());
        model.setHoTen(txtHoTen.getText());
        model.setMatKhau(txtMatKhau.getText());
        model.setNamSinh(dateHelper.toDate(txtNamSinh.getText()));
        model.setSdt(txtSDT.getText());
        model.setTaiKhoan(txtTaiKhoan.getText());
        model.setTenHienThi(txtTenHienThi.getText());
        if(rdoAdmin.isSelected()){
            model.setVaiTro(true);
        }
        if(rdoNguoiDung.isSelected()){
            model.setVaiTro(false);

        }
        return model;
    }
    
    public NguoiDung getModel(){
        
        NguoiDung model = new NguoiDung();

        model.setMSND(maND);
        model.setDiaChi(txtDiaChi.getText());
        model.setEmail(txtEmail.getText());
        
        if(rdoNam.isSelected()){
            model.setGioiTinh(1);
        }
        if(rdoNu.isSelected()){
            model.setGioiTinh(2);

        }
        if(rdoKhac.isSelected()){
            model.setGioiTinh(3);

        }
        if(rdoAdmin.isSelected()){
            model.setVaiTro(true);
        }
        if(rdoNguoiDung.isSelected()){
            model.setVaiTro(false);

        }
        model.setHinh(lblHinh.getToolTipText());
        model.setHoTen(txtHoTen.getText());
        model.setMatKhau(txtMatKhau.getText());
        model.setNamSinh(dateHelper.toDate(txtNamSinh.getText()));
        model.setSdt(txtSDT.getText());
        model.setTaiKhoan(txtTaiKhoan.getText());
        model.setTenHienThi(txtTenHienThi.getText());
        return model;
    }
    
    
    public void setModel(NguoiDung entity){
        maND= entity.getMSND();
        txtTenHienThi.setText(entity.getTenHienThi());
        txtDiaChi.setText(entity.getDiaChi());
        txtEmail.setText(entity.getEmail());
        txtHoTen.setText(entity.getHoTen());
        txtMatKhau.setText(entity.getMatKhau());
        txtNamSinh.setText(dateHelper.toString(entity.getNamSinh()));
        txtSDT.setText(entity.getSdt());
        txtTaiKhoan.setText(entity.getTaiKhoan());
        if(entity.getGioiTinh()==1){
            rdoNam.setSelected(true);
        }
        else if(entity.getGioiTinh() == 2){
            rdoNu.setSelected(true);
        }
        else{
            rdoKhac.setSelected(true);
        }
        
        if(entity.isVaiTro()){
            rdoAdmin.setSelected(true);
        }
        
        else{
            rdoNguoiDung.setSelected(true);
        }
        if(entity.getHinh()== null){
            lblHinh.setIcon(new ImageIcon());
            DialogHelper.alert(this, "Người dùng này chưa đặt ảnh đại diện");
        }else{
            lblHinh.setIcon(ShareHelper.readLogo(entity.getHinh(), lblHinh));
            lblHinh.setToolTipText(entity.getHinh());
        }
        
        
    }
    
    public void selectImage(){
        if(fileChooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION){
            File  file = fileChooser.getSelectedFile();
            if(ShareHelper.saveLog(file)){

                lblHinh.setIcon(ShareHelper.readLogo(file.getName(), lblHinh));
                lblHinh.setToolTipText(file.getName());
            }
        }
    }
    
    public void edit(){
        try {
            //lay ma CD tu index
            String maND = (String) tblNguoiDung.getValueAt(index, 0);
            
            NguoiDung nd = nguoiDungDAO.findById(maND);
            lblSoDem.setText((this.index+1)+ "/"+ nguoiDungDAO.countSize());
            if(nd != null){
                setStatus(false);
                setModel(nd);
               
            }
            
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
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
    
    public boolean check(){
        
        boolean error = false;
        
        if(checkHelper.checkNullText(txtTenHienThi) 
                && checkHelper.checkNullText(txtHoTen)
                && checkHelper.checkNullText(txtTaiKhoan)
                && checkHelper.checkNullText(txtMatKhau)
                && checkHelper.checkNullText(txtSDT)
                && checkHelper.checkNullText(txtEmail)
                && checkHelper.checkNullText(txtDiaChi)
                && checkHelper.checkNullText(txtNamSinh)
                && checkHelper.checkRdo(rdoNam, rdoNu, rdoKhac)
                && checkHelper.checkRdo2(rdoAdmin,rdoNguoiDung)){
            if(checkHelper.checkName(txtTenHienThi)
                && checkHelper.checkName(txtHoTen)
                && checkHelper.checkTaiKhoan(txtTaiKhoan)    
                && checkHelper.checkPass2(txtMatKhau)
                && checkHelper.checkSDT(txtSDT)
                && checkHelper.checkEmail(txtEmail)
                && checkHelper.checkDiaChi(txtDiaChi)
                && checkHelper.checkDate(txtNamSinh))
            {
                error = true;
            }
        }
        
        
        return error;
    }
    public boolean checkChinhMinh(JTextField txt){
        TaiKhoan tk=tkDao.findById(txt.getText());
        if (tk.getTenTaiKhoan().equals(USER.getTenTaiKhoan())) {
            DialogHelper.alert(this, "Bạn không được xóa chính mình.");
            return false;
        } else {
            return true;
        }
    }
    //cac nút
    public void insert(){
        
        NguoiDung model = getModelThem();
             
        
            try {
                
                nguoiDungDAO.insert(model);
                load();
                clear();
                tabs.setSelectedIndex(0);
                DialogHelper.alert(this, "Thêm mới thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại");
                e.printStackTrace();
            }
        
        
    }
    
     public void update(){
        NguoiDung model = getModel();
       
             try {
                 nguoiDungDAO.update(model);
                 load();
                 DialogHelper.alert(this, "Cập nhật thành công!");
                 clear();
                 tabs.setSelectedIndex(0);
                 setStatus(true);
             } catch (Exception e) {
                 DialogHelper.alert(this, "Cập nhật thất bại");
             }
         
    }
    public void delete(){
        
        if(DialogHelper.confirm(this, "Bạn có thực sự muốn xóa người dùng này chứ?")){
             try {
                 if(checkChinhMinh(txtTaiKhoan)){
                    nguoiDungDAO.delete(maND);
                    load();
                    DialogHelper.alert(this, "Xóa thành công!");
                    clear();
                    tabs.setSelectedIndex(0);
                     setStatus(true);                 
                 }      
             } catch (Exception e) {
                 DialogHelper.alert(this, "Xóa thất bại");
             }
        }
    }
    
    public void find(){
        try {
  
            
            if(checkHelper.checkSDT(txtTim)){
                
                    load();
                    this.index=-1;
                    clear();
                    tabs.setSelectedIndex(0);
                    
            }
            
        } catch (Exception e) {
            DialogHelper.alert(this, "Tìm thất bại");
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

        grpGT = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        grpVT = new javax.swing.ButtonGroup();
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
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelBaoCao = new javax.swing.JPanel();
        lblBaoCao = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblBG_BC = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        panelNguoiDung_2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        panelDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        panelCapNhat = new javax.swing.JPanel();
        panelHinh = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        panelDieuHuong = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblSoDem = new javax.swing.JLabel();
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
        txtMatKhau = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdoAdmin = new javax.swing.JRadioButton();
        rdoNguoiDung = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        panelTaiKhoan.add(lblTaiKhoan_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 100));

        panelTongQuan.setBackground(new java.awt.Color(199, 229, 245));
        panelTongQuan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTongQuan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongQuan.setForeground(new java.awt.Color(59, 204, 253));
        lblTongQuan.setText("Tổng quan");
        panelTongQuan.add(lblTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 83, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tongquan.png"))); // NOI18N
        panelTongQuan.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 24, -1, -1));

        lblTongQuan_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTongQuan_BG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTongQuan_BGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTongQuan_BGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTongQuan_BGMouseExited(evt);
            }
        });
        panelTongQuan.add(lblTongQuan_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 107));

        panelViTien.setBackground(new java.awt.Color(199, 229, 245));
        panelViTien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblViTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViTien.setForeground(new java.awt.Color(59, 204, 253));
        lblViTien.setText("Ví tiền");
        panelViTien.add(lblViTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vitien.png"))); // NOI18N
        panelViTien.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

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
        panelViTien.add(lblViTien_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 113));

        panelThuChi.setBackground(new java.awt.Color(199, 229, 245));
        panelThuChi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThuChi.setForeground(new java.awt.Color(59, 204, 253));
        lblThuChi.setText("Thu-Chi");
        panelThuChi.add(lblThuChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 20));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/thuchi.png"))); // NOI18N
        panelThuChi.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

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
        panelThuChi.add(lblThuChi_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 116));

        panelNguoiDung.setBackground(new java.awt.Color(114, 206, 255));
        panelNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Người dùng");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nguoidung.png"))); // NOI18N

        javax.swing.GroupLayout panelNguoiDungLayout = new javax.swing.GroupLayout(panelNguoiDung);
        panelNguoiDung.setLayout(panelNguoiDungLayout);
        panelNguoiDungLayout.setHorizontalGroup(
            panelNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNguoiDungLayout.createSequentialGroup()
                .addGroup(panelNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNguoiDungLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel18))
                    .addGroup(panelNguoiDungLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNguoiDungLayout.setVerticalGroup(
            panelNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNguoiDungLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(18, 18, 18))
        );

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
                .addGap(20, 20, 20)
                .addComponent(btnLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChuyenLayout.setVerticalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuyenLayout.createSequentialGroup()
                .addComponent(panelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelViTien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogOut)
                .addGap(24, 24, 24))
        );

        panelNguoiDung_2.setBackground(new java.awt.Color(114, 206, 255));

        tabs.setBackground(new java.awt.Color(51, 204, 255));
        tabs.setPreferredSize(new java.awt.Dimension(1040, 649));

        panelDanhSach.setBackground(new java.awt.Color(114, 206, 255));

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SỐ", "HỌ VÀ TÊN", "NĂM SINH", "TÊN TÀI KHOẢN", "SỐ ĐIỆN THOẠI", "VAI TRÒ", "HÌNH"
            }
        ));
        tblNguoiDung.setGridColor(new java.awt.Color(240, 240, 240));
        tblNguoiDung.setSelectionBackground(new java.awt.Color(114, 206, 255));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNguoiDung);

        javax.swing.GroupLayout panelDanhSachLayout = new javax.swing.GroupLayout(panelDanhSach);
        panelDanhSach.setLayout(panelDanhSachLayout);
        panelDanhSachLayout.setHorizontalGroup(
            panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelDanhSachLayout.setVerticalGroup(
            panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH", panelDanhSach);

        panelCapNhat.setBackground(new java.awt.Color(114, 206, 255));

        panelHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHinh.setOpaque(true);
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHinhLayout = new javax.swing.GroupLayout(panelHinh);
        panelHinh.setLayout(panelHinhLayout);
        panelHinhLayout.setHorizontalGroup(
            panelHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        panelHinhLayout.setVerticalGroup(
            panelHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );

        panelDieuHuong.setBackground(new java.awt.Color(171, 162, 162));
        panelDieuHuong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/prev.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        lblSoDem.setText("jLabel5");

        javax.swing.GroupLayout panelDieuHuongLayout = new javax.swing.GroupLayout(panelDieuHuong);
        panelDieuHuong.setLayout(panelDieuHuongLayout);
        panelDieuHuongLayout.setHorizontalGroup(
            panelDieuHuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDieuHuongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(lblSoDem)
                .addGap(74, 74, 74)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnLast)
                .addGap(27, 27, 27))
        );
        panelDieuHuongLayout.setVerticalGroup(
            panelDieuHuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDieuHuongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDieuHuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDieuHuongLayout.createSequentialGroup()
                        .addComponent(lblSoDem)
                        .addGap(9, 9, 9))
                    .addGroup(panelDieuHuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(114, 206, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tên hiển thị");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Họ và tên");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên tài khoản");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mật khẩu");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số điện thoại");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Địa chỉ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Giới tính");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Năm sinh");

        rdoNam.setBackground(new java.awt.Color(114, 206, 255));
        grpGT.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(13, 113, 180));
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(114, 206, 255));
        grpGT.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(13, 113, 180));
        rdoNu.setText("Nữ");

        rdoKhac.setBackground(new java.awt.Color(114, 206, 255));
        grpGT.add(rdoKhac);
        rdoKhac.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rdoKhac.setForeground(new java.awt.Color(13, 113, 180));
        rdoKhac.setText("Khác");

        txtNamSinh.setToolTipText("");

        iconCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Calendar.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Vai trò");

        rdoAdmin.setBackground(new java.awt.Color(114, 206, 255));
        grpVT.add(rdoAdmin);
        rdoAdmin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rdoAdmin.setForeground(new java.awt.Color(13, 113, 180));
        rdoAdmin.setText("Admin");
        rdoAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAdminActionPerformed(evt);
            }
        });

        rdoNguoiDung.setBackground(new java.awt.Color(114, 206, 255));
        grpVT.add(rdoNguoiDung);
        rdoNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rdoNguoiDung.setForeground(new java.awt.Color(13, 113, 180));
        rdoNguoiDung.setText("Người dùng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenHienThi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(31, 31, 31)
                                        .addComponent(rdoNu)
                                        .addGap(34, 34, 34)
                                        .addComponent(rdoKhac))
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(iconCalendar))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoAdmin)
                                        .addGap(84, 84, 84)
                                        .addComponent(rdoNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rdoKhac)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(iconCalendar)
                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNguoiDung)
                            .addComponent(rdoAdmin))))
                .addGap(218, 218, 218))
        );

        javax.swing.GroupLayout panelCapNhatLayout = new javax.swing.GroupLayout(panelCapNhat);
        panelCapNhat.setLayout(panelCapNhatLayout);
        panelCapNhatLayout.setHorizontalGroup(
            panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCapNhatLayout.createSequentialGroup()
                .addGap(0, 178, Short.MAX_VALUE)
                .addComponent(panelDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(panelCapNhatLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        panelCapNhatLayout.setVerticalGroup(
            panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCapNhatLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(panelDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        tabs.addTab("CẬP NHẬT", panelCapNhat);

        jPanel2.setBackground(new java.awt.Color(184, 133, 133));

        btnThem.setBackground(new java.awt.Color(223, 203, 203));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setOpaque(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(223, 203, 203));
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reset.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhat.setOpaque(false);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(223, 203, 203));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trash.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.setOpaque(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTimMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTimMouseExited(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(223, 203, 203));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTim.setOpaque(false);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(223, 203, 203));
        btnClear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/new (2).png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.setOpaque(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnThem)
                .addGap(44, 44, 44)
                .addComponent(btnCapNhat)
                .addGap(47, 47, 47)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa)
                            .addComponent(btnClear)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addComponent(btnTim))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelNguoiDung_2Layout = new javax.swing.GroupLayout(panelNguoiDung_2);
        panelNguoiDung_2.setLayout(panelNguoiDung_2Layout);
        panelNguoiDung_2Layout.setHorizontalGroup(
            panelNguoiDung_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelNguoiDung_2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelNguoiDung_2Layout.setVerticalGroup(
            panelNguoiDung_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNguoiDung_2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelNguoiDung_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNguoiDung_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void tblNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()>=0){
            this.index = tblNguoiDung.rowAtPoint(evt.getPoint());
            
            if(index>=0){
                txtTaiKhoan.setEnabled(false);
                edit();
                tabs.setSelectedIndex(1);
            }

        }
    }//GEN-LAST:event_tblNguoiDungMouseClicked

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        selectImage();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(check()){
            delete();
        }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if(check()){
            update();
            
        }
       
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(check()){
            if(checkTrungTenTaiKhoan_Email(txtTaiKhoan,txtEmail)){
               insert(); 
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
       
        if(index<0){
            DialogHelper.alert(this, "Đầu bảng");
        }
        else{
             edit();
        }
        
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        if(index==tblNguoiDung.getRowCount()){
            DialogHelper.alert(this, "Cuối bảng");
        }
        else{
            edit();
        }
        
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblNguoiDung.getRowCount()-1;
        edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
      if(checkHelper.checkNullText(txtTim))  {
        find();
      }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        ShareHelper.logoff();
        this.dispose();
        new Login().setVisible(true);
        
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void lblBaoCaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoCaoMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblBaoCaoMouseEntered
    
    private void lblBG_BCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseEntered
        // TODO add your handling code here:
        miceEntered(panelBaoCao, lblBaoCao);
      
    }//GEN-LAST:event_lblBG_BCMouseEntered

    private void lblBG_BCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseExited
        // TODO add your handling code here:
        miceExited(panelBaoCao, lblBaoCao);
    }//GEN-LAST:event_lblBG_BCMouseExited

    private void lblTaiKhoan_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelTaiKhoan, lblTaiKhoan);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseEntered

    private void lblTaiKhoan_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelTaiKhoan, lblTaiKhoan);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseExited

    private void lblTongQuan_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuan_BGMouseEntered
        // TODO add your handling code here:
         miceEntered(panelTongQuan, lblTongQuan);
    }//GEN-LAST:event_lblTongQuan_BGMouseEntered

    private void lblTongQuan_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuan_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelTongQuan, lblTongQuan);
    }//GEN-LAST:event_lblTongQuan_BGMouseExited

    private void lblViTien_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseEntered

    private void lblViTien_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseExited

    private void lblThuChi_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelThuChi, lblThuChi);

    }//GEN-LAST:event_lblThuChi_BGMouseEntered

    private void lblThuChi_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelThuChi, lblThuChi);
    }//GEN-LAST:event_lblThuChi_BGMouseExited

    private void lblBG_BCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new BaoCaoJFrame(0).setVisible(true);
    }//GEN-LAST:event_lblBG_BCMouseClicked

    private void lblThuChi_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ThuChiJFrame().setVisible(true);
    }//GEN-LAST:event_lblThuChi_BGMouseClicked

    private void lblViTien_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ViTienJFrame_2().setVisible(true);
    }//GEN-LAST:event_lblViTien_BGMouseClicked

    private void lblTongQuan_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuan_BGMouseClicked
        // TODO add your handling code here:
         this.dispose();
        new TongQuanJFrame().setVisible(true);
    }//GEN-LAST:event_lblTongQuan_BGMouseClicked

    private void lblTaiKhoan_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseClicked
        // TODO add your handling code here:
         this.dispose();
        new TaiKhoanJFrame().setVisible(true);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
      
        
        if(ShareHelper.authenticated()){
            load();
        }
        else{
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        CloseJframe();
    }//GEN-LAST:event_formWindowClosing

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        setStatus(true);
        clear();
        load();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtTimMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseEntered
        // TODO add your handling code here:
        txtTim.setForeground(Color.black);
        if(txtTim.getText().equalsIgnoreCase("Nhập SDT để tìm")){
            txtTim.setText("");
        }
    }//GEN-LAST:event_txtTimMouseEntered

    private void txtTimMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseExited
        // TODO add your handling code here:
        if(txtTim.getText().isEmpty()){
            txtTim.setForeground(new Color(109,109,109));
            txtTim.setText("Nhập SDT để tìm");
        }
    }//GEN-LAST:event_txtTimMouseExited

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
            java.util.logging.Logger.getLogger(NguoiDungJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiDungJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiDungJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiDungJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NguoiDungJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.ButtonGroup grpGT;
    private javax.swing.ButtonGroup grpVT;
    private javax.swing.JLabel iconCalendar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBG_BC;
    private javax.swing.JLabel lblBaoCao;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblSoDem;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTaiKhoan_BG;
    private javax.swing.JLabel lblThuChi;
    private javax.swing.JLabel lblThuChi_BG;
    private javax.swing.JLabel lblTongQuan;
    private javax.swing.JLabel lblTongQuan_BG;
    private javax.swing.JLabel lblViTien;
    private javax.swing.JLabel lblViTien_BG;
    private javax.swing.JPanel panelBaoCao;
    private javax.swing.JPanel panelCapNhat;
    private javax.swing.JPanel panelChuyen;
    private javax.swing.JPanel panelDanhSach;
    private javax.swing.JPanel panelDieuHuong;
    private javax.swing.JPanel panelHinh;
    private javax.swing.JPanel panelNguoiDung;
    private javax.swing.JPanel panelNguoiDung_2;
    private javax.swing.JPanel panelTaiKhoan;
    private javax.swing.JPanel panelThuChi;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelViTien;
    private javax.swing.JRadioButton rdoAdmin;
    private javax.swing.JRadioButton rdoKhac;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNguoiDung;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTenHienThi;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}

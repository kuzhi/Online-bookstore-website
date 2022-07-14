/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLi;

import DAO.ChiDAO;
import DAO.DanhMucChiDAO;
import DAO.DanhMucThuDAO;
import DAO.LoaiViDAO;
import DAO.NguoiDungDAO;
import DAO.TaiKhoanDAO;
import DAO.ThuDAO;
import DAO.ViTienDAO;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Helper.checkHelper;
import Helper.dateHelper;
import Jdialog_GT_DN.Login;
import Model.Chi;
import Model.DanhMucChi;
import Model.DanhMucThu;
import Model.LoaiVi;
import Model.NguoiDung;
import Model.Thu;
import Model.ViTien;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
public class ViTienJFrame_2 extends javax.swing.JFrame {
    
    
    public int index = -1;
    public ViTienDAO vtDao = new ViTienDAO();
    public LoaiViDAO lviDao = new LoaiViDAO();
    public String tenLoaiMoi=null;
    public ThuDAO thuDao = new ThuDAO();
    public ChiDAO chiDao = new ChiDAO();
    public NguoiDungDAO ndDao = new NguoiDungDAO();
    public TaiKhoanDAO tkdao = new TaiKhoanDAO();
    public int soBanDau = 0;
    public int tongTienThu = 0;
    public int tongTienChi = 0;
    public int temp = 0;
    /**
     * Creates new form ViTienJFrame_2
     */
    public ViTienJFrame_2() {
        
        initComponents();
        init();
       
    }

    public void init(){
        setIconImage(ShareHelper.APP_ICON);
        this.setTitle("CHI TIÊU HÀNG NGÀY");
        setStatus(true);
  
    }
    public void CloseJframe(){
                        DialogHelper.alert(this, "Cám ơn đã sử dụng phần mềm! \n Chúc bạn một ngày tốt lành!!");

            System.exit(0);
        
    }
    
    public boolean check(){
        boolean error = false;
        
        if(checkHelper.checkNullText(txtTenVi)&&checkHelper.checkNullText(txtSodu)
                 && checkHelper.checkRdo2(rdoMo, rdoDong)){
            
                 if(checkHelper.checkName(txtTenVi) && checkHelper.checkSoDu(txtSodu)
                         && checkHelper.checkSoAm(txtSodu)){
                     error = true;
                 }
                }
        return error;
    }
    
     public void setStatus(boolean status){
        btnThem.setEnabled(status);
        btnCapNhat.setEnabled(!status);
        rdoDong.setEnabled(!status);
        
        boolean first = this.index > 0;
        boolean last = this.index < tblViTien.getRowCount() - 1;
        
        btnFirst.setEnabled(!status && first);
        btnPrev.setEnabled(!status && first);
        btnLast.setEnabled(!status && last);
        btnNext.setEnabled(!status && last);
        
    }
    public void load(){
        try {
            DefaultTableModel model = (DefaultTableModel) tblViTien.getModel();
        
        model.setRowCount(0);
        List<ViTien> list = vtDao.selectByKeyword(ShareHelper.USER.getTenTaiKhoan());
        
        
        for(ViTien vt:list){
            Object [] rows= {
                vt.getMSVi(),
                vt.getHinh(),
                vt.getTenVi(),
                vt.getTenLoai(),
                vt.getSoDu(),
                vt.toStringTinhTrang()
                    
            };
            model.addRow(rows);
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
        
        
    }
    public void fillCombobox(){
        try {
            
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiVi.getModel();
            model.removeAllElements();
            List<LoaiVi> list = lviDao.select();
            for(LoaiVi lvi : list){
                model.addElement(lvi);
            }
            
        } catch (Exception e) {
             DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
     public LoaiVi getModelLV(){
        LoaiVi loaiVi = new LoaiVi();
        if(lviDao.countSize()<9){
            loaiVi.setMsLoaiVi("LV_0"+String.valueOf(lviDao.countSize()+1));

        }
        else{
            loaiVi.setMsLoaiVi("LV_"+String.valueOf(lviDao.countSize()+1));
        }
        loaiVi.setTenLoaiVi(tenLoaiMoi);
        
        return loaiVi;
    }
    
    
    public ViTien getModelThem(){
         ViTien model = new ViTien();
        
         
         if(vtDao.countSize2()<9){
            model.setMSVi("VI_0"+String.valueOf(vtDao.countSize2()+1));

        }
        else{
            model.setMSVi("VI_"+String.valueOf(vtDao.countSize2()+1));
        }
   
       
         model.setTenVi(txtTenVi.getText());
         model.setHinh(lblHinh.getToolTipText());
         
         LoaiVi lvi = (LoaiVi) cboLoaiVi.getSelectedItem();
         
         model.setMsLoaiVi(lvi.getMsLoaiVi());
         model.setTenLoai(lvi.getTenLoaiVi());
         model.setSoDu(Integer.parseInt(txtSodu.getText()));
          model.setTinhTrang(rdoMo.isSelected()?true:false);
         
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
        return model;
    }
    
    public void tinhTien_CapNhat() {
        
        try {
            int soDu  = Integer.parseInt(txtSodu.getText());
        
        
        if(soDu >soBanDau){
        
        tongTienThu = soDu - soBanDau;
            System.out.println("Thu "+tongTienThu);
            temp = 1;
        }
        else if(soDu < soBanDau){
            tongTienChi = soBanDau- soDu;
                        System.out.println("Chi "+tongTienChi);
            temp = 2;            
        }
        else{ 
            
        }
        if(tongTienThu<0 || tongTienChi<0){
            DialogHelper.alert(this, "Số dư trong ví đã âm /n Vui lòng bạn cập nhật lại");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi ghi số tiền vào việc thu");
        }
        
        
       
    }
    
    public Chi getModelThemChi(){
         Chi model = new Chi();
         tinhTien_CapNhat();
         String date = dateHelper.toString(dateHelper.now()).replace("/", "");
         if(chiDao.countSize()<9){
            model.setMaChi("MC_0"+String.valueOf(thuDao.countSize()+1)+date);

        }
        else{
            model.setMaChi("MC_"+String.valueOf(thuDao.countSize()+1)+date);
        }
        
         DanhMucChiDAO dchiDao = new DanhMucChiDAO();
         DanhMucChi dChi = dchiDao.findById("HMC_01");
         
         
         model.setMsHangMucChi(dChi.getMsHangMucChi());
        
         model.setNgayChi(dateHelper.now());
         model.setMSVI(lblMaSo.getText());
         
//                  model.setTaiKhoan("babaraus123");
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
          
          model.setSoTien(tongTienChi);
        return model;
    }
    
    public Thu getModelThemThu(){
         Thu model = new Thu();
         tinhTien_CapNhat();
         String date = dateHelper.toString(dateHelper.now()).replace("/", "");
         if(thuDao.countSize()<9){
            model.setMaThu("LT_0"+String.valueOf(thuDao.countSize()+1)+date);

        }
        else{
            model.setMaThu("LT_"+String.valueOf(thuDao.countSize()+1)+date);
        }
        
         DanhMucThuDAO dthuDao = new DanhMucThuDAO();
         DanhMucThu dThu = dthuDao.findById("HMT_01");
         
         
         model.setMsHangMucThu(dThu.getMsHangMucThu());
        
         model.setNgayThu(dateHelper.now());
         model.setMSVI(lblMaSo.getText());
         
//                  model.setTaiKhoan("babaraus123");
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
          
          model.setSoTien(tongTienThu);
        return model;
    }
    
    public ViTien getModel(){
         ViTien model = new ViTien();
        
         
        model.setMSVi(lblMaSo.getText());
        
         model.setTenVi(txtTenVi.getText());
         model.setHinh(lblHinh.getToolTipText());
         
         LoaiVi lvi = (LoaiVi) cboLoaiVi.getSelectedItem();
         
         model.setMsLoaiVi(lvi.getMsLoaiVi());
         model.setTenLoai(lvi.getTenLoaiVi());
         model.setSoDu(Integer.parseInt(txtSodu.getText()));
         
          model.setTinhTrang(rdoMo.isSelected()?true:false);
          
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
        return model;
    }
    
    public void setModel(ViTien entity){
      
        lblMaSo.setText(entity.getMSVi());
        txtTenVi.setText(entity.getTenVi());
        cboLoaiVi.setSelectedItem(entity.getTenLoai());
        
        txtSodu.setText(String.valueOf(entity.getSoDu()));
        soBanDau = entity.getSoDu();
       if(entity.getHinh() == null){
            DialogHelper.alert(this, "Người dùng này chưa đặt ảnh ví");
            lblHinh.setIcon(new ImageIcon());
        }else{
            lblHinh.setIcon(ShareHelper.readLogo(entity.getHinh(), lblHinh));
            lblHinh.setToolTipText(entity.getHinh());
        }
        if(entity.isTinhTrang()==true){
            rdoMo.setSelected(true);
        }
        else{
            rdoDong.setSelected(true);
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
            String maVi = (String) tblViTien.getValueAt(index, 0);
            
            ViTien vt1 = vtDao.findById(maVi);
            
            if(vt1 !=null){
                setStatus(false);

                setModel(vt1);
               
            }
            
            lblSoDem.setText(this.index+1+"/"+vtDao.countSize(ShareHelper.USER.getTenTaiKhoan()));
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }
    
    public void btnThemCBO(){
         try {
            tenLoaiMoi= DialogHelper.prompt(this, "Vui lòng nhập loại ví muốn thêm mới?");
            if(checkHelper.checkName2(tenLoaiMoi, this)){
            LoaiVi lvi = getModelLV();
            lviDao.insert(lvi);
            fillCombobox();
            DialogHelper.alert(this, "Thêm loại ví mới thành công");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm loại ví mới thất bại");
        }
    }
    public boolean checkTrungTenVi(JTextField txtTaiKhoan){
        ViTien vt = vtDao.findById(txtTaiKhoan.getText());
        txtTaiKhoan.setBackground(white);
        if(vt == null){
            txtTaiKhoan.setBackground(white);
            return true;
        }
        else{
            txtTaiKhoan.setBackground(Color.red);
            txtTaiKhoan.requestFocus();
            DialogHelper.alert(this, "Tài khoản "+ txtTaiKhoan.getText()+" đã có trong dữ liệu!");
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
    
    //cac nút
    public void insert(){
        
        ViTien model = getModelThem();
             
        
            try {
                
                vtDao.insert(model);
                load();
                clear();
                DialogHelper.alert(this, "Thêm mới thành công");
                tabs.setSelectedIndex(0);
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại");
                e.printStackTrace();
            }
        
        
    }
    
     public void update(){
        ViTien model = getModel();
        Thu modelThu = getModelThemThu();
        Chi modelChi = getModelThemChi();
             try {
                 vtDao.update(model);
                 if(temp ==1){
                     thuDao.insert(modelThu);
                 }
                 else if(temp == 2){
                      chiDao.insert(modelChi);
                 }
                 load();
                 clear();
                 DialogHelper.alert(this, "Cập nhật thành công!");
                 tabs.setSelectedIndex(0);
                 
             } catch (Exception e) {
                 DialogHelper.alert(this, "Cập nhật thất bại");
             }
         
    }
     public void setTrang(){
         lblMaSo.setBackground(white);
         txtTenVi.setBackground(white);
         txtSodu.setBackground(white);
     }
     
     public void clear(){
         setTrang();
         lblMaSo.setText("");
         txtTenVi.setText("");
         cboLoaiVi.setSelectedIndex(0);
         txtSodu.setText("");
         grpTT.clearSelection();
     }
     
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpTT = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jSlider1 = new javax.swing.JSlider();
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
        panelViTien_2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        panelDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViTien = new javax.swing.JTable();
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
        jLabel10 = new javax.swing.JLabel();
        lblMaSo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenVi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboLoaiVi = new javax.swing.JComboBox<>();
        btnThemCbo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSodu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdoMo = new javax.swing.JRadioButton();
        rdoDong = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

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
        panelTongQuan.add(lblTongQuan_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 110));

        panelViTien.setBackground(new java.awt.Color(199, 229, 245));
        panelViTien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblViTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViTien.setForeground(new java.awt.Color(255, 255, 255));
        lblViTien.setText("Ví tiền");
        panelViTien.add(lblViTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vitien.png"))); // NOI18N
        panelViTien.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        lblViTien_BG.setBackground(new java.awt.Color(114, 206, 255));
        lblViTien_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblViTien_BG.setOpaque(true);
        panelViTien.add(lblViTien_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 170, 110));

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
                .addGap(28, 28, 28)
                .addComponent(btnLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChuyenLayout.setVerticalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuyenLayout.createSequentialGroup()
                .addComponent(panelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(panelViTien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(panelThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(panelBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnLogOut)
                .addGap(33, 33, 33))
        );

        panelViTien_2.setBackground(new java.awt.Color(114, 206, 255));

        tabs.setBackground(new java.awt.Color(51, 204, 255));
        tabs.setPreferredSize(new java.awt.Dimension(1040, 649));

        panelDanhSach.setBackground(new java.awt.Color(114, 206, 255));

        tblViTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ SỐ", "HÌNH", "TÊN VÍ", "LOẠI VÍ", "SỐ DƯ", "TÌNH TRẠNG"
            }
        ));
        tblViTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblViTienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblViTien);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
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

        lblSoDem.setText("0/0");

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

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Mã số");

        lblMaSo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Tên ví");

        txtTenVi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Loại ví");

        cboLoaiVi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnThemCbo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Tao.png"))); // NOI18N
        btnThemCbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCboActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Số dư");

        txtSodu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("VNĐ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Tình trạng");

        rdoMo.setBackground(new java.awt.Color(114, 206, 255));
        grpTT.add(rdoMo);
        rdoMo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoMo.setText("Mở");

        rdoDong.setBackground(new java.awt.Color(114, 206, 255));
        grpTT.add(rdoDong);
        rdoDong.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rdoDong.setText("Đóng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaSo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenVi, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cboLoaiVi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemCbo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtSodu, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(35, 35, 35)
                        .addComponent(rdoMo)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDong)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(lblMaSo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cboLoaiVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCbo))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rdoMo)
                    .addComponent(rdoDong))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCapNhatLayout = new javax.swing.GroupLayout(panelCapNhat);
        panelCapNhat.setLayout(panelCapNhatLayout);
        panelCapNhatLayout.setHorizontalGroup(
            panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCapNhatLayout.createSequentialGroup()
                .addGap(0, 196, Short.MAX_VALUE)
                .addComponent(panelDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(panelCapNhatLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        panelCapNhatLayout.setVerticalGroup(
            panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCapNhatLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
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

        jButton4.setBackground(new java.awt.Color(223, 203, 203));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/new (2).png"))); // NOI18N
        jButton4.setText("CLEAR");
        jButton4.setOpaque(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnThem)
                .addGap(50, 50, 50)
                .addComponent(btnCapNhat)
                .addGap(64, 64, 64)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnThem))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelViTien_2Layout = new javax.swing.GroupLayout(panelViTien_2);
        panelViTien_2.setLayout(panelViTien_2Layout);
        panelViTien_2Layout.setHorizontalGroup(
            panelViTien_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelViTien_2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelViTien_2Layout.setVerticalGroup(
            panelViTien_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViTien_2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(panelViTien_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelViTien_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTaiKhoan_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new TaiKhoanJFrame().setVisible(true);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseClicked

    private void lblTaiKhoan_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelTaiKhoan, lblTaiKhoan);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseEntered

    private void lblTaiKhoan_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelTaiKhoan, lblTaiKhoan);
    }//GEN-LAST:event_lblTaiKhoan_BGMouseExited

    private void lblTongQuan_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuan_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new TongQuanJFrame().setVisible(true);
    }//GEN-LAST:event_lblTongQuan_BGMouseClicked

    private void lblTongQuan_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuan_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelTongQuan, lblTongQuan);
    }//GEN-LAST:event_lblTongQuan_BGMouseEntered

    private void lblTongQuan_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongQuan_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelTongQuan, lblTongQuan);
    }//GEN-LAST:event_lblTongQuan_BGMouseExited

    private void lblThuChi_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ThuChiJFrame().setVisible(true);
    }//GEN-LAST:event_lblThuChi_BGMouseClicked

    private void lblThuChi_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelThuChi, lblThuChi);
    }//GEN-LAST:event_lblThuChi_BGMouseEntered

    private void lblThuChi_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuChi_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelThuChi, lblThuChi);
    }//GEN-LAST:event_lblThuChi_BGMouseExited

    private void lblBaoCaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoCaoMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_lblBaoCaoMouseEntered

    private void lblBG_BCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new BaoCaoJFrame(0).setVisible(true);
    }//GEN-LAST:event_lblBG_BCMouseClicked

    private void lblBG_BCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseEntered
        // TODO add your handling code here:
        miceEntered(panelBaoCao, lblBaoCao);

    }//GEN-LAST:event_lblBG_BCMouseEntered

    private void lblBG_BCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBG_BCMouseExited
        // TODO add your handling code here:
        miceExited(panelBaoCao, lblBaoCao);
    }//GEN-LAST:event_lblBG_BCMouseExited

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        ShareHelper.logoff();
        this.dispose();
        new Login().setVisible(true);
        
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void tblViTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblViTienMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()>=0){
            this.index = tblViTien.rowAtPoint(evt.getPoint());
            if(index>=0){
                
                edit();
                tabs.setSelectedIndex(1);
            }

        }
    }//GEN-LAST:event_tblViTienMouseClicked

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        selectImage();
    }//GEN-LAST:event_lblHinhMouseClicked

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
        if(index==tblViTien.getRowCount()){
            DialogHelper.alert(this, "Cuối bảng");
        }
        else{
            edit();
        }

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblViTien.getRowCount()-1;
        edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        if(check()){
        if(checkTrungTenVi(txtTenVi)){
            insert();
        }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
         if(check()){
            update();
         }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemCboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCboActionPerformed
        // TODO add your handling code here:
        btnThemCBO();
       

    }//GEN-LAST:event_btnThemCboActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clear();
        setStatus(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void lblNguoiDung_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelNguoiDung, lblNguoiDung);
    }//GEN-LAST:event_lblNguoiDung_BGMouseEntered

    private void lblNguoiDung_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseClicked
        // TODO add your handling code here:]
        this.dispose();
        NguoiDung nd = ndDao.findByIdTK(ShareHelper.USER.getTenTaiKhoan());
        if(nd.isVaiTro()){
            new NguoiDungJFrame().setVisible(true);
        }
        else{
             new NguoiDungJFrame_2().setVisible(true);
        }
       
        
    }//GEN-LAST:event_lblNguoiDung_BGMouseClicked

    private void lblNguoiDung_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelNguoiDung, lblNguoiDung);
    }//GEN-LAST:event_lblNguoiDung_BGMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if(ShareHelper.authenticated()){
            load();
            
            fillCombobox();
            setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(ViTienJFrame_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViTienJFrame_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViTienJFrame_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViTienJFrame_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViTienJFrame_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCbo;
    private javax.swing.JComboBox<String> cboLoaiVi;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.ButtonGroup grpTT;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JLabel lblBG_BC;
    private javax.swing.JLabel lblBaoCao;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblMaSo;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblNguoiDung_BG;
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
    private javax.swing.JPanel panelTaiKhoan;
    private javax.swing.JPanel panelThuChi;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelViTien;
    private javax.swing.JPanel panelViTien_2;
    private javax.swing.JRadioButton rdoDong;
    private javax.swing.JRadioButton rdoMo;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblViTien;
    private javax.swing.JTextField txtSodu;
    private javax.swing.JTextField txtTenVi;
    // End of variables declaration//GEN-END:variables
}

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
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ThuChiJFrame extends javax.swing.JFrame {
    ChiDAO chiDao = new ChiDAO();
    ThuDAO thuDao = new ThuDAO();
    DanhMucThuDAO dmThuDao = new DanhMucThuDAO();
    DanhMucChiDAO dmChiDao = new DanhMucChiDAO();
    LoaiViDAO lviDao = new LoaiViDAO();
    ViTienDAO vtDao = new ViTienDAO();
    TaiKhoanDAO tkDao = new TaiKhoanDAO();
    NguoiDungDAO ndDao = new NguoiDungDAO();
    
     public String tenLoaiMoi=null;
     public String msVI = null;
    public int index = -1;
    public int index2 = -1;
    public String maThu= null;
    public String maChi = null;
    public int soBanDau = 0;
    
    
    /**
     * Creates new form ThuChiJFrame
     */
    public ThuChiJFrame() {
        initComponents();
        
     
       
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
        
        boolean first = this.index > 0;
        boolean last = this.index < tblThu.getRowCount() - 1;
        
         boolean first2 = this.index2 > 0;
        boolean last2 = this.index2< tblChi.getRowCount() - 1;
        
        btnFirst.setEnabled(!status && first);
        btnPrev.setEnabled(!status && first);
        btnLast.setEnabled(!status && last);
        btnNext.setEnabled(!status && last);
        
        btnFirst.setEnabled(!status && first2);
        btnPrev.setEnabled(!status && first2);
        btnLast.setEnabled(!status && last2);
        btnNext.setEnabled(!status && last2);
        
    }
    public void load(){
        try {
            DefaultTableModel model = (DefaultTableModel) tblChi.getModel();
            DefaultTableModel model2 = (DefaultTableModel) tblThu.getModel();
        model.setRowCount(0);
        model2.setRowCount(0);
        List<Chi> list = chiDao.selectByKeyword(ShareHelper.USER.getTenTaiKhoan());
         List<Thu> listThu = thuDao.selectByKeyword(ShareHelper.USER.getTenTaiKhoan());
//        List<Chi> list = chiDao.selectByKeyword("babaraus123");
//        List<Thu> listThu = thuDao.selectByKeyword("babaraus123");
        for(Chi vt:list){
            Object [] rows= {
                vt.getMaChi(),
                dateHelper.toString(vt.getNgayChi()),
                vt.getTenHangMucChi(),
                vt.getTenVi(),
                vt.getSoTien()
                
                    
            };
            model.addRow(rows);
        }
        
        for(Thu vt:listThu){
            Object [] rows= {
                vt.getMaThu(),
                dateHelper.toString(vt.getNgayThu()),
                vt.getTenHangMucThu(),
                vt.getTenVi(),
                vt.getSoTien()
                
                    
            };
            model2.addRow(rows);
        }
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
        
        
    }
   //fillCombobox
    public void fillCombobox(){
        try {
            DefaultComboBoxModel modelThuChi = (DefaultComboBoxModel) cboThuChi.getModel();
            modelThuChi.removeAllElements();
            modelThuChi.addElement("Thu");
            modelThuChi.addElement("Chi");

            
        } catch (Exception e) {
             DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void fillComboboxThu(){
        try {
             DefaultComboBoxModel model = (DefaultComboBoxModel) cboHangMuc.getModel();
            model.removeAllElements();
            List<DanhMucThu> list = dmThuDao.select();
            for(DanhMucThu lvi : list){
                model.addElement(lvi);
            }
        } catch (Exception e) {
                         DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");

        }
       
    }
   
    public void fillComboboxChi(){
        try {
             DefaultComboBoxModel model = (DefaultComboBoxModel) cboHangMuc.getModel();
            model.removeAllElements();
            List<DanhMucChi> list = dmChiDao.select();
            for(DanhMucChi lvi : list){
                model.addElement(lvi);
            }
        } catch (Exception e) {
                         DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");

        }
       
    }
    
    public void fillComboboxTenVi(){
        try {
             DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenVi.getModel();
            model.removeAllElements();
            List<ViTien> list = vtDao.selectByKeyword(ShareHelper.USER.getTenTaiKhoan());
//            List<ViTien> list = vtDao.selectByKeyword("babaraus123");
            
            for(ViTien vt : list){
                model.addElement(vt);
            }
        } catch (Exception e) {
                         DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
                e.printStackTrace();

        }
    }
    
    ///
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
    
    public void CloseJframe(){
                
                     DialogHelper.alert(this, "Cám ơn đã sử dụng phần mềm! \n Chúc bạn một ngày tốt lành!!");

                    System.exit(0);
            
    }
    //check loi
     public boolean check(){
        
        boolean error = false;
        
        if(checkHelper.checkNullText(txtSodu) 
                && checkHelper.checkNullText(txtDate)){
            if(checkHelper.checkSoDu(txtSodu) && checkHelper.checkSoAm(txtSodu)
                && checkHelper.checkDate(txtDate))
            {
                error = true;
            }
        }
        
        
        return error;
    }
    
    ///
    public void btnThemCBO(){
         try {
            tenLoaiMoi= DialogHelper.prompt(this, "Vui lòng nhập hạng mục thêm mới?");
            
            if(checkHelper.checkName2(tenLoaiMoi, this)){
            if(cboThuChi.getSelectedIndex()==0){
            DanhMucThu dmThu = getModelDMThu();
            dmThuDao.insert(dmThu);
            fillComboboxThu();
            DialogHelper.alert(this, "Thêm hạng mục mới thành công");
            }
            else{
                DanhMucChi dmChi = getModelDMChi();
                dmChiDao.insert(dmChi);
                fillComboboxChi();
                DialogHelper.alert(this, "Thêm hạng mục mới thành công");
            }
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm hạng mục mới thất bại");
        }
    }
    
    
    
//    
//    //get set
    public DanhMucChi getModelDMChi(){
        DanhMucChi dmChi = new DanhMucChi();
        if(dmChiDao.countSize()<9){
            dmChi.setMsHangMucChi("HMC_0"+String.valueOf(dmChiDao.countSize()+1));

        }
        else{
            dmChi.setMsHangMucChi("HMC_"+String.valueOf(dmChiDao.countSize()+1));
        }
        dmChi.setTenHangMuc(tenLoaiMoi);
        
        return dmChi;
    }
    
    public DanhMucThu getModelDMThu(){
        DanhMucThu dmThu = new DanhMucThu();
        if(dmThuDao.countSize()<9){
            dmThu.setMsHangMucThu("HMT_0"+String.valueOf(dmThuDao.countSize()+1));

        }
        else{
            dmThu.setMsHangMucThu("HTM_"+String.valueOf(dmThuDao.countSize()+1));
        }
        dmThu.setTenHangMuc(tenLoaiMoi);
        
        return dmThu;
    }
    
    public Thu getModelThemThu(){
         Thu model = new Thu();
               
         String date = txtDate.getText().replace("/", "");
         if(thuDao.countSize()<9){
            model.setMaThu("LT_0"+String.valueOf(thuDao.countSize()+1)+date);

        }
        else{
            model.setMaThu("LT_"+String.valueOf(thuDao.countSize()+1)+date);
        }
        
        
         DanhMucThu dThu = (DanhMucThu) cboHangMuc.getSelectedItem();
         ViTien vt = (ViTien) cboTenVi.getSelectedItem();
         
         model.setMsHangMucThu(dThu.getMsHangMucThu());
        
         model.setNgayThu(dateHelper.toDate(txtDate.getText()));
         model.setMSVI(vt.getMSVi());
         
//                  model.setTaiKhoan("babaraus123");
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
          
          model.setSoTien(Integer.parseInt(txtSodu.getText()));
        return model;
    }
    
    public Chi getModelThemChi(){
         Chi model = new Chi();
               String date = txtDate.getText().replace("/", "");
         
         if(thuDao.countSize()<9){
             System.out.println(String.valueOf(chiDao.countSize()+1));
                         model.setMaChi("MC_0"+String.valueOf(chiDao.countSize()+1)+date);

        }
        else{
            System.out.println(String.valueOf(chiDao.countSize()+1));
                        model.setMaChi("MC_"+String.valueOf(chiDao.countSize()+1)+date);


        }
        
        
         DanhMucChi dChi = (DanhMucChi) cboHangMuc.getSelectedItem();
         ViTien vt = (ViTien) cboTenVi.getSelectedItem();
         
         model.setMsHangMucChi(dChi.getMsHangMucChi());
        
         model.setNgayChi(dateHelper.toDate(txtDate.getText()));
         model.setMSVI(vt.getMSVi());
         
//                 model.setTaiKhoan("babaraus123");
          
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
          
          model.setSoTien(Integer.parseInt(txtSodu.getText()));
        return model;
    }
    
    public Thu getModelThu(){
         Thu model = new Thu();
        
         
        model.setMaThu(maThu);
            
         DanhMucThu dThu = (DanhMucThu) cboHangMuc.getSelectedItem();
         ViTien vt = (ViTien) cboTenVi.getSelectedItem();
         
         model.setMsHangMucThu(dThu.getMsHangMucThu());
        
         model.setNgayThu(dateHelper.toDate(txtDate.getText()));
         model.setMSVI(vt.getMSVi());
         
//          model.setTaiKhoan("babaraus123");
          
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
          model.setSoTien(Integer.parseInt(txtSodu.getText()));
        return model;
    }
//    
    public Chi getModelChi(){
        Chi model = new Chi();
               
         
         model.setMaChi(maChi);
        
        
         DanhMucChi dChi = (DanhMucChi) cboHangMuc.getSelectedItem();
         ViTien vt = (ViTien) cboTenVi.getSelectedItem();
         
         model.setMsHangMucChi(dChi.getMsHangMucChi());
        
         model.setNgayChi(dateHelper.toDate(txtDate.getText()));
         model.setMSVI(vt.getMSVi());
        
//                 model.setTaiKhoan("babaraus123");
          model.setTaiKhoan(ShareHelper.USER.getTenTaiKhoan());
          
          model.setSoTien(Integer.parseInt(txtSodu.getText()));
        return model;
    }
    
    public void setModelThu(Thu entity){
        maThu = entity.getMaThu();
        txtSodu.setText(String.valueOf(entity.getSoTien()));
        txtDate.setText(dateHelper.toString(entity.getNgayThu()));
        cboHangMuc.getModel().setSelectedItem(dmThuDao.findById(entity.getMsHangMucThu()));
        cboTenVi.getModel().setSelectedItem(vtDao.findById(entity.getMSVI()));
        soBanDau = Integer.parseInt(txtSodu.getText());
        cboTenVi.setSelectedItem(entity.getTenVi());
       
        
        
    }
    
    public void setModelChi(Chi entity){
        maChi = entity.getMaChi();
        txtSodu.setText(String.valueOf(entity.getSoTien()));
        txtDate.setText(dateHelper.toString(entity.getNgayChi()));
        cboHangMuc.getModel().setSelectedItem(dmChiDao.findById(entity.getMsHangMucChi()));
        cboTenVi.getModel().setSelectedItem(vtDao.findById(entity.getMSVI()));

        
        soBanDau = Integer.parseInt(txtSodu.getText());
        
    }
    
     public void editThu(){
        try {
            //lay ma  tu index
             cboThuChi.setSelectedIndex(0);
            String maThu = (String) tblThu.getValueAt(this.index, 0);
            
            Thu vt1 = thuDao.findById(maThu);
            
            if(vt1 != null){
                setStatus(false);
                setModelThu(vt1);
              
            }
            
//              lblSoDem.setText(this.index+1+"/"+thuDao.countSizeThu("babaraus123"));      
            lblSoDem.setText(this.index+1+"/"+thuDao.countSizeThu(ShareHelper.USER.getTenTaiKhoan()));
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }
    
     public void editChi(){
        try {
            //lay ma  tu index
            cboThuChi.setSelectedIndex(1);
            String maChi = (String) tblChi.getValueAt(this.index2, 0);
            
            Chi vt1 = chiDao.findById(maChi);
            
            if(vt1 != null){
                setModelChi(vt1);
                setStatus(false);
            }
            
//            lblSoDem.setText(this.index2+1+"/"+chiDao.countSizeChi("babaraus123"));
            
            lblSoDem.setText(this.index2+1+"/"+chiDao.countSizeChi(ShareHelper.USER.getTenTaiKhoan()));
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }
     
    public void tinhTienChi() {
        ViTien vt = (ViTien) cboTenVi.getSelectedItem();

        
        int tongTienVi = vt.getSoDu();
        int tongTien = 0;
        tongTien = tongTienVi - Integer.parseInt(txtSodu.getText());
        vtDao.updateTien(tongTien, vt.getMSVi());
        if(vt.getSoDu()<=0){
            DialogHelper.alert(this, "Số dư trong ví đã âm \n Vui lòng thay đổi kế hoạch chi");
        }
    }
     
        public void tinhTienThu() {
        ViTien vt = (ViTien) cboTenVi.getSelectedItem();

        
        int tongTienVi = vt.getSoDu();
        int tongTien = 0;
        tongTien = tongTienVi + Integer.parseInt(txtSodu.getText());
        vtDao.updateTien(tongTien, vt.getMSVi());
       
    }
    
        public void tinhTienChi_CapNhat() {
        ViTien vt = (ViTien) cboTenVi.getSelectedItem();

        
        int tongTienVi = vt.getSoDu();
        int soDu  = Integer.parseInt(txtSodu.getText());
        int tongTien = 0;
        
        if(soDu >soBanDau){
        
        tongTien = tongTienVi -(soDu - soBanDau);
        vtDao.updateTien(tongTien, vt.getMSVi());
        }
        else if(soDu < soBanDau){
            tongTien = tongTienVi + (soBanDau- soDu);
            vtDao.updateTien(tongTien, vt.getMSVi());
        }
        else{
            
        }
        
        
        
        if(vt.getSoDu()<=0){
            DialogHelper.alert(this, "Số dư trong ví đã âm ");
        }
    }
     
        public void tinhTienThu_CapNhat() {
        ViTien vt = (ViTien) cboTenVi.getSelectedItem();

        
        int tongTienVi = vt.getSoDu();
        int soDu  = Integer.parseInt(txtSodu.getText());
        int tongTien = 0;
        
        if(soDu >soBanDau){
        
        tongTien = tongTienVi +(soDu - soBanDau);
        vtDao.updateTien(tongTien, vt.getMSVi());
        }
        else if(soDu < soBanDau){
            tongTien = tongTienVi - (soBanDau- soDu);
            vtDao.updateTien(tongTien, vt.getMSVi());
        }
        else{
            
        }
        if(vt.getSoDu()<=0){
            DialogHelper.alert(this, "Số dư trong ví đã âm ");
        }
       
    }
        
        
        public void tinhTienThu_Xoa() {
        ViTien vt = (ViTien) cboTenVi.getSelectedItem();

        
        int tongTienVi = vt.getSoDu();
        int tongTien = 0;
        tongTien = tongTienVi - Integer.parseInt(txtSodu.getText());
        vtDao.updateTien(tongTien, vt.getMSVi());
        if(vt.getSoDu()<=0){
            DialogHelper.alert(this, "Số dư trong ví đã âm \n Vui lòng thay đổi kế hoạch chi");
        }
    }
        public void tinhTienChi_Xoa() {
        ViTien vt = (ViTien) cboTenVi.getSelectedItem();

        
        int tongTienVi = vt.getSoDu();
        int tongTien = 0;
        tongTien = tongTienVi + Integer.parseInt(txtSodu.getText());
        vtDao.updateTien(tongTien, vt.getMSVi());
        
    }
     //cac nút
     
     public void insertThu(){
        
        Thu model = getModelThemThu();
             
        
            try {
                ViTien vt = (ViTien) cboTenVi.getSelectedItem();
                if(vt.isTinhTrang()){
                thuDao.insert(model);
                tinhTienThu();
                load();
                clear();
                DialogHelper.alert(this, "Thêm mới thành công");
                tabs.setSelectedIndex(0);
                }
                else{
                    DialogHelper.alert(this, "Ví bạn chọn bị trong tình trạng đóng nên không được sử dụng");
                    clear();
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại");
                e.printStackTrace();
            }
        
        
    }
    
     public void insertChi(){
        
        Chi model = getModelThemChi();
             
        
            try {
                ViTien vt = (ViTien) cboTenVi.getSelectedItem();
                if(vt.isTinhTrang()){
                    chiDao.insert(model);
                    tinhTienChi();
                    load();
                    clear();
                    DialogHelper.alert(this, "Thêm mới thành công");
                    tabs.setSelectedIndex(1);
                }
                else{
                    DialogHelper.alert(this, "Ví bạn chọn bị trong tình trạng đóng nên không được sử dụng");
                    clear();
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại");
               e.printStackTrace();
            }
        
        
    }
     
     public void updateThu(){
         
        Thu model = getModelThu();
       
             try {
                 ViTien vt = (ViTien) cboTenVi.getSelectedItem();
                if(vt.isTinhTrang()){
                    thuDao.update(model);
                    tinhTienThu_CapNhat();
                    load();
                    clear();
                    DialogHelper.alert(this, "Cập nhật thành công!");
                    tabs.setSelectedIndex(0);
                }
                else{
                    DialogHelper.alert(this, "Ví bạn chọn bị trong tình trạng đóng nên không được sử dụng");
                    clear();
                } 
             } catch (Exception e) {
                 DialogHelper.alert(this, "Cập nhật thất bại");
             }
         
    }
     
     public void updateChi(){
        Chi model = getModelChi();
       
             try {
                  ViTien vt = (ViTien) cboTenVi.getSelectedItem();
                if(vt.isTinhTrang()){
                    chiDao.update(model);
                    tinhTienChi_CapNhat();
                    load();
                    clear();
                    DialogHelper.alert(this, "Cập nhật thành công!");
                    tabs.setSelectedIndex(1);
                 }
                else{
                    DialogHelper.alert(this, "Ví bạn chọn bị trong tình trạng đóng nên không được sử dụng");
                    clear();
                } 
             } catch (Exception e) {
                 DialogHelper.alert(this, "Cập nhật thất bại");
             }
         
    }
     public void deleteThu(){
         if(DialogHelper.confirm(this, "Bạn có thực sự muốn xóa phiếu thu này chứ?")){
             try {
                    String xoaMaThu = String.valueOf(tblThu.getValueAt(this.index, 0));
                ViTien vt = (ViTien) cboTenVi.getSelectedItem();
                if(vt.isTinhTrang()){    
                    thuDao.delete(xoaMaThu);
                    tinhTienThu_Xoa();
                    load();
                    DialogHelper.alert(this, "Xóa thành công!");
                    clear();
                    tabs.setSelectedIndex(0);
                }
                else{
                    DialogHelper.alert(this, "Ví bạn chọn bị trong tình trạng đóng nên không được sử dụng");
                    clear();
                }  
             } catch (Exception e) {
                 DialogHelper.alert(this, "Xóa thất bại");
                 e.printStackTrace();
             }
        }
     }
     
     public void deleteChi(){
         if(DialogHelper.confirm(this, "Bạn có thực sự muốn xóa phiếu chi này chứ?")){
             try {
                 String xoaMaChi = String.valueOf(tblChi.getValueAt(this.index2, 0));
                 ViTien vt = (ViTien) cboTenVi.getSelectedItem();
                if(vt.isTinhTrang()){
                    chiDao.delete(xoaMaChi);
                    tinhTienChi_Xoa();
                    load();
                    DialogHelper.alert(this, "Xóa thành công!");
                    clear();
                    tabs.setSelectedIndex(1);
                }
                else{
                    DialogHelper.alert(this, "Ví bạn chọn bị trong tình trạng đóng nên không được sử dụng");
                    clear();
                }  
             } catch (Exception e) {
                 DialogHelper.alert(this, "Xóa thất bại");
                 e.printStackTrace();
             }
        }
     }
     
     public void setTrang(){
         txtSodu.setBackground(white);
         txtDate.setBackground(white);
     }
     
     
     public void clear(){
         setTrang();
         cboThuChi.setSelectedIndex(0);
         cboHangMuc.setSelectedIndex(0);
         cboTenVi.setSelectedIndex(0);
         txtSodu.setText("");
         txtDate.setText("");
         maChi= null;
         maThu=null;
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
        panelThuChi_2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        panelDSThu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThu = new javax.swing.JTable();
        panelDSChi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChi = new javax.swing.JTable();
        panelCapNhat = new javax.swing.JPanel();
        panelDieuHuong = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblSoDem = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblMaSo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboHangMuc = new javax.swing.JComboBox<>();
        btnThemCbo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSodu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        lblChuyenDoi = new javax.swing.JLabel();
        cboThuChi = new javax.swing.JComboBox<>();
        cboTenVi = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

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
        panelViTien.add(lblViTien_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 115));

        panelThuChi.setBackground(new java.awt.Color(199, 229, 245));
        panelThuChi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThuChi.setForeground(new java.awt.Color(255, 255, 255));
        lblThuChi.setText("Thu-Chi");
        panelThuChi.add(lblThuChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 20));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/thuchi.png"))); // NOI18N
        panelThuChi.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        lblThuChi_BG.setBackground(new java.awt.Color(114, 206, 255));
        lblThuChi_BG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblThuChi_BG.setOpaque(true);
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
                .addComponent(panelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(panelViTien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(panelBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnLogOut)
                .addGap(33, 33, 33))
        );

        panelThuChi_2.setBackground(new java.awt.Color(114, 206, 255));

        tabs.setBackground(new java.awt.Color(51, 204, 255));
        tabs.setPreferredSize(new java.awt.Dimension(1040, 649));

        panelDSThu.setBackground(new java.awt.Color(114, 206, 255));

        tblThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ THU", "NGÀY", "HẠNG MỤC", "TÊN VÍ", "THU"
            }
        ));
        tblThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThu);

        javax.swing.GroupLayout panelDSThuLayout = new javax.swing.GroupLayout(panelDSThu);
        panelDSThu.setLayout(panelDSThuLayout);
        panelDSThuLayout.setHorizontalGroup(
            panelDSThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDSThuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelDSThuLayout.setVerticalGroup(
            panelDSThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDSThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );

        tabs.addTab("THU", panelDSThu);

        panelDSChi.setBackground(new java.awt.Color(114, 206, 255));

        tblChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ CHI", "NGÀY", "HẠNG MỤC", "TÊN VÍ", "CHI"
            }
        ));
        tblChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChi);

        javax.swing.GroupLayout panelDSChiLayout = new javax.swing.GroupLayout(panelDSChi);
        panelDSChi.setLayout(panelDSChiLayout);
        panelDSChiLayout.setHorizontalGroup(
            panelDSChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDSChiLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelDSChiLayout.setVerticalGroup(
            panelDSChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDSChiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );

        tabs.addTab("CHI", panelDSChi);

        panelCapNhat.setBackground(new java.awt.Color(114, 206, 255));

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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMaSo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Số tiền");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Chọn hạng mục");

        cboHangMuc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnThemCbo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Tao.png"))); // NOI18N
        btnThemCbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCboActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Tên ví");

        txtSodu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("VNĐ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Ngày");

        lblChuyenDoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cboThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cboThuChi.setForeground(new java.awt.Color(102, 102, 255));
        cboThuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThuChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtSodu, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel8))
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboTenVi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboHangMuc, javax.swing.GroupLayout.Alignment.LEADING, 0, 349, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addComponent(btnThemCbo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblMaSo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(lblChuyenDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cboThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaSo, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(lblChuyenDoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(cboThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txtSodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboHangMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCbo))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboTenVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCapNhatLayout = new javax.swing.GroupLayout(panelCapNhat);
        panelCapNhat.setLayout(panelCapNhatLayout);
        panelCapNhatLayout.setHorizontalGroup(
            panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCapNhatLayout.createSequentialGroup()
                .addGap(0, 180, Short.MAX_VALUE)
                .addGroup(panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(181, 181, 181))
        );
        panelCapNhatLayout.setVerticalGroup(
            panelCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCapNhatLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
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

        btnXoa.setBackground(new java.awt.Color(223, 203, 203));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trash.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setOpaque(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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
                .addGap(42, 42, 42)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
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
                    .addComponent(btnThem)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelThuChi_2Layout = new javax.swing.GroupLayout(panelThuChi_2);
        panelThuChi_2.setLayout(panelThuChi_2Layout);
        panelThuChi_2Layout.setHorizontalGroup(
            panelThuChi_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelThuChi_2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelThuChi_2Layout.setVerticalGroup(
            panelThuChi_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThuChi_2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabs.getAccessibleContext().setAccessibleName("Thu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelThuChi_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelThuChi_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void lblNguoiDung_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelNguoiDung, lblNguoiDung);
    }//GEN-LAST:event_lblNguoiDung_BGMouseEntered

    private void lblNguoiDung_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDung_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelNguoiDung, lblNguoiDung);
    }//GEN-LAST:event_lblNguoiDung_BGMouseExited

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

    private void tblThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()>=0){
            this.index = tblThu.rowAtPoint(evt.getPoint());
            if(index>=0){
                String tenHangMucThu = tblThu.getValueAt(this.index, 3).toString();
                
//                String tenVi = tblThu.getValueAt(this.index, 4).toString();
//                for(int i=0; i< cboHangMuc.getItemCount();i++){
//                   cboHangMuc.setToolTipText(cboHangMuc.getItemAt(index));
//                   
//                }
               
          
               
                editThu();
                tabs.setSelectedIndex(2);
                
            }

        }
    }//GEN-LAST:event_tblThuMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.index2 = 0;
        if(cboThuChi.getSelectedItem().equals("Thu")){
            editThu();
        }
       if(cboThuChi.getSelectedItem().equals("Chi")){
             editChi();
            }
        
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.index2--;
        if(index<0 ){
            DialogHelper.alert(this, "Đầu bảng");
        }
        else if(index2<0){
            DialogHelper.alert(this, "Đầu bảng");
        }
        else{
            if(cboThuChi.getSelectedItem().equals("Thu")){
            editThu();
        }
            if(cboThuChi.getSelectedItem().equals("Chi")){
             editChi();
            }
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.index2++;
        if(index==tblThu.getRowCount()  ){
            DialogHelper.alert(this, "Cuối bảng");
        }
        else if(index2==tblChi.getRowCount()){
            DialogHelper.alert(this, "Cuối bảng");
        }
        else{
            if(cboThuChi.getSelectedItem().equals("Thu")){
            editThu();
            }
            if(cboThuChi.getSelectedItem().equals("Chi")){
             editChi();
            }
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblThu.getRowCount()-1;
        this.index2 = tblChi.getRowCount()-1;
        if(cboThuChi.getSelectedItem().equals("Thu")){
            editThu();
        }
        if(cboThuChi.getSelectedItem().equals("Chi")){
             editChi();
            }
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnThemCboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCboActionPerformed
        // TODO add your handling code here:
        
        
        btnThemCBO();
    

    }//GEN-LAST:event_btnThemCboActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
      
           if(cboThuChi.getSelectedItem().equals("Thu")){
            if(check()){
                insertThu();
            }
            
               
           }
           else{
             if(check()){
                 insertChi();
             } 
               
           }
            
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if(cboThuChi.getSelectedItem().equals("Thu")){
             if(check()){  
            updateThu();
             }  
           }
           else{
              if(check()){
            updateChi(); 
              }
           }
        
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clear();
        setStatus(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void lblViTien_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseEntered

    private void lblViTien_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseExited

    private void lblViTien_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseClicked
        // TODO add your handling code here:        
        this.dispose();
        new ViTienJFrame_2().setVisible(true);
    }//GEN-LAST:event_lblViTien_BGMouseClicked

    private void tblChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()>=0){
            this.index2 = tblChi.rowAtPoint(evt.getPoint());
            if(index2>=0){
               
                editChi();
                tabs.setSelectedIndex(2);
               
            }

        }
    }//GEN-LAST:event_tblChiMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
//        ShareHelper.USER= tkDao.findById("babaraus123");
        if(ShareHelper.authenticated()){
            init();
            load();
            fillCombobox();
            fillComboboxTenVi();          
            setLocationRelativeTo(null);
        }
        else{
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void cboThuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuChiActionPerformed
        // TODO add your handling code here:
        switch( cboThuChi.getSelectedIndex()){
                case 0:
                    fillComboboxThu();
                    break;
                
                case 1:
                    fillComboboxChi();
                    break;
            }
    }//GEN-LAST:event_cboThuChiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(cboThuChi.getSelectedItem().equals("Thu")){
            if(check()){
            deleteThu();
            }
        }
        else{
            if(check()){
            deleteChi();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
            java.util.logging.Logger.getLogger(ThuChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThuChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThuChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThuChiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThuChiJFrame().setVisible(true);
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
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboHangMuc;
    private javax.swing.JComboBox<String> cboTenVi;
    private javax.swing.JComboBox<String> cboThuChi;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBG_BC;
    private javax.swing.JLabel lblBaoCao;
    private javax.swing.JLabel lblChuyenDoi;
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
    private javax.swing.JPanel panelDSChi;
    private javax.swing.JPanel panelDSThu;
    private javax.swing.JPanel panelDieuHuong;
    private javax.swing.JPanel panelNguoiDung;
    private javax.swing.JPanel panelTaiKhoan;
    private javax.swing.JPanel panelThuChi;
    private javax.swing.JPanel panelThuChi_2;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelViTien;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblChi;
    private javax.swing.JTable tblThu;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtSodu;
    // End of variables declaration//GEN-END:variables
}

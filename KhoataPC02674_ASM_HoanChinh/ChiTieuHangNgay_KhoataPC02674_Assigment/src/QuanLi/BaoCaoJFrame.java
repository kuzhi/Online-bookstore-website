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
import Helper.checkHelper;
import Helper.dateHelper;
import Jdialog_GT_DN.Login;
import Model.NguoiDung;
import Model.ThongKe_Nam;
import Model.ThongKe_Ngay;
import Model.ThongKe_Thang;
import Model.ViTien;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class BaoCaoJFrame extends javax.swing.JFrame {
    public TaiKhoanDAO tkDao = new TaiKhoanDAO();
    public ThongKeDAO thongkeDao = new ThongKeDAO();
    public ViTienDAO vtDao = new ViTienDAO();
    public NguoiDungDAO ndDao = new NguoiDungDAO();
    /**
     * Creates new form BaoCaoJFrame
     */
    public BaoCaoJFrame(int index) {
        initComponents();
       tabs.setSelectedIndex(index);
    }
    
     public void init(){
        setIconImage(ShareHelper.APP_ICON);
        this.setTitle("CHI TIÊU HÀNG NGÀY");
        setLocationRelativeTo(null);
        txtTongThuChi_Ngay.setEditable(false);
        txtTongChi_Ngay.setEditable(false);
        txtTongThu_Ngay.setEditable(false);
         getTaiChinh();
         load();
        fillComboBoxChi();
        fillComboBoxThu();
        fillComboBoxThuChi();
        showBar();
    }
     public void showBar(){
         showBarChart_Nam_ThuChi();

         showBarChart_Nam_Thu();

         showBarChart_Nam_Chi();

         showTongTien();
     }
     
     public void showTongTien(){
    
         getTaiChinh_ThuChi_Nam();

         getTaiChinh_Chi_Nam();

         getTaiChinh_Thu_Nam();
     }
     
   public void CloseJframe(){
                
            DialogHelper.alert(this, "Cám ơn đã sử dụng phần mềm! \n Chúc bạn một ngày tốt lành!!");
            System.exit(0);
        
    }
    
   
   
   
    public boolean checkThuChi(JTextField txtThang, JTextField txtNam){
        boolean error = false;
        if(checkHelper.checkNullText(txtThang)&&checkHelper.checkNullText(txtNam)){
            if(checkHelper.checkThang(txtThang) && checkHelper.checkNam(txtNam)){
                error = true;
            }
        }
        
        return error;
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
    
    
    //Get tai chinh
    
    public void getTaiChinh(){
        try {
            List<String> taichinh = thongkeDao.getTaiChinh(ShareHelper.USER.getTenTaiKhoan());
        String TongTien = null;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu tài chính hiện tại!!");
                   
        }
        else{
            
        
        for(String tc : taichinh){
            TongTien = tc;
        }
        
        if(txtTaiChinh.getText().equalsIgnoreCase("")){
                    txtTaiChinh.setForeground(new Color(93, 255, 51));
                    txtTaiChinh.setText("0 VNĐ");
                }
                else{
                    txtTaiChinh.setForeground(new Color(93, 255, 51));
                    txtTaiChinh.setText(TongTien+ " VNĐ");
                }
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }       
        
    }
    
   public void load(){
        try {
            DefaultTableModel model = (DefaultTableModel) tblTaiChinh.getModel();
        
        model.setRowCount(0);
        List<ViTien> list = vtDao.selectByKeyword(ShareHelper.USER.getTenTaiKhoan());
        Object [] ten= {"TÊN VÍ","SỐ DƯ(VNĐ)"};
        model.setColumnIdentifiers(ten);
        
        for(ViTien vt:list){
            Object [] rows= {
                
                
                vt.getTenVi(),                
                vt.getSoDu(),

                    
            };
            
            model.addRow(rows);
            
        }
        
       
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
        
        
    }
   
    //fill combobox
    public void fillComboBoxThu(){
        try {
          DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang_Thu.getModel();
        model.removeAllElements();
        
        List<Integer> nam = thongkeDao.getNam_Thu(ShareHelper.USER.getTenTaiKhoan());
        if(nam.isEmpty()){
            DialogHelper.alert(this, "Tài khoản này chưa có khoản thu");
        }
        else{
        for(Integer nT: nam){
            model.addElement(nT.intValue());
        }  
        cboThang_Thu.setSelectedIndex(0);
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
        
        
    }
   public void fillComboBoxThuChi(){
        try {
          DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang_ThuChi.getModel();
        model.removeAllElements();
        
        List<Integer> nam = thongkeDao.getNam_Thu(ShareHelper.USER.getTenTaiKhoan());
        if(nam.isEmpty()){
            DialogHelper.alert(this, "Tài khoản này chưa có khoản thu chi");
        }
        else{
             for(Integer nT: nam){
            model.addElement(nT.intValue());
        }  
        cboThang_ThuChi.setSelectedIndex(0);
        }
       
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
        
        
    }
    public void fillComboBoxChi(){
        try {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang_Chi.getModel();
        model.removeAllElements();
        
        List<Integer> nam = thongkeDao.getNam_Chi(ShareHelper.USER.getTenTaiKhoan());
        if(nam.isEmpty()){
            DialogHelper.alert(this, "Tài khoản này chưa có khoản chi");
        }
        else{
            for(Integer nT: nam){
            model.addElement(nT.intValue());
        }  
        cboThang_Chi.setSelectedIndex(0);
        }
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
         
        
    }
    
        //PANEL TINH HINH THU
   public void showBarChart_Nam_Thu(){
       try {
            
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Nam> tk = thongkeDao.getTKThu_Nam(ShareHelper.USER.getTenTaiKhoan());
        
        
        
        
        for(ThongKe_Nam tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Thu nhập", String.valueOf(tk1.getNam()));
            
        }
       
        JFreeChart chart = ChartFactory.createBarChart("Phân tích thu","Năm","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        Color clr3 = new Color(93, 255, 51);
//        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barChartPanel = new ChartPanel(chart);
        panelBarChartThu_Nam.removeAll();
        panelBarChartThu_Nam.add(barChartPanel);
        panelBarChartThu_Nam.updateUI();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
      public void showBarChart_Thang_Thu(){
          try {
        int nam = (int) cboThang_Thu.getSelectedItem();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Thang> tk = thongkeDao.getTKThu_Thang(ShareHelper.USER.getTenTaiKhoan(),nam);
        for(ThongKe_Thang tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Thu nhập", String.valueOf(tk1.getNgay()));
        }
//        
//        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích thu","Tháng","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        Color clr3 = new Color(93, 255, 51);
//        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartThu_Thang.removeAll();
        panelBarChartThu_Thang.add(barpChartPanel);
        panelBarChartThu_Thang.updateUI();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
      
          public void showBarChart_Ngay_Thu(){
              try {
            
                  int thang = Integer.parseInt(txtThang_Thu.getText());
                  
                  int nam   = Integer.parseInt(txtNam_Thu.getText());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Ngay> tk = thongkeDao.getTKThu_Ngay(ShareHelper.USER.getTenTaiKhoan(),thang,nam);
        for(ThongKe_Ngay tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Thu nhập", dateHelper.toString(tk1.getNgay()));
        }
//       
//        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích thu","Ngày","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        Color clr3 = new Color(93, 255, 51);
//        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartThu_Ngay.removeAll();
        panelBarChartThu_Ngay.add(barpChartPanel);
        panelBarChartThu_Ngay.validate();
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
        
    }

      
      //PANEL TINH HINH CHI TIEU
      public void showBarChart_Thang_Chi(){
          try {
            
        int nam = (int) cboThang_Chi.getSelectedItem();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Thang> tk = thongkeDao.getTKChi_Thang(ShareHelper.USER.getTenTaiKhoan(),nam);
        for(ThongKe_Thang tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Chi tiêu", String.valueOf(tk1.getNgay()));
        }
//        
//        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích chi","Tháng","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        
        
//        Color clr3 = new Color(93, 255, 51);
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartChi_Thang.removeAll();
        panelBarChartChi_Thang.add(barpChartPanel);
        panelBarChartChi_Thang.validate();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    public void showBarChart_Nam_Chi(){
        try {
            
       
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Nam> tk = thongkeDao.getTKChi_Nam(ShareHelper.USER.getTenTaiKhoan());
        for(ThongKe_Nam tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Chi tiêu", String.valueOf(tk1.getNam()));
           
        }
//        
//        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích chi","Năm","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
       domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartChi_Nam.removeAll();
        panelBarChartChi_Nam.add(barpChartPanel);
        panelBarChartChi_Nam.validate();
         } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
        
    }
    
    public void showBarChart_Ngay_Chi(){
        try {
            int thang = Integer.parseInt(txtThang_Chi.getText());
                  
                  int nam   = Integer.parseInt(txtNam_Chi.getText());
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Ngay> tk = thongkeDao.getTKChi_Ngay(ShareHelper.USER.getTenTaiKhoan(),thang,nam);
        for(ThongKe_Ngay tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Chi tiêu", dateHelper.toString(tk1.getNgay()));
        }
// 
//        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích chi","Ngày","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
       
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartChi_Ngay.removeAll();
        panelBarChartChi_Ngay.add(barpChartPanel);
        panelBarChartChi_Ngay.validate();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    //PANEL TINH HINH THU CHI
    public void showBarChart_Nam_ThuChi(){
        try {
            
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Nam> tk = thongkeDao.getTKThu_Nam(ShareHelper.USER.getTenTaiKhoan());
        for(ThongKe_Nam tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Thu nhập", String.valueOf(tk1.getNam()));
            
        }
        
        List<ThongKe_Nam> tk2 = thongkeDao.getTKChi_Nam(ShareHelper.USER.getTenTaiKhoan());
        for(ThongKe_Nam tk1: tk2){
            dataset.setValue(tk1.getTongTien(), "Chi tiêu", String.valueOf(tk1.getNam()));
           
        }
        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích thu","Năm","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        Color clr3 = new Color(93, 255, 51);
//        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barChartPanel = new ChartPanel(chart);
        panelBarChartThuChi_Nam.removeAll();
        panelBarChartThuChi_Nam.add(barChartPanel);
        panelBarChartThuChi_Nam.updateUI();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
      public void showBarChart_Thang_ThuChi(){
          try {
            
        int nam = (int) cboThang_ThuChi.getSelectedItem();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Thang> tk = thongkeDao.getTKThu_Thang(ShareHelper.USER.getTenTaiKhoan(),nam);
        for(ThongKe_Thang tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Thu nhập", String.valueOf(tk1.getNgay()));
        }
//        
        List<ThongKe_Thang> tk2 = thongkeDao.getTKChi_Thang(ShareHelper.USER.getTenTaiKhoan(),nam);
        for(ThongKe_Thang tk1: tk2){
            dataset.setValue(tk1.getTongTien(), "Chi tiêu", String.valueOf(tk1.getNgay()));
        } 
        JFreeChart chart = ChartFactory.createBarChart("Phân tích thu","Tháng","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        Color clr3 = new Color(93, 255, 51);
//        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartThuChi_Thang.removeAll();
        panelBarChartThuChi_Thang.add(barpChartPanel);
        panelBarChartThuChi_Thang.updateUI();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
      
    public void showBarChart_Ngay_ThuChi(){
              try {
            
                  int thang = Integer.parseInt(txtThang_ThuChi.getText());
                  
                  int nam   = Integer.parseInt(txtNam_ThuChi.getText());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ThongKe_Ngay> tk = thongkeDao.getTKThu_Ngay(ShareHelper.USER.getTenTaiKhoan(),thang,nam);
        for(ThongKe_Ngay tk1: tk){
            dataset.setValue(tk1.getTongTien(), "Thu nhập", dateHelper.toString(tk1.getNgay()));
        }
//       
        List<ThongKe_Ngay> tk2 = thongkeDao.getTKChi_Ngay(ShareHelper.USER.getTenTaiKhoan(),thang,nam);
        for(ThongKe_Ngay tk1: tk2){
            dataset.setValue(tk1.getTongTien(), "Chi tiêu", dateHelper.toString(tk1.getNgay()));
        }
        
//        
        JFreeChart chart = ChartFactory.createBarChart("Phân tích thu","Ngày","Tổng Tiền", 
                dataset, PlotOrientation.VERTICAL, true ,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        CategoryAxis domain = categoryPlot.getDomainAxis();
        domain.setLowerMargin(0.25);
        domain.setUpperMargin(0.25);
        
        Color clr3 = new Color(93, 255, 51);
//        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChartThuChi_Ngay.removeAll();
        panelBarChartThuChi_Ngay.add(barpChartPanel);
        panelBarChartThuChi_Ngay.validate();
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
          
    public void getTaiChinh_ThuChi_Nam(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongThuChi_Nam(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu thu chi hiện tại!!");
                   
        }
        else{
            for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtTongThuChi_Nam.setForeground(new Color(204,0,51));
        }
        else{
            txtTongThuChi_Nam.setForeground(new Color(93, 255, 51));
        }
        txtTongThuChi_Nam.setEditable(false);
        txtTongThuChi_Nam.setText(TongTien+ " VNĐ");
        }
        
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    public void getTaiChinh_ThuChi_Thang(){
        try {
            int nam = (int) cboThang_ThuChi.getSelectedItem();

        List<Integer> taichinh = thongkeDao.getTongThuChi_Thang(ShareHelper.USER.getTenTaiKhoan(), nam);
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu thu chi hiện tại!!");
                   
        }
        else{
            for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        if(TongTien<0){
            txtTongThuChi_Thang.setForeground(new Color(204,0,51));
        }
        else{
            txtTongThuChi_Thang.setForeground(new Color(93, 255, 51));
        }
        txtTongThuChi_Thang.setEditable(false);
        txtTongThuChi_Thang.setText(TongTien+ " VNĐ");
        }
    
        
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    public void getTaiChinh_ThuChi_Ngay(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongThuChi_Ngay(ShareHelper.USER.getTenTaiKhoan(), Integer.parseInt(txtThang_ThuChi.getText()),Integer.parseInt(txtNam_ThuChi.getText()));
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu thu chi hiện tại!!");
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtTongThuChi_Ngay.setForeground(new Color(204,0,51));
        }
        else{
            txtTongThuChi_Ngay.setForeground(new Color(93, 255, 51));
        }
        txtTongThuChi_Ngay.setEditable(false);
        txtTongThuChi_Ngay.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    
    
    public void getTaiChinh_Thu_Nam(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongThu_Nam(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu thu hiện tại!!");
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtTongThu_Nam.setForeground(new Color(204,0,51));
        }
        else{
            txtTongThu_Nam.setForeground(new Color(93, 255, 51));
        }
        txtTongThu_Nam.setEditable(false);
        txtTongThu_Nam.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Thu_Thang(){
        try {
            
                int nam = (int) cboThang_Thu.getSelectedItem();

        List<Integer> taichinh = thongkeDao.getTongThu_Thang(ShareHelper.USER.getTenTaiKhoan(), nam);
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu thu  hiện tại!!");
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        if(TongTien<0){
            txtTongThu_Thang.setForeground(new Color(204,0,51));
        }
        else{
            txtTongThu_Thang.setForeground(new Color(93, 255, 51));
        }
        txtTongThu_Thang.setEditable(false);
        txtTongThu_Thang.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Thu_Ngay(){
        try {
            int thang = Integer.parseInt(txtThang_Thu.getText());
                  
                  int nam   = Integer.parseInt(txtNam_Thu.getText());
        
        List<Integer> taichinh = thongkeDao.getTongThu_Ngay(ShareHelper.USER.getTenTaiKhoan(), thang,nam);
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu thu hiện tại!!");
                    
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtTongThu_Ngay.setForeground(new Color(204,0,51));
        }
        else{
            txtTongThu_Ngay.setForeground(new Color(93, 255, 51));
        }
        txtTongThu_Ngay.setEditable(false);
        txtTongThu_Ngay.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    //Tai chinh Chi
    
    public void getTaiChinh_Chi_Nam(){
        try {
            
        
        List<Integer> taichinh = thongkeDao.getTongChi_Nam(ShareHelper.USER.getTenTaiKhoan());
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu chi hiện tại!!");
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtTongChi_Nam.setForeground(new Color(204,0,51));
        }
        else{
            txtTongChi_Nam.setForeground(new Color(93, 255, 51));
        }
        txtTongChi_Nam.setEditable(false);
        txtTongChi_Nam.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Chi_Thang(){
        try {
            
        int nam = (int) cboThang_Chi.getSelectedItem();
        List<Integer> taichinh = thongkeDao.getTongChi_Thang(ShareHelper.USER.getTenTaiKhoan(), nam);
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu chi hiện tại!!");
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        if(TongTien<0){
            txtTongChi_Thang.setForeground(new Color(204,0,51));
        }
        else{
            txtTongChi_Thang.setForeground(new Color(93, 255, 51));
        }
        txtTongChi_Thang.setEditable(false);
        txtTongChi_Thang.setText(TongTien+ " VNĐ");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn");
             e.printStackTrace();
        }
    }
    
    public void getTaiChinh_Chi_Ngay(){
        try {
            int thang = Integer.parseInt(txtThang_Chi.getText());
                  
                  int nam   = Integer.parseInt(txtNam_Chi.getText());
        
        List<Integer> taichinh = thongkeDao.getTongChi_Ngay(ShareHelper.USER.getTenTaiKhoan(), thang,nam);
        int TongTien = 0;
        if(taichinh.isEmpty()){
                    DialogHelper.alert(this, "Tài khoản này không có dữ liệu  chi hiện tại!!");
                   
        }
        else{
        for(Integer tc : taichinh){
            TongTien = tc.intValue();
        }
        
        if(TongTien<0){
            txtTongChi_Ngay.setForeground(new Color(204,0,51));
        }
        else{
            txtTongChi_Ngay.setForeground(new Color(93, 255, 51));
        }
        txtTongChi_Ngay.setEditable(false);
        txtTongChi_Ngay.setText(TongTien+ " VNĐ");
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
        panelBaoCao_2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        panelTinhHinhThuChi = new javax.swing.JPanel();
        tabsTHThuChi = new javax.swing.JTabbedPane();
        panelNgay_ThuChi = new javax.swing.JPanel();
        panelBarChartThuChi_Ngay = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTongThuChi_Ngay = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtThang_ThuChi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNam_ThuChi = new javax.swing.JTextField();
        btnNgay_ThuChi = new javax.swing.JButton();
        panelThang_ThuChi = new javax.swing.JPanel();
        panelBarChartThuChi_Thang = new javax.swing.JPanel();
        txtTongThuChi_Thang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboThang_ThuChi = new javax.swing.JComboBox<>();
        panelNam_ThuChi = new javax.swing.JPanel();
        panelBarChartThuChi_Nam = new javax.swing.JPanel();
        txtTongThuChi_Nam = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        panelPhanTichChi = new javax.swing.JPanel();
        tabsChi = new javax.swing.JTabbedPane();
        panelNgay_Chi = new javax.swing.JPanel();
        panelBarChartChi_Ngay = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTongChi_Ngay = new javax.swing.JTextField();
        txtThang_Chi = new javax.swing.JTextField();
        txtNam_Chi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnNgay_Chi = new javax.swing.JButton();
        panelThang_Chi = new javax.swing.JPanel();
        panelBarChartChi_Thang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTongChi_Thang = new javax.swing.JTextField();
        cboThang_Chi = new javax.swing.JComboBox<>();
        panelNam_Chi = new javax.swing.JPanel();
        panelBarChartChi_Nam = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTongChi_Nam = new javax.swing.JTextField();
        panelPhanTichThu = new javax.swing.JPanel();
        tabsThu = new javax.swing.JTabbedPane();
        panelNgay_Thu = new javax.swing.JPanel();
        panelBarChartThu_Ngay = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTongThu_Ngay = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtThang_Thu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNam_Thu = new javax.swing.JTextField();
        btnThu_ngay = new javax.swing.JButton();
        panelThang_Thu = new javax.swing.JPanel();
        panelBarChartThu_Thang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTongThu_Thang = new javax.swing.JTextField();
        cboThang_Thu = new javax.swing.JComboBox<>();
        panelNam_Thu = new javax.swing.JPanel();
        panelBarChartThu_Nam = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTongThu_Nam = new javax.swing.JTextField();
        panelTaiChinhHienTai = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTaiChinh = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTaiChinh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelChuyen.setBackground(new java.awt.Color(199, 229, 245));
        panelChuyen.setPreferredSize(new java.awt.Dimension(170, 940));

        panelTaiKhoan.setBackground(new java.awt.Color(199, 229, 245));
        panelTaiKhoan.setPreferredSize(new java.awt.Dimension(2, 102));
        panelTaiKhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(59, 204, 253));
        lblTaiKhoan.setText("Tài khoản");
        panelTaiKhoan.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Unknown person2.png"))); // NOI18N
        panelTaiKhoan.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        lblTaiKhoan_BG.setBackground(new java.awt.Color(255, 255, 255));
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
        panelTongQuan.add(lblTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tongquan.png"))); // NOI18N
        panelTongQuan.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

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
        panelViTien.add(lblViTien_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 170, 110));

        panelThuChi.setBackground(new java.awt.Color(199, 229, 245));
        panelThuChi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThuChi.setForeground(new java.awt.Color(59, 204, 253));
        lblThuChi.setText("Thu-Chi");
        panelThuChi.add(lblThuChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 20));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/thuchi.png"))); // NOI18N
        panelThuChi.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        lblThuChi_BG.setBackground(new java.awt.Color(255, 255, 255));
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
        panelThuChi.add(lblThuChi_BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 115));

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
        lblBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        lblBaoCao.setText("Báo cáo");
        lblBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBaoCaoMouseEntered(evt);
            }
        });
        panelBaoCao.add(lblBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 65, -1));

        jLabel22.setBackground(new java.awt.Color(102, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Group 305.png"))); // NOI18N
        panelBaoCao.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        lblBG_BC.setBackground(new java.awt.Color(114, 206, 255));
        lblBG_BC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBG_BC.setOpaque(true);
        panelBaoCao.add(lblBG_BC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        btnLogOut.setBackground(new java.awt.Color(223, 203, 203));
        btnLogOut.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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
                .addGap(32, 32, 32)
                .addComponent(btnLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChuyenLayout.setVerticalGroup(
            panelChuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuyenLayout.createSequentialGroup()
                .addComponent(panelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelViTien, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(panelNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnLogOut)
                .addGap(29, 29, 29))
        );

        panelBaoCao_2.setBackground(new java.awt.Color(114, 206, 255));
        panelBaoCao_2.setPreferredSize(new java.awt.Dimension(1022, 940));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        panelTinhHinhThuChi.setBackground(new java.awt.Color(114, 206, 255));

        panelNgay_ThuChi.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartThuChi_Ngay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartThuChi_Ngay.setPreferredSize(new java.awt.Dimension(0, 378));
        panelBarChartThuChi_Ngay.setLayout(new javax.swing.BoxLayout(panelBarChartThuChi_Ngay, javax.swing.BoxLayout.LINE_AXIS));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("TỔNG THU-CHI");

        txtTongThuChi_Ngay.setBackground(new java.awt.Color(114, 206, 255));
        txtTongThuChi_Ngay.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongThuChi_Ngay.setForeground(new java.awt.Color(20, 255, 114));
        txtTongThuChi_Ngay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongThuChi_Ngay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));
        txtTongThuChi_Ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongThuChi_NgayActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("THÁNG");

        txtThang_ThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("NĂM");

        txtNam_ThuChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnNgay_ThuChi.setText("OK");
        btnNgay_ThuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgay_ThuChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNgay_ThuChiLayout = new javax.swing.GroupLayout(panelNgay_ThuChi);
        panelNgay_ThuChi.setLayout(panelNgay_ThuChiLayout);
        panelNgay_ThuChiLayout.setHorizontalGroup(
            panelNgay_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartThuChi_Ngay, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNgay_ThuChiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTongThuChi_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(panelNgay_ThuChiLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel13)
                .addGap(32, 32, 32)
                .addComponent(txtThang_ThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jLabel14)
                .addGap(46, 46, 46)
                .addComponent(txtNam_ThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNgay_ThuChi)
                .addGap(23, 23, 23))
        );
        panelNgay_ThuChiLayout.setVerticalGroup(
            panelNgay_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNgay_ThuChiLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelNgay_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThang_ThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNam_ThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(btnNgay_ThuChi))
                .addGap(18, 18, 18)
                .addComponent(panelBarChartThuChi_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNgay_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongThuChi_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(339, Short.MAX_VALUE))
        );

        tabsTHThuChi.addTab("Ngày", panelNgay_ThuChi);

        panelThang_ThuChi.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartThuChi_Thang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartThuChi_Thang.setPreferredSize(new java.awt.Dimension(0, 378));
        panelBarChartThuChi_Thang.setLayout(new javax.swing.BoxLayout(panelBarChartThuChi_Thang, javax.swing.BoxLayout.LINE_AXIS));

        txtTongThuChi_Thang.setBackground(new java.awt.Color(114, 206, 255));
        txtTongThuChi_Thang.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongThuChi_Thang.setForeground(new java.awt.Color(20, 255, 114));
        txtTongThuChi_Thang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongThuChi_Thang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TỔNG THU-CHI");

        cboThang_ThuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThang_ThuChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThang_ThuChiLayout = new javax.swing.GroupLayout(panelThang_ThuChi);
        panelThang_ThuChi.setLayout(panelThang_ThuChiLayout);
        panelThang_ThuChiLayout.setHorizontalGroup(
            panelThang_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartThuChi_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThang_ThuChiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(48, 48, 48)
                .addComponent(txtTongThuChi_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(panelThang_ThuChiLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(cboThang_ThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThang_ThuChiLayout.setVerticalGroup(
            panelThang_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThang_ThuChiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cboThang_ThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBarChartThuChi_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelThang_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongThuChi_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(341, Short.MAX_VALUE))
        );

        tabsTHThuChi.addTab("Tháng", panelThang_ThuChi);

        panelNam_ThuChi.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartThuChi_Nam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartThuChi_Nam.setPreferredSize(new java.awt.Dimension(0, 378));
        panelBarChartThuChi_Nam.setLayout(new javax.swing.BoxLayout(panelBarChartThuChi_Nam, javax.swing.BoxLayout.LINE_AXIS));

        txtTongThuChi_Nam.setBackground(new java.awt.Color(114, 206, 255));
        txtTongThuChi_Nam.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongThuChi_Nam.setForeground(new java.awt.Color(20, 255, 114));
        txtTongThuChi_Nam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongThuChi_Nam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TỔNG THU-CHI");

        javax.swing.GroupLayout panelNam_ThuChiLayout = new javax.swing.GroupLayout(panelNam_ThuChi);
        panelNam_ThuChi.setLayout(panelNam_ThuChiLayout);
        panelNam_ThuChiLayout.setHorizontalGroup(
            panelNam_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartThuChi_Nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNam_ThuChiLayout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(51, 51, 51)
                .addComponent(txtTongThuChi_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        panelNam_ThuChiLayout.setVerticalGroup(
            panelNam_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNam_ThuChiLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(panelBarChartThuChi_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNam_ThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongThuChi_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(341, Short.MAX_VALUE))
        );

        tabsTHThuChi.addTab("Năm", panelNam_ThuChi);

        javax.swing.GroupLayout panelTinhHinhThuChiLayout = new javax.swing.GroupLayout(panelTinhHinhThuChi);
        panelTinhHinhThuChi.setLayout(panelTinhHinhThuChiLayout);
        panelTinhHinhThuChiLayout.setHorizontalGroup(
            panelTinhHinhThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsTHThuChi)
        );
        panelTinhHinhThuChiLayout.setVerticalGroup(
            panelTinhHinhThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsTHThuChi)
        );

        tabs.addTab("TÌNH HÌNH THU CHI", new javax.swing.ImageIcon(getClass().getResource("/Images/barChart_up.jpg")), panelTinhHinhThuChi); // NOI18N

        panelPhanTichChi.setBackground(new java.awt.Color(114, 206, 255));

        panelNgay_Chi.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartChi_Ngay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartChi_Ngay.setPreferredSize(new java.awt.Dimension(0, 378));
        panelBarChartChi_Ngay.setLayout(new javax.swing.BoxLayout(panelBarChartChi_Ngay, javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TỔNG CHI");

        txtTongChi_Ngay.setBackground(new java.awt.Color(114, 206, 255));
        txtTongChi_Ngay.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongChi_Ngay.setForeground(new java.awt.Color(20, 255, 114));
        txtTongChi_Ngay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongChi_Ngay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        txtThang_Chi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtNam_Chi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("NĂM");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("THÁNG");

        btnNgay_Chi.setText("OK");
        btnNgay_Chi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgay_ChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNgay_ChiLayout = new javax.swing.GroupLayout(panelNgay_Chi);
        panelNgay_Chi.setLayout(panelNgay_ChiLayout);
        panelNgay_ChiLayout.setHorizontalGroup(
            panelNgay_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartChi_Ngay, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNgay_ChiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongChi_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(panelNgay_ChiLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel10)
                .addGap(32, 32, 32)
                .addComponent(txtThang_Chi, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jLabel9)
                .addGap(46, 46, 46)
                .addComponent(txtNam_Chi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNgay_Chi)
                .addContainerGap())
        );
        panelNgay_ChiLayout.setVerticalGroup(
            panelNgay_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNgay_ChiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelNgay_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThang_Chi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNam_Chi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(btnNgay_Chi))
                .addGap(18, 18, 18)
                .addComponent(panelBarChartChi_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNgay_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongChi_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(336, Short.MAX_VALUE))
        );

        tabsChi.addTab("Ngày", panelNgay_Chi);

        panelThang_Chi.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartChi_Thang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartChi_Thang.setPreferredSize(new java.awt.Dimension(0, 378));
        panelBarChartChi_Thang.setLayout(new javax.swing.BoxLayout(panelBarChartChi_Thang, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TỔNG CHI");

        txtTongChi_Thang.setBackground(new java.awt.Color(114, 206, 255));
        txtTongChi_Thang.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongChi_Thang.setForeground(new java.awt.Color(20, 255, 114));
        txtTongChi_Thang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongChi_Thang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        cboThang_Chi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThang_ChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThang_ChiLayout = new javax.swing.GroupLayout(panelThang_Chi);
        panelThang_Chi.setLayout(panelThang_ChiLayout);
        panelThang_ChiLayout.setHorizontalGroup(
            panelThang_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartChi_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThang_ChiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelThang_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThang_ChiLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTongChi_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThang_ChiLayout.createSequentialGroup()
                        .addComponent(cboThang_Chi, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))))
        );
        panelThang_ChiLayout.setVerticalGroup(
            panelThang_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThang_ChiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cboThang_Chi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBarChartChi_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelThang_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongChi_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(341, Short.MAX_VALUE))
        );

        tabsChi.addTab("Tháng", panelThang_Chi);

        panelNam_Chi.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartChi_Nam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartChi_Nam.setPreferredSize(new java.awt.Dimension(0, 378));
        panelBarChartChi_Nam.setLayout(new javax.swing.BoxLayout(panelBarChartChi_Nam, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TỔNG CHI");

        txtTongChi_Nam.setBackground(new java.awt.Color(114, 206, 255));
        txtTongChi_Nam.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongChi_Nam.setForeground(new java.awt.Color(20, 255, 114));
        txtTongChi_Nam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongChi_Nam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        javax.swing.GroupLayout panelNam_ChiLayout = new javax.swing.GroupLayout(panelNam_Chi);
        panelNam_Chi.setLayout(panelNam_ChiLayout);
        panelNam_ChiLayout.setHorizontalGroup(
            panelNam_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartChi_Nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNam_ChiLayout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongChi_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        panelNam_ChiLayout.setVerticalGroup(
            panelNam_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNam_ChiLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(panelBarChartChi_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNam_ChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongChi_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(341, Short.MAX_VALUE))
        );

        tabsChi.addTab("Năm", panelNam_Chi);

        javax.swing.GroupLayout panelPhanTichChiLayout = new javax.swing.GroupLayout(panelPhanTichChi);
        panelPhanTichChi.setLayout(panelPhanTichChiLayout);
        panelPhanTichChiLayout.setHorizontalGroup(
            panelPhanTichChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsChi)
        );
        panelPhanTichChiLayout.setVerticalGroup(
            panelPhanTichChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsChi)
        );

        tabs.addTab("PHÂN TÍCH CHI TIÊU", new javax.swing.ImageIcon(getClass().getResource("/Images/redbar.png")), panelPhanTichChi); // NOI18N

        panelPhanTichThu.setBackground(new java.awt.Color(114, 206, 255));

        panelNgay_Thu.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartThu_Ngay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartThu_Ngay.setLayout(new javax.swing.BoxLayout(panelBarChartThu_Ngay, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TỔNG THU");

        txtTongThu_Ngay.setBackground(new java.awt.Color(114, 206, 255));
        txtTongThu_Ngay.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongThu_Ngay.setForeground(new java.awt.Color(20, 255, 114));
        txtTongThu_Ngay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongThu_Ngay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("THÁNG");

        txtThang_Thu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("NĂM");

        txtNam_Thu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnThu_ngay.setText("OK");
        btnThu_ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThu_ngayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNgay_ThuLayout = new javax.swing.GroupLayout(panelNgay_Thu);
        panelNgay_Thu.setLayout(panelNgay_ThuLayout);
        panelNgay_ThuLayout.setHorizontalGroup(
            panelNgay_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartThu_Ngay, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNgay_ThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongThu_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(panelNgay_ThuLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel15)
                .addGap(32, 32, 32)
                .addComponent(txtThang_Thu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jLabel16)
                .addGap(46, 46, 46)
                .addComponent(txtNam_Thu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThu_ngay)
                .addGap(29, 29, 29))
        );
        panelNgay_ThuLayout.setVerticalGroup(
            panelNgay_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNgay_ThuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelNgay_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThang_Thu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNam_Thu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(btnThu_ngay))
                .addGap(18, 18, 18)
                .addComponent(panelBarChartThu_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNgay_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongThu_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(338, Short.MAX_VALUE))
        );

        tabsThu.addTab("Ngày", panelNgay_Thu);

        panelThang_Thu.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartThu_Thang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartThu_Thang.setLayout(new javax.swing.BoxLayout(panelBarChartThu_Thang, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TỔNG THU");

        txtTongThu_Thang.setBackground(new java.awt.Color(114, 206, 255));
        txtTongThu_Thang.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongThu_Thang.setForeground(new java.awt.Color(20, 255, 114));
        txtTongThu_Thang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongThu_Thang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        cboThang_Thu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThang_ThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThang_ThuLayout = new javax.swing.GroupLayout(panelThang_Thu);
        panelThang_Thu.setLayout(panelThang_ThuLayout);
        panelThang_ThuLayout.setHorizontalGroup(
            panelThang_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartThu_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThang_ThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongThu_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
            .addGroup(panelThang_ThuLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(cboThang_Thu, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThang_ThuLayout.setVerticalGroup(
            panelThang_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThang_ThuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cboThang_Thu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBarChartThu_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelThang_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTongThu_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(340, Short.MAX_VALUE))
        );

        tabsThu.addTab("Tháng", panelThang_Thu);

        panelNam_Thu.setBackground(new java.awt.Color(114, 206, 255));

        panelBarChartThu_Nam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarChartThu_Nam.setLayout(new javax.swing.BoxLayout(panelBarChartThu_Nam, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TỔNG THU");

        txtTongThu_Nam.setBackground(new java.awt.Color(114, 206, 255));
        txtTongThu_Nam.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTongThu_Nam.setForeground(new java.awt.Color(20, 255, 114));
        txtTongThu_Nam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongThu_Nam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        javax.swing.GroupLayout panelNam_ThuLayout = new javax.swing.GroupLayout(panelNam_Thu);
        panelNam_Thu.setLayout(panelNam_ThuLayout);
        panelNam_ThuLayout.setHorizontalGroup(
            panelNam_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarChartThu_Nam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNam_ThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongThu_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        panelNam_ThuLayout.setVerticalGroup(
            panelNam_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNam_ThuLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(panelBarChartThu_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNam_ThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTongThu_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(341, Short.MAX_VALUE))
        );

        tabsThu.addTab("Năm", panelNam_Thu);

        javax.swing.GroupLayout panelPhanTichThuLayout = new javax.swing.GroupLayout(panelPhanTichThu);
        panelPhanTichThu.setLayout(panelPhanTichThuLayout);
        panelPhanTichThuLayout.setHorizontalGroup(
            panelPhanTichThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsThu)
        );
        panelPhanTichThuLayout.setVerticalGroup(
            panelPhanTichThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsThu)
        );

        tabs.addTab("PHÂN TÍCH THU", new javax.swing.ImageIcon(getClass().getResource("/Images/green.png")), panelPhanTichThu); // NOI18N

        panelTaiChinhHienTai.setBackground(new java.awt.Color(114, 206, 255));

        tblTaiChinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTaiChinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTaiChinh.setRowHeight(25);
        tblTaiChinh.setSelectionBackground(new java.awt.Color(114, 206, 255));
        jScrollPane1.setViewportView(tblTaiChinh);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TÀI CHÍNH HIỆN TẠI:");

        txtTaiChinh.setBackground(new java.awt.Color(114, 206, 255));
        txtTaiChinh.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTaiChinh.setForeground(new java.awt.Color(20, 255, 114));
        txtTaiChinh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTaiChinh.setText("VNĐ");
        txtTaiChinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(104, 104, 172)));

        javax.swing.GroupLayout panelTaiChinhHienTaiLayout = new javax.swing.GroupLayout(panelTaiChinhHienTai);
        panelTaiChinhHienTai.setLayout(panelTaiChinhHienTaiLayout);
        panelTaiChinhHienTaiLayout.setHorizontalGroup(
            panelTaiChinhHienTaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panelTaiChinhHienTaiLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addComponent(txtTaiChinh, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
        );
        panelTaiChinhHienTaiLayout.setVerticalGroup(
            panelTaiChinhHienTaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaiChinhHienTaiLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(panelTaiChinhHienTaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTaiChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(99, 99, 99)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        tabs.addTab("TÀI CHÍNH HIỆN TẠI", new javax.swing.ImageIcon(getClass().getResource("/Images/taiChinh.png")), panelTaiChinhHienTai); // NOI18N

        javax.swing.GroupLayout panelBaoCao_2Layout = new javax.swing.GroupLayout(panelBaoCao_2);
        panelBaoCao_2.setLayout(panelBaoCao_2Layout);
        panelBaoCao_2Layout.setHorizontalGroup(
            panelBaoCao_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaoCao_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addGap(24, 24, 24))
        );
        panelBaoCao_2Layout.setVerticalGroup(
            panelBaoCao_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaoCao_2Layout.createSequentialGroup()
                .addComponent(tabs)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelBaoCao_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBaoCao_2, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addComponent(panelChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 891, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void lblViTien_BGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ViTienJFrame_2().setVisible(true);
    }//GEN-LAST:event_lblViTien_BGMouseClicked

    private void lblViTien_BGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseEntered
        // TODO add your handling code here:
        miceEntered(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseEntered

    private void lblViTien_BGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViTien_BGMouseExited
        // TODO add your handling code here:
        miceExited(panelViTien, lblViTien);
    }//GEN-LAST:event_lblViTien_BGMouseExited

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

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        ShareHelper.logoff();
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
       
        if(ShareHelper.authenticated()){
            init();
//           
        }
        else{
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowOpened

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

    private void txtTongThuChi_NgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongThuChi_NgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongThuChi_NgayActionPerformed

    private void cboThang_ThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThang_ThuActionPerformed
        // TODO add your handling code here:
        showBarChart_Thang_Thu();
        getTaiChinh_Thu_Thang();
    }//GEN-LAST:event_cboThang_ThuActionPerformed

    private void cboThang_ChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThang_ChiActionPerformed
        // TODO add your handling code here:
       showBarChart_Thang_Chi();
       getTaiChinh_Chi_Thang();
    }//GEN-LAST:event_cboThang_ChiActionPerformed

    private void cboThang_ThuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThang_ThuChiActionPerformed
        // TODO add your handling code here:
        showBarChart_Thang_ThuChi();
        getTaiChinh_ThuChi_Thang();
    }//GEN-LAST:event_cboThang_ThuChiActionPerformed

    private void btnNgay_ThuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgay_ThuChiActionPerformed
        // TODO add your handling code here:
        if(checkThuChi(txtThang_ThuChi,txtNam_ThuChi)){
        showBarChart_Ngay_ThuChi();
        getTaiChinh_ThuChi_Ngay();
        }
    }//GEN-LAST:event_btnNgay_ThuChiActionPerformed

    private void btnNgay_ChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgay_ChiActionPerformed
        // TODO add your handling code here:
        if(checkThuChi(txtThang_Chi,txtNam_Chi)){
        showBarChart_Ngay_Chi();
        getTaiChinh_Chi_Ngay();
        }
    }//GEN-LAST:event_btnNgay_ChiActionPerformed

    private void btnThu_ngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThu_ngayActionPerformed
        // TODO add your handling code here:
        if(checkThuChi(txtThang_Thu,txtNam_Thu)){
        showBarChart_Ngay_Thu();
        getTaiChinh_Thu_Ngay();
        }
    }//GEN-LAST:event_btnThu_ngayActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnNgay_Chi;
    private javax.swing.JButton btnNgay_ThuChi;
    private javax.swing.JButton btnThu_ngay;
    private javax.swing.JComboBox<String> cboThang_Chi;
    private javax.swing.JComboBox<String> cboThang_Thu;
    private javax.swing.JComboBox<String> cboThang_ThuChi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JPanel panelBaoCao_2;
    private javax.swing.JPanel panelBarChartChi_Nam;
    private javax.swing.JPanel panelBarChartChi_Ngay;
    private javax.swing.JPanel panelBarChartChi_Thang;
    private javax.swing.JPanel panelBarChartThuChi_Nam;
    private javax.swing.JPanel panelBarChartThuChi_Ngay;
    private javax.swing.JPanel panelBarChartThuChi_Thang;
    private javax.swing.JPanel panelBarChartThu_Nam;
    private javax.swing.JPanel panelBarChartThu_Ngay;
    private javax.swing.JPanel panelBarChartThu_Thang;
    private javax.swing.JPanel panelChuyen;
    private javax.swing.JPanel panelNam_Chi;
    private javax.swing.JPanel panelNam_Thu;
    private javax.swing.JPanel panelNam_ThuChi;
    private javax.swing.JPanel panelNgay_Chi;
    private javax.swing.JPanel panelNgay_Thu;
    private javax.swing.JPanel panelNgay_ThuChi;
    private javax.swing.JPanel panelNguoiDung;
    private javax.swing.JPanel panelPhanTichChi;
    private javax.swing.JPanel panelPhanTichThu;
    private javax.swing.JPanel panelTaiChinhHienTai;
    private javax.swing.JPanel panelTaiKhoan;
    private javax.swing.JPanel panelThang_Chi;
    private javax.swing.JPanel panelThang_Thu;
    private javax.swing.JPanel panelThang_ThuChi;
    private javax.swing.JPanel panelThuChi;
    private javax.swing.JPanel panelTinhHinhThuChi;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelViTien;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTabbedPane tabsChi;
    private javax.swing.JTabbedPane tabsTHThuChi;
    private javax.swing.JTabbedPane tabsThu;
    private javax.swing.JTable tblTaiChinh;
    private javax.swing.JTextField txtNam_Chi;
    private javax.swing.JTextField txtNam_Thu;
    private javax.swing.JTextField txtNam_ThuChi;
    private javax.swing.JTextField txtTaiChinh;
    private javax.swing.JTextField txtThang_Chi;
    private javax.swing.JTextField txtThang_Thu;
    private javax.swing.JTextField txtThang_ThuChi;
    private javax.swing.JTextField txtTongChi_Nam;
    private javax.swing.JTextField txtTongChi_Ngay;
    private javax.swing.JTextField txtTongChi_Thang;
    private javax.swing.JTextField txtTongThuChi_Nam;
    private javax.swing.JTextField txtTongThuChi_Ngay;
    private javax.swing.JTextField txtTongThuChi_Thang;
    private javax.swing.JTextField txtTongThu_Nam;
    private javax.swing.JTextField txtTongThu_Ngay;
    private javax.swing.JTextField txtTongThu_Thang;
    // End of variables declaration//GEN-END:variables
}

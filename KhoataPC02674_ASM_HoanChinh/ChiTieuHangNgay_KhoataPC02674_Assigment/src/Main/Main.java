/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DAO.NguoiDungDAO;
import Helper.ClockThread;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Jdialog_GT_DN.GioiThieu;
import Jdialog_GT_DN.Loading;
import Jdialog_GT_DN.Login;
import Model.NguoiDung;
import QuanLi.BaoCaoJFrame;
import QuanLi.DangKy;

import QuanLi.NguoiDungJFrame;
import QuanLi.NguoiDungJFrame_2;
import QuanLi.QuenMatKhau_1;
import QuanLi.TaiKhoanJFrame;
import QuanLi.ThuChiJFrame;
import QuanLi.TongQuanJFrame;
import QuanLi.ViTienJFrame_2;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Desktop;
import java.awt.Font;
import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class Main extends javax.swing.JFrame {
    NguoiDungDAO ndDAO = new NguoiDungDAO();
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
       
    }
    
    public void clock(){
        ClockThread clock = new ClockThread(lblClock);
        
        clock.start();
    }
   
    
   public void init(){
        setIconImage(ShareHelper.APP_ICON);
        this.setTitle("CHI TIÊU HÀNG NGÀY");
        setLocationRelativeTo(this);
        getHinh2();
        clock();
        
   }
    
   
   
   
    public void CloseJframe(){
               if(DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")){
            System.exit(0);
        }
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
    
    public void getHinh2(){
         try {
            String taiKhoan = ShareHelper.USER.getTenTaiKhoan();
            NguoiDung timTk = ndDAO.findByIdTK(taiKhoan);
           
            if(timTk.getHinh()==null){
                DialogHelper.alert(this, "Tài khoản này chưa đặt ảnh đại diện");
            }
            else{
                lblHinhDaiDien.setIcon(ShareHelper.readLogo(timTk.getHinh(), lblHinhDaiDien));
                lblHinhDaiDien.setToolTipText(timTk.getHinh());
            }
            
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn hình ảnh");
            e.printStackTrace();
        }
        
    }
    public void miceEntered(JPanel panel, JLabel lbl){
        Font myFont = new Font("Tahoma", Font.BOLD, 14);
       panel.setBackground(new Color(114,206,255));
       lbl.setForeground(white);
       lbl.setFont(myFont);
    }
    
    public void miceExited(JPanel panel, JLabel lbl){
         Font myFont = new Font("Tahoma", Font.BOLD, 14);
       panel.setBackground(new Color(227,249,249));
       lbl.setForeground(new Color(59,204,253));
       lbl.setFont(myFont);
    }
    
     public void exit(){
        DialogHelper.alert(this, "Cám ơn đã sử dụng phần mềm! \n Chúc bạn một ngày tốt lành!!");

            System.exit(0);
    }
    public void logoff(){
        ShareHelper.logoff();
        this.dispose();
        new Login().setVisible(true);
        
        
    }
    
    public void tongQuan(){
        this.dispose();
        new TongQuanJFrame().setVisible(true);
    }
    
    public void taiKhoan(){
         this.dispose();
        new TaiKhoanJFrame().setVisible(true);
    }
    
    public void viTien(){
        this.dispose();
        new ViTienJFrame_2().setVisible(true);
    }
    
    public void baoCao(){
        this.dispose();
        new BaoCaoJFrame(0).setVisible(true);
    }
    
    public void nguoiDung(){
        this.dispose();
        try {
             NguoiDung nd = ndDAO.findByIdTK(ShareHelper.USER.getTenTaiKhoan());
        if(nd.isVaiTro()){
            new NguoiDungJFrame().setVisible(true);
        }
        else{
             new NguoiDungJFrame_2().setVisible(true);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public void thuChi(){
        this.dispose();
        new ThuChiJFrame().setVisible(true);
    }
    
    
     public void openThongKe(int index) {
        try {
             if (ShareHelper.authenticated()) {
            new BaoCaoJFrame(index).setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
       
    }
     
    public void openHuongDan(){
        
        try {
            Desktop.getDesktop().browse(new URI("https://youtu.be/WK40gDUs7aU"));
        } catch (Exception e) {
        }
    }
    
    public void openInfo(){
        new GioiThieu(this, true).setVisible(true);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGiaoDien = new javax.swing.JPanel();
        panelHinhDaiDien = new javax.swing.JPanel();
        lblHinhDaiDien = new javax.swing.JLabel();
        panelTongQuan = new javax.swing.JPanel();
        lblTongQuan = new javax.swing.JLabel();
        lblTongQuan_txt = new javax.swing.JLabel();
        panelViTien = new javax.swing.JPanel();
        lblViTien = new javax.swing.JLabel();
        lblViTien_txt = new javax.swing.JLabel();
        panelThuChi = new javax.swing.JPanel();
        lblThuChi = new javax.swing.JLabel();
        lblThu_Chi = new javax.swing.JLabel();
        panelNguoiDung = new javax.swing.JPanel();
        lblNguoiDung = new javax.swing.JLabel();
        lblNguoiDung_txt = new javax.swing.JLabel();
        panelBaoCao = new javax.swing.JPanel();
        lblBaoCao = new javax.swing.JLabel();
        lblBaoCao_txt = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        panelTaiKhoan = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        lblTaiKhoan_txt = new javax.swing.JLabel();
        lblClock = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniDoiMatKhau = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuChucNang = new javax.swing.JMenu();
        mniTaiKhoan = new javax.swing.JMenuItem();
        mniTongQuan = new javax.swing.JMenuItem();
        mniViTien = new javax.swing.JMenuItem();
        mniQLNguoiDung = new javax.swing.JMenuItem();
        mniThuChi = new javax.swing.JMenuItem();
        mniBaoCao = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuThu = new javax.swing.JMenuItem();
        menuChi = new javax.swing.JMenuItem();
        mnuThuChi = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnuTaiChinh = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        openHuongDan = new javax.swing.JMenuItem();
        openGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGiaoDien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelGiaoDien.setOpaque(false);
        panelGiaoDien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHinhDaiDien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelHinhDaiDienLayout = new javax.swing.GroupLayout(panelHinhDaiDien);
        panelHinhDaiDien.setLayout(panelHinhDaiDienLayout);
        panelHinhDaiDienLayout.setHorizontalGroup(
            panelHinhDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        panelHinhDaiDienLayout.setVerticalGroup(
            panelHinhDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );

        panelGiaoDien.add(panelHinhDaiDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        panelTongQuan.setBackground(new java.awt.Color(227, 249, 249));
        panelTongQuan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTongQuan.setPreferredSize(new java.awt.Dimension(177, 161));
        panelTongQuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTongQuanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTongQuanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelTongQuanMouseExited(evt);
            }
        });

        lblTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tongquan.png"))); // NOI18N

        lblTongQuan_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongQuan_txt.setForeground(new java.awt.Color(59, 204, 253));
        lblTongQuan_txt.setText("Tổng quan");

        javax.swing.GroupLayout panelTongQuanLayout = new javax.swing.GroupLayout(panelTongQuan);
        panelTongQuan.setLayout(panelTongQuanLayout);
        panelTongQuanLayout.setHorizontalGroup(
            panelTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongQuanLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(panelTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongQuan_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        panelTongQuanLayout.setVerticalGroup(
            panelTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongQuanLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblTongQuan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongQuan_txt)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panelGiaoDien.add(panelTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, -1, -1));

        panelViTien.setBackground(new java.awt.Color(227, 249, 249));
        panelViTien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelViTien.setPreferredSize(new java.awt.Dimension(177, 161));
        panelViTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelViTienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViTienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViTienMouseExited(evt);
            }
        });

        lblViTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vitien.png"))); // NOI18N

        lblViTien_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViTien_txt.setForeground(new java.awt.Color(59, 204, 253));
        lblViTien_txt.setText("Ví Tiền");

        javax.swing.GroupLayout panelViTienLayout = new javax.swing.GroupLayout(panelViTien);
        panelViTien.setLayout(panelViTienLayout);
        panelViTienLayout.setHorizontalGroup(
            panelViTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViTienLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(panelViTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViTienLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblViTien_txt))
                    .addComponent(lblViTien))
                .addGap(55, 55, 55))
        );
        panelViTienLayout.setVerticalGroup(
            panelViTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViTienLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblViTien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblViTien_txt)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelGiaoDien.add(panelViTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 360, -1, -1));

        panelThuChi.setBackground(new java.awt.Color(227, 249, 249));
        panelThuChi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelThuChi.setPreferredSize(new java.awt.Dimension(177, 161));
        panelThuChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelThuChiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelThuChiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelThuChiMouseExited(evt);
            }
        });

        lblThuChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/thuchi.png"))); // NOI18N

        lblThu_Chi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThu_Chi.setForeground(new java.awt.Color(59, 204, 253));
        lblThu_Chi.setText("Thu-Chi");

        javax.swing.GroupLayout panelThuChiLayout = new javax.swing.GroupLayout(panelThuChi);
        panelThuChi.setLayout(panelThuChiLayout);
        panelThuChiLayout.setHorizontalGroup(
            panelThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThuChiLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(panelThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblThuChi)
                    .addComponent(lblThu_Chi))
                .addGap(56, 56, 56))
        );
        panelThuChiLayout.setVerticalGroup(
            panelThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuChiLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblThuChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblThu_Chi)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelGiaoDien.add(panelThuChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 603, -1, -1));

        panelNguoiDung.setBackground(new java.awt.Color(227, 249, 249));
        panelNguoiDung.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelNguoiDung.setPreferredSize(new java.awt.Dimension(177, 161));
        panelNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelNguoiDungMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelNguoiDungMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelNguoiDungMouseExited(evt);
            }
        });

        lblNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nguoidung.png"))); // NOI18N

        lblNguoiDung_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNguoiDung_txt.setForeground(new java.awt.Color(59, 204, 253));
        lblNguoiDung_txt.setText("Người dùng");

        javax.swing.GroupLayout panelNguoiDungLayout = new javax.swing.GroupLayout(panelNguoiDung);
        panelNguoiDung.setLayout(panelNguoiDungLayout);
        panelNguoiDungLayout.setHorizontalGroup(
            panelNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNguoiDungLayout.createSequentialGroup()
                .addGroup(panelNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNguoiDungLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblNguoiDung))
                    .addGroup(panelNguoiDungLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblNguoiDung_txt)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        panelNguoiDungLayout.setVerticalGroup(
            panelNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNguoiDungLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblNguoiDung)
                .addGap(18, 18, 18)
                .addComponent(lblNguoiDung_txt)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelGiaoDien.add(panelNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 603, -1, -1));

        panelBaoCao.setBackground(new java.awt.Color(227, 249, 249));
        panelBaoCao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBaoCao.setPreferredSize(new java.awt.Dimension(177, 161));
        panelBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBaoCaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBaoCaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBaoCaoMouseExited(evt);
            }
        });

        lblBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Group 305.png"))); // NOI18N

        lblBaoCao_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBaoCao_txt.setForeground(new java.awt.Color(59, 204, 253));
        lblBaoCao_txt.setText("Báo Cáo");

        javax.swing.GroupLayout panelBaoCaoLayout = new javax.swing.GroupLayout(panelBaoCao);
        panelBaoCao.setLayout(panelBaoCaoLayout);
        panelBaoCaoLayout.setHorizontalGroup(
            panelBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBaoCaoLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(panelBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBaoCao)
                    .addComponent(lblBaoCao_txt))
                .addGap(54, 54, 54))
        );
        panelBaoCaoLayout.setVerticalGroup(
            panelBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaoCaoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblBaoCao)
                .addGap(18, 18, 18)
                .addComponent(lblBaoCao_txt)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelGiaoDien.add(panelBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 603, -1, -1));

        btnDangXuat.setBackground(new java.awt.Color(209, 232, 226));
        btnDangXuat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dangxuat.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        panelGiaoDien.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        panelTaiKhoan.setBackground(new java.awt.Color(227, 249, 249));
        panelTaiKhoan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTaiKhoan.setPreferredSize(new java.awt.Dimension(177, 161));
        panelTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTaiKhoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelTaiKhoanMouseExited(evt);
            }
        });

        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Unknown person2.png"))); // NOI18N

        lblTaiKhoan_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaiKhoan_txt.setForeground(new java.awt.Color(59, 204, 253));
        lblTaiKhoan_txt.setText("Tài Khoản");

        javax.swing.GroupLayout panelTaiKhoanLayout = new javax.swing.GroupLayout(panelTaiKhoan);
        panelTaiKhoan.setLayout(panelTaiKhoanLayout);
        panelTaiKhoanLayout.setHorizontalGroup(
            panelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaiKhoanLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTaiKhoan_txt)
                    .addComponent(lblTaiKhoan))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        panelTaiKhoanLayout.setVerticalGroup(
            panelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lblTaiKhoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTaiKhoan_txt)
                .addGap(42, 42, 42))
        );

        panelGiaoDien.add(panelTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 360, -1, -1));

        lblClock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 255, 255));
        lblClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clock.png"))); // NOI18N
        panelGiaoDien.add(lblClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 210, 40));

        jLabel5.setBackground(new java.awt.Color(101, 157, 189));
        jLabel5.setOpaque(true);
        panelGiaoDien.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -36, 1040, 340));

        jLabel6.setBackground(new java.awt.Color(209, 232, 226));
        jLabel6.setOpaque(true);
        panelGiaoDien.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 1034, 530));

        getContentPane().add(panelGiaoDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 830));

        mnuHeThong.setText("Hệ thống");

        mniDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Key.png"))); // NOI18N
        mniDangNhap.setText("Đăng nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangNhap);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.add(jSeparator1);

        mniDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        mniDoiMatKhau.setText("Đổi mật khẩu");
        mniDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoiMatKhauActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDoiMatKhau);
        mnuHeThong.add(jSeparator2);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniKetThuc);

        jMenuBar1.add(mnuHeThong);

        mnuChucNang.setText("Chức năng");

        mniTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Unknown person.png"))); // NOI18N
        mniTaiKhoan.setText("Tài khoản");
        mniTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTaiKhoanActionPerformed(evt);
            }
        });
        mnuChucNang.add(mniTaiKhoan);

        mniTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Home.png"))); // NOI18N
        mniTongQuan.setText("Tổng quan");
        mniTongQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTongQuanActionPerformed(evt);
            }
        });
        mnuChucNang.add(mniTongQuan);

        mniViTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Wallet.png"))); // NOI18N
        mniViTien.setText("Ví tiền");
        mniViTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniViTienActionPerformed(evt);
            }
        });
        mnuChucNang.add(mniViTien);

        mniQLNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/User group.png"))); // NOI18N
        mniQLNguoiDung.setText("Người dùng");
        mniQLNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLNguoiDungActionPerformed(evt);
            }
        });
        mnuChucNang.add(mniQLNguoiDung);

        mniThuChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Calculator.png"))); // NOI18N
        mniThuChi.setText("Thu Chi");
        mniThuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThuChiActionPerformed(evt);
            }
        });
        mnuChucNang.add(mniThuChi);

        mniBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Properties.png"))); // NOI18N
        mniBaoCao.setText("Báo cáo");
        mniBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBaoCaoActionPerformed(evt);
            }
        });
        mnuChucNang.add(mniBaoCao);

        jMenuBar1.add(mnuChucNang);

        jMenu1.setText("Thống kê");

        mnuThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/green.png"))); // NOI18N
        mnuThu.setText("Phân tích thu");
        mnuThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThuActionPerformed(evt);
            }
        });
        jMenu1.add(mnuThu);

        menuChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/redbar.png"))); // NOI18N
        menuChi.setText("Phân tích chi");
        menuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChiActionPerformed(evt);
            }
        });
        jMenu1.add(menuChi);

        mnuThuChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/barChart_up.jpg"))); // NOI18N
        mnuThuChi.setText("Tình hình thu chi");
        mnuThuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThuChiActionPerformed(evt);
            }
        });
        jMenu1.add(mnuThuChi);
        jMenu1.add(jSeparator3);

        mnuTaiChinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/taiChinh.png"))); // NOI18N
        mnuTaiChinh.setText("Tình hình tài chính");
        mnuTaiChinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTaiChinhActionPerformed(evt);
            }
        });
        jMenu1.add(mnuTaiChinh);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Trợ giúp");

        openHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Brick house.png"))); // NOI18N
        openHuongDan.setText("Hướng dẫn");
        openHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openHuongDanActionPerformed(evt);
            }
        });
        jMenu2.add(openHuongDan);

        openGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Info.png"))); // NOI18N
        openGioiThieu.setText("Giới thiệu về phần mềm");
        openGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openGioiThieuActionPerformed(evt);
            }
        });
        jMenu2.add(openGioiThieu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         if(ShareHelper.authenticated()){
              init();
            }
        else{
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void panelTongQuanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTongQuanMouseEntered
        // TODO add your handling code here:
        miceEntered(panelTongQuan, lblTongQuan_txt);
    }//GEN-LAST:event_panelTongQuanMouseEntered

    private void panelTongQuanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTongQuanMouseExited
        // TODO add your handling code here:
        miceExited(panelTongQuan, lblTongQuan_txt);
    }//GEN-LAST:event_panelTongQuanMouseExited

    private void panelTongQuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTongQuanMouseClicked
        // TODO add your handling code here:
         tongQuan();
       
    }//GEN-LAST:event_panelTongQuanMouseClicked

    private void panelViTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViTienMouseClicked
        // TODO add your handling code here:
          viTien();
      
    }//GEN-LAST:event_panelViTienMouseClicked

    private void panelViTienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViTienMouseEntered
        // TODO add your handling code here:
        miceEntered(panelViTien, lblViTien_txt);
    }//GEN-LAST:event_panelViTienMouseEntered

    private void panelViTienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViTienMouseExited
        // TODO add your handling code here:
        miceExited(panelViTien, lblViTien_txt);
    }//GEN-LAST:event_panelViTienMouseExited

    private void panelThuChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelThuChiMouseClicked
        // TODO add your handling code here:
        thuChi();
        
    }//GEN-LAST:event_panelThuChiMouseClicked

    private void panelThuChiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelThuChiMouseEntered
        // TODO add your handling code here:
        miceEntered(panelThuChi, lblThu_Chi);
    }//GEN-LAST:event_panelThuChiMouseEntered

    private void panelThuChiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelThuChiMouseExited
        // TODO add your handling code here:
        miceExited(panelThuChi, lblThu_Chi);
    }//GEN-LAST:event_panelThuChiMouseExited

    private void panelNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNguoiDungMouseClicked
        // TODO add your handling code here:
        nguoiDung();
    }//GEN-LAST:event_panelNguoiDungMouseClicked

    private void panelNguoiDungMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNguoiDungMouseEntered
        // TODO add your handling code here:
        miceEntered(panelNguoiDung, lblNguoiDung_txt);
    }//GEN-LAST:event_panelNguoiDungMouseEntered

    private void panelNguoiDungMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNguoiDungMouseExited
        // TODO add your handling code here:
        miceExited(panelNguoiDung, lblNguoiDung_txt);
    }//GEN-LAST:event_panelNguoiDungMouseExited

    private void panelBaoCaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBaoCaoMouseClicked
        // TODO add your handling code here:
        baoCao();
        
    }//GEN-LAST:event_panelBaoCaoMouseClicked

    private void panelBaoCaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBaoCaoMouseEntered
        // TODO add your handling code here:
        miceEntered(panelBaoCao, lblBaoCao_txt);
    }//GEN-LAST:event_panelBaoCaoMouseEntered

    private void panelBaoCaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBaoCaoMouseExited
        // TODO add your handling code here:
        miceExited(panelBaoCao, lblBaoCao_txt);
    }//GEN-LAST:event_panelBaoCaoMouseExited

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        logoff();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void panelTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTaiKhoanMouseClicked
        // TODO add your handling code here:
       taiKhoan();
    }//GEN-LAST:event_panelTaiKhoanMouseClicked

    private void panelTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTaiKhoanMouseEntered
        // TODO add your handling code here:
        miceEntered(panelTaiKhoan, lblTaiKhoan_txt);
    }//GEN-LAST:event_panelTaiKhoanMouseEntered

    private void panelTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTaiKhoanMouseExited
        // TODO add your handling code here:
        miceExited(panelTaiKhoan, lblTaiKhoan_txt);
    }//GEN-LAST:event_panelTaiKhoanMouseExited

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        // TODO add your handling code here:
        
        logoff();
    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:

        logoff();

    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoiMatKhauActionPerformed
        // TODO add your handling code here:
        
        new QuenMatKhau_1().setVisible(true);
    }//GEN-LAST:event_mniDoiMatKhauActionPerformed

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_mniKetThucActionPerformed

    private void mniTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTaiKhoanActionPerformed
        // TODO add your handling code here:
        taiKhoan();
    }//GEN-LAST:event_mniTaiKhoanActionPerformed

    private void mniTongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTongQuanActionPerformed
        // TODO add your handling code here:
       tongQuan();
    }//GEN-LAST:event_mniTongQuanActionPerformed

    private void mniViTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniViTienActionPerformed
        // TODO add your handling code here:
        viTien();
    }//GEN-LAST:event_mniViTienActionPerformed

    private void mniQLNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLNguoiDungActionPerformed
        // TODO add your handling code here:
        nguoiDung();
    }//GEN-LAST:event_mniQLNguoiDungActionPerformed

    private void mniBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBaoCaoActionPerformed
        // TODO add your handling code here:
        baoCao();
    }//GEN-LAST:event_mniBaoCaoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void mniThuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThuChiActionPerformed
        // TODO add your handling code here:
        thuChi();
    }//GEN-LAST:event_mniThuChiActionPerformed

    private void mnuThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThuActionPerformed
        // TODO add your handling code here:
        this.dispose();
        openThongKe(2);
    }//GEN-LAST:event_mnuThuActionPerformed

    private void menuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChiActionPerformed
        // TODO add your handling code here:
        this.dispose();
        openThongKe(1);
    }//GEN-LAST:event_menuChiActionPerformed

    private void mnuThuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThuChiActionPerformed
        // TODO add your handling code here:
        this.dispose();
        openThongKe(0);
    }//GEN-LAST:event_mnuThuChiActionPerformed

    private void mnuTaiChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTaiChinhActionPerformed
        // TODO add your handling code here:
        this.dispose();
        openThongKe(3);
    }//GEN-LAST:event_mnuTaiChinhActionPerformed

    private void openHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openHuongDanActionPerformed
        // TODO add your handling code here:
        openHuongDan();
    }//GEN-LAST:event_openHuongDanActionPerformed

    private void openGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openGioiThieuActionPerformed
        // TODO add your handling code here:
        openInfo();
    }//GEN-LAST:event_openGioiThieuActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblBaoCao;
    private javax.swing.JLabel lblBaoCao_txt;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblHinhDaiDien;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblNguoiDung_txt;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTaiKhoan_txt;
    private javax.swing.JLabel lblThuChi;
    private javax.swing.JLabel lblThu_Chi;
    private javax.swing.JLabel lblTongQuan;
    private javax.swing.JLabel lblTongQuan_txt;
    private javax.swing.JLabel lblViTien;
    private javax.swing.JLabel lblViTien_txt;
    private javax.swing.JMenuItem menuChi;
    private javax.swing.JMenuItem mniBaoCao;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDoiMatKhau;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniQLNguoiDung;
    private javax.swing.JMenuItem mniTaiKhoan;
    private javax.swing.JMenuItem mniThuChi;
    private javax.swing.JMenuItem mniTongQuan;
    private javax.swing.JMenuItem mniViTien;
    private javax.swing.JMenu mnuChucNang;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenuItem mnuTaiChinh;
    private javax.swing.JMenuItem mnuThu;
    private javax.swing.JMenuItem mnuThuChi;
    private javax.swing.JMenuItem openGioiThieu;
    private javax.swing.JMenuItem openHuongDan;
    private javax.swing.JPanel panelBaoCao;
    private javax.swing.JPanel panelGiaoDien;
    private javax.swing.JPanel panelHinhDaiDien;
    private javax.swing.JPanel panelNguoiDung;
    private javax.swing.JPanel panelTaiKhoan;
    private javax.swing.JPanel panelThuChi;
    private javax.swing.JPanel panelTongQuan;
    private javax.swing.JPanel panelViTien;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SendMail_LayMaHelper {
     public String layma(){
        /*
        :: la double colon, dùng cho việc đi tắt và giúp cho biến kia gọi lên phương thức tham chiếu đó;  như StringBuilder::new thì sẽ tạo một new StringBuilder() 
       tránh viết new StringBuilder()vì rằng một void không thể thành một biến ở trong một hàm được, nên sử dụng :: để tạo đường tắt để có thể sử dụng nhanh phương thức
        tránh việc gọi lên rườm rà
        */
         int leftLimit = 48; // letter '0'
        int rightLimit = 122; // letter 'z'
        int len = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
        return generatedString;
    }
    
    public void sendMail(String Email, String maXacNhan){
         // TODO add your handling code here:
        try{
            String taiKhoan = "windnunbe@gmail.com";
            String matKhau = "tumottoichin";
            //Tạo đối tượng Properties và chỉ định thông tin host, port
            Properties prop = new Properties(); 
            
            prop.setProperty("mail.user", taiKhoan);
            prop.setProperty("mail.password", matKhau);
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
          
            //Tạo đối tượng session
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(taiKhoan, matKhau);
                    }
                });
           
           
            String subject = "Mã kích hoạt để tạo lại mật khẩu mới";
            String body = "Mã kích hoạt:  " + maXacNhan;
           
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(taiKhoan));
            message.setRecipients
            (
                Message.RecipientType.TO,
                InternetAddress.parse(Email)
            );
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);//gọi phương thức send để gửi mess di
           JOptionPane.showMessageDialog(null,"Đã gởi mail thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Gởi mail thất bại","Thông báo",JOptionPane.ERROR_MESSAGE);
            
        }
    }
}

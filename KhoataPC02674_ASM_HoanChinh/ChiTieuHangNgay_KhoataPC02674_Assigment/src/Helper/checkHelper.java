/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import static java.awt.Color.red;
import static java.awt.Color.white;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.RadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class checkHelper {

    

    //pass từ 6-16 kí tự
    public static boolean checkPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 7 && txt.getPassword().length < 17) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải có từ 8-16 kí tự.");
            return false;
        }
    }
    
    public static boolean checkPass2(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().length() > 7 && txt.getText().length() < 17) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải có từ 8-16 kí tự.");
            return false;
        }
    }
    
    
    public static boolean checkXNPass(JPasswordField txtXNPass, JPasswordField txtPass) {
        txtXNPass.setBackground(white);
        txtPass.setBackground(white);
        if (txtXNPass.getText().equals(txtPass.getText())) {
            return true;
        } else {
            txtXNPass.setBackground(red);
            txtPass.setBackground(red);
            
            DialogHelper.alert(txtXNPass.getRootPane(), "Mật khẩu và xác nhận mật khẩu không giống nhau!");
            return false;
        }
    }
    
    public static boolean checkXNPass2(JTextField txtXNPass, JTextField txtPass) {
        txtXNPass.setBackground(white);
        txtPass.setBackground(white);
        if (txtXNPass.getText().equals(txtPass.getText())) {
            return true;
        } else {
            txtXNPass.setBackground(red);
            txtPass.setBackground(red);
            
            DialogHelper.alert(txtXNPass.getRootPane(), "Mật khẩu và xác nhận mật khẩu không giống nhau!");
            return false;
        }
    }
    
    public static boolean isValidDate(String inDate) {

        if (inDate == null) {
            return false;
        }

   
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
        
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    //định dạng dd/MM/yyyy (hoặc d/M/yyyy nếu là số 0 đứng trước)
    public static boolean checkDate(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();

        if (isValidDate(id)) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " không đúng định dạng dd/MM/yyyy");
            return false;
        }
    }

    //gồm các ký tự chữ đấu cách
    //từ 3-25 kí tự
    public static boolean checkName(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        Pattern p = Pattern.compile("\\p{L}{3,25}");
        Matcher matcher = p.matcher(id);
        
        
        if (matcher.find()) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải là tên tiếng việt hoặc không đấu\ntừ 3-25 kí tự");
            return false;
        }
    }
    
    public static boolean checkName2(String string, java.awt.Frame parent) {
        String id = string;
        Pattern p = Pattern.compile("\\p{L}{3,25}");
        Matcher matcher = p.matcher(id);
        
        
        if (matcher.find()) {
            return true;
        } else {
           
            DialogHelper.alert(parent, string + " phải là tên tiếng việt hoặc không đấu\ntừ 3-25 kí tự");
            return false;
        }
    }
    
    
    
    public static boolean checkSoDu(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher matcher = p.matcher(id);
        
        
        if (matcher.find()) {
             txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải là số");
            return false;
            
        } else {
           return true;
        }
    }
    
     public static boolean checkSoAm(JTextField txt) {
        txt.setBackground(white);
        int id = Integer.parseInt(txt.getText());
        
        
        if (id<0) {
             txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), "Không được nhập số âm");
            return false;
            
        } else {
           return true;
        }
    }
    
     //từ 3-25 kí tự
    public static boolean checkTaiKhoan(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        Pattern p = Pattern.compile("[a-zA-Z0-9]{5,25}");
        Matcher matcher = p.matcher(id);
        
        
        if (matcher.find()) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " không có dấu và từ 5-25 kí tự");
            return false;
        }
    }
    public static boolean checkKituMK(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        Pattern p = Pattern.compile("[a-zA-Z0-9]{8,25}");
        Matcher matcher = p.matcher(id);
        
        
        if (matcher.find()) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " không có dấu và từ 8-16 kí tự");
            return false;
        }
    }
    //gồm 10 số 
    //các đầu 3 số của nhà mạng
    public static boolean checkSDT(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "(0)[0-9]{9}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải gồm 10 số và bắt đầu bằng số 0\n.");
            return false;
        }
    }
    
    public static boolean checkThang(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        int id2 = Integer.parseInt(id);
        if (id2>=1 &&  id2<=31) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải gồm 1 số và nhỏ hơn hoặc bằng 31 \n.");
            return false;
        }
    }
    
    public static boolean checkNam(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        int id2 = Integer.parseInt(id);
        if (id2>2018) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải gồm 4 số và bắt đầu là từ năm 2019 \n.");
            return false;
        }
    }
    
    public static boolean checkEmail(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " không đúng định dạng");
            return false;
        }
    }

    

    

   //bất kì kí tự nào
    //từ 3-255 kí tự
    public static boolean checkDiaChi(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = ".{3,255}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), txt.getText() + " phải từ 3-255 kí tự.");
            return false;
        }
    }

    public static boolean checkNullText(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }


    public static boolean checkNullPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 0) {
            return true;
        } else {
            txt.setBackground(red);
            txt.requestFocus();
            DialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }
    
    public static boolean checkRdo(JRadioButton rdo1, JRadioButton rdo2, JRadioButton rdo3) {
       
        if(rdo1.isSelected() || rdo2.isSelected() || rdo3.isSelected()){
            return true;
        }
        else{
            
            rdo1.requestFocus();
            DialogHelper.alert(rdo1.getRootPane(),"Không được để trống giới tính");
            return false;
        }
    }
    
    
     public static boolean checkRdo2(JRadioButton rdo1, JRadioButton rdo2) {
       
        if(rdo1.isSelected() || rdo2.isSelected() ){
            return true;
        }
        else{
            
            rdo1.requestFocus();
            DialogHelper.alert(rdo1.getRootPane(),"Không được để trống vai trò");
            return false;
        }
    }
}

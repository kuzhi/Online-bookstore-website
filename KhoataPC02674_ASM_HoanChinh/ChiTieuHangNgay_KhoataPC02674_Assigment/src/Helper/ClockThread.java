/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class ClockThread extends Thread{
    private JLabel jbl;
    private JLabel jb2;
    private JLabel jb3;

    public ClockThread(JLabel jbl) {
        this.jbl = jbl;
        
    }
    public void run(){
        SimpleDateFormat formater = new SimpleDateFormat();
        SimpleDateFormat dateFormater = new SimpleDateFormat();
        SimpleDateFormat dayFormater = new SimpleDateFormat();
        Calendar calendar;
        String date;
        String day;
        while(true){
                    Date now = new Date();
                    
            formater.applyPattern("hh:mm:ss aa");
            dateFormater.applyPattern("dd-MM-yyyy");
            dayFormater.applyPattern("E");
            
            
            String time = formater.format(now);
            date = dateFormater.format(now);
            day = dayFormater.format(now);
            
            this.jbl.setText(time);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
}

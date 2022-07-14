/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class DongHoHelper extends Thread{
    public int done = 60;
    public int interval;
    public Timer timer;
    public JLabel  jlabel;
    
   
    private  final int setInterval() {
    if (interval == 1)
        timer.cancel();
    return --interval;
}
    public void run(){
        int delay = 1000;
    int period = 1000;
    timer = new Timer();
    interval = 60;
    timer.scheduleAtFixedRate(new TimerTask() {
 
        public void run() {
            setInterval();
           jlabel.setText(String.valueOf(interval));

        }
    }, delay, period);
       
    }
}

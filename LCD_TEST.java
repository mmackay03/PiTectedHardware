
import com.pi4j.component.lcd.LCDTextAlignment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bball_000
 */
public class LCD_TEST 
{
    public static String words = "";
    
    public static void main(String[] args) throws InterruptedException 
    {
        LCD lcd = new LCD();
        words = "test 1";
        
        lcd.backlight(1);
        lcd.writeln(0, words);
        Thread.sleep(5000);
        words = "test 2";
        lcd.writeln(1, words);
        Thread.sleep(5000);
        lcd.clear();
        lcd.writeln(0, words, LCDTextAlignment.ALIGN_CENTER);
        Thread.sleep(5000);
        lcd.backlight(0);
        lcd.clear();
    }
    
}

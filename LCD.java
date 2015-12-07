/**
 *
 * @author Bryce Renninger
 *         Pitechted
 */
import com.pi4j.io.gpio.*;
import com.pi4j.util.StringUtil;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;

public class LCD 
{
    
    public final int LCD_ROWS = 2;
    public final int LCD_ROW_1 = 0;
    public final int LCD_ROW_2 = 1;
    public final int LCD_COLUMNS = 16;
    public final int LCD_BITS = 4;
    public GpioLcdDisplay lcd;
    final GpioController gpio = GpioFactory.getInstance();
    public GpioPinDigitalOutput backlight;
    
    
    
    
    public LCD()
    { 
        lcd = new GpioLcdDisplay(               LCD_ROWS,          // number of row supported by LCD
                                                LCD_COLUMNS,       // number of columns supported by LCD
                                                RaspiPin.GPIO_26,  // LCD RS pin
                                                RaspiPin.GPIO_11,  // LCD strobe pin
                                                RaspiPin.GPIO_10,  // LCD data bit 1
                                                RaspiPin.GPIO_06,  // LCD data bit 2
                                                RaspiPin.GPIO_05,  // LCD data bit 3
                                                RaspiPin.GPIO_01); // LCD data bit 4 
        //Initialies the backlight
        backlight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, PinState.HIGH);
//      System.out.println(backlight.getState());
    }
   //Writes data to top line
    public void write(String data)
    {
        lcd.write(data);
    }
    //Writes data to a specific line
    public void write(int row, String data)
    {
        lcd.write(row, data);
    }
    //Writes data to a specific line and then clears to the next line
    public void writeln(int row, String data)
    {
        lcd.writeln(row, data);
    }
    //Writes data to a specific line and can set the alignment of the text
     public void writeln(int row, String data, LCDTextAlignment alignment) {        
        String result = data;
        if(data.length() < LCD_COLUMNS){
            if(alignment == LCDTextAlignment.ALIGN_LEFT)
                result = StringUtil.padRight(data, (LCD_COLUMNS - data.length()));
            else if(alignment == LCDTextAlignment.ALIGN_RIGHT)
                result = StringUtil.padLeft(data, (LCD_COLUMNS - data.length()));
            else if(alignment == LCDTextAlignment.ALIGN_CENTER)
                result = StringUtil.padCenter(data, LCD_COLUMNS);
        }
        lcd.write(row, 0, result);
    }
    //CLears all the text on the LCD
    public void clear()
    {
        lcd.clear();
    }
    // Clears all of the text on a specific line
    public void clear(int row)
    {
        lcd.clear(row);
    }
    //Puts the cursor to Row 1 column 1
    public void homeCursor()
    {
        lcd.setCursorHome();
    }
    //Controls the backlight on the LCD to turn on or off.
    public void backlight(int status)
    {
        if(status == 1)
        {
            backlight.low();
        }
        else
        {
            backlight.high();
        }
    }
            
}

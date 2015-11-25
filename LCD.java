/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pi4j.component.Component;
import com.pi4j.component.lcd.LCDBase;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.Pin;
import com.pi4j.wiringpi.Lcd;
import com.pi4j.util.StringUtil;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;

/**
 *
 * @author bball_000
 */
public class LCD 
{
    
    public final int LCD_ROWS = 2;
    public final int LCD_ROW_1 = 0;
    public final int LCD_ROW_2 = 1;
    public final int LCD_COLUMNS = 16;
    public final int LCD_BITS = 4;
    public GpioLcdDisplay lcd;
    public GpioPinDigitalOutput backlight; 
    
    public LCD()
    {
        final GpioController gpio = GpioFactory.getInstance();
        lcd = new GpioLcdDisplay(LCD_ROWS,          // number of row supported by LCD
                                                LCD_COLUMNS,       // number of columns supported by LCD
                                                RaspiPin.GPIO_11,  // LCD RS pin
                                                RaspiPin.GPIO_10,  // LCD strobe pin
                                                RaspiPin.GPIO_00,  // LCD data bit 1
                                                RaspiPin.GPIO_01,  // LCD data bit 2
                                                RaspiPin.GPIO_02,  // LCD data bit 3
                                                RaspiPin.GPIO_03); // LCD data bit 4
        backlight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, PinState.HIGH);
        
    }
   
    public void write(String data)
    {
        lcd.write(data);
    }
    public void write(int row, String data)
    {
        lcd.write(row, data);
    }
    public void writeln(int row, String data)
    {
        lcd.writeln(row, data);
    }
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
    public void clear()
    {
        lcd.clear();
    }
    public void clear(int row)
    {
        lcd.clear(row);
    }
    public void homeCursor()
    {
        lcd.setCursorHome();
    }
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

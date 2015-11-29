/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CBus;

/**
 *
 * @author bball_000
 */
public class Expander {
    public static void main(String args[]) throws InterruptedException, IOException 
    {
        
        System.out.println("<--Pi4J--> Expander ... started.");
        
        final GpioController gpio = GpioFactory.getInstance();
        final MCP23017GpioProvider gpioProvider = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);
        
        final GpioPinDigitalOutput light = gpio.provisionDigitalOutputPin(gpioProvider, MCP23017Pin.GPIO_A6, "MyOutput-A6", PinState.LOW);
        
        light.high();
        Thread.sleep(5000);
        light.low();

    }
}

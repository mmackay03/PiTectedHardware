/*
 * PiTected Project
 * KeyboardContorller.java found online in keypad example
 * https://code.google.com/p/raspberry-pi4j-samples/source/browse/PhoneKeyboard3x4/src/phonekeyboard3x4/KeyboardController.java?r=d3e8ac190b04bf2c9d6d95bb8d311c023f27c53a
 * 
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class KeyboardController
{
  private GpioController gpio = null;
  private GpioPinDigitalMultipurpose[] rowButton = null;
  private GpioPinDigitalMultipurpose[] colButton = null;
  
  private char[][] keypad = new char[][] 
  {
    { '1', '2', '3' },
    { '4', '5', '6' },
    { '7', '8', '9' },
    { '*', '0', '#' }
  };

  /* 
   * Seen from the TOP of the keypad
   * https://www.adafruit.com/products/1824
   *   _________________________________
   *     |  |  |  |  |   |     |     |
   *     x  19 13 6  5  SCLK  MISO  MOSI
   */
  
  //                                               5                 6                13                19
  private Pin[] kpRow = new Pin[] { RaspiPin.GPIO_21, RaspiPin.GPIO_22, RaspiPin.GPIO_23, RaspiPin.GPIO_24 };
  //                                             MOSI              MISO               SCLK
  private Pin[] kpCol = new Pin[] { RaspiPin.GPIO_12, RaspiPin.GPIO_13, RaspiPin.GPIO_14 };

  public KeyboardController()
  {
    this.gpio = GpioFactory.getInstance();
    rowButton = new GpioPinDigitalMultipurpose[kpRow.length];
    for (int i=0; i<kpRow.length; i++)
      rowButton[i] = this.gpio.provisionDigitalMultipurposePin(kpRow[i], PinMode.DIGITAL_INPUT);
    colButton = new GpioPinDigitalMultipurpose[kpCol.length];
    for (int i=0; i<kpCol.length; i++)
      colButton[i] = this.gpio.provisionDigitalMultipurposePin(kpCol[i], PinMode.DIGITAL_INPUT);
    System.out.println("All pins provisioned, keypad ready.");
  }
  
  public char getKey()
  {
    char c = ' ';
    while (c == ' ')
    {
      // Set all column to output low
      for (int i=0; i<colButton.length; i++)
      {
        colButton[i].setMode(PinMode.DIGITAL_OUTPUT);
        colButton[i].low();
      }

      // All rows as input
      for (int i=0; i<rowButton.length; i++)
      {
        rowButton[i].setMode(PinMode.DIGITAL_INPUT);
        rowButton[i].setPullResistance(PinPullResistance.PULL_UP);
      }   
      int row = -1, col = -1;
      // Scan rows for pushed keys
      for (int i=0; i<rowButton.length; i++)
      {
        if (this.gpio.isLow(rowButton[i]))
        {
          row = i;
          break;
        }
      }
      if (row != -1)
      {
        // Set (convert) columns to input
        for (int i=0; i<colButton.length; i++)
        {
          colButton[i].setMode(PinMode.DIGITAL_INPUT);
          colButton[i].setPullResistance(PinPullResistance.PULL_DOWN);
        }
        // Scan cols for pushed keys
        for (int i=0; i<kpCol.length; i++)
        {
          if (this.gpio.isHigh(colButton[i]))
          {
            col = i;        
            break;
          }
        }
        if (col != -1)
        {
          c = keypad[row][col];
          reset();
     //   System.out.println("  >>> getKey: [" + row + ", " + col + "] = " + c);
        }
        else
          reset();
      }
      else
        reset();
    }
    return c;
  }
  
  /**
   * Reinitialize all rows and columns as input at exit
   */
  private void reset()
  {
    if (rowButton != null)
    {
      for (int i=0; i<rowButton.length; i++)
      {
        rowButton[i].setMode(PinMode.DIGITAL_INPUT);
        rowButton[i].setPullResistance(PinPullResistance.PULL_UP);
      }
    }
    if (colButton != null)
    {
      for (int i=0; i<colButton.length; i++)
      {
        colButton[i].setMode(PinMode.DIGITAL_INPUT);
        colButton[i].setPullResistance(PinPullResistance.PULL_UP);
      }
    }
  }
  
  public void shutdown()
  {
    this.gpio.shutdown();
  }
}

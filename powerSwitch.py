#Author: Margo Mac Kay
#July 2015
#Intended to safely power off the Pi
#Added "sudo python /opt/pi4j/PiTected/powerSwitch.py &" to rc.local to run on boot
#Allow GPIO pin calls
import RPi.GPIO as GPIO
#import os for linux commands
import os
#import time for sleep
import time

#set mode for Broadcom SOC channel
GPIO.setmode(GPIO.BCM)
#use gpio21 for input
INPUT_PIN=21
GPIO.setup(INPUT_PIN, GPIO.IN, pull_up_down=GPIO.PUD_UP)

#loop
while True:
	#wait for button to be pushed
	GPIO.wait_for_edge(INPUT_PIN, GPIO.FALLING)
	#halt command
	os.system('sudo shutdown -h now')
	#sleep for 1 second in case button is hit twice
	time.sleep(1)

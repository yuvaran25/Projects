package org.sankar;

import java.io.*;

import lejos.nxt.*;
import lejos.nxt.comm.*;


public class Receiver extends Thread
{ 
	public static volatile DataExchange DE;
	private  static DataReceiver DR;
	public static DataOutputStream dataOut; 	
	public static USBConnection USBLink;
	public static ObstacleDetector OD;
	public static DataInputStream dataIn;	
	public static BTConnection BTLink;
	public static BTConnection btLink;
	public static int speed =50, turnSpeed = 50,speedBuffer, speedControl;
	public static int commandValue,transmitReceived;
	public static boolean[] control = new boolean[6];
	public static boolean[] command = new boolean[6];
	public static UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);	
	public static TouchSensor touch = new TouchSensor(SensorPort.S2);
	
	public Receiver(DataExchange DE){
		this.DE = DE;		
	}

	public void run(){

		connect(); 
		OD = new ObstacleDetector(DE);
		OD.start();
		DR = new DataReceiver(DE);
		DR.start();
		while(true)
		{ 						
			try {
				control = checkCommand();
			} catch (IOException e) {
				// TODO Auto-generated catch block				
			}
			try {
				speedControl = getSpeed(control);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			move(control, speedControl);
		}		
	}

	public static boolean[] checkCommand() throws IOException//check input data
	{

		transmitReceived = DE.transmitReceived;		

		if(transmitReceived == 1) {command[0] = true;}//forward
		if(transmitReceived == 10){command[0] = false;}
		if(transmitReceived == 2) {command[1] = true;}//backward
		if(transmitReceived == 20){command[1] = false;}
		if(transmitReceived == 3) {command[2] = true;}//leftTurn
		if(transmitReceived == 30){command[2] = false;}
		if(transmitReceived == 4) {command[3] = true;}//rightTurn
		if(transmitReceived == 40){command[3] = false;}
		if(transmitReceived == 5) {command[0] = false;//stop
		command[1] = false;
		command[2] = false;
		command[3] = false;}
		if(transmitReceived == 6) {command[4] = true;}//speed up
		if(transmitReceived == 60){command[4] = false;}
		if(transmitReceived == 7) {command[5] = true;}//slow down
		if(transmitReceived == 70){command[5] = false;}
		if(transmitReceived == 8) {command[0] = false;//stop		
		command[2] = false;
		command[3] = false;}
		else{};
		return command;

	}

	public static void move(boolean[]D, int S)
	{ 
		int movingSpeed;
		boolean[] direction = new boolean[4];

		direction[0] = D[0];
		direction[1] = D[1];
		direction[2] = D[2];
		direction[3] = D[3];

		movingSpeed = S;

		Motor.A.setSpeed(movingSpeed);
		Motor.C.setSpeed(movingSpeed);
		
		if(direction[0] == true)
		{								
			if(!touch.isPressed() || DE.distance < 25){					
				Motor.A.stop();
				Motor.C.stop();
			}
			else {				
				Motor.A.forward();  
				Motor.C.forward();
			}						
		}

		if(direction[1] == true)
		{							
				Motor.A.backward();  
				Motor.C.backward();								
		}

		if(direction[2] == true)
		{
			if(!touch.isPressed() || DE.distance < 25){
				Motor.A.stop();
				Motor.C.stop();
			}
			else {
				Motor.A.setSpeed(turnSpeed);
				Motor.C.setSpeed(turnSpeed);
				Motor.A.forward();
				Motor.C.backward();
			}			
		}

		if(direction[3] == true)
		{
			if(!touch.isPressed() || DE.distance < 25){
				Motor.A.stop();
				Motor.C.stop();
			}
			else {
				Motor.A.setSpeed(turnSpeed);
				Motor.C.setSpeed(turnSpeed);
				Motor.A.backward(); 
				Motor.C.forward();
			}			
		}

		if(direction[0] == true && direction[2] == true)
		{
			speedBuffer =  (int) (movingSpeed *1.5);

			Motor.A.setSpeed(speedBuffer);
			Motor.C.forward();
			Motor.A.forward();
		}

		if(direction[0] == true && direction[3] == true)
		{
			speedBuffer =  (int) (movingSpeed *1.5);

			Motor.C.setSpeed(speedBuffer);
			Motor.C.forward();
			Motor.A.forward();
		}

		if(direction[1] == true && direction[2] == true)
		{
			speedBuffer =  (int) (movingSpeed *1.5);

			Motor.A.setSpeed(speedBuffer);
			Motor.C.backward();
			Motor.A.backward();
		}

		if(direction[1] == true && direction[3] == true)
		{
			speedBuffer =  (int) (movingSpeed *1.5);

			Motor.C.setSpeed(speedBuffer);
			Motor.C.backward();
			Motor.A.backward();
		}

		if(direction[0] == false && direction[1] == false &&
				direction[2] == false && direction[3] == false)
		{
			Motor.A.stop();
			Motor.C.stop();
		}

	}

	public static void connect()
	{  
		System.out.println("Listening");
		BTLink = Bluetooth.waitForConnection();    
		dataOut = BTLink.openDataOutputStream();
		dataIn = BTLink.openDataInputStream();
		//USBLink = USB.waitForConnection();
		//dataOut = USBLink.openDataOutputStream();
		//dataIn = USBLink.openDataInputStream();
		DE.setDataIn(dataIn);

	}

	public static int getSpeed(boolean[] D) throws InterruptedException
	{
		boolean accelerate, decelerate;

		accelerate = D[4];
		decelerate = D[5];

		if(accelerate == true)
		{
			speed += 20;		
			Thread.sleep(1000);
			command[4] = false;
		}

		if(decelerate == true)
		{
			speed -= 20;
			command[5] = false;
		}

		return speed;
	}

}

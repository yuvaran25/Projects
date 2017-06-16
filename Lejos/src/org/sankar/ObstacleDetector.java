package org.sankar;
import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.*;
import lejos.nxt.comm.USBConnection;

public class ObstacleDetector extends Thread {

	public static volatile DataExchange DE;	
	public UltrasonicSensor us;	
	public int transmitReceived;
	public TouchSensor touch;

	public ObstacleDetector(DataExchange DE){
		this.DE = DE;		
	}

	public void run(){	
		us = new UltrasonicSensor(SensorPort.S4);
		touch = new TouchSensor(SensorPort.S2);
		while(true){			
			DE.setDistance(us.getDistance());							
			if((DE.distance < 25) || (!touch.isPressed())){	
				System.out.println("inside stop");
				transmitReceived = 8;
				try {
					DE.setcmd(transmitReceived);					
					//transmitReceived = DE.dataIn.readInt();
					//DE.setcmd(transmitReceived);
					//DE.setcmd(DE.transmitReceived);					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("in Obstacle");
				}		
			}
			/*else{
				try {
					//transmitReceived = DE.dataIn.readInt();
					//DE.setcmd(transmitReceived);
					DE.setcmd(DE.transmitReceived);
				} catch (IOException e) {
					// TODO Auto-generated catch block					
				}				
			}*/
		}
	}
}
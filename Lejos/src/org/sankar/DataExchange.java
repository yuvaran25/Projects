package org.sankar;

import java.io.DataInputStream;
import java.io.IOException;

public class DataExchange {
	
	public static int transmitReceived;
	public static DataInputStream dataIn;
	public volatile int distance;
	
	public void setcmd(int command) throws IOException{
		
		transmitReceived = command;
	}
	
	public void setDataIn(DataInputStream DI){
		this.dataIn = DI;
	}
	
	public void setDistance(int distance){
		this.distance = distance;
	}
}
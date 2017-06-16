//Developed by Sankar for the project of Telepresence Robot - Ubiquitous Computing Project - Summer 2017

package org.sankar;

import java.io.DataInputStream;
import java.io.IOException;

public class DataReceiver extends Thread{
	public volatile DataExchange DE;	
	public static DataInputStream receivedData;
	
	public DataReceiver(DataExchange DE){
		this.DE = DE;		
	}
	
	public void run(){
		while(true){
			try {			
				DE.setcmd(DataExchange.dataIn.readInt());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
	}
}

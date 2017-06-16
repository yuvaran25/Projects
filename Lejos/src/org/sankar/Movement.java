package org.sankar;
import lejos.nxt.*;

//This triggers the main programme. It triggers the main thread(Receiver)
public class Movement {
	private static DataExchange DE;
	private static ObstacleDetector OD;
	private static Receiver RC;	
	
	public static void main(String[] args){
		DE = new DataExchange();
		RC = new Receiver(DE);
		
		
		RC.start();						
				
	}
}

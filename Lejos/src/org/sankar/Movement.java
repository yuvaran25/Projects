package org.sankar;
import lejos.nxt.*;

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

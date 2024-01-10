package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class GmanGame {
		
	 private static final int INITIAL_POWER = 200;
	    private static final int TURN_POWER_LOSS = 5;
	    private static final int MOVE_POWER_LOSS = 10;

	    public static void main(String[] args) {
	      int sX=0, sY=0, dX=0, dY=0;
	        String dir="";
	         try {
	            FileInputStream fis = new FileInputStream("./Input.txt");
	            Scanner sc = new Scanner(fis); 
	            while (sc.hasNextLine()) {
	                String input = sc.nextLine();
	                String[] cmd = input.split(" ");
	                switch (cmd[0]) {
	                    case "SOURCE":
	                        sX = Integer.parseInt(cmd[1]);
	                        sY =  Integer.parseInt(cmd[2]);
	                        dir =  cmd[3];
	                        break;
	                    case "DESTINATION":
	                        dX = Integer.parseInt(cmd[1]);
	                        dY =  Integer.parseInt(cmd[2]);
	                        break;
	                    case "PRINT_POWER":
	                        int p  = calculateRemainingPower(sX, sY, dir, dX, dY);    
	                        System.out.println("POWER "+ p);
	                        break;                    
	                }
	            }
	            sc.close(); 
	        } catch (IOException e) {
	        }
	     }
	   private static int calculateRemainingPower(int sourceX, int sourceY, String sourceDirection, int destX, int destY) {
	        int turnsNeeded = Math.abs(getDirectionIndex(sourceDirection) - getDirectionIndex(calculateDirection(sourceX, sourceY, destX, destY)));
	        int stepsNeeded = Math.abs(destX - sourceX) + Math.abs(destY - sourceY);

	        int powerLoss = calculatePowerLoss(stepsNeeded, turnsNeeded);
	        return Math.max(INITIAL_POWER - powerLoss, 0);
	    }

	   private static int getDirectionIndex(String direction) {
	        String[] compass = {"N", "E", "S", "W"};
	        for (int i = 0; i < compass.length; i++) {
	            if (compass[i].equals(direction)) {
	                return i;
	            }
	        }
	        return -1;
	    }

	    private static String calculateDirection(int sourceX, int sourceY, int destX, int destY) {
	        if (destX > sourceX) {
	            return "E";
	        } else if (destX < sourceX) {
	            return "W";
	        } else if (destY > sourceY) {
	            return "N";
	        } else {
	            return "S";
	        }
	    }

	    private static int calculatePowerLoss(int steps, int turns) {
	        return MOVE_POWER_LOSS * steps + TURN_POWER_LOSS * turns;
	    }
}

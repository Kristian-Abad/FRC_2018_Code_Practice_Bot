package org.usfirst.frc.team399.Systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Climber {
	TalonSRX Climber_Pully_1 = null;
	TalonSRX Climber_Winch_1 = null;
	TalonSRX Climber_Winch_2 = null;
	
	double output;
	public Climber(int POne, int WOne,int WTwo) {
		Climber_Pully_1 = new TalonSRX(POne);
	    Climber_Winch_1 = new TalonSRX(WOne);
	    Climber_Winch_2 = new TalonSRX(WTwo);
	    
	    Climber_Winch_2.setInverted(true);
	}
	public void teleopLoop(double Pully,boolean reverseWinch,boolean otherWinch) {
//		  output = (Pully < 0 ? Pully : Math.abs(Pully));
//		  Climber_Winch_1.set(ControlMode.PercentOutput, output);
//		  Climber_Winch_2.set(ControlMode.PercentOutput, output);
		
		  if(Pully > 0 && Pully > 0.1) {
		  
			  Climber_Winch_1.set(ControlMode.PercentOutput, Pully);
			  Climber_Winch_2.set(ControlMode.PercentOutput, Pully);
		  }else if(Pully < 0 && Pully < -0.1 ) {
			Climber_Pully_1.set(ControlMode.PercentOutput, Pully);
			
		  }else if(reverseWinch){

			  Climber_Winch_1.set(ControlMode.PercentOutput, 1);
			  Climber_Winch_2.set(ControlMode.PercentOutput, 1);
		  }else if(otherWinch) {
			  Climber_Winch_1.set(ControlMode.PercentOutput, -1);
			  Climber_Winch_2.set(ControlMode.PercentOutput, -1);
		  }else {
			  Climber_Winch_1.set(ControlMode.PercentOutput, 0);
			  Climber_Winch_2.set(ControlMode.PercentOutput, 0);
			  Climber_Pully_1.set(ControlMode.PercentOutput, 0);
		
		  }
	}
}

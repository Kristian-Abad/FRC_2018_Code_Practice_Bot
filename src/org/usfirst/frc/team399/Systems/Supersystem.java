package org.usfirst.frc.team399.Systems;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Utilities.Controls;

import edu.wpi.first.wpilibj.Joystick;

public class Supersystem {
	public static Supersystem instance = null;
//	public Joystick OperatorGamepad = new Joystick(2);
	public Controls controls;
	
	public Drivetrain drive;
	public Elevator elevator;
	public Climber climber;
	private Supersystem(){
		
		controls = new Controls(Constants.Ports.Controls.Driver_Joystick_1,
								Constants.Ports.Controls.Driver_Joystick_2,
								Constants.Ports.Controls.Driver_Joystick_OperatorGamepad);
		
		drive = new Drivetrain(Constants.Ports.Drivetrain.Left_1_ID,
							   Constants.Ports.Drivetrain.Left_2_ID,
							   Constants.Ports.Drivetrain.Right_1_ID,
							   Constants.Ports.Drivetrain.Right_2_ID);
		
		elevator = new Elevator(Constants.Ports.Elevator.Elevator_1_ID,
						        Constants.Ports.Elevator.Elevator_2_ID,
						        Constants.Ports.Elevator.Wheels_1_ID,
						        Constants.Ports.Elevator.Wheels_2_ID);
		
		climber = new Climber(Constants.Ports.Climber.Pully_ID,
							  Constants.Ports.Climber.Winch_1_ID,
							  Constants.Ports.Climber.Winch_2_ID);
		
		
	}
	
	public static Supersystem getInstance(){
		if(instance == null){
			instance = new Supersystem();
		}
		return instance;
		
	}
	
	public void runTeleop() {
		drive.teleopOpenLoop(controls.LeftThrottle(), controls.RightThrottle());
//		elevator.teleopLoop();
		climber.teleopLoop(controls.GamepadLeftJoystick(), controls.TopLeftTrigger(), controls.TopRightTrigger());
//		System.out.println(elevator.getEncoderPulses());
	}
	
	public void resetSensors(){
		drive.resetSensors();
		elevator.resetSensors();
	}
}

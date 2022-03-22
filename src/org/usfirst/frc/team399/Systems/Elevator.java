package org.usfirst.frc.team399.Systems;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.MultiToggler;
import org.usfirst.frc.team399.Utilities.Toggler;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

public class Elevator {
	TalonSRX elevator_1, elevator_2,wheels_1,wheels_2;
	Toggler toggler = new Toggler();
	MultiToggler multiToggler = new MultiToggler();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Elevator.Max_Velocity,
															Constants.Subsystems.Elevator.Max_Acceleration,
															Constants.Subsystems.Elevator.Switch_Position,
															Constants.Subsystems.Elevator.Scale_Position);
	double[][][] profiles = generator.getTrajectoryTransitions();
	MPController elevatorController = new MPController(profiles);
	boolean hasExtended = false;
	double wheelsOutput;
	double elevatorOutput;
	Encoder elevatorEncoder = new Encoder(0,1);

	
	
	public Elevator(int tal_1, int tal_2,int tal_3, int tal_4){
		elevator_1 = new TalonSRX(tal_1);
		elevator_2 = new TalonSRX(tal_2);
		wheels_1 = new TalonSRX(tal_3);
		wheels_2 = new TalonSRX(tal_4);
		elevatorEncoder.setDistancePerPulse(1);
		elevatorEncoder.setReverseDirection(true);
		
		elevator_1.enableVoltageCompensation(true);
		elevator_2.enableVoltageCompensation(true);
		wheels_1.enableVoltageCompensation(true);
		wheels_2.enableVoltageCompensation(true);
		
		elevator_1.configVoltageCompSaturation(12, 10);
		elevator_2.configVoltageCompSaturation(12, 10);
		wheels_1.configVoltageCompSaturation(12, 10);
		wheels_2.configVoltageCompSaturation(12, 10);
		
		//Config current management settings later
//		elevator_1.enableCurrentLimit(true);
//		elevator_1.configContinuousCurrentLimit(amps, timeoutMs);
//		elevator_1.configPeakCurrentLimit(amps, timeoutMs);
//		elevator_1.configPeakCurrentDuration(milliseconds, timeoutMs);
//		
//		elevator_2.enableCurrentLimit(true);
//		elevator_2.configContinuousCurrentLimit(amps, timeoutMs);
//		elevator_2.configPeakCurrentLimit(amps, timeoutMs);
//		elevator_2.configPeakCurrentDuration(milliseconds, timeoutMs);
//		
//		wheels_1.enableCurrentLimit(true);
//		wheels_1.configContinuousCurrentLimit(amps, timeoutMs);
//		wheels_1.configPeakCurrentLimit(amps, timeoutMs);
//		wheels_1.configPeakCurrentDuration(milliseconds, timeoutMs);
//		
//		wheels_2.enableCurrentLimit(true);
//		wheels_2.configContinuousCurrentLimit(amps, timeoutMs);
//		wheels_2.configPeakCurrentLimit(amps, timeoutMs);
//		wheels_2.configPeakCurrentDuration(milliseconds, timeoutMs);
	}
	
	
	
	public void teleopOpenLoop(double pow_1, double pow_2, double pow_3, double pow_4){
		//Will determine if we need to invert the output on one of the motors
//		elevator_1.setInverted(true);
		elevator_2.setInverted(true);
		wheels_1.setInverted(true);
//		wheels_2.setInverted(true);
		
		elevator_1.set(ControlMode.PercentOutput, pow_1);
		elevator_2.set(ControlMode.PercentOutput, pow_2);
		wheels_1.set(ControlMode.PercentOutput, pow_3);
		wheels_2.set(ControlMode.PercentOutput, pow_4);
		
	}
	
	public void teleopLoop(){
		wheels_1.setInverted(true);	
		wheelsOutput = (Supersystem.getInstance().controls.DPad() == 180 ? -1.0 : Supersystem.getInstance().controls.DPad() == 0 ? 1.0 : 0.0);
		wheels_1.set(ControlMode.PercentOutput, wheelsOutput);
		wheels_2.set(ControlMode.PercentOutput, wheelsOutput);
		if(toggler.set(Supersystem.getInstance().controls.X())){
			elevator_1.setInverted(false);
			elevator_2.setInverted(true);
			System.out.println("Mode: Manual Control || Output: " + Supersystem.getInstance().controls.GamepadRightJoystick());
			elevatorOutput = (Supersystem.getInstance().controls.GamepadRightJoystick() > -Constants.Subsystems.Elevator.k_Hold_Output && 
					  Supersystem.getInstance().controls.GamepadRightJoystick() < 0 ? -Constants.Subsystems.Elevator.k_Hold_Output :
					  Supersystem.getInstance().controls.GamepadRightJoystick());
			elevator_1.set(ControlMode.PercentOutput, elevatorOutput);
			elevator_2.set(ControlMode.PercentOutput, elevatorOutput);
			
		}else{
			elevator_1.setInverted(true);
			elevator_2.setInverted(false);
			System.out.println("Mode: Motion Profile Control || Output: " + elevatorOutput);
			multiToggler.loop();
			elevatorOutput = elevatorController.getSubsystemOutput(multiToggler.getFirstBool(),
																   multiToggler.getSecondBool(),
																   multiToggler.getThirdBool(),
												  				   Constants.Subsystems.Elevator.k_Proportional,
												  				   Constants.Subsystems.Elevator.k_Integral,
												  				   Constants.Subsystems.Elevator.k_Derivative,
												  				   Constants.Subsystems.Elevator.k_Velocity,
												  				   Constants.Subsystems.Elevator.k_Acceleration,
												  				   getDisplacement(),
												  				   0);
			
			elevator_1.set(ControlMode.PercentOutput, elevatorOutput);
			elevator_2.set(ControlMode.PercentOutput, elevatorOutput);
		}
		
	}
	
	public void elevatorAutonLoop(char transition, double currentTime){
//		elevator_1.setInverted(true);
//		elevator_2.setInverted(true);
		
		elevatorOutput = (transition == '0' ? elevatorController.getSubsystemOutput(false,
																   					false,
																   					true,
																   					Constants.Subsystems.Elevator.k_Proportional,
																   					Constants.Subsystems.Elevator.k_Integral,
																   					Constants.Subsystems.Elevator.k_Derivative,
																   					Constants.Subsystems.Elevator.k_Velocity,
																   					Constants.Subsystems.Elevator.k_Acceleration,
																   					getDisplacement(),
																   					currentTime) :
						  transition == '1' ?  elevatorController.getSubsystemOutput(false,
				   																	 true,
				   																	 false,
				   																	 Constants.Subsystems.Elevator.k_Proportional,
				   																	 Constants.Subsystems.Elevator.k_Integral,
				   																	 Constants.Subsystems.Elevator.k_Derivative,
				   																	 Constants.Subsystems.Elevator.k_Velocity,
				   																	 Constants.Subsystems.Elevator.k_Acceleration,
				   																	 getDisplacement(),
				   																	currentTime): 
				   		  transition == '2' ?  elevatorController.getSubsystemOutput(true,
							   														 false,
							   														 false,
							   														 Constants.Subsystems.Elevator.k_Proportional,
							   														 Constants.Subsystems.Elevator.k_Integral,
							   														 Constants.Subsystems.Elevator.k_Derivative,
							   														 Constants.Subsystems.Elevator.k_Velocity,
							   														 Constants.Subsystems.Elevator.k_Acceleration,
							   														 getDisplacement(),
							   														 currentTime) : 0);
		elevator_1.set(ControlMode.PercentOutput, elevatorOutput);
		elevator_2.set(ControlMode.PercentOutput, elevatorOutput);
		
	}
	
	public void wheelsAutonLoop(char runWheelsInThisDirection){
//		wheels_1.setInverted(true);
//		wheels_2.setInverted(true);
		wheels_1.set(ControlMode.PercentOutput, (runWheelsInThisDirection  == 'I' ? -1.0 : runWheelsInThisDirection == 'O' ?  1 : 0.0));
		wheels_1.set(ControlMode.PercentOutput, (runWheelsInThisDirection  == 'I' ? -1.0 : runWheelsInThisDirection == 'O' ?  1 : 0.0));
	}
	
	
	
	//Reset sensors
	public void resetSensors(){
		elevatorEncoder.reset();
	}
	
	public double getEncoderPulses(){
		return elevatorEncoder.getDistance();
	}
	
	public double getPosition(){
		return getEncoderPulses() * Constants.Subsystems.Elevator.Distance_Per_Pulse;
	}
	
	public double getVelocity(double time){
		return getPosition()/time;
	}
	
	public double getDisplacement(){
		//Change this to sensor value times some real world unit per sensor value
		double displacement = 0.0;
		return displacement;
	}
	
	public void autonRunMode(double pow_1, double pow_2,double pow_3, double pow_4){
		//Will determine if we need to invert the output on one of the motors
//		elevator_1.setInverted(true);
//		elevator_2.setInverted(true);
//		wheels_1.setInverted(true);
//		wheels_2.setInverted(true);
		
		elevator_1.set(ControlMode.PercentOutput, pow_1);
		elevator_2.set(ControlMode.PercentOutput, pow_2);
		wheels_1.set(ControlMode.PercentOutput, pow_3);
		wheels_2.set(ControlMode.PercentOutput, pow_4);
	}
}

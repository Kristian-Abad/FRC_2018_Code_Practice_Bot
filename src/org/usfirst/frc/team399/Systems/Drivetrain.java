package org.usfirst.frc.team399.Systems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.Toggler;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;


import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.ctre.phoenix.motion.MotionProfileStatus;

public class Drivetrain {
	double lastTime, lastAngle;
	boolean loop = false;
	boolean createController = true;
	
	double time;
	MPController mpcontroller;
	Timer timer = new Timer();
	public TalonSRX left_1;
	public TalonSRX left_2;
	public TalonSRX right_1;
	public TalonSRX right_2;
	
	public AHRS navx = new AHRS(SPI.Port.kMXP);

	
	

	
	public Drivetrain(int l1, int l2, int r1, int r2){
		left_1 = new TalonSRX(l1);
		left_2 = new TalonSRX(l2);
		right_1 = new TalonSRX(r1);
		right_2 = new TalonSRX(r2);
		
		left_1.setNeutralMode(NeutralMode.Brake);
		left_2.setNeutralMode(NeutralMode.Brake);
		right_1.setNeutralMode(NeutralMode.Brake);
		right_2.setNeutralMode(NeutralMode.Brake);
		
		
		left_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		right_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		left_2.setSensorPhase(false);
		right_2.setSensorPhase(false);
		
		left_1.enableVoltageCompensation(true);
		left_2.enableVoltageCompensation(true);
		right_1.enableVoltageCompensation(true);
		right_2.enableVoltageCompensation(true);
		
		left_1.configVoltageCompSaturation(12, 10);
		left_2.configVoltageCompSaturation(12, 10);
		right_1.configVoltageCompSaturation(12, 10);
		right_2.configVoltageCompSaturation(12, 10);
		
//		right_1.setSelectedSensorPosition(0, 0, 0);
//		left_2.setSelectedSensorPosition(0, 0, 0);
		
//		left_1.enableCurrentLimit(true);
//	    left_2.enableCurrentLimit(true);
//	    right_1.enableCurrentLimit(true);
//	    right_2.enableCurrentLimit(true);
//	    
//	    left_1.configPeakCurrentLimit(18, 10);
//	    left_2.configPeakCurrentLimit(18, 10);
//	    right_1.configPeakCurrentLimit(18, 10);
//	    left_1.configPeakCurrentLimit(18, 10);
//	    
//	    left_1.configPeakCurrentDuration(10, 10);
//	    left_2.configPeakCurrentDuration(10, 10);
//	    right_1.configPeakCurrentDuration(10, 10);
//	    right_2.configPeakCurrentDuration(10, 10);
//	    
//	    left_1.configContinuousCurrentLimit(18, 10);
//	    left_2.configContinuousCurrentLimit(18, 10);
//	    right_1.configContinuousCurrentLimit(18, 10);
//	    right_2.configContinuousCurrentLimit(18, 10);
	}
	
	public double getAngle(){
		return navx.getAngle();
	}
	
	public void runLeft1(double pow){
		left_1.set(ControlMode.PercentOutput, pow);
	}
	
	public void runLeft2(double pow){
		left_2.set(ControlMode.PercentOutput, pow);
	}
	
	public void runRight1(double pow){
		right_1.set(ControlMode.PercentOutput, pow);
	}
	
	public void runRight2(double pow){
		right_2.set(ControlMode.PercentOutput, pow);
	}
	
	public TalonSRX getLeft1() {
		return left_1;
	}
	
	public TalonSRX getLeft2() {
		return left_2;
	}
	
	public TalonSRX getRight1() {
		return right_1;
	}
	
	public TalonSRX getRight2() {
		return right_2;
	}
	
	public void runMotionProfile(double[][] motionProfile, int desiredAngle, boolean degreeMode, double currentTime){
		time = currentTime;
		if (createController){
			mpcontroller = new MPController(motionProfile,desiredAngle,degreeMode);
			createController = false;
		}
		double[] outputs = mpcontroller.getDrivetrainOutputs(currentTime);
		double leftOutput = outputs[0];
		double rightOutput = outputs[1];
		System.out.println(leftOutput + "\t\t\t" + rightOutput);
		autonRunMode(leftOutput,rightOutput);
	}
	
	public void autonRunMode(double lpow, double rpow){
		
		right_1.setInverted(true);
		right_2.setInverted(true);
		
		
		left_1.set(ControlMode.PercentOutput, lpow);
		left_2.set(ControlMode.PercentOutput,lpow);
		right_1.set(ControlMode.PercentOutput,rpow);
		right_2.set(ControlMode.PercentOutput,rpow);
	}
	
	public void teleopOpenLoop(double leftpow, double rightpow){
		left_1.setInverted(true);
		left_2.setInverted(true);

		left_1.setNeutralMode(NeutralMode.Brake);
		left_2.setNeutralMode(NeutralMode.Brake);
		right_1.setNeutralMode(NeutralMode.Brake);
		right_2.setNeutralMode(NeutralMode.Brake);
		
		left_1.set(ControlMode.PercentOutput, leftpow);
		left_2.set(ControlMode.PercentOutput,leftpow);
		right_1.set(ControlMode.PercentOutput,rightpow);
		right_2.set(ControlMode.PercentOutput,rightpow);
		
	    
	
	}
	
	public void tuneLoop(double[][] motionProfile, int desiredAngle, boolean degreeMode, double currentTime){
		time = currentTime;
		if (createController){
			mpcontroller = new MPController(motionProfile,desiredAngle,degreeMode);
			createController = false;
		}
		double[] outputs = mpcontroller.tuneDrive(currentTime);
		double leftOutput = outputs[0];
		double rightOutput = outputs[1];
		
		autonRunMode(leftOutput,rightOutput);
	}
	
	public void runMotorsInAmps(double amp1,double amp2){
		left_1.set(ControlMode.Current, amp1);
		left_2.set(ControlMode.Current, amp1);
		right_1.set(ControlMode.Current, amp2);
		right_2.set(ControlMode.Current, amp2);
	}
	
	public void printMotorOutputCurrent(){
		System.out.printf("%-10f%-10f%-10f%-10f\n",left_1.getOutputCurrent(),
											   	   left_2.getOutputCurrent(),
											       right_1.getOutputCurrent(),
											       right_2.getOutputCurrent());
	}
	
	public void printMotorOutputVoltage(){		
		System.out.printf("%-10f%-10f%-10f%-10f", left_1.getMotorOutputVoltage(),
												  left_2.getMotorOutputVoltage(),
												  right_1.getMotorOutputVoltage(),
												  right_2.getMotorOutputVoltage());
	}
	
	
	public void printPosition(){
		System.out.println(getLeftPosition() + "           " + getRightPosition());
	}
	
	public void printVelocity(){
		System.out.println("Left: " + getLeftVelocity() + " || Right: " + getRightVelocity() + " || Time: " + time);
	}
	
	public double getLeftRawUnits(){
		return left_2.getSelectedSensorPosition(0);
		
	}
	
	public double getRightRawUnits(){
		return right_2.getSelectedSensorPosition(0);
	}
	
	public double getLeftRotations(){
		return left_2.getSelectedSensorPosition(0)/4096;
	}
	
	public double getRightRotations(){
		return right_2.getSelectedSensorPosition(0)/4096;
	}
	
	public double getLeftPosition(){
		double left_in = getLeftRotations() * Constants.Wheel_Constants.Circumference_of_Wheel;
		return left_in;
	}
	
	public double getRightPosition(){
		double right_in = getRightRotations() * Constants.Wheel_Constants.Circumference_of_Wheel;
		return right_in;
	}
	
	public double getAverageDisplacement(){
		double averageDisplacement = (getLeftPosition() + getRightPosition()) * 0.5;
		return averageDisplacement;
	}
	
	public double getLeftVelocity(){
		double leftVelocity = getLeftPosition()/time;
		return leftVelocity;
	}
	
	public double getRightVelocity(){
		double rightVelocity = getRightPosition()/time;
		return rightVelocity;
	}
	
	public void setTime(double current){
		time = current;
	}
	
	public void resetSensors(){
		left_2.setSelectedSensorPosition(0, 0, 0);
		right_2.setSelectedSensorPosition(0, 0, 0);
		navx.reset();
	}
	

	
}


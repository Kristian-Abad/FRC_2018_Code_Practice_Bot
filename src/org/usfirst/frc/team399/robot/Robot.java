package org.usfirst.frc.team399.robot;

import org.usfirst.frc.team399.Auton.Baseline;
import org.usfirst.frc.team399.Auton.DoNothing;
import org.usfirst.frc.team399.Auton.LinearScaleLeft;
import org.usfirst.frc.team399.Auton.LinearScaleRight;
import org.usfirst.frc.team399.Auton.Switch;
import org.usfirst.frc.team399.Auton.TestPrint1;
import org.usfirst.frc.team399.Auton.TestPrint2;
import org.usfirst.frc.team399.Commands.TestPrint;
import org.usfirst.frc.team399.Commands.TestPrintSecondary;
import org.usfirst.frc.team399.Systems.Drivetrain;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.ButtonPositionChooser;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.MultiToggler;
import org.usfirst.frc.team399.Utilities.PIDController;
import org.usfirst.frc.team399.Utilities.Toggler;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;


public class Robot extends IterativeRobot {
	Supersystem PracticeBot = Supersystem.getInstance();
	ButtonPositionChooser positionChooser = new ButtonPositionChooser();
	Timer timer = new Timer();
	char pos, gameDataChar;	
	Command autoSelected;
	SendableChooser desiredChooser = new SendableChooser();
	SendableChooser defaultChooser = new SendableChooser();
	String gameData;
	boolean runTimer = true;
	boolean buttonPressed = false;
	double time;

	@Override
	public void robotInit() {
		
		timer.stop();
		
//		desiredChooser.addObject("Test", new Test());
//		desiredChooser.addObject("Test Print 1", new TestPrint1());
		desiredChooser.addObject("Baseline", new Baseline());
		desiredChooser.addObject("Switch", new Switch());
		desiredChooser.addObject("Linear Scale Left", new LinearScaleLeft());
		desiredChooser.addObject("Linear Scale Right", new LinearScaleRight());
		
//		defaultChooser.addObject("Test Print 2", new TestPrint2());
		defaultChooser.addDefault("Do Nothing", new DoNothing());
		defaultChooser.addObject("Baseline", new Baseline());
				
		SmartDashboard.putData("Auto choices", desiredChooser);
		SmartDashboard.putData("Stand-in", defaultChooser);
	}

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		gameDataChar = (pos == 'L' || pos == 'R'? gameData.charAt(0) : gameData.charAt(1));
		autoSelected = (gameDataChar == pos ? (Command) desiredChooser.getSelected() : (Command) defaultChooser.getSelected());
		
		if(autoSelected != null){
			autoSelected.start();
			System.out.println("Auton based on gamedata:" + autoSelected);
		}
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void disabledPeriodic() {
		timer.stop();
		pos = positionChooser.getPosition();
//		System.out.println("Field Position: " + pos);
	}
	
	public void teleopInit(){
		//Remember to add reset method here
		PracticeBot.resetSensors();
	}
	
	@Override
	public void teleopPeriodic() {
		PracticeBot.runTeleop();
		if(runTimer){
			timer.start();
			runTimer = false;
		}
		time = timer.get();
		PracticeBot.drive.setTime(time);
		PracticeBot.drive.printVelocity();

	}
		
	

	public void testInit(){
		
		
	}
	@Override
	public void testPeriodic() {
		
		
	}
}


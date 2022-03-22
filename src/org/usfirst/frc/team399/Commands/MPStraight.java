package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MPStraight extends Command{
	Timer timer = new Timer();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity,
			Constants.Subsystems.Drivetrain.Max_Acceleration);
//	MPController controller;
	
	private double timeout;
	double[][] profile;
	double leftOutput, rightOutput;
	boolean runTimer = true;
	
	public MPStraight(double disp, boolean invert, double time){
		timeout = time;
		profile = generator.generateTrajectory(disp, invert);
//		controller = new MPController(profile,0,false);
	}
	@Override
	protected void initialize() {
		timer.stop();
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(runTimer){
			timer.start();
			runTimer = false;
		}
		double time = timer.get();
		Supersystem.getInstance().drive.runMotionProfile(profile, 0, false, time);
//		double[] outputs = controller.getDrivetrainOutputs(time);
//		leftOutput = outputs[0];
//		rightOutput = outputs[1];
//		
//		Supersystem.getInstance().drive.autonRunMode(leftOutput, rightOutput);
		
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		// TODO Auto-generated method stub
		
	}
}

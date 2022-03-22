package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorWheels extends Command{
	Timer timer = new Timer();
	boolean runTimer = true;
	double timeout,time;
	char wheelsDirection;
	
	public RunElevatorWheels(char direction,double time){
		wheelsDirection = direction;
		timeout = time;
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
		time = timer.get();
		
		Supersystem.getInstance().elevator.wheelsAutonLoop(wheelsDirection);
		
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Supersystem.getInstance().elevator.wheelsAutonLoop('N');
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Supersystem.getInstance().elevator.wheelsAutonLoop('N');
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().elevator.wheelsAutonLoop('N');
		// TODO Auto-generated method stub
		
	}
}

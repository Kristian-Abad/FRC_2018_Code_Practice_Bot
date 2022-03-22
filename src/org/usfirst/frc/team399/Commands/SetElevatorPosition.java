package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorPosition extends Command{
	Timer timer = new Timer();
	boolean runTimer = true;
	double timeout,time;
	char position;
	
	public SetElevatorPosition(char pos,double time){
		position = pos;
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
		
		Supersystem.getInstance().elevator.elevatorAutonLoop(position, time);
		
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Supersystem.getInstance().elevator.elevatorAutonLoop('3', 0);
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Supersystem.getInstance().elevator.elevatorAutonLoop('3', 0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().elevator.elevatorAutonLoop('3', 0);
		// TODO Auto-generated method stub
		
	}

}

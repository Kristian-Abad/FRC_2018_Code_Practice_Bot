package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MPStraight;
import org.usfirst.frc.team399.Commands.MPTurn;
import org.usfirst.frc.team399.Commands.RunElevatorWheels;
import org.usfirst.frc.team399.Commands.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LinearScaleLeft extends CommandGroup{
	public LinearScaleLeft(){
		//Make this a parallel later to bring the elevator to max extension
		this.addParallel(new SetElevatorPosition('1', 1.0));
		this.addSequential(new MPStraight(50.781, false, 0.0));
		this.addSequential(new MPTurn(90,0.0));
		this.addSequential(new RunElevatorWheels('O', 1.0));
	}
}

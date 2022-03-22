package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MPStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Baseline extends CommandGroup{
	public Baseline(){
		this.addSequential(new MPStraight(120, false, 3.124804));
	}
}

package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.TestPrint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPrint1 extends CommandGroup{
	public TestPrint1(){
		this.addSequential(new TestPrint(5.0));
	}
}

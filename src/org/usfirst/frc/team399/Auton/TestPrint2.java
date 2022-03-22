package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.TestPrintSecondary;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPrint2 extends CommandGroup{
	public TestPrint2(){
		this.addSequential(new TestPrintSecondary(5.0));
	}
}

package org.usfirst.frc.team399.Commands;



import edu.wpi.first.wpilibj.command.Command;

public class TestPrintSecondary extends Command{
	private double timeout;
	
	public TestPrintSecondary(double time){
		timeout = time;
	}
	
	@Override
	protected void initialize() {
		
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		System.out.println("This is test print two");
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		
		// TODO Auto-generated method stub
		
	}

}

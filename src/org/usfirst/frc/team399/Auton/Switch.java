package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MPStraight;
import org.usfirst.frc.team399.Commands.MPTurn;
import org.usfirst.frc.team399.Commands.RunElevatorWheels;
import org.usfirst.frc.team399.Commands.SetElevatorPosition;

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Switch extends CommandGroup{
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	char gameDataChar = gameData.charAt(1);
	public Switch(){
		/**Once we get out max velocity and max acceleration get times for timeout.
		 * We also need to make some of the sequentials parallels
		 * 
		 * **/
		if(gameDataChar == 'L'){
			this.addParallel(new SetElevatorPosition('1', 1.0));
			this.addSequential(new MPStraight(50.781, false, 0.0));
			this.addSequential(new MPTurn(-90,0.0));
			this.addSequential(new MPStraight(59.895, false, 0.0));
			this.addSequential(new MPTurn(90,0.0));
			this.addSequential(new MPStraight(51.838,false, 0.0));
			this.addSequential(new RunElevatorWheels('O', 1.0));
		}else {
			this.addParallel(new SetElevatorPosition('1', 1.0));
			this.addSequential(new MPStraight(50.781, false, 0.0));
			this.addSequential(new MPTurn(90,0.0));
			this.addSequential(new MPStraight(48.476, false, 0.0));
			this.addSequential(new MPTurn(-90,0.0));
			this.addSequential(new MPStraight(48.865, false, 0.0));
			this.addSequential(new RunElevatorWheels('O', 1.0));
		}
	}
}

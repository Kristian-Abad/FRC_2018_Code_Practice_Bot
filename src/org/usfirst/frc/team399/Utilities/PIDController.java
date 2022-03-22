package org.usfirst.frc.team399.Utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDController {
	double p,i,d;
	double prop,inte,der=0;
	double targ,curr,errorMem;
	double out=0;
	long deltaTime,timeMem=0;
	int mult=1;
	

	public PIDController(double p,double i,double d){
		this.setPID(p,i,d);
	}
	
	public void setPID(double p,double i,double d){
		this.p=p;
		this.i=i;
		this.d=d;
//		SmartDashboard.putNumber("P", p);
//		SmartDashboard.putNumber("I", i);
//		SmartDashboard.putNumber("D", d);
	}
	
	public double calculate(double current, double setpoint){
		double error = setpoint - current;
		long time = System.currentTimeMillis();
		if(timeMem!=0){
			deltaTime = time-timeMem;
		}else{
			deltaTime = 1;
		}
		prop=p*error;
		inte=i*(inte + (error*deltaTime));
		der=d*((error - errorMem)/deltaTime);
		out=prop+inte+der; //+curr;
//		if(out > 1) out = 1;
//		else if(out < -1) out = -1;
//		SmartDashboard.putNumber("OUT", out);
		timeMem=time;
	
		return out;

	}
}

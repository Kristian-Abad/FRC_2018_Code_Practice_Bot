package org.usfirst.frc.team399.Utilities;

import org.usfirst.frc.team399.Systems.Supersystem;

public class MultiToggler {
	Toggler toggle1 = new Toggler();
	Toggler toggle2 = new Toggler();
	Toggler toggle3 = new Toggler();
	
	boolean t1 = false;
	boolean t2 = false;
	boolean t3 = false;
	
	boolean buttonPressed;
	
	public MultiToggler(){
		
	}
	
	
	public void loop(){		
		if(Supersystem.getInstance().controls.Y()){
			t1 = true;
			t2 = false;
			t3 = false;
		}else if(Supersystem.getInstance().controls.B()){
			t2 = true;
			t1 = false;
			t3 = false;
		}else if(Supersystem.getInstance().controls.A()){
			t3 = true;
			t1 = false;
			t2 = false;
		}
		
		
	}
	
	public boolean getFirstBool(){
		return t1;
	}
	
	public boolean getSecondBool(){
		return t2;
	}
	
	public boolean getThirdBool(){
		return t3;
	}
	
}

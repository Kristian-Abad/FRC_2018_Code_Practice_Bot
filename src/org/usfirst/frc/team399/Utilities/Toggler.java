package org.usfirst.frc.team399.Utilities;

public class Toggler {
boolean other, isBeingToggled = false;
	
	public boolean set(boolean in){
		if(in){
			isBeingToggled = !isBeingToggled;
		}
		
		if(isBeingToggled){
			other = true;
		}else{
			other = false;
		}
//		System.out.println(other);
		return other;
	}
	
	
	}

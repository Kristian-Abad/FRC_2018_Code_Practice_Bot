package org.usfirst.frc.team399.Utilities;

import org.usfirst.frc.team399.Systems.Supersystem;

public class ButtonPositionChooser {
	boolean buttonPressed;
	char pos;
	
	public ButtonPositionChooser(){
		
	}
	
	public char getPosition(){
		if(Supersystem.getInstance().controls.X() || 
		   Supersystem.getInstance().controls.Y() || 
		   Supersystem.getInstance().controls.B()){
			buttonPressed = true;
		}
				
		if(Supersystem.getInstance().controls.X() && buttonPressed){
			pos = 'L';
		}else if(Supersystem.getInstance().controls.Y() && buttonPressed){
			pos = 'M';
		}else if(Supersystem.getInstance().controls.B() && buttonPressed){
			pos = 'R';
		}
		return pos;
	}
}

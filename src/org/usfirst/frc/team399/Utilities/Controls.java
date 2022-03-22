package org.usfirst.frc.team399.Utilities;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {
	Joystick DriverLeft = null;
	Joystick DriverRight = null;
	Joystick OperatorGamepad = null;
	
	public Controls(int left, int right, int gamepad) {
		DriverLeft = new Joystick(left);
		DriverRight = new Joystick(right);
		OperatorGamepad = new Joystick(gamepad);
	}
	public double LeftThrottle() {
		return DriverLeft.getRawAxis(1);
	}
	public double RightThrottle() {
		return DriverRight.getRawAxis(1);
	}
	public double GamepadRightJoystick() {
		return OperatorGamepad.getRawAxis(3);
	}
	public double GamepadLeftJoystick() {
		return OperatorGamepad.getRawAxis(1);
	}
	
	public boolean GamepadLeftJoystickButton(){
		return OperatorGamepad.getRawButton(11);
	}
	
	public boolean GamepadRightJoystickButton(){
		return OperatorGamepad.getRawButton(12);
	}
	
	public boolean X() {
		return OperatorGamepad.getRawButton(1);
	}
	public boolean Y() {
		return OperatorGamepad.getRawButton(4);
	}
	public boolean A() {
		return OperatorGamepad.getRawButton(2);
	}
	public boolean B() {
		return OperatorGamepad.getRawButton(3);
	}
	
	public double DPad() {
		return OperatorGamepad.getPOV();
	}
	//Logitech
	public boolean Button1() {
		return OperatorGamepad.getRawButton(1);
	}
	public boolean Button2() {
		return OperatorGamepad.getRawButton(2);
	}
	public boolean Button3() {
		return OperatorGamepad.getRawButton(3);
	}
	public boolean Button4() {
		return OperatorGamepad.getRawButton(4);
	}
	
	public boolean TopLeftTrigger() {
		return OperatorGamepad.getRawButton(5);
	}
	public boolean BottomLeftTrigger() {
		return OperatorGamepad.getRawButton(7);
	}
	public boolean TopRightTrigger() {
		return OperatorGamepad.getRawButton(6);
	}
	public boolean BottonRightTrigger() {
		return OperatorGamepad.getRawButton(8);
	}
	
}

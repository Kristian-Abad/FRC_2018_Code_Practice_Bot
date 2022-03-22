package org.usfirst.frc.team399.Config;

public class Constants {
	public class Wheel_Constants{
		public static final double Circumference_of_Wheel = 37.699111843077518861551720599354;
		public static final int distance_Between_Wheels = 28;
		
	}
	
	public class Subsystems{
		public class Elevator{
			public static final double Circumference_Of_Drum = 2 * Math.PI * 1.25;
			public static final double Switch_Position = 18.375;
			public static final double Scale_Position = 80.5;
			
			public static final double Max_Velocity = 45.00995802303778;
			public static final double Max_Acceleration = 45.00995802303778/1.7575290000000003;
			public static final double Distance_Per_Pulse = Circumference_Of_Drum/485.25;
			
			public static final double k_Velocity = 0.02221730576794056545277056690485;
			public static final double k_Acceleration = 0.03904755918902282072483413206388;
			public static final double k_Proportional = 0.0;
			public static final double k_Integral = 0.0;
			public static final double k_Derivative = 0.0;
			
			public static final double k_Hold_Output = 0.09;
		}
		
		public class Drivetrain{
			public static final double Max_Velocity = 160;
			public static final double Max_Acceleration = 136.13301571733322/2.769285;
			public static final double k_Velocity = 1/Max_Velocity;
			public static final double k_Acceleration = 0.0;
			public static final double k_Tangential_Velocity = 0.0083;
			public static final double k_Perpendicular_Acceleration = 0.001;
			public static final int Raw_Units_Per_Revolution = 4096;
			
		}
	}
	
	
	public class MotionProfile{
		public static final int k_Timeout = 10;
		public static final int Motion_Profile_Update_Rate = 25;
		public static final int Minimum_Num_Of_Points = 5;
		
		
	}
	
	public class Ports{

		public class Controls{
			public static final int Driver_Joystick_1 = 0;
			public static final int Driver_Joystick_2 = 1;
			public static final int Driver_Joystick_OperatorGamepad = 2;
		}
		
		public class Drivetrain{
			public static final int Left_1_ID = 7;
			public static final int Left_2_ID = 8;
			public static final int Right_1_ID = 1;
			public static final int Right_2_ID = 2;
			
		}
		
		public class Elevator{
			public static final int Elevator_1_ID = 3;
			public static final int Elevator_2_ID = 4;
			public static final int Wheels_1_ID = 11;
			public static final int Wheels_2_ID = 6;
		}
		
		public class Climber{
			public static final int Pully_ID = 5;
			public static final int Winch_1_ID = 9;
			public static final int Winch_2_ID = 10;
		}
	}
}

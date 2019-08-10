/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {

	private WPI_VictorSPX leftFront;
	private WPI_VictorSPX leftBack;
	private WPI_VictorSPX leftTop;
	private WPI_VictorSPX rightFront;
	private WPI_VictorSPX rightBack;
	private WPI_VictorSPX rightTop; 
	
	public WPI_VictorSPX liftMotor;
	
	public Encoder liftEncoder;

	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;

	public SpeedControllerGroup leftDrive;
	public SpeedControllerGroup rightDrive;
	
	public Solenoid shifter;

	public RobotMap() {
		leftFront = new WPI_VictorSPX(1);
  		leftBack = new WPI_VictorSPX(2);
  		rightFront = new WPI_VictorSPX(3);
  		rightBack = new WPI_VictorSPX(4);
		leftTop = new WPI_VictorSPX(5);
		rightTop = new WPI_VictorSPX(6);

		liftMotor = new WPI_VictorSPX(11);
		
		liftEncoder = new Encoder(10, 12, false, CounterBase.EncodingType.k4X);

		leftDriveEncoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X); 
  		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
  
  		rightDriveEncoder = new Encoder(4, 5, true, CounterBase.EncodingType.k4X); 
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		  
		leftDriveEncoder.setDistancePerPulse((2.0 * (Math.PI) * (2.0 / 12)) / 2048.0); 
		rightDriveEncoder.setDistancePerPulse((2.0 * (Math.PI) * (2.0 / 12)) / 2048.0);

		leftDrive = new SpeedControllerGroup(leftFront , leftBack, leftTop); 
		rightDrive = new SpeedControllerGroup(rightFront , rightBack, rightTop); 

		shifter = new Solenoid(0xdeadbeef);
	}
}

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

public class RobotMap {

	public WPI_VictorSPX liftMotor; // I see you declare lift stuff before drive here - that's okay, the order is your preference, just keep it consistent
	public Encoder liftEncoder;

	// may also be a good idea to use comments to seperate/label the different subsystems

	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;

	public SpeedControllerGroup leftDrive;
	public SpeedControllerGroup rightDrive;

	private WPI_VictorSPX leftFront;
	private WPI_VictorSPX leftBack;
	private WPI_VictorSPX leftTop;
	
	private WPI_VictorSPX rightFront;
	private WPI_VictorSPX rightBack;
	private WPI_VictorSPX rightTop; 
   
	public RobotMap() {
		leftFront = new WPI_VictorSPX(1);
  		leftBack = new WPI_VictorSPX(2);
  		rightFront = new WPI_VictorSPX(3);
  		rightBack = new WPI_VictorSPX(4);
		leftTop = new WPI_VictorSPX(5);
		rightTop = new WPI_VictorSPX(6);

		liftMotor = new WPI_VictorSPX(11);
		liftEncoder = new Encoder(10, 12, false, CounterBase.EncodingType.k4X);

		leftDrive = new SpeedControllerGroup(leftFront , leftBack, leftTop); 
		rightDrive = new SpeedControllerGroup(rightFront , rightBack, rightTop); 

		leftDriveEncoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X); 
  		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
  
  		rightDriveEncoder = new Encoder(4, 5, true, CounterBase.EncodingType.k4X); 
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		  
		leftDriveEncoder.setDistancePerPulse((2.0 * (Math.PI) * (2.0 / 12)) / 2048.0); 
		rightDriveEncoder.setDistancePerPulse((2.0 * (Math.PI) * (2.0 / 12)) / 2048.0);
	}
}

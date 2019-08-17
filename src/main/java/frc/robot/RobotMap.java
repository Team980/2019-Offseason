/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.Potentiometer;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {

	// drive stuff
	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;

	public SpeedControllerGroup leftDrive;
	public SpeedControllerGroup rightDrive;

	public Solenoid shifter;

	// end effector
	public WPI_TalonSRX endEffectorIntakeMotor;

	// lift
	public WPI_TalonSRX liftMotor;
	public Encoder liftEncoder;

	// wrist
	public Potentiometer wristPotentiometer;
	public WPI_TalonSRX wristMotor;

	// sensors
	PigeonIMU imu;
	public Limelight limelight;

	RobotMap() {
		// drive stuff
		var leftFront = new WPI_TalonSRX(1);
		var leftBack = new WPI_TalonSRX(2);
		var leftTop = new WPI_TalonSRX(0);
		leftTop.setInverted(true);

  		var rightFront = new WPI_TalonSRX(4);
		var rightBack = new WPI_TalonSRX(5);
		var rightTop = new WPI_TalonSRX(3);
		rightTop.setInverted(true);

		leftDrive = new SpeedControllerGroup(leftFront, leftBack, leftTop);
		rightDrive = new SpeedControllerGroup(rightFront, rightBack, rightTop);

		leftDriveEncoder = new Encoder(2, 3, false, CounterBase.EncodingType.k4X);
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		leftDriveEncoder.setDistancePerPulse(Util.TAU * (2.0 / 12) / 2048.0);

		rightDriveEncoder = new Encoder(4, 5, true, CounterBase.EncodingType.k4X);
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		rightDriveEncoder.setDistancePerPulse(Util.TAU * (2.0 / 12) / 2048.0);

		shifter = new Solenoid(0); // FIXME: might be wrong

		// end effector
		endEffectorIntakeMotor = new WPI_TalonSRX(13); // FIXME: probably

		// lift
		liftMotor = new WPI_TalonSRX(15);
		liftEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);

		// wrist
		wristPotentiometer = new Potentiometer(0);
		wristMotor = new WPI_TalonSRX(11);

		// sensors
		imu = new PigeonIMU(0);
		limelight = new Limelight();
	}
}

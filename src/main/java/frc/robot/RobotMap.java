/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
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


	// lift
	public WPI_TalonSRX liftMotor; // actually a talon
	public Encoder liftEncoder;

	// wrist
//	public Potentiometer wristPotentiometer; UNDO
//	public WPI_VictorSPX wristMotor; UNDO

	// end effector
//	public WPI_VictorSPX endEffectorIntakeMotor; UNDO


	// sensors
	PigeonIMU imu;
	public Limelight limelight;

	RobotMap() {
		// drive stuff
		var leftFront = new WPI_VictorSPX(1); // UNDO: changed id's to 2018's
		var leftBack = new WPI_VictorSPX(4);
		var leftTop = new WPI_VictorSPX(3);
		leftTop.setInverted(true);

  		var rightFront = new WPI_VictorSPX(0); // UNDO ^
		var rightBack = new WPI_VictorSPX(5);
		var rightTop = new WPI_VictorSPX(2);
		rightTop.setInverted(true);

		leftDrive = new SpeedControllerGroup(leftFront, leftBack, leftTop);
		rightDrive = new SpeedControllerGroup(rightFront, rightBack, rightTop);

		leftDriveEncoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X); // UNDO
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		leftDriveEncoder.setDistancePerPulse(Util.TAU * (2.0 / 12) / 2048.0);

		rightDriveEncoder = new Encoder(4, 5, true, CounterBase.EncodingType.k4X); // UNDO: changed id's
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		rightDriveEncoder.setDistancePerPulse(Util.TAU * (2.0 / 12) / 2048.0);

		shifter = new Solenoid(0); // FIXME: might be wrong

		// lift
		liftMotor = new WPI_TalonSRX(11); // UNDO changed id's
		liftEncoder = new Encoder(1, 2, false, CounterBase.EncodingType.k4X); // UNDO: changed id's

		// wrist
//		wristPotentiometer = new Potentiometer(0); UNDO
//		wristMotor = new WPI_VictorSPX(11); UNDO

		// end effector
//		endEffectorIntakeMotor = new WPI_VictorSPX(13); UNDO

		// sensors
		imu = new PigeonIMU(0);
		limelight = new Limelight();
	}
}

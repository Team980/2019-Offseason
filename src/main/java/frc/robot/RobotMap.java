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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.sensors.BetterIMU;
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
	public WPI_VictorSPX liftMotor;
	public Encoder liftEncoder;

	// wrist
	public Potentiometer wristPotentiometer;
	public WPI_TalonSRX wristMotor;

	// sensors
	public BetterIMU imu;

	RobotMap() {
		// drive stuff
		var leftFront = new WPI_VictorSPX(1);
		var leftBack = new WPI_VictorSPX(2);
		var leftTop = new WPI_VictorSPX(5);
		leftTop.setInverted(true);

  		var rightFront = new WPI_VictorSPX(3);
		var rightBack = new WPI_VictorSPX(4);
		var rightTop = new WPI_VictorSPX(6);
		rightTop.setInverted(true);

		leftDrive = new SpeedControllerGroup(leftFront, leftBack, leftTop);
		rightDrive = new SpeedControllerGroup(rightFront, rightBack, rightTop);

		leftDriveEncoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X);
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		leftDriveEncoder.setDistancePerPulse(Util.TAU * (2.0 / 12) / 2048.0);

		rightDriveEncoder = new Encoder(4, 5, true, CounterBase.EncodingType.k4X);
		//(Channel A port, Channel B port, is it inverted true/false, encoder type)
		rightDriveEncoder.setDistancePerPulse(Util.TAU * (2.0 / 12) / 2048.0);

		shifter = new Solenoid(0xdeadbeef);

		// end effector
		endEffectorIntakeMotor = new WPI_TalonSRX(0x0ddba11);

		// lift
		liftMotor = new WPI_VictorSPX(11);
		liftEncoder = new Encoder(10, 12, false, CounterBase.EncodingType.k4X);

		// wrist

		wristPotentiometer = new Potentiometer(0xfa1_0af);
		wristMotor = new WPI_TalonSRX(0xc0de);

		// sensors
		imu = new BetterIMU(0xfacade);
	}
}

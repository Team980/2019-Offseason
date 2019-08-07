/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;

public class Lift extends Subsystem {

	private SpeedController liftMotor;
	private Encoder liftEncoder;

	public Lift() {
		liftMotor = Robot.robotMap.liftMotor;
		liftEncoder = Robot.robotMap.liftEncoder;
	}

	@Override
	public void initDefaultCommand() {}

	public void set(double input) {
		liftMotor.set(input);
	}

	public void setPosition(int targetPosition) {
		var currentPosition = liftEncoder.getRaw();

		if (currentPosition < targetPosition - 1000) {
			liftMotor.set(1);

		} else if (currentPosition > targetPosition + 1000) {
			liftMotor.set(-1);

		} else {
			liftMotor.set(0);
		}
 	}

	// TODO STOP/DISABLE METHOD?  - if added, every subsystem should have one...
}
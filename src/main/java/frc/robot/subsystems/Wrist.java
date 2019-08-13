/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.commands.wrist.HoldWrist;
import frc.robot.sensors.Potentiometer;

public class Wrist extends Subsystem {

    private static final double SPEED = 0.5;
    private static final double DEADBAND = 36;

    // the minimum angles so we don't crash into ourselves
    private static final double EXCLUSION_MIN = 0;
    private static final double EXCLUSION_MAX = 360;

    private SpeedController wristMotor;
    private Potentiometer wristPotentiometer;

	public Wrist()  {
        wristPotentiometer = Robot.robotMap.wristPotentiometer;
        wristMotor = Robot.robotMap.wristMotor;
	}

	public void set(double input) {
		wristMotor.set(input);
	}

	public boolean isAtTargetAngle(double targetAngle) {
		double distance = targetAngle - currentAngle();
		return distance < DEADBAND;
	}

	public void moveTowards(double targetAngle) {
        double difference = targetAngle - currentAngle();

        double input; // figure out which velocity we want to be
		if (isAtTargetAngle(targetAngle) || isInExclusionZone()) {
			input = 0;

		} else if (difference > 0) {
			input = SPEED;

		} else { // distance is less than 0
			input = -SPEED;
		}

		set(input);
    }

    private boolean isInExclusionZone() {
        double currentAngle = currentAngle();
        return currentAngle < EXCLUSION_MIN || currentAngle > EXCLUSION_MAX;
    }

	public double currentAngle() {
		return wristPotentiometer.getAngle();
	}

	public void stopMotors() {
		wristMotor.stopMotor();
	}

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new HoldWrist());
    }
}
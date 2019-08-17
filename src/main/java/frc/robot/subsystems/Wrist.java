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
import frc.robot.Util;
import frc.robot.commands.wrist.HoldWrist;
import frc.robot.sensors.Potentiometer;

public class Wrist extends Subsystem {

    private static final double MAX_SPEED = 0.5;
    private static final double DEADBAND = 3;

    // the minimum angles so we don't crash into ourselves
    private static final double MINIMUM_ANGLE = 25; // experimental numbers found
    private static final double MAXIMUM_ANGLE = 288;

    private SpeedController wristMotor;
    private Potentiometer wristPotentiometer;

	public Wrist()  {
        wristPotentiometer = Robot.robotMap.wristPotentiometer;
        wristMotor = Robot.robotMap.wristMotor;
	}

	public void set(double input) {
		if ((input < 0 && currentAngle() > MINIMUM_ANGLE) || (input > 0 && currentAngle() < MAXIMUM_ANGLE)){
			wristMotor.set(input);
		}//the softstop check needs to be here where the motor is running, this way both manual and automation use the soft stop protection
		else{
			wristMotor.stopMotor();
		}
		
	}

	public boolean isAtTargetAngle(double targetAngle) {
		double distance = Math.abs(targetAngle - currentAngle());
		return distance < DEADBAND;
	}

	public void moveTowards(double targetAngle) {
        double difference = targetAngle - currentAngle();

        double input; // figure out which velocity we want to be

		if (isAtTargetAngle(targetAngle) /*|| isInExclusionZone()*/) {
			input = 0;
		} else {
			input = Util.map(difference, MINIMUM_ANGLE, MAXIMUM_ANGLE, -MAX_SPEED, MAX_SPEED); // FIXME: we don't actually know what the values from the pot will be
		}

		set(input);
    }

    /*private boolean isInExclusionZone() {
        double currentAngle = currentAngle();
        return currentAngle < MINIMUM_ANGLE || currentAngle > MAXIMUM_ANGLE;
    }*/

	public double currentAngle() {
		return wristPotentiometer.getAngle(); // NB: can add an offset here if values returned by potentiometer are not helpful
	}

	public void stopMotors() {
		wristMotor.stopMotor();
	}

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new HoldWrist());
    }
}
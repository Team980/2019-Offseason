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

    private static final double ENCODER_MAX_TICK_COUNT = 22_000; // TODO: change this value later to trashpanda's map
    private static final double DEADBAND = 2_000;
	
    private Encoder liftEncoder; 
    private SpeedController liftMotor; 

	public Lift()  {
        liftEncoder = Robot.robotMap.liftEncoder;
        liftMotor = Robot.robotMap.liftMotor;
	}

	public void set(double input) { 
		liftMotor.set(input);
	}

	public boolean isAtTargetPosition(int targetPosition) {
		double distance = targetPosition - currentPosition();
		return distance < DEADBAND;
	}


	public void moveTowards(int targetPosition) {
		double distance = targetPosition - currentPosition();
		double input; // figure out which velocity we want to be

		if (isAtTargetPosition(targetPosition)) {
			input = 0;

		} else if (distance > 0) {
			input = 0.5;

		} else { // distance is less than 0
			input = -0.5;
		}

		set(input);
	}

	public int currentPosition() {
		return liftEncoder.getRaw();
	} 

	public void stopMotors() {
		liftMotor.stopMotor();
	}

    @Override
    public void initDefaultCommand() {}
}
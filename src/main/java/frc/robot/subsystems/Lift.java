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
import frc.robot.Util;

public class Lift extends Subsystem {

    private static final double ENCODER_MAX_TICK_COUNT = 22_000; // TODO: determine experimentally
    private static final double DEADBAND = 0.05;
	
    private Encoder liftEncoder; 
    private SpeedController liftMotor; 

	public Lift()  {
        liftEncoder = Robot.robotMap.liftEncoder;
        liftMotor = Robot.robotMap.liftMotor;
	}

	public void set(double input) { 
		liftMotor.set(input);
	}

	public boolean isAtTargetPosition(double targetPosition) {
		double distance = Math.abs(targetPosition - currentPosition());
		return distance < DEADBAND;
	}

	public void moveTowards(double targetPosition) {
		double distance = targetPosition - currentPosition();

		double input;
		if (isAtTargetPosition(targetPosition)) {
			input = 0;
		} else {
			input = distance;
		}

		set(input);
	}

	public double currentPosition() {
		return Util.map(liftEncoder.getRaw(), 0, ENCODER_MAX_TICK_COUNT, 0, 1);
	} 

	public void stopMotors() {
		liftMotor.stopMotor();
	}

    @Override
    public void initDefaultCommand() {}
}
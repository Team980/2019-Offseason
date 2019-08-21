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
import frc.robot.commands.lift.HoldLift;

public class Lift extends Subsystem {

	private static final double EXCLUSION_MIN = 0.03;
	private static final double EXCLUSION_MAX = 0.97;
	private double minSpeedUp = .35;
	private double minSpeedDown = -.15;

	// private static final double EXCLUSION_MIN = 0.2;
	// private static final double EXCLUSION_MAX = 1.1;

	// private static final double ENCODER_MIN_TICK_COUNT = -20_000;

    // private static final double ENCODER_MAX_TICK_COUNT = 22_000; // TODO: determine experimentally
    private static final double DEADBAND = 0.02;
	
    private Encoder liftEncoder; 
    private SpeedController liftMotor; 

	public Lift()  {
        liftEncoder = Robot.robotMap.liftEncoder;
        liftMotor = Robot.robotMap.liftMotor;
	}

	public void rawSet(double input) {
		liftMotor.set(input); // no holds barred
	}

	public void set(double input) { 
	
		if ((input < 0 && currentPosition() > EXCLUSION_MIN) || (input > 0 && currentPosition() < EXCLUSION_MAX)) {
			liftMotor.set(input);
		} 
		else {
			liftMotor.set(0);
		}
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
		} 
		else if (distance <= 0 && (distance > minSpeedDown)){
				input = minSpeedDown;
		}
		else if (distance > 0 && (distance < minSpeedUp)){
				input = minSpeedUp;
		}
		else {
			input = distance;
		}

		System.out.println(input);
		set(input);
	}


	public double getSpeed() {
		return liftEncoder.getRate();
	}

	public double currentPosition() {
		return Util.map(liftEncoder.getRaw(), -20_000, 0, 0, 1);
	} 

	public void stopMotors() {
		liftMotor.stopMotor();
	}

    @Override
    public void initDefaultCommand() {
		setDefaultCommand(new HoldLift());
	}
}
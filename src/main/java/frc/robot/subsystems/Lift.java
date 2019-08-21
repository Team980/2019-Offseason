/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.Util;
import frc.robot.commands.lift.HoldLift;

public class Lift extends Subsystem {

	private static final double MAX_LIFT_VELOCITY = 100; // TODO: determine

	private static final double EXCLUSION_MIN = 0.03;
	private static final double EXCLUSION_MAX = 0.97;

	private static double TOLERANCE = 15; // 0 - 100

	// private static final double EXCLUSION_MIN = 0.2;
	// private static final double EXCLUSION_MAX = 1.1;

	// private static final double ENCODER_MIN_TICK_COUNT = -20_000;

    // private static final double ENCODER_MAX_TICK_COUNT = 22_000; // TODO: determine experimentally
    private static final double DEADBAND = 0.02;
	
    private Encoder liftEncoder; 
    private SpeedController liftMotor;

    private PIDController pidController;

	public Lift()  {
        liftEncoder = Robot.robotMap.liftEncoder;
        liftMotor = Robot.robotMap.liftMotor;


		pidController = new PIDController(1, 1, 1, liftEncoder, liftMotor); // TODO: include pid constants
		pidController.setPercentTolerance(TOLERANCE);
		pidController.setName("lift pid controller");

		pidController.enable();
	}

	private void setVelocity(double velocity) {
		double newSetPoint;
		if ((velocity < 0 && currentPosition() > EXCLUSION_MIN) || (velocity > 0 && currentPosition() < EXCLUSION_MAX)) {
			newSetPoint = velocity;
		} else {
			newSetPoint = 0;
		}

		Robot.debugTable.getEntry("lift target velocity").setNumber(newSetPoint);
		pidController.setSetpoint(newSetPoint);
	}

	public boolean isAtTargetPosition(double targetPosition) {
		double distance = Math.abs(targetPosition - currentPosition());
		return distance < DEADBAND;
	}

	public void moveTowards(double targetPosition) {
		double distance = targetPosition - currentPosition();

		setVelocity(distance); // TODO: translate distance to velocity
	}

	public void manualSet(double joystickValue) {
		// joystick values are -1..0..1
		double deadbinded = OI.applyDeadband(joystickValue, 0.1);
		double velocity = Util.map(deadbinded, -1, 1, -MAX_LIFT_VELOCITY, MAX_LIFT_VELOCITY);
		setVelocity(velocity);
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
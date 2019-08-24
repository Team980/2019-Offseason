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

	private static final double MAX_LIFT_VELOCITY = 14_000; // UNDO: determine experimentally

	private static final double EXCLUSION_MIN = 0.03;
	private static final double EXCLUSION_MAX = 0.97;

	private static final double TOLERANCE = 10; // 0 - 100

	// private static final double EXCLUSION_MIN = 0.2;
	// private static final double EXCLUSION_MAX = 1.1;

	private static final double ENCODER_MIN_TICK_COUNT = 0; // UNDO changed min and max
	private static final double ENCODER_MAX_TICK_COUNT = 190_000; // UNDO

    private static final double DEADBAND = 0.01;
	
    private Encoder liftEncoder; 
    private SpeedController liftMotor;

    private PIDController pidController;

	public Lift()  {
        liftEncoder = Robot.robotMap.liftEncoder;
        liftMotor = Robot.robotMap.liftMotor;


		liftEncoder.setPIDSourceType(PIDSourceType.kRate);

		// good value
		// coast p=0.000004
		// overly sharp p=0.0001
		// sharp - 
		pidController = new PIDController(0.0001, 0, 0, liftEncoder, liftMotor); 
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
		Robot.debugTable.getEntry("lift encoder speed").setNumber(liftEncoder.getRate());

		//liftEncoder.setPIDSourceType(PIDSourceType.kRate);
		pidController.setSetpoint(newSetPoint);
	}

	

	public boolean isAtTargetPosition(double targetPosition) {
		double distance = Math.abs(targetPosition - currentPosition());
		return distance < DEADBAND;
	}

	public void setPosition(double scaledPosition) {
		double encoderTicks = Util.map(scaledPosition, 0, 1, ENCODER_MIN_TICK_COUNT, ENCODER_MAX_TICK_COUNT);
		
		liftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		pidController.setSetpoint(encoderTicks);
	}

	public void hold() {
		setVelocity(0);
	}

	public void moveTowards(double targetPosition) {
		double distance = targetPosition - currentPosition();
		//double velocity = MAX_LIFT_VELOCITY * Math.signum(distance);
		
		double power = distance;

	
		double MIN_POWER = 1;
		if (Math.abs(power) < MIN_POWER) {
			setVelocity(Math.copySign(MIN_POWER, power)*MAX_LIFT_VELOCITY);
			//power = ;
		} else {
			setVelocity(2*power*MAX_LIFT_VELOCITY);
		}


		// double velocity = power * MAX_LIFT_VELOCITY;
		// //double velocity = 2*Util.map(power, -1, 1, -MAX_LIFT_VELOCITY, MAX_LIFT_VELOCITY);


		// setVelocity(velocity); 
	}

	public void manualSet(double joystickValue) {
		// joystick values are -1..0..1
		double deadbinded = OI.applyDeadband(joystickValue, 0.1);
		double velocity = Util.map(deadbinded, -1, 1, -MAX_LIFT_VELOCITY, MAX_LIFT_VELOCITY);
		setVelocity(velocity);
	}


	public double currentPosition() {
		return Util.map(liftEncoder.getRaw(), ENCODER_MIN_TICK_COUNT, ENCODER_MAX_TICK_COUNT, 0, 1);
	} 

	public void stopMotors() {
		liftMotor.stopMotor();
	}

    @Override
    public void initDefaultCommand() {
		setDefaultCommand(new HoldLift());
	}
}
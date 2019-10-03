/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;


public class DriveSystem extends Subsystem {
/*	private static final double P = 0.01;
	private static final double I = 0;
	private static final double D = 0;
	private static final double MAX_VELOCITY = 17;*/ 

	private DifferentialDrive differentialDrive;

	private Encoder leftEncoder; 
	private Encoder rightEncoder; 

	private Solenoid shifterSolenoid;

	private SpeedController leftMotor;
	private SpeedController rightMotor;

	public DriveSystem() {	
		leftMotor = Robot.robotMap.leftDrive;
		rightMotor = Robot.robotMap.rightDrive;
		leftEncoder = Robot.robotMap.leftDriveEncoder; 
		rightEncoder = Robot.robotMap.rightDriveEncoder;
		
		shifterSolenoid = Robot.robotMap.shifter;
	}

	@Override
	public void initDefaultCommand() {
		//setDefaultCommand(new TelopDrive());
	}

	public void driveRobot(double move, double turn) {
		differentialDrive.arcadeDrive(move, turn);
	}

	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightDistance() {
		return rightEncoder.getDistance();
	}

	public void resetEncoderDistance() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getLeftSpeed() {
		return leftEncoder.getRate();
	}

	public double getRightSpeed() {
		return rightEncoder.getRate();
	}

	public Gear getGear() {
		return shifterSolenoid.get()? Gear.LOW : Gear.HIGH; // returns Gear.LOW when shifterSolenoid.get() is true
	}	

	public void setGear(boolean gear) {
		shifterSolenoid.set(gear);
	}

	public void stopMotors() {
		leftMotor.set(0);
		rightMotor.set(0);
	}

	public enum Gear {
		LOW,
		HIGH
	}

	public static double limit(double x) {
		return Math.abs(x) > 1 ? Math.signum(x) : x;
	}
/*	public void arcadeDrive(double xSpeed, double zRotation) {
		xSpeed = Math.copySign(xSpeed*xSpeed, xSpeed); // squaring inputs
		zRotation = Math.copySign(zRotation*zRotation, zRotation);
	
		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
		if (xSpeed * zRotation >= 0) { // signs are the same
			tankDrive(maxInput, xSpeed-zRotation);
		} else {
			tankDrive(xSpeed+zRotation, maxInput);
		}
	}

	public void tankDrive(double left, double right) {
		left = limit(left);
		right = limit(right);

		if (isPIDEnabled()) {
        		leftController.setSetpoint(left * MAX_VELOCITY);
        		rightController.setSetpoint(right * MAX_VELOCITY); 
		} else {
			leftMotor.set(left);
			rightMotor.set(right);
		}
	}*/


}



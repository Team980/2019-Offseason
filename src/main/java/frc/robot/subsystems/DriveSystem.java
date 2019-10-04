/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

public class DriveSystem extends Subsystem {
	private DifferentialDrive differentialDrive;

	public DriveSystem() {
		differentialDrive = new DifferentialDrive(Robot.robotMap.leftDrive, Robot.robotMap.rightDrive);
	}

	@Override
	public void initDefaultCommand() {
		//setDefaultCommand(new TelopDrive());
	}

	public void driveRobot(double move, double turn) {
		differentialDrive.arcadeDrive(move, turn);
	}

	public double getLeftDistance() {
		return Robot.robotMap.leftDriveEncoder.getDistance();
	}

	public double getRightDistance() {
		return Robot.robotMap.rightDriveEncoder.getDistance();
	}

	public void resetEncoderDistance() {
		Robot.robotMap.leftDriveEncoder.reset();
		Robot.robotMap.rightDriveEncoder.reset();
	}

	public double getLeftSpeed() {
		return Robot.robotMap.leftDriveEncoder.getRate();
	}

	public double getRightSpeed() {
		return Robot.robotMap.rightDriveEncoder.getRate();
	}

	public Gear getGear() {
		return Robot.robotMap.shifter.get()? Gear.LOW : Gear.HIGH; // returns Gear.LOW when shifterSolenoid.get() is true
	}	

	public void setGear(boolean gear) {
		Robot.robotMap.shifter.set(gear);
	}

	public void stopMotors() {
		differentialDrive.arcadeDrive(0 , 0);
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



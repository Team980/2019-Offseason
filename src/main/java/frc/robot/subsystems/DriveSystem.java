/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

public class DriveSystem extends Subsystem {
	
	private DifferentialDrive differentialDrive;

	private SpeedController leftDrive;
	private SpeedController rightDrive;

	private Encoder leftEncoder; 
	private Encoder rightEncoder; 

	private Solenoid shifterSolenoid;

	PIDController leftPidController;
	PIDController rightPidController;

	public DriveSystem() {	
		leftDrive = Robot.robotMap.leftDrive;
		rightDrive = Robot.robotMap.rightDrive;

		differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
		
		shifterSolenoid = Robot.robotMap.shifter;

		leftEncoder = Robot.robotMap.leftDriveEncoder; 
		rightEncoder = Robot.robotMap.rightDriveEncoder;

		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);

		leftPidController = new PIDController(0.0001, 0, 0, leftEncoder, leftDrive);
		rightPidController = new PIDController(0.0001, 0, 0, rightEncoder, rightDrive);
	}

	@Override
	public void initDefaultCommand() {}

	public void driveRobot(double move, double turn) {
		differentialDrive.arcadeDrive(move, turn);
	}

	public void velocityControlDriveForward(double targetDistance) {
		double currentLeftDistance = leftEncoder.getDistance();
		double currentRightDistance = rightEncoder.getDistance();
	
		//double speed = Util.map(currentLeftDistance, 0, targetDistance, 0, 1);

		double speed = (targetDistance - currentLeftDistance) / targetDistance;

		//double speed = currentLeftDistance / targetDistance;

		// double distanceToMove = currentLeftDistance - targetDistance;
		
		// double speed = (distanceToMove - currentLeftDistance) / distanceToMove;
	
		//double alsoSpeed = Util.map(currentLeftDistance, leftDriveStartDistance, leftDriveStartDistance+distance, 0, 1);
	
		leftPidController.setSetpoint(speed);
		rightPidController.setSetpoint(speed);
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

	public void setGear(Gear gear) {
		shifterSolenoid.set(gear == Gear.LOW);
	}

	public void stopMotors() {
		differentialDrive.stopMotor(); 
	}

	public enum Gear {
		LOW,
		HIGH
	}
}



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
//import frc.robot.commands.drive.TelopDrive;

public class DriveSystem extends Subsystem {
	
	private DifferentialDrive differentialDrive;

	private Encoder leftEncoder; 
	private Encoder rightEncoder; 

	private Solenoid shifterSolenoid;

	public DriveSystem() {
		differentialDrive = new DifferentialDrive(Robot.robotMap.leftDrive, Robot.robotMap.rightDrive);
	
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
		if (shifterSolenoid.get()){
			return Gear.LOW;
		}
		else{
			return Gear.HIGH;
		}
	}//end getGear	

	public void setGear(boolean gear) {//false = high gear, true = low gear
		shifterSolenoid.set(gear);
	}

	public void stopMotors() {
		differentialDrive.stopMotor(); 
	}

	public enum Gear {
		LOW,
		HIGH
	}
}



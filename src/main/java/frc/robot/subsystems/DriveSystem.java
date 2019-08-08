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
import frc.robot.commands.lift.DriveCommand;

public class DriveSystem extends Subsystem {

	private Solenoid shifter;

	private DifferentialDrive differentialDrive;

	private Encoder leftEncoder; 
	private Encoder rightEncoder; 
	//private boolean autoShift; 

	public DriveSystem() {
		differentialDrive = new DifferentialDrive(Robot.robotMap.leftDrive , Robot.robotMap.rightDrive);
	
		leftEncoder = Robot.robotMap.leftDriveEncoder; 
		rightEncoder = Robot.robotMap.rightDriveEncoder;
		//autoShift = true; 
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand()); //Better to do this in Robot.java's teleopInit, otherwise it interferes with autonomous
	}

	public void driveRobot(double move, double turn) { 
		/*if ((leftEncoder.getRate() > 4) && (rightEncoder.getRate() > 4) && autoShift) {
			setHighGear();
		}
		else if ((leftEncoder.getRate() < 3) && (rightEncoder.getRate() < 3) && autoShift) {
			setLowGear(); 
		}*/
		differentialDrive.arcadeDrive(move, turn);
	}
		
	public void disable() {
		differentialDrive.stopMotor(); 
	 }

	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightDistance(){
		return rightEncoder.getDistance();
	}

	public void resetEncoderDistance(){
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void setHighGear() {
		shifter.set(false);
	}

	public void setLowGear() {
		shifter.set(true);
	}

	/*public void setAutoShift(boolean auto) {
		autoShift = auto;
	} */
}



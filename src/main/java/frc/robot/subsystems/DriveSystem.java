/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.DifferentialDrive980;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

public class DriveSystem extends Subsystem {
	//private DifferentialDrive differentialDrive;
	private DifferentialDrive980 drive980;

	public DriveSystem() {
		//differentialDrive = new DifferentialDrive(Robot.robotMap.leftDrive, Robot.robotMap.rightDrive);
		drive980 = new DifferentialDrive980(Robot.robotMap.leftDrive, Robot.robotMap.rightDrive);
	}


	public void updateP() {
		if (getGear() == Gear.LOW) {
			drive980.setLowP();
		} else {
			drive980.setHighP();
		}
	}

	@Override
	public void initDefaultCommand() {
		//setDefaultCommand(new TelopDrive());
	}

	public void driveRobot(double move, double turn) {
		//differentialDrive.arcadeDrive(move, turn);
	
/**
 * if the enable PID switch is on and the PID controllers are not already enabled
 * enable them
 * if the enable PID switch is off and the PID controllers are currently enabled
 * disable them
 */	
		if (Robot.oi.getEnablePIDDrive()){
			if (!drive980.isPIDEnabled()){
				drive980.enablePID();
				System.out.println("PID Drive active");
			}
		}
		else{
			if (drive980.isPIDEnabled()){
				drive980.disablePID();
				System.out.println("Competition Drive active");
			}
		}
		drive980.arcadeDrive(move, turn);
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
		//differentialDrive.arcadeDrive(0 , 0);
		drive980.arcadeDrive(0, 0);
	}

	public enum Gear {
		LOW,
		HIGH
	}

/*	public static double limit(double x) {
		return Math.abs(x) > 1 ? Math.signum(x) : x;
	}*/

}



/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.DriveSystem;
//import frc.robot.subsystems.DriveSystem.Gear;

public class AutoShift extends Command {
  
	//private DriveSystem driveSystem;

	public AutoShift() {
		//driveSystem = Robot.driveSystem;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Math.abs(Robot.driveSystem.getLeftSpeed()) > 4.5 || Math.abs(Robot.driveSystem.getRightSpeed()) > 4.5) {
			Robot.driveSystem.setGear(false);

		} else if (Math.abs(Robot.driveSystem.getLeftSpeed()) < 3.75 && Math.abs(Robot.driveSystem.getRightSpeed()) < 3.75) {
			Robot.driveSystem.setGear(true);
		}
		//Robot.driveSystem.setGear(true);
	}
  
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;

public class TelopDrive extends Command {

	private DriveSystem driveSystem;
	private OI oi;

	public TelopDrive() {
		driveSystem  = Robot.driveSystem;
		oi = Robot.oi;
		
		requires(driveSystem);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		driveSystem.driveRobot(oi.getMove(), oi.getTurn());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		driveSystem.stopMotors();
	}
}

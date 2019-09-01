/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Wrist;

public class SetWristAngle extends Command {

	private Wrist wrist;

	private double targetAngle;

	public SetWristAngle(double targetAngle) {
		this.targetAngle = targetAngle;

		wrist = Robot.wrist;

		requires(wrist);
	}

	@Override
	protected void execute() {
		wrist.moveTowards(targetAngle);
	}

	@Override
	protected boolean isFinished() {
		return wrist.isAtTargetAngle(targetAngle);
	}

  	// Called when another command which requires one or more of the same
  	// subsystems is scheduled to run
  	@Override
 	protected void interrupted() {
		wrist.stopMotors();
	}
	  
	@Override
	protected void end() {
		wrist.stopMotors();
	}
}

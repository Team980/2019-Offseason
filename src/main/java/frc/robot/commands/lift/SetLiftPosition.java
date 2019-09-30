/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class SetLiftPosition extends Command {


	private double targetPosition;

	public SetLiftPosition(double targetPosition) {
		this.targetPosition = targetPosition;

		requires(Robot.lift);
	}

	@Override
	protected void execute() {
				// stuff is in periodic on lift now
		Robot.lift.moveTowards(targetPosition);
	}

	@Override
	protected boolean isFinished() {
		return Robot.lift.isAtTargetPosition(targetPosition);
	}

	@Override
	protected void end() {
		Robot.lift.stopMotors();
	}
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class SetLiftPosition extends Command {

	private Lift lift;

	private double targetPosition;

	public SetLiftPosition(double targetPosition) {
		this.targetPosition = targetPosition;

		lift = Robot.lift;

		requires(lift);
	}

	@Override
	protected void execute() {
		lift.moveTowards(targetPosition);
	}

	@Override
	protected boolean isFinished() {
		return lift.isAtTargetPosition(targetPosition);
	}

	@Override
	protected void end() {
		lift.stopMotors();
	}
}

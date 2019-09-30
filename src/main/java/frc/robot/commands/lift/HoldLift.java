/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldLift extends Command {

	private double targetPosition;

	public HoldLift() {
		requires(Robot.lift);
	}

	@Override
	protected void initialize() {
		targetPosition = Robot.lift.currentPosition();
	}

	@Override
	protected void execute() {
		Robot.lift.moveTowards(targetPosition);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.lift.stopMotors();
	}
}



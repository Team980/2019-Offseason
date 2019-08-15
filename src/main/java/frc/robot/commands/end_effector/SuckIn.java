/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.end_effector;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.EndEffector;

public class SuckIn extends Command {

    private EndEffector endEffector;

    public SuckIn() {
        endEffector = Robot.endEffector;

        requires(endEffector);
    }

    // Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        endEffector.set(0.75);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
        endEffector.stopMotors();
	}
}

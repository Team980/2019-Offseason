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
		requires(Robot.pidLift);
		requires(Robot.pidPositionalLift);
	}

	@Override
	protected void initialize() {
		if (Robot.oi.getEnablePIDLift()){
			Robot.pidLift.enable();
			Robot.pidPositionalLift.disable();
			targetPosition = Robot.pidLift.currentPosition();
			System.out.println("velocity control lift active");
		}
		else if (Robot.oi.getEnablePIDPositionalLift()){
			Robot.pidPositionalLift.enable();
			Robot.pidLift.disable();
			targetPosition = Robot.pidPositionalLift.getPosition();
			System.out.println("position control lift active");
		}
		else{
			Robot.pidPositionalLift.disable();
			Robot.pidLift.disable();
			targetPosition = Robot.pidLift.currentPosition();
			System.out.println("competition lift active");
		}
		
	}

	@Override
	protected void execute() {
		if (Robot.oi.getEnablePIDLift()){
			Robot.pidLift.setSetpoint(0);
		}
		else if (Robot.oi.getEnablePIDPositionalLift()){
			Robot.pidPositionalLift.setSetpoint(targetPosition);
		}
		else{
			Robot.pidLift.moveTowards(targetPosition);
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}
}



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

		requires(Robot.pidLift);
		requires(Robot.pidPositionalLift);
	}

	@Override
	protected void initialize() {
		if (Robot.oi.getEnablePIDLift()){
			Robot.pidLift.enable();
			Robot.pidPositionalLift.disable();
			System.out.println("Lift velocity control active");
		}
		else if (Robot.oi.getEnablePIDPositionalLift()){
			Robot.pidPositionalLift.enable();
			Robot.pidLift.disable();
			System.out.println("Lift positional PID control active");
		}
		else{
			Robot.pidPositionalLift.disable();
			Robot.pidLift.disable();
			System.out.println("Competition Lift active");
		}
		
	}

	@Override
	protected void execute() {
		if (Robot.oi.getEnablePIDPositionalLift()){
			Robot.pidPositionalLift.setSetpoint(targetPosition);
		}
		else{
			Robot.pidLift.moveTowards(targetPosition);
		}
		
	}

	@Override
	protected boolean isFinished() {
		if (Robot.oi.getEnablePIDPositionalLift()){
			return false;
		}
		else{
			return Robot.pidLift.isAtTargetPosition(targetPosition);
		}
	}

	@Override
	protected void end() {
	}
}

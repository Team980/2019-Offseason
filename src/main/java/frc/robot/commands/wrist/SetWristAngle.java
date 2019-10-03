/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetWristAngle extends Command {

	private double targetAngle;

	public SetWristAngle(double targetAngle) {
		this.targetAngle = targetAngle;


		//requires(Robot.wrist);
		requires(Robot.pidWrist);
	}

	@Override
    protected void initialize() {
		if (Robot.oi.getEnablePIDWrist()){
			Robot.pidWrist.enable();
			System.out.println("PID Wrist Active");
		}
		else{
			System.out.println("Competition Wrist Active");
		}
    }

	@Override
	protected void execute() {
		if (Robot.oi.getEnablePIDWrist()){
			Robot.pidWrist.setSetpoint(targetAngle);
		}
		else{
			Robot.pidWrist.moveTowards(targetAngle);
		}
	}

	@Override
	protected boolean isFinished() {
		if (Robot.oi.getEnablePIDWrist()){
			return false;
		}
		else{
			return Robot.pidWrist.onTarget();
		}
	}

  	// Called when another command which requires one or more of the same
  	// subsystems is scheduled to run
  	@Override
 	protected void interrupted() {
	}
	  
	@Override
	protected void end() {
	}
}

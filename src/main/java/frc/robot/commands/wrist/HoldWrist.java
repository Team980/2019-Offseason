/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldWrist extends Command {


    private double targetPosition;

	public HoldWrist() {

		requires(Robot.pidWrist);
		requires(Robot.wrist);
	}

	@Override
	protected void initialize() {
		targetPosition = Robot.pidWrist.getPosition();
		//Robot.debugTable.getEntry("wrist target").setNumber(targetPosition);
	}

	@Override
	protected void execute() {
		Robot.pidWrist.setSetpoint(targetPosition);
		//System.out.println("hold command" + targetPosition);
	}

	@Override
	protected boolean isFinished() {
		return false;
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



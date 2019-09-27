/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.Wrist;

public class HoldWrist extends Command {

	//private Wrist wrist;

	private double targetPosition;
	private double stickIncrement = 1;//amount target position can be changed by the stick each loop

	public HoldWrist() {
    //    wrist = Robot.wrist;

		requires(Robot.wrist);
	}

	@Override
	protected void initialize() {
		targetPosition = Robot.wrist.currentAngle();
		Robot.debugTable.getEntry("wrist target").setNumber(targetPosition);
	}

	@Override
	protected void execute() {
		if (Robot.oi.getWristJoystickValue() > 0){//experimental fine wrist control without disabling automation
			targetPosition += stickIncrement;
		}
		else if(Robot.oi.getWristJoystickValue() < 0){
			targetPosition -= stickIncrement;
		}

		Robot.wrist.moveTowards(targetPosition);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

  	// Called when another command which requires one or more of the same
  	// subsystems is scheduled to run
  	@Override
 	protected void interrupted() {
		Robot.wrist.stopMotors();
	}
	  
	@Override
	protected void end() {
		Robot.wrist.stopMotors();
	}
}



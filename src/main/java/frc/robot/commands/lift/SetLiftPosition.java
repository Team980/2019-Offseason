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

	int targetPosition; 

	public SetLiftPosition(int targetPosition) {
		this.targetPosition = targetPosition;

		requires(Robot.liftSystem); 
	}
	
	@Override
	protected void initialize() {
		Robot.liftSystem.setPosition(targetPosition); //setPosition should be called continuously as the command executes
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return false; //How does it know when it's reached targetPosition?
	}

	@Override
	protected void end() {
		
	}

}

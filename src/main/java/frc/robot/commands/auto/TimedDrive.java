/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;

public class TimedDrive extends TimedCommand {
  
  private DriveSystem driveSystem;

  private double speed;
	
	public TimedDrive(double timeout, double forwardSpeed) {
    	super(timeout);
    
    	speed = forwardSpeed;

		driveSystem = Robot.driveSystem;

		requires(driveSystem);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
    	driveSystem.driveRobot(speed, 0);
	}

	// Called once after timeout
	@Override
	protected void end() {
    	driveSystem.stopMotors();
	}
}

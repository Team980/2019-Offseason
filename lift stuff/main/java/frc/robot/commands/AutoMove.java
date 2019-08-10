/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoMove extends Command {
  private double distance;
  private boolean finished; 
  private int direction; 

  public AutoMove(double distance) {
    this.distance = distance;
    finished = false;
    direction = 1;
    requires(Robot.driveSystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSystem.getLeftEncoder().reset();
    Robot.driveSystem.setAutoShiftEnabled(false);
    Robot.driveSystem.setGear(true);
    if (distance < 0) {
      direction = -1;
    }
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {  
    if (Math.abs(distance) > Math.abs(Robot.driveSystem.getLeftEncoder().getDistance())) {
      Robot.driveSystem.arcadeDrive(0.5*direction, 0, false);
    }
    else {
      finished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSystem.arcadeDrive(0, 0, false);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class AutoTurn extends Command {
  private double angle; 
  private double [] ypr; 
  private boolean finished; 
  private int direction; 

  public AutoTurn(double angle) {
    this.angle = angle; 
    finished = false; 
    direction = -1; 
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires (Robot.driveSystem); 
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.robotMap.imu.setYaw(0);
    if (angle < 0) {
      direction = 1;  
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ypr = Robot.robotMap.getYpr(); 

    if (Math.abs(angle) > Math.abs(ypr[0])){
      Robot.driveSystem.arcadeDrive(0,0.5*direction,false); 
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
    Robot.driveSystem.arcadeDrive(0,0,false); 
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IncrementLiftPosition extends Command {

  private double targetPosition;
  private double increment;

  public IncrementLiftPosition(double increment) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  
    this.increment = increment;

    requires(Robot.pidLift);
    requires(Robot.pidPositionalLift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.oi.getEnablePIDPositionalLift()){
      Robot.pidPositionalLift.enable();
      targetPosition = Robot.pidPositionalLift.getPosition() + increment;
    }
    else{
      targetPosition = Robot.pidLift.currentPosition() + increment;
    }
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.oi.getEnablePIDPositionalLift()){
      Robot.pidPositionalLift.setSetpoint(targetPosition);
    }
    else{
      Robot.pidLift.moveTowards(targetPosition);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
		return Robot.pidLift.isAtTargetPosition(targetPosition);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }
}

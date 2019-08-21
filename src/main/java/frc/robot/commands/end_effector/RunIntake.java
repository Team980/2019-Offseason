/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.end_effector;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.EndEffector;

public class RunIntake extends Command {
  private EndEffector endEffector;
  private OI oi;

  public RunIntake() {
    endEffector = Robot.endEffector;
    oi = Robot.oi;

    requires(endEffector);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double inSpeed = oi.getSuckInSpeed();
    double outSpeed = oi.getSpitOutSpeed();

    if (inSpeed > 0) {
      endEffector.set(inSpeed);
    } 
    else if (outSpeed > 0) {
      endEffector.set(-outSpeed);
    } 
    else {
      endEffector.set(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
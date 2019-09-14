/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;


public class CheckPID extends Command {
  private Joystick stick;
  private DriveSystem driveSystem;

  private boolean startSwitchState;


  public CheckPID() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    stick = Robot.oi.prajBox;
    driveSystem = Robot.driveSystem;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startSwitchState = stick.getRawButton(7);
    System.out.println("started");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean currentSwitchState = stick.getRawButton(7); // white switch

    if (startSwitchState == currentSwitchState) {
      driveSystem.enablePID();
    } else {
      driveSystem.disablePID();
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

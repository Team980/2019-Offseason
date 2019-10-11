/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PIDDrive extends Command {
  public PIDDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.pidRightDrive);
    requires(Robot.pidLeftDrive);
    requires(Robot.driveSystem);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.pidLeftDrive.enable();
    Robot.pidRightDrive.enable();
    System.out.println("PID Drive activated");
    Robot.robotMap.rightDrive.setInverted(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    arcadeDrivePID(Robot.oi.getMove(), Robot.oi.getTurn());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.pidLeftDrive.setSetpoint(0);
    Robot.pidRightDrive.setSetpoint(0);
    Robot.pidLeftDrive.disable();
    Robot.pidLeftDrive.disable();
    Robot.robotMap.rightDrive.setInverted(false);
 }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.pidLeftDrive.setSetpoint(0);
    Robot.pidRightDrive.setSetpoint(0);
    Robot.pidLeftDrive.disable();
    Robot.pidLeftDrive.disable();
    Robot.robotMap.rightDrive.setInverted(false);
  }

  public void arcadeDrivePID(double xSpeed, double zRotation) {
		xSpeed = Math.copySign(xSpeed*xSpeed, xSpeed); // squaring inputs
		zRotation = Math.copySign(zRotation*zRotation, zRotation);
	
		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
    if (xSpeed * zRotation >= 0) { // signs are the same
      Robot.pidLeftDrive.setSetpoint(maxInput * Robot.pidLeftDrive.getMaxVelocity());
      Robot.pidRightDrive.setSetpoint((xSpeed-zRotation) * Robot.pidLeftDrive.getMaxVelocity());
    } 
    else {
      Robot.pidLeftDrive.setSetpoint((xSpeed+zRotation) * Robot.pidLeftDrive.getMaxVelocity());
      Robot.pidRightDrive.setSetpoint(maxInput * Robot.pidLeftDrive.getMaxVelocity());
		}
	}
}

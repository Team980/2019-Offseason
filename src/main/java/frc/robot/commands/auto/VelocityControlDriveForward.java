/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class VelocityControlDriveForward extends Command {


  private double distance;

  public VelocityControlDriveForward(double distance) {
    this.distance = distance;
    
    requires(Robot.driveSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.robotMap.leftDriveEncoder.reset(); // TODO: this is so painful
    Robot.robotMap.rightDriveEncoder.reset(); 

    Robot.driveSystem.enablePID(true); //usotnaehuaoesnu haoe
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveSystem.velocityControlDriveForward(distance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.driveSystem.getLeftDistance() > distance
      || Robot.driveSystem.getRightDistance() > distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.driveSystem.stopMotors();
    Robot.driveSystem.enablePID(false);

    Robot.driveSystem.driveRobot(0,0);

    System.out.println("move forward stopped");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

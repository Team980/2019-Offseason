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


  PIDController leftPidController;
  PIDController rightPidController;

  private double distance;

  private double leftDriveStartDistance;
  private double rightDriveStartDistance;

  public VelocityControlDriveForward(double distance) {
    Robot.robotMap.leftDriveEncoder.setPIDSourceType(PIDSourceType.kRate);
    Robot.robotMap.rightDriveEncoder.setPIDSourceType(PIDSourceType.kRate);

    leftPidController = new PIDController(0.00001, 0, 0, Robot.robotMap.leftDriveEncoder, Robot.robotMap.rightDrive);
    rightPidController = new PIDController(0.00001, 0, 0, Robot.robotMap.rightDriveEncoder, Robot.robotMap.leftDrive);

    this.distance = distance;
    leftDriveStartDistance = Robot.robotMap.leftDriveEncoder.getDistance();
    rightDriveStartDistance = Robot.robotMap.rightDriveEncoder.getDistance();
    
    requires(Robot.driveSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.robotMap.leftDriveEncoder.reset(); // TODO: this is so painful
    Robot.robotMap.rightDriveEncoder.reset(); 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  //   double currentLeftDistance = Robot.robotMap.leftDriveEncoder.getDistance();
  //   double currentRightDistance = Robot.robotMap.rightDriveEncoder.getDistance();

  //   double distanceToMove = currentLeftDistance - (leftDriveStartDistance+distance);
    
  //   double speed = (distanceToMove - currentLeftDistance) / distanceToMove;

  //   //double alsoSpeed = Util.map(currentLeftDistance, leftDriveStartDistance, leftDriveStartDistance+distance, 0, 1);

  //   leftPidController.setSetpoint(speed);
  //   rightPidController.setSetpoint(speed);
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

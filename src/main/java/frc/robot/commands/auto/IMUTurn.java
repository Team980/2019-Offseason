/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IMUTurn extends Command {

   private static final double DEADBAND = 1.0; // degrees

   private static final double minTurnSpeed = 0.3;
   private double pCoefficient = 2;

   private double targetAngle;
   private double turnSpeed;

	public IMUTurn(double targetAngle) {
   
       this.targetAngle = targetAngle;

       requires(Robot.driveSystem);
   }

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
       // figure out if we want to turn clockwise or counterclockwise
       turnSpeed = -1 * pCoefficient * (targetAngle - Robot.ypr[0]) / 180; //imu angle is reverse sign from the control stick
       if (turnSpeed < minTurnSpeed){
         turnSpeed = minTurnSpeed;
       }
       Robot.driveSystem.driveRobot(0, turnSpeed);
   }

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
       return Math.abs(Robot.ypr[0] - targetAngle) < DEADBAND;
   }

	// Called once after isFinished returns true
	@Override
	protected void end() {
        Robot.driveSystem.stopMotors();
	}

}

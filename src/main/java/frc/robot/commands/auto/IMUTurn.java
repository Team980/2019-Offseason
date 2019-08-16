/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Util;
import frc.robot.subsystems.DriveSystem;

public class IMUTurn extends Command {

   private static final double DEADBAND = 1.0; // degrees

   private static final double ABSOLUTE_TURN_SPEED = 0.35;

   private DriveSystem driveSystem;

   private double targetAngle;

	public IMUTurn(double targetAngle) {
       driveSystem = Robot.driveSystem;

       this.targetAngle = targetAngle;

       requires(driveSystem);
   }

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
       // figure out if we want to turn clockwise or counterclockwise
       driveSystem.driveRobot(0, Math.copySign(ABSOLUTE_TURN_SPEED, Robot.ypr[0] - targetAngle));
   }

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
       return Math.abs(Robot.ypr[0] - targetAngle) < DEADBAND;
   }

	// Called once after isFinished returns true
	@Override
	protected void end() {
        driveSystem.stopMotors();
	}

}

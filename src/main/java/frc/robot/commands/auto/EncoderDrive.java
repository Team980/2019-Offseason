/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderDrive extends Command {
  
	private static final double minimumSpeed = 0.3;
	private double pCoefficient = 2;
	private double yawCorrectCoefficient = 4;

	private double distanceRemaining;

	private double distance;
	private double speed;

	public EncoderDrive(double distance) {
		this.distance = distance;

         requires(Robot.driveSystem);
	}

    @Override
	protected void initialize() {
		Robot.robotMap.leftDriveEncoder.reset();
		Robot.robotMap.rightDriveEncoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.driveSystem.getLeftDistance() <= Robot.driveSystem.getRightDistance()){
			distanceRemaining = distance - Robot.driveSystem.getLeftDistance();
		}
		else{
			distanceRemaining = distance - Robot.driveSystem.getRightDistance();

		}
		speed = pCoefficient * distanceRemaining / distance;
		if (speed < minimumSpeed){
			speed = minimumSpeed;
		}
		Robot.driveSystem.driveRobot(speed , -yawCorrectCoefficient * Robot.ypr[0] / 180);
    }
  
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (distanceRemaining < 1 && distanceRemaining > -1) {
			return true;
		} 
		else {
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveSystem.stopMotors();
	}

}

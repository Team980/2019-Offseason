/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.DriveSystem;

public class EncoderDrive extends Command {
  
	private static final double FORWARD_SPEED = 0.75;

	//private DriveSystem driveSystem;

	private double leftStartDistance;
	private double rightStartDistance;

	private double displacement;

	public EncoderDrive(double displacement) {
		this.displacement = displacement;

        //driveSystem = Robot.driveSystem;
        requires(Robot.driveSystem);
	}

    @Override
	protected void initialize() {
		leftStartDistance = Robot.driveSystem.getLeftDistance();
		rightStartDistance = Robot.driveSystem.getRightDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.driveSystem.driveRobot(Math.copySign(FORWARD_SPEED, displacement), 0);
    }
  
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (displacement > 0) {
			return leftStartDistance + displacement > Robot.driveSystem.getLeftDistance() 
				|| rightStartDistance + displacement > Robot.driveSystem.getRightDistance();
		} else {
			return leftStartDistance - displacement < Robot.driveSystem.getLeftDistance() 
				|| rightStartDistance - displacement < Robot.driveSystem.getRightDistance();
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveSystem.driveRobot(0, 0);
	}

}

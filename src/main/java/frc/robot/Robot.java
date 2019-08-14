/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.drive.TelopDrive;
import frc.robot.commands.lift.HoldLift;
import frc.robot.commands.wrist.HoldWrist;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {

	public static RobotMap robotMap;

	public static DriveSystem driveSystem;
	public static EndEffector endEffector;
	public static Lift lift;
	public static Wrist wrist;

	public static OI oi;

  	@Override
  	public void robotInit() {
		robotMap = new RobotMap();

		driveSystem = new DriveSystem();
		endEffector = new EndEffector();
		lift = new Lift();
		wrist = new Wrist();

		oi = new OI();

		// default commands()
		
		lift.setDefaultCommand(new HoldLift());
		wrist.setDefaultCommand(new HoldWrist());
  	}

  	@Override
  	public void robotPeriodic() {
		Scheduler.getInstance().run();
  	}

  	@Override
  	public void autonomousInit() {
		  
  	}

  	@Override
  	public void autonomousPeriodic() {

  	}

  	@Override
  	public void teleopInit() {
		driveSystem.setDefaultCommand(new TelopDrive());
	}

	@Override
  	public void teleopPeriodic() {

  	}

	@Override
	public void disabledInit() {
		// clear default commands
		var defaultCommand = driveSystem.getDefaultCommand();
		if (defaultCommand != null) {
			defaultCommand.cancel();
			defaultCommand = null;	
		}

		driveSystem.stopMotors();
		endEffector.stopMotors();
		lift.stopMotors();
		wrist.stopMotors();
	}

  	@Override
  	public void testPeriodic() {

	}

}

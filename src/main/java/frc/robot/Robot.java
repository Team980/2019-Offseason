/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Lift;

public class Robot extends TimedRobot {

	public static RobotMap robotMap;
	
	public static DriveSystem driveSystem;
	public static Lift liftSystem;
	
	public static OI oi;

	public static PigeonIMU pigeonIMU;

  	@Override
  	public void robotInit() {
		robotMap = new RobotMap();
		
		driveSystem = new DriveSystem();
		liftSystem = new Lift();

		oi = new OI();

		pigeonIMU = new PigeonIMU(1);
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

	}
	  
	@Override
  	public void teleopPeriodic() {

  	}

	@Override
	public void disabledInit() {
		driveSystem.stopMotors();
		liftSystem.stopMotors();
	}

  	@Override
  	public void testPeriodic() {

	}

}

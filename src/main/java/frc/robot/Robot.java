/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Lift;

public class Robot extends TimedRobot {

	public static RobotMap robotMap;

	public static DriveSystem driveSystem; //These don't have to be static
	public static Lift liftSystem;

  	@Override
  	public void robotInit() {
    	robotMap = new RobotMap();

		liftSystem = new Lift(); //drive system?
  	}

  	@Override
  	public void robotPeriodic() {

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
  	public void testPeriodic() {

	}

}

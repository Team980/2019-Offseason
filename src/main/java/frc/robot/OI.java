/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick throttle;
	public Joystick wheel; 
	public XboxController xBox;

	private static final double LIFT_DEADBAND = 0.1;

	public OI () { 
		throttle = new Joystick(0); 
		wheel = new Joystick(1);
		xBox = new XboxController(2); 
	}

	public double getMove() {
		return -throttle.getY();
	}

	public double getTurn() {
		return wheel.getX();
	}

	public double getLiftJoystickValue() {
		double value = -xBox.getY(Hand.kLeft);
		return (Math.abs(value) < LIFT_DEADBAND)? 0 : value; 
	}
}
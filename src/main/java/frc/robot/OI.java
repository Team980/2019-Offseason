/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.lift.ManualLiftControl;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick throttle;
	public Joystick wheel; 
	public XboxController xBox;

	private JoystickButton button; //what is the purpose of this?

	public OI () { 
		throttle = new Joystick(0);
		xBox = new XboxController(2);

		XboxController xBox = new XboxController(4); // what is this for?- this is the xbox control for manual lift
		XboxController.whenPressed(new ManualLiftControl()); //do you know what the error is for whenPressed? 
	}
}
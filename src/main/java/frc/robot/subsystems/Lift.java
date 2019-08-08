/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.commands.lift.HoldLift;

public class Lift extends Subsystem {

	private SpeedController liftMotor;
	private Encoder liftEncoder;

	public Lift() {
		liftMotor = Robot.robotMap.liftMotor;
		liftEncoder = Robot.robotMap.liftEncoder;
	}

	public void set(double input) {
		liftMotor.set(input);
	}

	public void setPosition(int targetPosition) {
		var currentPosition = liftEncoder.getRaw();

		if (currentPosition < targetPosition - 1000) {
			liftMotor.set(1);

		} else if (currentPosition > targetPosition + 1000) {
			liftMotor.set(-1);

		} else {
			liftMotor.set(0);
		}
	}

	public boolean getter(int targetPosition) { //This method doesn't make the lift do anything. Rather, it's a form of "get" method that returns true when you have reached the desired position.
		// when does setPosition() stop the lift? When we have reached the target position
		// Yes, so that is when this method should return true. 
		//so we are writing a getter?
		// In a sense. You're "getting" a property of the system that isn't actually represented by a variable.

		// try using an if statement here
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new HoldLift());
	}
 
	// TODO STOP/DISABLE METHOD?  - if added, every subsystem should have one...
}
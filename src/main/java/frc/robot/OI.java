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
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.configuration.BattleConfiguration;
import frc.robot.commands.configuration.CargoScoreConfiguration;
import frc.robot.commands.drive.StopDriveTrain;
import frc.robot.commands.lift.IncrementLiftPosition;
import frc.robot.commands.lift.SetLiftPosition;
import frc.robot.commands.wrist.SetWristAngle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick throttle;
	private Joystick wheel;
	XboxController xBox;

	private static final double MOVE_DEADBAND = 0.1;
	private static final double TURN_DEADBAND = 0.1;
	private static final double LIFT_DEADBAND = 0.1;
	private static final double WRIST_DEADBAND = 0.1;


	OI () {
		throttle = new Joystick(0);
		wheel = new Joystick(1);
		xBox = new XboxController(2);

		// configurations

		JoystickButton stopAuto = new JoystickButton(throttle, 7);
		stopAuto.whenPressed(new StopDriveTrain());
		
		JoystickButton aButton = new JoystickButton(xBox, 1); // ball floor pickup
		aButton.whenPressed(new SetWristAngle(200));
		aButton.whenPressed(new SetLiftPosition(0)); 

		JoystickButton bButton = new JoystickButton(xBox, 2); // low ball score
		bButton.whenPressed(new SetWristAngle(124.5));
		bButton.whenPressed(new SetLiftPosition(0.03));


		JoystickButton xButton = new JoystickButton(xBox, 3); // cargo ship dump
		xButton.whenPressed(new SetWristAngle(120.2)); // used to be 140.2
		xButton.whenPressed(new SetLiftPosition(0.96));


		JoystickButton yButton = new JoystickButton(xBox, 4); // mid ball score 
		yButton.whenPressed(new SetWristAngle(101.337)); 
 		yButton.whenPressed(new SetLiftPosition(0.9731));


		Trigger povRight = new Trigger() { // low hatch 
			@Override
			public boolean get() {
				return xBox.getPOV() == 90;
			}
		};
		povRight.whenActive(new SetWristAngle(56));
		povRight.whenActive(new SetLiftPosition(0));
//
//
//		Trigger povUp = new Trigger() { // rocket mid hatch
//			@Override
//			public boolean get() {
//				return xBox.getPOV() == 0;
//			}
//		};
//		povUp.whenActive(new SetWristAngle(23));
//		povUp.whenActive(new SetLiftPosition(0.35));


		JoystickButton leftThumb = new JoystickButton(xBox, 9); // battle configuration
		leftThumb.whenPressed(new SetWristAngle(287));
		leftThumb.whenPressed(new SetLiftPosition(0.97));


		// snag & release hatch
		JoystickButton leftBumper = new JoystickButton(xBox, 5); // snag hatch
		leftBumper.whenPressed(new IncrementLiftPosition(0.285));

		JoystickButton rightBumper = new JoystickButton(xBox, 6); // spit out hatch
		rightBumper.whenPressed(new IncrementLiftPosition(-0.285));

		
	}

	public double getSuckInSpeed() {
		return xBox.getTriggerAxis(Hand.kRight);
	}

	public double getSpitOutSpeed() {
		return xBox.getTriggerAxis(Hand.kLeft);
	}

	public double getMove() {
		return applyDeadband(-throttle.getY(), MOVE_DEADBAND);
	}

	public double getTurn() {
		return applyDeadband(wheel.getX(), TURN_DEADBAND);
	}

	public double getRawLiftJoystick() {
		return -xBox.getY(Hand.kRight);
	}

	public double getWristJoystickValue() {
		double value = xBox.getY(Hand.kLeft);
		return applyDeadband(value, WRIST_DEADBAND);
	}


	/**
     * Returns 0.0 if the given value is within the specified range around zero. The remaining range
     * between the deadband and 1.0 is scaled from 0.0 to 1.0.
     *
     * @param value    value to clip
     * @param deadband range around zero
     */
    public static double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    } 
}
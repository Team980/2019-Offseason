/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.CargoShipAuto;
import frc.robot.auto.CrossHabAuto;
import frc.robot.commands.drive.AutoShift;
import frc.robot.commands.drive.TelopDrive;
import frc.robot.commands.lift.HoldLift;
import frc.robot.commands.lift.ManualLiftControl;
import frc.robot.commands.wrist.HoldWrist;
import frc.robot.commands.wrist.ManualWristControl;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.*;

import java.util.Arrays;

public class Robot extends TimedRobot {

	public static RobotMap robotMap;

	public static DriveSystem driveSystem;
	public static EndEffector endEffector;
	public static Lift lift;
	public static Wrist wrist;
	public static double[] ypr = new double[3];

	public static OI oi;

	private SendableChooser<AutoChoice> autoChooser;

	private AutoShift autoShiftCommand;

	public static NetworkTable debugTable;

	@Override
  	public void robotInit() {
		robotMap = new RobotMap();

		driveSystem = new DriveSystem();
		endEffector = new EndEffector();
		lift = new Lift();
		wrist = new Wrist();

		oi = new OI();
		robotMap.liftEncoder.reset();

		// default commands
		
		
		ManualLiftControl liftControlCommand = new ManualLiftControl();
		ManualWristControl wristControlCommand = new ManualWristControl();
		JoystickButton startLiftAndWristManualControl = new JoystickButton(oi.xBox, 8); // start button
		startLiftAndWristManualControl.whenPressed(liftControlCommand);
		startLiftAndWristManualControl.whenPressed(wristControlCommand);

		JoystickButton stopLiftAndWristManualControl = new JoystickButton(oi.xBox, 7); // back button
		stopLiftAndWristManualControl.cancelWhenPressed(liftControlCommand);
		stopLiftAndWristManualControl.cancelWhenPressed(wristControlCommand);

		// shuffleboard
		autoChooser = new SendableChooser<>();
		autoChooser.setName("Choose Autonomous");

		autoChooser.setDefaultOption(AutoChoice.CARGO_SHIP_AUTO.descriptor, AutoChoice.CARGO_SHIP_AUTO);
		autoChooser.addOption(AutoChoice.CROSS_HAB_AUTO.descriptor, AutoChoice.CROSS_HAB_AUTO);

		SmartDashboard.putData(autoChooser);

		debugTable = NetworkTableInstance.getDefault().getTable("debug");
	}

  	@Override
  	public void robotPeriodic() {
		robotMap.imu.getYawPitchRoll(ypr);
		
		debugTable.getEntry("wrist angle").setNumber(wrist.currentAngle());
		debugTable.getEntry("lift scaled height").setNumber(lift.currentPosition());
		debugTable.getEntry("lift encoder ticks").setNumber(robotMap.liftEncoder.getRaw());
		debugTable.getEntry("yaw").setNumber(ypr[0]);
		debugTable.getEntry("pitch").setNumber(ypr[1]);
		debugTable.getEntry("roll").setNumber(ypr[2]);


		Scheduler.getInstance().run();
  	}

  	@Override
  	public void autonomousInit() {
		// reset imu
		robotMap.imu.setYaw(0);

		// // start up auto command
  		// AutoChoice autoChoice = autoChooser.getSelected();
  		// if (autoChoice == null) {
		// 	autoChoice = AutoChoice.CARGO_SHIP_AUTO; // default value
		// }
		AutoChoice autoChoice = AutoChoice.CARGO_SHIP_AUTO; // default value
		Command autoCommand = autoChoice.command;
  		autoCommand.start();
  	}

  	@Override
  	public void autonomousPeriodic() {
  	}

  	@Override
  	public void teleopInit() {
		// auto shifting
		autoShiftCommand = new AutoShift(); // TODO: check if there is a better way to start & cancel this command
		autoShiftCommand.start();

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
			driveSystem.setDefaultCommand(null);
		}

		if (autoShiftCommand != null) {
			autoShiftCommand.cancel();
		}

		driveSystem.stopMotors();
		endEffector.stopMotors();
		lift.stopMotors();
		wrist.stopMotors();
	}

  	@Override
  	public void testPeriodic() {
	}

	enum AutoChoice {
		CARGO_SHIP_AUTO(new CargoShipAuto(), "cargo ship auto"),
		CROSS_HAB_AUTO(new CrossHabAuto(), "just cross hab line");

  		Command command;
  		String descriptor;

  		AutoChoice(Command command, String descriptor) {
  			this.command = command;
  			this.descriptor = descriptor;
		}
	}
}

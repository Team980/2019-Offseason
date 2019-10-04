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
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.CargoShipAuto;
import frc.robot.auto.CrossHabAuto;
import frc.robot.commands.drive.AutoShift;
import frc.robot.commands.drive.TelopDrive;
import frc.robot.commands.lift.ManualLiftControl;
import frc.robot.commands.wrist.CalcWristRate;
import frc.robot.commands.wrist.ManualWristControl;
import frc.robot.subsystems.*;


public class Robot extends TimedRobot {

	public static RobotMap robotMap;

	public static DriveSystem driveSystem;
	public static PIDLeftDrive pidLeftDrive;
	public static PIDRightDrive pidRightDrive;
	public static EndEffector endEffector;
	public static PIDLift pidLift;
	public static PIDPositionalLift pidPositionalLift;
	public static PIDWrist pidWrist;
	public static double[] ypr;

	public static OI oi;

	private SendableChooser<AutoChoice> autoChooser;

	private AutoShift autoShiftCommand;

	public Command wristRate;

	public static NetworkTable debugTable;



	@Override
  	public void robotInit() {
		robotMap = new RobotMap();

		driveSystem = new DriveSystem();
		pidLeftDrive = new PIDLeftDrive();
		pidRightDrive = new PIDRightDrive();
		endEffector = new EndEffector();
		pidLift = new PIDLift();
		pidPositionalLift = new PIDPositionalLift();
		pidWrist = new PIDWrist();
		ypr = new double[3];
		oi = new OI();
		robotMap.liftEncoder.reset();

		// default commands
		
		// shuffleboard
		autoChooser = new SendableChooser<>();
		autoChooser.setName("Choose Autonomous");

		autoChooser.setDefaultOption(AutoChoice.CARGO_SHIP_AUTO.descriptor, AutoChoice.CARGO_SHIP_AUTO);
		autoChooser.addOption(AutoChoice.CROSS_HAB_AUTO.descriptor, AutoChoice.CROSS_HAB_AUTO);

		SmartDashboard.putData(autoChooser);

		debugTable = NetworkTableInstance.getDefault().getTable("debug");

		Command manualLiftCommand = new ManualLiftControl(); //UNDO
		Command manualWristCommand = new ManualWristControl();
		JoystickButton startLiftAndWristManualControl = new JoystickButton(oi.xBox, 8); // start button
		startLiftAndWristManualControl.whenPressed(manualLiftCommand);
		startLiftAndWristManualControl.whenPressed(manualWristCommand);
		JoystickButton stopLiftAndWristManualControl = new JoystickButton(oi.xBox, 7); // back button
		stopLiftAndWristManualControl.cancelWhenPressed(manualLiftCommand);
		stopLiftAndWristManualControl.cancelWhenPressed(manualWristCommand);

		wristRate = new CalcWristRate();
		wristRate.start();
	}

  	@Override
  	public void robotPeriodic() {
		//robotMap.imu.getYawPitchRoll(ypr);
		
		debugTable.getEntry("wrist angle").setNumber(pidWrist.currentAngle()); 
		debugTable.getEntry("wrist rate").setNumber(robotMap.wristPotentiometer.getRate()); 
		debugTable.getEntry("lift height").setNumber(robotMap.liftEncoder.getDistance());
		debugTable.getEntry("lift rate").setNumber(robotMap.liftEncoder.getRate());
		debugTable.getEntry("yaw").setNumber(ypr[0]);
		debugTable.getEntry("pitch").setNumber(ypr[1]);
		debugTable.getEntry("roll").setNumber(ypr[2]);
	
		Scheduler.getInstance().run();

  	}

  	@Override
  	public void autonomousInit() {
		driveSystem.setDefaultCommand(new TelopDrive());

		// reset imu
		//robotMap.imu.setYaw(0); UNDO

		// start up auto command
  		// AutoChoice autoChoice = autoChooser.getSelected();
  		// if (autoChoice == null) {
		// 	autoChoice = AutoChoice.CARGO_SHIP_AUTO; // default value
		// }
		AutoChoice autoChoice = AutoChoice.CROSS_HAB_AUTO; // default value
		Command autoCommand = autoChoice.command;
		autoCommand.start();
		  
  	}

  	@Override
  	public void autonomousPeriodic() {
  	}

  	@Override
  	public void teleopInit() {
		// auto shifting
		autoShiftCommand = new AutoShift();
		autoShiftCommand.start();

		JoystickButton forceLow = new JoystickButton(oi.throttle, 1);
		forceLow.whileActive(new InstantCommand(() -> {
			autoShiftCommand.cancel();
			driveSystem.setGear(true);
		}));
		forceLow.whenReleased(autoShiftCommand);


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
		pidLift.stopMotors();
		pidWrist.stopMotors();
	}

	@Override
	public void disabledPeriodic() {
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

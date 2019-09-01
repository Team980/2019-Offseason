/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Robot;
import frc.robot.sensors.Potentiometer;
import frc.robot.commands.wrist.HoldWrist;

//import frc.robot.Util;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDSourceType;

public class Wrist extends Subsystem {

    private static final double DEADBAND = 10;

    // the minimum angles so we don't crash into ourselves
    private static final double MINIMUM_ANGLE = 30; // TODO NEED TO GET THE NEW NUMBERS AFTER WE CHANGED THE POTENTIOMETER
	private static final double MAXIMUM_ANGLE = 288;


    private SpeedController wristMotor;
	private Potentiometer wristPotentiometer;

    //private static final double MAX_SPEED = 0.5;
	//private static final double MAX_DIFFERENCE = MAXIMUM_ANGLE - MINIMUM_ANGLE;
	//private static final double MIN_SPEED = 0.5;
	//private static final double MAX_SPEED_DEGREES_PER_SECOND = 10;
	
	//private PIDController pidController; 

	public Wrist()  {
        wristPotentiometer = Robot.robotMap.wristPotentiometer;
		wristMotor = Robot.robotMap.wristMotor;
		

		/*wristPotentiometer.setPIDSourceType(PIDSourceType.kRate);
		pidController = new PIDController(0.0001, 0, 0, wristPotentiometer, wristMotor); // TODO: set constants
		pidController.setName("wrist pid controller");
		pidController.enable();

		LiveWindow.add(pidController);*/
	}


	public void rawSet(double input) {
		wristMotor.set(input);
	}

	public void set(double input) {
	 	if ((input < 0 && currentAngle() > MINIMUM_ANGLE) || (input > 0 && currentAngle() < MAXIMUM_ANGLE)) {
	 		wristMotor.set(input);
	 	} // the softstop check needs to be here where the motor is running, this way both manual and automation use the soft stop protection

		else {
	 		wristMotor.set(0);
	 	}
	}

	public void moveTowards(double targetAngle) {
        double difference = targetAngle - currentAngle();

        double input = 1.5 * difference / 260 ; // figure out which velocity we want to be

	 	if (isAtTargetAngle(targetAngle) /*|| isInExclusionZone()*/) {
	 		input = 0;
		} 
		 
		else if (targetAngle > 270 && Math.abs(difference) < 30) { // TODO: hack until pid
	 		input = 0.6;
	 	}

	 	set(input);
	}

	public boolean isAtTargetAngle(double targetAngle) {
		double distance = Math.abs(targetAngle - currentAngle());
		Robot.debugTable.getEntry("distance").setNumber(distance);

		return distance < DEADBAND;
	}

	public double currentAngle() {
		return wristPotentiometer.getAngle();
	}

	public void stopMotors() {
		//pidController.setSetpoint(0);
		//wristMotor.stopMotor();
		wristMotor.set(0);
	}

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new HoldWrist());
    }

	/*public void setPidEnabled(boolean enabled) {
		pidController.setEnabled(enabled);
	}*/

	/*@Override
	public void periodic() {
		Robot.debugTable.getEntry("wrist pid get").setNumber(wristPotentiometer.pidGet());
	}*/

	/*public void moveTowards(double targetAngle) {
        double difference = targetAngle - currentAngle();


		if (isAtTargetAngle(targetAngle)) {
			//pidController.setSetpoint(0);
			//setVelocity(0);
		} else {
			if (difference > 0) {
				pidController.setSetpoint(10);
			} else {
				pidController.setSetpoint(-10);
			}

			//input = difference / MAX_DIFFERENCE;

			//setVelocity(difference / MAX_DIFFERENCE);
		}

		//Robot.debugTable.getEntry("wrist input").setNumber(input);
	}*/

	/*public double getVelocity() {
		return wristPotentiometer.pidGet();
	}*/

	// accepts scaled velocity
	/*public void setVelocity(double scaledVelocity) {
  		pidController.setSetpoint(scaledVelocity*MAX_SPEED_DEGREES_PER_SECOND); // TODO: Exclusion
	}*/

    /*private boolean isInExclusionZone() {
        double currentAngle = currentAngle();
        return currentAngle < MINIMUM_ANGLE || currentAngle > MAXIMUM_ANGLE;
    }*/

}
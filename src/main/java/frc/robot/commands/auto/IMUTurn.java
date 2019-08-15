/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Util;
import frc.robot.sensors.BetterIMU;
import frc.robot.subsystems.DriveSystem;

/**
 * Turns the robot to an absolute angle as specified by the IMU.
 * The complexity in this class comes from the fact that this class is indended to provide proportional control. Additionally,
 * it should work with the fact that the IMU can report angles greater than 360.
 */
public class IMUTurn extends Command {

    private static final double DEADBAND = 2.0; // degrees

    private static final double MAX_TURN_SPEED = 0.75;

    private DriveSystem driveSystem;
    private BetterIMU imu;

    private double targetAngle;

	public IMUTurn(double targetAngle) {
        driveSystem = Robot.driveSystem;
        imu = Robot.robotMap.imu;

        this.targetAngle = normalize(targetAngle);

        requires(driveSystem);
    }

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        double currentAngle = currentAngleNormalized();

        // lets decide whether we want to spin clockwise or counterclockwise
        double clockwiseDistance = modularSubtract(currentAngle, targetAngle);
        double counterclockwiseDistance = modularSubtract(targetAngle, currentAngle);

        // now, we just have to calculate the value of our turn
        double distance = Math.min(clockwiseDistance, counterclockwiseDistance);
        double turnMagnitude = Util.map(distance, 0, 180, 0, MAX_TURN_SPEED); // proportional control

        double turn = clockwiseDistance < counterclockwiseDistance? turnMagnitude : -turnMagnitude; // FIXME: signs

        driveSystem.driveRobot(0, turn);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(currentAngleNormalized() - targetAngle) < DEADBAND;
    }

    @Override
    protected void end() {
        driveSystem.stopMotors();
    }

    private double currentAngleNormalized() {
	    return normalize(imu.getYaw());
    }

    // TODO: check this logic
	private static double normalize(double a) {
	    a %= 360;
	    return a < 0? a+360 : a;
    }

    private static double modularSubtract(double a, double b) {
	    double out = a>b? a-b : a+360 - b;
	    return normalize(out);
    }
}
//
//public class IMUTurn extends Command {
//
//    private static final double DEADBAND = 1.0; // degrees
//
//    private static final double ABSOLUTE_TURN_SPEED = 0.35;
//
//    private DriveSystem driveSystem;
//    private BetterIMU imu;
//
//    private double targetAngle;
//
//	public IMUTurn(double targetAngle) {
//        driveSystem = Robot.driveSystem;
//        imu = Robot.robotMap.imu;
//
//        this.targetAngle = targetAngle;
//
//        requires(driveSystem);
//    }
//
//	// Called repeatedly when this Command is scheduled to run
//	@Override
//	protected void execute() {
//        // figure out if we want to turn clockwise or counterclockwise
//        driveSystem.driveRobot(0, Math.copySign(ABSOLUTE_TURN_SPEED, imu.getAngle() - targetAngle));
//    }
//
//	// Make this return true when this Command no longer needs to run execute()
//	@Override
//	protected boolean isFinished() {
//        return Math.abs(imu.getAngle() - targetAngle) < DEADBAND;
//    }
//
//	// Called once after isFinished returns true
//	@Override
//	protected void end() {
//         driveSystem.stopMotors();
//	}
//
//}

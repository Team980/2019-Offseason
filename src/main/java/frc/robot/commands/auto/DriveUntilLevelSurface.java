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

public class DriveUntilLevelSurface extends Command {

    private static final double PITCH_DEADBAND = 2.0; // degrees

    private DriveSystem driveSystem;
    private BetterIMU imu;

    private double move;

    public DriveUntilLevelSurface(double move) {
        driveSystem = Robot.driveSystem;
        imu = Robot.robotMap.imu;

        this.move = move;

        requires(driveSystem);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        driveSystem.driveRobot(move, 0);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(imu.getPitch()) < PITCH_DEADBAND; // pitch returned by imu is 0 when upright
    }

    @Override
    protected void end() {
        driveSystem.stopMotors();
    }
}
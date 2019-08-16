/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Util;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.DriveSystem;


public class SpinUntilBallSeen extends Command {
    private static final double TURN_SPEED = 0.5;

    private DriveSystem driveSystem;
    private Limelight limelight;

    SpinUntilBallSeen() {
        driveSystem = Robot.driveSystem;
        limelight = Robot.robotMap.limelight;

        requires(driveSystem);
    }

    @Override
    protected void initialize() {
        limelight.setPipelineIndex(Limelight.BALL_TARGET_PIPELINE_INDEX);
    }

    /**
     * This method doesn't exist in driveSystem for a simple reason -> there is no reason for the driveSystem to keep state
     * (hasSeenTarget, etc) about this commands functioning. Additionally, there is no reason for driveSystem to know that the limelight exists.
     *
     * Purpose: drives toward the ball until it cannot se it anymore. Then, it drives forward some feet
     */
    @Override
    protected void execute() {
        driveSystem.driveRobot(0, TURN_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return limelight.hasTarget(); // we finish when we see the ball
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        driveSystem.stopMotors();
    }
}
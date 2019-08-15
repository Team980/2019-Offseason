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
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Limelight;

public class DriveTowardsBallUntilBallOutOfView extends Command {
    private static final double FORWARD_SPEED = 0.5;

    private DriveSystem driveSystem;
    private Limelight limelight;

    DriveTowardsBallUntilBallOutOfView() {
        driveSystem = Robot.driveSystem;
        limelight = Robot.limelight;

        requires(driveSystem);
        requires(limelight);
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
        double xOffset = limelight.getTargetX();
        double turn = Util.map(xOffset, -29, 29, -1, 1);

        driveSystem.driveRobot(FORWARD_SPEED, turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !limelight.hasTarget(); // we finish when we lose the target, FIXME: could finish when area becomes too large
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        driveSystem.stopMotors();
    }
}
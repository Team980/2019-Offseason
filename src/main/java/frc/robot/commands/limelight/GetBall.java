/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.EncoderDrive;

public class GetBall extends CommandGroup {
    public GetBall() {
        addSequential(new SpinUntilBallSeen());
        addSequential(new DriveTowardsBallUntilBallOutOfView());
        addSequential(new EncoderDrive(1.5));
        // TODO: move the wrist and endEffector
    }
}
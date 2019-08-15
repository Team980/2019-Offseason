
package frc.robot.commands.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.EncoderDrive;
import frc.robot.commands.lift.SetLiftPosition;
import frc.robot.commands.wrist.SetWristAngle;

public class BallFloorPickupConfiguration extends CommandGroup {
    public BallFloorPickupConfiguration() {
        addParallel(new SetLiftPosition(0.3)); // FIXME: add in real values
        addParallel(new SetWristAngle(100));
    }
}
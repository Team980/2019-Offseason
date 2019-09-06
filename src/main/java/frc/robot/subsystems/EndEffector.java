/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.end_effector.RunIntake;

public class EndEffector extends Subsystem {

    private SpeedController intakeMotor;

	public EndEffector()  {
        ///intakeMotor = Robot.robotMap.endEffectorIntakeMotor; UNDO
    }

    public void set(double input) {
        intakeMotor.set(input);
    }

    public void stopMotors() {
        intakeMotor.stopMotor();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RunIntake());
    }
}
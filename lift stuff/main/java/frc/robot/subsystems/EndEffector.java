package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;


public class EndEffector {
    public static final double INTAKE_SPEED = 0.5;
    public static final double INTAKE_STOP_SPEED = 0;
    public static final double SPIT_OUT_SPEED = -0.5;

    public EndEffector() {
        Robot.robotMap.intakeMotor.setName("End Effector", "Intake Motor");
    }

    public void setIntake(double speed) {
        Robot.robotMap.intakeMotor.set(speed);
    }

    public void disable() {
        Robot.robotMap.intakeMotor.stopMotor();
    }
}
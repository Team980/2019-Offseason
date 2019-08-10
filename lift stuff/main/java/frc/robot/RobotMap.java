/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.CounterBase;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //left drive
  /*private WPI_TalonSRX leftTopMotor;
  private WPI_TalonSRX leftFrontMotor;
  private WPI_TalonSRX leftBackMotor;*/

  private WPI_VictorSPX leftTopMotor;
  private WPI_VictorSPX leftFrontMotor;
  private WPI_VictorSPX leftBackMotor;


  public SpeedControllerGroup leftDrive;
  public Encoder leftEncoder;
  public PIDController leftController;

  //right drive
  /*private WPI_TalonSRX rightTopMotor;
  private WPI_TalonSRX rightFrontMotor;
  private WPI_TalonSRX rightBackMotor;*/

  private WPI_VictorSPX rightTopMotor;
  private WPI_VictorSPX rightFrontMotor;
  private WPI_VictorSPX rightBackMotor;

  public SpeedControllerGroup rightDrive;
  public Encoder rightEncoder;
  public PIDController rightController;

  public Solenoid shifterSolenoid;

//lift  
  public Encoder liftEncoder;
  public WPI_TalonSRX liftController;
  public WPI_TalonSRX intakeMotor;
  public WPI_TalonSRX wristMotor;

  public PigeonIMU imu;
  public double[] ypr;

  public RobotMap() {
    /*leftTopMotor = new WPI_TalonSRX(0);
    leftTopMotor.setInverted(true);
    leftFrontMotor = new WPI_TalonSRX(1);
    leftBackMotor = new WPI_TalonSRX(2);*/

    leftTopMotor = new WPI_VictorSPX(3);
    leftTopMotor.setInverted(true);
    leftFrontMotor = new WPI_VictorSPX(1);
    leftBackMotor = new WPI_VictorSPX(4);

    leftDrive = new SpeedControllerGroup(leftTopMotor, leftFrontMotor, leftBackMotor);
    leftDrive.setName("Drive System", "Left Speed Controllers");

    leftEncoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X);//need to figure out where this is plugged in
    leftEncoder.setDistancePerPulse((Math.PI * (4 / 12)) / 2048);
    leftEncoder.setPIDSourceType(PIDSourceType.kRate);
    leftEncoder.setName("Drive System", "Left Encoder");

    leftController = new PIDController(.1, 0, 0, 0, leftEncoder, leftDrive);
    leftController.setInputRange(-17, 17);
    leftController.setPercentTolerance(.01);
    leftController.setName("Drive System", "Left PID Controller");
  
    /*rightTopMotor = new WPI_TalonSRX(3);
    rightTopMotor.setInverted(true);
    rightFrontMotor = new WPI_TalonSRX(4);
    rightBackMotor = new WPI_TalonSRX(5);*/

    rightTopMotor = new WPI_VictorSPX(2);
    rightTopMotor.setInverted(true);
    rightFrontMotor = new WPI_VictorSPX(0);
    rightBackMotor = new WPI_VictorSPX(5);

    rightDrive = new SpeedControllerGroup(rightTopMotor, rightFrontMotor, rightBackMotor);
    rightDrive.setInverted(true);
    rightDrive.setName("Drive System", "Right Speed Controllers");

    rightEncoder = new Encoder(4, 5, true, CounterBase.EncodingType.k4X);
    rightEncoder.setDistancePerPulse((Math.PI * (4 / 12)) / 2048);
    rightEncoder.setPIDSourceType(PIDSourceType.kRate);
    //TODO make sure I don't need to reverse this - just to be safe
    rightEncoder.setName("Drive System", "Right Encoder");

    rightController = new PIDController(.1, 0, 0, 0, rightEncoder, rightDrive);
    rightController.setInputRange(-17, 17);
    rightController.setPercentTolerance(.01);
    rightController.setName("Drive System", "Right PID Controller");

    shifterSolenoid = new Solenoid(0);
    shifterSolenoid.setName("Drive System", "Shifter Solenoid");

    
    //lift
   /* liftController = new WPI_TalonSRX(11); // TODO: get real values
    liftEncoder = new Encoder(1, 2, false, CounterBase.EncodingType.k4X);
    intakeMotor = new WPI_TalonSRX(21); 
    wristMotor = new WPI_TalonSRX(10);*/

    imu = new PigeonIMU(0);
    ypr = new double[3];
  }

  public void setMotorSafetyEnabled(boolean isMotorSafetyEnabled) {
    leftTopMotor.setSafetyEnabled(isMotorSafetyEnabled);
    leftFrontMotor.setSafetyEnabled(isMotorSafetyEnabled);
    leftBackMotor.setSafetyEnabled(isMotorSafetyEnabled);
    rightTopMotor.setSafetyEnabled(isMotorSafetyEnabled);
    rightFrontMotor.setSafetyEnabled(isMotorSafetyEnabled);
    rightBackMotor.setSafetyEnabled(isMotorSafetyEnabled);


    
  }
  public double[] getYpr() {
    return ypr; 
  }

}

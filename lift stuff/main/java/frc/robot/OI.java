/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  public Joystick driveStick;
  public Joystick driveWheel;

  public XboxController controller;
  
  public JoystickButton liftHigh;
  public JoystickButton liftMid;
  public JoystickButton liftLow;

  public JoystickButton manualLowGear;
  public JoystickButton manualHighGear;
  public JoystickButton autoGear;

  

  public OI() {
    
    driveStick = new Joystick(0);
    driveWheel = new Joystick(1);

    controller = new XboxController(2);

    liftHigh = new JoystickButton(controller,0);
    
    liftMid = new JoystickButton(controller,1);
    liftLow = new JoystickButton(controller,2);


  }

  public double getThrottle(){
    return -driveStick.getY();
  }

  public double getSteering(){
    return .75 * driveWheel.getX();
  }


}

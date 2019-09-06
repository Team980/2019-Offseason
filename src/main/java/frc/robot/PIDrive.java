package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Like DifferentialDrive, but for cool people
 */
public class PIDrive {
    private static final double P = 0.00001;
    private static final double I = 0;
    private static final double D = 0;

    private static final double MAX_VELOCITY = 4.0; 
        
    private PIDController leftController;
    private PIDController rightController;
        

    public PIDrive(Encoder leftEncoder, Encoder rightEncoder, SpeedController leftMotor, SpeedController rightMotor) {
        leftEncoder.setPIDSourceType(PIDSourceType.kRate);
        rightEncoder.setPIDSourceType(PIDSourceType.kRate);
        
        leftController = new PIDController(P, I, D, leftEncoder, leftMotor);
        rightController = new PIDController(P, I, D, rightEncoder, rightMotor);

        leftController.enable();
        rightController.enable();
    }

    /**
     * left & right are from -1 to 1
     */
    public void tankDrive(double left, double right) {
        left = limit(left);
        right = limit(right);
        leftController.setSetpoint(left * MAX_VELOCITY);
        rightController.setSetpoint(right * MAX_VELOCITY);                
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        xSpeed = Math.copySign(xSpeed*xSpeed, xSpeed); // squaring inputs
        zRotation = Math.copySign(zRotation*zRotation, zRotation);

        double leftMotorOutput;
        double rightMotorOutput;
    
        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
          // First quadrant, else second quadrant
          if (zRotation >= 0.0) {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
          } else {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
          }
        } else {
          // Third quadrant, else fourth quadrant
          if (zRotation >= 0.0) {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
          } else {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
          }
        }
    
        tankDrive(leftMotorOutput, rightMotorOutput);
    }

    public void stopMotor() {
        leftController.setSetpoint(0);
        rightController.setSetpoint(0);
        // should we disable the pid controllers?
    }

    public static double limit(double x) {
        if (x < -1) {
            return -1;
        } else if (x > 1) {
            return 1;
        } else {
            return x;
        }
    }
}
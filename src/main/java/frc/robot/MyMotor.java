package frc.robot;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.ExternalFollower;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

/**
 * Motor Controller wrapper class made specifically for control in this context
 * Allows me to be lazy with how I setup Robot.java :)
 * 
 * @author William Hiers
 * @since 1/29/24
 */
public class MyMotor {
    private static final CANSparkLowLevel.MotorType MOTOR_TYPE = MotorType.kBrushless; // Just so I don't have to do the
                                                                                       // call every time
    private CANSparkMax motor;

    /**
     * Creates the MyMotor object
     * 
     * @param canID The CAN Bus ID of the Spark Max motor controller for NEO Motors
     */
    public MyMotor(int canID) {
        setCAN(canID);
    }

    public void setCAN(int canID) {
        // System.out.println("Seting CAN ID");
        if (canID <= 0 || canID > 999) { // Set the CANSparkMax to null so the program doesn't crash or yell
            motor = null; // There's some sort of max canID that can be set, but no clue what it is, so
                          // 999 for now
            return;
        }

        if (motor == null) {
            // System.out.println("1.) Setting Motor CAN");
            motor = new CANSparkMax(canID, MOTOR_TYPE);
            // System.out.println("2.) Successful Set CAN");
            return;
        }

        if (canID != motor.getDeviceId()) { // Set the motor object to a new motor, safely (ish)
            motor.set(0);
            double power = motor.get();
            motor = new CANSparkMax(canID, MOTOR_TYPE);
            motor.set(power);
            return;
        }
    }

    /**
     * Pulls the CAN ID of the motor (crazy)
     * 
     * @return CAN ID of the motor, returns 0 if motor controller is null
     */
    public int getCAN() {
        if (motor == null) { // If the motor was null, doing the normal return would break it
            return 0;
        }
        return motor.getDeviceId();
    }

    /**
     * Sets the power using the .set method of the motor controller, does nothing if
     * motor controller is null
     * 
     * @param power The power from -1 to 1 to set the motor power to
     */
    public void setPower(double power) {
        // System.out.println("Setting Power");
        if (power > 1 || power < -1) {
            return;
        }
        if (motor == null) {
            return;
        }
        if (power == motor.get()) {
            return;
        }
        motor.set(power);
    }

    /**
     * Returns the current running power of the motor using the .get method of the
     * motor controller
     * 
     * @return Returns the current power of the motor, but defaults to 0.0 if there
     *         is no controller
     */
    public double getPower() {
        if (motor == null) {
            return 0;
        }
        return motor.get();
    }

    public void stopMotor() {
        // motor.stopMotor();
        // System.out.println("Stopping Motor");
        if (motor == null) {
            return;
        }
        motor.set(0);
    }

    /**
     * Sets the idle mode of the motor
     * 
     * @param idleMode true = brake, false = coast
     */
    public void setIdleMode(boolean idleMode) {
        if (idleMode) {
            motor.setIdleMode(IdleMode.kBrake);
        } else {
            motor.setIdleMode(IdleMode.kCoast);
        }
    }

    /**
     * <p>
     * Returns the idle mode as a boolean
     * </p>
     * true = brake, false = coast
     */
    public boolean getIdleMode() {
        if (motor.getIdleMode() == IdleMode.kBrake) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets whether or not motor input should be inverted
     * 
     * @param isInverted true = inverted, false = normal
     */
    public void setInverted(boolean isInverted) {
        motor.setInverted(isInverted);
    }

    /**
     * Sets the motor that this motor should follow
     * 
     * @param canID
     */
    public void setFollower(int canID) {
        motor.follow(ExternalFollower.kFollowerSpark, canID);
    }

    /**
     * Sets the motor that this motor should follow
     * 
     * @param canID      CAN bus ID of the motor that this should follow
     * @param isInverted Whether or not this motor should be inverted compared to
     *                   the following motor
     */
    public void setFollower(int canID, boolean isInverted) {
        motor.follow(ExternalFollower.kFollowerSpark, canID, isInverted);
    }

    /**
     * Gets the CANSparkMax object of the motor being controlled (not recommended).
     * This is only here to ensure that you have some way to access it since there
     * can only be one CANSparkMax object per motor.
     * 
     * @return CANSparkMax of the motor
     */
    public CANSparkMax getMotor() {
        return motor;
    }

}

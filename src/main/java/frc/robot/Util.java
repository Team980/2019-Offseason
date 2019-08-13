package frc.robot;

public class Util {

    public static final double TAU = 2 * Math.PI;

    public static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        // this function is the spicy sauce
        // https://www.arduino.cc/reference/en/language/functions/math/map/
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }
}
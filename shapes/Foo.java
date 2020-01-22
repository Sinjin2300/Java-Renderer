package shapes;

import java.awt.Color;

public class Foo {
    public static final Foo DUMMY = new Foo(Double.POSITIVE_INFINITY, 0.9, false);

    public boolean isVisible;
    public double distance;
    private double hue;


    private Foo(double distance, double hue, boolean isVisible) {
        this.distance = distance;
        this.hue = hue;
    }


    public Foo(double distance, double hue) {
        this.distance = distance;
        this.hue = hue;
        this.isVisible = true;

    }

    public boolean isVisible() {
        return isVisible;
    }

    public Color toColor() {
        int color = Color.HSBtoRGB((float)hue, 1, (float)map(distance, 0.0, 50.0, 1.0, 0.0));
        return new Color(color);
    }

    public static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        // this function is the spicy sauce
        // https://www.arduino.cc/reference/en/language/functions/math/map/
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }
}
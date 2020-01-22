package shapes;

import shapes.Foo;

public class Circle implements GameObject {
    private double h;
    private double k;
    private double r;
    private double hue;

    public Circle(double h, double k, double r, double hue) {
        this.h = h;
        this.k = k;
        this.r = r;
        this.hue = hue;
    }

    public Foo intersect(double playerX, double playerY, double viewAngle) {
        double sin = Math.sin(viewAngle);
        double cos = Math.cos(viewAngle);

        double b = k*sin - h*cos + playerX*cos - playerY*sin;
        double descriminant = b*b - playerX*playerX - playerY*playerY + 2*h*playerX + 2*k*playerY - k*k - h*h + r*r;
        if (descriminant < 0) {
            return Foo.DUMMY;
        } else {
            return new Foo(-b - Math.sqrt(descriminant), hue);
        }
    }
}
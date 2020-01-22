package shapes;

import shapes.Foo;
public interface GameObject {
    public abstract Foo intersect(double playerX, double playerY, double viewAngle);
}
package shapes;

import shapes.Foo;

public class HorizontalLine implements GameObject {
    double startX;
    double endX;
    double y;
    double hue;

    public HorizontalLine(double startX, double endX, double y, double hue) {
        this.startX = startX;
        this.endX = endX;
        this.y = y;
        this.hue = hue;
    }

    public Foo intersect(double playerX, double playerY, double viewAngle) {
        var dist = (playerY-y)/Math.sin(viewAngle);
        var x = playerX + dist*Math.cos(viewAngle);
        if (x<=endX && x>=startX) {
            return new Foo(dist, hue);
        } else {
            return Foo.DUMMY;
        }
    }
}

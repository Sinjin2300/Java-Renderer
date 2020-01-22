package shapes;

public class VerticalLine implements GameObject {
    private double startY;
    private double endY;
    private double x;
    private double hue;

    public VerticalLine(double startY, double endY, double x, double hue) {
        this.startY = startY;
        this.endY = endY;
        this.x = x;
        this.hue = hue;
    }

    public Foo intersect(double playerX, double playerY, double viewAngle) {
        var dist = (x - playerX)/Math.cos(viewAngle);
        var y = playerY - dist*Math.sin(viewAngle);
        if (y<=endY && y>=startY) {
            return new Foo(dist, hue);
        } else {
            return Foo.DUMMY;
        }
    }
}
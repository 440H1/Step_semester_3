public class BasicDrawingCanvas {

    public static abstract class Shape {
        private static int counter = 0;
        private final String shapeId;

        protected Shape() {
            counter++;
            shapeId = "Shape-" + counter;
        }

        public abstract double calculateArea();

        public abstract void scale(double factor);

        public abstract void scale(double xFactor, double yFactor);

        public String getShapeId() {
            return shapeId;
        }
    }

    public static class CircleShape extends Shape {
        private double radius;

        public CircleShape(double radius) {
            if (radius <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public void scale(double factor) {
            if (factor <= 0) {
                throw new IllegalArgumentException("Scale factor must be positive");
            }
            radius *= factor;
        }

        @Override
        public void scale(double xFactor, double yFactor) {
            if (xFactor <= 0 || yFactor <= 0) {
                throw new IllegalArgumentException("Scale factors must be positive");
            }
            scale(xFactor);
        }
    }

    public static class SquareShape extends Shape {
        private double side;

        public SquareShape(double side) {
            if (side <= 0) {
                throw new IllegalArgumentException("Side must be positive");
            }
            this.side = side;
        }

        @Override
        public double calculateArea() {
            return side * side;
        }

        @Override
        public void scale(double factor) {
            if (factor <= 0) {
                throw new IllegalArgumentException("Scale factor must be positive");
            }
            side *= factor;
        }

        @Override
        public void scale(double xFactor, double yFactor) {
            if (xFactor <= 0 || yFactor <= 0) {
                throw new IllegalArgumentException("Scale factors must be positive");
            }
            scale(xFactor);
            scale(yFactor);
        }
    }

    public static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.println(c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);

        System.out.println(c.getShapeId());
        System.out.println(sq.getShapeId());
    }
}

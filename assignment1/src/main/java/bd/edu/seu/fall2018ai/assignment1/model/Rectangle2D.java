package bd.edu.seu.fall2018ai.assignment1.model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Rectangle2D extends Shape2D {
    private Point2D center;
    private double width;
    private double height;
    private Rectangle rectangle;

    public Rectangle2D(Point2D center, double width, double height) {
        this.center = center;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(center.getX() - width / 2, center.getY() - height / 2, width, height);
        rectangle.setFill(new Color(0.8, 0, 0, 0.7));
    }

    @Override
    public boolean collidesWith(Shape2D shape) {
        if (shape instanceof Circle2D) {
            Rectangle2D rectangle2D = this;
            Circle2D circle2D = (Circle2D) shape;

            double closestX = clamp(
                    circle2D.getCenter().getX(),
                    rectangle2D.getCenter().getX() - rectangle2D.getHeight() / 2,
                    rectangle2D.getCenter().getX() + rectangle2D.getHeight() / 2
            );

            double closestY = clamp(
                    circle2D.getCenter().getY(),
                    rectangle2D.getCenter().getY() - rectangle2D.getWidth() / 2,
                    rectangle2D.getCenter().getY() + rectangle2D.getWidth() / 2
            );

            double distanceX = circle2D.getCenter().getX() - closestX;
            double distanceY = circle2D.getCenter().getY() - closestY;

            double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

            return distanceSquared < (circle2D.getRadius() * circle2D.getRadius());
        }
        return false;
    }

    @Override
    public Group getJFXShape() {
        Group group = new Group();
        group.getChildren().add(rectangle);
        return group;
    }
}

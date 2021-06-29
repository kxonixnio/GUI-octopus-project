import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class StickGuy {

    private Circle head;
    private Line body;
    private Line leftArm;
    private Line rightArm;
    private Line leftLeg;
    private Line rightLeg;
    private int currentStickGuyPosition = 0;

    public StickGuy(Pane root, SimpleDoubleProperty X_Coordinate, SimpleDoubleProperty Y_Coordinate) {

        this.body = new Line();
        body.setStrokeWidth(5);
        body.startXProperty().bind(X_Coordinate);
        body.startYProperty().bind(Y_Coordinate.add(10));
        body.endXProperty().bind(X_Coordinate);
        body.endYProperty().bind(Y_Coordinate.add(60));

        this.head = new Circle();
        head.setStrokeWidth(5);
        head.centerXProperty().bind(X_Coordinate);
        head.centerYProperty().bind(Y_Coordinate.add(10));
        head.setRadius(15);

        this.leftArm = new Line();
        leftArm.setStrokeWidth(5);
        leftArm.startXProperty().bind(X_Coordinate);
        leftArm.startYProperty().bind(Y_Coordinate.add(25));
        leftArm.endXProperty().bind(X_Coordinate.add(-25));
        leftArm.endYProperty().bind(Y_Coordinate.add(50));

        this.rightArm = new Line();
        rightArm.setStrokeWidth(5);
        rightArm.startXProperty().bind(X_Coordinate);
        rightArm.startYProperty().bind(Y_Coordinate.add(25));
        rightArm.endXProperty().bind(X_Coordinate.add(25));
        rightArm.endYProperty().bind(Y_Coordinate.add(50));

        this.leftLeg = new Line();
        leftLeg.setStrokeWidth(5);
        leftLeg.startXProperty().bind(X_Coordinate);
        leftLeg.startYProperty().bind(Y_Coordinate.add(60));
        leftLeg.endXProperty().bind(X_Coordinate.add(-20));
        leftLeg.endYProperty().bind(Y_Coordinate.add(85));

        this.rightLeg = new Line();
        rightLeg.setStrokeWidth(5);
        rightLeg.startXProperty().bind(X_Coordinate);
        rightLeg.startYProperty().bind(Y_Coordinate.add(60));
        rightLeg.endXProperty().bind(X_Coordinate.add(20));
        rightLeg.endYProperty().bind(Y_Coordinate.add(85));

        root.getChildren().addAll(body, head, leftArm, rightArm, leftLeg, rightLeg);
    }

    public void setLeftArmUp(SimpleDoubleProperty X_Coordinate, SimpleDoubleProperty Y_Coordinate) {
        leftArm.setStrokeWidth(5);
        leftArm.startXProperty().bind(X_Coordinate);
        leftArm.startYProperty().bind(Y_Coordinate.add(25));
        leftArm.endXProperty().bind(X_Coordinate.add(-25));
        leftArm.endYProperty().bind(Y_Coordinate);
    }

    public void setLeftArmDown(SimpleDoubleProperty X_Coordinate, SimpleDoubleProperty Y_Coordinate) {
        leftArm.setStrokeWidth(5);
        leftArm.startXProperty().bind(X_Coordinate);
        leftArm.startYProperty().bind(Y_Coordinate.add(25));
        leftArm.endXProperty().bind(X_Coordinate.add(-25));
        leftArm.endYProperty().bind(Y_Coordinate.add(50));
    }

    public void setRightArmUp(SimpleDoubleProperty X_Coordinate, SimpleDoubleProperty Y_Coordinate) {
        rightArm.setStrokeWidth(5);
        rightArm.startXProperty().bind(X_Coordinate);
        rightArm.startYProperty().bind(Y_Coordinate.add(25));
        rightArm.endXProperty().bind(X_Coordinate.add(25));
        rightArm.endYProperty().bind(Y_Coordinate);
    }

    public void setRightArmDown(SimpleDoubleProperty X_Coordinate, SimpleDoubleProperty Y_Coordinate) {
        rightArm.setStrokeWidth(5);
        rightArm.startXProperty().bind(X_Coordinate);
        rightArm.startYProperty().bind(Y_Coordinate.add(25));
        rightArm.endXProperty().bind(X_Coordinate.add(25));
        rightArm.endYProperty().bind(Y_Coordinate.add(50));
    }

    public int getCurrentPosition() {
        return currentStickGuyPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentStickGuyPosition = currentPosition;
    }
}



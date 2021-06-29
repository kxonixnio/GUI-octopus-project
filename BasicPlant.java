import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;

public class BasicPlant extends Node {

    public BasicPlant(Pane root, int xPosition, int yPosition, int amountOfLeafs) {            //650, 525 -> 620, 600

        //Plant stem
        Line plantStem = new Line(xPosition, yPosition, xPosition, yPosition + (amountOfLeafs * 25));
        plantStem.setStroke(Color.GREEN);
        plantStem.setStrokeWidth(2);
        root.getChildren().addAll(
                plantStem
        );

        //Leafs
        for (int i = yPosition; i < yPosition+(amountOfLeafs*10); i+= 10) {
            Arc left_leaf = new Arc(xPosition, i, 10, 10, 180, 90);
            left_leaf.setType(ArcType.OPEN);
            left_leaf.setStroke(Color.GREEN);
            left_leaf.setFill(null);
            left_leaf.setStrokeWidth(2);

            Arc right_leaf = new Arc(xPosition, i, 10, 10, 270, 90);
            right_leaf.setType(ArcType.OPEN);
            right_leaf.setStroke(Color.GREEN);
            right_leaf.setFill(null);
            right_leaf.setStrokeWidth(2);

            root.getChildren().addAll(
                    left_leaf, right_leaf
            );
        }
    }
}

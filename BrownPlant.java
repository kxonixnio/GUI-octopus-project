import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BrownPlant {

    public BrownPlant(Pane root, int xPosition, int yPosition) {
        //Plant Stem
        Line plantStem = new Line(xPosition, yPosition, xPosition, yPosition + 75);
        plantStem.setStroke(Color.BROWN);
        plantStem.setStrokeWidth(2);
        root.getChildren().addAll(
                plantStem
        );

        //Plant Bush
        for (int i = 0, k = 0; i < 25; i+=5, k+=3) {
            Line plantLeftBranch = new Line(
                    xPosition - 10 - k,
                    yPosition - 10 + i,
                    xPosition + 10 + k,
                    yPosition + 10 + i
            );
            plantLeftBranch.setStroke(Color.BROWN);
            Line plantRightBranch = new Line(
                    xPosition - 10 - k,
                    yPosition + 10 + i,
                    xPosition + 10 + k,
                    yPosition - 10 + i
            );
            plantRightBranch.setStroke(Color.BROWN);

            root.getChildren().addAll(
                    plantLeftBranch, plantRightBranch
            );
        }

    }
}

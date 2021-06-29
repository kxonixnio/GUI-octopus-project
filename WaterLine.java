import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class WaterLine extends Line {

    public WaterLine(Pane root) {
        this.setStartX(0.0);
        this.setStartY(100.0);
        this.setEndX(1000.0);
        this.setEndY(100.0);
        this.setStroke(Color.LIGHTBLUE);
        this.setStrokeWidth(10);

        root.getChildren().addAll(
                this
        );

    }
}

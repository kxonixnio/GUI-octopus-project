import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Treasure {
    public Treasure(Pane root) {

        Rectangle treasure = new Rectangle(790.0, 530.0, 55.0, 40.0);
        Line treasureTrapdoor = new Line(845.0, 530.0, 800.0, 500.0);
        treasureTrapdoor.setStrokeWidth(3);

        root.getChildren().addAll(
                treasure, treasureTrapdoor
        );

    }
}

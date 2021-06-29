import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Boat extends Polygon {

    public Boat(Pane root) {
        //Boat
        this.getPoints().addAll(
                50.0, 50.0,
                100.0, 95.0,
                200.0, 95.0,
                250.0, 50.0
        );

        root.getChildren().addAll(
                this
        );

        //Boat Line
        for(double phi = -Math.PI; phi < 0; phi += 0.01) {
            Line line = new Line(
                    50 + Math.cos((0.8)*phi) * Math.sin(phi) * (-100), 250 + Math.cos(phi) * 200,
                    50 + Math.cos((0.8)*phi) * Math.sin(phi) * (-100), 250 + Math.cos(phi) * 200
            );
            line.setStrokeWidth(1.5);
            root.getChildren().addAll(line);
        }
        //0.8, bo dotykało okna od lewej strony
    }

}

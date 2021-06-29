import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class PirateBoat {

    public PirateBoat(Pane root) {
        Arc pirateShip = new Arc(1000, 180, 300, 400, 210, 90);
        pirateShip.setType(ArcType.CHORD);
        pirateShip.setStroke(Color.BLACK);
        pirateShip.setFill(null);
        pirateShip.setStrokeWidth(5);

        Line pirateShipMast = new Line(950.0, 450.0, 950.0, 175.0);
        pirateShipMast.setStrokeWidth(3);

        for(double i = 375.0; i >= 225.0; i-=75) {
            Line mastBar = new Line(900.0, i, 1000.0, i-25.0);
            mastBar.setStrokeWidth(3);
            root.getChildren().addAll(mastBar);
        }

        Polygon pirateBoatLight = new Polygon();
        pirateBoatLight.getPoints().addAll(
                920.0, 135.0,
                940.0, 175.0,
                960.0, 175.0,
                980.0, 135.0
        );
        pirateBoatLight.setStrokeWidth(3);
        pirateBoatLight.setStroke(Color.BLACK);
        pirateBoatLight.setFill(null);

        Line pirateBoatLightDecoration = new Line(950.0, 135.0, 950.0, 125.0);
        pirateBoatLightDecoration.setStrokeWidth(3);

        for(double i = 420.0; i > 225; i-=75) {
            Polygon leftPirateBoatSail = new Polygon();
            leftPirateBoatSail.getPoints().addAll(
                    900.0, i-40,
                    945.0, i-50,
                    945.0, i-10+5,
                    900.0, i+5
            );
            root.getChildren().addAll(leftPirateBoatSail);
        }

        for(double i = 407.5; i > 225; i-=75) {
            Polygon rightPirateBoatSail = new Polygon();
            rightPirateBoatSail.getPoints().addAll(
                    955.0, i+5,
                    1000.0, i-10+5,
                    1000.0, i-50,
                    955.0, i-40
            );
            root.getChildren().addAll(rightPirateBoatSail);
        }

        root.getChildren().addAll(
                pirateShip, pirateShipMast, pirateBoatLight, pirateBoatLightDecoration
        );

    }
}

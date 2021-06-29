import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.Random;

public class BottomOfTheOcean {

    public BottomOfTheOcean(Pane root) {

        Arc leftSeaBed = new Arc(-100, 1050, 600, 600, 45, 90);
        leftSeaBed.setType(ArcType.CHORD);
        leftSeaBed.setStroke(Color.BLACK);
        leftSeaBed.setFill(Color.BLACK);
        leftSeaBed.setStrokeWidth(3);
        root.getChildren().addAll(
                leftSeaBed
        );

        Random random = new Random();
        for(int i = 250; i < 1250; i+= random.nextInt(101)){
            Arc seaBottom = new Arc(i, 670, 100, 100, 45, 90);
            seaBottom.setType(ArcType.CHORD);
            seaBottom.setStroke(Color.BLACK);
            root.getChildren().addAll(seaBottom);
        }


    }
}

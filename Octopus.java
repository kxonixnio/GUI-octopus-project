import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Octopus {

    private Circle head;
    private Circle face;
    private Circle mouth;
    private Circle leftEyeWhite;
    private Line leftEyebrow;
    private Circle leftEye;
    private Circle rightEyeWhite;
    private Line rightEyebrow;
    private Circle rightEye;
    private Rectangle thirdArmFromRight;
    private Rectangle fourthArmFromRight;

    public Octopus(Pane root) {
        this.head = new Circle();
        head.setCenterX(800);
        head.setCenterY(190);
        head.setRadius(80);

        this.face = new Circle();
        face.setCenterX(730);
        face.setCenterY(210);
        face.setRadius(60);

        this.mouth = new Circle();
        mouth.setStroke(Color.WHITE);
        mouth.setStrokeWidth(7);
        mouth.setCenterX(690);
        mouth.setCenterY(235);
        mouth.setRadius(15);

        this.leftEyeWhite = new Circle();
        leftEyeWhite.setStroke(Color.WHITE);
        leftEyeWhite.setFill(Color.WHITE);
        leftEyeWhite.setCenterX(700);
        leftEyeWhite.setCenterY(195);
        leftEyeWhite.setRadius(15);

        this.leftEyebrow = new Line();
        leftEyebrow.setStartX(680);
        leftEyebrow.setStartY(200);
        leftEyebrow.setEndX(730);
        leftEyebrow.setEndY(180);
        leftEyebrow.setStrokeWidth(9);

        this.leftEye = new Circle();
        leftEye.setCenterX(710);
        leftEye.setCenterY(195);
        leftEye.setRadius(10);

        this.rightEyeWhite = new Circle();
        rightEyeWhite.setStroke(Color.WHITE);
        rightEyeWhite.setFill(Color.WHITE);
        rightEyeWhite.setCenterX(740);
        rightEyeWhite.setCenterY(195);
        rightEyeWhite.setRadius(15);

        this.rightEyebrow = new Line();
        rightEyebrow.setStartX(730);
        rightEyebrow.setStartY(185);
        rightEyebrow.setEndX(760);
        rightEyebrow.setEndY(205);
        rightEyebrow.setStrokeWidth(9);

        this.rightEye = new Circle();
        rightEye.setCenterX(730);
        rightEye.setCenterY(195);
        rightEye.setRadius(10);

        root.getChildren().addAll(
                head, face, leftEyeWhite, leftEyebrow, leftEye, rightEyeWhite, rightEyebrow, rightEye
        );

        //Macka najbardziej z prawej - "statyczna"
        for(double phi = -(1.0/2.0)*Math.PI; phi < (0.0/2.0)*Math.PI; phi += 0.01) {
            Line line = new Line(
                    850+Math.cos((1.5)*phi)*(120), 260+Math.cos(phi)*Math.sin(phi)*(-100),
                    850+Math.cos((1.25)*phi)*(120), 260+Math.cos(phi)*Math.sin(phi)*(-100)
            );
            line.setStrokeWidth(20);
            root.getChildren().addAll(line);
        }

        //Druga macka z prawej - "statyczna"
        for(double phi = -(1.0/2.0)*Math.PI; phi < 0*Math.PI; phi += 0.01) {
            Line line = new Line(
                    825+Math.cos((1.5)*phi)*(100), 260+Math.cos(phi)*Math.sin(phi)*(-200),
                    825+Math.cos((1.5)*phi)*(100), 260+Math.cos(phi)*Math.sin(phi)*(-200)
            );
            line.setStrokeWidth(20);
            root.getChildren().addAll(line);
        }

        thirdArmFromRight = new Rectangle(675, 250, 100, 50);;
        thirdArmFromRight.setRotate(-60);
        thirdArmFromRight.setArcHeight(20);
        thirdArmFromRight.setArcWidth(20);

        fourthArmFromRight = new Rectangle(585, 250, 125, 50);;
        fourthArmFromRight.setRotate(-30);
        fourthArmFromRight.setArcHeight(20);
        fourthArmFromRight.setArcWidth(20);

        root.getChildren().addAll(thirdArmFromRight, fourthArmFromRight);

        //FifthArmFromRight
        for(double phi = -(1.0/2.0)*Math.PI; phi < 0*Math.PI; phi += 0.01) {
            Line line = new Line(
                    560+Math.cos((1.5)*phi)*(120), 275+Math.cos(phi)*Math.sin(phi)*(100),
                    560+Math.cos((1.5)*phi)*(120), 275+Math.cos(phi)*Math.sin(phi)*(100)
            );
            line.setStrokeWidth(40);
            root.getChildren().addAll(line);
        }

        //SixthArmFromRight
        for(double phi = -(1.0/2.0)*Math.PI; phi < 0*Math.PI; phi += 0.01) {
            Line line = new Line(
                    475+Math.cos((1.5)*phi)*(175), 210+Math.cos(phi)*Math.sin(phi)*(100),
                    475+Math.cos((1.5)*phi)*(175), 210+Math.cos(phi)*Math.sin(phi)*(100)
            );
            line.setStrokeWidth(40);
            root.getChildren().addAll(line);
        }

        //SeventhArmFromRight
        for(double phi = -(1.0/2.0)*Math.PI; phi < 0*Math.PI; phi += 0.01) {
            Line line = new Line(
                    627+Math.cos((1.5)*phi)*(60), 170+Math.cos(phi)*Math.sin(phi)*(100),
                    627+Math.cos((1.5)*phi)*(60), 170+Math.cos(phi)*Math.sin(phi)*(100)
            );
            line.setStrokeWidth(20);
            line.setStroke(Color.BLACK);
            root.getChildren().addAll(line);
        }

        //Nie chcemy, żeby usta były przysłonięte przez inne wyrysowane macki, dlatego dopiero tu dodajemy
        root.getChildren().addAll(mouth);

//------------------------------------------------------------------------------------------------------------





    }
}

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class OctoupsAnimatedLegs {

    private long endTime;

    private ArrayList<String> getCorrectLetter = new ArrayList<>();
    private String username = "";
    private int clickedJustOnce = 0;

    private SimpleBooleanProperty[][] armsVisibility;
    private int[]numberOfPaintedSegmentsForEachLeg;
    private SimpleBooleanProperty endScreenVisibility = new SimpleBooleanProperty(false);

    private Text youLoseText;
    private ListView<String> typeYourNameListView;
    private ObservableList<String> items;

    private ListView<String> endScores;
    private ObservableList<String> scores;

    private Text usernameText;
    SimpleStringProperty currentUsernameTyped = new SimpleStringProperty(username);
    SimpleDoubleProperty X_usernameText_Coordinate = new SimpleDoubleProperty(500);
    private Text typeYourNameText;
    private Button submitUsernameButton;
    private Button sortByPoints;
    private Button sortByTime;

    private Rectangle thirdLegFirstSegment;
    private Rectangle thirdLegSecondSegment;
    private Arc thirdLegThirdSegment;

    private Rectangle fourthLegFirstSegment;
    private Rectangle fourthLegSecondSegment;
    private Rectangle fourthLegThirdSegment;
    private Arc fourthLegFourthSegment;

    private Rectangle fifthLegFirstSegment;
    private Rectangle fifthLegSecondSegment;
    private Rectangle fifthLegThirdSegment;
    private Rectangle fifthLegFourthSegment;
    private Arc fifthLegFifthSegment;

    private Rectangle sixthLegFirstSegment;
    private Rectangle sixthLegSecondSegment;
    private Rectangle sixthLegThirdSegment;
    private Arc sixthLegFourthSegment;

    public OctoupsAnimatedLegs(Pane root, StickGuy stickGuy, Enough enough) {

        armsVisibility = new SimpleBooleanProperty[4][];
        armsVisibility[0] = new SimpleBooleanProperty[3];   //Pierwsze animowane ramię
        armsVisibility[1] = new SimpleBooleanProperty[4];   //Drugie animowane ramię
        armsVisibility[2] = new SimpleBooleanProperty[5];   //...
        armsVisibility[3] = new SimpleBooleanProperty[4];
        for (int i = 0; i < armsVisibility.length; i++) {
            for (int j = 0; j < armsVisibility[i].length; j++) {
                armsVisibility[i][j] = new SimpleBooleanProperty(false);
            }
        }
        numberOfPaintedSegmentsForEachLeg = new int[4];

        thirdLegFirstSegment = new Rectangle(660, 325, 65, 50);
        thirdLegFirstSegment.visibleProperty().bind(armsVisibility[0][0]);
        thirdLegFirstSegment.setRotate(-70);
        thirdLegFirstSegment.setArcHeight(20);
        thirdLegFirstSegment.setArcWidth(20);

        thirdLegSecondSegment = new Rectangle(650, 390, 65, 50);
        thirdLegSecondSegment.visibleProperty().bind(armsVisibility[0][1]);
        thirdLegSecondSegment.setRotate(90);
        thirdLegSecondSegment.setArcHeight(20);
        thirdLegSecondSegment.setArcWidth(20);

        thirdLegThirdSegment = new Arc(765, 460, 80, 60, 170, 90);
        thirdLegThirdSegment.visibleProperty().bind(armsVisibility[0][2]);
        thirdLegThirdSegment.setType(ArcType.OPEN);
        thirdLegThirdSegment.setStroke(Color.BLACK);
        thirdLegThirdSegment.setFill(null);
        thirdLegThirdSegment.setStrokeWidth(30);
        thirdLegThirdSegment.setRotate(0);

        fourthLegFirstSegment = new Rectangle(555, 300, 50, 50);
        fourthLegFirstSegment.visibleProperty().bind(armsVisibility[1][0]);
        fourthLegFirstSegment.setRotate(-70);
        fourthLegFirstSegment.setArcHeight(20);
        fourthLegFirstSegment.setArcWidth(20);

        fourthLegSecondSegment = new Rectangle(545, 345, 50, 50);
        fourthLegSecondSegment.visibleProperty().bind(armsVisibility[1][1]);
        fourthLegSecondSegment.setRotate(80);
        fourthLegSecondSegment.setArcHeight(20);
        fourthLegSecondSegment.setArcWidth(20);

        fourthLegThirdSegment = new Rectangle(555, 400, 65, 50);
        fourthLegThirdSegment.visibleProperty().bind(armsVisibility[1][2]);
        fourthLegThirdSegment.setRotate(60);
        fourthLegThirdSegment.setArcHeight(20);
        fourthLegThirdSegment.setArcWidth(20);

        fourthLegFourthSegment = new Arc(615, 460, 60, 60, 170, 90);
        fourthLegFourthSegment.visibleProperty().bind(armsVisibility[1][3]);
        fourthLegFourthSegment.setType(ArcType.OPEN);
        fourthLegFourthSegment.setStroke(Color.BLACK);
        fourthLegFourthSegment.setFill(null);
        fourthLegFourthSegment.setStrokeWidth(30);
        fourthLegFourthSegment.setRotate(-110);

        fifthLegFirstSegment = new Rectangle(433, 270, 50, 50);
        fifthLegFirstSegment.visibleProperty().bind(armsVisibility[2][0]);
        fifthLegFirstSegment.setRotate(-60);
        fifthLegFirstSegment.setArcHeight(20);
        fifthLegFirstSegment.setArcWidth(20);

        fifthLegSecondSegment = new Rectangle(408, 315, 50, 50);
        fifthLegSecondSegment.visibleProperty().bind(armsVisibility[2][1]);
        fifthLegSecondSegment.setRotate(-65);
        fifthLegSecondSegment.setArcHeight(20);
        fifthLegSecondSegment.setArcWidth(20);

        fifthLegThirdSegment = new Rectangle(390, 362, 50, 50);
        fifthLegThirdSegment.visibleProperty().bind(armsVisibility[2][2]);
        fifthLegThirdSegment.setRotate(-75);
        fifthLegThirdSegment.setArcHeight(20);
        fifthLegThirdSegment.setArcWidth(20);

        fifthLegFourthSegment = new Rectangle(380, 410, 50, 50);
        fifthLegFourthSegment.visibleProperty().bind(armsVisibility[2][3]);
        fifthLegFourthSegment.setRotate(-100);
        fifthLegFourthSegment.setArcHeight(20);
        fifthLegFourthSegment.setArcWidth(20);

        fifthLegFifthSegment = new Arc(415, 460, 75, 50, 180, 90);
        fifthLegFifthSegment.visibleProperty().bind(armsVisibility[2][4]);
        fifthLegFifthSegment.setType(ArcType.OPEN);
        fifthLegFifthSegment.setStroke(Color.BLACK);
        fifthLegFifthSegment.setFill(null);
        fifthLegFifthSegment.setStrokeWidth(30);
        fifthLegFifthSegment.setRotate(-80);

        sixthLegFirstSegment = new Rectangle(285, 210, 85, 50);
        sixthLegFirstSegment.visibleProperty().bind(armsVisibility[3][0]);
        sixthLegFirstSegment.setRotate(-45);
        sixthLegFirstSegment.setArcHeight(20);
        sixthLegFirstSegment.setArcWidth(20);

        sixthLegSecondSegment = new Rectangle(225, 285, 100, 50);
        sixthLegSecondSegment.visibleProperty().bind(armsVisibility[3][1]);
        sixthLegSecondSegment.setRotate(-60);
        sixthLegSecondSegment.setArcHeight(20);
        sixthLegSecondSegment.setArcWidth(20);

        sixthLegThirdSegment = new Rectangle(182, 360, 85, 50);
        sixthLegThirdSegment.visibleProperty().bind(armsVisibility[3][2]);
        sixthLegThirdSegment.setRotate(-45);
        sixthLegThirdSegment.setArcHeight(20);
        sixthLegThirdSegment.setArcWidth(20);

        sixthLegFourthSegment = new Arc(187, 400, 75, 60, 180, 90);
        sixthLegFourthSegment.visibleProperty().bind(armsVisibility[3][3]);
        sixthLegFourthSegment.setType(ArcType.OPEN);
        sixthLegFourthSegment.setStroke(Color.BLACK);
        sixthLegFourthSegment.setFill(null);
        sixthLegFourthSegment.setStrokeWidth(30);
        sixthLegFourthSegment.setRotate(-60);
//-------------------------------------------------------------------------^sama ośmiornica^----------------------------
        youLoseText = new Text();
        youLoseText.setText("YOU LOSE");
        youLoseText.setFont(Font.font("Verdana", 150));
        youLoseText.setStroke(Color.RED);
        youLoseText.setStrokeWidth(3);
        youLoseText.setEffect(new DropShadow());
        youLoseText.setX(125);
        youLoseText.setY(350);
        youLoseText.visibleProperty().bind(endScreenVisibility);

        usernameText = new Text();
        usernameText.textProperty().bind(currentUsernameTyped);
        usernameText.setFont(Font.font("Verdana", 30));
        usernameText.setStroke(Color.RED);
        usernameText.setStrokeWidth(1);
        usernameText.xProperty().bind(X_usernameText_Coordinate);
        usernameText.setY(485);
        usernameText.visibleProperty().bind(endScreenVisibility);

        typeYourNameText = new Text();
        typeYourNameText.setText("Type your name:");
        typeYourNameText.setFont(Font.font("Verdana", 30));
        typeYourNameText.setStroke(Color.RED);
        typeYourNameText.setStrokeWidth(1);
        typeYourNameText.setX(385);
        typeYourNameText.setY(390);
        typeYourNameText.visibleProperty().bind(endScreenVisibility);

        submitUsernameButton = new Button();
        submitUsernameButton.setText("SUBMIT");
        submitUsernameButton.setTranslateX(480);
        submitUsernameButton.setTranslateY(500);
        submitUsernameButton.visibleProperty().bind(endScreenVisibility);


        typeYourNameListView = new ListView<>();
        typeYourNameListView.setOrientation(Orientation.HORIZONTAL);
        items = FXCollections.observableArrayList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T",
                "U", "W", "X", "Y", "Z"
        );
        typeYourNameListView.setItems(items);
        typeYourNameListView.setPrefHeight(50);
        typeYourNameListView.setPrefWidth(580);
        typeYourNameListView.setTranslateX(225);
        typeYourNameListView.setTranslateY(400);
        typeYourNameListView.visibleProperty().bind(endScreenVisibility);

//        ScreenAfterLose screenAfterLose = new ScreenAfterLose(root, endScreenVisibility, currentUsernameTyped, X_usernameText_Coordinate, items);

        endScores = new ListView<>();
        endScores.setItems(scores);
        endScores.setPrefHeight(500);
        endScores.setPrefWidth(800);
        endScores.setTranslateX(100);
        endScores.setTranslateY(50);
        endScores.setVisible(false);
        endScores.setCellFactory(stringListView -> new CenteredListViewCell());

        sortByPoints = new Button();
        sortByPoints.setText("Sort by points");
        sortByPoints.setTranslateX(100);
        sortByPoints.setTranslateY(498);
        sortByPoints.setVisible(false);

        sortByTime = new Button();
        sortByTime.setText("  Sort by time ");
        sortByTime.setTranslateX(100);
        sortByTime.setTranslateY(523);
        sortByTime.setVisible(false);


        root.getChildren().addAll(
                thirdLegFirstSegment, thirdLegSecondSegment, thirdLegThirdSegment,
                fourthLegFirstSegment, fourthLegSecondSegment, fourthLegThirdSegment, fourthLegFourthSegment,
                fifthLegFirstSegment, fifthLegSecondSegment, fifthLegThirdSegment, fifthLegFourthSegment, fifthLegFifthSegment,
                sixthLegFirstSegment, sixthLegSecondSegment, sixthLegThirdSegment, sixthLegFourthSegment,
                youLoseText, typeYourNameListView, usernameText, typeYourNameText, submitUsernameButton, endScores,
                sortByPoints, sortByTime
        );

        Random randomArm = new Random();

        Thread thread = new Thread(
                ()->{
                    while(!enough.isEnough()){
                        try {
                            //Wylosowanie atak/"obrona" i wylosowanie ramienia, którego będzie to dotyczyć
                            boolean attack = randomArm.nextBoolean();
                            int randomLeg = randomArm.nextInt(4);

                            if(attack){

                                //Jeżeli nie wszystkie segmenty ramienia są "wysunięte"
                                if(numberOfPaintedSegmentsForEachLeg[randomLeg] != armsVisibility[randomLeg].length){
                                    Thread.sleep(200);

                                    //to wysuwamy odpowiedni segment wylosowanego ramienia
                                    armsVisibility
                                            [randomLeg]
                                            [numberOfPaintedSegmentsForEachLeg[randomLeg]].set(true);

                                    //Zaktualizowanie odrębnej tablicy przechowującej informacje o "pojawionych" ramionach
                                    if(numberOfPaintedSegmentsForEachLeg[randomLeg] < armsVisibility[randomLeg].length - 1) {
                                        numberOfPaintedSegmentsForEachLeg[randomLeg] += 1;
                                    }
                                    Thread.sleep(200);
                                }

                            }else{
                                //Sprawdzenie czy pierwszy segment danego ramienia jest "pojawiony"
                                if(armsVisibility[randomLeg][0].getValue()){  //numberOfPaintedSegmentsForEachLeg[randomLeg] > 0
                                    Thread.sleep(200);

                                    //"Schowanie" segmentu wylosowanego ramienia
                                    armsVisibility
                                            [randomLeg]
                                            [numberOfPaintedSegmentsForEachLeg[randomLeg]].set(false);

                                    //Zaktualizowanie odrębnej tablicy przechowującej informacje o "pojawionych" ramionach
                                    if (numberOfPaintedSegmentsForEachLeg[randomLeg] > 1) {
                                        numberOfPaintedSegmentsForEachLeg[randomLeg] -= 1;
                                    }
                                    Thread.sleep(200);
                                }
                            }

                            /*
                            Jeżeli dane ramie jest maksymalnie wysunięte i ludzik tam stoi - koniec gry.
                            Zostaje pojawiony ekran końcowy i zliczony czas
                             */
                            if(armsVisibility[0][armsVisibility[0].length - 1].getValue() && stickGuy.getCurrentPosition() == 5){
                                enough.setEnough(true);
                                endScreenVisibility.set(true);
                                endTime = System.nanoTime();
                            }
                            if(armsVisibility[1][armsVisibility[1].length - 1].getValue() && stickGuy.getCurrentPosition() == 4){
                                enough.setEnough(true);
                                endScreenVisibility.set(true);
                                endTime = System.nanoTime();
                            }
                            if(armsVisibility[2][armsVisibility[2].length - 1].getValue() && stickGuy.getCurrentPosition() == 3){
                                enough.setEnough(true);
                                endScreenVisibility.set(true);
                                endTime = System.nanoTime();
                            }
                            if(armsVisibility[3][armsVisibility[3].length - 1].getValue() && stickGuy.getCurrentPosition() == 2){
                                enough.setEnough(true);
                                endScreenVisibility.set(true);
                                endTime = System.nanoTime();
                            }

//                            https://stackoverflow.com/questions/29179036/using-javafx-8-scene-read-keyboard-input-while-running
                            //Jeżeli gra się już skończyła
                            if(enough.isEnough()){

                                //https://self-learning-java-tutorial.blogspot.com/2018/06/javafx-listview-get-selected-item.html
                                typeYourNameListView.getSelectionModel().selectedItemProperty().addListener(
                                        (ObservableValue<? extends String> ov, String old_val, String new_val) -> {

                                            String selectedItem = typeYourNameListView.getSelectionModel().getSelectedItem();

                                            /*
                                            Dlaczego KeyCode.SPACE nie działa?
                                            Dlaczego jak chcę dodać np. "K" to dodają się wszystkie wcześniejsze literki?
                                            Po wciśnięciu X dodawane są literki - wybrana przez nas zawsze jest ostatnia...
                                             */
                                            root.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                                                if(key.getCode() == KeyCode.X){
                                                    clickedJustOnce = 0;
                                                    getCorrectLetter.add(selectedItem);
                                                }
                                            });

                                            root.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                                                if(key.getCode() == KeyCode.X){
                                                    clickedJustOnce += 1;
                                                    if(clickedJustOnce == 1) {

                                                        //...dlatego do username dodajemy ostatnią z arrayListy
                                                        username += getCorrectLetter.get(getCorrectLetter.size() - 1);

                                                        //Przesunięcie usernameText delikatnie w lewo tak, aby tekst był względnie wycentrowany
                                                        X_usernameText_Coordinate.set(X_usernameText_Coordinate.add(-8).getValue());

                                                        Platform.runLater(
                                                                ()->currentUsernameTyped.set(username)      //"dobra praktyka"
                                                        );
                                                    }
                                                }
                                            });
                                            /*
                                            "Puszczenie" przycisku wykonuje się kilkukrotnie, a chcemy, żeby wykonało się jedynie raz.
                                            Dlatego wymuszamy jego jednokrotne działanie, a warunek "resetujemy" przy ponownym
                                            wciśnięciu przycisku
                                             */

                                        }
                                );
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
    }


    public String getUsername() {
        return username;
    }

    public long getEndTime() {
        return endTime;
    }

    public Button getSubmitUsernameButton() {
        return submitUsernameButton;
    }

    public boolean isEndScreenVisibility() {
        return endScreenVisibility.get();
    }

    public SimpleBooleanProperty endScreenVisibilityProperty() {
        return endScreenVisibility;
    }


    public ListView<String> getEndScores() {
        return endScores;
    }

    public ObservableList<String> getScores() {
        return scores;
    }

    public void setScores(ObservableList<String> scores) {
        this.scores = scores;
    }

    public Button getSortByPoints() {
        return sortByPoints;
    }

    public Button getSortByTime() {
        return sortByTime;
    }

    public Text getYouLoseText() {
        return youLoseText;
    }

    public void setYouLoseText(Text youLoseText) {
        this.youLoseText = youLoseText;
    }

    public Text getTypeYourNameText() {
        return typeYourNameText;
    }

    public void setTypeYourNameText(Text typeYourNameText) {
        this.typeYourNameText = typeYourNameText;
    }

    public Text getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(Text usernameText) {
        this.usernameText = usernameText;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}


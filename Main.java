import javafx.animation.*;
import javafx.application.Application;
//import javafx.beans.property.SimpleDoubleProperty;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private boolean goRight, goLeft;
    private int isClicked = 0;
    private int bigPoints = 0;
    private int amount_of_presses = 0;
    private String username = "";
    private long timeInSecond = 0;

    private SimpleStringProperty bigPointsAsString = new SimpleStringProperty(Integer.toString(bigPoints));
    private Enough enough = new Enough();
    private final long startTime = System.nanoTime();
    private long endTime;

    private ArrayList<String> scoresFromFile = new ArrayList<>();

    private boolean giveBonusPoints = false;

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();

        new BasicPlant(root, 650, 525, 3);
        new BasicPlant(root, 450, 500, 6);
        new BasicPlant(root, 260, 520, 4);
        new BasicPlant(root, 30, 400, 5);
        new BasicPlant(root, 160, 460, 3);
        new BrownPlant(root, 120, 450);
        new BrownPlant(root, 210, 490);
        new BrownPlant(root, 325, 525);
        new WaterLine(root);
        new Boat(root);
        new BottomOfTheOcean(root);
        new PirateBoat(root);
        new Treasure(root);
        new Octopus(root);

        //Zmapowanie dostępnych pozycji w obrębie gry z odpowiednimi koordynatami
        Map<Integer, Coordinates> stickGuyPositions = new HashMap<Integer, Coordinates>();
        stickGuyPositions.put(0, new Coordinates(150, 0));
        stickGuyPositions.put(1, new Coordinates(75, 200));
        stickGuyPositions.put(2, new Coordinates(100, 400));
        stickGuyPositions.put(3, new Coordinates(350, 490));
        stickGuyPositions.put(4, new Coordinates(550, 490));
        stickGuyPositions.put(5, new Coordinates(760, 490));

        SimpleDoubleProperty X_Coordinate = new SimpleDoubleProperty(
                stickGuyPositions.get(0).getX()
        );
        SimpleDoubleProperty Y_Coordinate = new SimpleDoubleProperty(
                stickGuyPositions.get(0).getY()
        );

        StickGuy stickGuy = new StickGuy(root, X_Coordinate, Y_Coordinate);
        OctoupsAnimatedLegs octoupsAnimatedLegs = new OctoupsAnimatedLegs(root, stickGuy, enough);

        Scene scene = new Scene(root, 1005, 600);

        Text bigScoreText = new Text();
        bigScoreText.textProperty().bind(bigPointsAsString);
        bigScoreText.setFont(Font.font("Verdana", 70));
        bigScoreText.setEffect(new DropShadow());
        bigScoreText.setX(900);
        bigScoreText.setY(75);

        root.getChildren().addAll(bigScoreText);


        //https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx

        //Dlaczego nie działa setOnKeyTyped?
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case LEFT:  goLeft = true; break;
                    case RIGHT: goRight = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  goLeft = false; break;
                    case RIGHT: goRight = false; break;
                }
                isClicked = 0;
            }
        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (goRight && !enough.isEnough()) {

                    isClicked += 1;

                    if(isClicked == 1){
                        //Sprawdzenie czy ludzik nie znajduje się na ostatniej pozycji
                        if(stickGuy.getCurrentPosition() < stickGuyPositions.size()-1){

                            //Zmiana pozycji ludzika i aktualizacja koordynatów pozyskanych z mapy
                            stickGuy.setCurrentPosition(stickGuy.getCurrentPosition()+1);
                            X_Coordinate.set(stickGuyPositions.get(stickGuy.getCurrentPosition()).getX());
                            Y_Coordinate.set(stickGuyPositions.get(stickGuy.getCurrentPosition()).getY());

                        }else{
                            amount_of_presses += 1;

                            //Wygrana
                            if(bigPoints == 99){
                                System.out.println("Win!");
                                enough.setEnough(true);

                                Platform.runLater(
                                        ()->bigPointsAsString.set("99")
                                );

                                //Aktualizacja odpowiednich komponentów programu
                                octoupsAnimatedLegs.setEndTime(System.nanoTime());

                                octoupsAnimatedLegs.endScreenVisibilityProperty().set(true);
                                octoupsAnimatedLegs.getYouLoseText().setText("YOU WIN");
                                octoupsAnimatedLegs.getYouLoseText().setStroke(Color.GREEN);

                                octoupsAnimatedLegs.getTypeYourNameText().setStroke(Color.GREEN);

                                octoupsAnimatedLegs.getUsernameText().setStroke(Color.GREEN);
                            }

                            //Unoszenie i opuszczanie rąk przez ludzika
                            if(amount_of_presses % 2 == 1) {
                                stickGuy.setLeftArmUp(X_Coordinate, Y_Coordinate);
                                stickGuy.setRightArmUp(X_Coordinate, Y_Coordinate);
                                bigPoints += 1;
                                giveBonusPoints = true;

                            }else{
                                stickGuy.setLeftArmDown(X_Coordinate, Y_Coordinate);
                                stickGuy.setRightArmDown(X_Coordinate, Y_Coordinate);
                            }
                            //Aktualizacja punktów w prawym górnym rogu
                            if(bigPoints < 99) {
                                Platform.runLater(
                                        ()->bigPointsAsString.set(Integer.toString(bigPoints))      //"dobra praktyka"
                                );
                            }
                        }
                    }

                }
                if (goLeft && !enough.isEnough()) {

                    isClicked += 1;

                    if(isClicked == 1){
                        //Sprawdzenie czy ludzik nie znajduje się na pierwszej pozycji
                        if(stickGuy.getCurrentPosition() > 0){

                            //Zmiana pozycji ludzika i aktualizacja koordynatów pozyskanych z mapy
                            stickGuy.setCurrentPosition(stickGuy.getCurrentPosition()-1);
                            X_Coordinate.set(stickGuyPositions.get(stickGuy.getCurrentPosition()).getX());
                            Y_Coordinate.set(stickGuyPositions.get(stickGuy.getCurrentPosition()).getY());

                            //Unikamy możliwości, w której ludzik "uciekałby" z podniesionymi rękoma
                            stickGuy.setLeftArmDown(X_Coordinate, Y_Coordinate);
                            stickGuy.setRightArmDown(X_Coordinate, Y_Coordinate);
                        }
                        amount_of_presses = 0;
                    }

                    //Przyznanie dodatkowych punktów za wynurzenie
                    if(stickGuy.getCurrentPosition() == 0 && giveBonusPoints){
                        bigPoints += 2;
                        Platform.runLater(
                                ()->bigPointsAsString.set(Integer.toString(bigPoints))      //"dobra praktyka"
                        );
                        giveBonusPoints = false;
                    }
                }
//----------------------------------------------------------------------------------------------------------------------
                //Przycisk SUBMIT wysyłający nickname, punkty i czas do pliku
                octoupsAnimatedLegs.getSubmitUsernameButton().setOnAction(
                        (e)->{
                            username = octoupsAnimatedLegs.getUsername();
                            timeInSecond = (endTime - startTime) / 1_000_000_000;
                            octoupsAnimatedLegs.endScreenVisibilityProperty().set(false);

                            if(!username.isEmpty()) {
                                try {
                                    //Jeżeli został podany username to wysyłamy wyniki do pliku
                                    FileWriter fw = new FileWriter("scores.txt", true);
                                    String output = "";
                                    StringBuilder bobTheBuilder = new StringBuilder("");
                                    bobTheBuilder.
                                            append(username).append(" ").
                                            append(bigPoints).append(" ").
                                            append(timeInSecond).append("s\n");
                                    output = bobTheBuilder.toString();
                                    fw.write(output);
                                    fw.close();

                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                            try {
                                //Zczytanie z pliku
                                FileInputStream fis = new FileInputStream("scores.txt");
                                Scanner sc = new Scanner(fis);
                                while(sc.hasNextLine()){
                                    scoresFromFile.add(sc.nextLine());
                                }
                                sc.close();
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }

                            /*
                            //Ustawienie kontentu z pliku jako kontent ListView i wyświetlenie tablicy wyników wraz
                             z przyciskami sortowania
                             */
                            octoupsAnimatedLegs.setScores(FXCollections.observableArrayList(
                                    scoresFromFile
                            ));
                            octoupsAnimatedLegs.getEndScores().setItems(octoupsAnimatedLegs.getScores());
                            octoupsAnimatedLegs.getEndScores().setVisible(true);
                            octoupsAnimatedLegs.getSortByPoints().setVisible(true);
                            octoupsAnimatedLegs.getSortByTime().setVisible(true);

                            System.out.println("submitted");
                        }
                );
//----------------------------------------------------------------------------------------------------------------------
                octoupsAnimatedLegs.getSortByPoints().setOnAction(
                        (e) -> {
                            System.out.println("sort by points");
                            ArrayList<Integer> justPoints = new ArrayList<>();
                            ArrayList<String> splitted_scores = new ArrayList<>();

                            /*
                            Stworzenie dwóch potrzebnych kontenerów - jeden przechowywać będzie wyłącznie punkty
                            posortowane malejąco. Drugi to kontener zawierający posplitowane wyniki
                            (czyli kolejno zawiera nick, punkty, czas, nick, punkty, czas, nick...)
                             */
                            for(String line : scoresFromFile){
                                String[] user_with_points_and_time = line.split(" ");

                                justPoints.add(Integer.parseInt(user_with_points_and_time[1]));
                                splitted_scores.addAll(Arrays.asList(user_with_points_and_time));
                            }

                            Collections.sort(justPoints);

                            ArrayList<String> output_sorted_by_points = new ArrayList<>();

                            /*
                            Dla każdego punktu znajdujemy jego pozycję jego odpowiednika w "posplitowanej" tablicy.
                            Ta pozycja "otoczona" jest nickiem i czasem, te wartości Bob ze sobą "skleja".
                            Każdy "punkt" zostaje następnie zamieniony na 100 (wartość nieosiągalna) tak, aby przy
                            następnej iteracji uniknąć wyszukania tego samego elementu
                             */
                            for (Integer points : justPoints){
                                int pos = splitted_scores.indexOf(Integer.toString(points));

                                StringBuilder bobTheBuilder = new StringBuilder();

                                bobTheBuilder.append(splitted_scores.get(pos - 1));
                                bobTheBuilder.append(" ");
                                bobTheBuilder.append(splitted_scores.get(pos));
                                bobTheBuilder.append(" ");
                                bobTheBuilder.append(splitted_scores.get(pos + 1));

                                splitted_scores.set(pos, "100");

                                output_sorted_by_points.add(bobTheBuilder.toString());
                            }

                            Collections.reverse(output_sorted_by_points);

                            /*
                            Aktualizacja ListView
                             */
                            octoupsAnimatedLegs.setScores(FXCollections.observableArrayList(
                                    output_sorted_by_points
                            ));
                            octoupsAnimatedLegs.getEndScores().setItems(octoupsAnimatedLegs.getScores());
                        }
                );
//----------------------------------------------------------------------------------------------------------------------
                octoupsAnimatedLegs.getSortByTime().setOnAction(
                        (e) -> {
                            System.out.println("sort by time");

                            ArrayList<String> justTimeWithS = new ArrayList<>();
                            ArrayList<String> splitted_scores = new ArrayList<>();
                            ArrayList<Integer> justTime = new ArrayList<>();

                            /*
                            Stworzenie dwóch potrzebnych kontenerów - jeden przechowywać będzie wyłącznie punkty
                            posortowane malejąco. Drugi to kontener zawierający posplitowane wyniki
                            (czyli kolejno zawiera nick, punkty, czas, nick, punkty, czas, nick...)
                             */
                            for(String line : scoresFromFile){
                                String[] user_with_points_and_time = line.split(" ");

                                justTimeWithS.add(user_with_points_and_time[2]);
                                splitted_scores.addAll(Arrays.asList(user_with_points_and_time));
                            }

                            //Wycinamy "s" tak, aby móc traktować Stringa jako Integera
                            for (String jt : justTimeWithS){
                                justTime.add(Integer.parseInt(jt.substring(0, jt.length()-1)));
                            }

                            Collections.sort(justTime);
                            Collections.reverse(justTime);

                            //Punktom dodajemy tymczasowo "x" - nie chcemy, aby były później traktowane jako liczby
                            for (int i = 1; i < splitted_scores.size(); i+=3) {
                                splitted_scores.set(i, splitted_scores.get(i) + "x");
                            }

                            //Czasom w splitted scores odejmujemy "s" - chcemy, aby były później traktowane jako liczby
                            for (int i = 2; i < splitted_scores.size(); i+=3) {
                                splitted_scores.set(i, splitted_scores.get(i).substring(0, splitted_scores.get(i).length()-1));
                            }

                            ArrayList<String> output_sorted_by_time = new ArrayList<>();

                            /*
                            Dla każdego punktu znajdujemy jego pozycję jego odpowiednika w "posplitowanej" tablicy.
                            Ta pozycja "otoczona" jest nickiem i czasem, te wartości Bob ze sobą "skleja".
                            Każdy "punkt" zostaje następnie zamieniony na . (wartość nieosiągalna) tak, aby przy
                            następnej iteracji uniknąć wyszukania tego samego elementu
                             */
                            for (Integer time : justTime){
                                int pos = splitted_scores.indexOf(Integer.toString(time));

                                StringBuilder bobTheBuilder = new StringBuilder();

                                bobTheBuilder.append(splitted_scores.get(pos - 2));
                                bobTheBuilder.append(" ");
                                String remove_x = splitted_scores.get(pos - 1);
                                remove_x = remove_x.substring(0, remove_x.length() - 1);
                                bobTheBuilder.append(remove_x);
                                bobTheBuilder.append(" ");
                                bobTheBuilder.append(splitted_scores.get(pos));
                                bobTheBuilder.append("s");

                                splitted_scores.set(pos, ".");

                                output_sorted_by_time.add(bobTheBuilder.toString());
                            }

                            /*
                            Aktualizacja ListView
                             */
                            octoupsAnimatedLegs.setScores(FXCollections.observableArrayList(
                                    output_sorted_by_time
                            ));
                            octoupsAnimatedLegs.getEndScores().setItems(octoupsAnimatedLegs.getScores());
                        }
                );

                endTime = octoupsAnimatedLegs.getEndTime();
            }
        };
        timer.start();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        enough.setEnough(true);
    }
}

package htl.steyr.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.*;

public class AdventCalendarApp extends Application {

    private final Map<Integer, String> bilder = new HashMap<>();
    private final Map<Integer, String> raetsel = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        initializeContent();

        GridPane grid = new GridPane();

        for (int i = 1; i <= 24; i++) {
            Button button = new Button("Türchen " + i);
            button.setMinSize(100, 100);

            int day = i;
            button.setOnAction(e -> openDoor(day));

            grid.add(button, (i - 1) % 6, (i - 1) / 6);
        }

        Scene scene = new Scene(grid, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setTitle("Adventskalender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openDoor(int day) {
        if (bilder.containsKey(day)) {
            showImage(day);
        } else if (raetsel.containsKey(day)) {
            showRaetsel(day);
        } else {
            showDefault(day);
        }
    }

    private void showImage(int day) {
        String imagePath = bilder.get(day);
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);

        Stage stage = new Stage();
        stage.setScene(new Scene(new StackPane(imageView), 400, 300));
        stage.setTitle("Türchen " + day);
        stage.show();
    }

    private void showRaetsel(int day) {
        String question = raetsel.get(day);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("IT-Rätsel");
        alert.setHeaderText("Türchen " + day);
        alert.setContentText(question);
        alert.showAndWait();
    }

    private void showDefault(int day) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Türchen");
        alert.setHeaderText("Türchen " + day);
        alert.setContentText("Kein Inhalt für dieses Türchen verfügbar.");
        alert.showAndWait();
    }

    private void initializeContent() {
        // Bilder zu drei zufälligen Türchen zuordnen
        List<String> imagePaths = Arrays.asList(
                "/images/image1.png",
                "/images/image2.png",
                "/images/image3.png"
        );

        List<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            days.add(i);
        }
        Collections.shuffle(days);

        // Zufällige Zuweisung der Bilder zu den Türchen
        for (int i = 0; i < imagePaths.size(); i++) {
            bilder.put(days.get(i), imagePaths.get(i));
        }

        // Rätsel hinzufügen: Stelle sicher, dass es 24 Inhalte gibt (Bilder + Rätsel)
        List<String> raetselFragen = Arrays.asList(
                "Was bedeutet 'HTTP'?",
                "Was ist die Hauptsprache von Android-Apps?",
                "Nenne eine Programmiersprache, die 1995 veröffentlicht wurde.",
                "Was bedeutet 'OOP'?",
                "Was ist die Zeitkomplexität von Quicksort im Worst-Case?",
                "Nenne eine Linux-Distribution.",
                "Was bedeutet 'IDE'?",
                "Welches Symbol wird oft für Zeiger in C verwendet?",
                "Nenne einen IT-Pionier, der den Turing-Test erfunden hat.",
                "Welcher Browser wird von Google entwickelt?",
                "Nenne eine Version-Control-Plattform für Git.",
                "Was ist die Standard-Dateiendung für Java-Dateien?",
                "Was ist der Unterschied zwischen HTTP und HTTPS?",
                "Was bedeutet 'SQL'?",
                "Wie heißt das neueste iPhone-Modell (2024)?",
                "Was ist ein Algorithmus?",
                "Was ist der Unterschied zwischen Python 2 und Python 3?",
                "Nenne einen Cloud-Service-Anbieter.",
                "Was ist die größte Programmiersprache weltweit?",
                "Was ist der Zweck von Docker?",
                "Wie viele Bits hat eine IPv4-Adresse?",
                "Was bedeutet 'JSON'?",
                "Was ist ein Framework?",
                "Was ist eine API?"
        );

        Collections.shuffle(raetselFragen);

        int raetselIndex = 0;
        for (int i = 1; i <= 24; i++) {
            if (!bilder.containsKey(i)) {
                if (raetselIndex < raetselFragen.size()) {
                    raetsel.put(i, raetselFragen.get(raetselIndex));
                    raetselIndex++;
                } else {
                    raetsel.put(i, "Kein Rätsel für dieses Türchen verfügbar.");
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

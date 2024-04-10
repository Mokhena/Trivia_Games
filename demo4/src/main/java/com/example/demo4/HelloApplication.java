package com.example.demo4;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static final String[] questions = {
            "What is the capital city of Lesotho?",
            "Which country completely surrounds Lesotho?",
            "What is the highest peak in Lesotho?",
            "What is the traditional Basotho blanket called?",
            "Which river forms part of the border between Lesotho and South Africa?"
    };

    private static final String[][] options = {
            {"Maseru", "Cape Town", "Gaborone", "Maputo"},
            {"South Africa", "Zimbabwe", "Swaziland", "Botswana"},
            {"Thabana Ntlenyana", "Kilimanjaro", "Everest", "Table Mountain"},
            {"Seanamarena", "Kente", "Dashiki", "Kanga"},
            {"Orange River", "Nile River", "Congo River", "Zambezi River"}
    };

    private static final int[] correctAnswers = {0, 0, 0, 0, 0};

    private int currentQuestionIndex = 0;
    private int score = 0;

    private VBox layout;
    private Label questionLabel;
    private Button[] optionButtons;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        questionLabel = new Label();
        resultLabel = new Label();
        optionButtons = new Button[4];

        layout.getChildren().addAll(questionLabel, resultLabel);

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            int finalI = i; // effectively final
            button.setOnAction(event -> handleAnswer(finalI));
            optionButtons[i] = button;
            layout.getChildren().add(optionButtons[i]);
        }

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lesotho Trivia Game");
        primaryStage.show();

        updateQuestion();
    }

    private void updateQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[currentQuestionIndex][i]);
            optionButtons[i].setStyle("-fx-background-color: #CCCCCC;"); // Set button background color
        }
    }

    private void handleAnswer(int selectedOption) {
        if (selectedOption == correctAnswers[currentQuestionIndex]) {
            score++;
            resultLabel.setText("Correct!");
        } else {
            resultLabel.setText("Incorrect. The correct answer is: " + options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            updateQuestion();
        } else {
            showResults();
        }
    }

    private void showResults() {
        for (Button button : optionButtons) {
            layout.getChildren().remove(button);
        }
        resultLabel.setText("Game Over! Your final score is: " + score + "/" + questions.length);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

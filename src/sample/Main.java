package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

public class Main extends Application {

    private Button btnOpenFile = null;
    private Button btnDoCode = null;
    private Button btnSaveFile = null;

    private FileChooser fileChooser = null;


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane root = new StackPane();

        HBox horizBox = new HBox();
        VBox vertBox = new VBox();

        root.getChildren().add(horizBox);
        horizBox.getChildren().add(vertBox);

        btnOpenFile = new Button("Open File");
        btnDoCode = new Button("Do Code");
        btnSaveFile = new Button("Save File");

        vertBox.getChildren().add(btnOpenFile);
        vertBox.getChildren().add(btnDoCode);
        vertBox.getChildren().add(btnSaveFile);

        fileChooser = new FileChooser();
        addFileChooserListeners();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void addFileChooserListeners() {
        btnOpenFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle("Выбор файла");
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    System.out.println(selectedFile.getAbsolutePath());
//                    System.out.println(selectedFile.getCanonicalPath());
                    System.out.println(selectedFile.getPath());
                }
                else
                    System.out.println("file is not valid");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Button btnOpenFile = null;
    private Button btnDoCode = null;
    private Button btnSaveFile = null;

    private HBox horizBox = null;
    private VBox vertBox = null;

    private FileChooser fileChooser = null;
    private File selectedFile = null;

    private BufferedImage image = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane root = new StackPane();

        horizBox = new HBox();
        vertBox = new VBox();

        vertBox.setSpacing(10);
        horizBox.setSpacing(50);
        horizBox.setPadding(new Insets(100,50,100,50));

        root.getChildren().add(horizBox);
        horizBox.getChildren().add(vertBox);

        btnOpenFile = new Button("Open File");
        btnOpenFile.setMinWidth(100);
        btnDoCode = new Button("Do Code");
        btnDoCode.setMinWidth(100);
        btnSaveFile = new Button("Save File");
        btnSaveFile.setMinWidth(100);

        vertBox.getChildren().add(btnOpenFile);
        vertBox.getChildren().add(btnDoCode);
        vertBox.getChildren().add(btnSaveFile);

        fileChooser = new FileChooser();
        addFileChooserListeners();

        primaryStage.setTitle("Моя первая программа на JavaFX, не бейте");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    private void addFileChooserListeners() {
        btnOpenFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle("Выбор файла");
                selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    System.out.println(selectedFile.getAbsolutePath());
                }
                else {
                    System.out.println("file is not valid");
                    return;
                }
                if (horizBox.getChildren().size() > 1){
                    horizBox.getChildren().remove(1);
                }
                try {
                    image = ImageIO.read(selectedFile);
                    ImageView iv = new ImageView();
                    iv.setImage(SwingFXUtils.toFXImage(image, null));
                    horizBox.getChildren().add(iv);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnDoCode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (image == null) {
                    return 0;
                }
                int pixelBuffer[] = new int[image.getWidth() * image.getHeight()];
                for (int y = 0; y < image.getHeight(); y++)
                    for (int x = 0; x < image.getWidth(); x++)
                        pixelBuffer[y * image.getWidth() + x] = image.getRGB(x,y);
                // here is the func

                for (int y = 0; y < image.getHeight(); y++)
                    for (int x = 0; x < image.getWidth(); x++)
                        image.setRGB(x,y,pixelBuffer[y * image.getWidth() + x]);

                horizBox.getChildren().remove(1);
                ImageView iv = new ImageView();
                iv.setImage(SwingFXUtils.toFXImage(image, null));
                horizBox.getChildren().add(iv);
            }
        });

        btnSaveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {




            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

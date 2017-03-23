package com.blinkfox.learn.javafx.ui;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Scroll Pane使用示例.
 * Created by blinkfox on 2017-03-23.
 */
public class ScrollPaneSample extends Application {

    private final ScrollPane sp = new ScrollPane();
    private final Image[] images = new Image[5];
    private final ImageView[] pics = new ImageView[5];
    private final VBox vb = new VBox();
    private final Label fileName = new Label();
    private static final String [] imageNames = new String [] {"/javafx/image/fw1.jpg", "/javafx/image/fw2.jpg",
            "/javafx/image/fw3.jpg", "/javafx/image/fw4.jpg", "/javafx/image/fw5.jpg"};

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox box = new VBox();
        Scene scene = new Scene(box, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scroll Pane使用示例");
        box.getChildren().addAll(sp, fileName);
        VBox.setVgrow(sp, Priority.ALWAYS);

        fileName.setLayoutX(30);
        fileName.setLayoutY(160);

        Image roses = new Image(getClass().getResourceAsStream("/javafx/image/roses.jpg"));
        sp.setContent(new ImageView(roses));
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (int i = 0; i < 5; i++) {
            images[i] = new Image(getClass().getResourceAsStream(imageNames[i]));
            pics[i] = new ImageView(images[i]);
            pics[i].setFitWidth(100);
            pics[i].setPreserveRatio(true);
            vb.getChildren().add(pics[i]);
        }

        sp.setVmax(440);
        sp.setPrefSize(115, 150);
        sp.setContent(vb);
        sp.vvalueProperty().addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
            fileName.setText(imageNames[(newVal.intValue() - 1) / 100]);
        });
        primaryStage.show();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}
package com.blinkfox.test.other;

import java.time.LocalTime;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.pmw.tinylog.Logger;

/**
 * Timeline test.
 * Created by blinkfox on 2017-03-23.
 */
public class TimelineTest extends Application {

    /**
     * start 方法.
     * @param primaryStage 主stage
     * @throws Exception 异常
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Logger.info("开始执行定时任务..." + LocalTime.now());
        timeline();
        Logger.info("执行了..." + LocalTime.now());
    }

    /**
     * timline 方法.
     * JavaFx下才行
     */
    private static void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),
                event -> Logger.info("这是每5秒调用一次的线程，时间为:" + LocalTime.now())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}
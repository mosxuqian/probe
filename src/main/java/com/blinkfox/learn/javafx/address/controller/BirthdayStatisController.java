package com.blinkfox.learn.javafx.address.controller;

import com.blinkfox.learn.javafx.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 生日统计图控制器
 * Created by blinkfox on 2017-03-17.
 */
public class BirthdayStatisController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * FXML载入时执行，生成横坐标的月份值
     */
    @FXML
    private void initialize() {
        // 获取中文月份的数组
        String[] months = DateFormatSymbols.getInstance(Locale.CHINESE).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
    }

    /**
     * 设置persons的集合到统计表中
     * @param persons person集合
     */
    public void setPersons(List<Person> persons) {
        int[] monthCounter = new int[12];

        // 获取各月的值
        if (persons != null && !persons.isEmpty()) {
            for (Person person: persons) {
                int month = person.getBirthday().getMonthValue() - 1;
                monthCounter[month]++;
            }
        }

        // 遍历创建 XYChart.Data 对象数据，并将其添加到 series 中
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }

}
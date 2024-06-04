/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.gui;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * class for BenchmarkingFX
 */
public class BenchmarkingFX {

    /**
     * class for Benchmark()
     */
    public static class BenchMarkerFX extends Application {

        private static final int WIDTH = 800;
        private static final int HEIGHT = 600;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {

            // final constants
            final Map<String, String> params = getParameters().getNamed();
            final String output = params.get("output");
            final String implementation = params.get("implementation");
            final int startSize = Integer.parseInt(params.get("startSize"));
            final String operation = params.get("operation");
            final int numberOfSamples = Integer.parseInt(params.get("numberOfSamples"));
            final int multiplier = Integer.parseInt(params.get("multiplier"));

            // setting up the long arrays
            long[] runTimes = DataStructuresBenchmark.runBenchmarks(implementation,
                    operation, startSize,
                    multiplier, numberOfSamples);
            long[] retLongArray = new long[numberOfSamples];
            int currentSize = startSize;


            // filling up the long arrays
            for (int i = 0; i < numberOfSamples; i++) {
                currentSize = currentSize * multiplier;
                retLongArray[i] = currentSize;
            }

            // graphing up the chart
            LineChart<Number, Number> lineChart = makeLineChart("add() Ordered List",
                    "Sizes", "RunTimes");
            XYChart.Series<Number, Number> graphChart = makeSeries(implementation,
                    retLongArray, runTimes);
            lineChart.getData().add(graphChart);


            // setting up the scene
            Scene scene = new Scene(lineChart, WIDTH, HEIGHT);
            lineChart.applyCss();
            lineChart.layout();
            stage.setScene(scene);
            stage.setTitle("Example Line Plots");
            stage.show();
            try {
                saveScreenShot(output, scene);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Saving Plot");
                alert.setContentText(e.getMessage());
                alert.show();
            }

        }


        /**
         * code for XYChart
         *
         * @param lineName lineName
         * @param xAxis    xAxis
         * @param yAxis    yAxis
         * @return ret
         */
        public static XYChart.Series<Number, Number> makeSeries(String lineName,
                                                                long[] xAxis, long[] yAxis) {
            XYChart.Series<Number, Number> ret = new XYChart.Series<>();
            ret.setName(lineName);
            for (int i = 0; i < xAxis.length; i += 1) {
                ret.getData().add(new XYChart.Data<>(xAxis[i], yAxis[i]));
            }
            return ret;
        }

        /**
         * method for LineChart
         *
         * @param title      title
         * @param xAxisLabel xAxisLabel
         * @param yAxisLabel yAxisLabel
         * @return lineChart
         */
        private static LineChart<Number, Number> makeLineChart(String title, String xAxisLabel,
                                                               String yAxisLabel) {
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel(xAxisLabel);
            yAxis.setLabel(yAxisLabel);
            final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setAnimated(false);
            lineChart.setTitle(title);
            return lineChart;
        }

        /**
         * method for saveScreenShot
         *
         * @param filename fileName
         * @param scene    scene
         * @throws IOException e
         */
        private static void saveScreenShot(String filename, Scene scene) throws IOException {
            WritableImage image = scene.snapshot(null);
            File file = new File(filename == null ? "face.png" : filename);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        }
    }
}

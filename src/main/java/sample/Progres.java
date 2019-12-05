package sample;


import java.io.File;
        import java.util.List;

import filezip.ZipUtil;
import javafx.application.Application;
        import javafx.concurrent.WorkerStateEvent;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.geometry.Insets;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.ProgressBar;
        import javafx.scene.control.ProgressIndicator;
        import javafx.scene.layout.FlowPane;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

public class Progres extends Application {

    private ZipUtil copyTask;

    @Override
    public void start(Stage primaryStage) {

        final Label label = new Label("Copy files:");
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);

        final Button startButton = new Button("Start");
        final Button cancelButton = new Button("Cancel");

        final Label statusLabel = new Label();
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);

        // Start Button.
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startButton.setDisable(true);
                progressBar.setProgress(0);
                progressIndicator.setProgress(0);
                cancelButton.setDisable(false);

                // Create a Task.
                copyTask = new ZipUtil();

                // Unbind progress property
                progressBar.progressProperty().unbind();

                // Bind progress property
                progressBar.progressProperty().bind(copyTask.progressProperty());

                // Hủy bỏ kết nối thuộc tính progress
                progressIndicator.progressProperty().unbind();

                // Bind progress property.
                progressIndicator.progressProperty().bind(copyTask.progressProperty());

                // Unbind text property for Label.
                statusLabel.textProperty().unbind();

                // Bind the text property of Label
                // with message property of Task
                statusLabel.textProperty().bind(copyTask.messageProperty());

                // When completed tasks
                copyTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                        new EventHandler<WorkerStateEvent>() {

                            @Override
                            public void handle(WorkerStateEvent t) {
                                List<File> copied = copyTask.getValue();
                                statusLabel.textProperty().unbind();
                                statusLabel.setText("Copied: " + copied.size());
                            }
                        });

                // Start the Task.
                new Thread(copyTask).start();

            }
        });
    }}
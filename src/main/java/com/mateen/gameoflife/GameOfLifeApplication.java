package com.mateen.gameoflife;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Paths;

import com.mateen.gameoflife.constant.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.mateen.gameoflife.controller.Controller;
import com.mateen.gameoflife.model.GameOfLife;
import com.mateen.gameoflife.model.Grid;

/**
 * Entry point for <i>The Game of Life</i> application.
 *
 * @author Mike Lowe
 */
public class GameOfLifeApplication extends Application {

  private final GameOfLife gameOfLife;
  private FXMLLoader fxmlLoader;
  private Stage primaryStage;
  private Parent view;

  /**
   * Creates a new {@code GameOfLifeApplication} instance.
   */
  public GameOfLifeApplication() {
    this(new GameOfLife(new Grid(Constants.ROWS, Constants.COLUMNS)));
  }

  /**
   * Creates a new {@code GameOfLifeApplication} instance given a {@link GameOfLife} instance.
   *
   * @param gameOfLife the {@link GameOfLife} instance
   * @throws NullPointerException if {@code gameOfLife} is {@code null}
   */
  public GameOfLifeApplication(GameOfLife gameOfLife) {
    this.gameOfLife = requireNonNull(gameOfLife, "game of life is null");
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    initializePrimaryStage(primaryStage);
    initializeFxmlLoader();
    initializeView();
    initializeController();
    showScene();
  }

  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(Constants.GAME_NAME);
    this.primaryStage.setOnCloseRequest(event -> Platform.exit());
    this.primaryStage.setResizable(false);
    this.primaryStage.sizeToScene();
  }

  private void initializeFxmlLoader() throws Exception{
    fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(Paths.get(Constants.VIEW_PATH).toUri().toURL());
  }

  private void initializeView() throws IOException {
    view = fxmlLoader.load();
  }

  private void initializeController() {
    Controller controller = fxmlLoader.getController();
    controller.setGameOfLife(gameOfLife);



  }

  private void showScene() {
    primaryStage.setScene(new Scene(view));
    primaryStage.show();
  }
}

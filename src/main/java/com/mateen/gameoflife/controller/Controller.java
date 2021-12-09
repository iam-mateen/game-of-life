package com.mateen.gameoflife.controller;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import com.mateen.gameoflife.model.Cell;
import com.mateen.gameoflife.model.GameOfLife;
import com.mateen.gameoflife.model.Grid;
import com.mateen.gameoflife.model.Speed;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class Controller {

  public static final String CELL_PANE_STYLE_CLASS = "cell-pane";
  public static final String ALIVE_STYLE_CLASS = "alive";

  private static final double CELL_SIZE = 14;


  @FXML
  public AnchorPane rootPane;
  @FXML
  public Label generationNumberLabel;
  @FXML
  public GridPane gridPane;

  public GameOfLife gameOfLife;

  @FXML
  public void initialize() {
  }



  public void setGameOfLife(GameOfLife gameOfLife) {
    this.gameOfLife = requireNonNull(gameOfLife, "game of life is null");
    setGenerationNumberLabelTextProperty();
    initializeGridPane();
  }

  private void setGenerationNumberLabelTextProperty() {
    generationNumberLabel.textProperty().bind(gameOfLife.generationProperty().asString());
  }

  private void initializeGridPane() {
    Grid grid = gameOfLife.getGrid();
    int numberOfRows = grid.getNumberOfRows();
    int numberOfColumns = grid.getNumberOfColumns();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        addCellPane(rowIndex, columnIndex);
      }
    }
  }

  private void addCellPane(int rowIndex, int columnIndex) {
    Pane cellPane = new Pane();
    addCellPaneStyle(cellPane);
    addAlivePropertyListener(rowIndex, columnIndex, cellPane);
    setAliveStyle(cellPane, gameOfLife.getGrid().getCell(rowIndex, columnIndex).isAlive());
    addClickEventHandler(rowIndex, columnIndex, cellPane);

    gridPane.add(cellPane, columnIndex, rowIndex);
  }

  private void addCellPaneStyle(Pane cellPane) {
    cellPane.setPrefSize(CELL_SIZE, CELL_SIZE);
    GridPane.setFillHeight(cellPane, true);
    GridPane.setFillWidth(cellPane, true);
    cellPane.getStyleClass().add(CELL_PANE_STYLE_CLASS);
  }

  private void addAlivePropertyListener(int rowIndex, int columnIndex, Pane cellPane) {
    BooleanProperty aliveProperty = gameOfLife.getGrid().getCell(rowIndex, columnIndex)
        .aliveProperty();
    aliveProperty.addListener((observable, oldValue, newValue) ->
        setAliveStyle(cellPane, newValue));
  }

  private void setAliveStyle(Pane cellPane, boolean isAlive) {
    ObservableList<String> styleClass = cellPane.getStyleClass();
    if (isAlive) {
      styleClass.add(ALIVE_STYLE_CLASS);
    } else {
      styleClass.remove(ALIVE_STYLE_CLASS);
    }
  }

  private void addClickEventHandler(int rowIndex, int columnIndex, Pane cellPane) {
    Cell cell = gameOfLife.getGrid().getCell(rowIndex, columnIndex);
    cellPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cell.toggleAlive());
  }

  @FXML
  public void playButtonAction() {
    gameOfLife.play();
  }

  @FXML
  public void pauseButtonAction() {
    gameOfLife.pause();
  }

  @FXML
  public void resetButtonAction() {
    gameOfLife.reset();
  }

  @FXML
  public void clearButtonAction() {
    gameOfLife.clear();
  }

//  @FXML
//  public void mediumToggleButtonAction() {
//    gameOfLife.setSpeed(Speed.MEDIUM);
//  }
//
//  @FXML
//  public void fastToggleButtonAction() {
//    gameOfLife.setSpeed(Speed.FAST);
//  }

}

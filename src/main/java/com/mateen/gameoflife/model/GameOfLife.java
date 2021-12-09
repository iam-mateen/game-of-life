package com.mateen.gameoflife.model;

import static java.util.Objects.requireNonNull;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameOfLife {

  private static final Random RANDOM = new Random();

  private final Grid grid;
  private final ReadOnlyLongWrapper generation = new ReadOnlyLongWrapper();
  private Timeline timeline;
  private final ObjectProperty<Speed> speed = new SimpleObjectProperty<>(Speed.MEDIUM);

  public GameOfLife(Grid grid) {
    this.grid = requireNonNull(grid, "grid is null");
    updateTimeline();
    addSpeedPropertyListener();
    grid.randomGeneration(RANDOM);
  }

  private void updateTimeline() {
    Duration duration = new Duration(speed.get().getMilliseconds());
    EventHandler<ActionEvent> eventHandler = event -> next();
    KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
    timeline = new Timeline(keyFrame);
    timeline.setCycleCount(Animation.INDEFINITE);
  }

  public void next() {
    grid.nextGeneration();
    generation.set(getGeneration() + 1);
  }

  private void addSpeedPropertyListener() {
    speed.addListener((observable, oldValue, newValue) -> {
      boolean shouldPlay = timeline.getStatus() == Status.RUNNING;
      pause();
      updateTimeline();
      if (shouldPlay) {
        play();
      }
    });
  }

  public long getGeneration() {
    return generation.get();
  }

  public ReadOnlyLongProperty generationProperty() {
    return generation.getReadOnlyProperty();
  }

  public Grid getGrid() {
    return grid;
  }

  public void setSpeed(Speed speed) {
    this.speed.set(requireNonNull(speed, "speed is null"));
  }

  public void play() {
    timeline.play();
  }

  public void pause() {
    timeline.pause();
  }

  public void clear() {
    pause();
    grid.clear();
    generation.set(0);
  }

  public void reset() {
    clear();
    grid.randomGeneration(RANDOM);
  }
}

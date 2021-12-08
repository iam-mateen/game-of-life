package com.mateen.gameoflife.controller;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class PersistentToggleGroup extends ToggleGroup {

  public PersistentToggleGroup() {
    getToggles().addListener((Change<? extends Toggle> change) -> {
      while (change.next()) {
        for (Toggle toggle : change.getAddedSubList()) {
          ToggleButton toggleButton = (ToggleButton) toggle;
          toggleButton.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            if (toggleButton.equals(getSelectedToggle())) {
              mouseEvent.consume();
            }
          });
        }
      }
    });
  }
}

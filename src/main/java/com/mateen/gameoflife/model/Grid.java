package com.mateen.gameoflife.model;

import static java.util.Objects.requireNonNull;
import static com.mateen.gameoflife.util.Utils.requirePositiveNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Grid {

  private final int numberOfRows;
  private final int numberOfColumns;
  private final Cell[][] cells;

  public Grid(int numberOfRows, int numberOfColumns) {
    this.numberOfRows = requirePositiveNumber(numberOfRows, "number of rows is " + numberOfRows);
    this.numberOfColumns =
        requirePositiveNumber(numberOfColumns, "number of columns is " + numberOfColumns);
    this.cells = createCells();
  }

  private Cell[][] createCells() {
    Cell[][] cells = new Cell[getNumberOfRows()][getNumberOfColumns()];
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        cells[rowIndex][columnIndex] = new Cell();
      }
    }
    return cells;
  }

  public Cell getCell(int rowIndex, int columnIndex) {
    return cells[getWrappedRowIndex(rowIndex)][getWrappedColumnIndex(columnIndex)];
  }

  private int getWrappedRowIndex(int rowIndex) {
    return (rowIndex + getNumberOfRows()) % getNumberOfRows();
  }

  private int getWrappedColumnIndex(int columnIndex) {
    return (columnIndex + getNumberOfColumns()) % getNumberOfColumns();
  }

  public int getNumberOfRows() {
    return numberOfRows;
  }

  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  public void nextGeneration() {
    goToNextState(calculateNextState());
  }

  private boolean[][] calculateNextState() {
    boolean[][] nextState = new boolean[getNumberOfRows()][getNumberOfColumns()];

    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        Cell cell = getCell(rowIndex, columnIndex);
        int numberOfAliveNeighbours = countAliveNeighbours(rowIndex, columnIndex);
        boolean isAliveInNextState =
            ((cell.isAlive() && numberOfAliveNeighbours == 2) || numberOfAliveNeighbours == 3);
        nextState[rowIndex][columnIndex] = isAliveInNextState;
      }
    }

    return nextState;
  }

  private int countAliveNeighbours(int rowIndex, int columnIndex) {
    return (int) getNeighbours(rowIndex, columnIndex)
        .stream()
        .filter(Cell::isAlive)
        .count();
  }

  private List<Cell> getNeighbours(int rowIndex, int columnIndex) {
    int north = rowIndex - 1;
    int east = columnIndex + 1;
    int south = rowIndex + 1;
    int west = columnIndex - 1;

    return Arrays.asList(
        getCell(north, west),
        getCell(north, columnIndex),
        getCell(north, east),
        getCell(rowIndex, east),
        getCell(south, east),
        getCell(south, columnIndex),
        getCell(south, west),
        getCell(rowIndex, west)
    );
  }

  private void goToNextState(boolean[][] nextState) {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        getCell(rowIndex, columnIndex).setAlive(nextState[rowIndex][columnIndex]);
      }
    }
  }

  public void clear() {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        getCell(rowIndex, columnIndex).setAlive(false);
      }
    }
  }

  public void randomGeneration(Random random) {
    requireNonNull(random, "random is null");
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        getCell(rowIndex, columnIndex).setAlive(random.nextBoolean());
      }
    }
  }
}

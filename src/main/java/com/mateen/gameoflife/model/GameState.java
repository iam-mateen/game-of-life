package com.mateen.gameoflife.model;


public class GameState {
    private float speed;
    private int score;
    private int magnification;

    public GameState() {

    }

    public GameState(float speed, int score, int magnification) {
        this.speed = speed;
        this.score = score;
        this.magnification = magnification;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMagnification() {
        return magnification;
    }

    public void setMagnification(int magnification) {
        this.magnification = magnification;
    }
}

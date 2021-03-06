package model;

import com.sun.javafx.geom.Ellipse2D;
import javafx.geometry.Point2D;

/**
 * The GameModel saves data about the game, including the racecar.
 * It handles most of the calculations for the racegame.
 */
public class GameModel {
    public final int NUM_OBSTACLES = 10;
    public static String WELCOME_MSG = "Welcome to the game!\nDrive the car into the Finish Line without touching the obstacles!\nDon't forget to pass the Checkpoint!\n\nUse the arrow keys to drive the car.\nPress P to pause and R to restart the game.";
    public static String PAUSE_MSG = "Drive the car into the Finish Line without touching the obstacles!\nDon't forget to pass the Checkpoint!\n\nUse the arrow keys to drive the car.\nPress P to resume and R to restart the game.";

    /**
     * The car that is driven on the racetrack
     */
    private Car car;
    private Obstacle[][] obstacles;
    private Ellipse2D raceTrackInner, raceTrackOuter;

    public boolean checkpointPassed;
    public boolean startingLinePassed;
    public boolean gamePaused;
    /**
     * Creates a gameModel, that handles most of the actions
     */
    public GameModel() {
        //initialize Car, default data in GameView
        car = initializeCar();
        raceTrackInner = new Ellipse2D(200, 150, 900, 500);
        raceTrackOuter = new Ellipse2D(100, 50, 1100, 700);
        generateObstacles();
        checkpointPassed = false;
        startingLinePassed = false;
    }

    /**
     * Initializes a car with the initial values
     *
     * @return the initialized car
     */
    private Car initializeCar() {
        //initialize a new car and give it the init values set in the static variables
        car = new Car();
        return car;
    }
    /**
     * Notifies the car to update its speed, rotation and position.
     * Calculates if the car is still on the RaceTrack.
     *
     * @param newPos current position of the car on the screen
     */
    public void updateCar(Point2D newPos) {
        car.updateValues(newPos);
        car.isOnTrack = raceTrackOuter.contains((float)car.getPosition().getX(), (float)car.getPosition().getY())
                && !raceTrackInner.contains((float)car.getPosition().getX(), (float)car.getPosition().getY());
    }

    /**
     * Sets RotationStatus to NONE if the Key is being released. Otherwise it keeps rotating.
     *
     * @param release boolean that is set to true every time a KeyEvent stops
     */
    public void rotateLeft(boolean release){
        car.rotationStatus = release ? Car.RotationStatus.NONE : Car.RotationStatus.LEFT;
    }

    public void rotateRight(boolean release){
        car.rotationStatus = release ? Car.RotationStatus.NONE : Car.RotationStatus.RIGHT;
    }

    public double getCarRotation(){
        return car.getRotation();
    }

    /**
     * Sets accelarationStatus to NONE if the Key is being released. Otherwise it keeps accelerating.
     *
     * @param release boolean that is set to true every time a KeyEvent stops
     */
    public void accelerate(boolean release){
        car.accelerationStatus = release ? Car.AccelerationStatus.NONE : Car.AccelerationStatus.ACCELERATING;
    }

    /**
     *Sets AccelarationStatus to NONE if the Key is being  released. Otherwis it keeps braking.
     *
     * @param release boolean tha is set to true every time a KeyEvent stops
     */
    public void brake(boolean release){
        car.accelerationStatus = release ? Car.AccelerationStatus.NONE : Car.AccelerationStatus.BRAKING;
    }

    /**
     * Sets Car to 0 everytime a Obstacle is being hit.
     */
    public void hitObstacle() {
        car.hitObstacle();
    }

    public double getCarSpeed(){
        return car.getSpeed();
    }
    /**
     * Sets up the area in which the obstacles are being placed
     */
    private void generateObstacles() {
        obstacles = new Obstacle[4][NUM_OBSTACLES];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < NUM_OBSTACLES; j++) {
                obstacles[i][j] = new Obstacle(i);
//                obstacles[i][j] = new Obstacle(50, 1250, 50, 750);
            }
        }
    }
    public Obstacle[][] getObstacles() {
        return obstacles;
    }

    /**
     * Sets up a new Car, RaceTrack, Obstacles and sets the Checkpoint and Starting Line to the beginning state.
     */
    public void reset() {
        car = initializeCar();
        raceTrackInner = new Ellipse2D(200, 150, 900, 500);
        raceTrackOuter = new Ellipse2D(100, 50, 1100, 700);
        generateObstacles();
        checkpointPassed = false;
        startingLinePassed = false;
    }
}

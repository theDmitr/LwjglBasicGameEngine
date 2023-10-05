package dmitr.basic.core.util;

public class Timer {

    private double lastLoopTime;

    public void init() {
        lastLoopTime = getTime();
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }

    public double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }

}

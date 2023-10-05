package dmitr.basic.core.engine;

public class GameEngineProperties {

    private int FPS;
    private int UPS;

    public GameEngineProperties(int FPS, int UPS) {
        this.FPS = FPS;
        this.UPS = UPS;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int getUPS() {
        return UPS;
    }

    public void setUPS(int UPS) {
        this.UPS = UPS;
    }

}

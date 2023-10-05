package dmitr.basic.core.engine;

import dmitr.basic.core.input.MouseInput;
import dmitr.basic.core.log.LogCore;
import dmitr.basic.core.log.LogType;
import dmitr.basic.core.util.Timer;
import dmitr.basic.core.window.Window;

import static java.lang.Thread.sleep;

public class GameEngine implements Runnable {

    private final Window window;
    private final Timer timer;
    private final IGameLogic gameLogic;
    private final MouseInput mouseInput;
    private final GameEngineProperties gameEngineProperties;

    public GameEngine(Window window, GameEngineProperties gameEngineProperties, IGameLogic gameLogic) {
        this.window = window;
        this.gameEngineProperties = gameEngineProperties;
        this.gameLogic = gameLogic;
        this.timer = new Timer();
        this.mouseInput = new MouseInput();
    }

    @Override
    public void run() {
        try {
            init();
            loop();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    protected void init() throws Exception {
        LogCore.log("Engine initialization has been started!", LogType.INFO);
        window.init();
        timer.init();
        gameLogic.init(window);
        mouseInput.init(window);
        LogCore.log("Engine initialization complete!", LogType.INFO);
    }

    protected void loop() {
        float elapsedTime;
        float accumulator = 0.0f;
        float interval = 1.0f / gameEngineProperties.getUPS();

        while (!window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.windowProperties.isVSync())
                sync();
        }
    }

    protected void input() {
        mouseInput.input(window);
        gameLogic.input(window, mouseInput);
    }

    protected void sync() {
        float loopSlot = 1f / gameEngineProperties.getFPS();
        double endTime = timer.getLastLoopTime() + loopSlot;

        while (timer.getTime() < endTime) {
            try {
                sleep(1);
            } catch (InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    protected void update(float interval) {
        gameLogic.update(interval, mouseInput);
    }

    protected void render() {
        gameLogic.render(window);
        window.update();
    }

}

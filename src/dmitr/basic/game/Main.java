package dmitr.basic.game;

import dmitr.basic.core.engine.GameEngine;
import dmitr.basic.core.engine.GameEngineProperties;
import dmitr.basic.core.engine.IGameLogic;
import dmitr.basic.core.window.Window;
import dmitr.basic.core.window.WindowProperties;

public class Main {

    public static void main(String[] args) {
        WindowProperties windowProperties = new WindowProperties("MyWindow", 1280, 720, true);
        GameEngineProperties gameEngineProperties = new GameEngineProperties(60, 60);

        Window window = new Window(windowProperties);
        IGameLogic gameLogic = new Game();
        GameEngine gameEngine = new GameEngine(window, gameEngineProperties, gameLogic);

        try {
            gameEngine.run();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }
    }

}

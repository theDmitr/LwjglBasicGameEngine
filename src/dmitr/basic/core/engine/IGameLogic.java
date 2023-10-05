package dmitr.basic.core.engine;

import dmitr.basic.core.input.MouseInput;
import dmitr.basic.core.window.Window;

public interface IGameLogic {

    void init(Window window) throws Exception;

    void input(Window window, MouseInput mouseInput);

    void update(float interval, MouseInput mouseInput);

    void render(Window window);

    void cleanup();

}

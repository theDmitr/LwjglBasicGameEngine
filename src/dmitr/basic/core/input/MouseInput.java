package dmitr.basic.core.input;

import dmitr.basic.core.window.Window;
import org.joml.Vector2d;
import org.joml.Vector2f;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

    private final Vector2d previousPos;
    private final Vector2d currentPos;
    private final Vector2f displayVector;

    private boolean inWindow = false;
    private final boolean[] buttonPressed = new boolean[8];

    public MouseInput() {
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0, 0);
        displayVector = new Vector2f();
        Arrays.fill(buttonPressed, false);
    }

    public void init(Window window) {
        long hWnd = window.getHWnd();

        glfwSetCursorPosCallback(hWnd, (hWin, xPos, yPos) -> {
            currentPos.x = xPos;
            currentPos.y = yPos;
        });

        glfwSetCursorEnterCallback(hWnd, (hWin, entered) -> {
            inWindow = entered;
        });

        glfwSetMouseButtonCallback(hWnd, (hWin, button, action, mode) -> {
            for (int i = 0; i < buttonPressed.length; i++)
                buttonPressed[i] = button == i && action == GLFW_PRESS;
        });
    }

    public Vector2f getDisplayVector() {
        return displayVector;
    }

    public void input(Window window) {
        displayVector.x = 0;
        displayVector.y = 0;

        if (currentPos.x > 0 && currentPos.y > 0 && inWindow) {
            double deltaX = currentPos.x - previousPos.x;
            double deltaY = currentPos.y - previousPos.y;

            boolean rotateX = deltaX != 0;
            boolean rotateY = deltaY != 0;

            if (rotateX)
                displayVector.y = (float) deltaX;
            if (rotateY)
                displayVector.x = (float) deltaY;

            previousPos.x = currentPos.x;
            previousPos.y = currentPos.y;
        }
    }

    public boolean isButtonPressed(int button) {
        return buttonPressed[button];
    }

}

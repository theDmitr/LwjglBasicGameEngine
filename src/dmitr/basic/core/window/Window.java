package dmitr.basic.core.window;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    public final WindowProperties windowProperties;
    private long hWnd;

    public Window(WindowProperties windowProperties) {
        this.windowProperties = windowProperties;
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("GLFW initialization error!");

        int MAJOR = 4;
        int MINOR = 6;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, MAJOR);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, MINOR);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        hWnd = glfwCreateWindow(
                windowProperties.getWidth(),
                windowProperties.getHeight(),
                windowProperties.getTitle(),
                NULL, NULL
        );

        if (hWnd == NULL)
            throw new RuntimeException("Failed to create window!");

        glfwSetFramebufferSizeCallback(hWnd, (window, width, height) -> {
            windowProperties.setWidth(width);
            windowProperties.setHeight(height);
            windowProperties.setResized(true);
        });

        glfwSetKeyCallback(hWnd, (window, key, scanCode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(hWnd, true);
        });

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert vidMode != null;

        glfwSetWindowPos(
                hWnd,
                (vidMode.width() - windowProperties.getWidth()) / 2,
                (vidMode.height() - windowProperties.getHeight()) / 2
        );

        glfwMakeContextCurrent(hWnd);

        if (windowProperties.isVSync())
            glfwSwapInterval(1);

        glfwShowWindow(hWnd);

        createCapabilities();
    }

    public long getHWnd() {
        return hWnd;
    }

    public void update() {
        glfwSwapBuffers(hWnd);
        glfwPollEvents();
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(hWnd);
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(hWnd, keyCode) == GLFW_PRESS;
    }

}

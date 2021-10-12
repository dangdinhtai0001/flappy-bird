package com.phoenix.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.phoenix.flappybird.FlappyBird;
import com.phoenix.flappybird.config.ApplicationConfig;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config(config);
        new LwjglApplication(new FlappyBird(), config);
    }

    private static void config(LwjglApplicationConfiguration config) {
        config.width = ApplicationConfig.WIDTH;
        config.height = ApplicationConfig.HEIGHT;
        config.title = ApplicationConfig.TITLE;
    }

    ;
}

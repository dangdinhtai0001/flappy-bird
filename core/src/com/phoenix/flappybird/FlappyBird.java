package com.phoenix.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.phoenix.flappybird.states.impl.DefaultGameStateManager;
import com.phoenix.flappybird.states.GameStateManager;
import com.phoenix.flappybird.states.impl.MenuState;

public class FlappyBird extends ApplicationAdapter {
    private GameStateManager gameStateManager;
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameStateManager = new DefaultGameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);

        gameStateManager.push(new MenuState(gameStateManager));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

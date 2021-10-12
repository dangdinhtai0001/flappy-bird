package com.phoenix.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mousePosition;
    protected GameStateManager manager;

    public State(GameStateManager manager) {
        this.manager = manager;
        this.mousePosition = new Vector3();
        this.camera = new OrthographicCamera();
    }

    protected abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}

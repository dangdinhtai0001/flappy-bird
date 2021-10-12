package com.phoenix.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameStateManager {
    void push(State state);

    void pop();

    void set(State state);

    void update(float delta);

    void render(SpriteBatch batch);
}

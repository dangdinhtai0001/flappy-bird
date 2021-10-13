package com.phoenix.flappybird.states.impl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.phoenix.flappybird.states.GameStateManager;
import com.phoenix.flappybird.states.State;

import java.util.Stack;

public class DefaultGameStateManager implements GameStateManager {
    private final Stack<State> states;

    public DefaultGameStateManager(Stack<State> states) {
        this.states = states;
    }

    public DefaultGameStateManager() {
        this.states = new Stack<>();
    }

    @Override
    public void push(State state) {
        states.push(state);
    }

    @Override
    public void pop() {
        states.pop().dispose();
    }

    @Override
    public void set(State state) {
        states.pop();
        states.push(state);
    }

    @Override
    public void update(float delta) {
        states.peek().update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }
}

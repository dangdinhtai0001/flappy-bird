package com.phoenix.flappybird.states.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.phoenix.flappybird.config.ApplicationConfig;
import com.phoenix.flappybird.states.GameStateManager;
import com.phoenix.flappybird.states.State;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager manager) {
        super(manager);

        this.background = new Texture("bg.png");
        this.playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            this.manager.set(new PlayState(this.manager));
            dispose();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0, ApplicationConfig.WIDTH, ApplicationConfig.HEIGHT);
        //noinspection IntegerDivisionInFloatingPointContext
        spriteBatch.draw(playBtn, (ApplicationConfig.WIDTH / 2) - (playBtn.getWidth() / 2), ApplicationConfig.HEIGHT / 2);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();

        System.out.println("Menu state is disposed");
    }
}

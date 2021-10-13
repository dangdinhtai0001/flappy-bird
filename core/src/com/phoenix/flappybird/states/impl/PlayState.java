package com.phoenix.flappybird.states.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.phoenix.flappybird.config.ApplicationConfig;
import com.phoenix.flappybird.sprites.Bird;
import com.phoenix.flappybird.sprites.Score;
import com.phoenix.flappybird.sprites.Tube;
import com.phoenix.flappybird.states.GameStateManager;
import com.phoenix.flappybird.states.State;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int START_UP_SPACING = 250; // Khoảng cách khởi động mà tube vẫn chưa xuất hiện

    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Score scoreSprite;

    private Texture background;
    private Texture ground;
    private Texture gameoverImg;

    private Vector2 positionGround1, positionGround2;

    private Array<Tube> tubes;


    private boolean gameover;
    private boolean enableUpdateScore;
    private int score = 0;
    private BitmapFont font;

    public PlayState(GameStateManager manager) {
        super(manager);
        bird = new Bird(40, 200);

        this.background = new Texture("bg.png");
        this.ground = new Texture("ground.png");
        gameoverImg = new Texture("gameover.png");

        positionGround1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        positionGround2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<>();
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(START_UP_SPACING + (i * (TUBE_SPACING + Tube.TUBE_WIDTH))));
        }

        this.font = new BitmapFont();
        enableUpdateScore = true;

        scoreSprite = new Score();

        //noinspection IntegerDivisionInFloatingPointContext
        camera.setToOrtho(false, ApplicationConfig.WIDTH / 2, ApplicationConfig.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (gameover) {
                manager.set(new MenuState(manager));
            } else
                bird.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();
        bird.update(delta);

        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            // Khi mà vượt qua đc 1 tube thì reposition lại tube
            if (camera.position.x - camera.viewportWidth / 2 > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                enableUpdateScore = true;
            }

            if (bird.getPosition().x > tube.getPositionTopTube().x + tube.getTopTube().getWidth() && enableUpdateScore) {
                score++;
                scoreSprite.update(score);
                enableUpdateScore = false;
            }

            // Kiểm tra nếu có va chạm
            if (tube.collides(bird.getBounds())) {
                bird.setColliding(true);
                gameover = true;
            }
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            bird.setColliding(true);
            gameover = true;
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        // Background
        spriteBatch.draw(this.background, camera.position.x - (camera.viewportWidth / 2), 0);

        // Bird
        spriteBatch.draw(bird.getBirdTexture(), bird.getPosition().x, bird.getPosition().y);

        // Tube
        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }

        // Ground
        spriteBatch.draw(ground, positionGround1.x, positionGround1.y);
        spriteBatch.draw(ground, positionGround2.x, positionGround2.y);

//        font.draw(spriteBatch, String.valueOf(score), camera.position.x, 50);

        // Nếu game over
        if (gameover) {
            //noinspection IntegerDivisionInFloatingPointContext
            spriteBatch.draw(gameoverImg, camera.position.x - gameoverImg.getWidth() / 2, camera.position.y);
        }

        spriteBatch.draw(scoreSprite.getScoreTextures()[0], camera.position.x - scoreSprite.getScoreTextures()[0].getWidth(), 20);
        spriteBatch.draw(scoreSprite.getScoreTextures()[1], camera.position.x , 20);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();

        for (Tube tube : tubes) {
            tube.dispose();
        }

        ground.dispose();

    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > positionGround1.x + ground.getWidth()) {
            positionGround1.add(ground.getWidth() * 2, 0);
        }

        if (camera.position.x - (camera.viewportWidth / 2) > positionGround2.x + ground.getWidth()) {
            positionGround2.add(ground.getWidth() * 2, 0);
        }
    }
}

package com.phoenix.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird extends Sprite {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private static final int JUMP = 250;
    private static final float DEFAULT_ROTATION = 90f;
    private static final float JUMP_ROTATION = 125f;
    private static final float FALLING_ROTATION = 45f;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;

    private Texture birdTexture;
    private Animation birdAnimation;

    private boolean colliding;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdTexture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.3f);
        //noinspection IntegerDivisionInFloatingPointContext
        bounds = new Rectangle(x, y, birdTexture.getWidth() / 3, birdTexture.getHeight());

        colliding = false;
    }

    public void update(float delta) {
        birdAnimation.update(delta);

        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(delta);

        if (colliding) {
            position.add(0, velocity.y, 0);
        } else {
            position.add(MOVEMENT * delta, velocity.y, 0);
        }

        if (position.y < 82) {
            position.y = 82;
        }

        velocity.scl(1 / delta);
        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = JUMP;
    }

    @Override
    public void dispose() {
        this.birdTexture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdTexture() {
        return birdAnimation.getFrame();
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }


}

package com.phoenix.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird extends Sprite {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;

    private Texture birdTexture;

    private boolean colliding;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdTexture = new Texture("bird.png");
        bounds = new Rectangle(x, y, birdTexture.getWidth(), birdTexture.getHeight());

        colliding = false;
    }

    public void update(float delta) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(delta);

        if(!colliding)
            position.add(MOVEMENT * delta, velocity.y, 0);

        if (position.y < 82) {
            position.y = 82;
        }

        velocity.scl(1 / delta);
        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = 250;
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

    public Texture getBirdTexture() {
        return birdTexture;
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
}

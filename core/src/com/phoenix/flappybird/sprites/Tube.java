package com.phoenix.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube extends Sprite {
    public static final int TUBE_WIDTH = 52; //width of tube texture(used for spacing tubes on playstate)
    private static final int FLUCTUATION = 130; //may adjust to keep top tube in view
    private static final int TUBE_GAP = 100; //opening between tubes
    private static final int LOWEST_OPENING = 120;  //lowest position the top of the bottom tube can be, must be above 90 to be above ground level

    private Texture topTube, bottomTube;
    private Vector2 positionTopTube, positionBottomTube;

    private Rectangle boundTop, boundBot;

    private Random random;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        random = new Random();

        positionTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundTop = new Rectangle(positionTopTube.x, positionTopTube.y, topTube.getWidth(), bottomTube.getHeight());
        boundBot = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public void reposition(float x) {
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundTop.setPosition(positionTopTube.x, positionTopTube.y);
        boundBot.setPosition(positionBottomTube.x, positionBottomTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundTop) || player.overlaps(boundBot);
    }

    @Override
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }


}

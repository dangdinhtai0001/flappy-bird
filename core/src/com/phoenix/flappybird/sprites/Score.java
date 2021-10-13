package com.phoenix.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Score extends Sprite {
    private Texture[] scoreTextures;
    private Map<String, Texture> scoreMap;

    public Score() {
        scoreTextures = new Texture[2];
        scoreMap = new HashMap<>();

        loadScoreMap();

        scoreTextures[0] = scoreMap.get("0");
        scoreTextures[1] = scoreMap.get("0");
    }

    private void loadScoreMap() {
        for (int i = 0; i < 10; i++) {
            scoreMap.put(String.valueOf(i), new Texture(i + ".png"));
        }
    }

    public void update(int score){
        String[] arrScore = String.valueOf(score).split("");

        if(arrScore.length == 1){
            scoreTextures[0] = scoreMap.get("0");
            scoreTextures[1] = scoreMap.get(arrScore[0]);
        }else {
            scoreTextures[0] = scoreMap.get(arrScore[0]);
            scoreTextures[1] = scoreMap.get(arrScore[1]);
        }
    }

    @Override
    public void dispose() {

    }

    public Texture[] getScoreTextures() {
        return scoreTextures;
    }
}

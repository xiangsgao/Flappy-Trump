package com.gao23.trumpbird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by GAO on 7/25/2017.
 */

public class tubes {
    private static final int RANGE_OF_HEIGHT_RANDOM = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_TUBE_HEIGHT = 100;
    private Texture topTube, bottomTube;
    private Vector2 topTubPos, botTubePos;
    private Random rand;

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getTopTubPos() {
        return topTubPos;
    }

    public Vector2 getBotTubePos() {
        return botTubePos;
    }

    public tubes(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        this.topTubPos = new Vector2(x, rand.nextInt(this.RANGE_OF_HEIGHT_RANDOM) + this.TUBE_GAP + this.LOWEST_TUBE_HEIGHT);
        this.botTubePos = new Vector2(x, topTubPos.y-this.TUBE_GAP-bottomTube.getHeight());
    }
}

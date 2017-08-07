package com.gao23.trumpbird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by GAO on 7/25/2017.
 */

public class tubes {
    public static final int TUBE_WIDTH = 100;
    private static final int RANGE_OF_HEIGHT_RANDOM = 150;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_TUBE_HEIGHT = 180;
    private Texture topTube, bottomTube;
    private Vector2 topTubPos, botTubePos;
    private Rectangle topBounds, botBounds;
    private Random rand;
    public Rectangle scoreMarker;
    private boolean checked = false;

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

    public tubes(int  x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        this.topTubPos = new Vector2(x, rand.nextInt(this.RANGE_OF_HEIGHT_RANDOM) + this.TUBE_GAP + this.LOWEST_TUBE_HEIGHT);
        this.botTubePos = new Vector2(x, topTubPos.y-this.TUBE_GAP-bottomTube.getHeight());
        topBounds = new Rectangle(topTubPos.x,topTubPos.y,topTube.getWidth(),topTube.getHeight());
        botBounds = new Rectangle(botTubePos.x, botTubePos.y, bottomTube.getWidth(),bottomTube.getHeight());
        scoreMarker = new Rectangle(botBounds.x+botBounds.width/2, botBounds.y+botBounds.height,1,500);
        Gdx.app.log("Tubes","instantiated");
    }

    public void reposition(float x){
        this.topTubPos.set(x, rand.nextInt(this.RANGE_OF_HEIGHT_RANDOM) + this.TUBE_GAP + this.LOWEST_TUBE_HEIGHT);
        this.botTubePos = new Vector2(x, topTubPos.y-this.TUBE_GAP-bottomTube.getHeight());
        topBounds.setPosition(topTubPos.x,topTubPos.y);
        botBounds.setPosition(botTubePos.x,botTubePos.y);
        scoreMarker.setPosition(botBounds.x+botBounds.width/2,botBounds.y+botBounds.height);
        checked = false;
    }

    public boolean collides(Rectangle trump){
        return trump.overlaps(topBounds) || trump.overlaps(botBounds);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }

    public Rectangle getBotBounds() {
        return botBounds;
    }

    public Rectangle getTopBounds() {
        return topBounds;

    }

    public boolean scores(Rectangle trump){
        if(checked){
            return false;
        }
        else if(trump.overlaps(scoreMarker)){
            checked = true;
            return true;
        }
        else{
            return false;
        }
    }

}

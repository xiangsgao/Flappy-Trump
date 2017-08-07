package com.gao23.trumpbird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BooleanArray;
import com.gao23.trumpbird.TrumpBirdMain;

/**
 * Created by GAO on 7/24/2017.
 */

public class trumpBird {
    private static final int GRAVITY = -15;
    private static final int HORIZONTAL_VELOCITY = 140;
    private Vector3 position;
    private Vector3 velocity;
    private Texture trumpB;
    private Rectangle bounds;
    private Sound flapSound;
    private Sound boomSound;
    private Boolean isCrashed = false;


    public trumpBird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        trumpB = new Texture("trump.png");
        bounds = new Rectangle(x,y, trumpB.getWidth(),trumpB.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        boomSound = Gdx.audio.newSound(Gdx.files.internal("boom.mp3"));
        Gdx.app.log("TrumpBird","instantiated");
    }

    public Vector3 getPostion() {
        return position;
    }

    public Texture getTrumpB() {
        return trumpB;
    }

    public Boolean getCrashed() {
        return isCrashed;
    }

    public void update(float dt){
        this.velocity.add(0, GRAVITY, 0);
        this.velocity.scl(dt);
        if(!isCrashed) {
            this.position.add(HORIZONTAL_VELOCITY * dt, this.velocity.y, 0);
        }
        if(isCrashed&&this.position.y>0){
            this.position.add(-HORIZONTAL_VELOCITY * dt, this.velocity.y, 0);
        }
        if (this.position.y < 0) {
            this.position.y = 0;
        }
        if (this.position.y > TrumpBirdMain.HEIGHT/2-trumpB.getHeight()) {
            this.position.y = TrumpBirdMain.HEIGHT/2-trumpB.getHeight();
        }

        bounds.setPosition(position.x,position.y);

        // this resets the velocity value back to the original
        this.velocity.scl(1/dt);
    }

    public void jump(){
        if(!isCrashed) {
            velocity.y = 280;
            flapSound.play();
        }
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void disapose(){
        trumpB.dispose();
        flapSound.dispose();
        boomSound.dispose();
    }

    public void crash(){
        this.isCrashed = true;
        boomSound.play();
    }


}

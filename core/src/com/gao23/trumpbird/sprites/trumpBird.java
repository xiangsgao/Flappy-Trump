package com.gao23.trumpbird.sprites;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by GAO on 7/24/2017.
 */

public class trumpBird {
    private static final int GRAVITY = -15;
    private static final int HORIZONTAL_VELOCITY = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Texture trumpB;
    private Rectangle bounds;


    public trumpBird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        trumpB = new Texture("Trump.png");
        bounds = new Rectangle(x,y, trumpB.getWidth(),trumpB.getHeight());
    }

    public Vector3 getPostion() {
        return position;
    }

    public Texture getTrumpB() {
        return trumpB;
    }

    public void update(float dt){
            this.velocity.add(0, GRAVITY, 0);
            this.velocity.scl(dt);
        this.position.add(HORIZONTAL_VELOCITY*dt, this.velocity.y, 0);
        if (this.position.y < 0) {
            this.position.y = 0;
        }
        if (this.position.y > 350) {
            this.position.y = 350;
        }

        bounds.setPosition(position.x,position.y);

        // this resets the velocity value back to the original
        this.velocity.scl(1/dt);
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void disapose(){
        trumpB.dispose();
    }


}

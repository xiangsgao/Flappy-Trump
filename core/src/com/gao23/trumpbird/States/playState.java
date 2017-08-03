package com.gao23.trumpbird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.gao23.trumpbird.sprites.trumpBird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.TrumpBirdMain;
import com.gao23.trumpbird.sprites.tubes;
import java.util.Random;
import com.gao23.trumpbird.hud;

/**
 * Created by GAO on 7/23/2017.
 */

public class playState extends States {
    private hud scoreAndButton;
    private trumpBird bird;
    private Texture background;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_NUMBER = 4;
    private Array<tubes> tubeArray;
    private float lastPostion;
    private Music music;
    private Sound no;
    private boolean gameOver = false;
    BitmapFont font;



    public playState(stateManager manager){
        super(manager);
        scoreAndButton = new hud(manager.getAb());
        font = new BitmapFont();
        tubeArray = new Array<tubes>();
        bird = new trumpBird(25, 300);
        // this is to zoom in on only one part of the graphics
        cam.setToOrtho(false, TrumpBirdMain.WIDTH/2, TrumpBirdMain.HEIGHT/2);
        background = new Texture("background.jpg");
        for(int i = 0; i<=this.TUBE_NUMBER;i++){
                tubeArray.add(new tubes(i * this.TUBE_SPACING + tubes.TUBE_WIDTH +200));

        }
        lastPostion = tubeArray.get(tubeArray.size-1).getTopTubPos().x;
        music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        no = Gdx.audio.newSound(Gdx.files.internal("NO!!!!.mp3"));
    }

    @Override
    protected void input() {
         if(Gdx.input.justTouched()){
             bird.jump();
         }
    }

    @Override
    public void update(float time) {
        this.input();
        bird.update(time);
        cam.position.x = bird.getPostion().x + 80;
        if(Double.compare(bird.getPostion().y, 0) == 0 && !gameOver){
            this.gameOver = true;
            if(!bird.getCrashed()) {
                bird.crash();
            }
            music.stop();
            no.play();
        }
        for(tubes tube: tubeArray){
            if(this.gameOver||bird.getCrashed()){
                break;
            }
            if(cam.position.x-(cam.viewportWidth/2)>tube.getTopTubPos().x+tube.getTopTube().getWidth()) {
                if(tube.getTopTubPos().x>this.lastPostion){
                    tube.getTopTubPos().x = lastPostion;
                }
                tube.reposition(100 + tubes.TUBE_WIDTH + new Random().nextInt(100) + this.lastPostion);
                this.lastPostion = tube.getTopTubPos().x;

            }
           if(tube.collides(bird.getBounds())){
                bird.crash();
                break;
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch ab) {
        // this adjusts the sprite batch to the zoomed in camera
        ab.setProjectionMatrix(cam.combined);
        ab.begin();
        ab.draw(background, bird.getPostion().x-40, 0, cam.viewportWidth,cam.viewportHeight);
        for(tubes tube: tubeArray) {
            ab.draw(tube.getTopTube(), tube.getTopTubPos().x, tube.getTopTubPos().y);
            ab.draw(tube.getBottomTube(), tube.getBotTubePos().x, tube.getBotTubePos().y);
        }
        ab.draw(bird.getTrumpB(), bird.getPostion().x, bird.getPostion().y);
        if(this.gameOver){
            font.draw(ab, "GAME OVER", cam.position.x-40, cam.position.y);
        }
        ab.end();
        ab.setProjectionMatrix(scoreAndButton.stage.getCamera().combined);
        scoreAndButton.stage.draw();
    }


    @Override
    public void dispose() {
           for(tubes Tube: tubeArray){
               Tube.dispose();
           }
           bird.disapose();
           background.dispose();
           music.dispose();
           font.dispose();
           no.dispose();
    }
}

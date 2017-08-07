package com.gao23.trumpbird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gao23.trumpbird.sprites.trumpBird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.TrumpBirdMain;
import com.gao23.trumpbird.sprites.tubes;

import java.util.Random;

/**
 * Created by GAO on 7/23/2017.
 */

public class playState extends States {
    private hud scoreAndButton;
    private trumpBird bird;
    private Texture background;
    private static final int TUBE_SPACING = 200;
    private static final int TUBE_NUMBER = 4;
    private Array<tubes> tubeArray;
    private float lastPostion;
    private Music music;
    private Sound no;
    private Sound beep;
    private boolean gameOver = false;
    private boolean pause = false;
    // No use for this as of now. It was once used to draw game over on screen but now it is replaced by uhd's stage and labels.
    // I kept it for future reference.
    // BitmapFont font;
    private int scores = 0;



    public playState(stateManager manager){
        super(manager);
        bird = new trumpBird(15, 300);
        scoreAndButton = new hud(manager.getAb());
        Gdx.input.setInputProcessor(scoreAndButton.stage);
        tubeArray = new Array<tubes>();
        // this is to zoom in on only one part of the graphics
        cam.setToOrtho(false,TrumpBirdMain.WIDTH/2,TrumpBirdMain.HEIGHT/2);
        cam.update();
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
        beep = Gdx.audio.newSound(Gdx.files.internal("Beep.mp3"));
        Gdx.app.log("PlayState","instantiated");
    }

    @Override
    protected void input() {
        if(Gdx.input.justTouched() && !pause){
            bird.jump();
        }
    }

    @Override
    public void update(float time) {
        this.input();
        if(!this.pause) {
            bird.update(time);
        }
        cam.position.x = bird.getPostion().x + 80;
        if(Double.compare(bird.getPostion().y, 0) == 0 && !gameOver){
            this.gameOver = true;
            scoreAndButton.button.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("resume.png"))));
            if(!bird.getCrashed()) {
                bird.crash();
            }
            music.stop();
            no.play();
        }
        for(tubes tube: tubeArray){
            if(this.gameOver||bird.getCrashed()||this.pause){
                break;
            }
            if(cam.position.x-(cam.viewportWidth/2)>tube.getTopTubPos().x+tube.getTopTube().getWidth()) {
                if(tube.getTopTubPos().x>this.lastPostion){
                    tube.getTopTubPos().x = lastPostion;
                }
                tube.reposition(100 + tubes.TUBE_WIDTH + new Random().nextInt(50) + this.lastPostion);
                this.lastPostion = tube.getTopTubPos().x;

            }
           if(tube.collides(bird.getBounds())){
                bird.crash();
            }
            if(tube.scores(bird.getBounds())){
                scores += 1;
                beep.setVolume(beep.play(),0.05f);
                scoreAndButton.scoreUpdate();
            }
        }
        if(this.gameOver){
            scoreAndButton.displayGameOver();
        }
        cam.update();

    }

    @Override
    public void render(SpriteBatch ab) {
        // this adjusts the sprite batch to the zoomed in camera
        ab.setProjectionMatrix(cam.combined);
        ab.begin();
        ab.draw(background,bird.getPostion().x-100,0,cam.viewportWidth,cam.viewportHeight);

        for(tubes tube: tubeArray) {
            ab.draw(tube.getTopTube(), tube.getTopTubPos().x, tube.getTopTubPos().y);
            ab.draw(tube.getBottomTube(), tube.getBotTubePos().x, tube.getBotTubePos().y);
        }
        ab.draw(bird.getTrumpB(), bird.getPostion().x, bird.getPostion().y);
        ab.end();
        ab.setProjectionMatrix(scoreAndButton.stage.getCamera().combined);
        scoreAndButton.stage.draw();

    }


    @Override
    public void dispose() {

        scoreAndButton.stage.dispose();
           for(tubes Tube: tubeArray){
               Tube.dispose();
           }
           bird.disapose();
           background.dispose();
           music.dispose();
           no.dispose();
           beep.dispose();
    }


    // embedded hud class for easier abstraction
    public class hud {
        // stage is an empty box
        public Stage stage;
        // Label is a widget good for drawing text
        private Label scoreLabel;
        // this sets up a new camera so uhd(stage) does not move as the main camera moves with the bird
        private Viewport viewport2;
        public ImageButton button;


        private Table GameOver = null;


        public hud (SpriteBatch ab){
            // sets up the camera size
            viewport2 = new ScreenViewport();
            OrthographicCamera cam = new OrthographicCamera(TrumpBirdMain.WIDTH/2,TrumpBirdMain.HEIGHT/2);

            //stage takes in the viewport and configures its size
            stage = new Stage(viewport2,ab);
            //this organizes the shit in our stage os widget isn't all over the place
            Table table = new Table();
            // default is the center, this places all the widget at the top... top center now
            table.top();
            // this is important cuz it makes the image look right BUT don't use this on button
            table.setFillParent(true);
            scoreLabel = new Label(Integer.toString(playState.this.scores), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            table.add(scoreLabel).expandX();
            table.pad(50);
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.checked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("resume.png"))));
            style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pause.png"))));
            button = new ImageButton(style);
            button.setPosition(bird.getPostion().x+100,viewport2.getScreenHeight()-100);
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    // need to change icon and pause the game
                    if(gameOver){
                        playState.this.manager.set(new playState(playState.this.manager));
                    }
                   else if(pause == false && !gameOver) {
                        button.setChecked(true);
                        pause = true;
                        playState.this.music.pause();
                    }
                    else{
                        pause = false;
                        button.setChecked(false);
                        playState.this.music.play();
                    }
                }
            });
            stage.addActor(button);
            // adds everything in the table to the stage so everything is neat and organized
            stage.addActor(table);
            Gdx.app.log("UHD","instantiated");
        }

        public void scoreUpdate(){
            scoreLabel.setText(Integer.toString(playState.this.scores));
        }



        public void displayGameOver() {
            if (GameOver == null) {
                GameOver = new Table();
                Label overLabel = new Label("GAME OVER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
                Label overScoreLabel = new Label("FINAL SCORE IS " + playState.this.scores, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
                Label playAgain = new Label("TAP PLAY BUTTON TO PLAY AGAIN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
                GameOver.setFillParent(true);
                GameOver.center();
                GameOver.add(overLabel).expandX();
                GameOver.row();
                GameOver.add(overScoreLabel).expandX();
                GameOver.row();
                GameOver.add(playAgain).expandX();
                stage.addActor(GameOver);
            }
        }

    }


}

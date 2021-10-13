package edu.binghamton.cs;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {
    String spriteSheetPath;
    private static final int FRAME_COLS = 4, FRAME_ROWS = 2; //The number of rows and columns in the sprite sheet
    Animation<TextureRegion> walkAnimation;
    Texture spriteSheet; //The image containing the sprite sheet
    SpriteBatch spriteBatch;
    float stateTime;
    int num_ignored = 0; // How many sprite locations to ignore in the last row of the sheet (for if a sheet isn't completely filled)

    public Animator(String spriteSheetPath){
        this.spriteSheetPath = spriteSheetPath;
    }

    @Override
    public void create() {
        spriteSheet = new Texture(Gdx.files.internal(spriteSheetPath));
        TextureRegion[][] sprite = TextureRegion.split(spriteSheet,spriteSheet.getWidth()/FRAME_COLS, spriteSheet.getHeight()/FRAME_ROWS); //Splitting the sprite sheet into separate sprites based on the number of rows and columns compared to the size of the image itself
        TextureRegion[] spriteFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index = 0;
        for(int i=0; i<FRAME_ROWS;i++){
            for(int j=0; j<FRAME_COLS; j++){
                // ADD LOGIC FOR NOT CONSIDERING THE LAST num_ignored SPRITES IN THE SHEET
                spriteFrames[index++] = sprite[i][j]; //Putting the individual sprites into an array in the order they appear on the sheet
            }
        }
        walkAnimation = new Animation<TextureRegion>((float)0.025, spriteFrames);
        spriteBatch = new SpriteBatch();
        stateTime = (float)0;
    }

    @Override
    public void render(){
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50); // Draw current frame at (50, 50)
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteSheet.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }
}

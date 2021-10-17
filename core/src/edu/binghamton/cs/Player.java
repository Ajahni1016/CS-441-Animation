package edu.binghamton.cs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    String dir = "down";
    String in_motion = "no";
    private static final int FRAME_COLS = 4, FRAME_ROWS = 2; //The number of rows and columns in the sprite sheet
    Animation<TextureRegion> walkAnimation;
    int x_pos=50;
    int y_pos=50;
    float stateTime;

    // Walking Sprites
    String up_walk = "data/player_down_walk.png";
    String down_walk = "data/player_down_walk.png";
    String side_walk = "data/player_down_walk.png";
    // Idle Sprites
    // Equip Sprites
    // Attack Sprites

    public void animate(int num_ignored, String path, int rows, int cols){
        Texture spriteSheet = new Texture(Gdx.files.internal(path));
        TextureRegion[][] sprite = TextureRegion.split(spriteSheet,spriteSheet.getWidth()/cols, spriteSheet.getHeight()/rows); //Splitting the sprite sheet into separate sprites based on the number of rows and columns compared to the size of the image itself
        TextureRegion[] spriteFrames = new TextureRegion[cols*rows-num_ignored];
        int index = 0;
        int remaining = cols-num_ignored;
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols; j++){
                if(i==rows-1){ //If we're in the last row
                    if(remaining>0){
                        remaining--;
                        spriteFrames[index++] = sprite[i][j];
                    }
                }
                else {
                    spriteFrames[index++] = sprite[i][j]; //Putting the individual sprites into an array in the order they appear on the sheet
                }
            }
        }
        this.walkAnimation = new Animation<TextureRegion>((float)0.225, spriteFrames);
        this.stateTime = (float)0;
    }
}

package edu.binghamton.cs; //Justin

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import 	java.lang.Math;

public class CustomDrawing extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	double x, y=50;
	float dx, dy;
	Random rand = new Random();
	float w, h;
	float ticks=0;
	int dir_x = 0; //0 = forwards, 1 = backwards
	int dir_y = 0; //0 = forwards, 1 = backwards
	int red = 0;
	int green = 0;
	int blue = 0;
	int rad=50;
	ShapeRenderer circleRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		circleRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		ticks++;
		Gdx.gl.glClearColor( 12/255f, 2/255f, 92/255f, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
		if(x >=w ){
			dir_x = 1;
			rad = rand.nextInt((150 - 20) + 1) + 20;
			red = rand.nextInt(255);
			blue = rand.nextInt(255);
			green = rand.nextInt(255);
			circleRenderer.setColor(red/255f,green/255f,blue/255f,1);
		}
		else if(x<=0){
			dir_x = 0;
			rad = rand.nextInt((150 - 20) + 1) + 20;
			red = rand.nextInt(255);
			blue = rand.nextInt(255);
			green = rand.nextInt(255);
			circleRenderer.setColor(red/255f,green/255f,blue/255f,1);
		}
		if(y >=h ){
			dir_y = 1;
			rad = rand.nextInt((150 - 20) + 1) + 20;
			red = rand.nextInt(255);
			blue = rand.nextInt(255);
			green = rand.nextInt(255);
			circleRenderer.setColor(red/255f,green/255f,blue/255f,1);
		}
		else if(y<=0){
			dir_y = 0;
			rad = rand.nextInt((150 - 20) + 1) + 20;
			red = rand.nextInt(255);
			blue = rand.nextInt(255);
			green = rand.nextInt(255);
			circleRenderer.setColor(red/255f,green/255f,blue/255f,1);
		}
		if(dir_x == 0){ x+=20;}
		else{ x-=20; }
		if(dir_y == 0){ y+=20; }
		else{ y-=20; }
		circleRenderer.begin(ShapeRenderer.ShapeType.Filled);
		circleRenderer.circle((float)x, (float)y, rad);
		circleRenderer.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(int width, int height){
		w = width;
		h = height;
	}
}

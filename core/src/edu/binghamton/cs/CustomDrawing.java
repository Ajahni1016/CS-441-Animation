package edu.binghamton.cs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import 	java.lang.Math;

public class CustomDrawing extends ApplicationAdapter {
	SpriteBatch batch;
//	Texture img;
	TextureRegion img;
	boolean touched;
	private final Vector2 knobPercent = new Vector2();
	Stage stage;
	double x, y=50;
	float dx, dy;
	Random rand = new Random();
	float w, h;
	private final Vector2 knobPosition = new Vector2();
	boolean resetOnTouchUp = true;
	Touchpad joystick;
	ShapeRenderer circleRenderer;



	@Override
	public void create () {
		batch = new SpriteBatch();
//		img = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")));
		batch.begin();
		circleRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor( 122/255f, 2/255f, 92/255f, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
		createTouchpad();
		create();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		circleRenderer.begin(ShapeRenderer.ShapeType.Filled);
		circleRenderer.circle((float)700, (float)500, 50);
		circleRenderer.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height){
		w = width;
		h = height;
	}


	public void createTouchpad(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
//
//		joystickSkin = new Skin();
//		joystickSkin.add("touchKnob", new Texture(Gdx.files.internal("data/Pan_Blue_Circle.png")));

		Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
//		touchpadStyle.knob = joystickSkin.getDrawable("touchKnob");

		Pixmap knob = new Pixmap(200, 200, Pixmap.Format.RGBA8888);
		knob.setColor((float)231, (float)11, (float)11, 1);
		knob.fillCircle(100, 100, 50);
		touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(knob)));

		Pixmap background = new Pixmap(200, 200, Pixmap.Format.RGBA8888);
		background.setColor(1, 1, 1, 1);
		background.fillCircle(100, 100, 100);
		touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(background)));
		joystick = new Touchpad(10, touchpadStyle);
		joystick.setBounds(25, 25, 300, 300); //Size of outter circle and position on screen


		stage.addActor(joystick);

//		joystick.addListener(new ChangeListener() {
//			@Override
//			public void changed(ChangeEvent event, Actor actor) {
//				float deltaX = ((Touchpad) actor).getKnobPercentX();
//				float deltaY = ((Touchpad) actor).getKnobPercentY();
//			}
//		});


		joystick.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if (touched) return false;
				touched = true;
				System.out.println("DOWN "+joystick.getKnobPercentX());
				return true;
			}

			@Override
			public void touchDragged (InputEvent event, float x, float y, int pointer) {
				System.out.println("DRAGGED");

			}

			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				touched = false;
				System.out.println("UP");
			}
		});
	}


}
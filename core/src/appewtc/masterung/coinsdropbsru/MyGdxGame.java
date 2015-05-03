package appewtc.masterung.coinsdropbsru;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture imvBackGround, imvPig, imvCoins;
	private OrthographicCamera objOrthographicCamera;
	private Rectangle pigRectangle, coinsRectangle;
	private Vector3 objVector3;
	private Sound moveSound;
	private Array<Rectangle> objArray;
	private long lastDropTime;
	private Iterator<Rectangle> objIterator;
	private Music backGroundMusic;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		//Setup Screen Size
		objOrthographicCamera = new OrthographicCamera();
		objOrthographicCamera.setToOrtho(false, 1280, 800);

		//Setup BackGround
		imvBackGround = new Texture("bg0.png");

		//Setup Object
		imvPig = new Texture("pig.png");
		imvCoins = new Texture("coins.png");

		//Create pigRectangle
		pigRectangle = new Rectangle();
		pigRectangle.x = 1280 / 2 - 64 / 2;	// Center Horizontal
		pigRectangle.y = 50;
		pigRectangle.width = 64;
		pigRectangle.height = 64;

		//Setup Sound Effect
		moveSound = Gdx.audio.newSound(Gdx.files.internal("phonton2.wav"));

		//Create Array from Random
		objArray = new Array<Rectangle>();
		myRandomDrop();

		//Create Background Music
		backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("bggame.mp3"));



	}	// Main Method

	private void myRandomDrop() {

		//Create Rectangle coins
		coinsRectangle = new Rectangle();
		coinsRectangle.x = MathUtils.random(0, 1216);
		coinsRectangle.y = 800;
		coinsRectangle.width = 64;
		coinsRectangle.height = 64;

		objArray.add(coinsRectangle);
		lastDropTime = TimeUtils.nanoTime();

	}	// myRandomDrop

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//setup Size
		objOrthographicCamera.update();
		batch.setProjectionMatrix(objOrthographicCamera.combined);

		//Start Draw Object
		batch.begin();

		//Draw Wallpaper
		batch.draw(imvBackGround, 0, 0);

		//Draw Pig
		batch.draw(imvPig, pigRectangle.x, pigRectangle.y);

		//Draw Coins
		for (Rectangle forRectangle : objArray) {
			batch.draw(imvCoins, forRectangle.x, forRectangle.y);
		}


		batch.end();

		//Get onTouch
		if (Gdx.input.isTouched()) {

			//Open Sound Effect
			moveSound.play();

			//Check Touch
			objVector3 = new Vector3();
			objVector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);

			if (objVector3.x < 640) {
				pigRectangle.x -= 10;
			} else {
				pigRectangle.x += 10;
			}    // if1

		} 	// if

		//Control pigRectangle.x 0-1280
		if (pigRectangle.x < 0) {
			pigRectangle.x = 0;
		}	//if1
		if (pigRectangle.x > 1216) {
			pigRectangle.x = 1216;
		}	//if2


		//Start RunTime
		if (TimeUtils.nanoTime() - lastDropTime > 1E9 ) {
			myRandomDrop();
		}

		//Check True & False
		objIterator = objArray.iterator();

		while (objIterator.hasNext()) {

			Rectangle objMyCoins = objIterator.next();
			objMyCoins.y -= 100 * Gdx.graphics.getDeltaTime();

			//When drop to floor
			if (objMyCoins.y + 64 < 0) {

				objIterator.remove();


			}

		}	//while

		backGroundMusic.play();


	}	// rander

}	//Main Class


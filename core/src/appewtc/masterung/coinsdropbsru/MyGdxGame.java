package appewtc.masterung.coinsdropbsru;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture imvBackGround, imvPig, imvCoins;
	private OrthographicCamera objOrthographicCamera;
	private Rectangle pigRectangle, coinsRectangle;
	private Vector3 objVector3;
	
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



	}	// Main Method

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


		batch.end();

		//Get onTouch
		if (Gdx.input.isTouched()) {

			//Check Touch
			objVector3 = new Vector3();
			objVector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);

			if (objVector3.x < 640) {
				pigRectangle.x -= 10;
			} else {
				pigRectangle.x += 10;
			}	// if1



		} else {

		}	// if



	}	// rander

}	//Main Class


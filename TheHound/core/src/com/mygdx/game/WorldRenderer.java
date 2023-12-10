package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.items.Item;

import java.util.List;

public class WorldRenderer {

    Player player;

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public BitmapFont font;
    public FreeTypeFontGenerator generator;
    public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public ShapeRenderer shapeRenderer;

    public WorldRenderer(Player player) {
        this.player=player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 240, 160);
        camera.position.set(player.position.x, player.position.y, 0);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        font = generator.generateFont(parameter);
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void begin(){
        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();
        Gdx.gl.glClearColor(131/255f, 168/255f, 133/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
    }

    public void end(){
        batch.end();
        shapeRenderer.end();
    }

    public void render() {

        player.render(batch, shapeRenderer);
    }

    public void renderCursor(List<Entity> entityList){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        /*
        for(Entity entity: entityList){

            if(Utilities.checkWorldEntityContainsCursor(entity.bounds, camera)){
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.rect(entity.bounds.x-1, entity.bounds.y-1, entity.bounds.width+2, entity.bounds.height+2);
            }
        }*/

        for(int i=entityList.size()-1; i>=0; i--){
            Entity entity = entityList.get(i);
            if(Utilities.checkWorldEntityContainsCursor(entity.bounds, camera)){
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.rect(entity.bounds.x-1, entity.bounds.y-1, entity.bounds.width+2, entity.bounds.height+2);
                break;
            }
        }

        shapeRenderer.end();
    }

}

package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WorldScreen implements Screen {

    Entity test = new Entity(110, 100);
    public Application game;
    public Player player;
    public WorldRenderer worldRenderer;
    public List<Entity> entityList = new ArrayList<>();
    public MapObjects collisionObjects = null;
    public MapObjects objects=null;

    public WorldScreen(Application game){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game=game;


        TiledMap map = new TmxMapLoader().load("Map/samplemap.tmx");
        MapLayer layer = map.getLayers().get("positions");
        objects = layer.getObjects();

        this.player=new Player((float)objects.get("start").getProperties().get("x"), (float)objects.get("start").getProperties().get("y"));
        for(MapObject mapObject : objects.getByType(MapObject.class)){
            
            System.out.println(mapObject.getName());
        }
        worldRenderer= new WorldRenderer(player);

        layer = map.getLayers().get("collision");
        collisionObjects = layer.getObjects();

        layer = map.getLayers().get("objects");
        objects = layer.getObjects();

        for(MapObject object: objects){
            collisionObjects.add(object);
            System.out.println(object.getProperties().get("x")+", "+object.getProperties().get("y"));
        }

        player.setCollisionObjects(collisionObjects);

        entityList.add(test);

        MapLayer layer1 = map.getLayers().get("tree");
        System.out.println(layer1.getObjects().getCount());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        processKeys();
        worldRenderer.begin();

        player.update(delta, entityList);

        worldRenderer.render();
        //test.render(worldRenderer.shapeRenderer);

        worldRenderer.end();
    }

    public void processKeys(){

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            MapObject object=processLeftClick();
            if(object!=null)
                System.out.println("Test Successful");
        }
    }

    private MapObject processLeftClick(){

        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        worldRenderer.camera.unproject(touchPos);
        System.out.println("Unproject: "+touchPos);

        Polygon rPoly = new Polygon(new float[]{0, 0, 1, 0, 1, 1, 0, 1});
        rPoly.setPosition(touchPos.x, touchPos.y);

        for(MapObject object: objects) {
            Polygon p = null;
            if (object instanceof RectangleMapObject) {
                Rectangle r = ((RectangleMapObject) object).getRectangle();
                p = new Polygon(new float[]{0, 0, r.width, 0, r.width, r.height, 0, r.height});
                p.setPosition(r.x, r.y);

                if (Intersector.overlapConvexPolygons(rPoly, p)) {
                    return object;
                }
            }
        }

        return  null;
        /*
        Point mouseCoordinates = Utilities.getMouseCoordinates(Gdx.input, worldDataObject.viewDimensions);
        System.out.println(getEntity(mouseCoordinates));
        if (getEntity(mouseCoordinates) != null) {

            if(getEntity(mouseCoordinates) instanceof Item)
                processItem(mouseCoordinates);
            else if(getEntity(mouseCoordinates) instanceof NPC)
                processNPC(mouseCoordinates);
            else if(getEntity(mouseCoordinates).getDescription()!=null)
                processGenericEntity(mouseCoordinates);
        }
        */
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

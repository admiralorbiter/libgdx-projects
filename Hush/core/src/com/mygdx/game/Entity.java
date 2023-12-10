package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    public Vector2 position = new Vector2();
    public Rectangle bounds = null;

    public Color color = Color.WHITE;
    public String type = "Unknown";

    //public Texture texture = null;
    public List<Texture> texture = new ArrayList<>();
    int textureIndex=0;

    public Entity(int x, int y) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
        bounds.setCenter(position);
    }

    public Entity(int x, int y, int WIDTH, int HEIGHT) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        bounds.setCenter(position);
    }

    public Entity(int x, int y, int WIDTH, int HEIGHT, String type, List<Texture> texture) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        bounds.setCenter(position);
        this.type=type;
        this.texture=texture;
    }

    public Entity(int x, int y, int WIDTH, int HEIGHT, String type, Texture texture) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        bounds.setCenter(position);
        this.type=type;
        this.texture.add(texture);
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        if(position!=null) {
            shapeRenderer.setColor(color);
            if(texture.size()>0)
                batch.draw(texture.get(textureIndex), bounds.x, bounds.y, bounds.width, bounds.height);
            else
                shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public void setPosition(int x, int y){
        position.set(x, y);
        bounds.x=x;
        bounds.y=y;
    }

    /*public void setTexture(String texturePath){
        texture = new Texture(Gdx.files.internal(texturePath));
    }*/

    public void setTexture(Texture texture){this.texture.add(texture);}

    public Rectangle getBounds(){return bounds;}
    public Texture getTexture(){return texture.get(textureIndex);}
    public Color getColor(){return color;}
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.w3c.dom.Text;
import org.w3c.dom.css.Rect;

public class Entity {

    public Vector2 position = new Vector2();
    public Rectangle bounds = null;
    public Rectangle collision = null;

    public Color color = Color.WHITE;
    public String type = "Unknown";

    public Texture texture = null;

    public String clickResponse = "";

    public Entity(int x, int y) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
        bounds.setCenter(position);
        collision=new Rectangle(bounds);
    }

    public Entity(int x, int y, int WIDTH, int HEIGHT) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        bounds.setCenter(position);
        collision=new Rectangle(bounds);
    }

    public void render(Batch batch, ShapeRenderer shapeRenderer) {

        if(position!=null) {
            shapeRenderer.setColor(color);
            if(texture!=null)
                batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
            else
                shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public void setPosition(int x, int y){
        position.set(x, y);
        bounds.x=x;
        bounds.y=y;
        collision.x=x;
        collision.y=y;
    }

    public void setTexture(String texturePath){
        texture = new Texture(Gdx.files.internal(texturePath));
    }

    public void setTexture(Texture texture){this.texture=texture;}
    public void setCollision(Rectangle rectangle){
        this.collision=rectangle;
        collision.setCenter(position);
    }
    public Rectangle getCollision(){return collision;}
    public void setBounds(Rectangle rectangle){this.bounds=rectangle;}
    public Rectangle getBounds(){return bounds;}
    public Texture getTexture(){return texture;}
    public Color getColor(){return color;}
    public String getClickResponse(){return clickResponse;}
}

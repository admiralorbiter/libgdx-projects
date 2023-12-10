package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;

import java.awt.*;
import java.util.ArrayList;

public class MapEditorRenderer {
    public RenderDAO renderer = new RenderDAO();
    public Dimension viewDimensions = new Dimension(15, 15);
    public Vector2 position = new Vector2(15, 15);
    public Vector2 edge = new Vector2(30, 30);

    public MapEditorRenderer(){
        renderer.create();
    }

    public void begin(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.batch.begin();
    }

    public void end(){
        renderer.batch.end();
    }

    public void render(Entity[][] entityList){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setFontsize(25);
        renderer.batch.begin();
        renderer.font.setColor(Color.WHITE);
        renderer.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        renderer.font.draw(renderer.batch, position.x+", "+position.y, 75, (Utilities.getScreenSize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2-40);
        renderer.batch.end();

        renderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.shapeRenderer.setColor(Color.WHITE);
        showGrid();
        renderer.shapeRenderer.end();

        renderEntities(entityList);
    }

    private void renderEntities(Entity[][] entityList){
        int startX=0+(int)position.x;
        int startY=0+(int)position.y;
        int endX=(int)edge.x-1;
        int endY=(int)edge.y-1;
        if(startX+2*viewDimensions.width<edge.x-1)
            endX=(int)(startX+2*viewDimensions.width);
        if(startY+2*viewDimensions.height<edge.y-1)
            endY=(int)(startY+2*viewDimensions.height);


        renderer.batch.begin();
        //player.render(font, batch);

        ArrayList<Entity> drawList = new ArrayList<Entity>();

        int width=endX-startX;
        int height=endY-startY;


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                renderer.font.setColor(Color.WHITE);
                //System.out.println(startX+","+startY+" : "+player.position+" : "+endX+" , "+endY);
                if(entityList[startX][startY]!=null && entityList[startX][startY].draw==true) {
                    entityList[startX][startY].setRenderPoint(new Point(Utilities.getScreenSize().width/2+x*Utilities.getDefaultEntitySize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width, Utilities.getScreenSize().height/2+(y-1)*Utilities.getDefaultEntitySize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height));
                    drawList.add(entityList[startX][startY]);
                }
                else if(entityList[startX][startY]!=null && entityList[startX][startY].represntation!=null)
                    renderer.font.draw(renderer.batch, entityList[startX][startY].represntation, Utilities.getScreenSize().width/2+x*Utilities.getDefaultEntitySize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width, Utilities.getScreenSize().height/2+y*Utilities.getDefaultEntitySize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height);


                //shapeRenderer.rect(Utilities.getScreenSize().width/2+x*Utilities.getDefaultEntitySize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width, Utilities.getScreenSize().height/2+y*Utilities.getDefaultEntitySize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height, Utilities.getDefaultEntitySize().width,Utilities.getDefaultEntitySize().height);
                if(startY<endY)
                    startY++;
            }
            startY=0+(int)position.y;

            if(startX<endX)
                startX++;
        }

        renderer.batch.end();

        renderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.shapeRenderer.setColor(Color.WHITE);
        for(Entity entity: drawList) {
            entity.render(renderer.shapeRenderer);
        }

        renderer.shapeRenderer.end();
    }

    public void moveRight(){
        if(position.x+1<edge.x)
            position.x++;
    }

    public void moveLeft(){
        if(position.x>0)
            position.x--;
    }

    public void moveUp(){
        if(position.y+1<edge.y)
            position.y++;
    }

    public void moveDown(){
        if(position.y>0)
            position.y--;
    }

    private void showGrid() {
        for (int x = 0; x < viewDimensions.width*2; x++) {
            //Y Loop fucked up since y starts at the top of the screen
            for (int y = -1; y <viewDimensions.height*2-1; y++) {
                renderer.shapeRenderer.setColor(Color.WHITE);
                renderer.shapeRenderer.rect(Utilities.getScreenSize().width/2+x*Utilities.getDefaultEntitySize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width, Utilities.getScreenSize().height/2+y*Utilities.getDefaultEntitySize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height, Utilities.getDefaultEntitySize().width,Utilities.getDefaultEntitySize().height);
            }
        }
    }
}

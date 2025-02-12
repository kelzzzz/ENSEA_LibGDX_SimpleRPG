package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleTilemap extends SpriteWrapper{
    //This custom class implements a way to track the tiles are placed on the map
    //you define it with a size, a backdrop, and can dynamically overlay solid sprites

    public static SolidSprite[][] coordinates;

    @Override
    public void draw() {
        int xLen = coordinates.length;
        int yLen = coordinates[1].length;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion tr = new TextureRegion(texture);
        tr.setRegion(0,0,texture.getWidth()*xLen, texture.getHeight()*yLen);
        drawTextureRegion(tr, 0, 0);
        for(int i = 0; i < xLen; i++){
            for(int j = 0; j < yLen; j++){
                if(coordinates[i][j] != null){
                    coordinates[i][j].draw();
                }
            }
        }
    }
    
    public SimpleTilemap(int x, int y, String backdropFileName){
        coordinates = new SolidSprite[x][y];
        texture = new Texture(Gdx.files.internal(backdropFileName));
    }

    public void insertTile(SolidSprite tile, float coordX, float coordY){
        coordinates[(int)coordX/tile.getTextureWidth()][(int)coordY/tile.getTextureHeight()] = tile;
    }

    public void insertTile(SolidSprite tile, int i, int j){
        coordinates[i][j] = tile;
    }

    public void drawTextureRegion(TextureRegion tr, float x, float y) {
        batch.begin();
        batch.draw(tr, x, y);
        batch.end();
    }
}

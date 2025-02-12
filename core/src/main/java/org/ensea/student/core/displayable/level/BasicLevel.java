package org.ensea.student.core.displayable.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.ensea.student.core.displayable.SimpleTilemap;
import org.ensea.student.core.displayable.SolidSprite;

import static org.ensea.student.core.engine.RenderEngine.createTile;
import static org.ensea.student.core.util.Constants.*;

public class BasicLevel implements Level {
    private final Texture rockTexture = new Texture(Gdx.files.internal(TEXTURE_ROCK_PATH));
    private SimpleTilemap tm;

    @Override
    public SimpleTilemap retrievePackedLevelTilemap() {
        this.tm = new SimpleTilemap(6,9,TEXTURE_GRASS_PATH);
        packRocks();
        packTrees();
        return this.tm;
    }

    private void packRocks() {
        this.tm.insertTile(createTile(rockTexture,4,1),4,1);
        this.tm.insertTile(createTile(rockTexture,4,5),4,5);
        this.tm.insertTile(createTile(rockTexture,2,6),2,6);
    }

    public void packTrees(){
        Texture treeTexture = new Texture(Gdx.files.internal(TEXTURE_TREE_PATH));

        for(int i = 0; i < SimpleTilemap.coordinates.length; i++){
            for(int j = 0; j < SimpleTilemap.coordinates[0].length; j++){
                if(i == 0 || i == SimpleTilemap.coordinates.length-1){
                    SolidSprite tree = createTile(treeTexture,i,j);
                    this.tm.insertTile(tree,i,j);
                }
                else if(j == 0 || j == SimpleTilemap.coordinates[0].length-1){
                    SolidSprite tree = createTile(treeTexture,i,j);
                    this.tm.insertTile(tree,i,j);
                }
            }
        }
        SimpleTilemap.coordinates[5][4].releaseSprite();
        SimpleTilemap.coordinates[5][4] = null;
    }
}

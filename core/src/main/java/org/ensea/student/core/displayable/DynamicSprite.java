package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.ensea.student.core.displayable.DynamicSprite.direction.NONE;
import static org.ensea.student.core.engine.RenderEngine.pe;

public class DynamicSprite extends SolidSprite{
    int speed = 150;
    enum direction {NORTH, SOUTH, EAST, WEST, NONE};
    direction currentDirection = NONE;

    int spriteSheetNumberOfColumns = 10;
    int spriteSheetNumberOfRows = 4;

    Rectangle spriteRectangle;
    Animation am;

    float stateTime;

    float prevy = 0;
    float prevx = 0;

    Animation upAnimation;
    Animation downAnimation;
    Animation leftAnimation;
    Animation rightAnimation;
    Animation idleAnimation;

    public DynamicSprite(String textureFileName, float x, float y){
        super(textureFileName, x, y);

        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / spriteSheetNumberOfColumns,
                texture.getHeight() / spriteSheetNumberOfRows);

        TextureRegion[] downFrames = new TextureRegion[spriteSheetNumberOfColumns];
        TextureRegion[] upFrames = new TextureRegion[spriteSheetNumberOfColumns];
        TextureRegion[] leftFrames = new TextureRegion[spriteSheetNumberOfColumns];
        TextureRegion[] rightFrames = new TextureRegion[spriteSheetNumberOfColumns];

        TextureRegion[] idleFrames = new TextureRegion[1];

        idleFrames[0] = tmp[0][0];
        int index = 0;

        for (int j = 0; j < spriteSheetNumberOfColumns; j++) {
            downFrames[j] = tmp[0][j];
        }
        for (int j = 0; j < spriteSheetNumberOfColumns; j++) {
            upFrames[j] = tmp[2][j];
        }
        for (int j = 0; j < spriteSheetNumberOfColumns; j++) {
            leftFrames[j] = tmp[1][j];
        }
        for (int j = 0; j < spriteSheetNumberOfColumns; j++) {
            rightFrames[j] = tmp[3][j];
        }

        // Initialize the Animation with the frame interval and array of frames
        //am = new Animation(0.025f, walkFrames);
        upAnimation = new Animation(0.03F,upFrames);
        downAnimation = new Animation(0.03F,downFrames);
        leftAnimation = new Animation(0.03F,leftFrames);
        rightAnimation = new Animation(0.03F,rightFrames);

        idleAnimation = new Animation(0,idleFrames);

        xPos = x;
        yPos = y;
        prevx = x;
        prevy = y;
        spriteRectangle = new Rectangle(xPos,yPos, (float) texture.getWidth() / spriteSheetNumberOfColumns, (float) texture.getHeight() / spriteSheetNumberOfRows);
    }

    @Override
    public void draw(){
        boolean collide = pe.isColliding(spriteRectangle);

        //TODO The idle animation always faces forward, better if it faces the direction it was
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT)
            && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)
            && !Gdx.input.isKeyPressed(Input.Keys.UP)
            && !Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            am = idleAnimation;
        }
        if(collide){
            this.xPos = prevx;
            this.yPos = prevy;
        }
        /*** TODO FIX COLLISION !!!!*/
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            prevx = this.xPos;
            am = leftAnimation;
            currentDirection = direction.WEST;
            xPos -= Gdx.graphics.getDeltaTime() * speed;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            prevx = this.xPos;
            am = rightAnimation;
            currentDirection = direction.EAST;
            xPos += Gdx.graphics.getDeltaTime() * speed;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            prevy = this.yPos;
            am = upAnimation;
            currentDirection = direction.NORTH;
            yPos += Gdx.graphics.getDeltaTime() * speed;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            prevy = this.yPos;
            am=downAnimation;
            currentDirection = direction.SOUTH;
            yPos -= Gdx.graphics.getDeltaTime() * speed;
        }
        batch.begin();
        spriteRectangle = new Rectangle(xPos,yPos, (float) texture.getWidth() / spriteSheetNumberOfColumns, (float) texture.getHeight() / spriteSheetNumberOfRows);
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = am.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, (int)xPos, (int)yPos);
        batch.end();
    }
}

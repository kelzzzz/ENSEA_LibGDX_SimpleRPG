package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.ensea.student.core.engine.GameEngine;

import static org.ensea.student.core.displayable.DynamicSprite.direction.NONE;

public class DynamicSprite extends SolidSprite{

    /*Direction related variables*/
    public enum direction {NORTH, SOUTH, EAST, WEST, NONE}

    private direction currentDirection = NONE;

    /*Movement related variables*/
    private Rectangle spriteRectangle;
    private float prevy;
    private float prevx;
    private final int speed = 150;
    private final GameEngine ge;

    /*Animation related variables*/
    private final int spriteSheetNumberOfColumns = 10;
    private final int spriteSheetNumberOfRows = 4;
    private float stateTime;

    private Animation currentAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation idleAnimation;

    public DynamicSprite(String textureFileName, float x, float y){
        super(textureFileName, x, y);
        formatAnimationFromSpriteSheet();
        this.prevx = x;
        this.prevy = y;
        this.spriteRectangle = new Rectangle(this.xPos,this.yPos, (float) this.texture.getWidth() / this.spriteSheetNumberOfColumns, (float) texture.getHeight() / spriteSheetNumberOfRows);
        ge = new GameEngine(this);
    }

    @Override
    public void draw(){
        ge.update();
        batch.begin();
        TextureRegion currentFrame = getCurrentFrame();
        batch.draw(currentFrame, (int)xPos, (int)yPos);
        batch.end();
    }

    private void formatAnimationFromSpriteSheet() {
        TextureRegion[][] tmp = TextureRegion.split(this.texture,
                this.texture.getWidth() / this.spriteSheetNumberOfColumns,
                this.texture.getHeight() / this.spriteSheetNumberOfRows);

        TextureRegion[] downFrames = new TextureRegion[this.spriteSheetNumberOfColumns];
        TextureRegion[] upFrames = new TextureRegion[this.spriteSheetNumberOfColumns];
        TextureRegion[] leftFrames = new TextureRegion[this.spriteSheetNumberOfColumns];
        TextureRegion[] rightFrames = new TextureRegion[this.spriteSheetNumberOfColumns];
        TextureRegion[] idleFrames = new TextureRegion[1];

        idleFrames[0] = tmp[0][0];

        storeFrames(downFrames, tmp, 0);
        storeFrames(upFrames, tmp, 2);
        storeFrames(leftFrames, tmp, 1);
        storeFrames(rightFrames, tmp, 3);

        initDirectionalAnimations(upFrames, downFrames, leftFrames, rightFrames, idleFrames);
    }

    private void initDirectionalAnimations(TextureRegion[] upFrames, TextureRegion[] downFrames, TextureRegion[] leftFrames, TextureRegion[] rightFrames, TextureRegion[] idleFrames) {
        // Initialize the Animation with the frame interval and array of frames
        this.upAnimation = new Animation(0.03F, upFrames);
        this.downAnimation = new Animation(0.03F, downFrames);
        this.leftAnimation = new Animation(0.03F, leftFrames);
        this.rightAnimation = new Animation(0.03F, rightFrames);
        this.idleAnimation = new Animation(0, idleFrames);
    }

    private void storeFrames(TextureRegion[] framesList, TextureRegion[][] tmp, int x) {
        System.arraycopy(tmp[x], 0, framesList, 0, this.spriteSheetNumberOfColumns);
    }

    private TextureRegion getCurrentFrame() {
        spriteRectangle = new Rectangle(xPos,yPos, (float) texture.getWidth() / spriteSheetNumberOfColumns, (float) texture.getHeight() / spriteSheetNumberOfRows);
        stateTime += Gdx.graphics.getDeltaTime();
        return currentAnimation.getKeyFrame(stateTime, true);
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getSpriteRectangle() {
        return spriteRectangle;
    }

    public float getPrevy() {
        return prevy;
    }

    public float getPrevx() {
        return prevx;
    }

    public Animation getUpAnimation() {
        return upAnimation;
    }

    public Animation getDownAnimation() {
        return downAnimation;
    }

    public Animation getLeftAnimation() {
        return leftAnimation;
    }

    public Animation getRightAnimation() {
        return rightAnimation;
    }

    public Animation getIdleAnimation() {
        return idleAnimation;
    }
    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void setCurrentDirection(direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setPrevy(float prevy) {
        this.prevy = prevy;
    }

    public void setPrevx(float prevx) {
        this.prevx = prevx;
    }
}

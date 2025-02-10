package org.ensea.student.core.engine;

import org.ensea.student.core.displayable.DynamicSprite;
import org.ensea.student.core.displayable.SolidSprite;

public class PhysicsEngine implements Engine{
    public SolidSprite collidable;

    public PhysicsEngine(SolidSprite sp){
        this.collidable = sp;
    }
    @Override
    public void update() {
        /// ?
    }

}

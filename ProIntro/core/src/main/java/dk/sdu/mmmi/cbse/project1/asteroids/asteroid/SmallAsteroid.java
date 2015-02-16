/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.project1.asteroids.asteroid;

import dk.sdu.mmmi.cbse.project1.engine.Body;
import dk.sdu.mmmi.cbse.project1.engine.Health;
import dk.sdu.mmmi.cbse.project1.engine.Physics;
import dk.sdu.mmmi.cbse.project1.events.Events;
import playn.core.Image;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 *
 * @author Bullari
 */
public class SmallAsteroid extends Asteroid{
    
    public SmallAsteroid(float x, float y, float angle){
        Image asteroidImage = assets().getImageSync("images/Asteroid.png");
        view = graphics().createImageLayer(asteroidImage);
        view.setOrigin(asteroidImage.width() / 4, asteroidImage.height() / 4);
        
        body = new Body(this);
        body.x = x;
        body.y = y;
        body.angle = angle;
        body.radius = asteroidImage.height() / 4;

        physics = new Physics(this);
        physics.thrust(8.0);
        
        health = new Health(this);
        health.hits = 5;
    }
    
    @Override
    public void onDied(Events.DieEvent dieEvent) {
        for (Events.IEntityListener listener : listenerList) {
            listener.onDetroy(new Events.DestroyEvent(SmallAsteroid.this));
        }
    }
}

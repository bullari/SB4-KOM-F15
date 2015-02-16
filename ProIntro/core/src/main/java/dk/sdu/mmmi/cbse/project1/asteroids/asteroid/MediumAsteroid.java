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
public class MediumAsteroid extends Asteroid {
    
    private int age;
    
    public MediumAsteroid(float x, float y, float angle){
        Image asteroidImage = assets().getImageSync("images/Asteroid.png");
        view = graphics().createImageLayer(asteroidImage);
        view.setOrigin(asteroidImage.width(), asteroidImage.height());
        view.setSize(asteroidImage.width() / 2, asteroidImage.height() / 2);
        view.transform();
        
        body = new Body(this);
        body.x = x;
        body.y = y;
        body.angle = angle;
        body.radius = asteroidImage.height() / 2f;

        physics = new Physics(this);
        physics.thrust(5.0);
        
        health = new Health(this);
        health.hits = 10;
    }
    
     @Override
    public void onUpdate(Events.UpdateEvent event) {
        super.onUpdate(event);

        age++;

        if (age > 100) {
            onDied(new Events.DieEvent(this, this));
        }
    }
    
    @Override
    public void onDied(Events.DieEvent dieEvent) {
        float x = body.x;
        float y = body.y;
        float angle = body.angle;
        
        for (Events.IEntityListener listener : listenerList) {
            listener.onCreate(new Events.CreateEvent
                        (this, new SmallAsteroid(x, y, angle - 1.5f)));
            listener.onCreate(new Events.CreateEvent
                        (this, new SmallAsteroid(x, y, angle + 1.5f)));
            listener.onDetroy(new Events.DestroyEvent(MediumAsteroid.this));
        }
    }
    
}

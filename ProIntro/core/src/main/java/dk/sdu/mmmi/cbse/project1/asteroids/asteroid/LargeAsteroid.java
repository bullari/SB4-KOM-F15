/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.project1.asteroids.asteroid;

import dk.sdu.mmmi.cbse.project1.asteroids.Bullet;
import dk.sdu.mmmi.cbse.project1.engine.Body;
import dk.sdu.mmmi.cbse.project1.engine.Health;
import dk.sdu.mmmi.cbse.project1.engine.Physics;
import dk.sdu.mmmi.cbse.project1.events.Events;
import dk.sdu.mmmi.cbse.project1.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;
import java.util.Random;
import playn.core.Image;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 *
 * @author Bullari
 */
public class LargeAsteroid extends Asteroid {

    private int age;

    public LargeAsteroid() {

        Image asteroidImage = assets().getImageSync("images/Asteroid.png");
        view = graphics().createImageLayer(asteroidImage);
        view.setOrigin(asteroidImage.width(), asteroidImage.height());
        view.transform();

        body = new Body(this);
        body.radius = asteroidImage.height();
        randomizePos();
        randomizeAngle();

        physics = new Physics(this);
        physics.thrust(5.0);

        health = new Health(this);
        health.hits = 20;

        //TEMP
        age = 0;
    }

    private void randomizePos() {
        Random random = new Random();

        body.x = random.nextInt(graphics().width());
        body.y = random.nextInt(graphics().height());
    }

    private void randomizeAngle() {
        Random random = new Random();
        // Full circle in radians is 2*PI, which is around 6.283
        // Remove decimal and divide by 1000 to achieve a random angle for
        // a full circle
        body.angle = random.nextInt(6283) / 1000;
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
            listener.onCreate(new CreateEvent(this, new MediumAsteroid(x, y, angle - 1.0f)));
            listener.onCreate(new CreateEvent(this, new MediumAsteroid(x, y, angle + 1.0f)));
            listener.onDetroy(new DestroyEvent(LargeAsteroid.this));
        }
    }
}

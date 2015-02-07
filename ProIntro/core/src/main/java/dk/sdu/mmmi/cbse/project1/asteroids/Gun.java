package dk.sdu.mmmi.cbse.project1.asteroids;

import dk.sdu.mmmi.cbse.project1.engine.IEntity;
import dk.sdu.mmmi.cbse.project1.engine.Weapon;
import dk.sdu.mmmi.cbse.project1.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;

public class Gun extends Weapon {

	public Gun(IEntity entity) {
		super(entity);
	}

	@Override
	public void fire(double thrust) {

		Bullet bullet = new Bullet();
		bullet.body().x = (float) (entity.body().x + Math
				.sin(entity.body().angle));
		bullet.body().y = (float) (entity.body().y + Math
				.cos(entity.body().angle));
		bullet.body().angle = entity.body().angle;
		bullet.physics().thrust(thrust);

		// events
		for (IEntityListener l : entity.listeners()) {
			l.onCreate(new CreateEvent(this, bullet));
		}

		super.fire(thrust);
	}

}

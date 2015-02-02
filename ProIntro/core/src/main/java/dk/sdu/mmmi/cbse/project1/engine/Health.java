package dk.sdu.mmmi.cbse.project1.engine;

import dk.sdu.mmmi.cbse.project1.events.Events.DieEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.HurtEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;

public class Health {
	private final IEntity entity;
	public Integer hits;

	public Health(IEntity entity) {
		this.entity = entity;
	}

	public void hit(IEntity source, Integer damage) {
		hits -= damage;

		for (IEntityListener l : entity.listeners()) {
			l.onHurt(new HurtEvent(source, entity));
		}

		if (hits <= 0) {
			for (IEntityListener l : entity.listeners()) {
				l.onDied(new DieEvent(source, entity));
			}
		}
	}
}

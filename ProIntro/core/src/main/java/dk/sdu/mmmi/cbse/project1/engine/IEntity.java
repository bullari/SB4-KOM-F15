package dk.sdu.mmmi.cbse.project1.engine;

import java.util.List;

import playn.core.ImageLayer;
import dk.sdu.mmmi.cbse.project1.events.Events.IEngineListener;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;

public interface IEntity extends IEngineListener, IEntityListener {
	// ACTIONS

	// EVENTS
	// @Subscribe
	// void onEntityCreated(CreateEvent entity);
	//
	// @Subscribe
	// void onEntityDestroyed(DestroyEvent entity);

	// COMPONENTS
	Body body();

	Physics physics();

	Health health();

	Weapon getWeapon();

	ImageLayer view();

	List<IEntityListener> listeners();

	void addEntityListener(IEntityListener entityListener);

	void removeListener(IEntityListener entityListener);
}

package dk.sdu.mmmi.cbse.project1.asteroids;

import static playn.core.PlayN.graphics;

import java.util.ArrayList;
import java.util.List;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.util.Clock;
import dk.sdu.mmmi.cbse.project1.engine.IEntity;
import dk.sdu.mmmi.cbse.project1.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.EntityListenerAdapter;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;
import dk.sdu.mmmi.cbse.project1.events.Events.RenderEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.UpdateEvent;

public class AsteroidsGame extends Game.Default {

	private final Clock.Source clock = new Clock.Source(33);
	private GroupLayer layer;
	private final List<IEntity> entities = new ArrayList<IEntity>();

	public AsteroidsGame() {
		super(33); // call update every 33ms (30 times per second)
	}

	@Override
	public void init() {

		// create a group layer to hold everything
		layer = graphics().rootLayer();

		// create and add background image layer
		layer.add(graphics().createImmediateLayer(new StarRenderer(clock)));

		// add Player

		IEntity ship = new Ship();
		gameListener.onCreate(new CreateEvent(this, ship));
	}

	@Override
	public void update(int delta) {
		clock.update(delta);

		for (int i = 0; i < entities.size(); i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				// compare list.get(i) and list.get(j)
				IEntity entity = entities.get(i);
				IEntity target = entities.get(j);
				if (target != null && testCollision(entity, target)) {
					target.health().hit(entity, 1);
					entity.health().hit(target, 1);
				}
			}
		}

		for (IEntity e : new ArrayList<IEntity>(entities)) {
			e.onUpdate(new UpdateEvent(delta));
		}
	}

	public boolean testCollision(IEntity source, IEntity target) {
		float dx;
		float dy;

		dx = source.body().x - target.body().x;
		dy = source.body().y - target.body().y;

		boolean isCollision = Math.sqrt((dx * dx) + (dy * dy)) <= source.body().radius
				+ target.body().radius;

		// if (isCollision) {
		// System.out.println(String.format(
		// "%s hits %s, dist=%s, totalRadius=%s", source, target,
		// Math.sqrt((dx * dx) + (dy * dy)), source.body().radius
		// + target.body().radius));
		// }

		return isCollision;
	}

	@Override
	public void paint(float alpha) {
		// the background automatically paints itself, so no need to do anything
		// here!
		clock.paint(alpha);

		for (IEntity e : entities) {
			e.onRender(new RenderEvent(alpha));
		}
	}

	private final IEntityListener gameListener = new EntityListenerAdapter() {

		@Override
		public void onCreate(CreateEvent createEvent) {
			IEntity entitycreated = createEvent.entity();
			entitycreated.addEntityListener(this);
			entities.add(entitycreated);
			layer.add(createEvent.entity().view());
		};

		@Override
		public void onDetroy(DestroyEvent destroyEvent) {

			IEntity entityDestroyed = destroyEvent.entity();
			entities.remove(entityDestroyed);
			layer.remove(entityDestroyed.view());
		};
	};
}

package dk.sdu.mmmi.cbse.project1.engine;

import static playn.core.PlayN.graphics;
import playn.core.ImageLayer;
import dk.sdu.mmmi.cbse.project1.events.Events.RenderEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.UpdateEvent;

public class Physics {

	private final IEntity entity;
	public Double drag = 1.0;
	public Double velocityX = 0.0;
	public Double velocityY = 0.0;

	/**
	 * Provides a basic physics step without collision detection. Extend to add
	 * collision handling.
	 */
	public Physics(IEntity entity) {
		this.entity = entity;
	}

	public void onUpdate(UpdateEvent event) {

		entity.body().x += velocityX;
		entity.body().y += velocityY;

		velocityX *= drag;
		velocityY *= drag;
	}

	public void onRender(RenderEvent event) {
		if (entity.view() != null) {
			ImageLayer view = entity.view();
			Body body = entity.body();

			view.setTranslation(body.x, body.y);
			view.setRotation(body.angle);
			view.setAlpha(1.0f);
			view.setScale(1.0f);

			// wrap around if we hit the edge of the display
			float width = graphics().width();
			float height = graphics().height();

			if (body.x < 0)
				body.x += width;
			else if (body.x > width)
				body.x -= width;
			if (body.y < 0)
				body.y += height;
			else if (body.y > height)
				body.y -= height;
		}

	}

	public void thrust(Double power) {
		velocityX += Math.sin(-entity.body().angle) * power;
		velocityY += Math.cos(-entity.body().angle) * power;
	}
}

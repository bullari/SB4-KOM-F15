package dk.sdu.mmmi.cbse.project1.asteroids;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.Keyboard;
import playn.core.Keyboard.Listener;
import playn.core.PlayN;
import dk.sdu.mmmi.cbse.project1.engine.Body;
import dk.sdu.mmmi.cbse.project1.engine.Entity;
import dk.sdu.mmmi.cbse.project1.engine.Health;
import dk.sdu.mmmi.cbse.project1.engine.Physics;
import dk.sdu.mmmi.cbse.project1.events.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.DieEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;
import dk.sdu.mmmi.cbse.project1.events.Events.UpdateEvent;

public class Ship extends Entity {
	private boolean left;
	private boolean right;
	private boolean space;
	private boolean up;
	protected boolean down;
        
        // TEMP
        private double forwardWeaponThrust;

	public Ship() {

		Image shipImage = assets().getImageSync("images/Ship.png");
		view = graphics().createImageLayer(shipImage);
		view.setOrigin(shipImage.width() / 2f, shipImage.height() / 2f);

		body = new Body(this);
		body.x = graphics().width() / 4;
		body.y = graphics().height() / 2;
		body.radius = shipImage.height() / 2f;

		physics = new Physics(this);
		physics.drag = 0.9;

		health = new Health(this);
		health.hits = 100;

		forwardWeapon = new Gun(this);
                forwardWeaponThrust = 20.0;

		// Events
		PlayN.keyboard().setListener(this.spacebarListener);

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Ship@");
		sb.append(this.hashCode());
		sb.append("={x=");
		sb.append(body.x);
		sb.append(",y=");
		sb.append(body.y);
		sb.append(", a=");
		sb.append(body.angle);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public void onDied(DieEvent dieEvent) {
		for (IEntityListener listener : listenerList) {
			listener.onDetroy(new DestroyEvent(Ship.this));
		}
		PlayN.keyboard().setListener(null);
	}

	@Override
	public void onUpdate(UpdateEvent event) {
                super.onUpdate(event);
		if (space) {
			forwardWeapon.fire(forwardWeaponThrust);
			//space = false;
		}

		if (up)
			physics.thrust(1.0);

		if (down)
			physics.thrust(-1.0);

		if (left)
			body.angle -= 0.1;

		if (right)
			body.angle += 0.1;
	}

	private final Listener spacebarListener = new Keyboard.Adapter() {

		@Override
		public void onKeyDown(Keyboard.Event event) {
			switch (event.key()) {
			case LEFT:
				left = true;
				break;
			case RIGHT:
				right = true;
				break;
			case UP:
				up = true;
				break;
			case DOWN:
				down = true;
				break;
			case SPACE:
				space = true;
				break;
			default:
				break;
			}
		}

		@Override
		public void onKeyUp(Keyboard.Event event) {
			switch (event.key()) {
			case LEFT:
				left = false;
				break;
			case RIGHT:
				right = false;
				break;
			case UP:
				up = false;
				break;
			case DOWN:
				down = false;
				break;
			case SPACE:
				space = false;
				break;
			default:
				break;
			}
		}
	};
}

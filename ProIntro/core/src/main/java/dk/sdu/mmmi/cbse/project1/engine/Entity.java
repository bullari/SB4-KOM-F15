package dk.sdu.mmmi.cbse.project1.engine;

import dk.sdu.mmmi.cbse.project1.events.Events;
import dk.sdu.mmmi.cbse.project1.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.DieEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.HurtEvent;
import dk.sdu.mmmi.cbse.project1.events.Events.IEntityListener;
import dk.sdu.mmmi.cbse.project1.events.Events.UpdateEvent;
import java.util.ArrayList;
import java.util.List;
import playn.core.ImageLayer;
import static playn.core.PlayN.graphics;

public class Entity implements IEntity {

    public Body body;
    public Physics physics;
    public Health health;
    public Weapon forwardWeapon;
    public ImageLayer view;
    protected final List<IEntityListener> listenerList;

    public Entity() {
        listenerList = new ArrayList<IEntityListener>();
    }

    @Override
    public void onRender(Events.RenderEvent event) {
        if (view != null) {
            view.setTranslation(body.x, body.y);
            view.setRotation(this.body.angle);
            view.setAlpha(1.0f);
            view.setScale(1.0f);

            // wrap around if we hit the edge of the display
            float width = graphics().width();
            float height = graphics().height();
            if (body.x < 0) {
                body.x += width;
            } else if (body.x > width) {
                body.x -= width;
            }
            if (body.y < 0) {
                body.y += height;
            } else if (body.y > height) {
                body.y -= height;
            }
        }

    }

    @Override
    public Body body() {
        return this.body;
    }

    @Override
    public Physics physics() {
        return this.physics;
    }

    @Override
    public Health health() {
        return this.health;
    }

    @Override
    public Weapon getWeapon() {
        return this.forwardWeapon;
    }

    @Override
    public ImageLayer view() {
        return this.view;
    }

	// Events
    @Override
    public void onDetroy(DestroyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCreate(CreateEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDied(DieEvent dieEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onHurt(HurtEvent hurtEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdate(UpdateEvent event) {
        physics().onUpdate(event);
    }

    @Override
    public void addEntityListener(IEntityListener entityListener) {
        listenerList.add(entityListener);
    }

    @Override
    public void removeListener(IEntityListener listener) {
        this.listenerList.remove(listener);

    }

    @Override
    public List<IEntityListener> listeners() {
        return listenerList;
    }

}

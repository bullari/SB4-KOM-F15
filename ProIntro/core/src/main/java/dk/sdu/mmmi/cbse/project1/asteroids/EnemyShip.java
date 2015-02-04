package dk.sdu.mmmi.cbse.project1.asteroids;

import dk.sdu.mmmi.cbse.project1.engine.Body;
import dk.sdu.mmmi.cbse.project1.engine.Entity;
import dk.sdu.mmmi.cbse.project1.engine.Health;
import dk.sdu.mmmi.cbse.project1.engine.Physics;
import dk.sdu.mmmi.cbse.project1.events.Events;
import playn.core.Image;
import playn.core.Keyboard;
import playn.core.PlayN;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.util.Random;

/**
 *
 * @author Bullari
 */
public class EnemyShip extends Entity {

    private boolean left;
    private boolean right;
    private boolean space;
    private boolean up;
    protected boolean down;

    public EnemyShip() {
        Image shipImage = assets().getImageSync("images/EnemyShip.png");
        view = graphics().createImageLayer(shipImage);
        view.setOrigin(shipImage.width() / 2f, shipImage.height() / 2f);

        body = new Body(this);
        body.x = graphics().width() / 1.33f;
        body.y = graphics().height() / 2;
        body.radius = shipImage.height() / 2f;

        physics = new Physics(this);
        physics.drag = 0.9;

        health = new Health(this);
        health.hits = 5;

        weapon = new Gun(this);

        // Events
        //PlayN.keyboard().setListener(this.spacebarListener);
    }
    public void randomShootAction() {
        Random random = new Random();
        int action = random.nextInt(2);
        
        switch (action) {
            case 0:
                space = false;
                break;
            case 1:
                space = true;
                break;
            default:
                break;
        }
    }
    
    public void randomMovementAction(){
        Random random = new Random();
        int action = random.nextInt(4);
        
        switch (action) {
            case 0:
                left = true;
                break;
            case 1:
                right = true;
                break;
            default:
                break;
        }
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
    public void onDied(Events.DieEvent dieEvent) {
        for (Events.IEntityListener listener : listenerList) {
            listener.onDetroy(new Events.DestroyEvent(EnemyShip.this));
        }
        PlayN.keyboard().setListener(null);
    }

    @Override
    public void onUpdate(Events.UpdateEvent event) {
        super.onUpdate(event);
        randomShootAction();
        randomMovementAction();
        
        if (space) {
            weapon.fire();
            space = false;
        }

//        if (up) {
//            physics.thrust(1.0);
//            up = false;
//        }
        physics.thrust(1.0);
        
        if (down) {
            physics.thrust(-1.0);
            down = false;
        }

        if (left) {
            body.angle -= 0.1;
            left = false;
        }

        if (right) {
            body.angle += 0.1;
            right = false;
        }
    }
    /**
     * Currently not used since the enemy ship is controlled by the computer
     */
    private final Keyboard.Listener spacebarListener = new Keyboard.Adapter() {

        @Override
        public void onKeyDown(Keyboard.Event event) {
            switch (event.key()) {
                case A:
                    left = true;
                    break;
                case D:
                    right = true;
                    break;
                case W:
                    up = true;
                    break;
                case S:
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
                case A:
                    left = false;
                    break;
                case D:
                    right = false;
                    break;
                case W:
                    up = false;
                    break;
                case S:
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

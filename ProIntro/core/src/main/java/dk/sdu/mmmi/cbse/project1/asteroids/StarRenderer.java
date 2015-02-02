package dk.sdu.mmmi.cbse.project1.asteroids;

import static playn.core.PlayN.graphics;

import java.util.Random;

import playn.core.ImmediateLayer;
import playn.core.Surface;
import playn.core.util.Clock;
import pythagoras.f.Point;

public class StarRenderer implements ImmediateLayer.Renderer{
    private final Random random = new Random();
    private final Point[] stars;
    private float nextTwinkle;
	private final Clock clock;

    public StarRenderer(Clock clock) {
    	this.clock = clock;
        stars = new Point[50];
        for (int ii = 0; ii < stars.length; ii++) stars[ii] = randomize(new Point());
	}

    @Override
    public void render (Surface surf) {
      surf.setFillColor(0xFFFFFFFF);
      for (Point p : stars) {
        surf.fillRect(p.x, p.y, 2, 2);
      }
      if (clock.time() > nextTwinkle) {
        randomize(stars[random.nextInt(stars.length)]);
        nextTwinkle = clock.time() + 500 + random.nextInt(1500); // twinkle a star every 1/2 to 2s
      }
    }

    private Point randomize (Point p) {
      p.x = random.nextFloat()*graphics().width();
      p.y = random.nextFloat()*graphics().height();
      return p;
    }
}

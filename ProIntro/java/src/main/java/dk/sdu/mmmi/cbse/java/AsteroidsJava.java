package dk.sdu.mmmi.cbse.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;
import dk.sdu.mmmi.cbse.project1.asteroids.AsteroidsGame;

public class AsteroidsJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed  
    config.appName = "Asteroids";
    JavaPlatform.register(config);
    PlayN.run(new AsteroidsGame());
  }
}

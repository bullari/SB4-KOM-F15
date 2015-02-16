/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.project1.asteroids.asteroid;

import dk.sdu.mmmi.cbse.project1.engine.Entity;

/**
 *
 * @author Bullari
 */
public class Asteroid extends Entity {

    public Asteroid() {
        this.forwardWeapon = null;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Asteroid@");
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
}

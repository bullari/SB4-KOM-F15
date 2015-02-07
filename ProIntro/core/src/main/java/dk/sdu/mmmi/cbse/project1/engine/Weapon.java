package dk.sdu.mmmi.cbse.project1.engine;

public class Weapon {

	public IEntity entity;
	public Integer ammo = Integer.MAX_VALUE;

	/**
	 * Weapon is the base class for all weapons.
	 */
	public Weapon(IEntity entity) {
		this.entity = entity;
	}

	public void fire(double thrust) {
		ammo--;
	}
}

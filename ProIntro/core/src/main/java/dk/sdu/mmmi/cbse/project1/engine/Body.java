package dk.sdu.mmmi.cbse.project1.engine;

public class Body {

	private IEntity entity;
	public float x = 0;
	public float y = 0;
	public float angle = 0;
	public float radius = 10;

	/**
	 * If you give an entity a body it can take physical form in the world,
	 * although to see it you will need a view.
	 */

	public Body(IEntity entity) {
		this.setEntity(entity);
	}

	public IEntity getEntity() {
		return entity;
	}

	public void setEntity(IEntity entity) {
		this.entity = entity;
	}
}

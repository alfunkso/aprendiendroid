package cl.handsome.aprendiendroid;

public abstract class Operacion {
	
	protected Integer total;
	
	public Operacion( Integer total ) {
		this.total = total;
	}
	
	public abstract boolean correcto( Integer operando1 , Integer operando2 );

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}

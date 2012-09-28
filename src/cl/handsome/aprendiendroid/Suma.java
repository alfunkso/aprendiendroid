package cl.handsome.aprendiendroid;

public class Suma extends Operacion {

	public Suma(Integer total) {
		super(total);
	}

	@Override
	public boolean correcto(Integer operando1, Integer operando2) {
		return operando1 + operando2 == super.total;
	}

}

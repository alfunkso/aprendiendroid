package cl.handsome.aprendiendroid;

import java.util.ArrayList;
import java.util.List;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class Juego {

	private Estado estado;
	private List<Integer> numberPool;
	private Integer selected1, selected2;
	private Button btSelected1, btSelected2;
	private TextView instruccionesView;
	private Operacion operacionActual;
	private Integer correctas, incorrectas;
	private ColorStateList originalColors;
	
	public Juego() {
		this(new ArrayList<Integer>());
	}
	
	public Juego( List<Integer> numberPool ) {
		this(numberPool , new TextView(null));
	}
	
	public Juego( List<Integer> numberPool , TextView instruccionesView ) {
		this.estado = Estado.INSTRUYENDO;
		this.selected1 = null;
		this.selected2 = null;
		this.btSelected1 = null;
		this.btSelected2 = null;
		this.operacionActual = null;
		this.correctas = 0; 
		this.incorrectas = 0;
		this.numberPool = numberPool;
		this.instruccionesView = instruccionesView;
	}
	
	public void instruir() {		
		if ( numberPool.size() % 2 != 0 )
			throw new IllegalStateException("El total de numeros disponibles para jugar no es un numero par");
		if ( numberPool.isEmpty() ) {
			this.instruccionesView.setText("No quedan números con los que operar!");
			return;
		}
		
		int n1, n2;
		
		n1 = this.numberPool.get( (int)(Math.random()*this.numberPool.size()) );
		n2 = n1;
		while ( n2 == n1 )
			n2 = this.numberPool.get( (int)(Math.random()*this.numberPool.size()) );
		
		if ( Math.random() < 0.5 ) {
			this.operacionActual = new Suma( n1 + n2 );
			this.instruccionesView.setText( "Selecciona dos números que sumen " + this.operacionActual.getTotal() + "." );
		} else {
			this.operacionActual = new Resta( Math.abs(n1 - n2) );
			this.instruccionesView.setText( "Selecciona dos números que resten " + this.operacionActual.getTotal() + "." );
		}
		
		this.estado = Estado.JUGANDO;
	}
	
	public void seleccionar( Button bt ) {
		if ( this.estado != Estado.JUGANDO )
			return;
		
		if ( btSelected1 == null ) {
			if ( !bt.isEnabled() )
				return;
			
			this.originalColors = bt.getTextColors();
			this.btSelected1 = bt;
			this.btSelected1.setTextColor( new ColorStateList(					
					new int [] [] {
							new int [] {android.R.attr.state_pressed}
							, new int [] {android.R.attr.state_focused}
							, new int [0]
					}
					, new int [] {
							Color.GREEN,
							Color.GREEN,
							Color.GREEN } ) );
			this.selected1 = Integer.parseInt( this.btSelected1.getText().toString().trim() );
		} else if ( this.btSelected1 == bt ) {
			this.btSelected1.setTextColor( this.originalColors );
			this.btSelected1.setSelected(false);
			this.btSelected1 = null;
			this.selected1 = null;
		} else {
			this.estado = Estado.CORRIGIENDO;
			this.btSelected2 = bt;
			this.btSelected1.setTextColor( new ColorStateList(					
					new int [] [] {
							new int [] {android.R.attr.state_pressed}
							, new int [] {android.R.attr.state_focused}
							, new int [0]
					}
					, new int [] {
							Color.GREEN,
							Color.GREEN,
							Color.GREEN } ) );
			this.selected2 = Integer.parseInt( this.btSelected2.getText().toString().trim() );
			
			this.corregir();			
			
		}
	}
	
	private void corregir() {
		if ( selected1 == null || selected2 == null )
			throw new IllegalStateException("Uno de los valores selected1 o selected2 es null");

		if ( this.operacionActual.correcto( this.selected1 , this.selected2 ) ) {
			this.correctas++;
			this.instruccionesView.setText("¡Bien!. Correctas: " + this.correctas + ", Incorrectas: " + this.incorrectas);
		} else {
			this.incorrectas++;
			this.instruccionesView.setText("Incorrecto. Correctas: " + this.correctas + ", Incorrectas: " + this.incorrectas);
		}
		
		new Handler().postDelayed( new Runnable() {
			
			@Override
			public void run() {
				instruir();
			}
		} , 4000);
		
		this.btSelected1.setTextColor( this.originalColors );
		this.btSelected1.setTextColor( this.originalColors );
		this.btSelected1.setEnabled( false );
		this.btSelected2.setEnabled( false );
		this.numberPool.remove( this.numberPool.indexOf( this.selected1 ) );
		this.numberPool.remove( this.numberPool.indexOf( this.selected2 ) );
		
		this.btSelected1 = null;
		this.btSelected2 = null;
		this.selected1 = null;
		this.selected2 = null;
		this.operacionActual = null;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Integer> getNumberPool() {
		return numberPool;
	}

	public void setNumberPool(List<Integer> numberPool) {
		this.numberPool = numberPool;
	}

	public Integer getSelected1() {
		return selected1;
	}

	public void setSelected1(Integer selected1) {
		this.selected1 = selected1;
	}

	public Integer getSelected2() {
		return selected2;
	}

	public void setSelected2(Integer selected2) {
		this.selected2 = selected2;
	}

	public Button getBtSelected1() {
		return btSelected1;
	}

	public void setBtSelected1(Button btSelected1) {
		this.btSelected1 = btSelected1;
	}

	public Button getBtSelected2() {
		return btSelected2;
	}

	public void setBtSelected2(Button btSelected2) {
		this.btSelected2 = btSelected2;
	}

	public TextView getInstruccionesView() {
		return instruccionesView;
	}

	public void setInstruccionesView(TextView instruccionesView) {
		this.instruccionesView = instruccionesView;
	}

	public Operacion getOperacionActual() {
		return operacionActual;
	}

	public void setOperacionActual(Operacion operacionActual) {
		this.operacionActual = operacionActual;
	}
	
}

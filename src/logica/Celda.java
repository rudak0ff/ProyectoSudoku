package logica;

public class Celda {
	private Integer valor;
	private EntidadGrafica entidad;
	private boolean estado;
	
	public Celda() {
		estado = true;
		this.valor = 0;
		this.entidad = new EntidadGrafica();
	}

	//Actualiza el valor de la celda + 1 y actualiza la entidad grafica con su respectivo valor
	public void actualizarEnClick() {
		if(estado) {
			if(this.valor != null && this.valor < this.getCantElementos()) {
				this.valor++;
			}
			else {
				this.valor = 1;
			}
			if(valor>0)
				entidad.actualizar(valor-1);
			else
				entidad.actualizar(valor);
		}
	}
	
	public int getCantElementos() {
		return this.entidad.getImagenes().length;
	}
	public EntidadGrafica getEntidadGrafica() {
		return this.entidad;
	}
	public Integer getValor() {
		return this.valor;
	}
	//Setea el valor en la celda y actualiza su entidad grafica
	public void setValor(Integer valor) {
		if(this.valor != null && this.valor-1 < this.getCantElementos()) {
			this.valor = valor;
			this.entidad.actualizar(valor-1);
		}
	    else {
		this.valor = null;
	    }
		
	}
	public void setGrafica(EntidadGrafica g) {
		this.entidad = g;
	}
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}

package logica;

import javax.swing.ImageIcon;

public class EntidadGrafica {

	private ImageIcon grafico;
	private String[] imagenes;
	private String[] imagenesErrores;
	
	private String imagenVacia;
	public EntidadGrafica() {
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/sprites/1.png","/sprites/2.png","/sprites/3.png","/sprites/4.png","/sprites/5.png","/sprites/6.png","/sprites/7.png","/sprites/8.png","/sprites/9.png"};
		this.imagenesErrores = new String[]{"/sprites/1ERROR.png","/sprites/2ERROR.png","/sprites/3ERROR.png","/sprites/4ERROR.png","/sprites/5ERROR.png","/sprites/6ERROR.png","/sprites/7ERROR.png","/sprites/8ERROR.png","/sprites/9ERROR.png"};
		this.imagenVacia = new String("/sprites/0.png");	
	}
	
	public void actualizar(int indice) {
		if(indice == -1) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(imagenVacia));
			this.grafico.setImage(imageIcon.getImage());
		}
		else if (indice <= this.imagenes.length-1) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public void actualizarImagenErrores(int indice) {
		if (indice <= this.imagenesErrores.length-1) {
			ImageIcon imageIconError = new ImageIcon(this.getClass().getResource(this.imagenesErrores[indice]));
			this.grafico.setImage(imageIconError.getImage());
		}
	}

	public ImageIcon getGrafico() {
		return this.grafico;
	}
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
	
	
	
}

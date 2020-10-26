package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImagenesParaCronometro {

	private String[] imagenesTiempo;
	private ImageIcon grafico;
	
	public ImagenesParaCronometro() {
		this.imagenesTiempo = new String[] {"/sprites/Cero_temp.png","/sprites/Uno_temp.png","/sprites/Dos_temp.png","/sprites/Tres_temp.png","/sprites/Cuatro_temp.png","/sprites/Cinco_temp.png","/sprites/Seis_temp.png","/sprites/Siete_temp.png","/sprites/Ocho_temp.png","/sprites/Nueve_temp.png"};
	}
	
	public ImageIcon actualizarImagenTiempo(int indice) {
		
			ImageIcon imageIconTiempo = new ImageIcon(this.getClass().getResource(this.imagenesTiempo[indice]));
			
			return imageIconTiempo;
	}
	
	public String[] getImagenesTiempo() {
		return this.imagenesTiempo;
	}
	
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	
	public ImageIcon[] getImagenes() {		
		ImageIcon[] toReturn = new ImageIcon[11];
		for(int i=0; i<11; i++)
			toReturn[i] = new ImageIcon(getClass().getResource("/imagenes/r"+i+".png"));
		return toReturn;
	}
	
	
	
}

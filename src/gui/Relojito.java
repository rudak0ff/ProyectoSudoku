package gui;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Relojito extends TimerTask{
	
	private ImagenesParaCronometro im;
	private Timer t;
	private int hor1, hor2, min1, min2, seg1, seg2;
	
	private JLabel j1,j2,j3,j4,j5,j6;
	public Relojito(JLabel l1,JLabel l2,JLabel l3,JLabel l4,JLabel l5,JLabel l6) {
	t = new Timer();
	j1 = l1;
	j2 = l2;
	j3 = l3;
	j4 = l4;
	j5 = l5;
	j6 = l6;
	hor1=0;
	hor2=0;
	min1=0;
	min2=0;
	seg1=0;
	seg2=0;
	im=new ImagenesParaCronometro();
	}
	public void run() {
		seg1++;
		if(seg1==10) {
			seg2++;
			seg1=0;
			cambiarLabels(seg1,j1);
			cambiarLabels(seg2,j2);
			if(seg2==6) {
				min1++;
				seg2=0;
				seg1=0;
				cambiarLabels(min1,j3);
				cambiarLabels(seg2,j2);
				cambiarLabels(seg1,j1);
				if(min1==10) {
					min1=0;
					min2++;
					seg1=0;
					seg2=0;
					cambiarLabels(min1,j3);
					cambiarLabels(min2,j4);
					cambiarLabels(seg1,j1);
					cambiarLabels(seg2,j2);
					if(min2==6) {
						hor1++;
						min2=0;
						min1=0;
						seg1=0;
						seg2=0;
						cambiarLabels(hor1,j5);
						cambiarLabels(min1,j3);
						cambiarLabels(min2,j4);
						cambiarLabels(seg1,j1);
						cambiarLabels(seg2,j2);
						if(hor1==10) {
							hor2++;
							hor1=0;
							min2=0;
							min1=0;
							seg1=0;
							seg2=0;
							cambiarLabels(hor2,j6);
							cambiarLabels(hor1,j5);
							cambiarLabels(min1,j3);
							cambiarLabels(min2,j4);
							cambiarLabels(seg1,j1);
							cambiarLabels(seg2,j2);
							if(hor2==6) {
								hor2=0;
								hor1=0;
								min2=0;
								min1=0;
								seg1=0;
								seg2=0;
								cambiarLabels(hor2,j6);
								cambiarLabels(hor1,j5);
								cambiarLabels(min1,j3);
								cambiarLabels(min2,j4);
								cambiarLabels(seg1,j1);
								cambiarLabels(seg2,j2);
							}
							else {
								cambiarLabels(hor2,j6);
							}
						}
						else {
							cambiarLabels(hor1,j5);
						}
					}
					else {
						cambiarLabels(min2,j4);
					}
				}
				else {
					cambiarLabels(min1,j3);
				}
		}
			else {
				cambiarLabels(seg2,j2);
			}
		}
		else {
			cambiarLabels(seg1,j1);
		}
		
	};
	
	private void cambiarLabels(int num, JLabel j) {
		
		 switch (num) {
		 case 0: 
			 j.setIcon(im.actualizarImagenTiempo(0));
			 break;
		 case 1: 
			 j.setIcon(im.actualizarImagenTiempo(1));
			 break;
		 case 2:
			 j.setIcon(im.actualizarImagenTiempo(2));
			 break;
		 case 3: 
			 j.setIcon(im.actualizarImagenTiempo(3));
			 break;
		 case 4:
			 j.setIcon(im.actualizarImagenTiempo(4));
			 break;	 
		 case 5: 
			 j.setIcon(im.actualizarImagenTiempo(5));
			 break;
		 case 6:
			 j.setIcon(im.actualizarImagenTiempo(6));
			 break;
		 case 7: 
			 j.setIcon(im.actualizarImagenTiempo(7));
			 break;
		 case 8:
			 j.setIcon(im.actualizarImagenTiempo(8));
			 break;
		 case 9: 
			 j.setIcon(im.actualizarImagenTiempo(9));
			 break;
		 }
	j.repaint();
	}
	
	public void iniciar() {
		t.schedule(this, 0,1000);
	}
	
}

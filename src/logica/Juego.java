package logica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import gui.Sudoku;
public class Juego {

	private Celda[][] tablero;
	private int cantFilas;
	private Sudoku sud;
	private int[][] tableroEnteros;
	
	public Juego() {
		
		this.cantFilas = 9;
		tableroEnteros = new int[this.cantFilas][this.cantFilas];
		tablero = new Celda[this.cantFilas][this.cantFilas];
		for(int i=0 ; i<cantFilas ; i++) {
			for(int j=0 ; j<cantFilas ; j++) {
				tablero[i][j]= new Celda();
				}
			}
	}
	
	public String abrirArchivo() {
		  String aux="";   
		  String texto="";
		  try
		  {
		   /**llamamos el metodo que permite cargar la ventana*/
		   JFileChooser file=new JFileChooser();
		   file.showOpenDialog(sud);
		   /**abrimos el archivo seleccionado*/
		   File abre=file.getSelectedFile();

		   /**recorremos el archivo, lo leemos para plasmarlo
		   *en el area de texto*/
		   if(abre!=null)
		   {     
		      FileReader archivos=new FileReader(abre);
		      BufferedReader lee=new BufferedReader(archivos);
		      while((aux=lee.readLine())!=null)
		      {
		         texto+= aux+ "\n";
		      }
		         lee.close();
		    }    
		   }
		   catch(IOException ex)
		   {
		     JOptionPane.showMessageDialog(null,ex+"" + "\nNo se ha encontrado el archivo","ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
		    }
		  return texto;//El texto se almacena en el JTextArea
		}
	
	public boolean respetaFormato(String arch) {
		boolean cumple=true;
		
		//Si el tamanio del archivo no es el correcto entonces el formato no se respeta
		if(arch.length()!=162)
			cumple = false;
		
		int i = 0;
		while(i<arch.length() && cumple) {
			String linea="";
			while(arch.charAt(i)!='\n') {
			char c = arch.charAt(i);
			linea+=c;
			i++;
			}
			cumple = verificarLinea(linea);
			i++;
		}
	
		return cumple;
	}
	public boolean cargarEstadoInicial() {
		boolean cargarCorrectamente=true;
		String arch=abrirArchivo();
		//Si respeta el formato entonces inicializo una matriz de enteros
		if(respetaFormato(arch)) {
			String cadenaNueva = arch.replaceAll("\\s","");
			int n=0;
			for(int fil = 0 ; fil < cantFilas ; fil++) 
				for(int col = 0 ; col < cantFilas ; col++) {
					int x = cadenaNueva.charAt(n);
					tableroEnteros[fil][col] = x-48;
					n++;
				}
		
		//Ahora cargo los numeros de la matriz de enteros como valores del tablero principal
		for(int i = 0; i<cantFilas; i++)
			for(int j = 0; j<cantFilas; j++) {
				tablero[i][j].setValor(tableroEnteros[i][j]);
			}
		}
		else {
			cargarCorrectamente=false;
		}
		return cargarCorrectamente;
	}
	
	public void eliminarCeldasIniciales() {
	   for(int i=0; i<cantFilas; i++)
		for(int j=0 ; j<cantFilas ; j++) {
			Random rand= new Random();
			int value= rand.nextInt(2);
			//De acuerdo a value decido si asignar un valor o no
			if(value == 0) {
				tablero[i][j].setValor(0);
			}
			else {
				tablero[i][j].setEstado(false);
			}
		}
	}
	
	private boolean verificarLinea(String lin) {
		boolean lineacumple = true;
		int prim=0;
		if(lin.length()!=17) {
			lineacumple = false;
		}
		prim=lin.charAt(0);
		
		if(prim < 49 || prim > 57) {
			lineacumple = false;
		}
		
		if(lineacumple) {
			int i = 0;
			boolean sigue = true;
			while(sigue && i < lin.length()) {
				if(lin.charAt(i)!=lin.charAt(lin.length()-1)) {
					int n = lin.charAt(i);
					if(n < 49 || n > 57 || lin.charAt(i+1)!=' ' || lin.charAt(i)==' ') {
						lineacumple = false;
					}
				}
				else if(lin.charAt(i) == lin.charAt(lin.length()-1)) {
					int n = lin.charAt(i);
					if(n < 49 || n > 57 || lin.charAt(i)==' ') {
						lineacumple = false;
					}
					sigue = false;
				}
				i+=2;
			}
		}
		return lineacumple;
	}
	public int[][] obtenerTableroEnteros(){
		return tableroEnteros;
	}
	public boolean verificarSolucionSudoku(){
		
		boolean sudokuCumple = true;
		
		//Verifico filas
		sudokuCumple=verificarFilas();
		
		//Verifico columnas
		sudokuCumple=verificarColumnas(sudokuCumple);
	
		//Verifico Paneles
		//Panel1
	     		sudokuCumple=verificarPanel(0,0,3,3,sudokuCumple);
	    //Panel2
		    	sudokuCumple=verificarPanel(0,3,3,6,sudokuCumple);
		//Panel3
	    		sudokuCumple=verificarPanel(0,6,3,9,sudokuCumple);
		//Panel4
    			sudokuCumple=verificarPanel(3,0,6,3,sudokuCumple);
    	//Panel5
	    		sudokuCumple=verificarPanel(3,3,6,6,sudokuCumple);
	    //Panel6
		    	sudokuCumple=verificarPanel(3,6,6,9,sudokuCumple);
		//Panel7
		    	sudokuCumple=verificarPanel(6,0,9,3,sudokuCumple);
		//Panel8
		    	sudokuCumple=verificarPanel(6,3,9,6,sudokuCumple);
		//Panel9
		    	sudokuCumple=verificarPanel(6,6,9,9,sudokuCumple);

		return sudokuCumple;
	}
	private boolean verificarFilas() {
		boolean cumple=true;
		
		for(int i=0; i<9; i++)
			if(!verificarFila(i)) {
				cumple=false;
			}
		return cumple;
	}
	//Verifica los repetidos fila por fila
	private boolean verificarFila(int i) {
		boolean filacumple=true;
		//Un contador para cada numero, del 1 al 9
	      int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0,
	            c6 = 0, c7 = 0, c8 = 0, c9 = 0;
	      //Contadores para los primeros elementos en el caso de que se repitan
	      int fi1=0,co1=0,fi2=0,co2=0,fi3=0,co3=0,fi4=0,co4=0,fi5=0,
	    		  co5=0,fi6=0,co6=0,fi7=0,co7=0,fi8=0,co8=0,fi9=0,co9=0;
	      
			for(int j=0; j<9; j++) {
				 switch (tablero[i][j].getValor())
		         {
		         case 1:
		            c1++;
		            if(c1==1) {
		            	fi1=i;
		            	co1=j;
		            }
		            else if(c1>1) {
		            	tablero[fi1][co1].getEntidadGrafica().actualizarImagenErrores(0);
		            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(0);
		            }
		            	break;
		         case 2:
		        	  c2++;
			            if(c2==1) {
			            	fi2=i;
			            	co2=j;
			            }
			            else if(c2>1) {
			            	tablero[fi2][co2].getEntidadGrafica().actualizarImagenErrores(1);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(1);
			            }
			            	break;
		         case 3:
		        	  c3++;
			            if(c3==1) {
			            	fi3=i;
			            	co3=j;
			            }
			            else if(c3>1) {
			            	tablero[fi3][co3].getEntidadGrafica().actualizarImagenErrores(2);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(2);
			            }
			            	break;
		         case 4:
		        	  c4++;
			            if(c4==1) {
			            	fi4=i;
			            	co4=j;
			            }
			            else if(c4>1) {
			            	tablero[fi4][co4].getEntidadGrafica().actualizarImagenErrores(3);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(3);
			            }
			            	break;
		         case 5:
		        	  c5++;
			            if(c5==1) {
			            	fi5=i;
			            	co5=j;
			            }
			            else if(c5>1) {
			            	tablero[fi5][co5].getEntidadGrafica().actualizarImagenErrores(4);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(4);
			            }
			            	break;
		         case 6:
		        	  c6++;
			            if(c6==1) {
			            	fi6=i;
			            	co6=j;
			            }
			            else if(c6>1) {
			            	tablero[fi6][co6].getEntidadGrafica().actualizarImagenErrores(5);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(5);
			            }
			            	break;
		         case 7:
		        	  c7++;
			            if(c7==1) {
			            	fi7=i;
			            	co7=j;
			            }
			            else if(c7>1) {
			            	tablero[fi7][co7].getEntidadGrafica().actualizarImagenErrores(6);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(6);
			            }
			            	break;
		         case 8:
		        	  c8++;
					if(c8==1) {
			            	fi8=i;
			            	co8=j;
			            }
			            else if(c8>1) {
			            	tablero[fi8][co8].getEntidadGrafica().actualizarImagenErrores(7);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(7);
			            }
			            	break;
		         case 9:
		        	  c9++;
			            if(c9==1) {
			            	fi9=i;
			            	co9=j;
			            }
			            else if(c9>1) {
			            	tablero[fi9][co9].getEntidadGrafica().actualizarImagenErrores(8);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(8);
			            }
			            	break;
		         default:
		            filacumple=false;//Si encontrasemos un numero fuera del rango 1-9, Sudoku es erroneo
		         
			}
		   }
		  /*
	       * Ya se ha recorrido la fila y hemos contado según los numeros encontrados.
	       * La fila será CORRECTA si TODOS los contadores tienen valor 1, es decir, se ha encontrado
	       * UN UNICO NUMERO para cada posible valor entre 1-9.
	       * Si algun contador tuviera valor 0 o mayor que 1, es que la fila es INCORRECTA, porque
	       * faltan numeros y/o algunos están repetidos.
	       */
		if(filacumple)
	      if (c1 == 1 && c2 == 1 && c3 == 1 && c4 == 1 && c5 == 1 && c6 == 1
	            && c7 == 1 && c8 == 1 && c9 == 1)
	         filacumple=true;
	      else
	         filacumple=false;
		
		return filacumple;
	}
	
	private boolean verificarColumnas(boolean sudok) {
		boolean cumple=sudok;
		for(int i=0; i<9; i++)
			if(!verificarColumna(i)) {
				cumple=false;
			}
		return cumple;
	}
	//Verifica columna por columna los repetidos
	private boolean verificarColumna(int i) {
		boolean cumple=true;
		//Un contador para cada numero, del 1 al 9
	      int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0,
	            c6 = 0, c7 = 0, c8 = 0, c9 = 0;
	      //Contadores para los primeros elementos en el caso de que se repitan
	      int fi1=0,co1=0,fi2=0,co2=0,fi3=0,co3=0,fi4=0,co4=0,fi5=0,
	    		  co5=0,fi6=0,co6=0,fi7=0,co7=0,fi8=0,co8=0,fi9=0,co9=0;
	      
			for(int j=0; j<9; j++) {
				 switch (tablero[j][i].getValor())
		         {
		         case 1:
		            c1++;
		            if(c1==1) {
		            	fi1=i;
		            	co1=j;
		            }
		            else if(c1>1) {
		            	tablero[co1][fi1].getEntidadGrafica().actualizarImagenErrores(0);
		            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(0);
		            }
		            	break;
		         case 2:
		        	  c2++;
			            if(c2==1) {
			            	fi2=i;
			            	co2=j;
			            }
			            else if(c2>1) {
			            	tablero[co2][fi2].getEntidadGrafica().actualizarImagenErrores(1);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(1);
			            }
			            	break;
		         case 3:
		        	  c3++;
			            if(c3==1) {
			            	fi3=i;
			            	co3=j;
			            }
			            else if(c3>1) {
			            	tablero[co3][fi3].getEntidadGrafica().actualizarImagenErrores(2);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(2);
			            }
			            	break;
		         case 4:
		        	  c4++;
			            if(c4==1) {
			            	fi4=i;
			            	co4=j;
			            }
			            else if(c4>1) {
			            	tablero[co4][fi4].getEntidadGrafica().actualizarImagenErrores(3);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(3);
			            }
			            	break;
		         case 5:
		        	  c5++;
			            if(c5==1) {
			            	fi5=i;
			            	co5=j;
			            }
			            else if(c5>1) {
			            	tablero[co5][fi5].getEntidadGrafica().actualizarImagenErrores(4);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(4);
			            }
			            	break;
		         case 6:
		        	  c6++;
			            if(c6==1) {
			            	fi6=i;
			            	co6=j;
			            }
			            else if(c6>1) {
			            	tablero[co6][fi6].getEntidadGrafica().actualizarImagenErrores(5);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(5);
			            }
			            	break;
		         case 7:
		        	  c7++;
			            if(c7==1) {
			            	fi7=i;
			            	co7=j;
			            }
			            else if(c7>1) {
			            	tablero[co7][fi7].getEntidadGrafica().actualizarImagenErrores(6);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(6);
			            }
			            	break;
		         case 8:
		        	  c8++;
					if(c8==1) {
			            	fi8=i;
			            	co8=j;
			            }
			            else if(c8>1) {
			            	tablero[co8][fi8].getEntidadGrafica().actualizarImagenErrores(7);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(7);
			            }
			            	break;
		         case 9:
		        	  c9++;
			            if(c9==1) {
			            	fi9=i;
			            	co9=j;
			            }
			            else if(c9>1) {
			            	tablero[co9][fi9].getEntidadGrafica().actualizarImagenErrores(8);
			            	tablero[j][i].getEntidadGrafica().actualizarImagenErrores(8);
			            }
			            	break;
		         default:
		            cumple=false;//Si encontrasemos un numero fuera del rango 1-9, Sudoku es erroneo
		         
			}
		   }
		  /*
	       * Ya se ha recorrido la columna y hemos contado según los numeros encontrados.
	       * La columna será CORRECTA si TODOS los contadores tienen valor 1, es decir, se ha encontrado
	       * UN UNICO NUMERO para cada posible valor entre 1-9.
	       * Si algun contador tuviera valor 0 o mayor que 1, es que la columna es INCORRECTA, porque
	       * faltan numeros y/o algunos están repetidos.
	       */
		if(cumple)
	      if (c1 == 1 && c2 == 1 && c3 == 1 && c4 == 1 && c5 == 1 && c6 == 1
	            && c7 == 1 && c8 == 1 && c9 == 1)
	         cumple=true;
	      else
	         cumple=false;
		
		return cumple;
	}
	
	//Verifica que en el panel no hayan elementos repetidos
	private boolean verificarPanel(int filaInicio,int columnaInicial,int filaFinal,int columnaFinal,boolean sudok) {
		boolean cumple=sudok;
		//Un contador para cada numero, del 1 al 9
	      int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0,
	            c6 = 0, c7 = 0, c8 = 0, c9 = 0;
	      //Contadores para los primeros elementos en el caso de que se repitan
	      int fi1=0,co1=0,fi2=0,co2=0,fi3=0,co3=0,fi4=0,co4=0,fi5=0,
	    		  co5=0,fi6=0,co6=0,fi7=0,co7=0,fi8=0,co8=0,fi9=0,co9=0;
	      
		for(int i=filaInicio; i<filaFinal; i++)
			for(int j=columnaInicial; j<columnaFinal; j++) {
				 switch (tablero[i][j].getValor())
		         {
		         case 1:
		            c1++;
		            if(c1==1) {
		            	fi1=i;
		            	co1=j;
		            }
		            else if(c1>1) {
		            	tablero[fi1][co1].getEntidadGrafica().actualizarImagenErrores(0);
		            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(0);
		            }
		            	break;
		         case 2:
		        	  c2++;
			            if(c2==1) {
			            	fi2=i;
			            	co2=j;
			            }
			            else if(c2>1) {
			            	tablero[fi2][co2].getEntidadGrafica().actualizarImagenErrores(1);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(1);
			            }
			            	break;
		         case 3:
		        	  c3++;
			            if(c3==1) {
			            	fi3=i;
			            	co3=j;
			            }
			            else if(c3>1) {
			            	tablero[fi3][co3].getEntidadGrafica().actualizarImagenErrores(2);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(2);
			            }
			            	break;
		         case 4:
		        	  c4++;
			            if(c4==1) {
			            	fi4=i;
			            	co4=j;
			            }
			            else if(c4>1) {
			            	tablero[fi4][co4].getEntidadGrafica().actualizarImagenErrores(3);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(3);
			            }
			            	break;
		         case 5:
		        	  c5++;
			            if(c5==1) {
			            	fi5=i;
			            	co5=j;
			            }
			            else if(c5>1) {
			            	tablero[fi5][co5].getEntidadGrafica().actualizarImagenErrores(4);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(4);
			            }
			            	break;
		         case 6:
		        	  c6++;
			            if(c6==1) {
			            	fi6=i;
			            	co6=j;
			            }
			            else if(c6>1) {
			            	tablero[fi6][co6].getEntidadGrafica().actualizarImagenErrores(5);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(5);
			            }
			            	break;
		         case 7:
		        	  c7++;
			            if(c7==1) {
			            	fi7=i;
			            	co7=j;
			            }
			            else if(c7>1) {
			            	tablero[fi7][co7].getEntidadGrafica().actualizarImagenErrores(6);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(6);
			            }
			            	break;
		         case 8:
		        	  c8++;
					if(c8==1) {
			            	fi8=i;
			            	co8=j;
			            }
			            else if(c8>1) {
			            	tablero[fi8][co8].getEntidadGrafica().actualizarImagenErrores(7);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(7);
			            }
			            	break;
		         case 9:
		        	  c9++;
			            if(c9==1) {
			            	fi9=i;
			            	co9=j;
			            }
			            else if(c9>1) {
			            	tablero[fi9][co9].getEntidadGrafica().actualizarImagenErrores(8);
			            	tablero[i][j].getEntidadGrafica().actualizarImagenErrores(8);
			            }
			            	break;
		         default:
		            cumple=false;//Si encontrasemos un numero fuera del rango 1-9, Sudoku es erroneo
		         }
			}
		  /*
	       * Ya se ha recorrido el panel y hemos contado según los numeros encontrados.
	       * El panel será CORRECTO si TODOS los contadores tienen valor 1, es decir, se ha encontrado
	       * UN UNICO NUMERO para cada posible valor entre 1-9.
	       * Si algun contador tuviera valor 0 o mayor que 1, es que el panel es INCORRECTO, porque
	       * faltan numeros y/o algunos están repetidos.
	       */
		if(cumple)
	      if (c1 == 1 && c2 == 1 && c3 == 1 && c4 == 1 && c5 == 1 && c6 == 1
	            && c7 == 1 && c8 == 1 && c9 == 1)
	         cumple=true;
	      else
	         cumple=false;
	      
		return cumple;	
	}

	public Celda[][] getTablero(){
		return tablero;
	}
	
	public void accionarClick(Celda c) {
		c.actualizarEnClick();
	}
	
	public Celda getCelda(int i,int j) {
		return this.tablero[i][j];
	}
	public int[][] getTableroEnteros(){
		return tableroEnteros;
	}
	public int getCantFilas() {
		return this.cantFilas;
	}
	
}

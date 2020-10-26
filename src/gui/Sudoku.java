package gui;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Celda;
import logica.Juego;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sudoku extends JFrame {

	public JPanel contentPane;
	private Juego juego;
	private JLabel lblNewLabel_1,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1051, 444);
		
		//Paneles
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panelSudoku = new JPanel();
		panelSudoku.setBounds(74, 56, 405, 324);

	
		
		juego = new Juego();
		
		
		panelSudoku.setLayout(new GridLayout(juego.getCantFilas(),juego.getCantFilas()));
		contentPane.add(panelSudoku);
		JLabel lblNewLabel = new JLabel("   SUDOKU");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel.setBounds(211, 24, 108, 21);
		contentPane.add(lblNewLabel);
		panelSudoku.setBackground(Color.WHITE);
		panelSudoku.setEnabled(false);
		panelSudoku.setVisible(false);
		
		//Botones
		//Creo el boton de Verificar el tablero
		
		JButton btnNewButton = new JButton("Verificar tablero");
		btnNewButton.setEnabled(false);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarMatriz();
				if(!juego.verificarSolucionSudoku()) {
					repaint();
					if(hayBlancas())
						JOptionPane.showMessageDialog(null, "Porfavor completar las celdas vacias!");
					else
						JOptionPane.showMessageDialog(null, "Hay numeros repetidos!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ganaste!!! :)");
					System.exit(0);
				}
				
			}
		});
		btnNewButton.setBounds(489, 149, 146, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_10 = new JLabel("CRONOMETRO");
		lblNewLabel_10.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel_10.setBounds(739, 145, 158, 21);
		contentPane.add(lblNewLabel_10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(643, 177, 349, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Sudoku.class.getResource("/sprites/Cero_temp.png")));
		lblNewLabel_1.setBounds(49, 68, 23, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Sudoku.class.getResource("/sprites/Cero_temp.png")));
		lblNewLabel_2.setBounds(153, 68, 23, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Sudoku.class.getResource("/sprites/Cero_temp.png")));
		lblNewLabel_3.setBounds(70, 68, 23, 36);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Sudoku.class.getResource("/sprites/Cero_temp.png")));
		lblNewLabel_4.setBounds(175, 68, 23, 36);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(Sudoku.class.getResource("/sprites/Cero_temp.png")));
		lblNewLabel_5.setBounds(252, 68, 23, 36);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon(Sudoku.class.getResource("/sprites/Cero_temp.png")));
		lblNewLabel_6.setBounds(273, 68, 23, 36);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("HORAS");
		lblNewLabel_7.setForeground(Color.GREEN);
		lblNewLabel_7.setBounds(49, 40, 60, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("MINUTOS");
		lblNewLabel_8.setForeground(Color.GREEN);
		lblNewLabel_8.setBounds(153, 40, 54, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("SEGUNDOS");
		lblNewLabel_9.setForeground(Color.GREEN);
		lblNewLabel_9.setBounds(252, 40, 54, 14);
		panel.add(lblNewLabel_9);
		Relojito rel=new Relojito(lblNewLabel_6,lblNewLabel_5,lblNewLabel_4,lblNewLabel_2,lblNewLabel_3,lblNewLabel_1);
		
		//Creo el boton de Seleccion del archivo
				JButton btnNewButton_1 = new JButton("Seleccionar Archivo");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(juego.cargarEstadoInicial()) {
						actualizarGrafica();
						juego.eliminarCeldasIniciales();
						btnNewButton.setEnabled(true);
						btnNewButton.setVisible(true);
						panelSudoku.setEnabled(true);
						panelSudoku.setVisible(true);
						rel.iniciar();
						}
						else {
							JOptionPane.showMessageDialog(null, "El archivo no respeta el formato valido");
						}
					}
				});
			
				btnNewButton_1.setBounds(489, 91, 158, 23);
				contentPane.add(btnNewButton_1);
		

		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				Celda c = juego.getCelda(i,j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				panelSudoku.add(label);
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});

				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						juego.accionarClick(c);
						actualizarMatriz();
						reDimensionar(label,grafico);
					}
				});
			}
		}

	}
	//Actualiza la grafica al insertar el archivo
	private void actualizarGrafica() {
		Celda[][] tab = juego.getTablero();
		int [][] tableroEnteros = juego.getTableroEnteros();
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++) {
				tab[i][j].setValor(tableroEnteros[i][j]);
				contentPane.repaint();
			}
	}
	//Actualiza la grafica al usar el boton verificarTablero
	private void actualizarMatriz() {
		Celda[][] tab = juego.getTablero();
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++) {
				tab[i][j].getEntidadGrafica().actualizar(tab[i][j].getValor()-1);
				contentPane.repaint();
			}
	}
	private boolean hayBlancas() {
		boolean blancas=false;
		Celda[][] tab = juego.getTablero();
		int i=0,j=0;
		while(!blancas && i<9) {
			while(!blancas && j<9) {
				if(tab[i][j].getValor()==0)
					blancas=true;
				else
					j++;
			}
			i++;
		}
		return blancas;
	}

	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}


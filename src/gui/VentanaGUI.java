/**
 **  @author Juan Manuel Carrera García
 **/
 
package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logica.Album;
import logica.Artista;
import logica.GestorBD;

public class VentanaGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	// ------------------ PANEL GLOBAL ----------------
	private JPanel panel;
	
	// ------------------ PANEL SUPERIOR ----------------
	private JPanel panelSuperior;
	private JTextField buscador;
	private JButton buscar;
	
	// ----------------- PANEL RADIO BUTTONS ------------
	private JPanel panelButtons;
	private JRadioButton artistaBut;
	private JRadioButton albumBut;
	private ButtonGroup botonesRadio;
		
	// ------------------ PANEL CENTRAL SUPERIOR ----------------
	private DefaultListModel<Object> modeloLista;
	private JList<Object> lista;
	
	// ------------------ PANEL CENTRAL INFERIOR ----------------
	private JTextPane informacion;
	private JScrollPane barraInformacion;
	
	
	
	/**
	 * Arrancamos la ventana gráfica
	 */
	public VentanaGUI() {
		super("Buscador");
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.initVentana();
	}
	
	/**
	 * Definimos todos los paneles de la ventana gráfica, panel superior(Buscador), el panel de los botones 
	 * el panel de la información de la búsqueda y el panel de la información de los artistas
	 */
	private void initVentana() {
		GridBagConstraints gb = new GridBagConstraints();
		this.panel = new JPanel(new GridBagLayout());
		
		// ------------------ PANEL SUPERIOR ----------------
		this.panelSuperior = new JPanel(new FlowLayout());
		
		this.panelSuperior.add(new JLabel("Buscar: "));
		
		this.buscador = new JTextField(20);
		this.panelSuperior.add(this.buscador);
		
		this.buscar = new JButton("Buscar");
		this.buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informacion.setText("");
				if (artistaBut.isSelected())
					cargaInformacionArtista();
				else 
					cargaInformacionAlbum();
				
			}
		});
		
		this.panelSuperior.add(this.buscar);
		
		gb.gridx = 0; 
		gb.gridy = 0; 
		gb.gridwidth = 1; 
		gb.gridheight = 1;
		gb.weightx = 1.0;
		gb.fill = GridBagConstraints.BOTH;
		this.panel.add(panelSuperior, gb);
		
		
		
		// ----------------- PANEL RADIO BUTTONS ------------
		this.panelButtons = new JPanel(new GridLayout(1, 1, 125, 0));
		
		this.albumBut = new JRadioButton("Album"); 
		this.artistaBut = new JRadioButton("Artista");
		this.artistaBut.setSelected(true);
		
		this.botonesRadio = new ButtonGroup();
		this.botonesRadio.add(this.artistaBut);
		this.botonesRadio.add(this.albumBut);
		
		this.panelButtons.add(this.artistaBut);
		this.panelButtons.add(this.albumBut);
			
		gb.gridx = 0; 
		gb.gridy = 1; 
		gb.gridwidth = 1; 
		gb.gridheight = 1;
		gb.fill = GridBagConstraints.NONE;
		gb.insets = new Insets (10, 0, 0, 0);
		this.panel.add(this.panelButtons, gb);
		
		
		// ----------------- INFORMACI�N DE LA BÚSQUEDA ------------
		this.lista = new JList<Object>();
		this.modeloLista = new DefaultListModel<Object>();	
		this.lista.setModel(this.modeloLista);
		this.lista.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				actualizaInfo();
		    }
		});
				
			
		gb.gridx = 0; 
		gb.gridy = 2; 
		gb.gridwidth = 1; 
		gb.gridheight = 2;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.insets = new Insets (10, 5, 0, 5);
		JScrollPane barraSeleccion = new JScrollPane(this.lista);
		this.panel.add(barraSeleccion, gb);
		
		// ----------------- INFORMACIÓN DE LA SELECCIÓN ------------
		this.informacion = new JTextPane();
		this.informacion.setEditable(false);
		this.informacion.setContentType("text/html");
		
		
		gb.gridx = 0; 
		gb.gridy = 4; 
		gb.gridwidth = 1; 
		gb.gridheight = 3;
		gb.weighty = 1.0;
		gb.fill = GridBagConstraints.BOTH;
		gb.insets = new Insets (10, 5, 8, 5);
		
		this.barraInformacion = new JScrollPane(this.informacion);
		this.panel.add(barraInformacion, gb);
			
		this.getContentPane().add(this.panel);
		
		EventQueue.invokeLater(new Runnable(){
        	public void run() {
        		setVisible(true);
        	}
        });			
	}
	
	
	/**
	 * Metodo que carga la información del artista en un lista
	 * Creamos una lista genérica y en el momento de cargar la información de cada artista
	 * borramos todos los elementos de la lista  
	 */
	private void cargaInformacionArtista() {
		List<Artista> artistas = GestorBD.listaArtistas(buscador.getText());
	
		this.modeloLista.removeAllElements();
		for (int i = 0; i < artistas.size(); i++)
			this.modeloLista.addElement(artistas.get(i));
	
	
	}
	
	/**
	 * Metodo que carga la información de un álbum en un lista
	 * Creamos una lista genérica y en el momento de cargar la información de cada albúm
	 * borramos todos los elementos de la lista 
	 */
	private void cargaInformacionAlbum() {
		List<Album> albumes = GestorBD.listaAlbumes(buscador.getText());
		
		this.modeloLista.removeAllElements();
		for (int i = 0; i < albumes.size(); i++)
			this.modeloLista.addElement(albumes.get(i));
	}
	
	private void actualizaInfo() {
		Object aux = lista.getSelectedValue();
		if (aux instanceof Artista)
			informacion.setText(GestorBD.infoArtista(((Artista) aux).getId()));
		else if (aux instanceof Album)
			informacion.setText(GestorBD.infoAlbum(((Album) aux).getId()));
		
		// Para que el ScrollPane se quede arriba
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	barraInformacion.getViewport().setViewPosition(new Point(0, 0));
			}
		});
	}
	
	
}

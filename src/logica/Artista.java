/**
 **  @author Juan Manuel Carrera García
 **/

 package logica;


/**
 * Clase que contiene toda la información relacionada con un Artista
 *
 */
public class Artista {
	private String id;
	private String nombre;
	
	/**
	 * Constructura que inicializa los campos de id y nombre de un Artista
	 * @param ide
	 * @param tit
	 */
	public Artista(String ide, String nomb) {
		this.id = ide;
		this.nombre = nomb;
	}
	
	/**
	 * @return un id del artista
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * metodo que configura el id de un artista
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return el nombre de un artista
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * metodo que configura el nombre de un artista
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public String toString() {
		return this.nombre;
	}
}

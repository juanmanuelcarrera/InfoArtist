/**
 **  @author Juan Manuel Carrera García
 **/

 package logica;


/**
 * Clase que contiene toda la información relacionada con un Album
 *
 */
public class Album {
	private String id;
	private String titulo;
	
	/**
	 * Constructura que inicializa los campos de id y tiutlo
	 * @param ide
	 * @param tit
	 */
	public Album(String ide, String tit) {
		this.id = ide;
		this.titulo = tit;
	}
	
	/**
	 * @return un id del album
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * metodo que configura el id de un album
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return el titulo de un album
	 */
	public String getTitulo() {
		return this.titulo;
	}
	
	/**
	 * metodo que configura el titulo de un album
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String toString() {
		return this.titulo;
	}
}

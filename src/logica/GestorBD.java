/**
 **  @author Juan Manuel Carrera García
 **/

 package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Element;

import net.xqj.exist.ExistXQDataSource;

/**
 * Clase que contiene todo lo relacionado con la conexion a la base de datos
 *
 */
public class GestorBD {
	private static final String user = "admin";
	private static final String password = "jmcg6892";
	private static final String port = "8082";
	private static XQDataSource xqs;
	
	/**
	 * se encarga de la conexion a la base de datos en caso de error se lanza una excepcion 
	 * que es capturada
	 */
	public static void conexion() {
		try {
			xqs = new ExistXQDataSource();
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", port);
		} catch (XQException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga toda la informacion de los albumnes
	 * @param cadenaBusqueda
	 * @return una lista con todo el listado de los albumnes
	 */
	public static List<Album> listaAlbumes(String cadenaBusqueda) {
		List<Album> albumes = new ArrayList<Album>();
		InputStream is = null;
		XQConnection con = null;
		XQPreparedExpression exp = null;
		XQResultSequence rs = null;
		
		try {
			is = new FileInputStream("listaAlbumes.xquery");
			con = xqs.getConnection(user, password);
			exp = con.prepareExpression(is);
			exp.bindString(new QName("cadenaBusqueda"), cadenaBusqueda, null);
			rs = exp.executeQuery();
			
			while (rs.next()) {
				Object album = rs.getObject();
				albumes.add(new Album(((Element)album).getAttribute("id"), ((Element)album).getAttribute("titulo")));
			}
			
		} catch (FileNotFoundException | XQException e) {
			e.printStackTrace();
		}	
		finally {
			try {
				if (con != null)
					con.close();
				if (is != null)
					is.close();
				if (exp != null) 
					exp.close();
				if (rs != null) 
					rs.close();
			} catch (XQException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return albumes;
	}
	
	/**
	 * Carga toda la informacion de los artistas
	 * @param cadenaBusqueda
	 * @return una lista con todo el listado de los artistas
	 */
	public static List<Artista> listaArtistas(String cadenaBusqueda) {
		List<Artista> artistas = new ArrayList<Artista>();
		InputStream is = null;
		XQConnection con = null;
		XQPreparedExpression exp = null;
		XQResultSequence rs = null;
		
		try {
			is = new FileInputStream("listaArtistas.xquery");
			con = xqs.getConnection(user, password);
			exp = con.prepareExpression(is);
			exp.bindString(new QName("cadenaBusqueda"), cadenaBusqueda, null);
			rs = exp.executeQuery();
			
			while (rs.next()) {
				Object artista = rs.getObject();
				artistas.add(new Artista(((Element)artista).getAttribute("id"), ((Element)artista).getAttribute("nombre")));
			}
		} catch (FileNotFoundException | XQException e) {
			e.printStackTrace();
		}	
		finally {
			try {
				if (con != null)
					con.close();
				if (is != null)
					is.close();
				if (exp != null) 
					exp.close();
				if (rs != null) 
					rs.close();
			} catch (XQException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return artistas;
	}
	/**
	 * Carga la informacion del artista
	 * @param idArtista
	 * @return a String con toda la informacion del artista
	 */
	public static String infoArtista(String idArtista) {
		String info = "";
		InputStream is = null;
		XQConnection con = null;
		XQPreparedExpression exp = null;
		XQResultSequence rs = null;
		
		try {
			is = new FileInputStream("infoArtista.xquery");
			con = xqs.getConnection(user, password);
			exp = con.prepareExpression(is);
			exp.bindString(new QName("idArtista"), idArtista, null);
			rs = exp.executeQuery();
			
			if (rs.next())
				info = rs.getItemAsString(null);
		} catch (FileNotFoundException | XQException e) {
			e.printStackTrace();
		}	
		finally {
			try {
				if (con != null)
					con.close();
				if (is != null)
					is.close();
				if (exp != null) 
					exp.close();
				if (rs != null) 
					rs.close();
			} catch (XQException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return info;
	}
	
	/**
	 * Carga la informacion del album
	 * @param idAlbum
	 * @return a String con toda la informacion del album
	 */
	public static String infoAlbum(String idAlbum) {
		String info = "";
		InputStream is = null;
		XQConnection con = null;
		XQPreparedExpression exp = null;
		XQResultSequence rs = null;
		
		try {
			is = new FileInputStream("infoAlbum.xquery");
			con = xqs.getConnection(user, password);
			exp = con.prepareExpression(is);
			exp.bindString(new QName("idAlbum"), idAlbum, null);
			rs = exp.executeQuery();
			
			if (rs.next())
				info = rs.getItemAsString(null);
		} catch (FileNotFoundException | XQException e) {
			e.printStackTrace();
		}	
		finally {
			try {
				if (con != null)
					con.close();
				if (is != null)
					is.close();
				if (exp != null) 
					exp.close();
				if (rs != null) 
					rs.close();
			} catch (XQException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return info;
	}
}

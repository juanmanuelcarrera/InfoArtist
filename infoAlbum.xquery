declare variable $idAlbum as xs:string external;

let $album :=  doc("basededatos.xml")//album[@id=$idAlbum]
return <html>
            <head>        
            </head>
            <body>
	            <h1>{data($album/tituloalbum)}</h1>
	            
	            <img src="{$album/url_imagen}"/>
		
		
		        <h2>Descripción</h2>
	            <p>{data($album/descripcionalbum)}</p> 
                
                
		        <h2>Sello discográfico</h2>
		        <p>{data($album/sello_discografico)}</p>
		
		        <h2>Año</h2>
		        <p>{data($album/anio_publicacion)}</p>
		
		        <h2>Autor</h2>
		        <p>{string-join(for $artista in doc("basededatos.xml")//artista[@id=$album//@id]
		        	return $artista/nombre_artistico, ", ")}</p>
		        
		        <h2>Lista de canciones</h2>
		        <table>
		            {for $cancion in doc("basededatos.xml")//cancion
                    where $cancion//@id=$album//@id
                    return <tr>
                                <td>{data($cancion/titulocancion)} </td>
                                <td> {string-join(for $artista in doc("basededatos.xml")//artista[@id=$cancion//@id and @id!=$album/artistasid//@id]
                                	return $artista/nombre_artistico, ", ")} </td>
                                <td> {data($cancion/duracion)} </td>
                            </tr>
                    }	             
		        </table>
	        </body>
        </html>






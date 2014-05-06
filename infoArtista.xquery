declare variable $idArtista as xs:string external;

let $artista := doc("basededatos.xml")//artista[@id=$idArtista]
return <html>
            <head></head>
            <body>
           		<h1>{data($artista/nombre_artistico)}</h1>
    	
				
		        <h2>Descripción</h2>
	            <p>{data($artista/descripcionartista)}</p>
			
				<h2>Biografía</h2>
	            <p>{data($artista/biografia)}</p>
		
		        <h2>Discografía</h2>
		        <table>
		            {for $album in doc("basededatos.xml")//album
                    where $album//@id=$artista//@id
                    order by $album/anio_publicacion
                    return <tr>
                    			<td>{data($album/anio_publicacion)} </td>
                                <td> {data($album/tituloalbum)}</td>
                            </tr>
                    }
                             
		        </table>
		
            	
           		
	        </body>
        </html>

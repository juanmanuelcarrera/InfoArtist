declare variable $cadenaBusqueda as xs:string external;

for $album in doc("basededatos.xml")//album
where $album[contains(tituloalbum, $cadenaBusqueda)]
return <resultado id="{$album/@id}" titulo="{$album/tituloalbum}"/>
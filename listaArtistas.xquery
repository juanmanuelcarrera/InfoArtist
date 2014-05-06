declare variable $cadenaBusqueda as xs:string external;

for $artista in doc("basededatos.xml")//artista
where $artista[contains(nombre_artistico, $cadenaBusqueda)]
return <resultado id="{$artista/@id}" nombre="{$artista/nombre_artistico}"/>
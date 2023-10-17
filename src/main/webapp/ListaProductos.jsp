<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MVC</title>

<style type="text/css">

.cabecera{

	font-size:1.2em;
	font-weight:bold;
	color:#FFFFFF;
	background-color: #08088A;
	
}

.filas{

	text-align:center;
	color: #FFFFFF;
	background-color: #5882FA;

}	

table{

	float:left;

}

#contenedorBoton{

	margin-left:800px;

}

</style>

</head>

<body>

<h2>Tabla de Productos</h2>

	<table>
	
	<tr>
	
	<th class="cabecera">Codigo Articulo</th>
	<th class="cabecera">Seccion</th>
	<th class="cabecera">Nombre Articulo</th>
	<th class="cabecera">Fecha</th>
	<th class="cabecera">Precio</th>
	<th class="cabecera">Importado</th>
	<th class="cabecera">Pais de Origen</th>
	<th class="cabecera">Acción</th>
	
	</tr>
	
	<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
	
	<!--  Link para cada producto con su campo clave -->
	
	<c:url var="linkTemp" value="ControladorProductos">
	
		<c:param name="instruccion" value="cargar"></c:param>
		<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
	
	</c:url>
	
	<!-- Link para eliminar cada registro con su campo clave -->
	
	<c:url var="linkTempEliminar" value="ControladorProductos">
	
		<c:param name="instruccion" value="eliminar"></c:param>
		<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
	
	</c:url>
	
	<tr>
	
	<td class="filas">${tempProd.cArt}</td>
	<td class="filas">${tempProd.seccion}</td>
	<td class="filas">${tempProd.nArt}</td>
	<td class="filas">${tempProd.fecha}</td>
	<td class="filas">${tempProd.precio}</td>
	<td class="filas">${tempProd.importado}</td>
	<td class="filas">${tempProd.pOrigen}</td>
	<td class="filas"><a href="${linkTemp}">Actualizar</a>&nbsp;<a href="${linkTempEliminar}">Eliminar</a></td>
	
	</tr>
	
	</c:forEach>
	
	</table>
	
	<div id="contenedorBoton">
	
		<input type="button" value="Insertar Registro" onclick="window.location.href='InsertaProducto.jsp'"/>
	
	</div>

</body>
</html>
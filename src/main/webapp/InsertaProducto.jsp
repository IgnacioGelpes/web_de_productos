<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FormularioMVC</title>
</head>
<body>

<H1 style="text-align: center;">Insertar Registros</H1>

<form name="form1" method="get" action="ControladorProductos">
	<input type="hidden" name="instruccion" value="insertarBBDD">


<table style="width: 400px;">

 <tbody>
 
  <tr>
   <td>C&Oacute;DIGOART&Iacute;CULO</td>
   <td><label for="cArt"></label>
   <input type="text" name="cArt" id="cArt" /></td>
  </tr>
 
  <tr>
   <td>SECCI&Oacute;N</td>
   <td><label for="seccion"></label>
   <input type="text" name="seccion" id="seccion"/></td>
  </tr>
 
  <tr>
   <td>NOMBREART&Iacute;CULO</td>
   <td><label for="nArt"></label>
   <input type="text" name="nArt" id="nArt"/></td>
  </tr>
 
  <tr>
   <td>PRECIO</td>
   <td><label for="precio"></label>
   <input type="text" name="precio" id="precio"/></td>
  </tr>
 
  <tr>
   <td>FECHA</td>
   <td><label for="fecha"></label>
   <input type="text" name="fecha" id="fecha"/></td>
  </tr>
 
  <tr>
   <td>IMPORTADO</td>
   <td><label for="importado"></label>
   <input type="text" name="importado" id="importado"/></td>
  </tr>
 
  <tr>
   <td>PA&Iacute;SDEORIGEN</td>
   <td><label for="pOrigen"></label>
   <input type="text" name="pOrigen" id="pOrigen"/></td>
  </tr>
 
  <tr>
   <td><input type="submit" name="envio" id="envio" value="Enviar"/></td>
   <td><input type="reset" name="borrar" id="borrar" Value="Restablecer" /></td>
  </tr>
  
 </tbody>
 
</table>

</form>

</body>
</html>
package com.chonarts.productos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloProductos modeloProductos;
	
	@Resource(name="jdbc/Productos")
	private DataSource miPool;
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
		modeloProductos=new ModeloProductos(miPool);
		}catch(Exception e) {
			
			throw new ServletException(e);
		}
		
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Leer el parametro que llega del formulario
		
		String elComando=request.getParameter("instruccion");
		
		// Si no se envia el parametro, listar productos
		
		if(elComando==null) elComando="listar";
		
		// Redirigir el flujo de ejecucion al metodo adecuado
		
		switch(elComando) {
		
		case "listar":
			
			obtenerProductos(request, response);
			
			break;
			
		case "insertarBBDD":
			
			agregarProductos(request, response);
			
			break;
			
		case "cargar":
			
			try {
				cargaProductos(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
		case "actualizarBBDD":	
			
			try {
				actualizaProductos(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
		case "eliminar":
			
			try {
				eliminarProducto(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
		default:
			
			obtenerProductos(request, response);
		
		
		}

		obtenerProductos(request, response);
		

		
	}



	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Capturar el codigo Articulo
		
		String CodArticulo=request.getParameter("CArticulo");
		
		// Borrar producto de la BBDD
		
		modeloProductos.eliminarProducto(CodArticulo);
		
		// Volver al listado de productos
		
		obtenerProductos(request, response);
		
		
	}



	private void actualizaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		// Leer los datos que vienen del formulario actualizar
		
		String CodArticulo=request.getParameter("cArt");
		
		String Seccion=request.getParameter("seccion");
		
		String NombreArticulo=request.getParameter("nArt");
		
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		
		Date Fecha=null;
		
		try {
			Fecha=formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String Precio=request.getParameter("precio");
		
		String Importado=request.getParameter("importado");
		
		String PaisOrigen=request.getParameter("pOrigen");
		
		// Crear un objetos del tipo Producto con la info del formulario
		
		Productos productoActualizado=new Productos(CodArticulo, Seccion, NombreArticulo, Precio, Importado, PaisOrigen, Fecha);

		// Actualizar la BBDD con la info del obj Producto
		
		modeloProductos.actualizarProducto(productoActualizado);
		
		// Volver al listado con la info actualizada
		
		obtenerProductos(request, response);
		
	}



	private void cargaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Leer el Codigo Articulo que viene del listado
		
			String codigoArticulo=request.getParameter("CArticulo");
		
		// Enviar Codigo Articulo a modelo
		
			Productos elProducto=modeloProductos.getProducto(codigoArticulo);
		
		// Colocar atributo correspondiente al  C.Articulo
			
			request.setAttribute("ProductoActualizar", elProducto);
		
		// Enviar Producto al formulario de actualizar (jsp)
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/ActualizarProducto.jsp");
			
			dispatcher.forward(request, response);
		
	}



	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		
		// Leer la informacion del producto que viene del formulario
		
		String CodArticulo=request.getParameter("cArt");
		
		String Seccion=request.getParameter("seccion");
		
		String NombreArticulo=request.getParameter("nArt");
		
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		
		Date Fecha=null;
		
		try {
			Fecha=formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String Precio=request.getParameter("precio");
		
		String Importado=request.getParameter("importado");
		
		String PaisOrigen=request.getParameter("pOrigen");
		
		// Crear un objeto del tipo producto
		
		Productos NuevoProducto=new Productos(CodArticulo, Seccion, NombreArticulo, Precio, Importado, PaisOrigen, Fecha);
		
		// Enviar el objeto al modelo y despues insertar el objeto producto en la BBDD
		
		try {
			modeloProductos.agregarElNuevoProducto(NuevoProducto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Volver al listado de productos
		
		obtenerProductos(request, response);
		
	}



	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
		
	//   Obtener la lista de productos desde el modelo
		
		List<Productos> productos;
		
		try {
		
			productos= modeloProductos.getProductos();
			
		//   agregar lista de productos al request
		
			request.setAttribute("LISTAPRODUCTOS", productos);
		
		//   enviar ese request a la pagina JSP
			
			RequestDispatcher miDispatcher= request.getRequestDispatcher("/ListaProductos.jsp");
			
			miDispatcher.forward(request, response);
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}

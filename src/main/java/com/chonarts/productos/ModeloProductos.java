package com.chonarts.productos;

import java.sql.Connection;
import java.util.*;
import java.util.Date;

import javax.sql.*;
import java.sql.*;

public class ModeloProductos {
	
	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos) {
		
		this.origenDatos=origenDatos;
		
	}
	
	public List<Productos> getProductos() throws Exception{
		
		List<Productos> product= new ArrayList<>();
		
		Connection miConexion=null;
		
		Statement miStatement=null;
		
		ResultSet miResultset=null;
		
		//------establecer la conexion--------------
		
		miConexion=origenDatos.getConnection();
		
		//------crear sentencia sql-----------------
		
		String intruccionSql="SELECT * FROM PRODUCTOS";
		
		miStatement=miConexion.createStatement();
		
		//------ejecutar sql------------------------
		
		miResultset=miStatement.executeQuery(intruccionSql);
		
		//------recorrer el resultset obtenido------
		
		while(miResultset.next()) {
			
			String c_art=miResultset.getString("C”DIGOARTÕCULO");
			String secc=miResultset.getString("SECCI”N");
			String n_art=miResultset.getString("NOMBREARTÕCULO");
			String prec=miResultset.getString("PRECIO");
			Date   fech=miResultset.getDate("FECHA");
			String impor=miResultset.getString("IMPORTADO");
			String p_orig=miResultset.getString("PAÕSDEORIGEN");

			Productos tempProd=new Productos(c_art,secc,n_art,prec,impor,p_orig,fech);
			
			product.add(tempProd);
			
		}
		
		return product;
		
	}

	public void agregarElNuevoProducto(Productos NuevoProducto) throws Exception {
		
		Connection miConexion=null;
		
		PreparedStatement miStatement=null;
		
		// Obtener la conexion con la BBDD
		
		try {
			
			miConexion= origenDatos.getConnection();
		
		// Crear intruccion sql que inserte el producto. Crear la consulta preparada
		
		String sql="INSERT INTO PRODUCTOS (C”DIGOARTÕCULO, SECCI”N, NOMBREARTÕCULO, PRECIO, FECHA, IMPORTADO, PAÕSDEORIGEN) "+
		"VALUES(?,?,?,?,?,?,?)";
		
		miStatement=miConexion.prepareStatement(sql);
		
		// Establecer parametros para el producto
		
		miStatement.setString(1, NuevoProducto.getcArt());
		
		miStatement.setString(2, NuevoProducto.getSeccion());
		
		miStatement.setString(3, NuevoProducto.getnArt());
		
		miStatement.setString(4, NuevoProducto.getPrecio());
		
		java.util.Date utilDate=NuevoProducto.getFecha();
		
		java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());
		
		miStatement.setDate(5, fechaConvertida);
		
		miStatement.setString(6, NuevoProducto.getImportado());
		
		miStatement.setString(7, NuevoProducto.getpOrigen());
		
		// Ejecutar la instruccion sql
		
		miStatement.execute();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
			miStatement.close();
			miConexion.close();
		
		}
		
	}

	public Productos getProducto(String codigoArticulo) throws Exception{
		
		Productos elProducto=null;
		
		Connection miConexion=null;
		
		PreparedStatement miStatement=null;
		
		ResultSet miResultset=null;
		
		String cArticulo=codigoArticulo;
		
		try {
		
		// Establecer la conexion con la BBDD
		
		miConexion=origenDatos.getConnection();
		
		// Crear sql que busque el producto
		
		String sql= "SELECT * FROM PRODUCTOS WHERE C”DIGOARTÌCULO=?";
		
		// Crear la consulta preparada
		
		miStatement=miConexion.prepareStatement(sql);
		
		// Establecer los parametros
		
		miStatement.setString(1, cArticulo);
		
		// Ejecutar la consulta
		
		miResultset=miStatement.executeQuery();
		
		// Obtener los datos de respuesta
		
		if(miResultset.next()) {
			
			String c_art=miResultset.getString("C”DIGOARTÕCULO");
			String secc=miResultset.getString("SECCI”N");
			String n_art=miResultset.getString("NOMBREARTÕCULO");
			String prec=miResultset.getString("PRECIO");
			Date   fech=miResultset.getDate("FECHA");
			String impor=miResultset.getString("IMPORTADO");
			String p_orig=miResultset.getString("PAÕSDEORIGEN");

			elProducto=new Productos(c_art,secc,n_art,prec,impor,p_orig,fech);
			
		}else {
			
			throw new Exception("no hemos encontrado el producto con el codigo articulo: " + cArticulo);
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return elProducto;
	}

	public void actualizarProducto(Productos productoActualizado) throws Exception {
		
		Connection miConexion=null;
		
		PreparedStatement miStatement=null;
		
		// Establecer la conexion
		
		try {
		
		miConexion=origenDatos.getConnection();
		
		// Crear sentencia SQL
		
		String sql="UPDATE PRODUCTOS SET SECCI”N=?, NOMBREARTÕCULO=?, PRECIO=?, "+
		"FECHA=?, IMPORTADO=?, PAÕSDEORIGEN=? WHERE C”DIGOARTÕCULO=?";
		
		// Crear la consulta preparada
		
		miStatement=miConexion.prepareStatement(sql);
		
		// Establecer los parametros
		
		miStatement.setString(1, productoActualizado.getSeccion());
		miStatement.setString(2, productoActualizado.getnArt());
		miStatement.setString(3, productoActualizado.getPrecio());
		java.util.Date utilDate=productoActualizado.getFecha();
		
		java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());
		
		miStatement.setDate(4, fechaConvertida);
		miStatement.setString(5, productoActualizado.getImportado());
		miStatement.setString(6, productoActualizado.getpOrigen());
		miStatement.setString(7, productoActualizado.getcArt());
		
		// Ejecutar la instruccion SQL
		
		miStatement.execute();
		}finally {
			
			miStatement.close();
			miConexion.close();
		}
		
	}

	public void eliminarProducto(String codArticulo) throws Exception {
		Connection miConexion=null;
		
		PreparedStatement miStatement=null;
		
		// Establecer la conexion con BBDD
		
		try {
		miConexion=origenDatos.getConnection();
		
		// Crear instruccion sql de eliminacion
		
		String sql="DELETE FROM PRODUCTOS WHERE C”DIGOARTÕCULO=?";
		
		// Preparar la consulta
		
		miStatement=miConexion.prepareStatement(sql);
		
		// Establecer parametros de consulta
		
		miStatement.setString(1, codArticulo);
		
		// Ejecutar sentencia sql
		
		miStatement.execute();
		}finally {
			
			miStatement.close();
			miConexion.close();
		}
		
	}

}

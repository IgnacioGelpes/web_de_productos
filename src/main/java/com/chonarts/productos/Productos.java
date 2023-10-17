package com.chonarts.productos;

import java.util.Date;

public class Productos {
	
	public Productos(String seccion, String nArt, String precio, String importado, String pOrigen, Date fecha) {
		
		this.seccion = seccion;
		this.nArt = nArt;
		this.precio = precio;
		this.importado = importado;
		this.pOrigen = pOrigen;
		this.fecha = fecha;
	}

	public Productos(String cArt, String seccion, String nArt, String precio, String importado, String pOrigen,
			Date fecha) {
		
		this.cArt = cArt;
		this.seccion = seccion;
		this.nArt = nArt;
		this.precio = precio;
		this.importado = importado;
		this.pOrigen = pOrigen;
		this.fecha = fecha;
	}

	
	
	public String getcArt() {
		return cArt;
	}

	public void setcArt(String cArt) {
		this.cArt = cArt;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getnArt() {
		return nArt;
	}

	public void setnArt(String nArt) {
		this.nArt = nArt;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getImportado() {
		return importado;
	}

	public void setImportado(String importado) {
		this.importado = importado;
	}

	public String getpOrigen() {
		return pOrigen;
	}

	public void setpOrigen(String pOrigen) {
		this.pOrigen = pOrigen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	

	@Override
	public String toString() {
		return "Productos [cArt=" + cArt + ", seccion=" + seccion + ", nArt=" + nArt + ", precio=" + precio
				+ ", importado=" + importado + ", pOrigen=" + pOrigen + ", fecha=" + fecha + "]";
	}



	private String cArt, seccion, nArt, precio, importado, pOrigen;
	
	private Date fecha;

}

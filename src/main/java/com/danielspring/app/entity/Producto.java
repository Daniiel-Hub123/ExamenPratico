package com.danielspring.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 7262546995217868203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "precio",scale = 2, nullable = false)
	private double precio;
	
	@Column(name = "cantidad",nullable = false)
	private int cantidad;

	@Column(name = "total")
	private double total;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		double operacion = getCantidad()*getPrecio();
		return operacion;
	}

	public void setTotal(double total) {
		
		this.total = total;
	}
	
	

	
	

}

package com.carrito.compras.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CATALOGO")
public class CatalogoEntity {
	
	
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "marca")
	private String marca;

	@Column(name = "precio")
	private Long precio;

	@Column(name = "stock")
	private Long stock;

	@Column(name = "estado")
	private String estado;

	@Column(name = "descuento")
	private Long descuento;
	
	
	public CatalogoEntity() {
		super();
	}
	
	


	public CatalogoEntity(String nombre, String marca, Long precio, Long stock, String estado, Long descuento) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.stock = stock;
		this.estado = estado;
		this.descuento = descuento;
	}




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public Long getPrecio() {
		return precio;
	}


	public void setPrecio(Long precio) {
		this.precio = precio;
	}


	public Long getStock() {
		return stock;
	}


	public void setStock(Long stock) {
		this.stock = stock;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Long getDescuento() {
		return descuento;
	}


	public void setDescuento(Long descuento) {
		this.descuento = descuento;
	}


	@Override
	public String toString() {
		return "CatalogoEntity [nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock
				+ ", estado=" + estado + ", descuento=" + descuento + "]";
	}

	
	

}

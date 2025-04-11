package com.app.e_commerc.models;

import lombok.Data;;

@Data
public class Product {
	private int id;
	private String description;
	private int quantity;
	//Sobreescribo  los metodos para que dos productos con el mismo id se consideren iguales, aunque los otros campos sean distintos. 
	//Ya que el @Data me lo está comparando con los 3 campos del objeto
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return id == product.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}

package com.app.e_commerc.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.app.e_commerc.exception.DuplicateIdException;
import com.app.e_commerc.exception.ObjectNotExistException;
import com.app.e_commerc.models.Cart;
import com.app.e_commerc.models.Product;

@Service
public class CartService {
	private final Map<String, Cart> carts = new ConcurrentHashMap<>();

	/**
	 * 1.1 Método en el que vamos a crear un carrito * @param id
	 * 
	 * @return
	 */
	public Cart createCart() {
		Cart cart = new Cart();
		carts.put(cart.getId(), cart);
		return cart;
	}

	/*
	 * 
	 * /** Método en el que vamos a mostrar un carrito de la compra con todos sus
	 * datos
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public Cart getCart(String id) throws Exception {
		Cart cart = carts.get(id);
		if (cart != null) {
			cart.updateTimestamp();
			return cart;
		}
		StringBuilder sb=new StringBuilder("El carrito con id: ");
		sb.append(id);
		sb.append(" no existe");
		throw new ObjectNotExistException(sb.toString());
	}

	/**
	 * Método en el que vamos a eliminar un carrito si existe
	 * 
	 * @param id
	 * @return
	 */
	public Cart deleteCart(String id) throws Exception {
		if (!carts.containsKey(id)) {
			StringBuilder sb=new StringBuilder("El carrito con id: ");
			sb.append(id);
			sb.append(" no existe");
			throw new ObjectNotExistException(sb.toString());
		}
		return carts.remove(id);
		// != null;
	}

	/**
	 * Método en el que vamos añadir productos al carrito
	 * 
	 * @param id
	 * @param products
	 * @return
	 */
	
	public Cart addProducts(String id, Set<Product> products) throws Exception {
		Cart cart = carts.get(id);
		if (cart != null) {
			// comprobar si el producto ya existe en el carrito
			boolean exists = products.stream().anyMatch(p -> cart.getProducts().contains(p));
			if (exists) {
				StringBuilder sb=new StringBuilder("El carrito con id: ");
				sb.append(id);
				sb.append(" ya contiene el producto");
				sb.append(products);
				throw new DuplicateIdException(sb.toString());
			}
			cart.getProducts().addAll(products);
			cart.updateTimestamp();
			return cart;
		}
		StringBuilder sb=new StringBuilder("El carrito con id: ");
		sb.append(id);
		sb.append(" no existe");
		throw new ObjectNotExistException(sb.toString());
		
	}

	/**
	 * Método en el que vamos a borrar los carritos inactivos más de 10 min
	 */
	public void cleanInactiveCarts() {
		LocalDateTime now = LocalDateTime.now();
		carts.values().removeIf(cart -> Duration.between(cart.getLastUpdated(), now).toMinutes() >= 10);
	}

	/**
	 * Método para mostrar todos los carritos creados
	 * 
	 * @return
	 */
	public Collection<Cart> getAllCarts() {
		return carts.values();
	}
	
}

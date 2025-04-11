package com.app.e_commerc.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.e_commerc.exception.DuplicateIdException;
import com.app.e_commerc.exception.ObjectNotExistException;
import com.app.e_commerc.models.Cart;
import com.app.e_commerc.models.Product;
import com.app.e_commerc.services.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	private final String MENSAJEERRORSERVIDOR = "Error interno del servidor";

	/**
	 * Método para crear el carrito
	 * @return
	 */
		@PostMapping("/CreateCart")
	public ResponseEntity<?> createCart() {
		try {
			Cart save = cartService.createCart();
			return ResponseEntity.ok(save);
		} catch (ObjectNotExistException e) {
			// 404 con mensaje
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MENSAJEERRORSERVIDOR);
		}
	}

	

	/**
	 * Método para visualizar un carrito determinado.
	 * @param cart
	 * @return
	 */
	@PostMapping("/ViewCartXId/{id}")
	public ResponseEntity<?> getCart(@RequestBody Cart cart) {
		try {
			Cart save = cartService.getCart(cart.getId());
			// 200 OK con el carrito
			return ResponseEntity.ok(save);
		} catch (ObjectNotExistException e) {
			// 404 con mensaje
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MENSAJEERRORSERVIDOR);
		}

	}

	/**
	 * Método para eliminar un carrito
	 * @param id
	 * @return
	 */
	@DeleteMapping("/DeleteCartXId/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable String id) {
		try {
			Cart delete = cartService.deleteCart(id);
			return ResponseEntity.ok(delete);
		} catch (ObjectNotExistException e) {
			// 404 con mensaje

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MENSAJEERRORSERVIDOR);
		}
	}

	/**
	 * Método para añadir un producto al carrito
	 * @param id
	 * @param products
	 * @return
	 */
	@PostMapping("/AddProductsAtCartId/{id}/products")
	public ResponseEntity<?> addProducts(@PathVariable String id, @RequestBody @Valid Set<Product> products) {
		try {
			Cart save= cartService.addProducts(id, products);
			return ResponseEntity.ok(save);
		}
		catch (ObjectNotExistException e) {
			// 404 con mensaje
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); 
		} 
		catch (DuplicateIdException e) {
			// 409 con mensaje
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MENSAJEERRORSERVIDOR);
		}

	}
}

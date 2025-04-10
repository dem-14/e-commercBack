package com.app.e_commerc.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class Cart {
	private String id;
	private Set<Product> products = new HashSet<>();
	private LocalDateTime lastUpdated;

	public Cart() {
		this.id = UUID.randomUUID().toString();
		this.lastUpdated = LocalDateTime.now();
	}

	public void updateTimestamp() {
		this.lastUpdated = LocalDateTime.now();
	}
}

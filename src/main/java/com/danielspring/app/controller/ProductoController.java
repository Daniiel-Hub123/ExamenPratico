package com.danielspring.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielspring.app.entity.Producto;
import com.danielspring.app.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	// Create a Product
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Producto producto){
		
		double valor = producto.getTotal();
		double valord = producto.getTotal();
		
		if(valor>50) {
			
			double desc = valor*0.1;
		
			double subtotald = valord - desc; 
			
			double iva = subtotald * 0.12;
					
			double total = iva - subtotald;
			
			producto.setTotal(total);
			
		
		}
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));		
	}
		
	// Read a Product
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long productoId){
		
		Optional<Producto> oProducto = productoService.findById(productoId);
		
		if(!oProducto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oProducto);
		
	}
		
	// Update a Product
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Producto productoDetails, @PathVariable(value = "id") Long productoId){
		
		Optional<Producto> producto = productoService.findById(productoId);
		if(!producto.isPresent()){
			return ResponseEntity.notFound().build();
		}
		
		//BeanUtils.copyProperties(userDetails, user.get());
		
	
		producto.get().setDescripcion(productoDetails.getDescripcion());
		producto.get().setPrecio(productoDetails.getPrecio());
		producto.get().setCantidad(productoDetails.getCantidad());

		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto.get()));
		
	}
	
	// Delete a Product
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long productoId){
		
		if(!productoService.findById(productoId).isPresent()) {			
			return ResponseEntity.notFound().build();
		}
		
		productoService.deleteById(productoId);
		return ResponseEntity.ok().build();
		
	}
	
	// Read all Products
	@GetMapping
	public List<Producto> readAll(){
		
		List<Producto> productos = StreamSupport
				.stream(productoService.findAll().spliterator(),false)
				.collect(Collectors.toList());
		
		return productos;
		
	}
	
}

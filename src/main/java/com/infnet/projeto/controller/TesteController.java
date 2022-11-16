package com.infnet.projeto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.projeto.enums.Borda;
import com.infnet.projeto.enums.Sabor;
import com.infnet.projeto.model.Pizza;

@RestController
public class TesteController {
	
	@GetMapping("/teste")
	public ResponseEntity<Pizza> teste() {
		
		var pizza = new Pizza();
		pizza.setBorda(Borda.NORMAL);
		pizza.setDescricao("teste");
		pizza.setSabor(Sabor.PRESUNTO);
		pizza.setId(1l);
		
		return ResponseEntity.status(HttpStatus.OK).body(pizza);
		
	}

}

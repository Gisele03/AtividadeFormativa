package com.projetojpa.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTest {
	
	private Produto produto;
	
	@BeforeEach
	void setUp() {
		produto = new Produto(1L, "Notebook", "Dell", 1000.00);
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testId() {
		produto.setId(2L);
		
		assertEquals(2L,produto.getId());
	}
	@Test
	@DisplayName("Testando o getter e setter do campo Nome")
	void testNome() {
		produto.setNome("Computador");
		
		assertEquals("Computador",produto.getNome());
	}
	@Test
	@DisplayName("Testando o getter e setter do campo Descrição")
	void testDescricao() {
		produto.setDescricao("Samsung");
		
		assertEquals("Samsung",produto.getDescricao());
	}
	@Test
	@DisplayName("Testando o getter e setter do campo Preco")
	void testPreco() {
		produto.setPreco(1000.00);
		
		assertEquals(1000.00,produto.getPreco());
	}
	@Test
	@DisplayName("Tentando o construtor com todos os argumentos")
	void testConstructorAll() {
		
		Produto novoProduto = new Produto(3L,"Tablet", "Samsung", 100.00);
		
		assertAll("novoHospede",
				()-> assertEquals(3L, novoProduto.getId()),
				()-> assertEquals("Tablet", novoProduto.getNome()),
				()-> assertEquals("Samsung", novoProduto.getDescricao()),
				()-> assertEquals(100.00, novoProduto.getPreco()));
	}

}

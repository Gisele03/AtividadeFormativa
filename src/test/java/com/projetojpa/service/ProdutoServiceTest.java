package com.projetojpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projetojpa.entities.Produto;
import com.projetojpa.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@BeforeEach
	void setUp() {
		produtoRepository.deleteAll();
	}

	@DisplayName("Testando salvar Produto")
	@Test
	void testSalvarProduto() {
		Produto produto = new Produto(null, "Notebook", "Dell", 1000.00);

		Produto resultado = produtoService.salvarProduto(produto);

		assertNotNull(resultado);
		assertEquals("Notebook", resultado.getNome());
		assertTrue(resultado.getId() >0);
	}

	@DisplayName("Testando listar todos os Produtos")
	@Test
	void testListarTodos() {
		Produto produto1 = new Produto(null, "Notebook", "Dell", 1000.00);
		Produto produto2 = new Produto(null, "Computador", "Samsung", 1000.00);

		produtoService.salvarProduto(produto1);
		produtoService.salvarProduto(produto2);

		List<Produto> resultado = produtoService.listarTodos();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}

	@DisplayName("Testando buscar produto por ID")
	@Test
	void testBuscarPorId() {
		Produto produto = new Produto(null, "Notebook", "Dell", 1000.00);

		Produto salvo = produtoService.salvarProduto(produto);
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());

		assertTrue(resultado.isPresent());
		assertEquals("Notebook", resultado.get().getNome());
	}

	@DisplayName("Testando atualizar produto")
	@Test
	void testAtualizarProduto() {
		Produto produto  = new Produto(null, "Notebook", "Dell", 1000.00);
		
		Produto salvo = produtoService.salvarProduto(produto);
		salvo.setNome("Computador");
		salvo.setDescricao("Apple");
		
		Produto atualizado = produtoService.atualizarProduto(salvo);
		assertNotNull(atualizado);
		assertEquals("Computador", atualizado.getNome());
		assertEquals("Apple", atualizado.getDescricao());
	}
	
	@DisplayName("Testando deletar Produto")
	@Test
	void testDeletarProduto() {
		Produto produto = new Produto(null, "Notebook", "Dell", 1000.00);
		
		Produto salvo = produtoService.salvarProduto(produto);
		produtoService.deletarProduto(salvo.getId());
		
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isEmpty());
	}
}

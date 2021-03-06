package com.automationpractice.tests;

import static com.automationpractice.utils.Utils.capturarScreenshot;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.automationpractice.pages.IndexPage;
import com.codeborne.selenide.Configuration;

class CompraTest {

	IndexPage indexPage = new IndexPage();

	@Test
	@DisplayName("Validar Valor Total no Carrinho de Compra")
	void validarCarrinhoCompraTest() {
		
		// Pré-condições
		indexPage
			.visitar()
			.irParaPaginaDeLogin()
			.realizarLogin("email@email.email", "tester123");
		
		// Ações
		Double valorTotalSomado =
		indexPage
			.visitar()
			.adicionarItemAoCarrinho(0, true)
			.adicionarItemAoCarrinho(1, false)
			.somarValoresNoCarrinho();
		
		// Validações
		Double valorTotalEmTela =
		indexPage
			.buscarValorTotal();
		
		assertEquals(valorTotalSomado, valorTotalEmTela);
	
	}

	@BeforeEach
	public void setUp() {
		Properties prop = new Properties();

		InputStream inputFile = getClass().getClassLoader().getResourceAsStream("config.properties");

		try {
			prop.load(inputFile);
		} catch (Exception ex) {
			System.out.println("Erro ao ler arquivo config.properties. Trace=> " + ex.getMessage());
		}

		Configuration.browser = prop.getProperty("browser");
		Configuration.baseUrl = prop.getProperty("url");
		Configuration.timeout = Long.parseLong(prop.getProperty("timeout"));
		Configuration.startMaximized = true;
		Configuration.headless = false;
	}

	@AfterEach
	public void tearDown() {
		capturarScreenshot(getClass().getName());
	}
}

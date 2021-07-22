package com.automationpractice.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class IndexPage {

	private static final SelenideElement BUTTON_NAV_SING_IN = $("a[title='Log in to your customer account'");
	private static final SelenideElement BUTTON_SING_IN = $("#SubmitLogin");
	private static final SelenideElement BUTTON_CONTINUE_SHOPPING = $("span[title='Continue shopping'");
	private static final SelenideElement BUTTON_PROCEED_TO_CHECKOUT = $("a[title='Proceed to checkout'");

	private static final SelenideElement INPUT_EMAIL_ADDRESS_LOGIN = $("#email");
	private static final SelenideElement INPUT_PASSWORD = $("#passwd");

	private static final SelenideElement TABLE_CART_SUMMARY = $("#cart_summary");

	private static final ElementsCollection ITENS_PRODUCT = $$(
			"div.product-container[itemtype='http://schema.org/Product']");
	private static final ElementsCollection ITENS_CART = $$x("//tbody//tr[contains(@id, 'product_')]");

	public IndexPage visitar() {
		Selenide.open("/index.php");
		return this;
	}

	public IndexPage irParaPaginaDeLogin() {
		BUTTON_NAV_SING_IN.click();
		return this;
	}

	public IndexPage realizarLogin(String email, String senha) {
		INPUT_EMAIL_ADDRESS_LOGIN.setValue(email);
		INPUT_PASSWORD.setValue(senha);

		BUTTON_SING_IN.click();

		return this;
	}

	public IndexPage adicionarItemAoCarrinho(Integer index, Boolean continueShopping) {
		ITENS_PRODUCT.get(index).hover().$("a[title='Add to cart'").click();

		if (continueShopping)
			BUTTON_CONTINUE_SHOPPING.click();
		else
			BUTTON_PROCEED_TO_CHECKOUT.click();

		return this;
	}

	@SuppressWarnings("rawtypes")
	public Double somarValoresNoCarrinho() {
		Double totalValue = 0.0;

		for (Iterator iterator = ITENS_CART.iterator(); iterator.hasNext();) {
			SelenideElement selenideElement = (SelenideElement) iterator.next();

			Double price = Double.parseDouble(selenideElement.$("span.price>span").getText().replace("$", ""));
			Integer cartQuantity = Integer.parseInt(selenideElement.$("input.cart_quantity_input").getValue());

			totalValue = totalValue + (price * cartQuantity);
		}

		Double totalShipping = Double.parseDouble(TABLE_CART_SUMMARY.$("#total_shipping").getText().replace("$", ""));
		Double tax = Double.parseDouble(TABLE_CART_SUMMARY.$("#total_tax").getText().replace("$", ""));

		totalValue = totalValue + totalShipping + tax;

		BigDecimal bd = new BigDecimal(totalValue).setScale(2, RoundingMode.HALF_UP);
		double total = bd.doubleValue();

		return total;
	}

	public Double buscarValorTotal() {
		Double totalValue = Double.parseDouble(TABLE_CART_SUMMARY.$("#total_price").getText().replace("$", ""));
		return totalValue;
	}

}

package com.automationpractice.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.ElementsCollection;

import com.codeborne.selenide.Selenide;

public class IndexPage {

	private static final SelenideElement BUTTON_NAV_SING_OUT = $("a[title='Log me out'");
	private static final SelenideElement BUTTON_NAV_SING_IN = $("a[title='Log in to your customer account'");
	private static final SelenideElement BUTTON_SING_IN = $("#SubmitLogin");
	private static final SelenideElement BUTTON_CONTINUE_SHOPPING = $("span[title='Continue shopping'");
	private static final SelenideElement BUTTON_PROCEED_TO_CHECKOUT = $("a[title='Proceed to checkout'");

	private static final SelenideElement INPUT_EMAIL_ADDRESS_LOGIN = $("#email");
	private static final SelenideElement INPUT_PASSWORD = $("#passwd");

	private static final ElementsCollection ITENS_PRODUCT = $$(
			"div.product-container[itemtype='http://schema.org/Product']");

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

}

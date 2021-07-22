package com.automationpractice.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;

public class IndexPage {

	private static final SelenideElement BUTTON_NAV_SING_OUT = $("a[title='Log me out'");
	private static final SelenideElement BUTTON_NAV_SING_IN = $("a[title='Log in to your customer account'");
	private static final SelenideElement BUTTON_SING_IN = $("#SubmitLogin");

	private static final SelenideElement INPUT_EMAIL_ADDRESS_LOGIN = $("#email");
	private static final SelenideElement INPUT_PASSWORD = $("#passwd");

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

}

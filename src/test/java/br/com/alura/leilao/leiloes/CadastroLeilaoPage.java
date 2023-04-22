package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {
    private static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes";

    private WebDriver browser;

    public CadastroLeilaoPage(WebDriver browser) {
        this.browser = browser;
    }


    public void fechar() {
        this.browser.quit();
    }
}

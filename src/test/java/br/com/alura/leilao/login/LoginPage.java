package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public void preencheFormularioDeLogin(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage submeterFormulario() {
        this.browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);
    }

    public boolean isPaginaDeLogin() {
        return this.browser.getCurrentUrl().equals("http://localhost:8080/login");
    }

    public String getNomeUsuarioLogado() {
        try {
            return this.browser.findElement(By.id("usuario-logado")).getText();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public boolean isPageContains(String texto){
        return this.browser.getPageSource().contains(texto);
    }

    public void navegaParaPaginaDeLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }
}

package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    WebDriver browser = new ChromeDriver();
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILAO = "http://localhost:8080/leiloes";

    @BeforeEach
    public void antes(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        this.browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void depois(){
        this.browser.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        this.browser.findElement(By.id("username")).sendKeys("fulano");
        this.browser.findElement(By.id("password")).sendKeys("pass");
        this.browser.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals(URL_LEILAO, browser.getCurrentUrl());
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosValidos(){
        this.browser.findElement(By.id("username")).sendKeys("invalido");
        this.browser.findElement(By.id("password")).sendKeys("invalido");
        this.browser.findElement(By.id("login-form")).submit();

        Assertions.assertEquals(URL_LOGIN + "?error", browser.getCurrentUrl());
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválido"));
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> browser.findElement(By.id("usuario-logado"))
        );
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        this.browser.navigate().to(URL_LEILAO + "/2");

        Assertions.assertEquals(URL_LOGIN, browser.getCurrentUrl());
        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }
}

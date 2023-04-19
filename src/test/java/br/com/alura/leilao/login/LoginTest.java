package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaDelogin;


    @BeforeEach
    public void antes(){
        this.paginaDelogin = new LoginPage();
    }

    @AfterEach
    public void depois(){
        this.paginaDelogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        this.paginaDelogin.preencheFormularioDeLogin("fulano","pass");
        this.paginaDelogin.submeterFormulario();

        Assertions.assertFalse(paginaDelogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", paginaDelogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosValidos(){
        this.paginaDelogin.preencheFormularioDeLogin("invalido","invalido");
        this.paginaDelogin.submeterFormulario();

        Assertions.assertTrue(paginaDelogin.isPaginaDeLogin());
        Assertions.assertTrue(paginaDelogin.isPageContains("Usuário e senha inválido."));
        Assertions.assertNull(paginaDelogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDelogin.navegaParaPaginaDeLances();

        Assertions.assertTrue(paginaDelogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDelogin.isPageContains("Dados do Leilão"));
    }
}

package br.unb.vvts.arquillian.dao;

import org.junit.Test;

import br.unb.vvts.arquillian.model.Seminario;
import br.unb.vvts.arquillian.util.SeminarioUtil;

public class SeminarioUtilTest {

	@Test(expected = IllegalStateException.class)
	public void caso_nao_seja_informado_tema_retorna_erro() {
		Seminario seminario = new Seminario();
		SeminarioUtil.validarTemaSeminario(seminario);
	}

	@Test(expected = IllegalStateException.class)
	public void caso_tenha_mais_que_60_retorna_erro() {
		Seminario seminario = new Seminario();
		seminario.setTema("12345123451234512345123451234512345123451234512345123451234512345");
		SeminarioUtil.validarTemaSeminario(seminario);
	}
	
	@Test
	public void caso_tenha_menos_que_60_retorna_sucesso(){
		Seminario seminario = new Seminario();
		seminario.setTema("123451234512345123451234512345123451234512345123451234512345");
		SeminarioUtil.validarTemaSeminario(seminario);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void caso_seja_nulo_retorna_erro(){
		Seminario seminario = null;
		SeminarioUtil.validarTemaSeminario(seminario);
	}

}

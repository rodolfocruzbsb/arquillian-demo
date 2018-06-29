package br.unb.vvts.arquillian.util;

import br.unb.vvts.arquillian.model.Seminario;

public class SeminarioUtil {

	public static void validarTemaSeminario(Seminario seminario){
		if(seminario == null ){
			throw new IllegalArgumentException("É necessário informar um Seminário");
		}
		if(seminario.getTema() == null || seminario.getTema().length() > 60){
			throw new IllegalStateException("O seminario deve conter um tema e este deve ter no máximo 60 caracteres.");
		}
	}
}

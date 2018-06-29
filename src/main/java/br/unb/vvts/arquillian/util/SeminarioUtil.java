package br.unb.vvts.arquillian.util;

import br.unb.vvts.arquillian.model.Seminario;

public class SeminarioUtil {

	public static void validarTemaSeminario(Seminario seminario){
		if(seminario == null ){
			throw new IllegalArgumentException("� necess�rio informar um Semin�rio");
		}
		if(seminario.getTema() == null || seminario.getTema().length() > 60){
			throw new IllegalStateException("O seminario deve conter um tema e este deve ter no m�ximo 60 caracteres.");
		}
	}
}

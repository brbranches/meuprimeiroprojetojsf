package br.com.cursojsf;

import java.util.List;

import javax.persistence.Persistence;

import org.junit.Test;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;

public class TesteJPA {

	public static void main(String[] args) {

		Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");

	}

	@Test
	public void testeListar() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> lista = daoGeneric.listar(Pessoa.class);
		for (Pessoa pessoa : lista) {
			System.out.println(pessoa);
			System.out.println("");
		}

	}

}

package br.unb.vvts.arquillian.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.unb.vvts.arquillian.model.OutraClasseQualquer;
import br.unb.vvts.arquillian.model.Seminario;
import br.unb.vvts.arquillian.util.SeminarioUtil;

@RunWith(Arquillian.class)
public class SeminarioDaoTest {

	@EJB
	SeminarioDao seminarioDao;
	
	@Deployment
	public static Archive<?> criarArquivoTeste() {
		Archive<?> arquivoTeste = ShrinkWrap.create(WebArchive.class, "aplicacao-arquillian-demo.war")
				// Adicionando o pacote inteiro da classe SeminarioDao, ou seja
				// incluí todas as outras classes deste pacote
				.addPackage(SeminarioDao.class.getPackage())
				// Adicionando apenas a classe Seminario, e não o pacote inteiro
				// como na linha anterior
				.addClasses(Seminario.class, OutraClasseQualquer.class, SeminarioUtil.class)
				// Adicionando o arquivo persistence.xml para conexão JPA
				.addAsResource("META-INF/persistence.xml")
				// Adicionando o beans.xml para ativação do CDI
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return arquivoTeste;
	}

	@Test
	@InSequence(1)
	public void testeSalvarSeminario() {
		Seminario p1 = new Seminario();
		p1.setData(new Date());
		p1.setTema("Ferramenta ARQUILLIAN");
		seminarioDao.salvar(p1);

		Seminario p2 = new Seminario();
		p2.setData(new Date());
		p2.setTema("Prism Model Checker");
		seminarioDao.salvar(p2);

	}

	@Test
	@InSequence(2)
	public void testeAtualizarSeminarioP1() {
		Seminario p1 = seminarioDao.buscar(1);
		LocalDate localDate = LocalDate.now().plusDays(7);
		Date novaData = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		p1.setData(novaData);
		seminarioDao.atualizar(p1);

		assertEquals("Ferramenta ARQUILLIAN", p1.getTema());
		assertEquals(novaData, p1.getData());

	}

	@Test
	@InSequence(3)
	public void testeBuscarSeminarioP2() {
		Seminario p2 = seminarioDao.buscar(2);

		LocalDate localDate = LocalDate.now();
		Date novaData = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		assertEquals("Prism Model Checker", p2.getTema());
		assertEquals(novaData, p2.getData());
	}

	@Test
	@InSequence(4)
	public void testeBuscarTodos() {
		List<Seminario> seminarios = seminarioDao.buscarTodos();
		assertEquals(2, seminarios.size());
	}
}
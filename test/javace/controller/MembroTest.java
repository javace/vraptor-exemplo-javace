package javace.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.javace.controler.MembrosController;
import br.com.caelum.vraptor.javace.dao.MembrosDAO;
import br.com.caelum.vraptor.javace.model.Membro;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class MembroTest {

	@Mock
	private Result result;
	@Mock
	private MembrosDAO membrosDAO;
	
	private MembrosController controller;
	
	private Validator validator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		validator = new MockValidator();
		controller = new MembrosController(result, membrosDAO, validator);
	}
	
	@Test
	public void deveriaIncluriMembro(){
		Membro membro = criarMembro();
		Mockito.when(membrosDAO.salvar(Mockito.any(Membro.class))).thenReturn(membro);
		controller.novo(membro);
		Assert.assertTrue("deveria ser verdadeiro", result.included().containsKey("membro"));
	}
	
	@Test
	public void deveriaExcluirMembro(){
		Membro membro = criarMembro();
		controller.deletar(membro);
		Mockito.verify(membrosDAO).remover(Mockito.any(Membro.class));
	}
	
	@Test
	public void deveriaListarMembros(){
		List<Membro> listaMembros = new ArrayList<Membro>();
		Mockito.when(membrosDAO.loadAll()).thenReturn(listaMembros);
		controller.index();
		Assert.assertTrue("deveria ter a lista de membros", result.included().containsKey("membros"));
	}
	
	@Test
	public void deveriaEncontrarMembroPorID(){
		Membro membro = criarMembro();
		Mockito.when(membrosDAO.loadById(membro)).thenReturn(Mockito.any(Membro.class));
		controller.pesquisar(membro);
		Assert.assertTrue("deveria emcontrar um membro", result.included().containsKey("membro"));
	}
	
	private Membro criarMembro() {
		Membro membro = new Membro();
		membro.setId(1);
		membro.setNome("Handerson Frota");
		membro.setEmail("handersonbf@gmail.com");
		return membro;
	}
	
}

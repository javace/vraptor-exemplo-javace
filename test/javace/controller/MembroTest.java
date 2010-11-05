package javace.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.javace.controler.MembrosController;
import br.com.caelum.vraptor.util.test.MockResult;

public class MembroTest {

	@Mock
	private Result result;
	
	private MembrosController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		controller = new MembrosController(result);
	}
	
	@Test
	public void deveriaExibirMembro(){
		controller.index();
		Assert.assertFalse("deveria ser falso", result.included().containsKey("erros"));
		Assert.assertTrue("deveria ser verdadeiro", result.included().containsKey("membro"));
	}
	
}

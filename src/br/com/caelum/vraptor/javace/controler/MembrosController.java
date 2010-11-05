package br.com.caelum.vraptor.javace.controler;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.javace.dao.MembrosDAO;
import br.com.caelum.vraptor.javace.model.Membro;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class MembrosController {

	/**
	 * GET – Listar dados e acessar links;
	 * POST – Salvar uma entidade;
	 * PUT – Atualizar uma entidade;
	 * DELETE – Excluir uma entidade.
	 */
	
	private final Result result;
	private MembrosDAO membrosDAO;
	private Validator validator;

	public MembrosController(Result result, MembrosDAO membrosDAO, Validator validator) {
		this.result = result;
		this.membrosDAO = membrosDAO;
		this.validator = validator;
	}
	
	@Path("/membros/javace")
	public void index(){
		List<Membro> membros = membrosDAO.loadAll();
		result.include("membros", membros);
	}
	
	@Get
	@Path("/novo/membro")
	public void novo() {
	}
	
	@Post
	@Path("/novo/membro")
	public void novo(Membro membro) {
		membro.setId(new Integer(1));
		validator.onErrorUse(Results.page()).of(MembrosController.class).novo();
		
		validaCamposObrigatorios(membro);
		
		try {
			Membro salvar = membrosDAO.salvar(membro);
			result.include("membro", salvar);
		} catch (Exception e) {
			result.include("erros", e.getMessage());
		}
		
		result.forwardTo(this).index();
	}

	@Path("/deletar/membro/{membro.id}")
	public void deletar(Membro membro) {
		membrosDAO.remover(membro);
		result.forwardTo(this).index();
	}
	
	@Get
	@Path("/alterar/membro/{id}")
	public void edita(Integer id) {
		Membro m = new Membro();
		m.setId(id);
		Membro membro = membrosDAO.loadById(m);
		result.include("membro", membro);
	}
	
	@Post
	@Path("/alterar/membro/edita")
	public void edita(Membro membro) {
		membrosDAO.salvar(membro);
		result.redirectTo(this).index();
	}
	
	@Get
	@Path("/membros/pesquisa")
	public void pesquisar(Membro membro){
		Membro loadMembro = membrosDAO.loadById(membro);
		result.include("membro", loadMembro);
		result.forwardTo(this).index();
	}
	
	private void validaCamposObrigatorios(final Membro membro) {
		validator.checking(new Validations(){{
	        that(!membro.getNome().trim().isEmpty(), "ss", "required_field", "Nome");
		}});
		
		validator.checking(new Validations(){{
	        that(!membro.getEmail().trim().isEmpty(), "ss", "required_field", "Email");
		}});
		
		//Outra maneira de criar uma validacao
		if(membro != null && membro.getNome() != ""){
			if(membro.getNome().length() > 15){
				validator.checking(new Validations(){{
			        that(false, "", "mensagem_customizada", "Sua mensagem customizada. Texto Maior que 15 caracteres !");
				}});
			}
		}
		validator.onErrorUse(Results.page()).of(MembrosController.class).novo();
	}
	
	
}

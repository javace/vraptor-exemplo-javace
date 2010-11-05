package br.com.caelum.vraptor.javace.controler;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.javace.dao.MembrosDAO;
import br.com.caelum.vraptor.javace.model.Membro;

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

	public MembrosController(Result result, MembrosDAO membrosDAO) {
		this.result = result;
		this.membrosDAO = membrosDAO;
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
		Membro salvar = membrosDAO.salvar(membro);
		result.include("membro", salvar);
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
	
	
}

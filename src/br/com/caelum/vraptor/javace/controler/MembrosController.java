package br.com.caelum.vraptor.javace.controler;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MembrosController {

	private final Result result;

	public MembrosController(Result result) {
		this.result = result;
	}
	
	@Path("/membros/javace")
	public void index(){
		result.include("membro", "Handerson Frota");
	}
	
	
}

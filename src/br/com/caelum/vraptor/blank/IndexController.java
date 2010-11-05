/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.caelum.vraptor.blank;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.javace.dao.MembrosDAO;
import br.com.caelum.vraptor.javace.model.Membro;
import br.com.caelum.vraptor.view.Results;

@Resource
public class IndexController {

	private final Result result;
	private MembrosDAO membrosDAO;

	public IndexController(Result result, MembrosDAO membrosDAO) {
		this.result = result;
		this.membrosDAO = membrosDAO;
	}

	@Path("/")
	public void index() {
		result.include("variavel", "VRaptor!");
	}
	
	@Path("/jquery")
	public void jquery(){
		Membro m = new Membro();
		m.setId(new Integer(1));
		m.setNome("Handerson Frota");
		m.setEmail("handersonbf@gmail.com");
		result.use(Results.json()).from(m).serialize();
	}

}

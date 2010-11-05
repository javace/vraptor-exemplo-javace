package br.com.caelum.vraptor.javace.dao;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.javace.model.Membro;

public interface MembrosDAO {

	public Membro salvar(Membro membro);

	public List<Membro> loadAll();

	public Membro loadById(Membro membro);

	public void remover(Membro membro);

}

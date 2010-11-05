package br.com.caelum.vraptor.javace.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.javace.model.Membro;

@ApplicationScoped
@Component
public class MembrosDAOImpl implements MembrosDAO, Serializable {
	
	private static final long serialVersionUID = 6168824114064770114L;
	
	private List<Membro> membroList = new ArrayList<Membro>();
	private Integer id = 1;

	public Membro salvar(Membro membro) {
		membroList.add(membro);
		membro.setId(id++);
		return membro;
	}

	public List<Membro> loadAll() {
	return membroList;
	}

	public Membro loadById(Membro membro) {
		Membro membroDelete = null;
		for (Membro item : membroList) {
			if (item.getId().intValue() == membro.getId().intValue()) {
				membroDelete = item;
				break;
			}
		}

		removerItem(membroDelete);

		return membroDelete;
	}

	public void remover(Membro membro) {
		Membro membroDelete = null;
	
		for (Membro item : membroList) {
			if (membro.getId().intValue() == item.getId().intValue()) {
				membroDelete = item;
				break;
			}
		}
	
		removerItem(membroDelete);
	}

	private void removerItem(Membro membroDelete) {
		if (membroList.remove(membroDelete)) {
			id--;
		}
	}

}

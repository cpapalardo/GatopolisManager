package br.com.farofa.gm.dao;

import javax.persistence.EntityManager;

import br.com.farofa.gm.model.Note;

public class NoteDAOImpl extends GenericDAOImpl<Note, Integer> implements NoteDAO {

	public NoteDAOImpl(EntityManager manager) {
		super(manager);
	}

}

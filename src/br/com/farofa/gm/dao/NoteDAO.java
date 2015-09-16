package br.com.farofa.gm.dao;

import javax.inject.Named;

import br.com.farofa.gm.model.Note;

@Named
public interface NoteDAO extends GenericDAO<Note, Integer>  {

}

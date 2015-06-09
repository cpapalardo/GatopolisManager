package br.com.farofa.gm.webservice;

import br.com.farofa.gm.model.Note;

public class NoteWS extends GenericWSImpl<Note, Integer> {
	public NoteWS() {
		super(Note.class);
	}
}

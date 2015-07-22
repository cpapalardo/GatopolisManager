package br.com.farofa.gm.dao;

import javax.inject.Named;

import br.com.farofa.gm.model.GameBird;

@Named
public interface GameBirdDAO extends GenericDAO<GameBird, Integer> {
	
}

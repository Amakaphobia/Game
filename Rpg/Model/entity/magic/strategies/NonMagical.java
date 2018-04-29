package entity.magic.strategies;

import java.util.ArrayList;
import java.util.List;

import entity.magic.I_SpellStrategy;
import entity.magic.SpellBase;

@SuppressWarnings("javadoc") //TODO Docu
public class NonMagical implements I_SpellStrategy{

	public NonMagical() {
		super();
	}

	@Override
	public List<SpellBase<?>> getSpellsInMemory() {
		return new ArrayList<>();
	}

	@Override
	public List<SpellBase<?>> getSpellsLearned() {
		return new ArrayList<>();
	}

	@Override
	public List<SpellBase<?>> getSpellsComplete() {
		return new ArrayList<>();
	}

}

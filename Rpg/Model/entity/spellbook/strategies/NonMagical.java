package entity.spellbook.strategies;

import java.util.ArrayList;
import java.util.List;

import entity.spellbook.I_SpellStrategy;
import entity.spellbook.SpellBase;

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

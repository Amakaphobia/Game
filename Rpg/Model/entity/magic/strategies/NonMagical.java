package entity.magic.strategies;

import java.util.ArrayList;
import java.util.List;

import entity.magic.I_SpellStrategy;
import entity.magic.SpellBase;

/**
 * This Class is a basic implementation of the spell strategy for people who cannot do magic. Ii returns only empty
 * Lists what so ever.
 *
 * @author Dave
 *
 * @see I_SpellStrategy
 */
public class NonMagical implements I_SpellStrategy{

	/**Constructor*/
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

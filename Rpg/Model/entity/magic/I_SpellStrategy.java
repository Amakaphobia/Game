package entity.magic;

import java.io.Serializable;
import java.util.List;

/**
 * This Interface is implemented by all Spell-Strategies. SpellStrategies are objects that handle all the logic
 * necessary for magical (or non magical) magic users. Its abstract Methods {@link #getSpellsComplete()},
 * {@link #getSpellsLearned()}, {@link #getSpellsInMemory()} are used to access the three different lists responsible
 * for handling what spells can be learned by this class (complete), what spells are learned by the parent of this class
 * (Learned) and which spells are currently in memory ready to be cast (InMemory).
 *
 * @author Dave
 *
 * @see SpellBase
 */
public interface I_SpellStrategy extends Serializable{

	//Abstract

	/**
	 * This abstract Method is used to get A list of Spells that are currently in memory and castable by the class
	 * @return a list of all Spells currently in memory
	 */
	public abstract List<SpellBase<?>> getSpellsInMemory();
	/**
	 * This abstract Method is used to get A list of Spells that are currently known by the class
	 * @return a list of all Spells currently in your spell book
	 */
	public abstract List<SpellBase<?>> getSpellsLearned();
	/**
	 * This abstract Method is used to get A list of all Spells that can be learned by this class
	 * @return a list of all Spells that you could possibly learn
	 */
	public abstract List<SpellBase<?>> getSpellsComplete();

	//Default
	/**
	 * This Default method checks if this class can learn any magic at all or if it is completely mundane
	 * @return true if the {@link #getSpellsComplete()} is empty false if not
	 */
	public default boolean isMagic() {
		return this.getSpellsComplete().size() > 0;
	}
	/**
	 * Tests if a given Spell can be committed to memory. only tests if the given spell is part of
	 * {@link #getSpellsLearned()}. Does NOT handle maximum spells.
	 * @param Spell the spell you want to commit to memory
	 * @return true if the given spell is part of the learned list
	 */
	public default boolean canCommitToMemory(SpellBase<?> Spell) {
		return this.getSpellsLearned().stream()
					.filter(s -> s.equals(Spell))
					.findFirst()
					.isPresent();
	}
	/**
	 * Tests if a given Spell can be learned. only tests if the given spell is part of
	 * {@link #getSpellsComplete()}. Does NOT handle maximum spells.
	 * @param Spell the spell you want to learn
	 * @return true if the given spell is part of the complete list
	 */
	public default boolean canLearn(SpellBase<?> Spell) {
		return this.getSpellsComplete().stream()
					.filter(s -> s.equals(Spell))
					.findFirst()
					.isPresent();
	}

	/**
	 * Tests if a given Spell can be cast. only tests if the given spell is part of
	 * {@link #getSpellsInMemory()}. Does NOT handle maximum spells.
	 * @param Spell the spell you want to cast
	 * @return true if the given spell is part of the memory list
	 */
	public default boolean canCast(SpellBase<?> Spell) {
		return this.getSpellsInMemory().stream()
					.filter(s -> s.equals(Spell))
					.findFirst()
					.isPresent();
	}

	/**
	 * this Method is used to add a spell to your Spellbook. if the spell cannot be learned or is already learned
	 * nothing happens
	 * @param Spell the SPell you want to learn
	 */
	public default void learnSpell(SpellBase<?> Spell) {
		if(canCommitToMemory(Spell)) return;
		if(!canLearn(Spell)) return;
		this.getSpellsLearned().add(Spell);
	}

	/**
	 * this method is used to commit a spell to memory to ready it to be cast
	 * @param Spell the spell you want to commit to memory
	 */
	public default void memorizeSpell(SpellBase<?> Spell) {
		if(!canCommitToMemory(Spell)) return;
		this.getSpellsInMemory().add(Spell);
	}
}

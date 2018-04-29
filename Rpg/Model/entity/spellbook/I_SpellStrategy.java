package entity.spellbook;

import java.io.Serializable;
import java.util.List;

public interface I_SpellStrategy extends Serializable{

	public abstract List<I_Spell<?>> getSpellsLearned();

	public abstract List<I_Spell<?>> getSpellsComplete();

	public abstract boolean canCast(I_Spell Spell);

	public default boolean isMagic() {
		return this.getSpellsLearned().size() > 0;
	}

}

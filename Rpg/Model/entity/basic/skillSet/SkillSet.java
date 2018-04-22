package entity.basic.skillSet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the skills a character has learned so far. It contains a list and is itself a {@link Iterable}
 * delegates the {@link SkillSet#iterator()} call to the {@link List#iterator()} method.
 * @author Dave
 *
 */
public class SkillSet implements I_SkillSet{

	/**the list that holds the all skills in this Set*/
	private List<Skill> skillList;

	/**empty constructor, sets up with an empty skill list*/
	public SkillSet() {
		this(new LinkedList<Skill>());
	}
	/**
	 * Constructor
	 * it uses initiates itself with the list parameter variable
	 * @param liste a list of {@link Skill}s that should be used by this set
	 */
	public SkillSet(List<Skill> liste) {
		super();
		this.skillList = new LinkedList<>(liste);
	}

	/**
	 * This Method is used count the different skills an Actor has
	 * @return the size of the List {@link #skillList}.
	 */
	public int size() { return this.skillList.size(); }

	@Override
	public Optional<Skill> getSkill(Skills id) {
		return this.skillList.stream()
				.filter(skill -> id.equals(skill.getName()))
				.findFirst();
	}

	@Override
	public void addSkill(Skills id, int level) {
		final Optional<Skill> skill = this.getSkill(id);

		if(skill.isPresent())
			return;

		this.skillList.add(new Skill(id, level));
	}

	@Override
	public void removeSkill(Skills id) {
		this.skillList.removeIf(e -> e.getName() == id);
	}

	@Override
	public Iterator<Skill> iterator() {
		return this.skillList.iterator();
	}

	@Override
	public String toString() {
		return this.skillList.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof SkillSet)) return false;

		SkillSet other = (SkillSet) obj;
		return this.skillList.equals(other.skillList);
	}

	@Override
	public int hashCode() {
		int erg = 0;
		int count = 1;
		int mult, hash;

		for(Skill e : this.skillList) {
			mult = 1;
			hash = e.hashCode();
			for(int i = 0; i < count; i++)
				mult *= hash;
			erg += mult;
		}
		return erg;
	}
}

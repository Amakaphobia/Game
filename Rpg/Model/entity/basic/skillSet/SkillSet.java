package entity.basic.skillSet;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import entity.basic.common.enums.skillsattributes.Skills;

/**
 * This class represents the skills a character has learned so far. It contains a list and is itself a {@link Iterable}
 * delegates the {@link SkillSet#iterator()} call to the {@link List#iterator()} method.
 * @author Dave
 *
 */
public class SkillSet implements I_SkillSet{

	/**the list that holds the all skills in this Set*/
	private final Map<Skills, I_Skill> skillList;

	//Constructor

	/**empty constructor, sets up with an empty skill list*/
	public SkillSet() {
		super();
		this.skillList = new TreeMap<>();
	}

	/**
	 * Constructor
	 * it uses initiates itself with the list parameter variable
	 * @param liste a list of {@link Skill}s that should be used by this set
	 */
	public SkillSet(List<I_Skill> liste) {
		this();
		for(I_Skill e : liste)
			this.addSkill(e.getName(), e.getValue());
	}

	//Deocrator

	@Override
	public void addDecorator(I_SkillSet other) {
		for(I_Skill e : other) {
			if(!this.skillList.containsKey(e.getName()))
				this.addSkill(e.getName(), 0);
			this.getSkill(e.getName())
			.get()
			.addDecorator(e);
		}

	}
	@Override
	public void removeFirstDecorator(I_SkillSet other) {
		for(I_Skill e : other) {
			Skills name = e.getName();
			if(!this.skillList.containsKey(name))
				continue;
			this.skillList.get(name)
				.removeFirstDecorator(e);
			if(this.getSkillLevel(name) == 0)
				this.removeSkill(name);
		}

	}

	//SkillSet
	//TODO Test empty optional
	@Override
	public Optional<I_Skill> getSkill(Skills id) {
		return Optional.ofNullable(this.skillList.get(id));
	}

	@Override
	public void addSkill(Skills id, int level) {
		this.skillList.putIfAbsent(id, new Skill(id, level));
	}

	@Override
	public void removeSkill(Skills id) {
		this.skillList.remove(id);
	}

	/**
	 * This Method is used count the different skills an Actor has
	 * @return the size of the List {@link #skillList}.
	 */
	public int size() { return this.skillList.size(); }

	//Iterable

	@Override
	public Iterator<I_Skill> iterator() {
		return this.skillList.values().iterator();
	}

	//Obj

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

		for(I_Skill e : this) {
			mult = 1;
			hash = e.hashCode();
			for(int i = 0; i < count; i++)
				mult *= hash;
			erg += mult;
			count++;
		}
		return erg;
	}
}

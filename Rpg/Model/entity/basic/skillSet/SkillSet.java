package entity.basic.skillSet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SkillSet implements I_SkillSet, Serializable{

	/**serial*/
	private static final long serialVersionUID = 6020275397583604437L;
	private List<Skill> skilllist;
	
	public SkillSet() {
		this(new ArrayList<Skill>());
	}
	
	public SkillSet(List<Skill> liste) {
		super();
		this.skilllist = liste;
	}

	@Override
	public Optional<Skill> getSkill(Skills id) {
		return this.skilllist.stream()
				.filter(skill -> id.equals(skill.getName()))
				.findFirst();
	}

	@Override
	public void addSkill(Skills id, int level) {
		final Optional<Skill> skill = this.getSkill(id);
				
		if(skill.isPresent())
			return;
		
		this.skilllist.add(new Skill(id, level));
	}

	@Override
	public void removeSkill(Skills id) {
		this.getSkill(id)
			.ifPresent(s -> this.skilllist.remove(s));		
	}

	@Override
	public Iterator<Skill> iterator() {
		return this.skilllist.iterator();
	}
	
	@Override
	public String toString() {
		return this.skilllist.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof SkillSet)) return false;
		
		SkillSet other = (SkillSet) obj;
		return this.skilllist.equals(other.skilllist);
	}
	
	@Override
	public int hashCode() {
		int erg = 0;
		int count = 1;
		int mult, hash;
		
		for(Skill e : this.skilllist) {
			mult = 1;
			hash = e.hashCode();
			for(int i = 0; i < count; i++) {
				mult *= hash;
			}
			erg += mult;
		}
		return erg;
	}
}

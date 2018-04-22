package entity.basic.common.enums.size;

@SuppressWarnings("javadoc")
public enum Sizes {

	FINE("Fine", 8, -16, 16, 0, 0, 1/8, 1/4),
	DIMINUTIVE("Diminutive", 4, -12, 12, 0, 0, 1/4, 1/2),
	TINY("Tiny", 2, -8, 8, 0, 0, 1/2, 3/4),
	SMALL("Small", 1, -4, 4, 5, 5, 3/4, 1),
	MEDIUM("Medium", 0, 0, 0, 5, 5, 1, 1.5),
	LARGE("Large", -1, 4, -4, 10, 5, 2, 3),
	HUGE("Huge", -2, +8, -8, 15, 10, 4, 6),
	GARGANTUAN("Gargantuan", -4, 12, -12, 20, 15, 8, 12),
	COLOSSAL("Colossal", -8, 16, -16, 30, 20, 16, 24);

	/**the name of the SizeLogic*/
	private String nameId;
	/**@return the nameId*/
	public String getNameId() { return this.nameId; }

	/**modifier used by attackrolls and armor class*/
	private int attackAcModifier;
	/**@return the attackAcModifier*/
	public int getAttackAcModifier() { return this.attackAcModifier; }
	/**This modifier applies to the bull rush, grapple, overrun, and trip special attacks.*/
	private int specialAttackModifier;
	/**@return the specialAttackModifier*/
	public int getSpecialAttackModifier() { return this.specialAttackModifier; }
	/**Modifier for hiding rolls*/
	private int hideModifier;
	/**@return the hideModifier*/
	public int getHideModifier() { return this.hideModifier; }

	/**true if this creatures longest axis isnt its height*/
	private boolean isLong = false;
	/**@param isLong set to true if this creatures longest axis isnt its height*/
	public void setLong(boolean isLong) { this.isLong = isLong; }
	/**@return the isLong*/
	public boolean isLong() { return this.isLong; }
	/**true if this creature has more than 2 legs*/
	private boolean isQuadruped = false;
	/**@param isQuadruped true if more than 2 legs*/
	public void setQuadruped(boolean isQuadruped) { this.isQuadruped = isQuadruped; }
	/**@return true if more than 2 legs*/
	public boolean isQuadruped() { return this.isQuadruped; }

	/**the unarmed reach of this creature if its longer than tall*/
	private int naturalReachIfLong;
	/**@return the naturalReachIfLong*/
	public int getNaturalReachIfLong() { return this.naturalReachIfLong; }
	/**the unarmed reach of this creature if its taller than long*/
	private int naturalReachIfTall;
	/**@return the naturalReachIfTall*/
	public int getNaturalReachIfTall() { return this.naturalReachIfTall; }


	private double carryModifierIfBiped;
	/**@return the carryModifierIfBiped*/
	public double getCarryModifierIfBiped() { return this.carryModifierIfBiped; }
	private double carryModifierIfQuadruped;
	/**@return the carryModifierIfQuadruped*/
	public double getCarryModifierIfQuadruped() { return this.carryModifierIfQuadruped; }

	/**
	 * Constructor
	 * @param nameId {@link #nameId}
	 * @param attackAcModifier {@link #attackAcModifier}
	 * @param specialAttackModifier {@link #specialAttackModifier}
	 * @param hideModifier {@link #hideModifier}
	 * @param naturalReachIfLong {@link #naturalReachIfLong}
	 * @param naturalReachIfTall {@link #naturalReachIfTall}
	 * @param carryModifierIfBiped {@link #carryModifierIfBiped}
	 * @param carryModifierIfQuadruped {@link #carryModifierIfQuadruped}
	 */
	private Sizes(
			String nameId,
			int attackAcModifier,
			int specialAttackModifier,
			int hideModifier,
			int naturalReachIfLong,
			int naturalReachIfTall,
			double carryModifierIfBiped,
			double carryModifierIfQuadruped) {

		this.nameId = nameId;
		this.attackAcModifier = attackAcModifier;
		this.specialAttackModifier = specialAttackModifier;
		this.hideModifier = hideModifier;
		this.naturalReachIfLong = naturalReachIfLong;
		this.naturalReachIfTall = naturalReachIfTall;
		this.carryModifierIfBiped = carryModifierIfBiped;
		this.carryModifierIfQuadruped = carryModifierIfQuadruped;
	}
}

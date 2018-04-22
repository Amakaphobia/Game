package entity.basic.common.enums.size;

/**
 * This enum defines all possible sizes a Actor can have.
 * @author Dave
 *
 */
public enum Sizes {

	/**6 in. or less<br>1/8 lb. or less*/
	FINE("Fine", 8, -16, 16, 0, 0, 1/8, 1/4),

	/**6 in. - 1 ft.<br>1/8 lb. - 1 lb.*/
	DIMINUTIVE("Diminutive", 4, -12, 12, 0, 0, 1/4, 1/2),

	/**1 ft. - 2 ft.<br>1 lb. - 8 lb.*/
	TINY("Tiny", 2, -8, 8, 0, 0, 1/2, 3/4),

	/**2 ft. - 4 ft.<br>8 lb. - 60 lb.*/
	SMALL("Small", 1, -4, 4, 5, 5, 3/4, 1),

	/**4 ft. - 8 ft.<br>60 lb. - 500 lb.*/
	MEDIUM("Medium", 0, 0, 0, 5, 5, 1, 1.5),

	/**8 ft. - 16 ft.<br>500 lb. - 2 tons*/
	LARGE("Large", -1, 4, -4, 10, 5, 2, 3),

	/**16 ft. - 32 ft.<br>2 tons - 16 tons*/
	HUGE("Huge", -2, +8, -8, 15, 10, 4, 6),

	/**32 ft. - 64 ft.<br>16 tons - 125 tons*/
	GARGANTUAN("Gargantuan", -4, 12, -12, 20, 15, 8, 12),

	/**64 ft. or more<br>125 tons or more*/
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

	/**true if this creatures longest axis isn't its height*/
	private boolean isLong = false;
	/**@param isLong set to true if this creatures longest axis isn't its height*/
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

	/**modifier for carry weight if creature is biped*/
	private double carryModifierIfBiped;
	/**@return the carryModifierIfBiped*/
	public double getCarryModifierIfBiped() { return this.carryModifierIfBiped; }
	/**modifier for carry weight if creature is Quadruped*/
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

package nl.paulinternet.gtasaveedit.model.variables;

import nl.paulinternet.gtasaveedit.model.savegame.data.Cloth;
import nl.paulinternet.gtasaveedit.model.savegame.data.Garage;
import nl.paulinternet.gtasaveedit.model.savegame.data.Jump;
import nl.paulinternet.gtasaveedit.model.savegame.data.Pickup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Variables
{
	// Version
	public final VariableIntegerImpl version = new VariableIntegerImpl();
	public final VariableIntegerImpl scriptVersion = new VariableIntegerImpl();
	public final VariableIntegerImpl currentIplVersion = new VariableIntegerImpl();
	public final VariableIntegerImpl convertIplVersion = new VariableIntegerImpl();
	
	public final VariableTitle title = new VariableTitle();
	public final VariableIntegerImpl storyLine = new VariableIntegerImpl();
	public final VariableBoolean roadblockSF = new RoadblockVariable(RoadblockVariable.SAN_FIERRO);
	public final VariableBoolean roadblockLV = new RoadblockVariable(RoadblockVariable.LAS_VENTURAS);
	public final VariableBooleanImpl gangWars = new VariableBooleanImpl();
	public final VariableBooleanImpl riots = new VariableBooleanImpl();
	public final VariableBooleanImpl hotCoffee = new VariableBooleanImpl();
	public final VariableBooleanImpl taxiNitro = new VariableBooleanImpl();
	public final VariableBooleanImpl prostitutesPay = new VariableBooleanImpl();
	public final VariableBooleanImpl loseStuffWasted = new VariableBooleanImpl();
	public final VariableBooleanImpl loseStuffBusted = new VariableBooleanImpl();
	public final VariableBooleanImpl basketballGlitch = new VariableBooleanImpl();
	public final VariableBooleanImpl poolPlayerGlitch = new VariableBooleanImpl();
	public final VariableBooleanImpl zoneGlitch = new VariableBooleanImpl();
	public final VariableBooleanImpl uncensored = new VariableBooleanImpl();
	public final VariableBooleanImpl twoTimingDate = new VariableBooleanImpl();
	public final VariableIntegerImpl timesCheated = new VariableIntegerImpl();
	public final VariableIntegerImpl timesBusted = new VariableIntegerImpl();
	public final VariableIntegerImpl timesWasted = new VariableIntegerImpl();
	
	// Schools
	public final List<VariableIntegerImpl> schoolDriving = intList(12);
	public final List<VariableIntegerImpl> schoolFlying = intList(10);
	public final List<VariableIntegerImpl> schoolBoat = intList(5);
	public final List<VariableIntegerImpl> schoolBike = intList(6);
	
	// Player data (block 15)
	public final VariableIntegerImpl money = new VariableIntegerImpl();
	public final VariableIntegerImpl moneyOnScreen = new VariableIntegerImpl();
	public final VariableBooleanImpl infiniteRun = new VariableBooleanImpl();
	public final VariableBooleanImpl fastReload = new VariableBooleanImpl();
	public final VariableBooleanImpl fireProof = new VariableBooleanImpl();
	public final VariableBooleanImpl freeBustedOnce = new VariableBooleanImpl();
	public final VariableBooleanImpl freeWastedOnce = new VariableBooleanImpl();
	public final VariableBooleanImpl enableDriveby = new VariableBooleanImpl();
	
	// Ped Acquaintances
	public final boolean[][][] pedAcq = new boolean[4][32][32]; // respect, like, dislike, hate
	
	// Clothes
	public final List<Variable<Cloth>> clothes = varList(9);
	public final List<Variable<Cloth>> tattoos = varList(9);

	// Tags
	public final int[] tags = new int[100];
	
	// Jumps
	public Jump[] jumps;
	
	// Pickups
	public final VariableIntegerImpl oystersCollected = new VariableIntegerImpl();
	public final VariableIntegerImpl snapshotsCollected = new VariableIntegerImpl();
	public final VariableIntegerImpl horseshoesCollected = new VariableIntegerImpl();
	
	public List<Pickup> oysters, snapshots, horseshoes;
	
	// Help messages
	public final VariableBooleanImpl helpCar = new VariableBooleanImpl();
	public final VariableBooleanImpl helpWasted = new VariableBooleanImpl();
	public final VariableBooleanImpl helpBusted = new VariableBooleanImpl();
	public final VariableBooleanImpl helpSwimming = new VariableBooleanImpl();
	public final VariableBooleanImpl helpGym = new VariableBooleanImpl();
	public final VariableBooleanImpl helpStealVehicle = new VariableBooleanImpl();
	public final VariableIntegerImpl helpReplaceWeapon = new VariableIntegerImpl(0, 255);
	
	// Save location
	public final VariableIntegerImpl savePlace = new VariableIntegerImpl();

	// Time
	public final VariableIntegerImpl timeHour = new VariableIntegerImpl();
	public final VariableIntegerImpl timeMinute = new VariableIntegerImpl();
	public final VariableIntegerImpl daysPassed = new VariableIntegerImpl();
	public final VariableIntegerImpl minuteLength = new VariableIntegerImpl();
	public final VariableIntegerImpl timeDayOfWeek = new VariableIntegerImpl();
	
	// Weapons
	public final VariableIntegerImpl weaponStartSlot = new VariableIntegerImpl();
	public final List<VariableIntegerImpl> weaponType = intList(13);
	public final List<VariableIntegerImpl> weaponAmmo = intList(13);
	public final List<VariableIntegerImpl> gangWeapon = intList(30);
	
	// Body / Skills
	public final VariableFloat health = new VariableFloat();
	public final VariableFloat armor = new VariableFloat();
	public final VariableFloat fat = new VariableFloat();
	public final VariableFloat stamina = new VariableFloat();
	public final VariableFloat muscle = new VariableFloat();
	public final VariableFloat maxHealth = new VariableFloat();
	public final VariableFloat respect = new VariableFloat();
	public final VariableFloat weaponPistol = new VariableFloat();
	public final VariableFloat weaponSilencedPistol = new VariableFloat();
	public final VariableFloat weaponDesertEagle = new VariableFloat();
	public final VariableFloat weaponShotgun = new VariableFloat();
	public final VariableFloat weaponSawnoffShotgun = new VariableFloat();
	public final VariableFloat weaponCombatShotgun = new VariableFloat();
	public final VariableFloat weaponMachinePistol = new VariableFloat();
	public final VariableFloat weaponSmg = new VariableFloat();
	public final VariableFloat weaponAk47 = new VariableFloat();
	public final VariableFloat weaponM4 = new VariableFloat();
	public final VariableFloat sexAppeal = new VariableFloat();
	public final VariableFloat gamblingSkill = new VariableFloat();
	public final VariableIntegerImpl drivingSkill = new VariableIntegerImpl();
	public final VariableIntegerImpl flyingSkill = new VariableIntegerImpl();
	public final VariableIntegerImpl lungCapacity = new VariableIntegerImpl();
	public final VariableIntegerImpl bikeSkill = new VariableIntegerImpl();
	public final VariableIntegerImpl cyclingSkill = new VariableIntegerImpl();

	// Garages / Cars
	public final List<VariableIntegerImpl> carId =  intList(Garage.TOTAL_COUNT); // probably not enough as you can place more than one car into a single garage
	public final List<VariableIntegerImpl> radioId = intList(Garage.TOTAL_COUNT); // probably not enough as you can place more than one car into a single garage
	public final List<VariableIntegerImpl> color1Id = intList(Garage.TOTAL_COUNT); // probably not enough as you can place more than one car into a single garage
	public final List<VariableIntegerImpl> color2Id = intList(Garage.TOTAL_COUNT); // probably not enough as you can place more than one car into a single garage

	public Variables () {
		currentIplVersion.onChange().addHandler(roadblockSF, "updateValue");
		currentIplVersion.onChange().addHandler(roadblockLV, "updateValue");
	}

	private List<VariableIntegerImpl> intList (int size) {
		List<VariableIntegerImpl> list = new ArrayList<VariableIntegerImpl>(size);
		for (int i=0; i<size; i++) {
			list.add(new VariableIntegerImpl());
		}
		return Collections.unmodifiableList(list);
	}
	
	private <E> List<Variable<E>> varList (int size) {
		List<Variable<E>> list = new ArrayList<Variable<E>>(size);
		for (int i=0; i<size; i++) {
			list.add(new Variable<E>());
		}
		return Collections.unmodifiableList(list);
	}
}

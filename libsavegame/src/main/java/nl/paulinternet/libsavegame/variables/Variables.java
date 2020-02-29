package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.data.Cloth;
import nl.paulinternet.libsavegame.data.Garage;
import nl.paulinternet.libsavegame.data.Jump;
import nl.paulinternet.libsavegame.data.Pickup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Variables {
    private static Variables instance;
    // Version
    public final Variable<Integer> version = new Variable<>();
    public final Variable<Integer> scriptVersion = new Variable<>();
    public final Variable<Integer> currentIplVersion = new Variable<>();
    public final Variable<Integer> convertIplVersion = new Variable<>();

    public final VariableTitle title = new VariableTitle();
    public final Variable<Integer> storyLine = new Variable<>();
    public final RoadblockVariable roadblockSF = new RoadblockVariable(RoadblockVariable.SAN_FIERRO);
    public final RoadblockVariable roadblockLV = new RoadblockVariable(RoadblockVariable.LAS_VENTURAS);
    public final Variable<Boolean> gangWars = new Variable<>();
    public final Variable<Boolean> riots = new Variable<>();
    public final Variable<Boolean> hotCoffee = new Variable<>();
    public final Variable<Boolean> taxiNitro = new Variable<>();
    public final Variable<Boolean> prostitutesPay = new Variable<>();
    public final Variable<Boolean> loseStuffWasted = new Variable<>();
    public final Variable<Boolean> loseStuffBusted = new Variable<>();
    public final Variable<Boolean> basketballGlitch = new Variable<>();
    public final Variable<Boolean> poolPlayerGlitch = new Variable<>();
    public final Variable<Boolean> zoneGlitch = new Variable<>();
    public final Variable<Boolean> uncensored = new Variable<>();
    public final Variable<Boolean> twoTimingDate = new Variable<>();
    public final Variable<Integer> timesCheated = new Variable<>();
    public final Variable<Integer> timesBusted = new Variable<>();
    public final Variable<Integer> timesWasted = new Variable<>();

    // Schools
    public final List<Variable<Integer>> schoolDriving = intList(12);
    public final List<Variable<Integer>> schoolFlying = intList(10);
    public final List<Variable<Integer>> schoolBoat = intList(5);
    public final List<Variable<Integer>> schoolBike = intList(6);

    // Player data (block 15)
    public final Variable<Integer> money = new Variable<>() {
        @Override
        public int getMaxLength() {
            return 9;
        }
    };
    public final Variable<Integer> moneyOnScreen = new Variable<>();
    public final Variable<Boolean> infiniteRun = new Variable<>();
    public final Variable<Boolean> fastReload = new Variable<>();
    public final Variable<Boolean> fireProof = new Variable<>();
    public final Variable<Boolean> freeBustedOnce = new Variable<>();
    public final Variable<Boolean> freeWastedOnce = new Variable<>();
    public final Variable<Boolean> enableDriveby = new Variable<>();

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
    public final Variable<Integer> oystersCollected = new Variable<>();
    public final Variable<Integer> snapshotsCollected = new Variable<>();
    public final Variable<Integer> horseshoesCollected = new Variable<>();

    public List<Pickup> oysters, snapshots, horseshoes;

    // Help messages
    public final Variable<Boolean> helpCar = new Variable<>();
    public final Variable<Boolean> helpWasted = new Variable<>();
    public final Variable<Boolean> helpBusted = new Variable<>();
    public final Variable<Boolean> helpSwimming = new Variable<>();
    public final Variable<Boolean> helpGym = new Variable<>();
    public final Variable<Boolean> helpStealVehicle = new Variable<>();
    public final Variable<Integer> helpReplaceWeapon = new Variable<>();

    // Save location
    public final Variable<Integer> savePlace = new Variable<>();

    // Time
    public final Variable<Integer> timeHour = new Variable<>();
    public final Variable<Integer> timeMinute = new Variable<>();
    public final Variable<Integer> daysPassed = new Variable<>();
    public final Variable<Integer> minuteLength = new Variable<>();
    public final Variable<Integer> timeDayOfWeek = new Variable<>();

    // Weapons
    public final Variable<Integer> weaponStartSlot = new Variable<>();
    public final List<Variable<Integer>> weaponType = intList(13);
    public final List<Variable<Integer>> weaponAmmo = intList(13);
    public final List<Variable<Integer>> gangWeapon = intList(30);

    // Body / Skills
    public final Variable<Float> health = new Variable<>();
    public final Variable<Float> armor = new Variable<>();
    public final Variable<Float> fat = new Variable<>();
    public final Variable<Float> stamina = new Variable<>();
    public final Variable<Float> muscle = new Variable<>();
    public final Variable<Float> maxHealth = new Variable<>();
    public final Variable<Float> respect = new Variable<>();
    public final Variable<Float> weaponPistol = new Variable<>();
    public final Variable<Float> weaponSilencedPistol = new Variable<>();
    public final Variable<Float> weaponDesertEagle = new Variable<>();
    public final Variable<Float> weaponShotgun = new Variable<>();
    public final Variable<Float> weaponSawnoffShotgun = new Variable<>();
    public final Variable<Float> weaponCombatShotgun = new Variable<>();
    public final Variable<Float> weaponMachinePistol = new Variable<>();
    public final Variable<Float> weaponSmg = new Variable<>();
    public final Variable<Float> weaponAk47 = new Variable<>();
    public final Variable<Float> weaponM4 = new Variable<>();
    public final Variable<Float> sexAppeal = new Variable<>();
    public final Variable<Float> gamblingSkill = new Variable<>();
    public final Variable<Integer> drivingSkill = new Variable<>();
    public final Variable<Integer> flyingSkill = new Variable<>();
    public final Variable<Integer> lungCapacity = new Variable<>();
    public final Variable<Integer> bikeSkill = new Variable<>();
    public final Variable<Integer> cyclingSkill = new Variable<>();

    // Garages / Cars
    public final List<Garage.Car> garageCars = garageCarList(Garage.TOTAL_COUNT); // TODO: probably not enough as you can place more than one car into a single garage

    private Variables() {
    }

    public static Variables get() {
        if (instance == null) {
            instance = new Variables();
        }
        return instance;
    }

    private List<Variable<Integer>> intList(int size) {
        List<Variable<Integer>> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Variable<>());
        }
        return Collections.unmodifiableList(list);
    }

    private List<Garage.Car> garageCarList(int size) {
        List<Garage.Car> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Garage.Car());
        }
        return Collections.unmodifiableList(list);
    }

    private <E> List<Variable<E>> varList(int size) {
        List<Variable<E>> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Variable<>());
        }
        return Collections.unmodifiableList(list);
    }
}

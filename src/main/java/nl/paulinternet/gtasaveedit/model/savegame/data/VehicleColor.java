package nl.paulinternet.gtasaveedit.model.savegame.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleColor {

    private static final Logger log = LoggerFactory.getLogger(VehicleColor.class);

    private final int id;
    private final String name;
    private final int[] rgb;


    private VehicleColor(int id, int r, int g, int b, String name) {
        this.id = id;
        this.name = name;
        this.rgb = new int[]{g, r, b};
    }

    private VehicleColor(int id, int r, int g, int b) {
        this.id = id;
        this.name = "Unnamed rgb(" + r + ", " + g + ", " + b + ")";
        this.rgb = new int[]{g, r, b};
        log.warn("WARN: no colorName set for color with id: '" + id + "' (" + String.format("#%02X%02X%02X", r, g, b) + ")");
    }

    public int getId() {
        return id;
    }

    public int[] getRgb() {
        return rgb;
    }

    public String getName() {
        return name;
    }

    private static List<VehicleColor> colors;

    static {
        colors = new ArrayList<>();

        colors.add(new VehicleColor(0, 0, 0, 0, "Black"));
        colors.add(new VehicleColor(1, 245, 245, 245, "White"));
        colors.add(new VehicleColor(2, 42, 119, 161, "Police Car Blue"));
        colors.add(new VehicleColor(3, 132, 4, 16, "Sangria"));
        colors.add(new VehicleColor(4, 38, 55, 57, "Oxford Blue"));
        colors.add(new VehicleColor(5, 134, 68, 110, "Cannon Pink"));
        colors.add(new VehicleColor(6, 215, 142, 16, "Golden Bell"));
        colors.add(new VehicleColor(7, 76, 117, 183, "Steel Blue"));
        colors.add(new VehicleColor(8, 189, 190, 198, "French Gray"));
        colors.add(new VehicleColor(9, 94, 112, 114, "Nevada"));
        colors.add(new VehicleColor(10, 70, 89, 122, "Blue Bayoux"));
        colors.add(new VehicleColor(11, 101, 106, 121, "Shuttle Gray"));
        colors.add(new VehicleColor(12, 93, 126, 141, "Lynch"));
        colors.add(new VehicleColor(13, 88, 89, 90, "Abbey)"));
        colors.add(new VehicleColor(14, 214, 218, 214, "Tasman"));
        colors.add(new VehicleColor(15, 156, 161, 163, "Gray Chateau"));
        colors.add(new VehicleColor(16, 51, 95, 63, "Killarney"));
        colors.add(new VehicleColor(17, 115, 14, 26, "Falu Red (brighter 2)"));
        colors.add(new VehicleColor(18, 123, 10, 42, "Monarch (brighter)"));
        colors.add(new VehicleColor(19, 159, 157, 148, "Dawn"));
        colors.add(new VehicleColor(20, 59, 78, 120, "East Bay"));
        colors.add(new VehicleColor(21, 115, 46, 62, "Tawney Port (more red)"));
        colors.add(new VehicleColor(22, 105, 30, 59, "Wine Berry"));
        colors.add(new VehicleColor(23, 150, 145, 140, "Natural Gray"));
        colors.add(new VehicleColor(24, 81, 84, 89, "Abbey (darker)"));
        colors.add(new VehicleColor(25, 63, 62, 69, "Ship Gray"));
        colors.add(new VehicleColor(26, 165, 169, 167, "Edward"));
        colors.add(new VehicleColor(27, 99, 92, 90, "Dorado"));
        colors.add(new VehicleColor(28, 61, 74, 104, "Fiord"));
        colors.add(new VehicleColor(29, 151, 149, 146, "Dawn"));
        colors.add(new VehicleColor(30, 66, 31, 33, "Bulgarian Rose"));
        colors.add(new VehicleColor(31, 95, 39, 43, "Buccaneer"));
        colors.add(new VehicleColor(32, 132, 148, 171, "Bali Hai"));
        colors.add(new VehicleColor(33, 118, 123, 124, "Sirocco"));
        colors.add(new VehicleColor(34, 100, 100, 100, "Dove Gray"));
        colors.add(new VehicleColor(35, 90, 87, 82, "Chicago"));
        colors.add(new VehicleColor(36, 37, 37, 39, "Jaguar"));
        colors.add(new VehicleColor(37, 45, 58, 53, "Aztec"));
        colors.add(new VehicleColor(38, 147, 163, 150, "Mantle"));
        colors.add(new VehicleColor(39, 109, 122, 136, "Pale Sky"));
        colors.add(new VehicleColor(40, 34, 25, 24, "Wood Bark"));
        colors.add(new VehicleColor(41, 111, 103, 95, "Flint"));
        colors.add(new VehicleColor(42, 124, 28, 42, "Merlot (brighter)"));
        colors.add(new VehicleColor(43, 95, 10, 21, "Falu Red"));
        colors.add(new VehicleColor(44, 25, 56, 38, "Deep Fir"));
        colors.add(new VehicleColor(45, 93, 27, 32, "Pohutukawa"));
        colors.add(new VehicleColor(46, 157, 152, 114, "Gurkha"));
        colors.add(new VehicleColor(47, 122, 117, 96, "Pablo"));
        colors.add(new VehicleColor(48, 152, 149, 134, "Lemon Grass"));
        colors.add(new VehicleColor(49, 173, 176, 176, "Edward"));
        colors.add(new VehicleColor(50, 132, 137, 136, "Gunsmoke"));
        colors.add(new VehicleColor(51, 48, 79, 69, "Stromboli"));
        colors.add(new VehicleColor(52, 77, 98, 104, "Cutty Sark"));
        colors.add(new VehicleColor(53, 22, 34, 72, "Regal Blue"));
        colors.add(new VehicleColor(54, 39, 47, 75, "Pickled Bluewood"));
        colors.add(new VehicleColor(55, 125, 98, 86, "Roman Coffee"));
        colors.add(new VehicleColor(56, 158, 164, 171, "Gray Chateau"));
        colors.add(new VehicleColor(57, 156, 141, 113, "Pale Oyster"));
        colors.add(new VehicleColor(58, 109, 24, 34, "Persian Plum"));
        colors.add(new VehicleColor(59, 78, 104, 129, "Blue Bayoux"));
        colors.add(new VehicleColor(60, 156, 156, 152, "Star Dust"));
        colors.add(new VehicleColor(61, 145, 115, 71, "Cape Palliser"));
        colors.add(new VehicleColor(62, 102, 28, 38, "Persian Plum (darker)"));
        colors.add(new VehicleColor(63, 148, 157, 159, "Oslo Gray"));
        colors.add(new VehicleColor(64, 164, 167, 165, "Edward"));
        colors.add(new VehicleColor(65, 142, 140, 70, "Sycamore"));
        colors.add(new VehicleColor(66, 52, 26, 30, "Temptress"));
        colors.add(new VehicleColor(67, 106, 122, 140, "Slate Gray"));
        colors.add(new VehicleColor(68, 170, 173, 142, "Locust"));
        colors.add(new VehicleColor(69, 171, 152, 143, "Del Rio"));
        colors.add(new VehicleColor(70, 133, 31, 46, "Old Brick"));
        colors.add(new VehicleColor(71, 111, 130, 151, "Lynch"));
        colors.add(new VehicleColor(72, 88, 88, 83, "Fuscous Gray"));
        colors.add(new VehicleColor(73, 154, 167, 144, "Sage"));
        colors.add(new VehicleColor(74, 96, 26, 35, "Celtic"));
        colors.add(new VehicleColor(75, 32, 32, 44, "Black Russian"));
        colors.add(new VehicleColor(76, 164, 160, 150, "Dawn"));
        colors.add(new VehicleColor(77, 170, 157, 132, "Hillary"));
        colors.add(new VehicleColor(78, 120, 34, 43, "Crown Of Thorns (brighter)"));
        colors.add(new VehicleColor(79, 14, 49, 109, "Madison"));
        colors.add(new VehicleColor(80, 114, 42, 63, "Tawney Port (brighter)"));
        colors.add(new VehicleColor(81, 123, 113, 94, "Pablo"));
        colors.add(new VehicleColor(82, 116, 29, 40, "Crown Of Thorns"));
        colors.add(new VehicleColor(83, 30, 46, 50, "Dark Pearl"));
        colors.add(new VehicleColor(84, 77, 50, 47, "Rock"));
        colors.add(new VehicleColor(85, 124, 27, 68, "Claret"));
        colors.add(new VehicleColor(86, 46, 91, 32, "Parsley"));
        colors.add(new VehicleColor(87, 57, 90, 131, "Chambray"));
        colors.add(new VehicleColor(88, 109, 40, 55, "Tawny Port"));
        colors.add(new VehicleColor(89, 167, 162, 143, "Gray Olive"));
        colors.add(new VehicleColor(90, 175, 177, 177, "Edward"));
        colors.add(new VehicleColor(91, 54, 65, 85, "Oxford Blue"));
        colors.add(new VehicleColor(92, 109, 108, 110, "Salt Box"));
        colors.add(new VehicleColor(93, 15, 106, 137, "Surfie Green"));
        colors.add(new VehicleColor(94, 32, 75, 107, "Cello"));
        colors.add(new VehicleColor(95, 43, 62, 87, "Rhino"));
        colors.add(new VehicleColor(96, 155, 159, 157, "Pewter"));
        colors.add(new VehicleColor(97, 108, 132, 149, "Slate Gray"));
        colors.add(new VehicleColor(98, 77, 93, 96, "River Bed"));
        colors.add(new VehicleColor(99, 174, 155, 127, "Sandrift"));
        colors.add(new VehicleColor(100, 64, 108, 143, "Ming"));
        colors.add(new VehicleColor(101, 31, 37, 59, "Midnight Express"));
        colors.add(new VehicleColor(102, 171, 146, 118, "Sandrift"));
        colors.add(new VehicleColor(103, 19, 69, 115, "Chathams Blue"));
        colors.add(new VehicleColor(104, 150, 129, 108, "Arrowtown"));
        colors.add(new VehicleColor(105, 100, 104, 106, "Nevada"));
        colors.add(new VehicleColor(106, 16, 80, 130, "Chathams Blue (brighter)"));
        colors.add(new VehicleColor(107, 161, 153, 131, "Tallow"));
        colors.add(new VehicleColor(108, 56, 86, 148, "Chambray"));
        colors.add(new VehicleColor(109, 82, 86, 97, "Trout"));
        colors.add(new VehicleColor(110, 127, 105, 86, "Shadow"));
        colors.add(new VehicleColor(111, 140, 146, 154, "Oslo Gray"));
        colors.add(new VehicleColor(112, 89, 110, 135, "Blue Bayoux"));
        colors.add(new VehicleColor(113, 71, 53, 50, "Woody Brown"));
        colors.add(new VehicleColor(114, 68, 98, 79, "Mineral Green"));
        colors.add(new VehicleColor(115, 115, 10, 39, "Monarch"));
        colors.add(new VehicleColor(116, 34, 52, 87, "Cloud Burst"));
        colors.add(new VehicleColor(117, 100, 13, 27, "Falu Red (more red)"));
        colors.add(new VehicleColor(118, 163, 173, 198, "Cadet Blue"));
        colors.add(new VehicleColor(119, 105, 88, 83, "Dorado"));
        colors.add(new VehicleColor(120, 155, 139, 128, "Pale Oyster"));
        colors.add(new VehicleColor(121, 98, 11, 28, "Falu Red (brighter 1)"));
        colors.add(new VehicleColor(122, 91, 93, 94, "Abbey (brighter)"));
        colors.add(new VehicleColor(123, 98, 68, 40, "Irish Coffee"));
        colors.add(new VehicleColor(124, 115, 24, 39, "Merlot"));
        colors.add(new VehicleColor(125, 27, 55, 109, "Biscay"));
        colors.add(new VehicleColor(126, 236, 106, 174, "Deep Blush"));

        colors = Collections.unmodifiableList(colors);
    }


    public static List<VehicleColor> getColors() {
        return colors;
    }

    public static VehicleColor getColor(int id) {
        return colors.stream().filter(color -> color.id == id).findFirst().get();
    }
}

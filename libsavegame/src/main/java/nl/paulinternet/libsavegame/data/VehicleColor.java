package nl.paulinternet.libsavegame.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        colors.add(new VehicleColor(3, 132, 4, 16, "Cherry Red"));
        colors.add(new VehicleColor(4, 38, 55, 57, "Midnight Blue"));
        colors.add(new VehicleColor(5, 134, 68, 110, "Temple Curtain Purple"));
        colors.add(new VehicleColor(6, 215, 142, 16, "Taxi Yellow"));
        colors.add(new VehicleColor(7, 76, 117, 183, "Striking Blue"));
        colors.add(new VehicleColor(8, 189, 190, 198, "Light Blue Gray"));
        colors.add(new VehicleColor(9, 94, 112, 114, "Hoods"));
        colors.add(new VehicleColor(10, 70, 89, 122, "Saxony Blue Poly"));
        colors.add(new VehicleColor(11, 101, 106, 121, "Concord Blue Poly"));
        colors.add(new VehicleColor(12, 93, 126, 141, "Jasper Green Poly"));
        colors.add(new VehicleColor(13, 88, 89, 90, "Pewter Gray Poly"));
        colors.add(new VehicleColor(14, 214, 218, 214, "Frost White"));
        colors.add(new VehicleColor(15, 156, 161, 163, "Silver Stone Poly"));
        colors.add(new VehicleColor(16, 51, 95, 63, "Rio Red"));
        colors.add(new VehicleColor(17, 115, 14, 26, "Torino Red Pearl"));
        colors.add(new VehicleColor(18, 123, 10, 42, "Formula Red"));
        colors.add(new VehicleColor(19, 159, 157, 148, "Honey Beige Poly"));
        colors.add(new VehicleColor(20, 59, 78, 120, "Mariner Blue"));
        colors.add(new VehicleColor(21, 115, 46, 62, "Blaze Red"));
        colors.add(new VehicleColor(22, 105, 30, 59, "Classic Red"));
        colors.add(new VehicleColor(23, 150, 145, 140, "Winning Silver Poly"));
        colors.add(new VehicleColor(24, 81, 84, 89, "Steel Gray Poly"));
        colors.add(new VehicleColor(25, 63, 62, 69, "Shadow Silver Poly"));
        colors.add(new VehicleColor(26, 165, 169, 167, "Silver Stone Poly (brighter)"));
        colors.add(new VehicleColor(27, 99, 92, 90, "Warm Grey Mica"));
        colors.add(new VehicleColor(28, 61, 74, 104, "Harbor Blue Poly"));
        colors.add(new VehicleColor(29, 151, 149, 146, "Porcelain Silver Poly"));
        colors.add(new VehicleColor(30, 66, 31, 33, "Mellow Burgundy"));
        colors.add(new VehicleColor(31, 95, 39, 43, "Graceful Red Mica"));
        colors.add(new VehicleColor(32, 132, 148, 171, "Currant Blue Poly"));
        colors.add(new VehicleColor(33, 118, 123, 124, "Gray Poly"));
        colors.add(new VehicleColor(34, 100, 100, 100, "Arctic White"));
        colors.add(new VehicleColor(35, 90, 87, 82, "Anthracite Gray Poly"));
        colors.add(new VehicleColor(36, 37, 37, 39, "Black Poly"));
        colors.add(new VehicleColor(37, 45, 58, 53, "Dark Green Poly"));
        colors.add(new VehicleColor(38, 147, 163, 150, "Seafoam Poly"));
        colors.add(new VehicleColor(39, 109, 122, 136, "Diamond Blue Poly"));
        colors.add(new VehicleColor(40, 34, 25, 24, "Biston Brown Poly"));
        colors.add(new VehicleColor(41, 111, 103, 95, "Desert Taupe Poly"));
        colors.add(new VehicleColor(42, 124, 28, 42, "Garnet Red Poly"));
        colors.add(new VehicleColor(43, 95, 10, 21, "Desert Red"));
        colors.add(new VehicleColor(44, 25, 56, 38, "Green (darker)"));
        colors.add(new VehicleColor(45, 93, 27, 32, "Cabernet Red Poly"));
        colors.add(new VehicleColor(46, 157, 152, 114, "Light Ivory"));
        colors.add(new VehicleColor(47, 122, 117, 96, "Pueblo Beige"));
        colors.add(new VehicleColor(48, 152, 149, 134, "Smoke Silver Poly"));
        colors.add(new VehicleColor(49, 173, 176, 176, "Astra Silver Poly"));
        colors.add(new VehicleColor(50, 132, 137, 136, "Ascot Gray"));
        colors.add(new VehicleColor(51, 48, 79, 69, "Agate Green"));
        colors.add(new VehicleColor(52, 77, 98, 104, "Petrol Blue Green Poly"));
        colors.add(new VehicleColor(53, 22, 34, 72, "Surf Blue"));
        colors.add(new VehicleColor(54, 39, 47, 75, "Nautical Blue Poly"));
        colors.add(new VehicleColor(55, 125, 98, 86, "Woodrose Poly"));
        colors.add(new VehicleColor(56, 158, 164, 171, "Crystal Blue Poly"));
        colors.add(new VehicleColor(57, 156, 141, 113, "Bisque Frost Poly"));
        colors.add(new VehicleColor(58, 109, 24, 34, "Currant Red Solid"));
        colors.add(new VehicleColor(59, 78, 104, 129, "Light Crystal Blue Poly"));
        colors.add(new VehicleColor(60, 156, 156, 152, "Light Titanium Poly"));
        colors.add(new VehicleColor(61, 145, 115, 71, "Race Yellow Solid"));
        colors.add(new VehicleColor(62, 102, 28, 38, "Bright Currant Red Poly"));
        colors.add(new VehicleColor(63, 148, 157, 159, "Clear Crystal Blue Frost Poly"));
        colors.add(new VehicleColor(64, 164, 167, 165, "Silver Poly"));
        colors.add(new VehicleColor(65, 142, 140, 70, "Pastel Alabaster"));
        colors.add(new VehicleColor(66, 52, 26, 30, "Medium Currant Red Poly"));
        colors.add(new VehicleColor(67, 106, 122, 140, "Medium Regatta Blue Poly"));
        colors.add(new VehicleColor(68, 170, 173, 142, "Oxford White Solid"));
        colors.add(new VehicleColor(69, 171, 152, 143, "Alabaster Solid"));
        colors.add(new VehicleColor(70, 133, 31, 46, "Electric Current Red Poly"));
        colors.add(new VehicleColor(71, 111, 130, 151, "Spinnaker Blue Solid"));
        colors.add(new VehicleColor(72, 88, 88, 83, "Dark Titanium Poly"));
        colors.add(new VehicleColor(73, 154, 167, 144, "Pastel Alabaster Solid"));
        colors.add(new VehicleColor(74, 96, 26, 35, "Medium Cabernet Solid"));
        colors.add(new VehicleColor(75, 32, 32, 44, "Twilight Blue Poly"));
        colors.add(new VehicleColor(76, 164, 160, 150, "Titanium Frost Poly"));
        colors.add(new VehicleColor(77, 170, 157, 132, "Sandalwood Frost Poly"));
        colors.add(new VehicleColor(78, 120, 34, 43, "Wild Strawberry Poly"));
        colors.add(new VehicleColor(79, 14, 49, 109, "Ultra Blue Poly"));
        colors.add(new VehicleColor(80, 114, 42, 63, "Vermilion Solid"));
        colors.add(new VehicleColor(81, 123, 113, 94, "Medium Sandalwood Poly"));
        colors.add(new VehicleColor(82, 116, 29, 40, "Medium Red Solid"));
        colors.add(new VehicleColor(83, 30, 46, 50, "Deep Jewel Green"));
        colors.add(new VehicleColor(84, 77, 50, 47, "Medium Woodrose Poly"));
        colors.add(new VehicleColor(85, 124, 27, 68, "Vermilion Solid (brighter)"));
        colors.add(new VehicleColor(86, 46, 91, 32, "Green"));
        colors.add(new VehicleColor(87, 57, 90, 131, "Bright Blue Poly"));
        colors.add(new VehicleColor(88, 109, 40, 55, "Bright Red"));
        colors.add(new VehicleColor(89, 167, 162, 143, "Light Champagne Poly"));
        colors.add(new VehicleColor(90, 175, 177, 177, "Silver Poly (brighter)"));
        colors.add(new VehicleColor(91, 54, 65, 85, "Steel Blue Poly"));
        colors.add(new VehicleColor(92, 109, 108, 110, "Medium Gray Poly"));
        colors.add(new VehicleColor(93, 15, 106, 137, "Arctic Pearl"));
        colors.add(new VehicleColor(94, 32, 75, 107, "Nassau Blue Poly"));
        colors.add(new VehicleColor(95, 43, 62, 87, "Medium Sapphire Blue Poly"));
        colors.add(new VehicleColor(96, 155, 159, 157, "Silver Poly (darker)"));
        colors.add(new VehicleColor(97, 108, 132, 149, "Light Sapphire Blue Poly"));
        colors.add(new VehicleColor(98, 77, 93, 96, "Malachite Poly"));
        colors.add(new VehicleColor(99, 174, 155, 127, "Flax"));
        colors.add(new VehicleColor(100, 64, 108, 143, "Medium Maui Blue Poly"));
        colors.add(new VehicleColor(101, 31, 37, 59, "Dark Sapphire Blue Poly"));
        colors.add(new VehicleColor(102, 171, 146, 118, "Copper Beige"));
        colors.add(new VehicleColor(103, 19, 69, 115, "Bright Blue Poly (darker)"));
        colors.add(new VehicleColor(104, 150, 129, 108, "Medium Flax"));
        colors.add(new VehicleColor(105, 100, 104, 106, "Medium Gray Poly (darker)"));
        colors.add(new VehicleColor(106, 16, 80, 130, "Bright Blue Poly (more intense)"));
        colors.add(new VehicleColor(107, 161, 153, 131, "Light Driftwood Poly"));
        colors.add(new VehicleColor(108, 56, 86, 148, "Blue"));
        colors.add(new VehicleColor(109, 82, 86, 97, "Steel Gray Poly (brighter)"));
        colors.add(new VehicleColor(110, 127, 105, 86, "Light Beechwood Poly"));
        colors.add(new VehicleColor(111, 140, 146, 154, "Slate Gray"));
        colors.add(new VehicleColor(112, 89, 110, 135, "Light Sapphire Blue Poly (darker)"));
        colors.add(new VehicleColor(113, 71, 53, 50, "Dark Beechwood Poly"));
        colors.add(new VehicleColor(114, 68, 98, 79, "Torch Red"));
        colors.add(new VehicleColor(115, 115, 10, 39, "Bright Red (more red)"));
        colors.add(new VehicleColor(116, 34, 52, 87, "Medium Sapphire Blue Firemist"));
        colors.add(new VehicleColor(117, 100, 13, 27, "Medium Garnet Red Poly"));
        colors.add(new VehicleColor(118, 163, 173, 198, "White Diamond Pearl"));
        colors.add(new VehicleColor(119, 105, 88, 83, "Dark Sable Poly"));
        colors.add(new VehicleColor(120, 155, 139, 128, "Antelope Beige"));
        colors.add(new VehicleColor(121, 98, 11, 28, "Brilliant Red Poly"));
        colors.add(new VehicleColor(122, 91, 93, 94, "Gun metal Poly"));
        colors.add(new VehicleColor(123, 98, 68, 40, "Medium Beechwood Poly"));
        colors.add(new VehicleColor(124, 115, 24, 39, "Brilliant Red Poly (brighter)"));
        colors.add(new VehicleColor(125, 27, 55, 109, "Bright Blue Poly (even darker)"));
        colors.add(new VehicleColor(126, 236, 106, 174, "Pink"));

        colors = Collections.unmodifiableList(colors);
    }

    public static List<VehicleColor> getColors() {
        return colors;
    }

    public static VehicleColor getColor(int id) {
        Optional<VehicleColor> result = colors.stream().filter(color -> color.id == id).findFirst();
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Invalid colorId: '" + id + "'");
        }
    }

    public static class ColorPair {
        private final Integer firstColor, secondColor;

        ColorPair(Integer firstColor, Integer secondColor)
        {
            this.firstColor = firstColor;
            this.secondColor = secondColor;
        }

        public Integer getFirstColor()
        {
            return firstColor;
        }

        public VehicleColor getFirstVehicleColor() {
            final VehicleColor[] result = { null };
            colors.forEach(c -> {
                if (firstColor.equals(c.id)) {
                    result[0] = c;
                }
            });
            return result[0];
        }

        public Integer getSecondColor()
        {
            return secondColor;
        }

        public VehicleColor getSecondVehicleColor() {
            final VehicleColor[] result = { null };
            colors.forEach(c -> {
                if (secondColor.equals(c.id)) {
                    result[0] = c;
                }
            });
            return result[0];
        }
    }
}

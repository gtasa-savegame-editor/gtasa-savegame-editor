package nl.paulinternet.libsavegame.data;

public class Clothes
{
	public static Cloth getCloth (int type, int textureId) {
		for (Cloth[] clothArr : clothes[type]) {
			for (Cloth cloth : clothArr) {
				if (cloth.getTextureId() == textureId) return cloth;
			}
		}
		return null;
	}
	
	public static Cloth getTattoo (int type, int textureId) {
		for (Cloth tattoo : tattoos[type]) {
			if (tattoo.getTextureId() == textureId) return tattoo;
		}
		return null;
	}
	
	public static void setAllPurchased (boolean value) {
		for (Cloth[][] clothArr1 : Clothes.clothes) {
			for (Cloth[] clothArr2 : clothArr1) {
				for (Cloth cloth : clothArr2) {
					cloth.setPurchased(value);
				}
			}
		}
	}
	
	public static final Cloth[][][] clothes = new Cloth[][][] {{{
		new Cloth("Torso", "torso", null, 5, 20, 0, 0)
		},{
		new Cloth("White Tank", "vest", "vest", 2, 3, 10, 0xb5),
		new Cloth("Black Tank", "vest", "vestblack", 2, 3, 10, 0xb4),
		new Cloth("Green Hoody", "hoodya", "hoodyagreen", 15, 5, 45, 0xda),
		new Cloth("White T-Shirt", "tshirt", "tshirtwhite", 0, 0, 15, 0xb7),
		new Cloth("L.S. T-Shirt", "tshirt", "tshirtilovels", 0, 0, 10, 0xb8),
		new Cloth("Sharps T-Shirt", "tshirt", "tshirtblunts", 2, 0, 20, 0xb9),
		new Cloth("Green Shirt", "shirtb", "shirtbgang", 15, 3, 40, 0xdf),
		new Cloth("Checkered Shirt", "shirtb", "shirtbcheck", 3, 0, 20, 0xbb),
		new Cloth("Combat Jacket", "field", "field", 10, 0, 30, 0xbc),
		new Cloth("Eris T-Shirt", "tshirt", "tshirterisyell", 15, 7, 35, 0xbd),
		new Cloth("Eris T-Shirt2", "tshirt", "tshirterisorn", 5, 7, 35, 0xbe),
		new Cloth("Track Top", "trackytop1", "trackytop2eris", 5, 9, 70, 0xbf)
		},{
		new Cloth("Rimmers Jacket", "bbjack", "bbjackrim", 10, 5, 150, 0xc0),
		new Cloth("R-Star Jacket", "bbjack", "bballjackrstar", 10, 5, 200, 0xc1),
		new Cloth("Dribblers Vest", "baskball", "baskballdrib", 7, 5, 30, 0xc2),
		new Cloth("Saints Shirt", "baskball", "baskballrim", 7, 5, 30, 0xc3),
		new Cloth("69ers T-Shirt", "tshirt", "sixtyniners", 7, 5, 50, 0xc4),
		new Cloth("2 T-Shirt", "tshirt", "tshirtprored", 7, 10, 50, 0xc6),
		new Cloth("2 Black T", "tshirt", "tshirtproblk", 7, 10, 50, 0xc7),
		new Cloth("Bandits Top", "baseball", "bandits", 7, 5, 70, 0xc5),
		new Cloth("Track Top", "trackytop1", "trackytop1pro", 10, 15, 85, 0xc8),
		new Cloth("Leisure Top", "trackytop1", "shellsuit", 0, 0, 30, 0xcb),
		new Cloth("Slappers Top", "sweat", "hockeytop", 5, 2, 150, 0xc9),
		new Cloth("Baseball T", "sleevt", "bbjersey", 10, 5, 80, 0xca)
		},{
		new Cloth("White Heat T", "tshirt", "tshirtheatwht", 5, 0, 35, 0xcc),
		new Cloth("Bobo Ape T", "tshirt", "tshirtbobomonk", 17, 13, 115, 0xcd),
		new Cloth("Red Bobo T", "tshirt", "tshirtbobored", 12, 12, 80, 0xce),
		new Cloth("Base 5 T", "tshirt", "tshirtbase5", 15, 12, 80, 0xcf),
		new Cloth("Suburban T", "tshirt", "tshirtsuburb", 15, 10, 60, 0xd0),
		new Cloth("Mercury Hood", "hoodya", "hoodyamerc", 15, 10, 70, 0xd1),
		new Cloth("Base 5 Hood", "hoodya", "hoodyabase5", 20, 14, 105, 0xd2),
		new Cloth("Rockstar Hood", "hoodya", "hoodyarockstar", 17, 15, 120, 0xd3),
		new Cloth("Vest & T-Shirt", "wcoat", "wcoatblue", 0, 0, 30, 0xd4),
		new Cloth("Grn Windbreaker", "coach", "coach", 15, 5, 30, 0xd5),
		new Cloth("Blk Windbreaker", "coach", "coachsemi", 5, 5, 30, 0xd6),
		new Cloth("Rockstar Sweat", "sweat", "sweatrstar", 10, 10, 60, 0xd7)
		},{
		new Cloth("Blue Hoody", "hoodya", "hoodyablue", 5, 5, 65, 0xd8),
		new Cloth("Black Hoody", "hoodya", "hoodyablack", 5, 5, 65, 0xd9),
		new Cloth("Striped T-Shirt", "tshirt2", "tshirt2horiz", 0, 0, 10, 0xb6),
		new Cloth("Brown Shirt", "sleevt", "sleevtbrown", 5, 10, 40, 0xdb),
		new Cloth("Sky Blue Shirt", "shirta", "shirtablue", 7, 10, 40, 0xdc),
		new Cloth("Yellow Shirt", "shirta", "shirtayellow", 7, 10, 40, 0xdd),
		new Cloth("Gray Shirt", "shirta", "shirtagrey", 7, 10, 40, 0xde),
		new Cloth("Plaid Shirt", "shirtb", "shirtbplaid", 3, 0, 20, 0xba),
		new Cloth("Cream Logo T", "tshirt", "tshirtzipcrm", 0, 5, 35, 0xe0),
		new Cloth("Gray Logo T", "tshirt", "tshirtzipgry", 0, 5, 35, 0xe1),
		new Cloth("Jean Jacket", "denim", "denimfade", 10, 20, 90, 0xe2),
		new Cloth("Bowling shirt", "hawaii", "bowling", 7, 13, 70, 0xe3)
		},{
		new Cloth("Hooded Jacket", "hoodjack", "hoodjackbeige", 17, 15, 75, 0xe4),
		new Cloth("Gray Jacket", "suit1", "suit1grey", 20, 25, 1620, 0xe9),
		new Cloth("Black Jacket", "suit1", "suit1blk", 10, 20, 300, 0xea),
		new Cloth("Biker Jacket", "leather", "leather", 17, 15, 320, 0xeb),
		new Cloth("Chore Coat", "painter", "painter", 15, 10, 320, 0xec),
		new Cloth("Hawaiian Shirt", "hawaii", "hawaiiwht", 7, 13, 200, 0xed),
		new Cloth("Blue Hawaiian", "hawaii", "hawaiired", 7, 13, 300, 0xee),
		new Cloth("Sports Jacket", "trackytop1", "sportjack", 15, 17, 450, 0xef),
		new Cloth("Madd Tagg T", "tshirt", "tshirtmaddgrey", 5, 5, 30, 0xe7),
		new Cloth("Green Tagg T", "tshirt", "tshirtmaddgrn", 5, 5, 30, 0xe8),
		new Cloth("Loc-Down T", "tshirt", "tshirtlocgrey", 5, 5, 200, 0xe6),
		new Cloth("Loc-Down Vest", "baskball", "baskballloc", 5, 5, 300, 0xe5)
		},{
		new Cloth("Tweed Jacket", "suit2", "suit2grn", 25, 25, 5500, 0xf3),
		new Cloth("Red Jacket", "suit1", "suit1red", 23, 25, 4000, 0xf0),
		new Cloth("Blue Jacket", "suit1", "suit1blue", 22, 25, 3000, 0xf1),
		new Cloth("Yellow Jacket", "suit1", "suit1yellow", 25, 25, 6000, 0xf2),
		new Cloth("Tuxedo", "suit2", "tuxedo", 20, 25, 7000, 0xf4),
		new Cloth("Green Jacket", "suit1", "suit1gang", 25, 15, 5500, 0xf5),
		new Cloth("Letterman Top", "sleevt", "letter", 15, 5, 1525, 0xf6)
		}},{{
		new Cloth("Cesar", "head", "player_face", 5, 15, 50, 0),
		new Cloth("Blonde Hair", "head", "hairblond", 0, 15, 50, 0),
		new Cloth("Red Hair", "head", "hairred", 10, 0, 200, 0),
		new Cloth("Blue Hair", "head", "hairblue", 10, 0, 200, 0),
		new Cloth("Green Hair", "head", "hairgreen", 20, 0, 200, 0),
		new Cloth("Pink Hair", "head", "hairpink", 0, 0, 200, 0),
		new Cloth("Bald Head", "head", "bald", 10, 20, 10, 0),
		new Cloth("Bald & Beard", "head", "baldbeard", 15, 15, 50, 0),
		new Cloth("Bald & stash", "head", "baldtash", 15, 15, 25, 0),
		new Cloth("Bald & Goatee", "head", "baldgoatee", 15, 10, 35, 0),
		new Cloth("High Fade", "head", "highfade", 20, 20, 150, 0),
		new Cloth("High Afro", "highafro", "highafro", 15, 0, 150, 0),
		new Cloth("Wedge", "wedge", "wedge", 20, 20, 150, 0),
		new Cloth("Slope", "slope", "slope", 20, 10, 200, 0),
		new Cloth("Jheri Curl", "jheri", "jhericurl", 5, 25, 350, 0),
		new Cloth("Cornrow", "cornrows", "cornrows", 30, 30, 500, 0),
		new Cloth("Blonde Cornrow", "cornrows", "cornrowsb", 30, 30, 550, 0),
		new Cloth("Detail Cut", "tramline", "tramline", 25, 30, 400, 0),
		new Cloth("Groove Cut", "groovecut", "groovecut", 30, 30, 500, 0),
		new Cloth("Mohawk", "mohawk", "mohawk", 20, 10, 200, 0),
		new Cloth("Blonde Mohawk", "mohawk", "mohawkblond", 10, 15, 250, 0),
		new Cloth("Pink Mohawk", "mohawk", "mohawkpink", 0, 0, 400, 0),
		new Cloth("Mohawk & Beard", "mohawk", "mohawkbeard", 10, 0, 250, 0),
		new Cloth("Afro", "afro", "afro", 15, 10, 150, 0),
		new Cloth("Afro & stash", "afro", "afrotash", 20, 5, 200, 0),
		new Cloth("Afro & Beard", "afro", "afrobeard", 20, 5, 250, 0),
		new Cloth("Blonde Afro", "afro", "afroblond", 15, 15, 300, 0),
		new Cloth("FlatTop", "flattop", "flattop", 25, 25, 500, 0),
		new Cloth("Elvis Hair", "elvishair", "elvishair", 0, 0, 1000, 0),
		new Cloth("Cesar & Beard", "head", "beard", 10, 10, 80, 0),
		new Cloth("Cesar & stash", "head", "tash", 10, 10, 50, 0),
		new Cloth("Cesar & Goatee", "head", "goatee", 10, 0, 100, 0),
		new Cloth("Afro & Goatee", "afro", "afrogoatee", 20, 0, 300, 0)
		}},{{
		new Cloth("White Boxers", "legs", "player_legs", 0, 0, 0, 0)
		},{
		new Cloth("Black Boxers", "legs", "legsblack", 1, 1, 10, 0xfe),
		new Cloth("Heart Boxers", "legs", "legsheart", 1, 2, 12, 0xff),
		new Cloth("Woodland Camo", "worktr", "worktrcamogrn", 6, 1, 55, 0xf7),
		new Cloth("Urban Camo", "worktr", "worktrcamogry", 2, 1, 55, 0xf8),
		new Cloth("Gray Pants", "worktr", "worktrgrey", 0, 1, 55, 0xf9),
		new Cloth("Olive Pants", "worktr", "worktrkhaki", 5, 1, 55, 0xfa),
		new Cloth("Sweat Pants", "tracktr", "tracktr", 3, 2, 60, 0xfb),
		new Cloth("Blue Jeans", "jeans", "jeansdenim", 0, 0, 50, 0xfd),
		new Cloth("Track Pants", "tracktr", "tracktreris", 5, 5, 70, 0xfc),
		new Cloth("Beige Pants", "chinosb", "biegetr", 1, 0, 35, 0x100),
		new Cloth("Green Jeans", "jeans", "denimsgang", 15, 5, 60, 0x110),
		new Cloth("Grn Track Pants", "tracktr", "tracktrgang", 9, 2, 40, 0x104)
		},{
		new Cloth("Track Pants", "tracktr", "tracktrpro", 5, 5, 100, 0x101),
		new Cloth("Blk Track Pants", "tracktr", "tracktrwhstr", 5, 5, 120, 0x102),
		new Cloth("Blu Track Pants", "tracktr", "tracktrblue", 5, 5, 140, 0x103),
		new Cloth("Ball Shorts", "boxingshort", "bbshortwht", 5, 1, 60, 0x105),
		new Cloth("Boxing Shorts", "boxingshort", "boxshort", 5, 1, 60, 0x106),
		new Cloth("Dribbler Shorts", "boxingshort", "bbshortred", 5, 1, 60, 0x107),
		new Cloth("Leisure Pants", "tracktr", "shellsuittr", 0, 0, 50, 0x108)
		},{
		new Cloth("Gray Shorts", "shorts", "shortsgrey", 0, 0, 30, 0x109),
		new Cloth("Olive Shorts", "shorts", "shortskhaki", 5, 0, 30, 0x10a),
		new Cloth("Gray Chonglers", "chonger", "chongergrey", 2, 1, 50, 0x10b),
		new Cloth("Green Chonglers", "chonger", "chongergang", 6, 0, 50, 0x10c),
		new Cloth("Red Chonglers", "chonger", "chongerred", 6, 2, 50, 0x10d),
		new Cloth("Blue Chonglers", "chonger", "chongerblue", 2, 2, 50, 0x10e),
		new Cloth("Green Shorts", "shorts", "shortsgang", 0, 5, 30, 0x10f),
		new Cloth("Red Jeans", "jeans", "denimsred", 0, 0, 60, 0x111)
		},{
		new Cloth("Beige Khakis", "chinosb", "chinosbiege", 10, 7, 150, 0x112),
		new Cloth("Olive Khakis", "chinosb", "chinoskhaki", 10, 7, 150, 0x113),
		new Cloth("Black Khakis", "chinosb", "chinosblack", 8, 8, 150, 0x116),
		new Cloth("Blue Khakis", "chinosb", "chinosblue", 8, 7, 150, 0x117),
		new Cloth("Beige Shorts", "shorts", "cutoffchinos", 6, 3, 80, 0x114),
		new Cloth("Blue Shorts", "shorts", "cutoffchinosblue", 6, 3, 80, 0x115)
		},{
		new Cloth("Leather Pants", "leathertr", "leathertr", 8, 8, 875, 0x118),
		new Cloth("Leather Chaps", "leathertr", "leathertrchaps", 0, 0, 80, 0x119),
		new Cloth("Gray Pants", "suit1tr", "suit1trgrey", 8, 10, 800, 0x11a),
		new Cloth("Black Pants", "suit1tr", "suit1trblk", 8, 10, 800, 0x11b),
		new Cloth("Jean Shorts", "shorts", "cutoffdenims", 9, 7, 1000, 0x11c)
		},{
		new Cloth("Red Pants", "suit1tr", "suit1trred", 9, 10, 2000, 0x11d),
		new Cloth("Blue Pants", "suit1tr", "suit1trblue", 9, 10, 2500, 0x11e),
		new Cloth("Yellow Pants", "suit1tr", "suit1tryellow", 10, 10, 4000, 0x11f),
		new Cloth("Tweed Pants", "suit1tr", "suit1trgreen", 10, 10, 3000, 0x120),
		new Cloth("Tuxedo Pants", "suit1tr", "suit1trblk2", 9, 10, 3000, 0x121),
		new Cloth("Green Pants", "suit1tr", "suit1trgang", 10, 6, 1500, 0x122)
		}},{{
		new Cloth("Feet", "feet", "foot", 0, 0, 0, 0)
		},{
		new Cloth("Sandals", "flipflop", "sandal", 0, 0, 15, 0x12a),
		new Cloth("Sandal & Socks", "flipflop", "sandalsock", 0, 0, 20, 0x12b),
		new Cloth("Flip-Flops", "flipflop", "flipflop", 0, 0, 15, 0x12c),
		new Cloth("Cowboy Boots", "biker", "cowboyboot2", 1, 2, 100, 0x124),
		new Cloth("Hi-Top Kicks", "bask1", "bask2semi", 2, 1, 50, 0x125),
		new Cloth("Hi-Top Sneaks", "bask1", "bask1eris", 3, 2, 100, 0x126),
		new Cloth("Green Low-Tops", "sneaker", "sneakerbincgang", 3, 1, 50, 0x127),
		new Cloth("Blue Low-Tops", "sneaker", "sneakerbincblu", 1, 1, 50, 0x128),
		new Cloth("Black Low-Tops", "sneaker", "sneakerbincblk", 1, 1, 50, 0x129)
		},{
		new Cloth("Mid-Top Sneaker", "bask1", "hitop", 5, 3, 115, 0x12d),
		new Cloth("Black Hi-Tops", "conv", "convproblk", 3, 2, 70, 0x12e),
		new Cloth("Blue Hi-Tops", "conv", "convproblu", 3, 2, 65, 0x12f),
		new Cloth("Green Hi-Tops", "conv", "convprogrn", 5, 2, 60, 0x130),
		new Cloth("Red Sneakers", "sneaker", "sneakerprored", 2, 3, 80, 0x131),
		new Cloth("Blue Sneakers", "sneaker", "sneakerproblu", 2, 2, 75, 0x132),
		new Cloth("White Sneakers", "sneaker", "sneakerprowht", 2, 2, 70, 0x133),
		new Cloth("White Mid-Tops", "bask1", "bask1prowht", 4, 2, 70, 0x134),
		new Cloth("Black Mid-Tops", "bask1", "bask1problk", 4, 2, 70, 0x135),
		new Cloth("Boxing Shoes", "biker", "boxingshoe", 0, 0, 70, 0x136)
		},{
		new Cloth("Black Hi-Tops", "conv", "convheatblk", 2, 1, 55, 0x137),
		new Cloth("Red Hi-Tops", "conv", "convheatred", 2, 1, 55, 0x138),
		new Cloth("Orange Hi-Tops", "conv", "convheatorn", 2, 1, 50, 0x139),
		new Cloth("White Low-Tops", "sneaker", "sneakerheatwht", 2, 2, 70, 0x13a),
		new Cloth("Gray Low-Tops", "sneaker", "sneakerheatgry", 2, 1, 65, 0x13b),
		new Cloth("Black Low-Tops", "sneaker", "sneakerheatblk", 2, 2, 65, 0x13c),
		new Cloth("White Hi-Tops", "bask1", "bask2heatwht", 3, 2, 80, 0x13d),
		new Cloth("Strap Sneakers", "bask1", "bask2heatband", 3, 2, 80, 0x13e)
		},{
		new Cloth("Gray Boots", "bask1", "timbergrey", 5, 5, 125, 0x13f),
		new Cloth("Red Boots", "bask1", "timberred", 5, 5, 135, 0x140),
		new Cloth("Brown Boots", "bask1", "timberfawn", 5, 4, 115, 0x141),
		new Cloth("Hiking Boots", "bask1", "timberhike", 4, 2, 110, 0x142)
		},{
		new Cloth("Cowboy Boots", "biker", "cowboyboot", 4, 5, 500, 0x143),
		new Cloth("Biker Boots", "biker", "biker", 3, 3, 145, 0x144),
		new Cloth("Snake Skin", "biker", "snakeskin", 5, 5, 1000, 0x145)
		},{
		new Cloth("Black Shoes", "shoe", "shoedressblk", 5, 5, 2500, 0x146),
		new Cloth("Brown Shoes", "shoe", "shoedressbrn", 4, 5, 1100, 0x147),
		new Cloth("Spats", "shoe", "shoespatz", 5, 5, 350, 0x148)
		}},{{
		new Cloth("No", null, null, 0, 0, 0, 0)
		},{
		new Cloth("Dogtags", "neck", "dogtag", 1, 0, 10, 0x149),
		new Cloth("Africa Pendant", "neck", "neckafrica", 1, 1, 12, 0x14a)
		},{
		new Cloth("Stop Watch", "neck", "stopwatch", 0, 0, 20, 0x14b),
		new Cloth("Saints Chain", "neck", "necksaints", 1, 0, 25, 0x14c)
		},{
		new Cloth("Silver Cuban", "neck2", "necksilver", 2, 2, 200, 0x14e),
		new Cloth("L.S. Chain", "neck", "neckls", 2, 0, 50, 0x152)
		},{
		new Cloth("Leaf Chain", "neck", "neckhash", 2, 1, 100, 0x14d),
		new Cloth("Gold Cuban", "neck2", "neckgold", 2, 2, 350, 0x14f)
		},{
		new Cloth("Silver Chain", "neck2", "neckropes", 2, 1, 450, 0x150),
		new Cloth("Gold Chain", "neck2", "neckropeg", 2, 2, 550, 0x151)
		},{
		new Cloth("Cross Chain", "neck", "neckcross", 2, 2, 5000, 0x154),
		new Cloth("Dollar Chain", "neck", "neckdollar", 2, 2, 2000, 0x153)
		}},{{
		new Cloth("No", null, null, 0, 0, 0, 0)
		},{
		new Cloth("Pink Watch", "watch", "watchpink", 0, 0, 15, 0x155),
		new Cloth("Yellow Watch", "watch", "watchyellow", 0, 0, 15, 0x156)
		},{
		new Cloth("Pro-Laps White", "watch", "watchpro", 0, 1, 440, 0x157),
		new Cloth("Pro-Laps Black", "watch", "watchpro2", 1, 1, 700, 0x158)
		},{
		new Cloth("Face Watch", "watch", "watchsub1", 1, 2, 70, 0x159),
		new Cloth("Face Black", "watch", "watchsub2", 2, 2, 120, 0x15a)
		},{
		new Cloth("Zip Blue", "watch", "watchzip1", 2, 2, 100, 0x15b),
		new Cloth("Zip Gold", "watch", "watchzip2", 2, 2, 220, 0x15c)
		},{
		new Cloth("Gold Gnocchi", "watch", "watchgno", 1, 2, 1500, 0x15d),
		new Cloth("Silver Gnocchi", "watch", "watchgno2", 2, 2, 3000, 0x15e)
		},{
		new Cloth("Gold Crowex", "watch", "watchcro", 3, 3, 8000, 0x15f),
		new Cloth("Silver Crowex", "watch", "watchcro2", 3, 3, 5000, 0x160)
		}},{{
		new Cloth("No", null, null, 0, 0, 0, 0)
		},{
		new Cloth("Joke Glasses", "grouchos", "groucho", 0, 0, 10, 0x161),
		new Cloth("Joke Mask", "zorromask", "zorro", 0, 0, 20, 0x162),
		new Cloth("Eyepatch", "eyepatch", "eyepatch", 0, 0, 5, 0x163),
		new Cloth("Red Rag", "bandmask", "bandred3", 1, 0, 50, 0x166),
		new Cloth("Blue Rag", "bandmask", "bandblue3", 1, 0, 50, 0x167),
		new Cloth("Green Rag", "bandmask", "bandgang3", 2, 0, 50, 0x168),
		new Cloth("Black Rag", "bandmask", "bandblack3", 1, 0, 50, 0x169)
		},{
		new Cloth("Aviators", "glasses01", "glasses01dark", 1, 1, 150, 0x16a),
		new Cloth("Sun Glasses", "glasses04", "glasses04dark", 2, 1, 150, 0x16b)
		},{
		new Cloth("Red Tint", "glasses03", "glasses03red", 1, 1, 200, 0x16d),
		new Cloth("Blue Tint", "glasses03", "glasses03blue", 1, 1, 220, 0x16e)
		},{
		new Cloth("Black Shades", "glasses01", "glasses01", 2, 2, 100, 0x164),
		new Cloth("Brown Shades", "glasses04", "glasses04", 2, 2, 150, 0x165)
		},{
		new Cloth("Black Shades", "glasses03", "glasses03dark", 2, 2, 500, 0x16f),
		new Cloth("Green Tint", "glasses03", "glasses03", 2, 2, 400, 0x16c)
		},{
		new Cloth("Black Shades", "glasses03", "glasses05dark", 1, 2, 600, 0x170),
		new Cloth("Black Rim", "glasses03", "glasses05", 2, 2, 800, 0x171)
		}},{{
		new Cloth("No", null, null, 0, 0, 0, 0)
		},{
		new Cloth("Red Rag Back", "bandana", "bandred", 1, 1, 25, 0x172),
		new Cloth("Blue Rag Back", "bandana", "bandblue", 1, 1, 25, 0x173),
		new Cloth("Green Rag Back", "bandana", "bandgang", 3, 1, 25, 0x174),
		new Cloth("Black Rag Back", "bandana", "bandblack", 1, 1, 25, 0x175),
		new Cloth("Red Rag Front", "bandknots", "bandred2", 1, 1, 25, 0x176),
		new Cloth("Blue Rag Front", "bandknots", "bandblue2", 1, 1, 25, 0x177),
		new Cloth("Black Rag Front", "bandknots", "bandblack2", 1, 1, 25, 0x178),
		new Cloth("Green Rag Front", "bandknots", "bandgang2", 3, 1, 25, 0x179),
		new Cloth("Watch Cap", "capknit", "capknitgrn", 2, 0, 15, 0x17a),
		new Cloth("Trucker Hat", "captruck", "captruck", 0, 0, 5, 0x17b),
		new Cloth("Cowboy Hat", "cowboy", "cowboy", 0, 0, 10, 0x17c),
		new Cloth("Leopard Cowboy", "cowboy", "hattiger", 0, 0, 10, 0x17d)
		},{
		new Cloth("Green Cap", "cap", "capgang", 1, 0, 40, 0x182),
		new Cloth("Grn Cap (Back)", "capback", "capgangback", 1, 0, 40, 0x183),
		new Cloth("Grn Cap (Side)", "capside", "capgangside", 1, 0, 40, 0x184),
		new Cloth("Grn Cap (Tilt)", "capovereye", "capgangover", 1, 0, 40, 0x185),
		new Cloth("Grn Cap (Up)", "caprimup", "capgangup", 1, 0, 40, 0x186),
		new Cloth("Boxing Helmet", "boxingcap", "boxingcap", 0, 0, 80, 0x180),
		new Cloth("Hockey Mask", "hockeymask", "hockey", 2, 0, 40, 0x181),
		new Cloth("Fullface Helmet", "helmet", "helmet", 0, 0, 150, 0x17e),
		new Cloth("MotoX Helmet", "moto", "moto", 0, 0, 100, 0x17f),
		new Cloth("Helmut", "bikerhelmet", "bikerhelmet", 1, 0, 100, 0x187)
		},{
		new Cloth("Red Cap", "cap", "capred", 1, 0, 40, 0x188),
		new Cloth("Red Cap (Back)", "capback", "capredback", 1, 0, 40, 0x189),
		new Cloth("Red Cap (Side)", "capside", "capredside", 1, 0, 40, 0x18a),
		new Cloth("Red Cap (Tilt)", "capovereye", "capredover", 1, 0, 40, 0x18b),
		new Cloth("Red Cap (Up)", "caprimup", "capredup", 1, 0, 40, 0x18c),
		new Cloth("Blue Cap", "cap", "capblue", 1, 0, 40, 0x18d),
		new Cloth("Blue Cap (Back)", "capback", "capblueback", 1, 0, 40, 0x18e),
		new Cloth("Blue Cap (Side)", "capside", "capblueside", 1, 0, 40, 0x18f),
		new Cloth("Blue Cap (Tilt)", "capovereye", "capblueover", 1, 0, 40, 0x190),
		new Cloth("Blue Cap (Up)", "caprimup", "capblueup", 1, 0, 40, 0x191),
		new Cloth("Black Skully", "skullycap", "skullyblk", 1, 0, 60, 0x192),
		new Cloth("Green Skully", "skullycap", "skullygrn", 3, 0, 60, 0x193)
		},{
		new Cloth("Black Sun Hat", "hatmanc", "hatmancblk", 2, 2, 20, 0x194),
		new Cloth("Plaid Sun Hat", "hatmanc", "hatmancplaid", 2, 2, 20, 0x195),
		new Cloth("Cap", "cap", "capzip", 1, 0, 40, 0x196),
		new Cloth("Cap (Back)", "capback", "capzipback", 1, 0, 40, 0x197),
		new Cloth("Cap (Side)", "capside", "capzipside", 1, 0, 40, 0x198),
		new Cloth("Cap Tilted", "capovereye", "capzipover", 1, 0, 40, 0x199),
		new Cloth("Cap Rim Up", "caprimup", "capzipup", 1, 0, 40, 0x19a)
		},{
		new Cloth("Red Beret", "beret", "beretred", 1, 2, 900, 0x19b),
		new Cloth("Black Beret", "beret", "beretblk", 1, 2, 900, 0x19c),
		new Cloth("Black Cap", "cap", "capblk", 1, 0, 40, 0x19d),
		new Cloth("Black Cap (Back)", "capback", "capblkback", 1, 0, 40, 0x19e),
		new Cloth("Black Cap (Side)", "capside", "capblkside", 1, 0, 40, 0x19f),
		new Cloth("Black Cap (Tilt)", "capovereye", "capblkover", 1, 0, 40, 0x1a0),
		new Cloth("Black Cap (Up)", "caprimup", "capblkup", 1, 0, 40, 0x1a1)
		},{
		new Cloth("Dark Trilby", "trilby", "trilbydrk", 3, 2, 300, 0x1a2),
		new Cloth("Light Trilby", "trilby", "trilbylght", 3, 2, 300, 0x1a3),
		new Cloth("Black Derby", "bowler", "bowler", 1, 2, 500, 0x1a4),
		new Cloth("Red Derby", "bowler", "bowlerred", 2, 3, 600, 0x1a5),
		new Cloth("Blue Derby", "bowler", "bowlerblue", 2, 2, 600, 0x1a6),
		new Cloth("Yellow Derby", "bowler", "bowleryellow", 2, 2, 700, 0x1a7),
		new Cloth("Green Derby", "bowler", "bowlergang", 3, 2, 550, 0x1a9),
		new Cloth("Gray Boater", "boater", "boater", 3, 2, 800, 0x1a8),
		new Cloth("Black Boater", "boater", "boaterblk", 3, 2, 700, 0x1aa)
		}},{{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Gimp Suit", "gimpleg", "gimpleg", 10, 10, 0, 0x27f0),
		new Cloth("Valet Uniform", "valet", "valet", 0, 0, 0, 0x27f4),
		new Cloth("Rural Clothes", "countrytr", "countrytr", 0, 0, 0, 0x2800),
		new Cloth("Croupier", "valet", "croupier", 0, 15, 0, 0x27f8),
		new Cloth("Cop", "policetr", "policetr", 5, 10, 0, 0x27fc),
		new Cloth("Pimp Suit", "pimptr", "pimptr", 10, 15, 0, 0x280c),
		new Cloth("Racing Suit", "garagetr", "garageleg", 5, 5, 0, 0x2804),
		new Cloth("Medic Uniform", "medictr", "medictr", 15, 10, 0, 0x2808),
		new Cloth("Ski Mask", "balaclava", "balaclava", 2, 0, 0, 0)
	}}};
	
	public static final Cloth[][] tattoos = new Cloth[][] {{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Weed", null, "4weed", 1, 2, 50, 0),
		new Cloth("Grave", null, "4rip", 1, 2, 45, 0),
		new Cloth("Spider", null, "4spider", 2, 2, 60, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Gun", null, "5gun", 2, 2, 50, 0),
		new Cloth("Cross", null, "5cross", 1, 2, 70, 0),
		new Cloth("Rose", null, "5cross2", 2, 2, 80, 0),
		new Cloth("Clown", null, "5cross3", 1, 2, 30, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Aztec", null, "6aztec", 2, 2, 40, 0),
		new Cloth("Web", null, "6crown", 1, 2, 50, 0),
		new Cloth("Clown", null, "6clown", 2, 2, 60, 0),
		new Cloth("Africa", null, "6africa", 2, 2, 90, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Cross (1)", null, "7cross", 1, 2, 25, 0),
		new Cloth("Cross (2)", null, "7cross2", 1, 2, 40, 0),
		new Cloth("Cross (3)", null, "7cross3", 2, 2, 70, 0),
		new Cloth("Mary", null, "7mary", 2, 2, 100, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Grove St.", null, "8sa", 3, 3, 150, 0),
		new Cloth("San Andreas", null, "8sa2", 3, 3, 250, 0),
		new Cloth("San Fiero", null, "8sa3", 3, 3, 350, 0),
		new Cloth("Westside", null, "8westside", 3, 3, 200, 0),
		new Cloth("Los Santos", null, "8santos", 3, 3, 150, 0),
		new Cloth("Card", null, "8poker", 3, 3, 200, 0),
		new Cloth("Gun", null, "8gun", 3, 3, 450, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Crown", null, "9crown", 2, 1, 125, 0),
		new Cloth("Gun (1)", null, "9gun", 2, 2, 50, 0),
		new Cloth("Gun (2)", null, "9gun2", 2, 1, 70, 0),
		new Cloth("Homeboy", null, "9homeboy", 2, 2, 100, 0),
		new Cloth("Bullet", null, "9bullet", 2, 1, 90, 0),
		new Cloth("Rasta", null, "9rasta", 2, 2, 125, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Los Santos (1)", null, "10ls", 2, 2, 80, 0),
		new Cloth("Los Santos (2)", null, "10ls2", 2, 2, 45, 0),
		new Cloth("Los Santos (3)", null, "10ls3", 2, 2, 50, 0),
		new Cloth("Los Santos (4)", null, "10ls4", 2, 2, 100, 0),
		new Cloth("Los Santos (5)", null, "10ls5", 2, 2, 65, 0),
		new Cloth("O.G.", null, "10og", 2, 2, 90, 0),
		new Cloth("Weed", null, "10weed", 2, 2, 40, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Grove (1)", null, "11grove", 2, 2, 70, 0),
		new Cloth("Grove (2)", null, "11grove2", 2, 2, 125, 0),
		new Cloth("Grove (3)", null, "11grove3", 2, 2, 100, 0),
		new Cloth("Dice (1)", null, "11dice", 2, 2, 90, 0),
		new Cloth("Dice (2)", null, "11dice2", 2, 2, 50, 0),
		new Cloth("Jail", null, "11jail", 2, 2, 30, 0),
		new Cloth("Gods Gift", null, "11godsgift", 2, 2, 200, 0)
		},{
		new Cloth("No", null, null, 0, 0, 0, 0),
		new Cloth("Angel", null, "12angels", 3, 3, 450, 0),
		new Cloth("Mayan Bird", null, "12mayabird", 3, 3, 550, 0),
		new Cloth("Dagger", null, "12dagger", 3, 3, 350, 0),
		new Cloth("Masks", null, "12bandit", 3, 3, 600, 0),
		new Cloth("Cross", null, "12cross7", 3, 3, 500, 0),
		new Cloth("Mayan Face", null, "12mayaface", 3, 3, 525, 0)
	}};
}

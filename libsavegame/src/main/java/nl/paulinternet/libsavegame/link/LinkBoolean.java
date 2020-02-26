package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.variables.Variable;

public class LinkBoolean implements Link
{
	public static final int NORMAL = 0, INVERSE = 1, AT_LEAST = 2;
	
	private Variable<Boolean> var;
	private int block;
	private int pos;
	private int size;
	private int mode, parameter;
	
	public LinkBoolean (Variable<Boolean> var, int block, int pos, int size, int mode, int parameter) {
		this.var = var;
		this.block = block;
		this.pos = pos;
		this.size = size;
		this.mode = mode;
		this.parameter = parameter;
	}
	
	public LinkBoolean (Variable<Boolean> var, int block, int pos, int size, int mode) {
		this(var, block, pos, size, mode, 0);
	}
	
	public LinkBoolean (Variable<Boolean> var, int block, int pos, int size) {
		this(var, block, pos, size, 0, 0);
	}

	@Override
	public void load (SavegameData io) {
		int value = io.readInt(block, pos, size);
		switch (mode) {
			case NORMAL: var.setValue(value != 0); break;
			case INVERSE: var.setValue(value == 0); break;
			case AT_LEAST: var.setValue(value >= parameter); break;
			default: throw new IllegalStateException();
		}
		var.resetChangedState();
	}

	@Override
	public void save (SavegameData io) {
		if (var.hasChanged()) {
			boolean bool = var.getValue();
			int value;
			switch (mode) {
				case NORMAL: value = bool ? 1 : 0; break;
				case INVERSE: value = bool ? 0 : 1; break;
				case AT_LEAST: value = bool ? parameter : 0; break;
				default: throw new IllegalStateException();
			}
			io.writeInt(block, pos, size, value);
			var.resetChangedState();
		}
	}
}

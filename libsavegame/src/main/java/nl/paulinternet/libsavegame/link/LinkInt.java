package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.variables.Variable;

public class LinkInt implements Link
{
	private Variable<Integer> var;
	private int block;
	private int pos;
	private int size;
	
	public LinkInt (Variable<Integer> var, int block, int pos, int size) {
		this.var = var;
		this.block = block;
		this.pos = pos;
		this.size = size;
	}
	
	public LinkInt (Variable<Integer> var, int block, int pos) {
		this(var, block, pos, 4);
	}

	@Override
	public void load (SavegameData io) {
		var.setValue(io.readInt(block, pos, size));
		var.resetChangedState();
	}

	@Override
	public void save (SavegameData io) {
		if (var.hasChanged()) {
			io.writeInt(block, pos, size, var.getValue());
			var.resetChangedState();
		}
	}
}

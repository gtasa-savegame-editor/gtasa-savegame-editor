package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.variables.VariableFloat;

public class LinkFloat implements Link
{
	private VariableFloat var;
	private int block;
	private int pos;
	
	public LinkFloat (VariableFloat var, int block, int pos) {
		this.var = var;
		this.block = block;
		this.pos = pos;
	}

	@Override
	public void load (SavegameData io) {
		var.setValue(io.readFloat(block, pos));
		var.resetChangedState();
	}

	@Override
	public void save (SavegameData io) {
		if (var.hasChanged()) {
			io.writeFloat(block, pos, var.getValue());
			var.resetChangedState();
		}
	}
}

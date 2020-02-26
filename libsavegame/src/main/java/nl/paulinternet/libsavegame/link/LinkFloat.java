package nl.paulinternet.libsavegame.link;

import nl.paulinternet.libsavegame.SavegameData;
import nl.paulinternet.libsavegame.variables.Variable;

public class LinkFloat implements Link
{
	private Variable<Float> var;
	private int block;
	private int pos;
	
	public LinkFloat (Variable<Float> var, int block, int pos) {
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

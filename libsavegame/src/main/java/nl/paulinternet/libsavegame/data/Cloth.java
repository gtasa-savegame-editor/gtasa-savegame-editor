package nl.paulinternet.libsavegame.data;

import java.util.zip.CRC32;

public class Cloth
{
	private static final CRC32 crc = new CRC32();
	
	private String name;
	private String model, texture;
	private int modelId, textureId;
	private int respect, sexy, price;
	private int address;
	private boolean purchased;
	
	public Cloth (String name, String model, String texture, int respect, int sexy, int price, int address) {
		this.name = name;
		this.model = model;
		this.texture = texture;
		this.respect = respect;
		this.sexy = sexy;
		this.price = price;
		this.address = address;
		
		if (model != null) {
			crc.reset();
			crc.update(model.toUpperCase().getBytes());
			modelId = ~ (int) crc.getValue();
		}
		
		if (texture != null) {
			crc.reset();
			crc.update(texture.toUpperCase().getBytes());
			textureId = ~ (int) crc.getValue();
		}
	}

	public String getName () {
		return name;
	}

	public String getModelName () {
		return model;
	}

	public String getTextureName () {
		return texture;
	}
	
	public int getModelId () {
		return modelId;
	}
	
	public int getTextureId () {
		return textureId;
	}

	public int getRespect () {
		return respect;
	}

	public int getSexy () {
		return sexy;
	}

	public int getPrice () {
		return price;
	}

	public int getAddress () {
		return address;
	}

	public boolean isPurchased () {
		return purchased;
	}

	public void setPurchased (boolean purchased) {
		this.purchased = purchased;
	}
}

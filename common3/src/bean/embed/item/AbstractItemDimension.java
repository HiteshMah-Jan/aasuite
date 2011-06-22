package bean.embed.item;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AbstractItemDimension implements Serializable{
	public double length;
	public double width;
	public double height;
	public double volume;
	public String volumeUom;
	public double weight;
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getVolumeUom() {
		return volumeUom;
	}
	public void setVolumeUom(String volumeUom) {
		this.volumeUom = volumeUom;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

}

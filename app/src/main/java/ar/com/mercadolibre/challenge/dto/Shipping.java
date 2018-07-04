package ar.com.mercadolibre.challenge.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Shipping{

	@SerializedName("mode")
	private String mode;

	@SerializedName("free_shipping")
	private boolean freeShipping;

	@SerializedName("tags")
	private List<Object> tags;

	@SerializedName("logistic_type")
	private String logisticType;

	public void setMode(String mode){
		this.mode = mode;
	}

	public String getMode(){
		return mode;
	}

	public void setFreeShipping(boolean freeShipping){
		this.freeShipping = freeShipping;
	}

	public boolean isFreeShipping(){
		return freeShipping;
	}

	public void setTags(List<Object> tags){
		this.tags = tags;
	}

	public List<Object> getTags(){
		return tags;
	}

	public void setLogisticType(String logisticType){
		this.logisticType = logisticType;
	}

	public String getLogisticType(){
		return logisticType;
	}

	@Override
 	public String toString(){
		return 
			"Shipping{" + 
			"mode = '" + mode + '\'' + 
			",free_shipping = '" + freeShipping + '\'' + 
			",tags = '" + tags + '\'' + 
			",logistic_type = '" + logisticType + '\'' + 
			"}";
		}
}
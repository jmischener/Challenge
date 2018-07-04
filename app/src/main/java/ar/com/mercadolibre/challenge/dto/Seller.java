package ar.com.mercadolibre.challenge.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Seller{

	@SerializedName("power_seller_status")
	private String powerSellerStatus;

	@SerializedName("car_dealer")
	private boolean carDealer;

	@SerializedName("id")
	private int id;

	@SerializedName("real_estate_agency")
	private boolean realEstateAgency;

	@SerializedName("tags")
	private List<Object> tags;

	public void setPowerSellerStatus(String powerSellerStatus){
		this.powerSellerStatus = powerSellerStatus;
	}

	public String getPowerSellerStatus(){
		return powerSellerStatus;
	}

	public void setCarDealer(boolean carDealer){
		this.carDealer = carDealer;
	}

	public boolean isCarDealer(){
		return carDealer;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setRealEstateAgency(boolean realEstateAgency){
		this.realEstateAgency = realEstateAgency;
	}

	public boolean isRealEstateAgency(){
		return realEstateAgency;
	}

	public void setTags(List<Object> tags){
		this.tags = tags;
	}

	public List<Object> getTags(){
		return tags;
	}

	@Override
 	public String toString(){
		return 
			"Seller{" + 
			"power_seller_status = '" + powerSellerStatus + '\'' + 
			",car_dealer = '" + carDealer + '\'' + 
			",id = '" + id + '\'' + 
			",real_estate_agency = '" + realEstateAgency + '\'' + 
			",tags = '" + tags + '\'' + 
			"}";
		}
}
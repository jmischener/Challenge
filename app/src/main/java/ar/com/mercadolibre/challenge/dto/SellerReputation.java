package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class SellerReputation{

	@SerializedName("power_seller_status")
	private String powerSellerStatus;

	@SerializedName("level_id")
	private String levelId;

	public void setPowerSellerStatus(String powerSellerStatus){
		this.powerSellerStatus = powerSellerStatus;
	}

	public String getPowerSellerStatus(){
		return powerSellerStatus;
	}

	public void setLevelId(String levelId){
		this.levelId = levelId;
	}

	public String getLevelId(){
		return levelId;
	}

	@Override
 	public String toString(){
		return 
			"SellerReputation{" + 
			"power_seller_status = '" + powerSellerStatus + '\'' + 
			",level_id = '" + levelId + '\'' + 
			"}";
		}
}
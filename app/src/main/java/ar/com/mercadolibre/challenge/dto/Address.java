package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class Address{

	@SerializedName("city_name")
	private String cityName;

	@SerializedName("state_name")
	private String stateName;

	@SerializedName("state_id")
	private String stateId;

	@SerializedName("city_id")
	private String cityId;

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	public void setStateName(String stateName){
		this.stateName = stateName;
	}

	public String getStateName(){
		return stateName;
	}

	public void setStateId(String stateId){
		this.stateId = stateId;
	}

	public String getStateId(){
		return stateId;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"Address{" + 
			"city_name = '" + cityName + '\'' + 
			",state_name = '" + stateName + '\'' + 
			",state_id = '" + stateId + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}
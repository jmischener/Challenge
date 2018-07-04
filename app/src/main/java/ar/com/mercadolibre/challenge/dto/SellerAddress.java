package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class SellerAddress{

	@SerializedName("country")
	private Country country;

	@SerializedName("address_line")
	private String addressLine;

	@SerializedName("city")
	private City city;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id")
	private String id;

	@SerializedName("state")
	private State state;

	@SerializedName("zip_code")
	private String zipCode;

	@SerializedName("longitude")
	private String longitude;

	public void setCountry(Country country){
		this.country = country;
	}

	public Country getCountry(){
		return country;
	}

	public void setAddressLine(String addressLine){
		this.addressLine = addressLine;
	}

	public String getAddressLine(){
		return addressLine;
	}

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(State state){
		this.state = state;
	}

	public State getState(){
		return state;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String getZipCode(){
		return zipCode;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"SellerAddress{" + 
			"country = '" + country + '\'' + 
			",address_line = '" + addressLine + '\'' + 
			",city = '" + city + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",zip_code = '" + zipCode + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}
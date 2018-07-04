package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("seller_reputation")
	private SellerReputation sellerReputation;

	@SerializedName("registration_date")
	private String registrationDate;

	@SerializedName("nickname")
	private String nickname;

	@SerializedName("id")
	private int id;

	@SerializedName("permalink")
	private String permalink;

	public void setSellerReputation(SellerReputation sellerReputation){
		this.sellerReputation = sellerReputation;
	}

	public SellerReputation getSellerReputation(){
		return sellerReputation;
	}

	public void setRegistrationDate(String registrationDate){
		this.registrationDate = registrationDate;
	}

	public String getRegistrationDate(){
		return registrationDate;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return nickname;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPermalink(String permalink){
		this.permalink = permalink;
	}

	public String getPermalink(){
		return permalink;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"seller_reputation = '" + sellerReputation + '\'' + 
			",registration_date = '" + registrationDate + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",id = '" + id + '\'' + 
			",permalink = '" + permalink + '\'' + 
			"}";
		}
}
package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class Answer{

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("text")
	private String text;

	@SerializedName("status")
	private String status;

	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Answer{" + 
			"date_created = '" + dateCreated + '\'' + 
			",text = '" + text + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
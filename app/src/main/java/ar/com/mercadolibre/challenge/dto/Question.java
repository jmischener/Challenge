package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class Question{

	@SerializedName("answer")
	private Answer answer;

	@SerializedName("item_id")
	private String itemId;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("text")
	private String text;

	@SerializedName("id")
	private long id;

	@SerializedName("seller_id")
	private int sellerId;

	@SerializedName("status")
	private String status;

	public void setAnswer(Answer answer){
		this.answer = answer;
	}

	public Answer getAnswer(){
		return answer;
	}

	public void setItemId(String itemId){
		this.itemId = itemId;
	}

	public String getItemId(){
		return itemId;
	}

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

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setSellerId(int sellerId){
		this.sellerId = sellerId;
	}

	public int getSellerId(){
		return sellerId;
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
			"Question{" + 
			"answer = '" + answer + '\'' + 
			",item_id = '" + itemId + '\'' + 
			",date_created = '" + dateCreated + '\'' + 
			",text = '" + text + '\'' + 
			",id = '" + id + '\'' + 
			",seller_id = '" + sellerId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
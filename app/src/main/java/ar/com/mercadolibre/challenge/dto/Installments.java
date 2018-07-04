package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class Installments{

	@SerializedName("amount")
	private double amount;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("rate")
	private double rate;

	@SerializedName("currency_id")
	private String currencyId;

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setRate(double rate){
		this.rate = rate;
	}

	public double getRate(){
		return rate;
	}

	public void setCurrencyId(String currencyId){
		this.currencyId = currencyId;
	}

	public String getCurrencyId(){
		return currencyId;
	}

	@Override
 	public String toString(){
		return 
			"Installments{" + 
			"amount = '" + amount + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",rate = '" + rate + '\'' + 
			",currency_id = '" + currencyId + '\'' + 
			"}";
		}
}
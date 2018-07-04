package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class Reviews{

	@SerializedName("rating_average")
	private double ratingAverage;

	@SerializedName("total")
	private int total;

	public void setRatingAverage(double ratingAverage){
		this.ratingAverage = ratingAverage;
	}

	public double getRatingAverage(){
		return ratingAverage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	@Override
 	public String toString(){
		return 
			"Reviews{" + 
			"rating_average = '" + ratingAverage + '\'' + 
			",total = '" + total + '\'' + 
			"}";
		}
}
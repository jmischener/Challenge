package ar.com.mercadolibre.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class PicturesItem{

	@SerializedName("size")
	private String size;

	@SerializedName("secure_url")
	private String secureUrl;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("max_size")
	private String maxSize;

	@SerializedName("quality")
	private String quality;

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setSecureUrl(String secureUrl){
		this.secureUrl = secureUrl;
	}

	public String getSecureUrl(){
		return secureUrl;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setMaxSize(String maxSize){
		this.maxSize = maxSize;
	}

	public String getMaxSize(){
		return maxSize;
	}

	public void setQuality(String quality){
		this.quality = quality;
	}

	public String getQuality(){
		return quality;
	}

	@Override
 	public String toString(){
		return 
			"PicturesItem{" + 
			"size = '" + size + '\'' + 
			",secure_url = '" + secureUrl + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			",max_size = '" + maxSize + '\'' + 
			",quality = '" + quality + '\'' + 
			"}";
		}
}
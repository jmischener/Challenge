package ar.com.mercadolibre.challenge.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("seller")
	private Seller seller;

	@SerializedName("original_price")
	private Object originalPrice;

	@SerializedName("stop_time")
	private String stopTime;

	@SerializedName("buying_mode")
	private String buyingMode;

	@SerializedName("title")
	private String title;

	@SerializedName("sold_quantity")
	private int soldQuantity;

	@SerializedName("available_quantity")
	private int availableQuantity;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("reviews")
	private Reviews reviews;

	@SerializedName("installments")
	private Installments installments;

	@SerializedName("price")
	private Double price;

	@SerializedName("official_store_id")
	private Object officialStoreId;

	@SerializedName("id")
	private String id;

	@SerializedName("accepts_mercadopago")
	private boolean acceptsMercadopago;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("address")
	private Address address;

	@SerializedName("catalog_product_id")
	private Object catalogProductId;

	@SerializedName("seller_address")
	private SellerAddress sellerAddress;

	@SerializedName("tags")
	private List<Object> tags;

	@SerializedName("condition")
	private String condition;

	@SerializedName("site_id")
	private String siteId;

	@SerializedName("attributes")
	private List<Object> attributes;

	@SerializedName("listing_type_id")
	private String listingTypeId;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("currency_id")
	private String currencyId;

	public void setSeller(Seller seller){
		this.seller = seller;
	}

	public Seller getSeller(){
		return seller;
	}

	public void setOriginalPrice(Object originalPrice){
		this.originalPrice = originalPrice;
	}

	public Object getOriginalPrice(){
		return originalPrice;
	}

	public void setStopTime(String stopTime){
		this.stopTime = stopTime;
	}

	public String getStopTime(){
		return stopTime;
	}

	public void setBuyingMode(String buyingMode){
		this.buyingMode = buyingMode;
	}

	public String getBuyingMode(){
		return buyingMode;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setSoldQuantity(int soldQuantity){
		this.soldQuantity = soldQuantity;
	}

	public int getSoldQuantity(){
		return soldQuantity;
	}

	public void setAvailableQuantity(int availableQuantity){
		this.availableQuantity = availableQuantity;
	}

	public int getAvailableQuantity(){
		return availableQuantity;
	}

	public void setShipping(Shipping shipping){
		this.shipping = shipping;
	}

	public Shipping getShipping(){
		return shipping;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setReviews(Reviews reviews){
		this.reviews = reviews;
	}

	public Reviews getReviews(){
		return reviews;
	}

	public void setInstallments(Installments installments){
		this.installments = installments;
	}

	public Installments getInstallments(){
		return installments;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getPrice(){
		return price;
	}

	public void setOfficialStoreId(Object officialStoreId){
		this.officialStoreId = officialStoreId;
	}

	public Object getOfficialStoreId(){
		return officialStoreId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAcceptsMercadopago(boolean acceptsMercadopago){
		this.acceptsMercadopago = acceptsMercadopago;
	}

	public boolean isAcceptsMercadopago(){
		return acceptsMercadopago;
	}

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setCatalogProductId(Object catalogProductId){
		this.catalogProductId = catalogProductId;
	}

	public Object getCatalogProductId(){
		return catalogProductId;
	}

	public void setSellerAddress(SellerAddress sellerAddress){
		this.sellerAddress = sellerAddress;
	}

	public SellerAddress getSellerAddress(){
		return sellerAddress;
	}

	public void setTags(List<Object> tags){
		this.tags = tags;
	}

	public List<Object> getTags(){
		return tags;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}

	public String getCondition(){
		return condition;
	}

	public void setSiteId(String siteId){
		this.siteId = siteId;
	}

	public String getSiteId(){
		return siteId;
	}

	public void setAttributes(List<Object> attributes){
		this.attributes = attributes;
	}

	public List<Object> getAttributes(){
		return attributes;
	}

	public void setListingTypeId(String listingTypeId){
		this.listingTypeId = listingTypeId;
	}

	public String getListingTypeId(){
		return listingTypeId;
	}

	public void setPermalink(String permalink){
		this.permalink = permalink;
	}

	public String getPermalink(){
		return permalink;
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
			"Result{" + 
			"seller = '" + seller + '\'' + 
			",original_price = '" + originalPrice + '\'' + 
			",stop_time = '" + stopTime + '\'' + 
			",buying_mode = '" + buyingMode + '\'' + 
			",title = '" + title + '\'' + 
			",sold_quantity = '" + soldQuantity + '\'' + 
			",available_quantity = '" + availableQuantity + '\'' + 
			",shipping = '" + shipping + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",reviews = '" + reviews + '\'' + 
			",installments = '" + installments + '\'' + 
			",price = '" + price + '\'' + 
			",official_store_id = '" + officialStoreId + '\'' + 
			",id = '" + id + '\'' + 
			",accepts_mercadopago = '" + acceptsMercadopago + '\'' + 
			",thumbnail = '" + thumbnail + '\'' + 
			",address = '" + address + '\'' + 
			",catalog_product_id = '" + catalogProductId + '\'' + 
			",seller_address = '" + sellerAddress + '\'' + 
			",tags = '" + tags + '\'' + 
			",condition = '" + condition + '\'' + 
			",site_id = '" + siteId + '\'' + 
			",attributes = '" + attributes + '\'' + 
			",listing_type_id = '" + listingTypeId + '\'' + 
			",permalink = '" + permalink + '\'' + 
			",currency_id = '" + currencyId + '\'' + 
			"}";
		}
}
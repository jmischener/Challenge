package ar.com.mercadolibre.challenge.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Item{

	@SerializedName("original_price")
	private Object originalPrice;

	@SerializedName("secure_thumbnail")
	private String secureThumbnail;

	@SerializedName("buying_mode")
	private String buyingMode;

	private String description;

	@SerializedName("available_quantity")
	private int availableQuantity;

	@SerializedName("domain_id")
	private String domainId;

	@SerializedName("price")
	private Double price;

	@SerializedName("warranty")
	private String warranty;

	@SerializedName("seller_contact")
	private Object sellerContact;

	@SerializedName("id")
	private String id;

	@SerializedName("seller_id")
	private int sellerId;

	@SerializedName("accepts_mercadopago")
	private boolean acceptsMercadopago;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("last_updated")
	private String lastUpdated;

	@SerializedName("automatic_relist")
	private boolean automaticRelist;

	@SerializedName("start_time")
	private String startTime;

	@SerializedName("condition")
	private String condition;

	@SerializedName("site_id")
	private String siteId;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("status")
	private String status;

	@SerializedName("stop_time")
	private String stopTime;

	@SerializedName("title")
	private String title;

	@SerializedName("sold_quantity")
	private int soldQuantity;

	@SerializedName("pictures")
	private List<PicturesItem> pictures;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("base_price")
	private Double basePrice;

	@SerializedName("initial_quantity")
	private int initialQuantity;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("catalog_product_id")
	private String catalogProductId;

	@SerializedName("listing_source")
	private String listingSource;

	@SerializedName("seller_address")
	private SellerAddress sellerAddress;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("international_delivery_mode")
	private String internationalDeliveryMode;

	@SerializedName("parent_item_id")
	private Object parentItemId;

	@SerializedName("listing_type_id")
	private String listingTypeId;

	@SerializedName("currency_id")
	private String currencyId;

	@SerializedName("geolocation")
	private Geolocation geolocation;

	public void setOriginalPrice(Object originalPrice){
		this.originalPrice = originalPrice;
	}

	public Object getOriginalPrice(){
		return originalPrice;
	}

	public void setSecureThumbnail(String secureThumbnail){
		this.secureThumbnail = secureThumbnail;
	}

	public String getSecureThumbnail(){
		return secureThumbnail;
	}

	public void setBuyingMode(String buyingMode){
		this.buyingMode = buyingMode;
	}

	public String getBuyingMode(){
		return buyingMode;
	}

	public void setDescription(String descriptions){
		this.description = descriptions;
	}

	public String getDescription(){
		return description;
	}

	public void setAvailableQuantity(int availableQuantity){
		this.availableQuantity = availableQuantity;
	}

	public int getAvailableQuantity(){
		return availableQuantity;
	}

	public void setDomainId(String domainId){
		this.domainId = domainId;
	}

	public String getDomainId(){
		return domainId;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getPrice(){
		return price;
	}

	public void setWarranty(String warranty){
		this.warranty = warranty;
	}

	public String getWarranty(){
		return warranty;
	}

	public void setSellerContact(Object sellerContact){
		this.sellerContact = sellerContact;
	}

	public Object getSellerContact(){
		return sellerContact;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSellerId(int sellerId){
		this.sellerId = sellerId;
	}

	public int getSellerId(){
		return sellerId;
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

	public void setLastUpdated(String lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdated(){
		return lastUpdated;
	}

	public void setAutomaticRelist(boolean automaticRelist){
		this.automaticRelist = automaticRelist;
	}

	public boolean isAutomaticRelist(){
		return automaticRelist;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return startTime;
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

	public void setPermalink(String permalink){
		this.permalink = permalink;
	}

	public String getPermalink(){
		return permalink;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setStopTime(String stopTime){
		this.stopTime = stopTime;
	}

	public String getStopTime(){
		return stopTime;
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

	public void setPictures(List<PicturesItem> pictures){
		this.pictures = pictures;
	}

	public List<PicturesItem> getPictures(){
		return pictures;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setBasePrice(Double basePrice){
		this.basePrice = basePrice;
	}

	public Double getBasePrice(){
		return basePrice;
	}

	public void setInitialQuantity(int initialQuantity){
		this.initialQuantity = initialQuantity;
	}

	public int getInitialQuantity(){
		return initialQuantity;
	}

	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public void setCatalogProductId(String catalogProductId){
		this.catalogProductId = catalogProductId;
	}

	public String getCatalogProductId(){
		return catalogProductId;
	}

	public void setListingSource(String listingSource){
		this.listingSource = listingSource;
	}

	public String getListingSource(){
		return listingSource;
	}

	public void setSellerAddress(SellerAddress sellerAddress){
		this.sellerAddress = sellerAddress;
	}

	public SellerAddress getSellerAddress(){
		return sellerAddress;
	}

	public void setInternationalDeliveryMode(String internationalDeliveryMode){
		this.internationalDeliveryMode = internationalDeliveryMode;
	}

	public String getInternationalDeliveryMode(){
		return internationalDeliveryMode;
	}

	public void setParentItemId(Object parentItemId){
		this.parentItemId = parentItemId;
	}

	public Object getParentItemId(){
		return parentItemId;
	}

	public void setListingTypeId(String listingTypeId){
		this.listingTypeId = listingTypeId;
	}

	public String getListingTypeId(){
		return listingTypeId;
	}

	public void setCurrencyId(String currencyId){
		this.currencyId = currencyId;
	}

	public String getCurrencyId(){
		return currencyId;
	}

	public void setGeolocation(Geolocation geolocation){
		this.geolocation = geolocation;
	}

	public Geolocation getGeolocation(){
		return geolocation;
	}

	@Override
 	public String toString(){
		return 
			"Item{" + 
			"original_price = '" + originalPrice + '\'' +
			",secure_thumbnail = '" + secureThumbnail + '\'' + 
			",buying_mode = '" + buyingMode + '\'' +
			",descriptions = '" + description + '\'' +
			",available_quantity = '" + availableQuantity + '\'' + 
			",domain_id = '" + domainId + '\'' +
			",price = '" + price + '\'' +
			",warranty = '" + warranty + '\'' + 
			",seller_contact = '" + sellerContact + '\'' + 
			",id = '" + id + '\'' + 
			",seller_id = '" + sellerId + '\'' + 
			",accepts_mercadopago = '" + acceptsMercadopago + '\'' + 
			",thumbnail = '" + thumbnail + '\'' + 
			",last_updated = '" + lastUpdated + '\'' + 
			",automatic_relist = '" + automaticRelist + '\'' +
			",start_time = '" + startTime + '\'' + 
			",condition = '" + condition + '\'' +
			",site_id = '" + siteId + '\'' + 
			",permalink = '" + permalink + '\'' +
			",status = '" + status + '\'' + 
			",stop_time = '" + stopTime + '\'' + 
			",title = '" + title + '\'' + 
			",sold_quantity = '" + soldQuantity + '\'' + 
			",pictures = '" + pictures + '\'' + 
			",category_id = '" + categoryId + '\'' +
			",base_price = '" + basePrice + '\'' +
			",initial_quantity = '" + initialQuantity + '\'' + 
			",date_created = '" + dateCreated + '\'' +
			",catalog_product_id = '" + catalogProductId + '\'' +
			",listing_source = '" + listingSource + '\'' + 
			",seller_address = '" + sellerAddress + '\'' + 
			",international_delivery_mode = '" + internationalDeliveryMode + '\'' + 
			",parent_item_id = '" + parentItemId + '\'' +
			",listing_type_id = '" + listingTypeId + '\'' + 
			",currency_id = '" + currencyId + '\'' +
			",geolocation = '" + geolocation + '\'' + 
			"}";
		}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
}
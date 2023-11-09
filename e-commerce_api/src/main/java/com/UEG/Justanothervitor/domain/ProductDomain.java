package com.UEG.Justanothervitor.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductDomain  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String productId;
	
	private String productName;
	private String description;
	private String linkToImage;
	private Double price;
	
	public ProductDomain()
	{
		
	}
	
	public ProductDomain(String productId, String productName, String description, String linkToImage, Double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.linkToImage = linkToImage;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLinkToImage() {
		return linkToImage;
	}

	public void setLinkToImage(String linkToImage) {
		this.linkToImage = linkToImage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDomain other = (ProductDomain) obj;
		return Objects.equals(productId, other.productId);
	}
	
	
	

}

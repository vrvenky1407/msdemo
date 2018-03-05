package com.fisglobal.emtech.products;

import lombok.Data;

/**
 * Product POJO. Note with Lambok @Data annotation, we don't need the
 * getter/setters (http://jnb.ociweb.com/jnb/jnbJan2010.html)
 * 
 * @author e0079140
 */
@Data
public class Product {

	int assetId;
	String solutionName;
	String solutionDescription;
	String solutionOwner;
	String domain;
	String category;
	String solutionStatus;
	String riskBusinessLine;
	String financeLevel5;
	String financeLevel10;
	String buOwner;
	String productOwner;
	String implementationMgr;
	String developmentMgr;
	String deliveryMethod;
	String processingLocation;
	String ancilliaryProducts;

	public void sanitize() {
		if (solutionName == null)
			solutionName = "";
		if (solutionDescription == null)
			solutionDescription = "";
		if (solutionOwner == null)
			solutionOwner = "";
		if (domain == null)
			domain = "";
		if (category == null)
			category = "";
		if (solutionStatus == null)
			solutionStatus = "";
		if (riskBusinessLine == null)
			riskBusinessLine = "";
		if (financeLevel5 == null)
			financeLevel5 = "";
		if (financeLevel10 == null)
			financeLevel10 = "";
		if (buOwner == null)
			buOwner = "";
		if (productOwner == null)
			productOwner = "";
		if (implementationMgr == null)
			implementationMgr = "";
		if (developmentMgr == null)
			developmentMgr = "";
		if (deliveryMethod == null)
			deliveryMethod = "";
		if (processingLocation == null)
			processingLocation = "";
		if (ancilliaryProducts == null)
			ancilliaryProducts = "";

		// remove html content
		processingLocation = processingLocation.replaceAll("\\<[^>]*>", "");
	}

	@Override
	public String toString() {
		return "Product [assetId=" + assetId + ", solutionName=" + solutionName + ", solutionDescription="
				+ solutionDescription + ", domain=" + domain + ", category=" + category + ", solutionStatus="
				+ solutionStatus + ", riskBusinessLine=" + riskBusinessLine + ", financeLevel5=" + financeLevel5
				+ ", financeLevel10=" + financeLevel10 + ", buOwner=" + buOwner + ", productOwner=" + productOwner
				+ ", implementationMgr=" + implementationMgr + ", developmentMgr=" + developmentMgr
				+ ", deliveryMethod=" + deliveryMethod + ", processingLocation=" + processingLocation
				+ ", ancilliaryProducts=" + ancilliaryProducts + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product))
			return false;
		else {
			Product p = (Product) obj;
			if (p.assetId == this.assetId)
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.assetId;
	}

}

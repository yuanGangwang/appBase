package com.example.appbase.demo;

public class ProductBean {

    /**
     * bannerId :
     * id : 656211269098930176
     * img : http://hjb.onmsbx.com/uploads/20190723/51e9ba91fc4cb0204cd4b4e97debed2f.jpg
     * isSale : 2
     * maxBuyRed : 0
     * maxChangeRed : 0
     * originalPrice : 6720
     * productName : iPad Pro 12.9英寸
     */

    private String bannerId;
    private String id;
    private String img;
    private int isSale;
    private String maxBuyRed;
    private String maxChangeRed;
    private String originalPrice;
    private String productName;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIsSale() {
        return isSale;
    }

    public void setIsSale(int isSale) {
        this.isSale = isSale;
    }

    public String getMaxBuyRed() {
        return maxBuyRed;
    }

    public void setMaxBuyRed(String maxBuyRed) {
        this.maxBuyRed = maxBuyRed;
    }

    public String getMaxChangeRed() {
        return maxChangeRed;
    }

    public void setMaxChangeRed(String maxChangeRed) {
        this.maxChangeRed = maxChangeRed;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

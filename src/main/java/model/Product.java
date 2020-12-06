package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {

    private LocalDate shippingDate;
    private String packageSize;
    private String provider;
    private BigDecimal shippingPrice; //dinaminis
    private BigDecimal discount; //dinaminis

    public Product(LocalDate shippingDate, String packageSize, String provider) {
        this.shippingDate = shippingDate;
        this.packageSize = packageSize;
        this.provider = provider;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    //TODO implement comparing logic by product shipping date

    @Override
    public String toString() {
        return shippingDate + " " + packageSize + " " + provider + " " + shippingPrice + " " + discount;
    }
}

package model;

import java.math.BigDecimal;

public class Provider {

    private String name;
    private String packageSize;
    private BigDecimal shippingPrice;

    public Provider(String name, String packageSize, BigDecimal shippingPrice) {
        this.name = name;
        this.packageSize = packageSize;
        this.shippingPrice = shippingPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
}

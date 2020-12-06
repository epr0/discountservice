package chain;

import model.Product;
import model.Provider;
import services.DiscountService;
import utils.PackageProcessorUtils;

import java.math.BigDecimal;
import java.util.List;

public class SmallPackageProcessor implements PackageProcessorChain {

    private PackageProcessorChain nextPackageProcessorChain;

    @Override
    public void setNextChain(PackageProcessorChain nextPackageProcessorChain) {
        this.nextPackageProcessorChain = nextPackageProcessorChain;
    }

    @Override
    public void processProduct(Product product, List<Provider> providers) {
        if(product.getPackageSize().equalsIgnoreCase("S")) {
            //TODO dabaigti sita fielda
            BigDecimal cheapestShippingPrice = getCheapestPrice(providers, "S");
            BigDecimal providerShippingPrice =
                    PackageProcessorUtils.getProviderShippingPrice(providers,
                            product.getProvider(), product.getPackageSize());
            BigDecimal shippingPriceDifference = providerShippingPrice.subtract(cheapestShippingPrice);

            DiscountService discountService = new DiscountService();
            BigDecimal remainingAcumulativeDiscount = discountService.getRemainingMonthlyDisc();

            BigDecimal shippingPrice;
            BigDecimal discountPrice;

            if(remainingAcumulativeDiscount.compareTo(shippingPriceDifference) == -1) {
                //shiping price 1.50 is 2 euru
                //max nuolaida likusi 0.3
                //shipping price 1.70 ir disocuntas 0.3, o ne standartinis 0.5
                shippingPrice = providerShippingPrice.subtract(remainingAcumulativeDiscount);
                discountPrice = remainingAcumulativeDiscount;
            } else {
                shippingPrice = cheapestShippingPrice;
                discountPrice = providerShippingPrice.subtract(cheapestShippingPrice);
            }

            product.setShippingPrice(shippingPrice);
            product.setDiscount(discountPrice);

        } else {
            this.nextPackageProcessorChain.processProduct(product, providers);
        }
    }
    //Optimize methods with overused parameters
    private BigDecimal getCheapestPrice(List<Provider> providers, String s) {
        //Optimize this!!!
        BigDecimal cheapestPrice = PackageProcessorUtils.ZERO;
        for (Provider provider : providers) {
            if(provider.getPackageSize().equalsIgnoreCase("S")) {
                if (cheapestPrice == PackageProcessorUtils.ZERO || cheapestPrice.compareTo(provider.getShippingPrice()) == 1) {
                    cheapestPrice = provider.getShippingPrice();
                }
            }
        }

        return cheapestPrice;
    }
}

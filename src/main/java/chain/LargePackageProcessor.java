package chain;

import model.Discount;
import model.Product;
import model.Provider;
import utils.PackageProcessorUtils;

import java.math.BigDecimal;
import java.util.List;

public class LargePackageProcessor implements PackageProcessorChain {

    public static int LP_LARGE_PACKAGE_SHIPPING_COUNTER = 0;
    private final int FREE_SHIPPING_INDICATOR = 3;

    private PackageProcessorChain nextPackageProcessorChain;

    @Override
    public void setNextChain(PackageProcessorChain nextPackageProcessorChain) {
        this.nextPackageProcessorChain = nextPackageProcessorChain;
    }

    @Override
    public void processProduct(Product product, List<Provider> providers) {
        if(product.getPackageSize().equalsIgnoreCase("L")) {
            BigDecimal shippingPrice =
                    PackageProcessorUtils.getProviderShippingPrice(providers, product.getProvider(),
                            product.getPackageSize());
            processProductPrice(product, shippingPrice);
        }
    }

    private void processProductPrice(Product product, BigDecimal shippingPrice) {
        if(product.getProvider().equalsIgnoreCase("LP")) {
            LP_LARGE_PACKAGE_SHIPPING_COUNTER++;
        }

        if(LP_LARGE_PACKAGE_SHIPPING_COUNTER == FREE_SHIPPING_INDICATOR) {
            product.setShippingPrice(PackageProcessorUtils.ZERO);
            product.setDiscount(shippingPrice);
            Discount.amount = Discount.amount.add(shippingPrice);
        } else {
            product.setShippingPrice(shippingPrice);
            product.setDiscount(PackageProcessorUtils.ZERO);
        }


    }
}

package chain;

import model.Product;
import model.Provider;
import utils.PackageProcessorUtils;

import java.math.BigDecimal;
import java.util.List;

public class MediumPackageProcessor implements PackageProcessorChain {

    private PackageProcessorChain packageProcessorChain;

    @Override
    public void setNextChain(PackageProcessorChain nextPackageProcessorChain) {
        this.packageProcessorChain = nextPackageProcessorChain;
    }

    @Override
    public void processProduct(Product product, List<Provider> providers) {
        if(product.getPackageSize().equalsIgnoreCase("M")) {
            BigDecimal shippingPrice = PackageProcessorUtils.getProviderShippingPrice(providers,
                    product.getProvider(), product.getPackageSize());
            product.setShippingPrice(shippingPrice);
            product.setDiscount(PackageProcessorUtils.ZERO);
        } else {
            this.packageProcessorChain.processProduct(product, providers);
        }
    }
}

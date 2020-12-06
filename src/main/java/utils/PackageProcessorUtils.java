package utils;

import model.Provider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PackageProcessorUtils {

    public static BigDecimal ZERO = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public static BigDecimal getProviderShippingPrice(List<Provider> providers,
                                                      String providerName, String packageSize) {
        for (Provider provider : providers) {
            if(provider.getName().equalsIgnoreCase(providerName)
                    && provider.getPackageSize().equalsIgnoreCase(packageSize)) {
                return provider.getShippingPrice();
            }
        }

        return ZERO;
    }
}

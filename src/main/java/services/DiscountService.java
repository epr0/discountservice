package services;

import chain.LargePackageProcessor;
import model.Discount;
import utils.PackageProcessorUtils;

import java.math.BigDecimal;

public class DiscountService {

    public BigDecimal getRemainingMonthlyDisc() {
        BigDecimal remainingDiscount = BigDecimal.TEN.subtract(Discount.amount);
        if(BigDecimal.ZERO.compareTo(remainingDiscount) == 1) {
            return BigDecimal.ZERO;
        }

        return remainingDiscount;
    }

}

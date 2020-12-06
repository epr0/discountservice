package chain;

import model.Product;
import model.Provider;

import java.util.List;

public interface PackageProcessorChain {

    void setNextChain(PackageProcessorChain packageProcessorChain);

    void processProduct(Product product, List<Provider> providers);
}

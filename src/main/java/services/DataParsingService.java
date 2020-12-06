package services;

import chain.PackageProcessor;
import model.Product;
import model.Provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//service responsible for handling data inputs and parsing them
public class DataParsingService {

    public List<String> retrieveInputFileContent(String providerInputFilePath) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(providerInputFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> inputFileRows = new ArrayList<>();
        String singleLine;
        while ((singleLine = reader.readLine()) != null) {
            inputFileRows.add(singleLine);
        }
        reader.close();

        return inputFileRows;
    }

    public List<Provider> constructProviderList(List<String> providerInputFileRows) {
        List<Provider> providers = new ArrayList<>();
        for (String row : providerInputFileRows) {
            List<String> providerTokens = Arrays.asList(row.split("\\s+"));
            String providerName = providerTokens.get(0);
            String packageSize = providerTokens.get(1);
            BigDecimal shippingPrice = new BigDecimal(providerTokens.get(2));
            providers.add(new Provider(providerName, packageSize, shippingPrice));
        }

        return providers;
    }

    public void constructProductList(List<String> prodcutInputFileRows, List<Provider> providers) {
        List<Product> products = new ArrayList<>();
        for (String row : prodcutInputFileRows) {
            List<String> productTokens = Arrays.asList(row.split("\\s+"));
            try {
                LocalDate shippingDate = LocalDate.parse(productTokens.get(0));
                String packageSize = productTokens.get(1);
                String provider = productTokens.get(2);
                Product product = new Product(shippingDate, packageSize, provider);
                products.add(product);

                PackageProcessor packageProcessor = new PackageProcessor();
                packageProcessor.getFirstPackageProcessorChain().processProduct(product, providers);

                printOutProductInfo(product);
            } catch (DateTimeException ex) {
                System.out.println(productTokens.get(0) + " Ignored");
            }

        }
    }

    private void printOutProductInfo(Product product) {
        if(product.getDiscount().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println(product.getShippingDate() + " "
                    + product.getPackageSize() + " "
                    + product.getProvider() + " "
                    + product.getShippingPrice() + " -");
        } else {
            System.out.println(product);
        }
    }
}

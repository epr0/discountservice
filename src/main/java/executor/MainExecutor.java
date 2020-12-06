package executor;

import model.Provider;
import services.DataParsingService;

import java.io.IOException;
import java.util.List;

public class MainExecutor {

    private static final String PRODUCT_INPUT_FILE_PATH = "/input.txt";
    private static final String PROVIDER_INPUT_FILE_PATH = "/providerInfo.txt";

    public static void main(String[] args) throws IOException {
        //provider parsing logic
        DataParsingService dataParsingService = new DataParsingService();

        List<String> providerInputFileRows = dataParsingService.retrieveInputFileContent(PROVIDER_INPUT_FILE_PATH);
        List<Provider> providers = dataParsingService.constructProviderList(providerInputFileRows);

        List<String> prodcutInputFileRows = dataParsingService.retrieveInputFileContent(PRODUCT_INPUT_FILE_PATH);
        dataParsingService.constructProductList(prodcutInputFileRows, providers);
    }
}

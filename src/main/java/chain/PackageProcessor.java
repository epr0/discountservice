package chain;

public class PackageProcessor {

    private PackageProcessorChain firstPackageProcessorChain;

    public PackageProcessor() {
        this.firstPackageProcessorChain = new SmallPackageProcessor();
        PackageProcessorChain mediumPackageProcessor = new MediumPackageProcessor();
        PackageProcessorChain largePackageProcessor = new LargePackageProcessor();
        firstPackageProcessorChain.setNextChain(mediumPackageProcessor);
        mediumPackageProcessor.setNextChain(largePackageProcessor);
    }

    public PackageProcessorChain getFirstPackageProcessorChain() {
        return firstPackageProcessorChain;
    }
}

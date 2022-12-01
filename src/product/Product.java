package product;

/**
 * Base abstract product class for all the products creature can have
 */
public abstract class Product {
    protected final String name;
    protected final int level;
    protected final float price;
    protected final String description;
    protected final String productCode;

    /**
     * creates a new product
     * @param name name of the product
     * @param level level of the product
     * @param price price of the product
     * @param description description of the product
     */
    public Product(String name, int level, float price, String description){
        this.name = name;
        this.level = level;
        this.price = price;
        this.description = description;
        this.productCode = name.replace(" ", "_").toLowerCase();
    }

    /**
     * get the name of the product
     * @return string
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter for the level of the product
     * @return integer
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * getter of for the price of product
     * @return double
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * getter for the description of the product
     * @return string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * getter for the product code
     * @return string
     */
    public String getProductCode(){
        return this.productCode;
    }

    /**
     * To string for the product
     * @return string
     */
    @Override
    public String toString() {
        return "Product<"+this.productCode+">";
    }

    /**
     * equals method for comparison of product
     * @param o other product
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product)){
            return false;
        }
        Product other = (Product) o;
        return other.getProductCode().equals(this.getProductCode());
    }
}
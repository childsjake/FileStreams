import java.io.Serializable;

public class Product implements Serializable
{
    private String name;
    private String description;
    private String ID;
    private double cost;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return ID;
    }

    public double getCost() {
        return cost;
    }

    public String getFormattedName()
    {
        return String.format("%35s", name);
    }

    public String getFormattedDescription()
    {
        return String.format("%75s", description);
    }

    public String getFormattedID()
    {
        return String.format("%6s", ID);
    }

    public String toRandomAccess()
    {
        return getFormattedID() + getFormattedName() + getFormattedDescription() + cost;
    }


}

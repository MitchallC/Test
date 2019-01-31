package DTO;


import java.io.Serializable;


public class itemDTO implements Serializable
{
    private final int id;
    private final int productID;
    private final int storeID;
    private final int quantity;
    
public itemDTO(int id, int productID, int storeID, int quantity)
{
    this.id = id;
    this.productID = productID;
    this.storeID = storeID;
    this.quantity = quantity;
}

    public int getId()
    {
        return id;
    }

    public int getProductID()
    {
        return productID;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public int getQuantity()
    {
        return quantity;
    }

   






}

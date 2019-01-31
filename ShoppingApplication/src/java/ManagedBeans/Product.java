package ManagedBeans;

import Database.dbManager;
import java.io.Serializable;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;

@Named(value = "Products")
@RequestScoped

public class Product
{

    private String itemName;
    private int storeID;
    private int quantity;
    private int quantityRemove;
    boolean itemExists = false;
    private int itemID;
    private float itemPrice;
    private int newQuantity;
    private int oldQuantity;
    private int cost;

    public String addProduct()
    {
        boolean dataOK = false;
        try
        {

            Connection conn = dbManager.getConnection();

            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE) VALUES (?, ?)");
            stmt2.setString(1, itemName);
            stmt2.setFloat(1, itemPrice);

            PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?");
            stmt3.setString(1, itemName);
            ResultSet rs = stmt3.executeQuery();

            if (rs.next())
            {
                itemID = rs.getInt("PRODUCT_ID");
            }
            rs.close();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO STOCKED_BY (PRODUCT_ID , QUANTITY, STORE_ID) VALUES (?, ?, ?)");
            stmt.setInt(1, itemID);
            stmt.setInt(2, quantity);
            stmt.setInt(3, storeID);

            int rows = stmt.executeUpdate();

            dataOK = rows == 1;

            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {

        }
        if (dataOK)
        {
            return "adminHome";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item not added"));
            return null;
        }
    }

    public String removeProduct()
    {
        {
            boolean dataOK = false;
            try
            {

                Connection conn = dbManager.getConnection();

                PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?");
                stmt1.setString(1, itemName);
                ResultSet rs = stmt1.executeQuery();

                oldQuantity = rs.getInt("QUANTITY");
                newQuantity = oldQuantity - quantityRemove;
                PreparedStatement stmt = conn.prepareStatement("UPDATE STOCKED_BY SET QUANTITY = ? WHERE STORE_ID = ? ");
                stmt.setInt(1, newQuantity);
                stmt.setInt(2, storeID);

                int rows = stmt.executeUpdate();

                dataOK = rows == 1;

                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {

            }
            if (dataOK)
            {
                return "adminHome";
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item not added"));
                return null;
            }
        }
    }

    public int StokeBuyProduct()
    {
        {
            boolean dataOK = false;
            try
            {

                Connection conn = dbManager.getConnection();

                PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?");
                stmt1.setString(1, itemName);
                ResultSet rs = stmt1.executeQuery();

                oldQuantity = rs.getInt("QUANTITY");
                newQuantity = oldQuantity - quantityRemove;
                PreparedStatement stmt = conn.prepareStatement("UPDATE STOCKED_BY SET QUANTITY = ? WHERE STORE_ID =3");
                stmt.setInt(1, newQuantity);
                stmt.setInt(2, storeID);

                int rows = stmt.executeUpdate();

                dataOK = rows == 1;
                stmt1.close();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
            }

            if (dataOK)
            {

                try
                {

                    Connection conn = dbManager.getConnection();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_NAME = ?");
                    stmt2.setString(1, itemName);
                    ResultSet rs2 = stmt2.executeQuery();
                    cost = cost + rs2.getInt("PRODUCT_PRICE");
                    return cost;

                }
                catch (Exception e)
                {
                    return 0;
                }
            }
            else
            {
                return 0;
            }
        }
    }

    public int HanleyBuyProduct()
    {
        {
            boolean dataOK = false;
            try
            {

                Connection conn = dbManager.getConnection();

                PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?");
                stmt1.setString(1, itemName);
                ResultSet rs = stmt1.executeQuery();

                oldQuantity = rs.getInt("QUANTITY");
                newQuantity = oldQuantity - quantityRemove;
                PreparedStatement stmt = conn.prepareStatement("UPDATE STOCKED_BY SET QUANTITY = ? WHERE STORE_ID =1");
                stmt.setInt(1, newQuantity);
                stmt.setInt(2, storeID);

                int rows = stmt.executeUpdate();

                dataOK = rows == 1;
                stmt1.close();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
            }

            if (dataOK)
            {

                try
                {

                    Connection conn = dbManager.getConnection();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_NAME = ?");
                    stmt2.setString(1, itemName);
                    ResultSet rs2 = stmt2.executeQuery();
                    cost = cost + rs2.getInt("PRODUCT_PRICE");
                    return cost;

                }
                catch (Exception e)
                {
                    return 0;
                }
            }
            else
            {
                return 0;
            }
        }
    }

    public int StaffordshireBuyProduct()
    {
        {
            boolean dataOK = false;
            try
            {

                Connection conn = dbManager.getConnection();

                PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?");
                stmt1.setString(1, itemName);
                ResultSet rs = stmt1.executeQuery();

                oldQuantity = rs.getInt("QUANTITY");
                newQuantity = oldQuantity - quantityRemove;
                PreparedStatement stmt = conn.prepareStatement("UPDATE STOCKED_BY SET QUANTITY = ? WHERE STORE_ID =2");
                stmt.setInt(1, newQuantity);
                stmt.setInt(2, storeID);

                int rows = stmt.executeUpdate();

                dataOK = rows == 1;
                stmt1.close();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
            }

            if (dataOK)
            {

                try
                {

                    Connection conn = dbManager.getConnection();
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_NAME = ?");
                    stmt2.setString(1, itemName);
                    ResultSet rs2 = stmt2.executeQuery();
                    cost = cost + rs2.getInt("PRODUCT_PRICE");
                    return cost;

                }
                catch (Exception e)
                {
                    return 0;
                }
            }
            else
            {
                return 0;
            }
        }
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getItemID()
    {
        return itemID;
    }

    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public void setStoreID(int storeID)
    {
        this.storeID = storeID;
    }

    public float getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public int getQuantityRemove()
    {
        return quantityRemove;
    }

    public void setQuantityRemove(int quantityRemove)
    {
        this.quantityRemove = quantityRemove;
    }

}

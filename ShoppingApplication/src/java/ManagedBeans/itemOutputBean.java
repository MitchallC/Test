package ManagedBeans;

import DTO.itemDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "item")
@SessionScoped
public class itemOutputBean implements Serializable
{

    private itemDTO itemDetails = null;
    private int totalItems = 0;

    public String fetchItemDetails(int CustID)
    {
        try
        {
          Connection conn = Database.dbManager.getConnection();

            String sqlString = "SELECT * "
                    + "FROM STOCKED_BY"
                    + "WHERE STOCKED_BY_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                itemDetails = new itemDTO(
                        rs.getInt("STOCKED_BY_ID"),
                        rs.getInt("PRODUCT_ID"),
                        rs.getInt("STORE_ID"),
                        rs.getInt("QUANTITY"));
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return "view";
    }

    public itemDTO getItemDetails()
    {
        return itemDetails;
    }

    public ArrayList<itemDTO> getItemSummaries()
    {
        ArrayList<itemDTO> ItemSummaries = new ArrayList<>();
        try
        {
         Connection conn = Database.dbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT CUST_ID, fName FROM Customer");
            ResultSet rs = stmt.executeQuery();

            
            
            while (rs.next())
            {
                itemDTO item = new itemDTO(rs.getInt("STOCKED_BY_ID"), rs.getInt("PRODUCT_ID"), rs.getInt("STORE_ID"), rs.getInt("QUANTITY"));
              ItemSummaries.add(item);
            }

            
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        totalItems = ItemSummaries.size();
        return ItemSummaries;
    }

    public int getTotalItems()
    {
        return totalItems;
    }

    public void setItemDetails(itemDTO itemDetails)
    {
        this.itemDetails = itemDetails;
    }

}
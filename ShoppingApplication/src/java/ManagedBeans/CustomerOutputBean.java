package ManagedBeans;

import DTO.customerDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "customer")
@SessionScoped
public class CustomerOutputBean implements Serializable
{

    private customerDTO customerDetails = null;
    private int totalCustomers = 0;

    public String fetchCustomerDetails(int CustID)
    {
        try
        {
          Connection conn = Database.dbManager.getConnection();

            String sqlString = "SELECT * "
                    + "FROM CUSTOMER"
                    + "WHERE CUST_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = new customerDTO(
                        rs.getInt("CUST_ID"),
                        rs.getString("CUST_F_NAME"),
                        rs.getString("CUST_L_NAME"),
                        rs.getString("CUST_USERNAME"),
                        rs.getString("CUST_PASSWORD"));
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return "view";
    }

    public customerDTO getCustomerDetails()
    {
        return customerDetails;
    }

    public ArrayList<customerDTO> getCustomerSummaries()
    {
        ArrayList<customerDTO> customerSummaries = new ArrayList<>();
        try
        {
         Connection conn = Database.dbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT CUST_ID, fName FROM Customer");
            ResultSet rs = stmt.executeQuery();

            
            
            while (rs.next())
            {
                customerDTO cust = new customerDTO(rs.getInt("CUST_ID"), rs.getString("CUST_F_NAME"), rs.getString("CUST_L_NAME"), rs.getString("CUST_USERNAME"), rs.getString("CUST_PASSWORD"));
                customerSummaries.add(cust);
            }

            
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        totalCustomers = customerSummaries.size();
        return customerSummaries;
    }

    public int getTotalCustomers()
    {
        return totalCustomers;
    }

    public void setCustomerDetails(customerDTO customerDetails)
    {
        this.customerDetails = customerDetails;
    }

}
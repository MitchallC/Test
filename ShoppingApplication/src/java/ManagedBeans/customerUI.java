package ManagedBeans;

import Database.dbManager;
import java.io.Serializable;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Named(value = "customerUI")
@RequestScoped

public class customerUI implements Serializable
{
    private String fName;
    private String lName;
    private String username;
    private String password1;
    private String password2; 
    private int CustID; 
    private boolean credentialsOK = false;
    private customerUI customerDetails = null;
    private int totalCustomers = 0;
    
    public customerUI()
    {
    }
    public String register()
    {
        boolean dataOK = false;

        if (password1.equals(password2))
        {
            try
            {
                byte[] hash = MessageDigest.getInstance("SHA-256").digest(password1.getBytes(StandardCharsets.UTF_8));
                password1 = Base64.getEncoder().encodeToString(hash);

                    Connection conn = dbManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO CUSTOMER (CUST_F_NAME, CUST_L_NAME, CUST_USERNAME, CUST_PWD) VALUES (?, ?, ?, ?)");
                    stmt.setString(1, fName);
                    stmt.setString(2, lName);
                    stmt.setString(3, username);
                    stmt.setString(4, password1);
                    int rows = stmt.executeUpdate();
                    
                    dataOK = rows == 1;
                    
                    stmt.close();
                    conn.close();
            }
        
                        catch (Exception e)
            {
                //Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, e.toString());
            }
        }   
         if (dataOK)
        {
            return "customerLogin";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials are not correct"));
            return null;
        }
    }
    
    
    
    

       public String checkCredentials()
    {
        credentialsOK = false;
        try
        {
            Connection conn = dbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADMIN WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            credentialsOK = rs.next() && rs.getString("pwd").equals(password1);

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            Logger.getLogger(adminUI.class.getName()).log(Level.SEVERE, e.toString());
        }

        if (credentialsOK)
        {
            return "adminHome";
        }
        else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct"));
            return null;
        }
    
    }

     private void clearCredentials()
    {
        this.fName = "";
        this.lName = "";
        this.username = "";
        this.password1 = "";
        this.password2 = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    

    public String getfName()
    {
        return fName;
    }

    public String getlName()
    {
        return lName;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword1()
    {
        return password1;
    }

    public String getPassword2()
    {
        return password2;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword1(String password1)
    {
        this.password1 = password1;
    }

    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }
    
    
}

    
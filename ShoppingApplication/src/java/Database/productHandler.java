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



@Named(value = "Product")
@RequestScoped

public class productHandler
{
    
}

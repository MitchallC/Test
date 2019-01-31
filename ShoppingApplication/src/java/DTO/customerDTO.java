package DTO;


import java.io.Serializable;


public class customerDTO implements Serializable
{
    private final int id;
    private final String fName;
    private final String lName;
    private final String username;
    private final String password;
    
public customerDTO(int id, String fName, String lName, String username, String password)
{
    this.id = id;
    this.fName = fName;
    this.lName = lName;
    this.username = username;
    this.password = password;
}

    public int getId()
    {
        return id;
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

    public String getPassword()
    {
        return password;
    }
  






}

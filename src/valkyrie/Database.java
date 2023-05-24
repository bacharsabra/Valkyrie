package valkyrie;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Database {

    //this method establishes connection with database
    public static Connection ConnectDB() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/valkyrie", "root", "");
            System.out.println("Database Connected.");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed.");
            System.out.println("Database.ConnectDB():" + e);
        }

        return conn;
    }

    //this method return InputStream for image
    public InputStream getImage(int id) {
        String query = "SELECT Image FROM Donors WHERE ID = " + id;

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs;
        InputStream is = null;
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            rs.next();
            is = rs.getBinaryStream("Image");
            System.out.println(query);
        } catch (SQLException ex) {
            System.out.println("Database.getImage()" + ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.viewProfile():" + e);
            }
        }
        return is;
    }

    //this method used for retrieving data to show in profile
    public Donor viewProfile(int id) {
        String query = "SELECT * FROM Donors WHERE ID = '" + id + "'";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Donor donor = new Donor();
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            rs.next();

            donor.setFirstName(rs.getString("First_Name"));
            donor.setLastName(rs.getString("Last_Name"));
            donor.setFathersName(rs.getString("Fathers_Name"));
            donor.setGender(rs.getString("Gender"));
            donor.setAge(rs.getInt("Age"));
            donor.setWeight(rs.getString("Weight"));
            donor.setBloodType(rs.getString("Blood_Type"));
            donor.setIdentificationType(rs.getString("Identification_Type"));
            donor.setIDNo(rs.getString("ID_No"));
            donor.setMedical(rs.getString("Medical"));
            donor.setMobileNo(rs.getString("Mobile_No"));
            donor.setLandlineNo(rs.getString("Landline_No"));
            donor.setEMail(rs.getString("E_Mail"));
            donor.setAddress(rs.getString("Address"));
            donor.setLastDonation(rs.getDate("Last_Donation"));
            donor.setPicture(rs.getBinaryStream("Image"));

            System.out.println(query);
            System.out.println("Fetched Profile");
        } catch (SQLException e) {
            System.out.println("Database.viewProfile(int id):" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.viewProfile():" + e);
            }
        }
        return donor;
    }

    //this method used for specific query in database
    public ArrayList<Donor> search(String value) {
        String query = "SELECT ID, First_Name, Last_Name, Gender, Age, Weight, Blood_Type,"
                + " Mobile_No, Landline_No, E_Mail, Address FROM Donors"
                + " WHERE " + value;

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Donor> donorsList = new ArrayList();
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                donorsList.add(new Donor(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString(),
                        rs.getObject(3).toString(), rs.getObject(4).toString(), Integer.parseInt(rs.getObject(5).toString()), rs.getObject(6).toString(),
                        rs.getObject(7).toString(), rs.getObject(8).toString(), rs.getObject(9).toString(), rs.getObject(10).toString(), rs.getObject(11).toString()));
            }
            System.out.println(query);
            System.out.println("Data Fetched");
        } catch (SQLException e) {
            System.out.println("Database.search(String value):" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.search():" + e);
            }
        }
        return donorsList;
    }

    //this method retrieves data from database and return them as ResultSet
    public ArrayList<Donor> getDataForTable() {
        String query = "SELECT ID, First_Name, Last_Name, Gender, Age, Weight, Blood_Type,"
                + " Mobile_No, Landline_No, E_Mail, Address FROM Donors";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Donor> donorsList = new ArrayList();

        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                donorsList.add(new Donor(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString(),
                        rs.getObject(3).toString(), rs.getObject(4).toString(), Integer.parseInt(rs.getObject(5).toString()), rs.getObject(6).toString(),
                        rs.getObject(7).toString(), rs.getObject(8).toString(), rs.getObject(9).toString(), rs.getObject(10).toString(), rs.getObject(11).toString()));
            }
            System.out.println(query);
            System.out.println("Data Fetched");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Occured While Fetching Data!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Database.getDataForTable():" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.getDataForTable():" + e);
            }
        }
        return donorsList;
    }

    //this method inserts data into database
    public void insert(Donor donor) {
        int age = donor.getAge();
        String fName = donor.getFirstName();
        String lName = donor.getLastName();
        String faName = donor.getFathersName();
        String gender = donor.getGender();
        String weight = donor.getWeight();
        String bloodType = donor.getBloodType();
        String idType = donor.getIdentificationType();
        String idNo = donor.getIDNo();
        String medical = donor.getMedical();
        String mobile = donor.getMobileNo();
        String landline = donor.getLandlineNo();
        String eMail = donor.getEMail();
        String address = donor.getAddress();
        InputStream isImage = donor.getPicture();
        Date lstDonation = null;
        try {
        	lstDonation = new Date(donor.getLastDonation().getTime());
        }
        catch (Exception ex) {
        	lstDonation = null;
        }

        String sql = "INSERT INTO Donors"
                + "(First_Name, Last_Name, Fathers_Name, Gender, Age, Weight,"
                + " Blood_Type, Identification_Type, ID_No, Last_Donation, Medical,"
                + " Mobile_No, Landline_No, E_Mail, Address, Image)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(sql);

            pst.setString(1, fName);
            pst.setString(2, lName);
            pst.setString(3, faName);
            pst.setString(4, gender);
            pst.setInt(5, age);
            pst.setString(6, weight);
            pst.setString(7, bloodType);
            pst.setString(8, idType);
            pst.setString(9, idNo);
            pst.setDate(10, lstDonation);
            pst.setString(11, medical);
            pst.setString(12, mobile);
            pst.setString(13, landline);
            pst.setString(14, eMail);
            pst.setString(15, address);
            pst.setBlob(16, isImage);

            pst.executeUpdate();

            System.out.println("Data Inserted Successfully.");
        } catch (SQLException e) {
            System.out.println("Database.insert():" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.insert():" + e);
            }
        }
    }

    //this method update data
    public void update(Donor donor) {
        int id = donor.getID();
        int age = donor.getAge();
        String fName = donor.getFirstName();
        String lName = donor.getLastName();
        String faName = donor.getFathersName();
        String gender = donor.getGender();
        String weight = donor.getWeight();
        String bloodType = donor.getBloodType();
        String idType = donor.getIdentificationType();
        String idNo = donor.getIDNo();
        String medical = donor.getMedical();
        String mobile = donor.getMobileNo();
        String landline = donor.getLandlineNo();
        String eMail = donor.getEMail();
        String address = donor.getAddress();
        InputStream isImage = donor.getPicture();
        Date lstDonation = null;
        if(donor.getLastDonation() != null)
            lstDonation = new Date(donor.getLastDonation().getTime());

        String sql = "UPDATE Donors SET "
                + "First_Name=?, Last_Name=?, Fathers_Name=?, Gender=?, Age=?, Weight=?,"
                + "Blood_Type=?, Identification_Type=?, ID_No=?, Last_Donation=?, Medical=?, Mobile_No=?,"
                + "Landline_No=?, E_Mail=?, Address=?, Image=? WHERE ID=" + id;

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(sql);

            System.out.println(sql);

            pst.setString(1, fName);
            pst.setString(2, lName);
            pst.setString(3, faName);
            pst.setString(4, gender);
            pst.setInt(5, age);
            pst.setString(6, weight);
            pst.setString(7, bloodType);
            pst.setString(8, idType);
            pst.setString(9, idNo);
            pst.setDate(10, lstDonation);
            pst.setString(11, medical);
            pst.setString(12, mobile);
            pst.setString(13, landline);
            pst.setString(14, eMail);
            pst.setString(15, address);
            pst.setBlob(16, isImage);

            pst.executeUpdate();

            System.out.println("Data Updated Successfully.");
        } catch (SQLException e) {
            System.out.println("Database.update():" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.update():" + e);
            }
        }
    }

    //this method delete data from database
    public void delete(int id) {
        String del = "DELETE FROM Donors WHERE ID = '" + id + "'";
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(del);
            pst.executeUpdate();

            System.out.println(id + " No Data Deleted Successfully.");
        } catch (SQLException e) {
            System.out.println("Database.delete(int id):" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Database Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.delete(int id):" + e);
            }
        }
    }
    
	public String bloodCount(String bloodType) {
		
		String q="SELECT Count(Blood_Type) FROM Donors WHERE Blood_Type =?";
		String count = "";
		Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
		try{
			conn = ConnectDB();
	        pst = conn.prepareStatement(q);
	        pst.setString(1, bloodType);
	        rs = pst.executeQuery();
	        rs.next();

			count = rs.getString(1);

			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return count;
	}
}
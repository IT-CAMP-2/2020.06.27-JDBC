package pl.camp.it;

import pl.camp.it.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        readDatabase();
    }

    public static void saveToDatabase(Customer customer) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/it-camp2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8","root","");
            //here sonoo is database name, root is username and password

            Statement statement = con.createStatement();

            statement.executeUpdate("insert into tcustomer (name, surname, pesel) " +
                    "values ('" + customer.getName() + "', '" + customer.getSurname() + "', " + customer.getPesel() + ")");

            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> readDatabase() {
        List<Customer> resultList = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/it-camp2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            //here sonoo is database name, root is username and password

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from tcustomer");
            while(rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setPesel(rs.getString("pesel"));

                resultList.add(customer);

                System.out.println("------------");
                System.out.println("Klient:");
                System.out.println(customer.getId());
                System.out.println(customer.getName());
                System.out.println(customer.getSurname());
                System.out.println(customer.getPesel());
            }

            con.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return resultList;
    }
}

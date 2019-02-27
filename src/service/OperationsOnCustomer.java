package service;

import com.mysql.cj.protocol.Resultset;
import connection.ConnectionToDatabase;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationsOnCustomer {

    public static String getFirstNameOfClient() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type first name of the Client:");
        return scanner.nextLine();
    }

    public static String getLastNameOfClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type last name of the Client:");
        return scanner.nextLine();
    }

    public static String getEmailfClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type email of the Client:");
        return scanner.nextLine();
    }

    public static String getPhonefClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type phone of the Client:");
        return scanner.nextLine();
    }

    public static boolean insertNewCustomer(final String lastName, final String firstName,
                                            final String email, final String phone) {

        boolean isCustomerAdded = false;
        String parametrizedQuery = "INSERT INTO Customers (lastName, firstName, email, phone) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            isCustomerAdded = preparedStatement.execute();
        } catch (SQLException s) {
            System.out.println(s);
        }
        return isCustomerAdded;
    }

    public static boolean deleteCustomer() {

        List<Customer> customerList = new ArrayList<>();
        boolean isCustomerDeleted = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type lastName of client wanted to delete");
        String customerToDelete = scanner.nextLine();


        String parametrizedQuery = "SELECT * FROM Customers WHERE lastName = " + "'" + customerToDelete + "'";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            Statement statement = ConnectionToDatabase.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery(parametrizedQuery);

            while (resultset.next()) {
                int customerId = resultset.getInt("customerId");
                String lastName = resultset.getString("lastName");
                String firstName = resultset.getString("firstName");
                String email = resultset.getString("email");
                String phone = resultset.getString("phone");

                Customer customer = new Customer(customerId, lastName, firstName, email, phone);
                customerList.add(customer);
            }
        } catch (SQLException s) {
            System.out.println(s);
        }

        if (customerList.size() == 1) {
            String parametrizedQueryToDelete = "DELETE FROM Customers WHERE lastName = ? ";
            try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQueryToDelete)) {
                preparedStatement.setString(1,customerToDelete);
                isCustomerDeleted = preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            System.out.println("Customer is deleted");
            return isCustomerDeleted;
        }

        if (customerList.size() < 1) {
            System.out.println("There is no person with last name " + customerToDelete);
        }

        if (customerList.size() > 1) {
            System.out.println("There are more customers with the same surname." + customerList +  "Select ID of person you want to delete: ");
            int idOfCustomerToDelete = scanner.nextInt();
            String parametrizedQueryToDelete = "DELETE FROM Customers WHERE customerId = " + idOfCustomerToDelete;
            try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQueryToDelete)) {
                isCustomerDeleted = preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Customer is deleted");
        return isCustomerDeleted;
    }

        public static boolean isCustomerUpdated(){

        boolean isUpdated=false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select id of customer you want to update");
        int idToUpdate = scanner.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Type updated LastName");
        String updatedLastName = scanner1.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Type updated FirstName");
        String updatedFirstName = scanner2.nextLine();
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Type email");
        String updatedEmail = scanner3.nextLine();
        Scanner scanner4 = new Scanner(System.in);
        System.out.println("Type phone");
        String updatedPhone = scanner4.nextLine();

        String parametrizedQuery = "UPDATE Customers SET customerId = ?, lastName = ?, firstName = ?, email= ?, phone = ?";
        try(PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {

            preparedStatement.setInt(1, idToUpdate);
            preparedStatement.setString(2, updatedLastName);
            preparedStatement.setString(3, updatedFirstName);
            preparedStatement.setString(4, updatedEmail);
            preparedStatement.setString(5, updatedPhone);

            isUpdated = preparedStatement.executeUpdate()==1;

        } catch(SQLException ex){
            System.out.println(ex);
        }

        System.out.println("Customer is updated");
        return isUpdated;
        }
}








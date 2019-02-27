package service;

import connection.ConnectionToDatabase;
import model.Rents;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationsOnRents {

    public static String getRentedDate() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type rentedDate in format dd-mm-yyyy: ");
        String date = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        return String.valueOf(format.parse(date));
    }

    public static String getReturnedDate() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type returnedDate in format dd-mm-yyyy: ");
        String date = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        return String.valueOf(format.parse(date));
    }

    public static int getCustomerRentingBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type customerId: ");
        return scanner.nextInt();
    }

    public static String getStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type status of the book: IN_RENT or RETURNED");
        return scanner.nextLine();
    }

    public static int getBookCopyId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id of the bookCopy: ");
        return scanner.nextInt();
    }

    ///TOTAL PRICE ADDITIONAL METHOD!!!!!
    public static boolean insertNewRent(final String rentedDate, final String returnDate, final int rentedTo, final int rentCopyId, final String status, final BigDecimal totalPrice) {

        boolean isRentAdded = false;
        String parametrizedQuery = "INSERT INTO Rents (rentedDate, returnDate, rentedTo, rentCopyId, status, totalPrice) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setString(1, rentedDate);
            preparedStatement.setString(2, returnDate);
            preparedStatement.setInt(3, rentedTo);
            preparedStatement.setInt(4, rentCopyId);
            preparedStatement.setString(5, status);
            preparedStatement.setBigDecimal(6, totalPrice);
            isRentAdded = preparedStatement.execute();
        } catch (SQLException s) {
            System.out.println(s);
        }
        return isRentAdded;
    }

    public static boolean deleteRent() {

        List<Rents> rentsList = new ArrayList<>();
        boolean isRentDeleted = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id of the rent you want to delete");
        int rentToDelete = scanner.nextInt();

        String parametrizedQuery = "SELECT * FROM Rents WHERE rentsId =" + rentToDelete;
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            Statement statement = ConnectionToDatabase.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery(parametrizedQuery);

            while (resultset.next()) {
                int rentId = resultset.getInt("rentsId");
                String rentedDate = resultset.getString("rentedDate");
                String returnDate = resultset.getString("returnDate");
                int rentedTo = resultset.getInt("rentedTo");
                int rentCopyId = resultset.getInt("rentCopyId");
                String status = resultset.getString("status");
                BigDecimal totalPrice = resultset.getBigDecimal("totalPrice");

                Rents rents = new Rents(rentId, rentedDate, returnDate, rentedTo, rentCopyId,status,totalPrice);
                rentsList.add(rents);
            }
        } catch (SQLException s) {
            System.out.println(s);
        }

            String parametrizedQueryToDelete = "DELETE FROM Rents WHERE rentsId = ?";
            try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQueryToDelete)) {
                preparedStatement.setInt(1, rentToDelete);
                isRentDeleted = preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            System.out.println("Rent is deleted");
            return isRentDeleted;
        }

    public static boolean updateRent(){ /// ON DELETE CASCADE ON UPDATE CASCADE ???? // CHANGE STATUS

        boolean isUpdated=false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select id of rent you want to update:");
        int idToUpdate = scanner.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Type rentedDate to upDate:");
        String updatedRentedDate = scanner1.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Type returnDate to upDate:");
        String updateReturnDate = scanner2.nextLine();
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Type id of the customer renting a book:");
        int idCustomer = scanner3.nextInt();
        Scanner scanner4 = new Scanner(System.in);
        System.out.println("Type copyId of the book rented:");
        int copyId = scanner4.nextInt();
        Scanner scanner5 = new Scanner(System.in);
        System.out.println("Type status : IN_RENT or RETURNED "); ////???ENUM !!!
        String status = scanner5.nextLine();

        String parametrizedQuery = "UPDATE Rents SET rentedDate = ?, returnDate = ?, rentedTo = ?, rentCopyId= ?, status = ? WHERE rentsId= " + idToUpdate;
        try(PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setString(1, updatedRentedDate );
            preparedStatement.setString(2, updateReturnDate);
            preparedStatement.setInt(3, idCustomer);
            preparedStatement.setInt(4, copyId);
            preparedStatement.setString(5,status);

            isUpdated = preparedStatement.executeUpdate()==1;

        } catch(SQLException ex){
            System.out.println(ex);
        }

        System.out.println("Rent is updated");
        return isUpdated;
    }
}

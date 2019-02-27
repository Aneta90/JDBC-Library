package service;

import connection.ConnectionToDatabase;
import model.BookCopy;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationsOnBookCopy {

    public static int getCustomerId() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id of customer you want to rent a book: ");
        return scanner.nextInt();
    }

    public static int getBookInfoId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type bookInfoId: ");
        return scanner.nextInt();
    }

    public static double getPricePerDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type price per day: ");
        return scanner.nextDouble();
    }


    public static boolean insertNewBookCopy(final int rentedTo,final int bookInfoId, final double pricePerDay) {

        boolean isBookCopyAdded = false;
        String parametrizedQuery = "INSERT INTO bookCopy (rentedTo, bookInfoId, pricePerDay) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setInt(1,rentedTo);
            preparedStatement.setInt(2,bookInfoId);
            preparedStatement.setDouble(3,pricePerDay);
            isBookCopyAdded = preparedStatement.execute();
        } catch (SQLException s) {
            System.out.println(s);
        }
        return isBookCopyAdded;
    }

    public static boolean deleteBook() {

        List<BookCopy> bookCopiesList = new ArrayList<>();
        boolean isBookCopyDeleted = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type title of the book you want to delete");
        String bookCopyToDelete = scanner.nextLine();


        String parametrizedQuery = "SELECT * FROM BookCopy WHERE bookInfoId = (SELECT bookInfoId FROM BookInfo WHERE title = ?";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            Statement statement = ConnectionToDatabase.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery(parametrizedQuery);

            while (resultset.next()) {
                int bookCopyId = resultset.getInt("bookCopyId");
                boolean isRented = resultset.getBoolean("isRented");
                int rentedTo = resultset.getInt("rentedTo");
                int bookInfoId = resultset.getInt("bookInfoId");
                double pricePerDay = resultset.getDouble("pricePerDay");

                BookCopy bookCopy = new BookCopy(bookCopyId, isRented, rentedTo, bookInfoId, pricePerDay);
                bookCopiesList.add(bookCopy);
            }
        } catch (SQLException s) {
            System.out.println(s);
        }

        if (bookCopiesList.size() == 1) {
            String parametrizedQueryToDelete = "DELETE FROM BookCopy WHERE bookCopyId = ?";
            try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQueryToDelete)) {
                preparedStatement.setString(1,bookCopyToDelete);
                isBookCopyDeleted = preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            System.out.println("BookCopy is deleted");
            return isBookCopyDeleted;
        }

        if (bookCopiesList.size() < 1) {
            System.out.println("There is no bookCopy with bookInfoId " + bookCopyToDelete);
        }

        if (bookCopiesList.size() > 1) {
            System.out.println("There are more bookCopies with the same title." + "Select ID of bookCopy you want to delete: ");
            int idOfBookCopyToDelete = scanner.nextInt();
            String parametrizedQueryToDelete = "DELETE FROM BookCopy WHERE bookCopyId = " + idOfBookCopyToDelete;
            try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQueryToDelete)) {
                isBookCopyDeleted = preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Customer is deleted");
        return isBookCopyDeleted;
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

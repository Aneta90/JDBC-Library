package service;

import connection.ConnectionToDatabase;
import model.BookCopy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationsOnBookCopy {

    public static void getBookCopyById() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id of the book you are interested in: ");
        int id = scanner.nextInt();
        String parametrizedQuery = "SELECT * FROM bookcopy WHERE bookCopyId = ?";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    int bookCopyId = resultset.getInt("bookCopyId");
                    boolean isRented = resultset.getBoolean("isRented");
                    int rentedTo = resultset.getInt("rentedTo");
                    int bookInfoId = resultset.getInt("bookInfoId");
                    double pricePerDay = resultset.getDouble("pricePerDay");
                    System.out.println("The book you are interested in:" + bookCopyId + " " + isRented + " " + rentedTo + " " + bookInfoId + " " + pricePerDay);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static boolean insertNewBookCopy(final int rentedTo, final int bookInfoId, final double pricePerDay) {

        boolean isBookCopyAdded = false;
        String parametrizedQuery = "INSERT INTO bookCopy (rentedTo, bookInfoId, pricePerDay) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setInt(1, rentedTo);
            preparedStatement.setInt(2, bookInfoId);
            preparedStatement.setDouble(3, pricePerDay);
            isBookCopyAdded = preparedStatement.execute();
        } catch (SQLException s) {
            System.out.println(s);
        }

        System.out.println("Book copy is added");
        return isBookCopyAdded;
    }

    public static boolean deleteBook() {

        List<BookCopy> bookCopiesList = new ArrayList<>();
        boolean isBookCopyDeleted = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id of the bookCopy you want to delete");
        int bookCopyToDelete = scanner.nextInt();

        String parametrizedQueryToDelete = "DELETE FROM BookCopy WHERE bookCopyId = ?";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQueryToDelete)) {
            preparedStatement.setInt(1, bookCopyToDelete);
            isBookCopyDeleted = preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("BookCopy is deleted");
        return isBookCopyDeleted;
    }


    public static boolean isBookCopyUpdated(){

        boolean isUpdated=false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select id of bookCopy you want to update");
        int idToUpdate = scanner.nextInt();

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Is rented? TRUE/FALSE");
        Boolean isBookRented = scanner1.hasNext();

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Type id of customer who rents the copy");
        int customerId = scanner2.nextInt();

        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Type bookInfoId");
        int bookInfoId = scanner3.nextInt();

        Scanner scanner4 = new Scanner(System.in);
        System.out.println("Type price per day");
        double pricePerDay = scanner4.nextDouble();

        String parametrizedQuery = "UPDATE BookCopy SET bookCopyId = ?, isRented = ?, rentedTo = ?, bookInfoId= ?, pricePerDay = ?";

        try(PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {

            preparedStatement.setInt(1, idToUpdate);
            preparedStatement.setBoolean(2, isBookRented);
            preparedStatement.setInt(3, customerId);
            preparedStatement.setInt(4, bookInfoId);
            preparedStatement.setDouble(5, pricePerDay);

            isUpdated = preparedStatement.executeUpdate()==1;

        } catch(SQLException ex){
            System.out.println(ex);
        }

        System.out.println("BookCopy is updated");
        return isUpdated;
    }
}

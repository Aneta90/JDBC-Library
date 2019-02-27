package service;

import connection.ConnectionToDatabase;
import model.BookCopy;
import model.BookInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationsOnBookInfo {

    public static String getBookitle() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type title of book: ");
        return scanner.next();
    }

    public static String getBookDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type description of book: ");
        return scanner.next();
    }

    public static boolean insertNewBook (final String title,final String bookDescription){

        boolean isBookAdded = false;
        String parametrizedQuery = "INSERT INTO BookInfo (title, bookDescription) VALUES (?,?)";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, bookDescription);
            isBookAdded = preparedStatement.execute();
        } catch (SQLException s) {
            System.out.println(s);
        }
        return isBookAdded;
    }

    public static boolean deleteBookInfo() {

        boolean isBookInfoDeleted = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type title of the book you want to delete");
        String bookToDelete = scanner.nextLine();


        String parametrizedQuery = "DELETE FROM BookInfo WHERE title = ?";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            Statement statement = ConnectionToDatabase.getConnection().createStatement();
            //ResultSet resultset = statement.executeQuery(parametrizedQuery);
            preparedStatement.setString(1,bookToDelete);
            isBookInfoDeleted = preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("BookCopy is deleted");
        return isBookInfoDeleted;

           /* while (resultset.next()) {
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
            String parametrizedQueryToDelete = "DELETE FROM BookCopy WHERE bookInfoId = ?";
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
        return isBookCopyDeleted;*/
        }


    public static boolean isBoookInfoUpdated(){

        boolean isUpdated=false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select id of book you want to update");
        int idToUpdate = scanner.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Type updated title");
        String updateTitle = scanner1.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Type updated description");
        String updateDescription = scanner2.nextLine();

        String parametrizedQuery = "UPDATE BookInfo SET bookInfoId = ?, title = ?, description = ?";
        try(PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {

            preparedStatement.setInt(1, idToUpdate);
            preparedStatement.setString(2, updateTitle);
            preparedStatement.setString(3, updateDescription);

            isUpdated = preparedStatement.executeUpdate()==1;

        } catch(SQLException ex){
            System.out.println(ex);
        }

        System.out.println("BookInfo is updated");
        return isUpdated;
    }


}

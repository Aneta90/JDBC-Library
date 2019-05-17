package service;

import connection.ConnectionToDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OperationsOnBookInfo {

    public static void getBookInfoByTitle() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type title of book: ");
        String bookTitle = scanner.nextLine();

        String parametrizedQuery = "SELECT * FROM bookinfo WHERE title = ?";
        try (PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {
            preparedStatement.setString(1, bookTitle);

            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    int bookInfoId = resultset.getInt("bookInfoId");
                    String description = resultset.getString("bookDescription");
                    System.out.println("The book you are interested in" + bookInfoId + " " + description);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
        System.out.println("New Book is added");
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
            ResultSet resultset = statement.executeQuery(parametrizedQuery);
            preparedStatement.setString(1,bookToDelete);
            isBookInfoDeleted = preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("BookCopy is deleted");
        return isBookInfoDeleted;

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

        String parametrizedQuery = "UPDATE BookInfo SET title = ?, bookDescription = ? WHERE bookInfoId = ?";
        try(PreparedStatement preparedStatement = ConnectionToDatabase.getConnection().prepareStatement(parametrizedQuery)) {

            preparedStatement.setString(1, updateTitle);
            preparedStatement.setString(2, updateDescription);
            preparedStatement.setInt(3, idToUpdate);

            isUpdated = preparedStatement.executeUpdate()==1;

        } catch(SQLException ex){
            System.out.println(ex);
        }

        System.out.println("BookInfo is updated");
        return isUpdated;
    }


}

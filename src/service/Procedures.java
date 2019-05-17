package service;

import java.sql.*;

public class Procedures {

    public static void getRentsByCustomer(final int customerId, final Connection connection){

        String callableQuery = "{call BooksRentedByCustomer(?,?,?)}";
        try(CallableStatement callableStatement = connection.prepareCall(callableQuery)){
            callableStatement.setInt(1,customerId);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3,Types.INTEGER);

            callableStatement.execute();

            int numberOfBooksReturned = callableStatement.getInt("numberOfBooksReturned");
            int numberOfBooksInRent = callableStatement.getInt("numberOfBooksInRent");

            System.out.println("Customer with Id " + customerId + " has already returned " + numberOfBooksReturned + " and he still has " + numberOfBooksInRent + " book/s to return ");

        }
        catch (SQLException ex){
            System.out.println(ex);
        }
    }

    public static void getDiscountByCustomer (final int customerId, final Connection connection){

        String callableQuery = "{call GetDiscountByCustomer(?,?,?)}";
        try(CallableStatement callableStatement = connection.prepareCall(callableQuery)){
            callableStatement.setInt(1,customerId);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();

            int rentedTimes = callableStatement.getInt("rentedTimes");
            double discount = callableStatement.getInt("discount");

            System.out.println("Customer with id: " + customerId + " has got a discount in the amount of " + discount + ". He/she rented the book " + rentedTimes + " times");
        }
        catch (SQLException ex){
            System.out.println(ex);
        }
    }

    public static void getBestSeller(final Connection connection){

        String callableQuery = "{call GetBestseller(?,?)}";
        try(CallableStatement callableStatement = connection.prepareCall(callableQuery)){
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.registerOutParameter(2,Types.INTEGER);
            callableStatement.execute();

            String title = callableStatement.getString("the_best_title");
            int title_occurence = callableStatement.getInt("title_occurence");

            System.out.println("The best title is: " + title + ". It was rented " + title_occurence + " times");

        }
        catch (SQLException ex){
            System.out.println(ex);
        }
    }


    ///do poprawy TYPES.CURSOR???
    public static void getEmailList(final String emailList, final Connection connection) {

        String userMail = null;
        String userName = null;

        String callableQuery = "{call customer_email_list(?,?)}";
        try(CallableStatement callableStatement = connection.prepareCall(callableQuery)) {
            callableStatement.setString(1, emailList);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.executeUpdate();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

        while (resultSet.next()) {
            userMail = resultSet.getString("end_of_the_list");
            userName = resultSet.getString("email");
        }

            System.out.println(userMail);
            System.out.println(userName);

            } catch (SQLException sql){
            System.out.println(sql);
        }

    }

}

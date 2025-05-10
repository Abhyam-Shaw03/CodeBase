import java.sql.*;

public class DAO {
    public static void newResrevation(Connection con, ReservationDetails guest) throws SQLException {
        try{
            String query = Query.newReservation;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,guest.getName());
            preparedStatement.setString(2,guest.getMobile());
            preparedStatement.setString(3, guest.getRoom_no());

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0)
                System.out.println("\nInsertion Successfull..!!\n" + rowsAffected + " affected..!!");
            else
                System.out.println("\nInsertion Failed..!!\n" + rowsAffected + " row/s affected..!!");

            preparedStatement.close();
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteReservation(Connection con, ReservationDetails guest){
        try{
            String query = Query.deleteReservation;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, guest.getId());
            preparedStatement.setString(2, guest.getRoom_no());

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0)
                System.out.println("\nDeletion Successfull..!!\n" + rowsAffected + " affected..!!");
            else
                System.out.println("\nDeletion Failed..!!\n" + rowsAffected + " row/s affected..!!");

            preparedStatement.close();
            return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void showReservation(Connection con){
        try{
            String query = Query.showReservations;
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String mobile = resultSet.getString("mobile");
                String room_no = resultSet.getString("room_no");
                String date_of_booking = resultSet.getString("date_of_booking").toString();

                System.out.println("\n" + new ReservationDetails(id,name,mobile,room_no,date_of_booking));
            }
            statement.close();
            return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void getRoomNumber(Connection con, ReservationDetails getRoom){
        try{
            String query = Query.getRoomNo;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, getRoom.getName());
            preparedStatement.setString(2, getRoom.getMobile());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String room_no = resultSet.getString("room_no");
                System.out.println("\nRoom Number of " + getRoom.getName() + " is : " + room_no + ".");
            }

            preparedStatement.close();
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateReservation(Connection con, ReservationDetails guest, String old_roomNo){
        try{
            String query = Query.updateReservation;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, guest.getName());
            preparedStatement.setString(2, guest.getMobile());
            preparedStatement.setString(3, guest.getRoom_no());
            preparedStatement.setString(4, old_roomNo);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0)
                System.out.println("\nUpdate Successfull..!!\n" + rowsAffected + " affected..!!");
            else
                System.out.println("\nUpdate Failed..!!\n" + rowsAffected + " row/s affected..!!");

            preparedStatement.close();
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

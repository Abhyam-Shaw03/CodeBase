import java.sql.Connection;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfull..!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = HotelDB.connect();
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("\n\n********** HOTEL BOOKING SYSTEM ***********");
                System.out.println("1. NEW RESERVATION\n2. UPDATE RESERVATION\n3. GET ROOM DETAILS");
                System.out.println("4. SHOW RESERVATIONS\n5. DELETE RESERVATION\n6. EXIT");
                System.out.print("\nSelect the option: ");
                int op = scanner.nextInt();
                switch (op){
                    case 1:
                        ReservationDetails guest = ReservationDetails.booking(scanner);
                        DAO.newResrevation(connection,guest);
                        break;

                    case 2:
                        scanner.nextLine();
                        System.out.print("Enter the old room no. : ");
                        String old_roomNo = scanner.next();

                        ReservationDetails updateGuest = ReservationDetails.booking(scanner);
                        DAO.updateReservation(connection, updateGuest, old_roomNo);
                        break;
                    case 3:
                        ReservationDetails getRoom = ReservationDetails.getRoomNo(scanner);
                        DAO.getRoomNumber(connection, getRoom);
                        break;
                    case 4:
                        DAO.showReservation(connection);
                        break;
                    case 5:
                        ReservationDetails deleteGuest = ReservationDetails.deleteBooking(scanner);
                        DAO.deleteReservation(connection,deleteGuest);
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter valid options..!!");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
import java.util.Scanner;

public class ReservationDetails {
    private int id;
    private String name;
    private String mobile;
    private String room_no;
    private String booking_details;

    //CONSTRUCTOR : 5
    public ReservationDetails(int id, String name, String mobile, String room_no, String booking_details) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.room_no = room_no;
        this.booking_details = booking_details;
    }

    //CONSTRUCTOR : 3
    public ReservationDetails(String name, String mobile, String room_no) {
        this.name = name;
        this.mobile = mobile;
        this.room_no = room_no;
    }

    //CONSTRUCTOR : 2
    public ReservationDetails(int id, String roomNo) {
        this.id = id;
        this.room_no = roomNo;
    }

    //CONSTRUCTOR : 2
    public ReservationDetails(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    //GETTER
    public String getName() {
        return name;
    }

    //GETTER
    public String getMobile() {
        return mobile;
    }

    //GETTER
    public String getRoom_no() {
        return room_no;
    }

    //GETTER
    public int getId() {
        return id;
    }

    public static ReservationDetails booking(Scanner scanner){
        scanner.nextLine();

        System.out.print("\nEnter Guest Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Guest Mobile Number: ");
        String mobile = scanner.nextLine();
        System.out.print("Enter the Room Number: ");
        String room_no = scanner.nextLine();

        return new ReservationDetails(name, mobile,room_no);
    }

    public static ReservationDetails deleteBooking(Scanner scanner){
        System.out.print("\nEnter id : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the Room Number: ");
        String room_no = scanner.nextLine();

        return new ReservationDetails(id, room_no);
    }

    public static ReservationDetails getRoomNo(Scanner scanner){
        scanner.nextLine();

        System.out.print("\nEnter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter mobile number: ");
        String mobile = scanner.nextLine();

        return new ReservationDetails(name, mobile);
    }


    //TOSTRING

    @Override
    public String toString() {
        return "||" +
                "id = " + id +
                " | name = " + name +
                " | mobile = " + mobile +
                " | room_no = " + room_no +
                " | booking_details = " + booking_details +
                " ||";

    }
}

public class Query {
    static String newReservation = "INSERT INTO reservation_table (name, mobile, room_no) VALUES (?, ?, ?)";
    static String updateReservation = "UPDATE reservation_table SET name = ?, mobile = ?, room_no = ? WHERE room_no = ?";
    static String getRoomNo = "SELECT room_no FROM reservation_table WHERE name = ? AND mobile = ?";
    static String showReservations = "SELECT * FROM reservation_table";
    static String deleteReservation = "DELETE FROM reservation_table WHERE id = ? AND room_no = ?";
}

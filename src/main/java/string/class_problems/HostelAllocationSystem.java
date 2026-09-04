class HostelRoom {
    String roomNo;
    int beds;
    int occupied;
    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }
    void allot(String name) {
        occupied++;
    }
}
public class HostelAllocationSystem {
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }
        return null;
    }
    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room == null) {
            System.out.println("No rooms available for " + studentName);
            return;
        }
        room.allot(studentName);
        System.out.println(studentName + " allotted to room " + room.roomNo);
    }
    /*Arrays hold references to objects, not the objects themselves.
    So passing the HostelRoom[] array into a method copies the array
    reference (and the individual room references inside it), but
    every element still points to the same HostelRoom objects in
    memory. Any change made to a room through that reference (like
    occupied++ in allot()) is visible everywhere, since no room was
    ever duplicated. */
    public static void main(String[] args) {
        HostelRoom[] roomsWithSpace = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsWithSpace, "Divya");
        HostelRoom[] roomsFull = {
                new HostelRoom("C-214", 3, 3),
                new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsFull, "Divya");
    }
}
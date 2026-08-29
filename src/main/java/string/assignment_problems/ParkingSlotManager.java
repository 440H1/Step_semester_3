import java.util.Scanner;

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity)
                return slot;
        }
        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null)
            slot.allot(vehicleNo);
        else
            System.out.println("No slots available for " + vehicleNo);
    }
}

public class ParkingSlotManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ParkingSlot[] slots = new ParkingSlot[n];

        for (int i = 0; i < n; i++) {
            String slotNo = sc.next();
            int capacity = sc.nextInt();
            int occupiedCount = sc.nextInt();

            slots[i] = new ParkingSlot(slotNo, capacity, occupiedCount);
        }

        String vehicleNo = sc.next();

        ParkingSlot.safeAllot(slots, vehicleNo);

        sc.close();
    }
}

import java.util.*;

enum ParcelStatus {
    BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED
}

interface NotificationChannel {
    void notify(String parcelId, ParcelStatus status);
}

class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    @Override
    public double calculateCharge(double weight) {
        return 40.0 + (10.0 * weight);
    }
}

class ExpressShipping implements ShippingType {
    @Override
    public double calculateCharge(double weight) {
        return 80.0 + (15.0 * weight);
    }
}

class FragileShipping implements ShippingType {
    private StandardShipping standard = new StandardShipping();

    @Override
    public double calculateCharge(double weight) {
        return standard.calculateCharge(weight) + 50.0;
    }
}

class Parcel {
    private String id;
    private double weight;
    private ShippingType shippingType;
    private String typeName;
    private ParcelStatus status;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String id, double weight, String typeName, ShippingType shippingType) {
        this.id = id;
        this.weight = weight;
        this.typeName = typeName;
        this.shippingType = shippingType;
        this.status = ParcelStatus.BOOKED;
    }

    public void addNotificationChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    public void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    public void updateStatus(ParcelStatus newStatus) {
        if (newStatus.ordinal() == this.status.ordinal() + 1) {
            this.status = newStatus;
            notifyChannels();
        } else {
            System.out.println("Invalid transition: " + this.status + " -> " + newStatus + " is not allowed.");
        }
    }

    public void cancel() {
        if (this.status == ParcelStatus.BOOKED) {
            System.out.println("Parcel " + id + " cancelled.");
        } else {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
        }
    }

    public String getId() { return id; }
    public double getWeight() { return weight; }
    public String getTypeName() { return typeName; }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        Parcel parcel = new Parcel("P101", 2.0, "Express", new ExpressShipping());

        System.out.println("Parcel " + parcel.getId() + " booked (" + parcel.getTypeName() + ", " + (int)parcel.getWeight() + " kg). Charge: ₹" + String.format(Locale.US, "%.2f", parcel.getCharge()) + ".");

        parcel.addNotificationChannel(new SmsChannel());
        parcel.addNotificationChannel(new EmailChannel());

        parcel.notifyChannels();

        parcel.updateStatus(ParcelStatus.PICKED_UP);
        parcel.cancel();
        parcel.updateStatus(ParcelStatus.IN_TRANSIT);
        parcel.updateStatus(ParcelStatus.DELIVERED);
    }
}
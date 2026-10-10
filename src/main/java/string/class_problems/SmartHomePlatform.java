import java.util.*;

abstract class Device {
    protected String id;
    protected boolean isOn;

    public Device(String id) {
        this.id = id;
        this.isOn = false;
    }

    public String getId() {
        return id;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(id + " is ON");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(id + " is OFF");
    }
}

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitorable {
    void showEnergy();
}

class Light extends Device implements Dimmable, Schedulable {
    public Light(String id) {
        super(id);
    }

    @Override
    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    public Fan(String id) {
        super(id);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class SmartPlug extends Device implements EnergyMonitorable {
    private int energyKwh;

    public SmartPlug(String id, int energyKwh) {
        super(id);
        this.energyKwh = energyKwh;
    }

    @Override
    public void showEnergy() {
        System.out.println(id + " energy " + energyKwh + " kWh");
    }
}

public class SmartHomePlatform {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new SmartPlug("P1", 12));

        processCommand(devices, "ON L1");
        processCommand(devices, "DIM L1 40");
        processCommand(devices, "DIM F1 30");
        processCommand(devices, "SCHEDULE F1 22:00");
        processCommand(devices, "ENERGY P1");
        processCommand(devices, "ENERGY L1");
    }

    public static void processCommand(Map<String, Device> devices, String commandStr) {
        String[] parts = commandStr.split(" ");
        String action = parts[0];
        String id = parts[1];
        Device device = devices.get(id);

        if (device == null) return;

        if (action.equals("ON")) {
            device.turnOn();
        } else if (action.equals("OFF")) {
            device.turnOff();
        } else if (action.equals("DIM")) {
            if (device instanceof Dimmable) {
                ((Dimmable) device).dim(Integer.parseInt(parts[2]));
            } else {
                System.out.println(id + " rejected: DIM unsupported");
            }
        } else if (action.equals("SCHEDULE")) {
            if (device instanceof Schedulable) {
                ((Schedulable) device).schedule(parts[2]);
            } else {
                System.out.println(id + " rejected: SCHEDULE unsupported");
            }
        } else if (action.equals("ENERGY")) {
            if (device instanceof EnergyMonitorable) {
                ((EnergyMonitorable) device).showEnergy();
            } else {
                System.out.println(id + " rejected: ENERGY unsupported");
            }
        }
    }
}
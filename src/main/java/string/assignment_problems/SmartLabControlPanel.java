import java.util.*;

interface Capability {
    String getName();
}

class PowerCapability implements Capability {
    private boolean on = false;

    @Override
    public String getName() {
        return "Power";
    }

    public void setPower(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }
}

class BrightnessCapability implements Capability {
    private int brightness = 0;

    @Override
    public String getName() {
        return "Brightness";
    }

    public boolean setBrightness(int level) {
        if (level >= 0 && level <= 100) {
            this.brightness = level;
            return true;
        }
        return false;
    }

    public int getBrightness() {
        return brightness;
    }
}

class TemperatureCapability implements Capability {
    private int temperature = 20;

    @Override
    public String getName() {
        return "Temperature";
    }

    public boolean setTemperature(int temp) {
        if (temp >= 16 && temp <= 30) {
            this.temperature = temp;
            return true;
        }
        return false;
    }

    public int getTemperature() {
        return temperature;
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
    }

    public boolean hasCapability(String capabilityName) {
        return capabilities.containsKey(capabilityName);
    }

    @SuppressWarnings("unchecked")
    public <T extends Capability> T getCapability(String capabilityName) {
        return (T) capabilities.get(capabilityName);
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device labAC = new Device("Lab AC");
        labAC.addCapability(new PowerCapability());
        labAC.addCapability(new TemperatureCapability());

        Device ceilingLights = new Device("Ceiling Lights");
        ceilingLights.addCapability(new PowerCapability());
        ceilingLights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(labAC, ceilingLights, projector);

        System.out.println("Scene 'Lecture Mode' started.");
        int actionsApplied = 0;

        for (Device d : devices) {
            if (d.hasCapability("Power")) {
                PowerCapability power = d.getCapability("Power");
                power.setPower(true);
                System.out.println(d.getName() + ": ON.");
                actionsApplied++;
            }
        }

        for (Device d : devices) {
            if (d.hasCapability("Brightness")) {
                BrightnessCapability brightness = d.getCapability("Brightness");
                if (brightness.setBrightness(40)) {
                    System.out.println(d.getName() + ": brightness set to 40%.");
                    actionsApplied++;
                }
            }
        }

        for (Device d : devices) {
            if (d.hasCapability("Temperature")) {
                TemperatureCapability temp = d.getCapability("Temperature");
                if (temp.setTemperature(24)) {
                    System.out.println(d.getName() + ": temperature set to 24°C.");
                    actionsApplied++;
                }
            }
        }

        System.out.println("Scene 'Lecture Mode' completed: " + actionsApplied + " actions applied.");

        TemperatureCapability acTemp = labAC.getCapability("Temperature");
        if (!acTemp.setTemperature(12)) {
            System.out.println("Rejected: Lab AC temperature must be between 16°C and 30°C.");
        }

        projector.addCapability(new BrightnessCapability());
        System.out.println("Projector: Brightness capability added.");

        BrightnessCapability projBrightness = projector.getCapability("Brightness");
        if (projBrightness.setBrightness(70)) {
            System.out.println("Projector: brightness set to 70%.");
        }
    }
}
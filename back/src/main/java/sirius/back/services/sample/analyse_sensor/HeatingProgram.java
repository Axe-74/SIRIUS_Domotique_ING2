package sirius.back.services.sample.analyse_sensor;


public class HeatingProgram {

    private static final double ON_THRESHOLD = 19.0;
    private static final double OFF_THRESHOLD = 21.0;

    private boolean heatingOn = false;
    private Heater heater;

    public void setHeater(Heater heater) {
        this.heater = heater;
    }

    public void onTemperatureChange(double temperature) {
        if (temperature < ON_THRESHOLD ) {
            heatingOn = true;
            heater.turnOn();
        } else if (temperature > OFF_THRESHOLD ) {
            heatingOn = false;
            heater.turnOff();
        } else {
            System.out.println("Aucun changement");
        }
    }
}

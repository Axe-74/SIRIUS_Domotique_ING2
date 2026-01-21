package sirius.back.services.analyse_sensor;

import java.util.ArrayList;
import java.util.List;

public class Temp_analyse {

    private final List<Temp_listener> listeners = new ArrayList<>();

    public void addListener(Temp_listener listener) {
        listeners.add(listener);
    }

    public void analyzeTemperature(double temperature) {
        for (Temp_listener listener : listeners) {
            listener.onTemperatureChange(temperature);
        }
    }
}


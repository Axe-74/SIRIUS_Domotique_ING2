package sirius.back.services.sample.analyse_sensor;

public class Analyse_temp_executer {
    public static void main(String[] args) throws InterruptedException {
        Temp_analyse sensor = new Temp_analyse();
        HeatingProgram heatingProgram = new HeatingProgram();
        Heater heater = new Heater();

        heatingProgram.setHeater(heater);
        sensor.addListener(temperature ->
                heatingProgram.onTemperatureChange(temperature)
        );

        // Valeur remplacée plus tard par celle de la bd
        double temperatureFromDatabase = 22;

        //ResponseEntity<Automatisation> response = AutomatisationController.findOldestSample();

        System.out.println("Température lue : " + temperatureFromDatabase + "°C");
        sensor.analyzeTemperature(temperatureFromDatabase);


    }
}

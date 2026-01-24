package sirius.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.SimulationConfigTemp;

@Repository
public interface SimulationConfigTempRepository extends JpaRepository<SimulationConfigTemp, Long> {

    // Cette méthode magique fait exactement :
    // SELECT * FROM simulation_config WHERE etat_simulation = true ORDER BY id_simulation DESC LIMIT 1
    SimulationConfigTemp findTopByEtatSimulationTrueOrderByIdSimulationDesc();
}
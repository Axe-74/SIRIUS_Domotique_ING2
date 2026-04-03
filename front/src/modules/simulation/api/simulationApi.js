import axios from 'axios';
import { POST_SIMULATION_ACCELEREE } from '../../../constants/back';

export const lancerSimulationAcceleree = async () => {
    try {
        const response = await axios.post(POST_SIMULATION_ACCELEREE);
        return response.data;
    } catch (error) {
        throw error;
    }
};
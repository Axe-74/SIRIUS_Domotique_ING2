import axios from "axios";
import { GET_SCENARIO_CANICULE, GET_ARRET_SCENARIO_CANICULE } from "../../../constants/back";

export const activateCanicule = () => {
    return axios.get(GET_SCENARIO_CANICULE);
};

export const stopCanicule = () => {
    return axios.get(GET_ARRET_SCENARIO_CANICULE);
};
import axios from "axios";
import { GET_LAST_MESURE } from "../../../constants/back";

export const getDerniereMesure = async () => {
    try {
        const response = await axios.get(GET_LAST_MESURE);
        if (Array.isArray(response.data) && response.data.length > 0) {
            return response.data[0];
        }
        throw new Error("La liste reçue est vide");
    } catch (error) {
        throw error;
    }
};
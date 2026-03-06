import axios from "axios";
import { GET_HISTORIQUE } from "../../../constants/back";

export const getHistoriqueData = async (idCapteur) => {
    try {
        const response = await axios.get(GET_HISTORIQUE + `?idCapteur=${idCapteur}`);
        return response.data; 
    } catch (error) {
        throw error;
    }
};
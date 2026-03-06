import axios from "axios";
import { GET_AUTOMATISATION } from "../../../constants/back";

export const fetchAutomatisations = () => {
    return axios.get(GET_AUTOMATISATION)
        .then(response => response.data);
};
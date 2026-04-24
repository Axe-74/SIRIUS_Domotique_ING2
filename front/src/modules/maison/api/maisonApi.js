import {
    GET_PIECES,
    GET_CAPTEURS_TESTPIECE,
    UPDATE_PIECE,
    GET_MESURE_BY_CAPTEUR,
    LOCAL_HOST_PIECE
} from "../../../constants/back";

export const fetchPieces = () => {
    return fetch(GET_PIECES).then(response => response.json());
}

export const fetchCapteurs = () => {
    return fetch(GET_CAPTEURS_TESTPIECE).then(response => response.json());
}

export const sauvegarderPosition = (pieceAEnvoyer) => {
    return fetch(UPDATE_PIECE, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(pieceAEnvoyer)
    })
}

export const fetchMesureCapteur = (id) => {
    const url = GET_MESURE_BY_CAPTEUR + "?idCapteur=" + id;
    return fetch(url).then(function(r) {
        return r.json();
    });
}

export const fetchPieceById = (id) => {
    const url = LOCAL_HOST_PIECE + id;
    return fetch(url).then(function(r) {
        return r.json();
    });
}
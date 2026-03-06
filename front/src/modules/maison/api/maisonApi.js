import {GET_PIECES, GET_CAPTEURS_TESTPIECE, UPDATE_PIECE} from "../../../constants/back";

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
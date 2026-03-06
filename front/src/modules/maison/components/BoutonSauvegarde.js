export default function BoutonSauvegarde({modifications, onClickSauvegarde}) {
    if (modifications === false) {
        return null;
    }
    else {
        return (
            <div className="conteneur-sauvegarde">
                <button
                    className="bouton-sauvegarde"
                    onClick={onClickSauvegarde}
                >
                    Sauvegarder
                </button>
            </div>
        );
    }
}
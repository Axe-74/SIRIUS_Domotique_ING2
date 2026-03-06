import React from 'react';

export default function ScenarioControls({ onLaunch, onStop }) {
    return (
        <>
            <button
                onClick={onLaunch}
                className="btn-scenario btn-launch"
            >
                Scénario Canicule
            </button>

            <button
                onClick={onStop}
                className="btn-scenario btn-stop"
            >
                Stop Scénario Canicule
            </button>
        </>
    );
}
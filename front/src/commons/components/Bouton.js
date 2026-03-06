import React from 'react';

export default function Bouton({ onClick, className, children }) {
    return (
        <button
            onClick={onClick}
            className={`btn ${className || ''}`}
        >
            {children}
        </button>
    );
}
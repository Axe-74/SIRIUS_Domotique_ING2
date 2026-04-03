import React from 'react';
import {LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, ResponsiveContainer} from 'recharts';

export default function MesuresHistoriqueComponents({ data }) {
    return (
        <ResponsiveContainer width='100%' height={600}>
            <LineChart
                width={1100}
                height={500}
                data={data}
                //margin={{ top: 50, right: 10, left: 100, bottom: 5 }}
            >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" minTickGap={30}/>
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="valeurExt" stroke="#8884d8" dot={false} name="Température Extérieure"/>
                <Line type="monotone" dataKey="valeurInt" stroke="#ff7300" dot={false} name="Température Intérieure"/>
                <Line type="step" dataKey="valeurMouv" stroke="#28a745" dot={false} name="Présence" />
            </LineChart>
        </ResponsiveContainer>
    );
}
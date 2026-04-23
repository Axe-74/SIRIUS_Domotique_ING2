import React from 'react';
import {ComposedChart, Line, CartesianGrid, XAxis, YAxis, Area, Tooltip, ResponsiveContainer} from 'recharts';

export default function MesuresHistoriqueComponents({ data }) {
    return (
        <div style={{height : 600 }}>
            <ResponsiveContainer width='100%' height={450} >
                <ComposedChart
                    width={1100}
                    height={500}
                    data={data}
                    syncId={"anyId"}
                    margin={{ top: 10, right: 30, left: 0, bottom: 15 }}
                >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="date" minTickGap={30} hide={true}/>
                    <YAxis domain={[0, 'auto']} />
                    <Tooltip />
                    <Line type="monotone" dataKey="valeurExt" stroke="#8884d8" dot={false} name="Température Extérieure"/>
                    <Line type="monotone" dataKey="valeurInt" stroke="#ff7300" dot={false} name="Température Intérieure"/>

                </ComposedChart>
            </ResponsiveContainer>

            <ResponsiveContainer width='100%' height={150}>
                <ComposedChart
                    width={1100}
                    height={500}
                    data={data}
                    syncId={"anyId"}
                    margin={{ top: 10, right: 30, left: 0, bottom: 0 }}
                >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="date" minTickGap={30}/>
                    <YAxis domain={[0, 1]} ticks={[0, 1]} />
                    <Tooltip
                        formatter={(value, name) => {
                            const valeur = value > 0 ? "Oui" : "Non";
                            return valeur ;
                        }}/>
                    <Area type="step" dataKey="valeurMouv" stroke="#28a745" dot={false} name="Présence" fillOpacity={1} fill="#28a745"/>

                </ComposedChart>
            </ResponsiveContainer>
        </div>
    );
}
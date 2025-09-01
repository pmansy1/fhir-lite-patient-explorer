import { useEffect, useState } from "react";
import { getPatient } from "../api";
import type { PatientDetail } from "../types";

type Props = { id: string | null; onClose: ()=>void };

export default function PatientsDetail({ id, onClose }: Props) {
    const [data, setData] = useState<PatientDetail | null>(null);
    const [loading, setLoading] = useState(false);
    const [err, setErr] = useState<string | null>(null);

    useEffect(()=> {
        if (!id) return;
        setLoading(true); setErr(null);
        getPatient(id)
            .then(res => setData(res))
            .catch(e => setErr(e?.response?.data?.message || "Failed to load"))
            .finally(()=> setLoading(false));
    }, [id]);

    if (!id) return null;

    return (
        <div className="detail">
            <div className="detailHeader">
                <h2>Patient Detail</h2>
                <button onClick={onClose}>Close</button>
            </div>
            {loading && <div className="loading">Loading…</div>}
            {err && <div className="error">{err}</div>}
            {data && (
                <>
                    <div className="card">
                        <h3>{data.fullName}</h3>
                        <p><b>DOB:</b> {data.birthDate}</p>
                        <p><b>Gender:</b> {data.gender}</p>
                        <p><b>MRN:</b> {data.mrn}</p>
                        {data.address && <p><b>Address:</b> {data.address}</p>}
                        {data.telecom && <p><b>Contact:</b> {data.telecom}</p>}
                    </div>

                    <div className="card">
                        <h3>Conditions</h3>
                        {(!data.conditions || data.conditions.length===0) ? (
                            <div className="empty">No conditions.</div>
                        ) : (
                            <table>
                                <thead><tr><th>Code</th><th>Display</th><th>Onset</th></tr></thead>
                                <tbody>
                                {data.conditions.map((c, i)=>(
                                    <tr key={i}>
                                        <td>{c.code}</td>
                                        <td>{c.display}</td>
                                        <td>{c.onsetDate ?? "-"}</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        )}
                    </div>
                </>
            )}
        </div>
    );
}

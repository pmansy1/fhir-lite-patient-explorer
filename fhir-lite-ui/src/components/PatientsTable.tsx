import type { PatientCard } from "../types";

type Props = {
    rows: PatientCard[];
    page: number;
    totalPages: number;
    onPage: (p: number) => void;
    onSelect: (id: string) => void;
    loading?: boolean;
    error?: string | null;
};

export default function PatientsTable({ rows, page, totalPages, onPage, onSelect, loading, error }: Props) {
    if (error) return <div className="error">Error: {error}</div>;
    if (loading) return <div className="loading" aria-live="polite">Loading…</div>;
    if (!rows.length) return <div className="empty">No patients match your filters.</div>;

    return (
        <div className="tableWrap">
            <table>
                <thead>
                <tr>
                    <th>Name</th><th>DOB</th><th>Gender</th><th>MRN</th>
                </tr>
                </thead>
                <tbody>
                {rows.map(r => (
                    <tr key={r.id} onClick={()=>onSelect(r.id)} className="clickable" tabIndex={0}
                        onKeyDown={(e)=>{ if(e.key==='Enter') onSelect(r.id); }}>
                        <td>{r.fullName}</td>
                        <td>{r.birthDate}</td>
                        <td>{r.gender}</td>
                        <td>{r.mrn}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div className="pager">
                <button disabled={page<=0} onClick={()=>onPage(page-1)}>Prev</button>
                <span>Page {page+1} of {totalPages || 1}</span>
                <button disabled={page+1>=totalPages} onClick={()=>onPage(page+1)}>Next</button>
            </div>
        </div>
    );
}

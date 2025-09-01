import { useEffect, useState } from "react";
import SearchBar from "./components/SearchBar";
import PatientsTable from "./components/PatientsTable";
import PatientsDetail from "./components/PatientsDetail";
import { searchPatients } from "./api";
import type { PatientCard, Page } from "./types";
import "./index.css";

export default function App() {
    const [query, setQuery] = useState<{ name?: string; dob?: string; mrn?: string }>({});
    const [page, setPage] = useState(0);
    const [data, setData] = useState<Page<PatientCard> | null>(null);
    const [loading, setLoading] = useState(false);
    const [err, setErr] = useState<string | null>(null);
    const [selectedId, setSelectedId] = useState<string | null>(null);

    async function runSearch(q = query, p = page) {
        setLoading(true); setErr(null);
        try {
            const res = await searchPatients({ ...q, page: p, size: 10, sort: "lastName,asc" });
            setData(res);
        } catch (e: any) {
            setErr(e?.response?.data?.message || "Failed to fetch patients");
        } finally {
            setLoading(false);
        }
    }

    useEffect(()=> { runSearch(); /* initial */ }, []);
    useEffect(()=> { runSearch(); }, [page]);

    function onSearch(newQ: typeof query) {
        setQuery(newQ);
        setPage(0);
        runSearch(newQ, 0);
    }

    return (
        <div className="layout">
            <header><h1>FHIR-Lite Patient Explorer</h1></header>

            <section className="content">
                <div className="left">
                    <SearchBar onSearch={onSearch} initial={query} />
                    <PatientsTable
                        rows={data?.content || []}
                        page={data?.number || 0}
                        totalPages={data?.totalPages || 0}
                        onPage={setPage}
                        onSelect={setSelectedId}
                        loading={loading}
                        error={err}
                    />
                </div>

                <div className="right">
                    <PatientsDetail id={selectedId} onClose={()=>setSelectedId(null)} />
                </div>
            </section>

            <footer>v0 — minimal, fast, understandable</footer>
        </div>
    );
}

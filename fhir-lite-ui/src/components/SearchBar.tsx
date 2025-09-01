import { useState } from "react";

type Props = {
    onSearch: (q: { name?: string; dob?: string; mrn?: string }) => void;
    initial?: { name?: string; dob?: string; mrn?: string };
};

export default function SearchBar({ onSearch, initial }: Props) {
    const [name, setName] = useState(initial?.name ?? "");
    const [dob, setDob] = useState(initial?.dob ?? "");
    const [mrn, setMrn] = useState(initial?.mrn ?? "");

    function submit(e: React.FormEvent) {
        e.preventDefault();
        onSearch({
            name: name.trim() || undefined,
            dob: dob || undefined,
            mrn: mrn.trim() || undefined,
        });
    }

    function clear() {
        setName(""); setDob(""); setMrn("");
        onSearch({});
    }

    return (
        <form onSubmit={submit} className="searchbar">
            <div>
                <label> Name<br/>
                    <input value={name} onChange={e=>setName(e.target.value)} placeholder="e.g. Ann" />
                </label>
            </div>
            <div>
                <label> DOB<br/>
                    <input type="date" value={dob} onChange={e=>setDob(e.target.value)} />
                </label>
            </div>
            <div>
                <label> MRN<br/>
                    <input value={mrn} onChange={e=>setMrn(e.target.value)} placeholder="e.g. 12345" />
                </label>
            </div>
            <div className="actions">
                <button type="submit">Search</button>
                <button type="button" onClick={clear}>Clear</button>
            </div>
        </form>
    );
}

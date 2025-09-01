import axios from "axios";
import type { Page, PatientCard, PatientDetail } from "./types";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE || "http://localhost:8080/api",
    timeout: 8000,
});

export async function searchPatients(params: {
    name?: string;
    dob?: string;   // YYYY-MM-DD
    mrn?: string;
    page?: number;  // 0-based
    size?: number;  // default 10/20
    sort?: string;  // e.g., "lastName,asc"
}): Promise<Page<PatientCard>> {
    const { data } = await api.get<Page<PatientCard>>("/patients", { params });
    return data;
}

export async function getPatient(id: string): Promise<PatientDetail> {
    const { data } = await api.get<PatientDetail>(`/patients/${id}`);
    return data;
}

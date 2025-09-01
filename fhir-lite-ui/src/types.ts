export type PatientCard = {
    id: string;
    fullName: string;
    birthDate: string;
    gender: string;
    mrn: string;
};

export type PatientDetail = {
    id: string;
    fullName: string;
    birthDate: string;
    gender: string;
    mrn: string;
    address: string | null;
    telecom: string | null;
    conditions: { code: string; display: string; onsetDate: string | null }[];
};

export type Page<T> = {
    content: T[];
    totalElements: number;
    totalPages: number;
    size: number;
    number: number; // current page index (0-based)
};

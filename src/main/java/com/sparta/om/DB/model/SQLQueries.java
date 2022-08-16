package com.sparta.om.DB.model;

public interface SQLQueries {
    public static final String SELECT_ALL = "SELECT * FROM public.employees";

    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS public.employees\n" +
            "(\n" +
            "    emp_id numeric NOT NULL,\n" +
            "    name_prefix character varying(5) COLLATE pg_catalog.\"default\",\n" +
            "    first_name character varying(255) COLLATE pg_catalog.\"default\",\n" +
            "    middle_initial \"char\",\n" +
            "    last_name character varying(255) COLLATE pg_catalog.\"default\",\n" +
            "    gender \"char\",\n" +
            "    email character varying(255) COLLATE pg_catalog.\"default\",\n" +
            "    date_of_birth date,\n" +
            "    date_of_joining date,\n" +
            "    salary numeric,\n" +
            "    CONSTRAINT employees_pkey PRIMARY KEY (emp_id)\n" +
            ")";

//    String DROP_TABLE = "DROP TABLE IF EXISTS public.employees;";

    String INSERT_INTO_TABLE = "INSERT INTO public.employees (emp_id, name_prefix, first_name, middle_initial, last_name, gender, email, date_od_birth, date_of_joining, salary) VALUES (? ,? ,?, ?, ?, ?, ?, ?, ?, ?)";
}

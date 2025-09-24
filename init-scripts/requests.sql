--
-- PostgreSQL database dump
--

\restrict LBmA920RvGfwtHKPs1gmnElSjj2Qx3461XRmadQDd8PYShc6vEdfWVMJZKjpNPH

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2025-09-21 13:26:02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16464)
-- Name: loan_request; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.loan_request (
    amount numeric(15,2) NOT NULL,
    identity_document_applicant character varying(20) NOT NULL,
    time_limit double precision NOT NULL,
    loan_type character varying(50) NOT NULL,
    state integer NOT NULL,
    id uuid DEFAULT gen_random_uuid()
);


--
-- TOC entry 217 (class 1259 OID 16452)
-- Name: loans_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.loans_type (
    id integer NOT NULL,
    loan_type character varying(100),
    interest_rate double precision
);


--
-- TOC entry 218 (class 1259 OID 16455)
-- Name: loans_type_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.loans_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 218
-- Name: loans_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.loans_type_id_seq OWNED BY public.loans_type.id;


--
-- TOC entry 4746 (class 2604 OID 16456)
-- Name: loans_type id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.loans_type ALTER COLUMN id SET DEFAULT nextval('public.loans_type_id_seq'::regclass);


--
-- TOC entry 4897 (class 0 OID 16464)
-- Dependencies: 219
-- Data for Name: loan_request; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.loan_request (amount, identity_document_applicant, time_limit, loan_type, state, id) FROM stdin;
500000.00	1000002	12	libre inversion	1	173617a6-2886-487e-94e4-57cf9770201e
1.00	1000002	12	hipotecario	1	03294f13-7e95-4ad3-b794-15c7df2c9ea9
2.00	1000002	12	hipotecario	1	278cc7a0-86ee-4a94-abc0-9701ff25cdae
3.00	1000002	12	hipotecario	1	e6217b82-083d-45f8-b6b8-7954a1eb8076
4.00	1000002	12	hipotecario	1	211533ab-1441-4736-bab0-7b05e838d293
5.00	1000002	12	hipotecario	1	8b9056c6-637a-4744-b2e6-9da23279dc99
6.00	1000002	12	hipotecario	1	0ddd07d5-821f-4bd3-b459-06d703e316bb
7.00	1000002	12	hipotecario	1	a47de82c-a1a6-4488-8172-28da7f7c54cd
8.00	1000002	12	hipotecario	1	8db0b475-01ca-4cb7-8bbb-f33674630051
9.00	1000002	12	hipotecario	1	c5f5a4ba-fff0-4e5a-b0e9-89fad98c1654
10.00	1000002	12	hipotecario	1	acb15b7d-f83e-4c23-9af3-ce04b3f76ec4
11.00	1000002	12	hipotecario	1	2b7fa468-32bb-41bf-add6-8ae8813bba49
12.00	1000002	12	hipotecario	1	8d0668d7-b7d8-44cd-bdde-9db918e828c6
12.00	1000002	12	hipotecario	2	ac8b2b0a-6e94-4796-a2dd-4f9aeb49655c
15.00	1000002	12	hipotecario	3	7f324074-64e8-4191-97c2-f61dfef941f9
3000000.00	1000002	20	libre inversion	0	2b51f06f-d85a-478f-b2dd-31876a42563d
3000000.00	1000002	20	libre inversion	0	d7b5ae78-2c42-4dc5-aecd-fd6ce142e228
3000000.00	1000002	20	libre inversion	0	a8178d39-3a91-440e-b550-573a89ee4f4f
3000000.00	1000002	20	libre inversion	0	95f7ba06-0a49-468d-b191-be79a98acbf6
\.


--
-- TOC entry 4895 (class 0 OID 16452)
-- Dependencies: 217
-- Data for Name: loans_type; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.loans_type (id, loan_type, interest_rate) FROM stdin;
1	libre inversion	1.8
2	hipotecario	1.6
\.


--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 218
-- Name: loans_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.loans_type_id_seq', 2, true);


--
-- TOC entry 4749 (class 2606 OID 16458)
-- Name: loans_type loans_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.loans_type
    ADD CONSTRAINT loans_type_pkey PRIMARY KEY (id);


-- Completed on 2025-09-21 13:26:02

--
-- PostgreSQL database dump complete
--

\unrestrict LBmA920RvGfwtHKPs1gmnElSjj2Qx3461XRmadQDd8PYShc6vEdfWVMJZKjpNPH


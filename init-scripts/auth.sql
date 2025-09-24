--
-- PostgreSQL database dump
--

\restrict yZ9LZ24k12AiWjz9V1YIC80Qe1FXfmfiWZLvIEfQ43okVjxkmhYgsLULYEhXheA

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2025-09-21 13:25:32

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

--
-- TOC entry 2 (class 3079 OID 16472)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- TOC entry 4932 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16398)
-- Name: applicants; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.applicants (
    name character varying(30),
    last_name character varying(30),
    birth_date date,
    address character varying(50),
    phone_number character varying(25),
    email character varying(30),
    base_salary integer,
    identity_document character varying(25),
    id uuid DEFAULT gen_random_uuid(),
    role integer,
    password_hash character varying(255)
);


--
-- TOC entry 4926 (class 0 OID 16398)
-- Dependencies: 218
-- Data for Name: applicants; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.applicants (name, last_name, birth_date, address, phone_number, email, base_salary, identity_document, id, role, password_hash) FROM stdin;
Cristina	Tabarez	2000-06-20	calle 23	+57111	cristina@gmail.com	12	1000002	ffd75b8d-e296-4620-94fe-c923af2e270d	2	$2a$10$zm2VXIMdxe3wosX/CXlKyeci0Sp.i6Vkdq3qGPU92DFzDmvdxWdcG
Juan	Juan	2000-06-20	calle 23	+57111	juan@gmail.com	16000000	123	4b90f940-241d-4ddb-b950-1a9029298f75	3	$2a$10$zm2VXIMdxe3wosX/CXlKyeci0Sp.i6Vkdq3qGPU92DFzDmvdxWdcG
Isabel	Palacios	2000-06-20	calle 23	+57111	isabel@gmail.com	12	321	239a9aac-efe6-45db-a789-0f62e3614a2d	1	$2a$10$zm2VXIMdxe3wosX/CXlKyeci0Sp.i6Vkdq3qGPU92DFzDmvdxWdcG
\.


--
-- TOC entry 4780 (class 2606 OID 16437)
-- Name: applicants applicants_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.applicants
    ADD CONSTRAINT applicants_email_key UNIQUE (email);


-- Completed on 2025-09-21 13:25:32

--
-- PostgreSQL database dump complete
--

\unrestrict yZ9LZ24k12AiWjz9V1YIC80Qe1FXfmfiWZLvIEfQ43okVjxkmhYgsLULYEhXheA


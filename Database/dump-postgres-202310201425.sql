--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-10-20 14:25:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 260 (class 1259 OID 33246)
-- Name: konsumen; Type: TABLE; Schema: salt; Owner: postgres
--

CREATE TABLE salt.konsumen (
    id integer DEFAULT nextval('salt.konsumen_id'::regclass) NOT NULL,
    nama character varying,
    alamat text,
    kota bpchar,
    provinsi bpchar,
    tgl_registrasi timestamp without time zone,
    status bpchar
);


ALTER TABLE salt.konsumen OWNER TO postgres;

--
-- TOC entry 3416 (class 0 OID 33246)
-- Dependencies: 260
-- Data for Name: konsumen; Type: TABLE DATA; Schema: salt; Owner: postgres
--

INSERT INTO salt.konsumen VALUES (1, 'Fikri', 'jambi', 'Badung', 'Bali', '2023-10-18 00:00:00', 'non-aktif');
INSERT INTO salt.konsumen VALUES (2, 'Andi', 'Jl. Pakenjengsd', 'Bandung', 'Jawa Barat', '2023-10-20 00:00:00', 'aktif');
INSERT INTO salt.konsumen VALUES (3, 'Wina', 'Jln PVJ ', 'Bandung', 'Jawa Barat', '2023-10-19 00:00:00', 'aktif');


--
-- TOC entry 3273 (class 2606 OID 33255)
-- Name: konsumen konsumen_pk; Type: CONSTRAINT; Schema: salt; Owner: postgres
--

ALTER TABLE ONLY salt.konsumen
    ADD CONSTRAINT konsumen_pk PRIMARY KEY (id);


-- Completed on 2023-10-20 14:25:37

--
-- PostgreSQL database dump complete
--


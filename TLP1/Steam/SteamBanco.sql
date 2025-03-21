--
-- PostgreSQL database dump
--

-- Dumped from database version 13.15
-- Dumped by pg_dump version 16.3

-- Started on 2025-03-21 14:29:23

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 24591)
-- Name: jogador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogador (
    id integer NOT NULL,
    nome character varying(20) NOT NULL,
    senha character varying(20) NOT NULL
);


ALTER TABLE public.jogador OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24589)
-- Name: jogador_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jogador_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jogador_id_seq OWNER TO postgres;

--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 200
-- Name: jogador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogador_id_seq OWNED BY public.jogador.id;


--
-- TOC entry 2850 (class 2604 OID 24594)
-- Name: jogador id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador ALTER COLUMN id SET DEFAULT nextval('public.jogador_id_seq'::regclass);


--
-- TOC entry 2984 (class 0 OID 24591)
-- Dependencies: 201
-- Data for Name: jogador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jogador (id, nome, senha) FROM stdin;
1	digao	12345
\.


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 200
-- Name: jogador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogador_id_seq', 1, true);


--
-- TOC entry 2852 (class 2606 OID 24596)
-- Name: jogador jogador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador
    ADD CONSTRAINT jogador_pkey PRIMARY KEY (id);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-03-21 14:29:23

--
-- PostgreSQL database dump complete
--


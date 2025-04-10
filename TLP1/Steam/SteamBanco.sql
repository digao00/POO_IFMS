--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.1

-- Started on 2025-04-10 16:01:43

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 27389)
-- Name: jogador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogador (
    id integer NOT NULL,
    nome character varying(20) NOT NULL,
    senha character varying(20) NOT NULL
);


ALTER TABLE public.jogador OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 27392)
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
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 218
-- Name: jogador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogador_id_seq OWNED BY public.jogador.id;


--
-- TOC entry 222 (class 1259 OID 27428)
-- Name: jogador_jogos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogador_jogos (
    id integer NOT NULL,
    id_jogo integer NOT NULL,
    id_jogador integer NOT NULL
);


ALTER TABLE public.jogador_jogos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 27427)
-- Name: jogador_jogos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jogador_jogos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jogador_jogos_id_seq OWNER TO postgres;

--
-- TOC entry 4921 (class 0 OID 0)
-- Dependencies: 221
-- Name: jogador_jogos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogador_jogos_id_seq OWNED BY public.jogador_jogos.id;


--
-- TOC entry 219 (class 1259 OID 27397)
-- Name: jogos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogos (
    id integer NOT NULL,
    nome character varying(50) NOT NULL
);


ALTER TABLE public.jogos OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 27400)
-- Name: jogos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jogos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jogos_id_seq OWNER TO postgres;

--
-- TOC entry 4922 (class 0 OID 0)
-- Dependencies: 220
-- Name: jogos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogos_id_seq OWNED BY public.jogos.id;


--
-- TOC entry 4752 (class 2604 OID 27401)
-- Name: jogador id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador ALTER COLUMN id SET DEFAULT nextval('public.jogador_id_seq'::regclass);


--
-- TOC entry 4754 (class 2604 OID 27431)
-- Name: jogador_jogos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador_jogos ALTER COLUMN id SET DEFAULT nextval('public.jogador_jogos_id_seq'::regclass);


--
-- TOC entry 4753 (class 2604 OID 27403)
-- Name: jogos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogos ALTER COLUMN id SET DEFAULT nextval('public.jogos_id_seq'::regclass);


--
-- TOC entry 4908 (class 0 OID 27389)
-- Dependencies: 217
-- Data for Name: jogador; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.jogador VALUES (6, 'joao', '123');
INSERT INTO public.jogador VALUES (8, 'jao', '0609');
INSERT INTO public.jogador VALUES (9, 'hatsu', '6969');
INSERT INTO public.jogador VALUES (10, 'jaozin', '555');
INSERT INTO public.jogador VALUES (12, 'yami', '123');
INSERT INTO public.jogador VALUES (16, 'digao', '1234');
INSERT INTO public.jogador VALUES (22, 'yamiz0', '12345678');


--
-- TOC entry 4913 (class 0 OID 27428)
-- Dependencies: 222
-- Data for Name: jogador_jogos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.jogador_jogos VALUES (7, 7, 12);
INSERT INTO public.jogador_jogos VALUES (9, 5, 16);
INSERT INTO public.jogador_jogos VALUES (12, 6, 16);
INSERT INTO public.jogador_jogos VALUES (15, 5, 22);
INSERT INTO public.jogador_jogos VALUES (16, 7, 22);


--
-- TOC entry 4910 (class 0 OID 27397)
-- Dependencies: 219
-- Data for Name: jogos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.jogos VALUES (5, 'Rust');
INSERT INTO public.jogos VALUES (6, 'Battlefield 4');
INSERT INTO public.jogos VALUES (7, 'CS 2');
INSERT INTO public.jogos VALUES (8, 'Unturned');
INSERT INTO public.jogos VALUES (4, 'FIFA 24');
INSERT INTO public.jogos VALUES (3, 'Ultrakill');
INSERT INTO public.jogos VALUES (2, 'Garry''s Mod');
INSERT INTO public.jogos VALUES (1, 'Half-Life 2');


--
-- TOC entry 4923 (class 0 OID 0)
-- Dependencies: 218
-- Name: jogador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogador_id_seq', 22, true);


--
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 221
-- Name: jogador_jogos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogador_jogos_id_seq', 16, true);


--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 220
-- Name: jogos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogos_id_seq', 8, true);


--
-- TOC entry 4760 (class 2606 OID 27433)
-- Name: jogador_jogos jogador_jogos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador_jogos
    ADD CONSTRAINT jogador_jogos_pkey PRIMARY KEY (id);


--
-- TOC entry 4756 (class 2606 OID 27407)
-- Name: jogador jogador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador
    ADD CONSTRAINT jogador_pkey PRIMARY KEY (id);


--
-- TOC entry 4758 (class 2606 OID 27409)
-- Name: jogos jogos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogos
    ADD CONSTRAINT jogos_pkey PRIMARY KEY (id);


--
-- TOC entry 4761 (class 2606 OID 27439)
-- Name: jogador_jogos id_jogador; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador_jogos
    ADD CONSTRAINT id_jogador FOREIGN KEY (id_jogador) REFERENCES public.jogador(id);


--
-- TOC entry 4762 (class 2606 OID 27434)
-- Name: jogador_jogos id_jogo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogador_jogos
    ADD CONSTRAINT id_jogo FOREIGN KEY (id_jogo) REFERENCES public.jogos(id);


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-04-10 16:01:43

--
-- PostgreSQL database dump complete
--


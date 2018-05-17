--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-05-16 12:27:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2826 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 16404)
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact (
    id integer NOT NULL,
    resume_uuid character(36) NOT NULL,
    type text NOT NULL,
    value text NOT NULL
);


ALTER TABLE public.contact OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16402)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_id_seq OWNER TO postgres;

--
-- TOC entry 2827 (class 0 OID 0)
-- Dependencies: 197
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contact_id_seq OWNED BY public.contact.id;


--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: resume; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resume (
    uuid character(36) NOT NULL,
    full_name text NOT NULL
);


ALTER TABLE public.resume OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16566)
-- Name: section_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.section_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.section_id_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16539)
-- Name: section; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.section (
    id integer DEFAULT nextval('public.section_id_seq'::regclass) NOT NULL,
    resume_uuid character(36) NOT NULL,
    type text NOT NULL,
    value text NOT NULL
);


ALTER TABLE public.section OWNER TO postgres;

--
-- TOC entry 2683 (class 2604 OID 16570)
-- Name: contact id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact ALTER COLUMN id SET DEFAULT nextval('public.contact_id_seq'::regclass);


--
-- TOC entry 2816 (class 0 OID 16404)
-- Dependencies: 198
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contact VALUES (2073, 'uuid1                               ', 'PHONE', '+79888887946');
INSERT INTO public.contact VALUES (2074, 'uuid1                               ', 'MAIL', 'email1@ya.ru');
INSERT INTO public.contact VALUES (2075, 'uuid2                               ', 'PHONE', '+79888887947');
INSERT INTO public.contact VALUES (2076, 'uuid2                               ', 'MAIL', 'email2@ya.ru');
INSERT INTO public.contact VALUES (2077, 'uuid3                               ', 'PHONE', '+79888887948');
INSERT INTO public.contact VALUES (2078, 'uuid3                               ', 'MAIL', 'email3@ya.ru');
INSERT INTO public.contact VALUES (2079, 'uuid4                               ', 'PHONE', '+79888887949');
INSERT INTO public.contact VALUES (2080, 'uuid4                               ', 'MAIL', 'email4@ya.ru');


--
-- TOC entry 2814 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: resume; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.resume VALUES ('uuid1                               ', 'Ивановuuid1 Иван Иванович');
INSERT INTO public.resume VALUES ('uuid2                               ', 'Ивановuuid2 Иван Иванович');
INSERT INTO public.resume VALUES ('uuid3                               ', 'Ивановuuid3 Иван Иванович');
INSERT INTO public.resume VALUES ('uuid4                               ', 'Ивановuuid4 Иван Иванович');


--
-- TOC entry 2817 (class 0 OID 16539)
-- Dependencies: 199
-- Data for Name: section; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.section VALUES (1283, 'uuid1                               ', 'OBJECTIVE', 'Описание позиция 1a');
INSERT INTO public.section VALUES (1284, 'uuid1                               ', 'PERSONAL', 'Описание личные качества 1a');
INSERT INTO public.section VALUES (1285, 'uuid1                               ', 'QUALIFICATIONS', 'язык программирования 1 1a
язык программирования 2 1a');
INSERT INTO public.section VALUES (1292, 'uuid4                               ', 'OBJECTIVE', 'Описание позиция 4a');
INSERT INTO public.section VALUES (1293, 'uuid4                               ', 'PERSONAL', 'Описание личные качества 4a');
INSERT INTO public.section VALUES (1294, 'uuid4                               ', 'QUALIFICATIONS', 'язык программирования 1 4a
язык программирования 2 4a');
INSERT INTO public.section VALUES (1286, 'uuid2                               ', 'OBJECTIVE', 'Описание позиция 2a');
INSERT INTO public.section VALUES (1287, 'uuid2                               ', 'PERSONAL', 'Описание личные качества 2a');
INSERT INTO public.section VALUES (1288, 'uuid2                               ', 'QUALIFICATIONS', 'язык программирования 1 2a
язык программирования 2 2a');
INSERT INTO public.section VALUES (1289, 'uuid3                               ', 'OBJECTIVE', 'Описание позиция 3a');
INSERT INTO public.section VALUES (1290, 'uuid3                               ', 'PERSONAL', 'Описание личные качества 3a');
INSERT INTO public.section VALUES (1291, 'uuid3                               ', 'QUALIFICATIONS', 'язык программирования 1 3a
язык программирования 2 3a');


--
-- TOC entry 2828 (class 0 OID 0)
-- Dependencies: 197
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contact_id_seq', 1322, true);


--
-- TOC entry 2829 (class 0 OID 0)
-- Dependencies: 200
-- Name: section_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.section_id_seq', 880, true);


--
-- TOC entry 2686 (class 2606 OID 16401)
-- Name: resume resume_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resume
    ADD CONSTRAINT resume_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2687 (class 1259 OID 16416)
-- Name: contact_uuid_type_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX contact_uuid_type_index ON public.contact USING btree (resume_uuid, type);


--
-- TOC entry 2689 (class 1259 OID 16550)
-- Name: fki_section_resume_uuid_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_section_resume_uuid_fkey ON public.section USING btree (resume_uuid);


--
-- TOC entry 2688 (class 1259 OID 16430)
-- Name: uniq_uuid_type; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX uniq_uuid_type ON public.contact USING btree (resume_uuid, type);


--
-- TOC entry 2690 (class 1259 OID 16551)
-- Name: uniq_uuid_type_sections; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX uniq_uuid_type_sections ON public.section USING btree (resume_uuid, type);


--
-- TOC entry 2691 (class 2606 OID 16411)
-- Name: contact contact_resume_uuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_resume_uuid_fkey FOREIGN KEY (resume_uuid) REFERENCES public.resume(uuid) ON DELETE CASCADE;


--
-- TOC entry 2692 (class 2606 OID 16545)
-- Name: section section_resume_uuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section
    ADD CONSTRAINT section_resume_uuid_fkey FOREIGN KEY (resume_uuid) REFERENCES public.resume(uuid) ON DELETE CASCADE;


-- Completed on 2018-05-16 12:27:22

--
-- PostgreSQL database dump complete
--


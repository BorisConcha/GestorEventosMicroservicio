DROP TABLE eventos CASCADE CONSTRAINTS;

CREATE TABLE eventos (
  id_evento                     NUMBER(8) NOT NULL,
  fecha_inicio_evento           VARCHAR2(100) NOT NULL,
  fecha_fin_evento              VARCHAR2(100) NOT NULL,
  nombre_evento                 VARCHAR2(250) NOT NULL,
  tipo_evento                   VARCHAR2(250) NOT NULL,
  cantidad_participantes        NUMBER(8) NOT NULL,
  cantidad_entradas             NUMBER(8) NOT NULL,
  descripcion_evento            VARCHAR2(500) NOT NULL
);

ALTER TABLE eventos ADD CONSTRAINT pk_eventos PRIMARY KEY ( id_evento );


INSERT INTO eventos VALUES (5000, 7500, 1, '15/04/2025', '16/04/2025', 'Rock en Vivo', 'Concierto',  'Festival de bandas de rock alternativo');
INSERT INTO eventos VALUES (1200, 1500, 2, '20/05/2025', '20/05/2025', 'Sinfónica Nacional', 'Concierto', 'Presentación de música clásica en el teatro municipal');
INSERT INTO eventos VALUES (30000, 35000, 3, '05/05/2025', '05/05/2025', 'Final de Copa regional sub 17', 'Deportivo', 'Final del campeonato regional de fútbol');
INSERT INTO eventos VALUES (500, 600, 4, '20/08/2025', '21/08/2025', 'Convención de Medicina', 'Conferencia', 'Encuentro de profesionales médicos para discutir avances en el campo');
INSERT INTO eventos VALUES (2000, 2500, 5, '28/09/2025', '28/09/2025', 'Concurso de Parrilleros', 'Gastronomía', 'Competencia de asadores con los mejores chefs de la región');
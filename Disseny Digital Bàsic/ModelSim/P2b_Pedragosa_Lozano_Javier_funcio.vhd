-- d) Les diferències que podem observar són sobretot pics i valls que es
-- donen entre el moment en què canvien les entrades i el moment en que
-- el circuit ens dona la sortida definitiva. Aquests pics i valls són
-- causats pel retràs de les portes, que triguen a modificar les entrades
-- de les següents portes i fan que la sortida sigui incorrecta durant un
-- breu període de temps.

-- e) Són diferents per culpa del retràs entre portes. Quan canviem les
-- entrades cada 50 ms ja es donen rebots, així que reduïnt el retràs
-- entre canvis d'entrades agreujem aquests rebots de manera que el
-- circuit acaba donant sortides incorrectes.

ENTITY funcio_2 is
PORT (a, b, c, d: IN BIT;
	     f: OUT BIT);
END funcio_2;

ARCHITECTURE logica OF funcio_2 IS
BEGIN
f <= ((NOT a AND b AND NOT c) OR (b AND NOT d) OR (a AND c AND d) OR (a AND NOT d)) XOR (a or NOT d);
END logica;

ARCHITECTURE logicaretard OF funcio_2 IS
BEGIN
f <= ((NOT a AND b AND NOT c) OR (b AND NOT d) OR (a AND c AND d) OR (a AND NOT d)) XOR (a or NOT d) AFTER 3 ns;
END logicaretard;

ARCHITECTURE estructural OF funcio_2 IS
COMPONENT portaand2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portainv IS
PORT (a: IN BIT;
      z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portaand3 IS
PORT (a, b, c: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portaor4 IS
PORT (a, b, c, d: IN BIT;
	z: OUT BIT);
END COMPONENT;

SIGNAL inva, invc, invd, alpha, beta, chi, delta, epsilon, gamma: BIT;

FOR DUT1: portainv USE ENTITY WORK.inversor(logica);
FOR DUT2: portainv USE ENTITY WORK.inversor(logica);
FOR DUT3: portainv USE ENTITY WORK.inversor(logica);
FOR DUT4: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT5: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT6: portaand3 USE ENTITY WORK.and3(logica);
FOR DUT7: portaand3 USE ENTITY WORK.and3(logica);
FOR DUT8: portaor2 USE ENTITY WORK.or2(logica);
FOR DUT9: portaor4 USE ENTITY WORK.or4(logica);
FOR DUT10: portaxor2 USE ENTITY WORK.xor2(logica);


BEGIN
	DUT1: portainv PORT MAP (A, inva);
	DUT2: portainv PORT MAP (C, invc);
	DUT3: portainv PORT MAP (D, invd);
	DUT4: portaand2 PORT MAP (B, invd, beta);
	DUT5: portaand2 PORT MAP (A, invd, delta);
	DUT6: portaand3 PORT MAP (inva, B, invc, alpha);
	DUT7: portaand3 PORT MAP (A, C, D, chi);
	DUT8: portaor2 PORT MAP (A, invd, epsilon);
	DUT9: portaor4 PORT MAP (alpha, beta, chi, delta, gamma);
	DUT10: portaxor2 PORT MAP (epsilon, gamma, f);
END estructural;

ARCHITECTURE estructural_R OF funcio_2 IS
COMPONENT portaand2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portainv IS
PORT (a: IN BIT;
      z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portaand3 IS
PORT (a, b, c: IN BIT;
	z: OUT BIT);
END COMPONENT;

COMPONENT portaor4 IS
PORT (a, b, c, d: IN BIT;
	z: OUT BIT);
END COMPONENT;

SIGNAL inva, invc, invd, alpha, beta, chi, delta, epsilon, gamma: BIT;

FOR DUT1: portainv USE ENTITY WORK.inversor(logicaretard);
FOR DUT2: portainv USE ENTITY WORK.inversor(logicaretard);
FOR DUT3: portainv USE ENTITY WORK.inversor(logicaretard);
FOR DUT4: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT5: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT6: portaand3 USE ENTITY WORK.and3(logicaretard);
FOR DUT7: portaand3 USE ENTITY WORK.and3(logicaretard);
FOR DUT8: portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT9: portaor4 USE ENTITY WORK.or4(logicaretard);
FOR DUT10: portaxor2 USE ENTITY WORK.xor2(logicaretard);

BEGIN
	DUT1: portainv PORT MAP (A, inva);
	DUT2: portainv PORT MAP (C, invc);
	DUT3: portainv PORT MAP (D, invd);
	DUT4: portaand2 PORT MAP (B, invd, beta);
	DUT5: portaand2 PORT MAP (A, invd, delta);
	DUT6: portaand3 PORT MAP (inva, B, invc, alpha);
	DUT7: portaand3 PORT MAP (A, C, D, chi);
	DUT8: portaor2 PORT MAP (A, invd, epsilon);
	DUT9: portaor4 PORT MAP (alpha, beta, chi, delta, gamma);
	DUT10: portaxor2 PORT MAP (epsilon, gamma, f);
END estructural_R;

ENTITY bdp IS
END bdp;

ARCHITECTURE test OF bdp IS
COMPONENT bloc_que_simulem IS
PORT (A, B, C, D: IN BIT;
		f: OUT BIT);
END COMPONENT;

SIGNAL ent3, ent2, ent1, ent0, sort_logica, sort_logica_R,
	sort_estructural_R, sort_estructural: BIT;
FOR DUT1: bloc_que_simulem USE ENTITY WORK.funcio_2(logica);
FOR DUT2: bloc_que_simulem USE ENTITY WORK.funcio_2(logicaretard);
FOR DUT3: bloc_que_simulem USE ENTITY WORK.funcio_2(estructural);
FOR DUT4: bloc_que_simulem USE ENTITY WORK.funcio_2(estructural_R);

BEGIN
DUT1: bloc_que_simulem PORT MAP (ent0, ent1, ent2, ent3, sort_logica);
DUT2: bloc_que_simulem PORT MAP (ent0, ent1, ent2, ent3, sort_logica_R);
DUT3: bloc_que_simulem PORT MAP (ent0, ent1, ent2, ent3, sort_estructural);
DUT4: bloc_que_simulem PORT MAP (ent0, ent1, ent2, ent3, sort_estructural_R);


PROCESS (ent0, ent1, ent2, ent3)
BEGIN
	ent0 <= NOT ent0 AFTER 50 ns;
	ent1 <= NOT ent1 AFTER 100 ns;
	ent2 <= NOT ent2 AFTER 200 ns;
	ent3 <= NOT ent3 AFTER 400 ns;
END PROCESS;
END test;

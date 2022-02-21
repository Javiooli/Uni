ENTITY funcio_logica is
PORT (a, b, c, d: IN BIT;
	     f: OUT BIT);
END funcio_logica;

ARCHITECTURE logica OF funcio_logica IS
BEGIN
f <= ((a AND c AND (a XOR d)) OR (NOT b AND c));
END logica;

ARCHITECTURE logicaretard OF funcio_logica IS
BEGIN
f <= ((a AND c AND (a XOR d)) OR (NOT b AND c)) AFTER 3 ns;
END logicaretard;

ARCHITECTURE estructural OF funcio_logica IS
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

SIGNAL invb, alpha, beta, chi, delta: BIT;

FOR DUT1: portainv USE ENTITY WORK.inversor(logica);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT4: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT5: portaor2 USE ENTITY WORK.or2(logica);
FOR DUT6: portaxor2 USE ENTITY WORK.xor2(logica);

BEGIN
	DUT1: portainv PORT MAP (B, invb);
	DUT2: portaand2 PORT MAP (A, C, alpha);
	DUT3: portaand2 PORT MAP (C, invb, beta);
	DUT4: portaand2 PORT MAP (alpha, chi, delta);
	DUT5: portaor2 PORT MAP (beta, delta, f);
	DUT6: portaxor2 PORT MAP (A, D, chi);
END estructural;

ARCHITECTURE estructural_R OF funcio_logica IS
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

SIGNAL invb, alpha, beta, chi, delta: BIT;

FOR DUT1: portainv USE ENTITY WORK.inversor(logicaretard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT4: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT5: portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT6: portaxor2 USE ENTITY WORK.xor2(logicaretard);

BEGIN
	DUT1: portainv PORT MAP (B, invb);
	DUT2: portaand2 PORT MAP (A, C, alpha);
	DUT3: portaand2 PORT MAP (C, invb, beta);
	DUT4: portaand2 PORT MAP (alpha, chi, delta);
	DUT5: portaor2 PORT MAP (beta, delta, f);
	DUT6: portaxor2 PORT MAP (A, D, chi);
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
FOR DUT1: bloc_que_simulem USE ENTITY WORK.funcio_logica(logica);
FOR DUT2: bloc_que_simulem USE ENTITY WORK.funcio_logica(logicaretard);
FOR DUT3: bloc_que_simulem USE ENTITY WORK.funcio_logica(estructural);
FOR DUT4: bloc_que_simulem USE ENTITY WORK.funcio_logica(estructural_R);

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
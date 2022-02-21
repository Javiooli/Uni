ENTITY FF_D IS
PORT(d,clk,pre: IN BIT; q: OUT BIT);
END FF_D;

ARCHITECTURE ifthen OF FF_D IS

	SIGNAL qint: BIT;

	BEGIN
	PROCESS(d,clk,pre)

	BEGIN
		IF pre ='0' THEN qint<='1' AFTER 2 ns;
		ELSIF clk'EVENT AND clk='1' THEN qint <= d  AFTER 2 ns;
		END IF;
	END PROCESS;

	q<=qint;

END ifthen;

ENTITY mux4a1 IS
PORT(a,b,c,d,sel1,sel0: IN BIT; f: OUT BIT);
END mux4a1;

ARCHITECTURE logicaretard OF mux4a1 IS
	BEGIN
		f<= (a AND (NOT sel1) AND (NOT sel0)) OR
		(b AND (NOT sel1) AND sel0) OR
		(c AND sel1 AND (NOT sel0)) OR
		(d AND sel1 AND sel0) AFTER 2 ns;
END logicaretard;

ARCHITECTURE ifthen OF mux4a1 IS
	BEGIN
	PROCESS (sel1, sel0)
	
	BEGIN
		IF sel1='0' AND sel0='0' THEN f<=a AFTER 2 ns;
		ELSIF sel1='0' AND sel0='1' THEN f<=b AFTER 2 ns;
		ELSIF sel1='1' AND sel0='0' THEN f<=c AFTER 2 ns;
		ELSE f<=d AFTER 2 ns;
		END IF;
	END PROCESS;
END ifthen;

ENTITY registre IS
PORT(a2, a1, a0, sel1, sel0, E, clock: IN BIT; Q2, Q1, Q0: OUT BIT);
END registre;

ARCHITECTURE estructural OF registre IS
COMPONENT mux4a1 IS
PORT (a, b, c, d, sel1, sel0: IN BIT;
			f: OUT BIT);
END COMPONENT;

COMPONENT FF_D IS
PORT (d, clk, pre: IN BIT;
	q: OUT BIT);
END COMPONENT;

SIGNAL D2, D1, D0, S2, S1, S0: BIT;

FOR DUT1: mux4a1 USE ENTITY WORK.mux4a1(logicaretard);
FOR DUT2: mux4a1 USE ENTITY WORK.mux4a1(ifthen);
FOR DUT3: mux4a1 USE ENTITY WORK.mux4a1(logicaretard);
FOR DUT4: FF_D USE ENTITY WORK.FF_D(ifthen);
FOR DUT5: FF_D USE ENTITY WORK.FF_D(ifthen);
FOR DUT6: FF_D USE ENTITY WORK.FF_D(ifthen);

BEGIN
	DUT1: mux4a1 PORT MAP ('1', S2, a2, E, sel1, sel0, D2);
	DUT2: mux4a1 PORT MAP ('1', S1, a1, S2, sel1, sel0, D1);
	DUT3: mux4a1 PORT MAP ('1', S0, a0, S1, sel1, sel0, D0);
	DUT4: FF_D PORT MAP (D2, clock, '1', S2);
	DUT5: FF_D PORT MAP (D1, clock, '1', S1);
	DUT6: FF_D PORT MAP (D0, clock, '1', S0);

	PROCESS (S2, S1, S0)
	BEGIN
		Q2<=S2;
		Q1<=S1;
		Q0<=S0;
	END PROCESS;
END estructural;


ENTITY bdp IS
END bdp;

ARCHITECTURE test OF bdp IS
COMPONENT registre_que_simulem IS
PORT (a2, a1, a0, sel1, sel0, E, clock: IN BIT;
	Q2, Q1, Q0: OUT BIT);
END COMPONENT;
SIGNAL a2, a1, a0, sel1, sel0, E, clock, Q2, Q1, Q0: BIT; 

FOR DUT1: registre_que_simulem USE ENTITY WORK.registre(estructural);

BEGIN
DUT1: registre_que_simulem PORT MAP (a2, a1, a0, sel1, sel0, E, clock, Q2, Q1, Q0);

PROCESS (a2, a1, a0, sel1, sel0, E, clock)
BEGIN
	clock <= NOT clock AFTER 20 ns;
	sel0 <= NOT sel0 AFTER 40 ns;
	sel1 <= NOT sel1 AFTER 80 ns;
	a0 <= NOT a0 AFTER 160 ns;
	a1 <= NOT a1 AFTER 320 ns;
	a2 <= NOT a2 AFTER 640 ns;
END PROCESS;
END test;

-- Una de les primeres coses que verifica el correcte funcionament del registre és el fet que les sortides només s'actualitzin
-- amb els flancs de pujada del rellotge, ja que surten dels Flip-Flops.
-- Podem observar com, encara que hi hagi vàries realimentacions, no es produeix cap rebot. Això es completament gràcies als flip-flops,
-- però a més és molt probable que el retràs que he donat (2 ns) sigui suficientment baix com per a no afectar al correcte funcionament.
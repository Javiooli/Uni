-- Aquest circuit compta fins a 5 quan sel = '0'. La seva funcionalitat, llavors, és la d'un comptador de 0 a 5.
-- El primer Flip-Flop (amb sortida Q0), pràcticament fa la funció de rellotge però els seus pulsos duren el doble dels
-- pulsos del clock. La sortida Q0 fa de rellotge al Flip-Flop amb sortida Q1, que farà el mateix que fa el Flip-Flop
-- amb sortida Q0 respecte del clock però respecte de Q0, és a dir, torna a fer de rellotge però els seus pulsos duren
-- el doble dels pulsos de Q0, o quatre vegades els pulsos del clock.
-- El tercer Flip-Flop (amb sortida Q2), fa exactament el mateix però respecte de Q1.
-- D'aquesta manera aconseguim fer un comptador de 3 bits, és a dir, fins a 2^3 o 8.
-- De totes maneres, aquest circuit només compta fins a 5, i això és gràcies a la porta NAND que agafa Q0, /Q1 i Q2.
-- Amb aquesta combinació, la NAND tindrà sortida 1 sempre i quan la seva entrada no sigui 1, 0, 1, és a dir, 5.
-- Quan la entrada de la NAND és 101 o 5, la seva sortida (que és igual a 0) envia el senyal de clear als Flip-Flops,
-- i fa que tornin a començar a comptar.
-- Per últim, com he dit al principi del comentari, si sel = '1', el comptador es queda paral·litzat, ja que els multiplexors donaran
-- com a sortida la entrada b, que val 0, i en aquest cas el Flip-Flop amb sortida Q0 no fa el toggle, i com que el Flip-Flop amb
-- sortida Q1 depen de Q0, i el Flip-Flop amb sortida Q2 depen de Q1, el comptador es manté estable amb la última sortida que ha donat.

ENTITY circuit4 IS
PORT(sel, clock: IN BIT;
	Q2, Q1, Q0: OUT BIT);
END circuit4;

ARCHITECTURE estructural OF circuit4 IS
COMPONENT mux2a1 IS
PORT(S, a, b: IN BIT;
	f: OUT BIT);
END COMPONENT;
COMPONENT FF_T IS
PORT(T, clock, Clr: IN BIT;
	Q, noQ: OUT BIT);
END COMPONENT;
COMPONENT nand3 IS
PORT(a, b, c: IN BIT;
	z: OUT BIT);
END COMPONENT;

SIGNAL T0, T1, T2, qint2, qint1, qint0, noqint2, noqint1, noqint0, znand: BIT;

FOR DUT1: mux2a1 USE ENTITY WORK.mux2a1(logicaretard);
FOR DUT2: mux2a1 USE ENTITY WORK.mux2a1(estructural);
FOR DUT3: mux2a1 USE ENTITY WORK.mux2a1(ifthen);
FOR DUT4: FF_T USE ENTITY WORK.FF_T(ifthen);
FOR DUT5: FF_T USE ENTITY WORK.FF_T(ifthen);
FOR DUT6: FF_T USE ENTITY WORK.FF_T(ifthen);
FOR DUT7: nand3 USE ENTITY WORK.nand3(logicaretard);

BEGIN
	DUT1: mux2a1 PORT MAP (sel, '1', '0', T0);
	DUT2: mux2a1 PORT MAP (sel, '1', '0', T1);
	DUT3: mux2a1 PORT MAP (sel, '1', '0', T2);
	DUT4: FF_T PORT MAP (T0, clock, znand, qint0, noqint0);
	DUT5: FF_T PORT MAP (T1, noqint0, znand, qint1, noqint1);
	DUT6: FF_T PORT MAP (T2, noqint1, znand, qint2, noqint2);
	DUT7: nand3 PORT MAP (qint0, noqint1, qint2, znand);

	Q0 <= qint0; Q1 <= qint1; Q2 <= qint2;
END estructural;

ENTITY bdp_circuit4 IS
END bdp_circuit4;

ARCHITECTURE test_circuit4 OF bdp_circuit4 IS
COMPONENT circuit4 IS
	PORT (sel, clock: IN BIT;
		Q2, Q1, Q0: OUT BIT);
END COMPONENT;

SIGNAL sel, clock, Q2, Q1, Q0: BIT;

FOR DUT1: circuit4 USE ENTITY WORK.circuit4(estructural);

BEGIN
	DUT1: circuit4 PORT MAP (sel, clock, Q2, Q1, Q0);
	
	clock <= NOT clock AFTER 50 ns;
	sel <= NOT sel AFTER 800 ns;

END test_circuit4;
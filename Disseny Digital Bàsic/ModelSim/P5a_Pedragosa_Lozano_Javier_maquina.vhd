ENTITY inversor IS
PORT(a: IN BIT; z: OUT BIT);
END inversor;

ARCHITECTURE logicaretard OF inversor IS
BEGIN
z <= NOT a AFTER 3 ns;
END logicaretard;

ENTITY and2 IS
PORT(a, b: IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logicaretard OF and2 IS
BEGIN
z <= a AND b AFTER 3 ns;
END logicaretard;

ENTITY xor2 IS
PORT(a, b: IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logicaretard OF xor2 IS
BEGIN
z <= a XOR b AFTER 3 ns;
END logicaretard;

ENTITY JK_FF IS
PORT(j,k,clk: IN BIT; q: OUT BIT);
END JK_FF;

ARCHITECTURE ifthen OF JK_FF IS

	SIGNAL qint: BIT;

	BEGIN
	PROCESS (j,k,clk,qint)

	BEGIN
		IF clk'EVENT AND clk='1' THEN
			IF j='0' AND k='0' THEN qint<=qint AFTER 3 ns;
			ELSIF j='0' AND k='1' THEN qint<='0' AFTER 3 ns;
			ELSIF j='1' AND k='0' THEN qint<='1' AFTER 3 ns;
			ELSIF j='1' AND k='1' THEN qint<= NOT qint AFTER 3 ns;
			END IF;
		END IF;
	END PROCESS;

	q<=qint;

END ifthen;

ENTITY circuit_pr05 IS
PORT(clock, X: IN BIT;
	Z2, Z1, Z0: OUT BIT);
END circuit_pr05;

ARCHITECTURE estructural OF circuit_pr05 IS
COMPONENT xor2 IS
PORT(a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;
COMPONENT inversor IS
PORT(a: IN BIT;
	z: OUT BIT);
END COMPONENT;
COMPONENT and2 IS
PORT(a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;
COMPONENT JK_FF IS
PORT(j, k, clk: IN BIT;
	q: OUT BIT);
END COMPONENT;

SIGNAL zxor, zinv, zand, y2, y1, y0: BIT;

FOR DUT1: xor2 USE ENTITY WORK.xor2(logicaretard);
FOR DUT2: inversor USE ENTITY WORK.inversor(logicaretard);
FOR DUT3: and2 USE ENTITY WORK.and2(logicaretard);
FOR DUT4: JK_FF USE ENTITY WORK.JK_FF(ifthen);
FOR DUT5: JK_FF USE ENTITY WORK.JK_FF(ifthen);
FOR DUT6: JK_FF USE ENTITY WORK.JK_FF(ifthen);

BEGIN
	DUT1: xor2 PORT MAP (X, y0, zxor);
	DUT2: inversor PORT MAP (zxor, zinv);
	DUT3: and2 PORT MAP (zinv, y1, zand);
	DUT4: JK_FF PORT MAP (zand, zand, clock, y2);
	DUT5: JK_FF PORT MAP (zinv, zinv, clock, y1);
	DUT6: JK_FF PORT MAP (X, '1', clock, y0);

	Z2 <= y2; Z1 <= y1; Z0 <= y0;
END estructural;

ENTITY bdp_pr05 IS
END bdp_pr05;

ARCHITECTURE test_pr05 OF bdp_pr05 IS
COMPONENT circuit_pr05 IS
PORT(clock, X: IN BIT;
	Z2, Z1, Z0: OUT BIT);
END COMPONENT;

SIGNAL clock, X, Z2, Z1, Z0: BIT;

FOR DUT1: circuit_pr05 USE ENTITY WORK.circuit_pr05(estructural);

BEGIN
	DUT1: circuit_pr05 PORT MAP (clock, X, Z2, Z1, Z0);

	clock <= NOT clock AFTER 50 ns;
	X <= NOT X AFTER 30 ns;

END test_pr05;

-- Al cronograma podem observar com, de fet, la m�quina compleix amb el
-- funcionament esperat. En l'instant 50 ms, X = '1', aix� que amb el cop de
-- rellotge la m�quina suma 1 a la sortida, tal com s'esperava. En l'instant
-- 150 ms X canvia a 1 a la vegada que el rellotge, per� la m�quina la llegeix com
-- un 0, aix� que com que la sortida �s senar (1), li resta 1. A l'instant 250 ms
-- X = '0', i com que la sortida �s parella (0), li suma 2 a la sortida.
-- Aquest tros ja demostra que la m�quina ja exerceix les tres funcions que ha
-- de fer, per� si avan�em fins a l'instant 1150 ms la m�quina intenta sumar 2
-- a 6, ja que �s parell, per� com que nom�s compta fins a 7 i en arribar a 8 reinicia,
-- en aquest cas reinicia directament perqu� ja ha alcan�at 8.
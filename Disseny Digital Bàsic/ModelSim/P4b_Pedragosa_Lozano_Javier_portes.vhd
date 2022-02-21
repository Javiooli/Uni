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

ENTITY nand3 IS
PORT(a, b, c: IN BIT; z: OUT BIT);
END nand3;

ARCHITECTURE logicaretard OF nand3 IS
BEGIN
z <= NOT (a AND b AND c) AFTER 3 ns;
END logicaretard;

ENTITY or2 IS
PORT(a, b: IN BIT; z: OUT BIT);
END or2;

ARCHITECTURE logicaretard OF or2 IS
BEGIN
z <= a OR b AFTER 3 ns;
END logicaretard;

ENTITY mux2a1 IS
PORT(S, a, b: IN BIT; f: OUT BIT);
END mux2a1;

ARCHITECTURE logicaretard OF mux2a1 IS
	BEGIN
		f<= (a AND (NOT S)) OR
		(b AND (NOT S)) AFTER 2 ns;
END logicaretard;

ARCHITECTURE estructural OF mux2a1 IS
COMPONENT inversor IS
PORT (a: IN BIT;
	z: OUT BIT);
END COMPONENT;
COMPONENT and2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;
COMPONENT or2 IS
PORT (a, b: IN BIT;
	z: OUT BIT);
END COMPONENT;

SIGNAL invs, alpha, beta: BIT;

FOR DUT1: inversor USE ENTITY WORK.inversor(logicaretard);
FOR DUT2: and2 USE ENTITY WORK.and2(logicaretard);
FOR DUT3: and2 USE ENTITY WORK.and2(logicaretard);
FOR DUT4: or2 USE ENTITY WORK.or2(logicaretard);

BEGIN
	DUT1: inversor PORT MAP (S, invs);
	DUT2: and2 PORT MAP (a, invs, alpha);
	DUT3: and2 PORT MAP (S, b, beta);
	DUT4: or2 PORT MAP (alpha, beta, f);
END estructural;

ARCHITECTURE ifthen OF mux2a1 IS
BEGIN
	PROCESS (S)
	
	BEGIN
		IF S='0' THEN f<=a AFTER 2 ns;
		ELSE f<=b AFTER 2 ns;
		END IF;
	END PROCESS;
END ifthen;

ENTITY FF_T IS
PORT(T, clock, Clr: IN BIT;
	Q, noQ: OUT BIT);
END FF_T;

ARCHITECTURE ifthen OF FF_T IS
SIGNAL qint: BIT;
BEGIN
	PROCESS (T, clock, Clr, qint)

	BEGIN
		IF Clr='0' THEN qint<='0' AFTER 2 ns;
		ELSIF clock'EVENT AND clock='1' THEN
			IF T='0' THEN qint<=qint AFTER 2 ns;
			ELSE qint<= NOT qint AFTER 2 ns;
			END IF;
		END IF;
	END PROCESS;
	Q <= qint; noQ <= NOT qint;
END ifthen;

	AREA		SRAM1,		NOINIT,		READWRITE
	SPACE		0x0200
Stack_Top

	AREA		RESET,		DATA,		READONLY
	DCD			Stack_Top				;[0x0000-0x0003]
	DCD			Start_Init				;[0x0004-0x0007]

	AREA		PROGRAM,	CODE,		READONLY
	ENTRY
	
Start_Init

	; CPACR is located at address 0xE000ED88
	LDR.W 		R0, 		=0xE000ED88
	; Read CPACR
	LDR 		R1, 		[R0]
	; Set bits 20-23 to enable CP10 and CP11 coprocessors
	ORR 		R1, 		R1, 		#(0xF << 20)
	; Write back the modified value to the CPACR
	STR 		R1, 		[R0]		; wait for store to complete
	DSB
	;reset pipeline now the FPU is enabled
	ISB

	VLDR.F32	S0,			=0.0
	VLDR.F32	S1,			=2.56
	MOV			R0,			#2
	MOV			R1,			#3
	MOV			R2,			#-4
	MOV			R3,			#0				;R3 = k
	MOV			R7,			#-1
	
Count
	MOV			R4, 		#1				;R4 = R1^0
	CMP			R3,			#0				;if k = 0
	MOV			R5,			#0				;R5 = i
	BLS			end_for
start_for					
	CMP			R5,			R3
	BGE			end_for
	MUL			R4,			R1				;R4 = R1^k
	ADD			R5,			#1				;R5 = i+1
	B			start_for
end_for
	ADD			R3,			#1				;R3 = k+1
	
Koren_i_modul
	SUB			R4,			R0,			R4	;R4 = R0-R1^k = S3
	VMOV		S3,			R4
	VCVT.F32.S32			S3,			S3
	
	MUL			R6,			R2,			R3	;R6 = R2*k
	CMP			R6,			#0				
	MULMI		R6,			R7				;R6 = |R2*k| = S2
	
	VMOV		S2,			R6
	VCVT.F32.S32			S2,			S2
	
	
	VADD.F32	S2,			S1				;S2 = 2,56+|(R2*k)| (S1,S2)
	VCMP.F32	S2,			#0.0
	VSQRTPL.F32 	S2, 		S2				;S2 = S2^0.5
	
	VCMP.F32	S2,			#0.0
	VDIVNE.F32	S2,			S3,			S2	;S2 = (R0-R1^k)/(2,56+|(R2*k)|)^0.5 (S3,S2)
	VADD.F32	S0,			S2				;S0 = SUM
	CMP			R3,			#7
	BLS			Count

Main_Loop
	B			Main_Loop
	
	END
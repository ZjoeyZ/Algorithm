;一个.com文件格式的程序，完成从键盘上输入一个4位以内的16进制数，并以10进制形式显示 出来。 
HEX2DEC SEGMENT         
ORG     100H         
ASSUME CS:HEX2DEC,SS:HEX2DEC,DS:HEX2DEC,ES:HEX2DEC 
MAIN    PROC    NEAR         
XOR     BX,BX         
MOV     CH,4 
L1:     
MOV     AH,1       
int 21h 
cmp   AL,0DH         
JE      DISP10         
CMP     AL,30H         
JB      L1         
cmp al,39h
JBE     L2         
CMP     AL,41H         
JB      L1       
CMP     AL,46H         
JBE     L2         
CMP     AL,61H         
JB      L1         
CMP     AL,66H         
JA      L1         
SUB     AL,20H 
L2:     
SUB     AL,30H         
CMP     AL,9         
JBE     L3
SUB     AL,7 
L3:     
MOV     CL,4         
SHL    BX,CL         
XOR     AH,AH         
ADD     BX,AX     
dec ch
JNZ     L1 
DISP10: 
LEA     DX,CRLF         
MOV     AH,9         
INT     21H         
OR      BX,BX         
JNS     L4         
MOV     DL,2DH         
MOV     AH,2         
INT     21H         
NEG     BX 
L4:     
XOR     CX,CX         
MOV     AX,BX 
L5:     
XOR     DX,DX        
mov si,10
DIV     SI         
PUSH    DX         
INC     CX         
OR      AX,AX        
JZ      L6         
JMP     L5 
L6:     
POP     DX  
add dl,30h 
MOV     AH,2         
INT     21H         
LOOP    L6         
MOV  AX,4C00H 
INT  21H
CRLF    DB      0DH,0AH,24H 
MAIN ENDP 
HEX2DEC ENDS      
END     MAIN 
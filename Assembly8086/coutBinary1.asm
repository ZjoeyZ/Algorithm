;已知变量X的值为2767，请编程序统计变量X中的二进制值中有多少个1，并记入ONE变量中。
HEX2DEC SEGMENT         
ORG     100H         
ASSUME CS:HEX2DEC,SS:HEX2DEC,DS:HEX2DEC,ES:HEX2DEC 
MAIN    PROC    NEAR         
	mov cx,16
	mov dx,0
	mov ax,x
go:	rol ax,1
	jnc back
	inc dx
back:
	loop go
	lea si,one
	mov [si],dx
	
	
	mov ah,2
	int 21h
	mov ax,4c00h
	int 21h
x dw 2726
one db ?
MAIN endp
HEX2DEC ends
	end MAIN

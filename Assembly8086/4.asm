;已知变量X的值为2767，请编程序统计变量X中的二进制值中有多少个1，并记入ONE变量中。
.model small
.stack
.data 
x dw 2726
one db ?
.code
start:      
	mov ax,@data
	mov ds,ax
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
	
	add dl,30h
	mov ah,2
	int 21h
	mov ax,4c00h
	int 21h
end start
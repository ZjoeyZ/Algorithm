;编写一子程序SKIPLINES，完成输出空行的功能。空行的行数在参数AX寄存器中
.model small
.stack 100h
.data
crlf db 0ah,0dh,24h
.code
start:
	mov ax,@data
	mov ds,ax
	mov ax,0
	mov ah,1
	int 21h
	mov ah,0
	mov cx,ax
go: call far ptr SKIPLINES
	loop go

	mov ax,4c00h
	int 21h
	
SKIPLINES proc far
	push dx
	lea dx,crlf
	mov ah,09h
	int 21h
	pop dx
	ret 
SKIPLINES endp

end start
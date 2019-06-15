;编写一段子程序 BINIHEX，完成十六进制数输出的功能。要输出的数在 AX 寄存器中
;debug:每次div 除以16之前应该先清空dx，应为是dx,ax 除以16
.model small
.stack 40h
.data 
x dw 0ffaah
.code
start:
	mov ax,@data
	mov ds,ax
	mov ax,x
	
	call BINIHEX
	mov ax,4c00h
	int 21h
	
BINIHEX proc near
	mov bp,16
	
	xor si,si
	
go:	xor dx,dx
	div bp
	push dx
	inc si
	cmp ax,0
	jnz go
	
po:	pop dx
	cmp dx,9
	jle print
	add dx,7h
print:
	add dx,30h
	mov ah,2
	int 21h
	dec si
	jnz po
	ret
BINIHEX endp

end start
	
	

	
;从键盘上输入一系列以$为结束符的字符串，然后对其中的非数字字符计数，并显示出计数结果。 
.model small
.stack 60h
.data
.code
start:
	mov ax,@data
	mov ds,ax
	xor dx,dx

l1:	mov ah,1
	int 21h
	
	cmp al,24h
	je exit
	cmp al,30h
	jb l2
	cmp al,39h
	jbe l1
l2: inc dx
	jmp l1
	
exit:
	mov ax,dx
	mov bp,10
	xor cx,cx
	xor dx,dx
	
go:	div bp
	push dx
	inc cx
	cmp ax,0
	jnz go
	
print:	
	pop dx
	add dx,30h
	mov ah,2
	int 21h
	loop print
	mov ax,4c00h
	int 21h
end start
;let feel the rain
;let me suffer the pain
;let me toch the fin
;let hold on your dream

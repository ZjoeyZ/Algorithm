; slect sort to get a list which is ordered from smallest to greatest
;debug1:bp 默认段寄存器是ss
;debug2;dx ponited at the first one and stay fixed
s1 segment stack
	db 40 dup(?)
	top label word
s1	ends

s2 segment 
list dw	23,-12,22,1,33,55,21
len  dw	7
crlf dw 0ah,0dh,24h
s2	ends

s3	segment
	assume	cs:s3,ds:s2,ss:s1
main proc far
	mov ax,s1
	mov ss,ax
	lea sp,top
	mov ax,s2
	mov ds,ax
	
	lea dx,list	 ;dx ponted at the first number
	mov cx,len
	dec cx		 ;gonna loop for (len-1) times

go:	 mov bp,dx   ;bp the smallest number,but now poined at the first num
	 mov bx,bp
	 mov ax,cx   ;compare for ax times
	 
find: 
	 inc bx
	 inc bx	 ;bx poined at next number
	 mov si,ds:[bp]
	 mov di,[bx]
	 cmp si,di
	 jl  ncm
cm:  mov bp, bx
ncm: 	
	dec ax
	cmp ax,0
	jnz	find
	
    mov bx,dx
swp:mov si,[bx];first num
	mov di,ds:[bp];smallest num
	mov [bx],di
	mov ds:[bp],si
nsw:inc dx
    inc dx	;next compare start from  dx+2
    loop go
	
print:	
		lea si,list
		cld
		mov cx,len
l1:		lodsw 
		test ax,8000h
		jz l2
		push ax
		mov ah,2
		mov dl,'-'
		int 21h
		pop ax
		neg ax
l2:		xor di,di
		mov bp,10
l3:		xor dx,dx
		div bp
		push dx
		inc di
		cmp ax,0
		jne	l3
l4:		pop dx
		add dl,30h
		mov ah,2
		int 21h
		dec di
		jnz l4
		
		lea dx,crlf
		mov ah,9
		int 21h
		
		loop l1
		
	mov ax,4c00h
	int 21h

main endp	
s3	ends
	end main
	 
	 
	
	
	
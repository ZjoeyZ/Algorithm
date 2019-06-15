; sort to get a list which is ordered from smallest to greatest
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
	
	;out sort,the inner sort loop time is decreasing
	
	;inner first sort loop get the address of the loop times + 1th number's address
	;and loop for the time which is the nth loop time
	lea bx,list	;pointed at the first one
	mov cx,len  
	mov di,cx
	dec cx		;total loop times
sel:
	;ganna compare for di times di = len - cx
	sub di,cx
	;si pointed at the last one but first to compare 
	mov si,bx
	;bp one pointed at the next number
	inc bx
	inc bx	;debug forgot it is word, not byte 
	mov bp,bx
	
	;compare it to the one before it 
com: mov ax,ds:[bp]
	 mov dx,[si]
	 cmp ax,dx
	 jnl nsw ;debug: jl nsw and nsw pointed at next loop 
swi: mov [si],ax
	 mov ds:[bp],dx
	mov bp,si;bug dec bp
	dec si
	dec si ;debug forgot it is word, not byte 
	dec di
	cmp di,0
	jnz com
nsw:loop sel
	
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
	 
	 
	
	
	
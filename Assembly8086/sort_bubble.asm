s1 segment stack
    db  40h dup(0)
top label word
s1  ends

s2 segment
list dw  1,23,423,2,34,321	
len  dw   6
s2  ends

s3  segment
       assume    cs:s3,ds:s2,ss:s1
p      proc   far
        mov ax,s1
        mov ss,ax
        lea   sp,top
        mov ax,s2
        mov ds,ax
		
		
        lea bx,list
		mov cx,len
		dec cx			;一次bubble sort 比较几次
		add bx,cx
		add	bx,cx
		mov dx,bx		;dx 始终指向最后一个数
		
		mov ax,cx		;bubble sort 次数
		
bubble:	mov si,[bx]
		mov di,[bx-2]
		cmp si,di
		ja	ok
		mov [bx],di
		mov [bx-2],si
ok:		dec bx
		dec bx
		loop bubble
		dec ax			
		mov cx,ax		;恢复初始值
		mov bx,dx
		cmp ax,0
		jne bubble		;新一轮bubble sort

        mov ax,4c00h
        int 21h

p      endp
s3     ends
       end p

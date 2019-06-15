;something for test what kind of value the register can recieve
s1 segment
    db  40h dup(0)
top label word
s1  ends

s2 segment
ary dw  10,730,-5,30,100h
      dw   0,0ffh,5,25,-9,73
crlf db   0dh,0ah,24h
s2  ends

s3  segment
       assume    cs:s3,ds:s2,ss:s1
p      proc   far
        mov ax,s1
        mov ss,ax
        lea   sp,top
        mov ax,s2
        mov ds,ax

		;mov ax,65535   ax = ffff
		;mov ax,-65535	ax = 0001
		;mov ax,-65536	overflow
		;mov ah,256 		value out of range
		mov al,-255		;ah = 01
		cbw
		;mov ah,-128			ah = 10
        mov ax,4c00h
        int 21h

p      endp
s3     ends
        end p

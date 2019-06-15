;十进制形式多个数分行输出，data里第一个数据是要输出的个数
;1, 加载数字个数，放在cx中，为程序的总循环次数
;2,加载数字，判断正负
;       负数: 不跳转，将数字push进栈后，输出一个符号，再讲数字pop出栈，取反
;       正数：跳转，将dx清零，dx,ax/bp, 余数进栈，计数+1，除数判断是否为0，
;                 输出字符串：回车换行
;4，loop 结束

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

        cld
        lea si,ary
        lodsw
        mov cx,ax

l1:    lodsw
        test ax,8000h
        jz  l2
        push ax
        mov dl,'-'
        mov ah,2
        int 21h
        pop ax
        neg ax

l2:    xor  dx,dx
        mov bp,10
        div  bp
        push    dx
        inc di
        cmp ax,0
        jne l2

l4:     pop dx
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

p      endp
s3     ends
        end p

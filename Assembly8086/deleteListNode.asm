;Link指向一个字线性表，其中的首单元保存线性表的长度，编一个程序，将该线性表中内容为0FFFFH 的项删除。 
.model small
.stack 40h
.data
link dw 10,1,2,3,4,5,6,7,8,9,0fffh
buff dw 10 dup(?)
.code
start:
	;遍历link每一项，不是offf就复制到buff里，如果是则跳过
	mov ax,@data
	mov ds,ax
	lea si,link
	lea di,buff
	mov cx,[si]
	
go:	inc si
	inc si
	mov ax,[si]
	cmp ax,0fffh
	je go
	inc di
	inc di
	mov [di],ax
	loop go
	
	inc di
	inc di
	mov [di],word ptr 24h
	lea dx,buff
	mov ah,9h
	int 21h
	
	mov ax,4c00h
	int 21h
end start
	
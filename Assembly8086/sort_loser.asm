; sort to get a list which is ordered from smallest to greatest
;利用败者树进行归并，在算法中避开了外存信息存储的细节，认为并归段已经在内存中，即--下面的3给list
;使用三路败者树将3给归并段的值合并到一个列表里
;使用一个列表leaves装入败者树的叶子，每个并归段会将他们的第一个值传入leaves
;败者树也使用顺序存储结构的列表实现，本算法实现的是3路归并的败者树，所以列表里有3个结点，依次存入较小值在leaves中的下标index
;首先是初始化leaves 和 loser tree
;然后开始调整，循环3次,n = 0,1,2
;		从第n个叶子开始向上比较，叶子值和结点指向的叶子值比较，
;		循环：小的胜利继续向上比较，大的失败，他在leaves中的index留在结点里
;		当比到loser tree 的第0个结点时，不用比较，直接把这个最小值在leaves 里的index留在第0个结点里
;调整完毕，
;只要第0个结点的index，指向的不是255，说明还没归并完，leaves里面不是【255,255,255,-100】
;	取到第0个结点的index指向的leaves里的值
;	把这个值存到list里去
;	把这个值从它之前的归并段里抹去
;	把这个归并段的第一个值存到第0个结点的index指向的leaves里的位置
;	从这个index（第几个叶子）开始向上调整

s1 segment stack
	db 40 dup(?)
	top label word
s1	ends

s2 segment 
list0 dw	4, 8, 10, 15, 255	; msp 255
len0  dw	5

list1 dw    5, 14, 21, 255		
len1  dw    4

list2 dw    1, 2, 77,255
len2  dw    4

leaves dw	4 dup(?)		; 3路的 loser tree，三个叶子，第四个叶子装最小值  4,5,1,-100
loser_tree dw	3 dup(3)	; 3 个结点的loser tree，初始化是最小值在leaves的index

list  dw    10 dup(?)		;排序成功的list合集 10 = len1 + len2 + len0
len dw 10
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
	
	call loser_tree_sort
	call print
	
	mov ax,4c00h
	int 21h
main endp	
;debug 1,ad: not ad:mov cx,0




loser_tree_sort proc near
	;add the first one of every list in to leaves

	lea bx, leaves
	mov ax, list0
	mov [bx],ax
	mov ax, list1
	mov [bx+2],ax
	mov ax, list2
	mov [bx+4],ax
	mov ax, -100		;-100 is the min key ，leaves【4，5，1，-100】
	mov [bx+6],ax
	lea bp,loser_tree
	;first time adjust the loser tree
	mov dx,3
	mov cx,0	;cx = i	，index 第几个叶子
ad:	call adjust			;debug 1,ad: not ad:mov cx,0
	inc cx
	dec dx
	cmp dx,0
	jnz ad
	
	mov dx,0
go: 
	push bx
	mov si,ds:[bp]		;si got the index the the smallest number in leaves
	lea di,list			
	add di,dx
	add di,dx
	add bx,si
	add bx,si			;debug3、4add 2times si and not al but ax below
	mov ax,[bx]
	cmp ax,255
	je back
	mov [di],ax		;把指向值存到list里去
	pop  bx
	inc dx			
	;把这个值从它之前的归并段里抹去,判断是哪一个归并段，sp是多少就是那哪一个段
	cmp si,0
	je	pop0
	cmp si,1
	je  pop1
	cmp si,2
	je  pop2
	
	whi:
	mov cx,si
	call adjust		;debug 5 remmenber to adjust
	mov ax,ds:[bp]	;loser tree 的第0个各节点值给ax
	push bx
	add bx,ax
	add bx,ax		;bx指向leaves里的最小值
	mov ax,[bx]
	pop bx			;debug 6 remver don't change bx
	cmp ax,255		;若最小值不是255 继续
	jne go
	
back:	ret

;抹去归并段第一个值，其他每个值向前移动一位,把第一个值传到leaves里的相应位置
pop0: 
	push bx
	lea di,list0
	mov ax,[di+2]
	mov [di],ax
	mov ax,[di+4]
	mov [di+2],ax
	mov ax,[di+6]
	mov [di+4],ax
	mov ax,[di+8]
	mov [di+6],ax
	mov ax,[di]
	add bx,si
	add bx,si
	mov [bx],ax
	pop bx
	jmp	whi 

pop1: 
	push bx
	lea di,list1
	mov ax,[di+2]
	mov [di],ax
	mov ax,[di+4]
	mov [di+2],ax
	mov ax,[di+6]
	mov [di+4],ax
	add bx,si
	add bx,si
	mov ax,[di]
	mov [bx],ax
	pop bx
	jmp	whi 
	
pop2: 
	push bx
	lea di,list2
	mov ax,[di+2]
	mov [di],ax
	mov ax,[di+4]
	mov [di+2],ax
	mov ax,[di+6]
	mov [di+4],ax
	add bx,si
	add bx,si
	mov ax,[di]
	mov [bx],ax
	pop bx
	jmp	whi 
loser_tree_sort endp	





adjust proc near
	push di
	push si
	push dx
	push bp
	push ax
	push cx
	;cx = i，ax=第几个父节点，bp=父节点的地址,从i开始向上调整败者树
	
	mov ax,cx
	add ax,3
	mov dx,0
	mov di,2
	div di		;ax = 父节点是第几个结点
cp: push bp
	add bp,ax		;bp pointed at the parent,bp指向父节点 
	add bp,ax
	mov si,ds:[bp] ; si got that parent，si得到父结点值
	mov dx,si
	add si,bx		
	add si,dx		; si *2 + bx 指向leaves中的父值
	;compare the number with it‘s parent's numbr
	mov di,bx	
	add di,cx		;di pointed at the number，si 指向leaves中的竞争值
	add di,cx
	mov dx,[si]
	cmp [di],dx	;比较两个leaves的值谁大
	jl nsw
;竞争值大则输了，要把i留下，bp指向的父节点值要存入i
;switch i and loser_tree[parent_index]
	mov dx,cx
	mov si,ds:[bp] 
	mov cx,si		;父节点值进入i
	mov ds:[bp],dx	;i 进入父节点
	
;竞争值小则赢了，更新sp指向的父节点
nsw:
	mov dx,0
	mov di,2
	div di			;ax = 父节点是第几个结点
	pop bp			;bp 指向败者树
	cmp ax,0		;父节点是第0个结点结束比较
	jnz cp
	mov ds:[bp],cx	;debug2 not [bp] but ds:[bp]			;把i存到底0个结点里
	
	pop cx
	pop ax
	pop bp
	pop dx
	pop si
	pop di
	ret
adjust endp





print proc near	
		push si
		push di
		push bx
		push ax
		push cx
		push dx
		push bp
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
		pop bp
		pop dx
		pop cx
		pop ax
		pop bx
		pop di
		pop si
		ret
print   endp

s3	ends
	end main

s3	ends
	end main
	 
	 
	
	
	
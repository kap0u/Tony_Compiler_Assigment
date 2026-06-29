.class public TonyProgram
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 50
.limit locals 50

; assignment
iconst_0
istore 2
; assignment
iconst_1
istore 1
for_start_0:
iload 1
iconst_5
if_icmple cmp_true_2
iconst_0
goto cmp_end_3
cmp_true_2:
iconst_1
cmp_end_3:
ifeq for_end_1
; assignment
iload 2
iload 1
iadd
istore 2
; assignment
iload 1
iconst_1
iadd
istore 1
goto for_start_0
for_end_1:
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 2
invokevirtual java/io/PrintStream/println(I)V

return
.end method

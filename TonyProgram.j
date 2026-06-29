.class public TonyProgram
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 50
.limit locals 50

; assignment
iconst_5
istore 1
iload 1
iconst_3
if_icmpgt cmp_true_2
iconst_0
goto cmp_end_3
cmp_true_2:
iconst_1
cmp_end_3:
ifeq if_else_0
getstatic java/lang/System/out Ljava/io/PrintStream;
iconst_1
invokevirtual java/io/PrintStream/println(I)V
goto if_end_1
if_else_0:
getstatic java/lang/System/out Ljava/io/PrintStream;
iconst_0
invokevirtual java/io/PrintStream/println(I)V
if_end_1:

return
.end method

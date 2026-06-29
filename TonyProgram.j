.class public TonyProgram
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 50
.limit locals 50

getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "Code generation demo"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
; assignment
bipush 10
istore 1
; assignment
iconst_3
istore 2
; assignment
iload 1
iload 2
iadd
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
invokevirtual java/io/PrintStream/println(I)V
; assignment
iload 1
iload 2
isub
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
invokevirtual java/io/PrintStream/println(I)V
; assignment
iload 1
iload 2
imul
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
invokevirtual java/io/PrintStream/println(I)V
; assignment
iload 1
iload 2
idiv
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
invokevirtual java/io/PrintStream/println(I)V
; assignment
iload 1
iload 2
irem
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
invokevirtual java/io/PrintStream/println(I)V
; assignment
iload 1
iload 2
if_icmpgt cmp_true_0
iconst_0
goto cmp_end_1
cmp_true_0:
iconst_1
cmp_end_1:
istore 6
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 6
invokevirtual java/io/PrintStream/println(Z)V
iload 1
bipush 10
if_icmpge cmp_true_4
iconst_0
goto cmp_end_5
cmp_true_4:
iconst_1
cmp_end_5:
ifeq if_else_2
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "if branch"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iconst_1
invokevirtual java/io/PrintStream/println(I)V
goto if_end_3
if_else_2:
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "else branch"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iconst_0
invokevirtual java/io/PrintStream/println(I)V
if_end_3:
; assignment
iconst_0
istore 5
; assignment
iconst_1
istore 4
for_start_6:
iload 4
iconst_5
if_icmple cmp_true_8
iconst_0
goto cmp_end_9
cmp_true_8:
iconst_1
cmp_end_9:
ifeq for_end_7
; assignment
iload 5
iload 4
iadd
istore 5
; assignment
iload 4
iconst_1
iadd
istore 4
goto for_start_6
for_end_7:
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 5
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
bipush 65
invokevirtual java/io/PrintStream/println(C)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "done"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

return
.end method

import java_cup.runtime.Symbol;



%%

%class Lexer
%unicode
%integer
%line
%column
%cup 

%{
        

public static class Token {
        // keywords
        public static final int T_AND = 1;
        public static final int T_BOOL = 2;
        public static final int T_CHAR = 3;
        public static final int T_DECL = 4;
        public static final int T_DEF = 5;
        public static final int T_ELSE = 6;
        public static final int T_ELSIF = 7;
        public static final int T_END = 8;
        public static final int T_EXIT = 9;
        public static final int T_FALSE = 10;
        public static final int T_FOR = 11;
        public static final int T_HEAD = 12;
        public static final int T_IF = 13;
        public static final int T_INT = 14;
        public static final int T_LIST = 15;
        public static final int T_MOD = 16;
        public static final int T_NEW = 17;
        public static final int T_NIL = 18;
        public static final int T_NILQ = 19;   // nil?
        public static final int T_NOT = 20;
        public static final int T_OR = 21;
        public static final int T_REF = 22;
        public static final int T_RETURN = 23;
        public static final int T_SKIP = 24;
        public static final int T_TAIL = 25;
        public static final int T_TRUE = 26;

        // general
        public static final int T_ID = 27;
        public static final int T_NUM = 28;
        public static final int T_STRING = 29;

        // separators
        public static final int T_LPAR = 30;      // (
        public static final int T_RPAR = 31;      // )
        public static final int T_LBRACKET = 32;  // [
        public static final int T_RBRACKET = 33;  // ]
        public static final int T_COMMA = 34;     // ,
        public static final int T_SEMICOLON = 35; // ;
        public static final int T_COLON = 36;     // :
        public static final int T_ASSIGN = 37;    // :=

        // operators
        public static final int T_PLUS = 38;      // +
        public static final int T_MINUS = 39;     // -
        public static final int T_TIMES = 40;     // *
        public static final int T_DIV = 41;       // /
        public static final int T_EQ = 42;        // =
        public static final int T_NEQ = 43;       // <>
        public static final int T_LT = 44;        // <
        public static final int T_GT = 45;        // >
        public static final int T_LE = 46;        // <=
        public static final int T_GE = 47;        // >=
        public static final int T_HASH = 48;      // #
        public static final int T_CHARCONST = 49;
};

%}




delim = [ \t\n\r]
ws    = {delim}+

letter = [A-Za-z]
digit  = [0-9]

id     = {letter}({letter}|{digit}|_|\?)*
number = {digit}+
str_char = [^\n\r\"\\]
escape   = \\[ntr0\\\'\"]
string   = \"({str_char}|{escape})*\"
hex      = [0-9A-Fa-f]
char_char = [^\n\r\'\\]
char_escape = \\(n|t|r|0|\\|\'|\"|x[0-9A-Fa-f]{2})
char_const = \'({char_char}|{char_escape})\'
%%

// multi-character operators 
":="     { return new Symbol(sym.T_ASSIGN); }
"<="     { return new Symbol(sym.T_LE); }
">="     { return new Symbol(sym.T_GE); }
"<>"     { return new Symbol(sym.T_NEQ); }
// separators
"("      { return new Symbol(sym.T_LPAR); }
")"      { return new Symbol(sym.T_RPAR); }
"["      { return new Symbol(sym.T_LBRACKET); }
"]"      { return new Symbol(sym.T_RBRACKET); }
","      { return new Symbol(sym.T_COMMA); }
";"      { return new Symbol(sym.T_SEMICOLON); }
":"      { return new Symbol(sym.T_COLON); }

// single-character operators
"+"      { return new Symbol(sym.T_PLUS); }
"-"      { return new Symbol(sym.T_MINUS); }
"*"      { return new Symbol(sym.T_TIMES); }
"/"      { return new Symbol(sym.T_DIV); }
"="      { return new Symbol(sym.T_EQ); }
"<"      { return new Symbol(sym.T_LT); }
">"      { return new Symbol(sym.T_GT); }
"#"      { return new Symbol(sym.T_HASH); }

// keywords
"and"    { return new Symbol(sym.T_AND); }
"bool"   { return new Symbol(sym.T_BOOL); }
"char"   { return new Symbol(sym.T_CHAR); }
"decl"   { return new Symbol(sym.T_DECL); }
"def"    { return new Symbol(sym.T_DEF); }
"else"   { return new Symbol(sym.T_ELSE); }
"elsif"  { return new Symbol(sym.T_ELSIF); }
"end"    { return new Symbol(sym.T_END); }
"exit"   { return new Symbol(sym.T_EXIT); }
"false"  { return new Symbol(sym.T_FALSE); }
"for"    { return new Symbol(sym.T_FOR); }
"head"   { return new Symbol(sym.T_HEAD); }
"if"     { return new Symbol(sym.T_IF); }
"int"    { return new Symbol(sym.T_INT); }
"list"   { return new Symbol(sym.T_LIST); }
"mod"    { return new Symbol(sym.T_MOD); }
"new"    { return new Symbol(sym.T_NEW); }
"nil?"   { return new Symbol(sym.T_NILQ); }
"nil"    { return new Symbol(sym.T_NIL); }
"not"    { return new Symbol(sym.T_NOT); }
"or"     { return new Symbol(sym.T_OR); }
"ref"    { return new Symbol(sym.T_REF); }
"return" { return new Symbol(sym.T_RETURN); }
"skip"   { return new Symbol(sym.T_SKIP); }
"tail"   { return new Symbol(sym.T_TAIL); }
"true"   { return new Symbol(sym.T_TRUE); }

// literals and identifiers
{number} { return new Symbol(sym.T_NUM , Integer.parseInt(yytext())); }
{string} { return new Symbol(sym.T_STRING , yytext()); }
{char_const} { return new Symbol(sym.T_CHARCONST , yytext()); }
{id}     { return new Symbol(sym.T_ID , yytext()); } 


// ignore whitespace and single-line comments
{ws}         {}
"%"[^\n]*    {}

// lexical error
. {
    System.out.println("Lexical error at line " + yyline +
                       ", column " + yycolumn +
                       ": " + yytext());
}


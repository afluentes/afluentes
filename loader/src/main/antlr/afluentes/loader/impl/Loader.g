grammar Loader;

options {
	output=AST;
	ASTLabelType=CommonTree;
}

@header {
	package afluentes.loader.impl;
}

@lexer::header {
	package afluentes.loader.impl;
}

start
	:	'.'! edgeList
	;

edgeList
	:	edge
	|	'{'! edge (','! edge)* '}'!
	;
	
edge
	:	navigation
	|	ID
	;
	
navigation
	:	ID^ '.'! edgeList
	;

ID  	
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

WS  	
	: 	(' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;}
	;
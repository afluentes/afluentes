grammar Loader;

options {
	output = AST;
	ASTLabelType = CommonTree;
}

@header {
	package afluentes.loader.impl;
}

@lexer::header {
	package afluentes.loader.impl;
}

@members {
	protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
		throw new MismatchedTokenException(ttype, input);
	}
	
	public void recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
		throw e;
	}
}

@rulecatch {
	catch (RecognitionException e) {
		throw e;
	}
}

start
	:	traversal* path
	;
	
traversal
	:	(ID '='! path ';'!)
	;

path
	:	step
	|	'{'! step (','! step)* '}'!
	;

step
	:	ID^
	|	ID^ '.'! path
	|	ID^ '('! ')'!
	|	ID^ '('! step ')'!
	;

ID
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

WS  	
	: 	(' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;}
	;
notNull(parameter) ::= 

<<if ($parameter.name$ == null) {
	throw new IllegalArgumentException("$parameter.name$ == null");
}>>
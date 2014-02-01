package afluentes.loader.impl;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

public class ParserTest {
	public static void testParser(String program) throws RecognitionException {
		CharStream characters = new ANTLRStringStream(program);
		LoaderLexer lexer = new LoaderLexer(characters);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LoaderParser parser = new LoaderParser(tokens);
		System.out.println(program);
		Tree tree = parser.start().tree;
		System.out.println(tree.toStringTree());
		print(0, tree);
		System.out.println();
	}

	public static void print(int level, Tree tree) {
		println(level, LoaderParser.tokenNames[tree.getType()] + ": " + tree.getText());
		int nextLevel = level + 1;
		for (int i = 0; i < tree.getChildCount(); ++i) {
			print(nextLevel, tree.getChild(i));
		}
	}
	
	public static void println(int level, String string) {
		for (int i = 0; i < level; ++i) {
			System.out.print("  ");
		}
		System.out.println(string);
	}

	public static void main(String[] args) throws RecognitionException {
		testParser("sender");

		testParser("sender.picture");
		
		testParser("sender.picture.mediaType");
		
		testParser("{sender, recipients}");
		
		testParser("{sender.picture, recipients.picture}");
		
		testParser("{sender.picture.mediaType, recipients.picture.mediaType}");
				
		testParser("visitUser = picture.mediaType; visitUser(sender)");
		
		testParser("visitUser = picture.mediaType; {visitUser(sender), visitUser(recipients)}");
		
		testParser("visitUser = picture.mediaType; visitMessage = {visitUser(sender), visitUser(recipients)}; visitMessage()");		
	}
}
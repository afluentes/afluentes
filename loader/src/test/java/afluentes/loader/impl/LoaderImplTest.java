package afluentes.loader.impl;

import java.util.List;

import org.antlr.runtime.RecognitionException;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.ILoader;

public class LoaderImplTest {
	public static void main(String[] args) throws RecognitionException {
		Dao dao = new Dao();
		IEvaluation<List<IMessage>> messages = dao.getMessages.y();
		ILoader<List<IMessage>> loader = new LoaderImpl<>(List.class, ".{sender.picture, recipients.picture, files.{mediaType}}");
		loader.load(messages);
		
		new StaticManualLoader().load(messages);
	}
}
package afluentes.loader.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;
import org.apache.commons.beanutils.PropertyUtils;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceEvaluator;
import afluentes.core.api.IReduceFlatEvaluator;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceEvaluator;
import afluentes.core.impl.ReduceFlatEvaluator;
import afluentes.core.impl.SynchronousEvaluator1;
import afluentes.loader.api.IEvaluationHolder;

import com.google.common.reflect.TypeToken;

class Compiler<Root> {
	private static final TypeToken<List<?>> LIST_TOKEN = new TypeToken<List<?>>() {
		private static final long serialVersionUID = 5972486484027311791L;		
	};
	
	IEvaluator1<Root, Root> compile(TypeToken<Root> token, String route) throws RecognitionException {
		CharStream characters = new ANTLRStringStream(route);
		LoaderLexer lexer = new LoaderLexer(characters);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LoaderParser parser = new LoaderParser(tokens);		
		return compile(token, parser.start().tree);		
	}
	
	private <Node> IEvaluator1<Node, Node> compile(TypeToken<?> token, Tree route) {		
		if (LIST_TOKEN.isAssignableFrom(token)) {
			ParameterizedType listType = (ParameterizedType) token.getType();
			TypeToken<?> elementToken = TypeToken.of(listType.getActualTypeArguments()[0]);
			return (IEvaluator1<Node, Node>) compileList(elementToken, route);
		} else {
			return (IEvaluator1<Node, Node>) compileElement(token, route);
		}
		
	}
	
/*---------------------------------------------------------------------------*/	
	private <Node> IEvaluator1<List<Node>, List<Node>> compileList(TypeToken<?> nodeToken, Tree nodeRoute) {		
		final IReduceFlatEvaluator<Node, List<Node>> reduceNodes = new ReduceFlatEvaluator<>(new IReduce<Node, List<Node>>() {
			@Override
			public List<Node> y(List<Node> x1s) {
				return x1s;
			}
		});
		
		final IReduceEvaluator<Object, Node> reduceNode = new ReduceEvaluator<>(this.<Node>reduceNode());
		
		final ISynchronousFunction1<Node, List<IEvaluation<?>>> getEvaluations = this.<Node>getEvaluations(nodeToken, nodeRoute);

		final IEvaluator1<List<Node>, List<IEvaluation<? extends Node>>> mapNodes = new SynchronousEvaluator1<>(new ISynchronousFunction1<List<Node>, List<IEvaluation<? extends Node>>>() {
			@Override
			public List<IEvaluation<? extends Node>> y(List<Node> x1s) {
				List<IEvaluation<? extends Node>> ys = new ArrayList<>();
				for (Node x1 : x1s) {
					ys.add(reduceNode.y(getEvaluations.y(x1)));
				}
				return ys;
			}
		});

		final IEvaluator1<List<Node>, List<Node>> loadNodes = new IEvaluator1<List<Node>, List<Node>>() {
			@Override
			public IEvaluation<List<Node>> y(IEvaluation<List<Node>> x1) {
				return reduceNodes.y(mapNodes.y(x1));
			}
		};

		return loadNodes;
	}
/*---------------------------------------------------------------------------*/
	
/*---------------------------------------------------------------------------*/	
	private <Node> IEvaluator1<Node, Node> compileElement(TypeToken<?> nodeToken, final Tree nodeRoute) {				
		final IReduceFlatEvaluator<Object, Node> reduceNode = new ReduceFlatEvaluator<>(new IReduce<Object, Node>() {
			@Override
			public Node y(List<Object> x1s) {
				return (Node) x1s.get(0);
			}
		});		
		
		final IEvaluator1<Node, List<IEvaluation<?>>> getEvaluations = new SynchronousEvaluator1<>(this.<Node>getEvaluations(nodeToken, nodeRoute));
		
		final IEvaluator1<Node, Node> loadNode = new IEvaluator1<Node, Node>() {
			@Override
			public IEvaluation<Node> y(IEvaluation<Node> x1) {
				return reduceNode.y(getEvaluations.y(x1));
			}
		};
		
		return loadNode;
	}
/*---------------------------------------------------------------------------*/
	
	private <Node> IReduce<Object, Node> reduceNode() {
		return new IReduce<Object, Node>() {
			@Override
			public Node y(List<Object> x1s) {
				return (Node) x1s.get(0);
			}
		};		
	}
	
	private <Node> ISynchronousFunction1<Node, List<IEvaluation<?>>> getEvaluations(TypeToken<?> nodeToken, Tree nodeRoute) {
		PropertyDescriptor[] childDescriptors = PropertyUtils.getPropertyDescriptors(nodeToken.getRawType());
		int childCount = nodeRoute.getChildCount();
		final Method[] readChilds = new Method[childCount];
		final IEvaluator1<?, ?> loadChilds[] = new IEvaluator1<?, ?>[childCount]; 
		for (int i = 0; i < childCount; ++i) {
			Tree childRoute = nodeRoute.getChild(i); 
			String childName = childRoute.getText();
			for (int j = 0; i < childDescriptors.length; ++j) {				
				if (childDescriptors[j].getName().equals(childName)) {
					readChilds[i] = childDescriptors[j].getReadMethod();
					TypeToken<?> childToken = TypeToken.of(readChilds[i].getGenericReturnType());
					loadChilds[i] = compile(childToken, childRoute);
					break;
				}
			}
		}
		
		return new ISynchronousFunction1<Node, List<IEvaluation<?>>>() {
			@Override
			public List<IEvaluation<?>> y(Node x1) {
				List<IEvaluation<?>> ys = new ArrayList<>();

				Node node = (Node) x1;
				ys.add(new Constant<>(node));
				
				for (int i = 0; i < readChilds.length; ++i) {
					try {
						IEvaluationHolder<?> holder = (IEvaluationHolder<?>) readChilds[i].invoke(x1, (Object[]) null);
						IEvaluation evaluation = holder.getEvaluation();
						ys.add(loadChilds[i].y(evaluation));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}

				return ys;
			}
		};
	}
}
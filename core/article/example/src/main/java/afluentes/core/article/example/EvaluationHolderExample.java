package afluentes.core.article.example;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

class EvaluationHolderExample {

interface Subject {
  void request();
}

class Proxy implements 
    IEvaluationHolder<Subject>,
    Subject {
  IEvaluation<Subject> evaluation;
	
  public IEvaluation<Subject> getEvaluation() {
    return evaluation;
  }

  public void request() {
    getRealSubject().request();
  }
  
  Subject getRealSubject() {
	  return evaluation.y();
  }
}

}
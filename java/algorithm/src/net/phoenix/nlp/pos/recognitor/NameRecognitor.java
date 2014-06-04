/**
 * 
 */
package net.phoenix.nlp.pos.recognitor;

import net.phoenix.nlp.pos.Dictionary;
import net.phoenix.nlp.pos.Term;
import net.phoenix.nlp.pos.TermGraph;
import net.phoenix.nlp.pos.dictionary.PersonTermAttribute;

/**
 * 
 * 人名识别基类，处理公共方法
 * @author lixf
 *
 */
public class NameRecognitor extends AbstractRecognitor {

	public NameRecognitor(Dictionary dictionary){
		super(dictionary);
	}
	

	/**
	 * 计算前导词的频率
	 * 
	 * @param path
	 */
	protected int calcLeadingFrequency(TermGraph graph, Term name, Term leading) {
		//TermEdge first = path.get(0);
		int weight = 1;
		if (leading.equals(graph.getStartVertex()))
			 weight = 10;
		PersonTermAttribute attr = (PersonTermAttribute) leading.getTermNatures().getAttribute(PersonTermAttribute.ATTRIBUTE);
		if (attr == null)
			weight = 1;
		else 
			weight =  attr.getLeadingFrequency() + 1;
		return weight;

	}

	/**
	 * 计算后缀词的频率
	 * 
	 * @param path
	 * @return
	 */
	protected int calcFollowingFrequency(TermGraph graph, Term name, Term following) {
		int weight = 1;
		if (graph.getEndVertex().equals(following))
			weight = 10;
		PersonTermAttribute attr = (PersonTermAttribute) following.getTermNatures().getAttribute(PersonTermAttribute.ATTRIBUTE);
		if (attr != null)
			weight = attr.getFollowingFrequency() + 1;
		return weight;
		
	}

}
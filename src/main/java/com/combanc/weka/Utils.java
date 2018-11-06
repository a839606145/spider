package com.combanc.weka;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.clusterers.Clusterer;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Utils {

	//读取数据
	public static Instances getInstance(String fileName) throws Exception{
		return ConverterUtils.DataSource.read(MLearnTest.class.getClassLoader().getResourceAsStream(fileName));
	}
	public static void print(Clusterer cluster){
		System.out.println(cluster.toString());
	}
	
	//过滤数据【去掉某些属性】
	public static Instances filterInstances(Instances old,int[] indices) throws Exception{
		Remove remove=new Remove();
		remove.setAttributeIndicesArray(indices);//选择去掉那些属性
		remove.setInputFormat(old);
		return Filter.useFilter(old, remove);
	}
	
	/**
	 * 自动属性选择
	 * @param old
	 * @return
	 * @throws Exception
	 */
	public static Instances filterInstanceByApi(Instances old) throws Exception{
		AttributeSelection attributeSelection=new AttributeSelection();
		CfsSubsetEval eval=new CfsSubsetEval();
		GreedyStepwise search=new GreedyStepwise();
		search.setSearchBackwards(true);
		attributeSelection.setSearch(search);
		attributeSelection.setEvaluator(eval);
		attributeSelection.SelectAttributes(old);
	    int[] indices=attributeSelection.selectedAttributes();
	    return   Utils.filterInstances(old, indices);
	}
}

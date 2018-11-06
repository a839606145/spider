package com.combanc.weka;

import com.combanc.util.Strings;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Capabilities;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class MLearnTest {

	public final static String PATH="/";
	
	//线性回归   预测 a1x1+a2x2+a3x3....+a0;
	public Classifier testLinearRegresession(Instances dataset) throws Exception{
	    LinearRegression lp=new LinearRegression();
	    try{
	    	lp.buildClassifier(dataset);
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    double[] coef = lp.coefficients();
	   for(int i=0;i<coef.length;i++){
		 System.out.print("权重系数："+coef[i]+" ");
	   }
	    return lp;
	}
	//随机森林
	public Classifier testRandomForestClassfier(Instances dataset) throws Exception{
		RandomForest rf=new RandomForest();
		String[] ops=new String[]{"-print"};
		rf.setOptions(ops);
		rf.buildClassifier(dataset);
		
		/*System.out.println("options:");
		String[] ops=rf.getOptions();
		for(String s:ops){
			System.out.println(s);
		}*/
		Capabilities cb=rf.getCapabilities();
		System.out.println("Capabilities:");
		
		
		return rf;
	}
	//J48决策树
	public Classifier testJ48(Instances dataset) throws Exception{
		Classifier cf=new J48();
		cf.buildClassifier(dataset);
		return cf;
	}
	
	
	//评估分类器
	public void evauationFiter(Classifier cf,Instances test) throws Exception{
		Evaluation classifierEval=new Evaluation(test);
		classifierEval.evaluateModel(cf, test);
		System.out.println(classifierEval.toSummaryString("\nResults\n\n", false));
		System.out.println("ROC下面的面积："+classifierEval.areaUnderROC(0));
	}
	//预测分类器
	public void predictFiter(Classifier classifier,Instances test) throws Exception{
		for (int i = 0; i < test.numInstances(); i++) {
            double pred = classifier.classifyInstance(test.instance(i));
            double[] dist = classifier.distributionForInstance(test.instance(i));
            StringBuilder sb = new StringBuilder();
            sb.append("样本"+(i + 1))
                    .append(" - ")
                    .append("原class:"+test.instance(i).toString(test.classIndex()))
                    .append(" - ")
                    .append("预测class:"+test.classAttribute().value((int) pred))
                    .append(" - ");
            if (pred != test.instance(i).classValue())
                sb.append("no");
            else
                sb.append("yes");
            sb.append(" - ");
            sb.append(Strings.arrayToString(dist));
            System.out.println(sb.toString());
        }
	}
	//CV验证 样本分一部分不进行参数建模【训练】
	public void getCV(Instances old){
		int seed = 123;
        int folds = 2;

        Debug.Random random = new Debug.Random(seed);
        Instances newData = new Instances(old);
        newData.randomize(random);
        if (newData.classAttribute().isNominal())
            newData.stratify(folds);
        for(int i=0;i<folds;i++){
        	Instances train = newData.trainCV(folds, i);
        	Instances test = newData.testCV(folds, i);
        	System.out.println(train);
        	System.out.println(test);
        }
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		MLearnTest mlearn=new MLearnTest();
		Instances dataset=Utils.getInstance("legeng.arff");
		dataset.setClassIndex(dataset.numAttributes()-1);
	    /*Classifier classifier= mlearn.testJ48(dataset);
	    Instances testData=mlearn.getInstance("legeng-test.arff");
	    testData.setClassIndex(testData.numAttributes()-1);
	    mlearn.evauationFiter(classifier,testData);
	    mlearn.predictFiter(classifier,testData);*/
		mlearn.getCV(dataset);
	    
	}
}

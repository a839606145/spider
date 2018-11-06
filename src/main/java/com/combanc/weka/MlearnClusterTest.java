package com.combanc.weka;


import com.combanc.util.Strings;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 * 聚类test：
 * https://blog.csdn.net/Katherine_hsr/article/details/79382249
 * Kmeans：k均值
 * K-Medians：使用中位数
 * 均值漂移聚类：使用一个圆内的中位数
 * 基于密度的聚类方法(DBSCAN)
 * 高斯混合EM
 * 层次聚类
 * @author Administrator
 *
 *
 */
public class MlearnClusterTest {

	/**
	 * EM聚类【最大似然聚类】
	 * @param instances
	 * @return
	 * @throws Exception
	 */
	public Clusterer testEM(Instances instances) throws Exception{
		EM cluster =new EM();
		cluster.setOptions(new String[]{"-I", "100"});
		cluster.buildClusterer(instances);
		return cluster;
	}
	/**
	 * 可选参数
	 * 簇的个数：-N <num>
     * 聚类中心点初始化选择-init 可选择 0 = random, 1 = k-means++, 2 = canopy, 3 = farthest first
     * 距离计算的选择 -A <classname and options>
     * 迭代计算次数 -I <num>
     * 随机数种子 -S <num>
	 * 
	 * @param instances
	 * @return
	 * @throws Exception
	 */
	public Clusterer testKMean(Instances instances) throws Exception{
		SimpleKMeans km=new SimpleKMeans();
		km.buildClusterer(instances);
		return km;
	}
	
	/**
	 * 评估聚类
	 * @param cluster
	 * @param data
	 * @throws Exception
	 */
	public void evaluation(Clusterer cluster,Instances data) throws Exception{
		ClusterEvaluation clusterEvaluation = new ClusterEvaluation();
        clusterEvaluation.setClusterer(cluster);
        clusterEvaluation.evaluateClusterer(data);
        System.out.println(clusterEvaluation.clusterResultsToString());
        double[] cnum=clusterEvaluation.getClusterAssignments();//获取每个样本聚类后所属类别
        System.out.println(Strings.arrayToString(cnum)); ;
        
	}
	/**
	 * 预测聚类
	 * @param clusterer
	 * @param testdata
	 * @throws Exception
	 */
	public void predict(Clusterer clusterer,Instances testdata) throws Exception{
		for(int i=0;i<testdata.numInstances();i++){
			int cluster = clusterer.clusterInstance(testdata.instance(i));
            double[] dists = clusterer.distributionForInstance(testdata.instance(i));
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1).append(" - ").append(cluster).append(" - ").append(Strings.arrayToString(dists));
            System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args) throws Exception {
		MlearnClusterTest test=new MlearnClusterTest();
		Instances data=Utils.getInstance("contact-lenses.arff");
		Clusterer cluster=test.testKMean(data);
		Utils.print(cluster);
		
		/*System.out.println(Strings.arrayToString(((EM)cluster).getClusterPriors()));
		
		double[][][] c=((EM)cluster).getClusterModelsNumericAtts();
		for(int i=0;i<c.length;i++){
			for(int j=0;j<c[0].length;j++){
				for(int m=0;m<c[0][0].length;m++){
					System.out.print(c[i][j][m]);
				}
				System.out.println("\r");
			}
		}*/
		// test.evaluation(cluster, data);
		/*Instances testData=Utils.getInstance("segment-test.arff");
		test.predict(cluster, testData);*/
	}
}
